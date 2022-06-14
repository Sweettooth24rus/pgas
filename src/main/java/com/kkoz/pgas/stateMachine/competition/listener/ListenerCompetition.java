package com.kkoz.pgas.stateMachine.competition.listener;

import com.kkoz.pgas.stateMachine.competition.event.EventCompetition;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

/**
 * Слушает события машины состояний
 */

@Slf4j
public class ListenerCompetition implements StateMachineListener<StateCompetition, EventCompetition> {

    @Override
    public void stateChanged(State<StateCompetition, EventCompetition> from, State<StateCompetition, EventCompetition> to) {

    }

    @Override
    public void stateEntered(State<StateCompetition, EventCompetition> state) {

    }

    @Override
    public void stateExited(State<StateCompetition, EventCompetition> state) {

    }

    @Override
    public void eventNotAccepted(Message<EventCompetition> message) {
        log.error("Переход не произведен: {}", message.getPayload());
    }

    @Override
    public void transition(Transition<StateCompetition, EventCompetition> transition) {
        log.debug("  _.transition FROM [{}] -> TO [{}]",
                ofNullableState(transition.getSource()),
                ofNullableState(transition.getTarget()));
    }

    private Object ofNullableState(State s) {
        return Optional.ofNullable(s)
                .map(State::getId)
                .orElse(null);
    }

    @Override
    public void transitionStarted(Transition<StateCompetition, EventCompetition> transition) {

    }

    @Override
    public void transitionEnded(Transition<StateCompetition, EventCompetition> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<StateCompetition, EventCompetition> stateMachine) {
        log.info("Машина запущена, state: {}", stateMachine.getState().getId());
    }

    @Override
    public void stateMachineStopped(StateMachine<StateCompetition, EventCompetition> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<StateCompetition, EventCompetition> stateMachine, Exception e) {
        log.error("Произошла внутрення ошибка Машины состояний: {}", e.getMessage());
    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<StateCompetition, EventCompetition> stateContext) {

    }
}
