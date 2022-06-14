package com.kkoz.pgas.stateMachine._state_manager;

import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.stateMachine.application.event.EventApplication;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import org.springframework.statemachine.StateMachine;

public interface StateManagerApplication {

    void sendApplicationEvent(Application application, EventApplication eventEnum);

    StateMachine<StateApplication, EventApplication> build(Application application);

}
