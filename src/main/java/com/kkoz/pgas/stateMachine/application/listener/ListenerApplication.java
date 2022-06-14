package com.kkoz.pgas.stateMachine.application.listener;

import com.kkoz.pgas.stateMachine.application.event.EventApplication;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
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
public class ListenerApplication implements StateMachineListener<StateApplication, EventApplication> {

    @Override
    public void stateChanged(State<StateApplication, EventApplication> from, State<StateApplication, EventApplication> to) {

    }

    @Override
    public void stateEntered(State<StateApplication, EventApplication> state) {

    }

    @Override
    public void stateExited(State<StateApplication, EventApplication> state) {

    }

    @Override
    public void eventNotAccepted(Message<EventApplication> message) {
        log.error("Переход не произведен {}", message.getPayload());
    }

    @Override
    public void transition(Transition<StateApplication, EventApplication> transition) {
        log.debug("  _.move from:{} to:{}",
                ofNullableState(transition.getSource()),
                ofNullableState(transition.getTarget()));
    }

    private Object ofNullableState(State s) {
        return Optional.ofNullable(s)
                .map(State::getId)
                .orElse(null);
    }

    @Override
    public void transitionStarted(Transition<StateApplication, EventApplication> transition) {

    }

    @Override
    public void transitionEnded(Transition<StateApplication, EventApplication> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<StateApplication, EventApplication> stateMachine) {
        log.info("Машина запущена, state: {}", stateMachine.getState().getId());
    }

    @Override
    public void stateMachineStopped(StateMachine<StateApplication, EventApplication> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<StateApplication, EventApplication> stateMachine, Exception e) {
        log.error("Произошла внутрення ошибка машины-состояний. Exception: {}", e.getMessage());
    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<StateApplication, EventApplication> stateContext) {

    }
}
