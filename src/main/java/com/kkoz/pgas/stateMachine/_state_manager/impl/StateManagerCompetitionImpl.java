package com.kkoz.pgas.stateMachine._state_manager.impl;

import com.kkoz.pgas.controls.exceptions.SendEventException;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.stateMachine._state_machine_enum.StateMachineEnum;
import com.kkoz.pgas.stateMachine._state_manager.StateManagerCompetition;
import com.kkoz.pgas.stateMachine.competition.event.EventCompetition;
import com.kkoz.pgas.stateMachine.competition.interceptor.CompetitionStateChangeInterceptor;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
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
public class StateManagerCompetitionImpl implements StateManagerCompetition {

    /**
     * заголовок для машины состояний
     */
    public static final String COMPETITION_OBJ_HEADER = "COMPETITION_OBJ_HEADER";

    private final CompetitionStateChangeInterceptor competitionStateChangeInterceptor;
    private final StateMachineFactory<StateCompetition, EventCompetition> stateMachineFactory;

    /**
     * С помощью сообщений переходим из текущего статуса конкурса в следующий
     * С помощью Message мы знаем id конкурса и что с ним надо сделать.
     * Переход происходит в классе CompetitionStateChangeInterceptor.java
     * @param competition текущий конкурс
     * @param eventCompetition требуемый переход
     */
    @Override
    public void sendCompetitionEvent(Competition competition, EventCompetition eventCompetition){
        StateMachine<StateCompetition, EventCompetition> sm = build(competition);

        Message<EventCompetition> msg = MessageBuilder.withPayload(eventCompetition)
                .setHeader(COMPETITION_OBJ_HEADER, competition)
                .build();

        sm.sendEvent(msg);
        checkSendEventExceptions(sm.getExtendedState().getVariables());
    }

    /**
     * Присваиваем созданной машине, состояние конкурса из базы
     * @param competition текущий конкурса
     * @return возвращает statemachine в нужном состоянии
     */
    @Override
    public StateMachine<StateCompetition, EventCompetition> build(Competition competition) {
        StateMachine<StateCompetition, EventCompetition> sm = stateMachineFactory.getStateMachine("competition_" + competition.getId().toString());

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(competitionStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(competition.getStatus(), null, null, null));
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
