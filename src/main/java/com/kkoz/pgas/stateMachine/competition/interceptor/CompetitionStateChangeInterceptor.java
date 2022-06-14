package com.kkoz.pgas.stateMachine.competition.interceptor;

import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.repositories.RepoApplication;
import com.kkoz.pgas.repositories.RepoCompetition;
import com.kkoz.pgas.stateMachine._state_machine_enum.GuardEnum;
import com.kkoz.pgas.stateMachine._state_manager.impl.StateManagerCompetitionImpl;
import com.kkoz.pgas.stateMachine.competition.component.ValidateCompetitionSM;
import com.kkoz.pgas.stateMachine.competition.event.EventCompetition;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * перехватчик, чтобы перехватить и остановить изменение текущего состояния или изменить его логику перехода
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class CompetitionStateChangeInterceptor extends StateMachineInterceptorAdapter<StateCompetition, EventCompetition> {

    private final RepoCompetition repoCompetition;
    private final ValidateCompetitionSM validateCompetitionSM;

    /**
     * Перед переходом происходит валидация конкурса, записываются результаты и если между переходами есть Guard
     * он проверяет результат читая его из переменной контекста машины-состояний
     */
    @Override
    public Message<EventCompetition> preEvent(Message<EventCompetition> message, StateMachine<StateCompetition, EventCompetition> stateMachine) {
        log.debug("  _.Pre-Event - validate competition");

        Optional.ofNullable(message)
                .flatMap(
                        msg -> Optional.ofNullable(
                                msg.getHeaders().getOrDefault(
                                        StateManagerCompetitionImpl.COMPETITION_OBJ_HEADER, null
                                )
                        )
                )
                .ifPresent(competition -> {
                    Map<GuardEnum, Boolean> guardVar = validateCompetitionSM.checkEntity((Competition) competition);
                    stateMachine.getExtendedState().getVariables().putAll(guardVar);

                });
        return message;
    }


    /**
     * действие выполняется перед сменой состояний как видно из названия
     * общий смысл - ищем нужное сообщение, если оно есть и не пустое, то меняем статус нужного конкурса
     */
    @Transactional
    @Override
    public void preStateChange(State<StateCompetition, EventCompetition> state,
                               Message<EventCompetition> message,
                               Transition<StateCompetition, EventCompetition> transition,
                               StateMachine<StateCompetition, EventCompetition> stateMachine,
                               StateMachine<StateCompetition, EventCompetition> rootStateMachine) {
        log.debug("  _.Pre-State Change");

        Optional.ofNullable(message)
                .flatMap(
                        msg -> Optional.ofNullable(
                                msg.getHeaders().getOrDefault(
                                        StateManagerCompetitionImpl.COMPETITION_OBJ_HEADER, null
                                )
                        )
                )
                .ifPresent(
                        competition -> {
                            Competition cmp = (Competition) competition;
                            cmp.setStatus(state.getId());
                            repoCompetition.saveAndFlush(cmp);
                            log.debug("  _.Saving state for competition id: " + cmp.getId() + " Status: " + state.getId());
                        }
                );
    }

    @Override
    public void postStateChange(State<StateCompetition, EventCompetition> state, Message<EventCompetition> message,
                                Transition<StateCompetition, EventCompetition> transition,
                                StateMachine<StateCompetition, EventCompetition> stateMachine,
                                StateMachine<StateCompetition, EventCompetition> rootStateMachine) {
        log.debug("  _.Post-State Change");

        Optional.ofNullable(message)
                .flatMap(
                        msg -> Optional.ofNullable(
                                msg.getHeaders().getOrDefault(
                                        StateManagerCompetitionImpl.COMPETITION_OBJ_HEADER, null
                                )
                        )
                )
                .ifPresent(
                        competition -> {
                            Competition cmp = (Competition) competition;
                            cmp.setStatus(state.getId());
                            repoCompetition.saveAndFlush(cmp);
                            log.debug("  _.Saving state for competition id: " + cmp.getId() + " Status: " + state.getId());
                        }
                );
    }
}
