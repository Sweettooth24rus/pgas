package com.kkoz.pgas.stateMachine._state_manager.impl;

import com.kkoz.pgas.controls.exceptions.SendEventException;
import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.stateMachine._state_machine_enum.StateMachineEnum;
import com.kkoz.pgas.stateMachine._state_manager.StateManagerApplication;
import com.kkoz.pgas.stateMachine.application.event.EventApplication;
import com.kkoz.pgas.stateMachine.application.interceptor.ApplicationStateChangeInterceptor;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * класс для взаимодействия с машиной состояний
 */

@Component
@RequiredArgsConstructor
public class StateManagerApplicationImpl implements StateManagerApplication {

    /**
     * заголовок для машины состояний
     */
    public static final String APPLICATION_OBJ_HEADER = "APPLICATION_OBJ_HEADER";

    private final ApplicationStateChangeInterceptor applicationStateChangeInterceptor;
    private final StateMachineFactory<StateApplication, EventApplication> stateMachineFactory;

    @Override
    public void sendApplicationEvent(Application application, EventApplication eventEnum) {
        StateMachine<StateApplication, EventApplication> sm = build(application);

        Message<EventApplication> msg = MessageBuilder.withPayload(eventEnum)
                .setHeader(APPLICATION_OBJ_HEADER, application)
                .build();

        sm.sendEvent(msg);
        checkSendEventExceptions(sm.getExtendedState().getVariables());
    }

    /**
     * Присваиваем созданной машине, состояние заявления из базы
     * @param application текущее заявление
     * @return возвращает statemachine в нужном состоянии
     */
    @Override
    public StateMachine<StateApplication, EventApplication> build(Application application) {
        StateMachine<StateApplication, EventApplication> sm = stateMachineFactory.getStateMachine("app_" + application.getId().toString());

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(applicationStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(application.getStatus(), null, null, null));
                });

        return sm;
    }

    private void checkSendEventExceptions(Map<Object, Object> stateMachineVariables) {
        boolean fl = (boolean) stateMachineVariables.getOrDefault(StateMachineEnum.EXCEPTION, true);
        if (!fl) {
            throw new SendEventException(
                    (String) stateMachineVariables.getOrDefault(
                            StateMachineEnum.EXCEPTION_MESSAGE,
                            "Ошибка перехода между статусами"
                    )
            );
        }
    }
}
