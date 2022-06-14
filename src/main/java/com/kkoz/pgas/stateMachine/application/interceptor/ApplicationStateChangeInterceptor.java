package com.kkoz.pgas.stateMachine.application.interceptor;

import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.repositories.RepoApplication;
import com.kkoz.pgas.stateMachine._state_machine_enum.GuardEnum;
import com.kkoz.pgas.stateMachine._state_manager.impl.StateManagerApplicationImpl;
import com.kkoz.pgas.stateMachine.application.component.ValidateApplication;
import com.kkoz.pgas.stateMachine.application.event.EventApplication;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * перехватчик, чтобы перехватить и остановить изменение текущего состояния или изменить его логику перехода
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationStateChangeInterceptor extends StateMachineInterceptorAdapter<StateApplication, EventApplication> {

    private final RepoApplication repoApplication;
    private final ValidateApplication validateApplication;

    /**
     * Перед переходом происходит валидация заявления, записываются результаты и если между переходами есть Guard
     * он проверяет результат читая его из переменной контекста машины-состояний
     */
    @Override
    public Message<EventApplication> preEvent(Message<EventApplication> message, StateMachine<StateApplication, EventApplication> stateMachine) {
        log.debug("  _.Pre-Event - validate application");

        Optional.ofNullable(message)
                .flatMap(
                        msg -> Optional.ofNullable(
                                msg.getHeaders().getOrDefault(StateManagerApplicationImpl.APPLICATION_OBJ_HEADER, null)
                        )
                )
                .ifPresent(application -> {
                    Map<GuardEnum, Boolean> guardVar = validateApplication.checkEntity((Application) application);
                    stateMachine.getExtendedState().getVariables().putAll(guardVar);

                });
        return message;
    }

    /**
     * действие выполняется перед сменой состояний как видно из названия
     * общий смысл - ищем нужное сообщение, если оно есть и не пустое, то меняем статус нужного заявления
     */
    @Transactional
    @Override
    public void preStateChange(State<StateApplication, EventApplication> state,
                               Message<EventApplication> message,
                               Transition<StateApplication, EventApplication> transition,
                               StateMachine<StateApplication, EventApplication> stateMachine,
                               StateMachine<StateApplication, EventApplication> rootStateMachine) {
        log.debug("  _.Pre-State Change");

        Optional.ofNullable(message)
                .flatMap(
                        msg -> Optional.ofNullable(
                                msg.getHeaders().getOrDefault(StateManagerApplicationImpl.APPLICATION_OBJ_HEADER, null)
                        )
                )
                .ifPresent(
                        application -> {
                            Application app = (Application) application;
                            app.setStatus(state.getId());
                            repoApplication.saveAndFlush(app);
                            log.debug("  _.Saving state for application id: " + app.getId() + " Status: " + state.getId());
                        }
                );
    }
}
