package com.kkoz.pgas.stateMachine._state_manager;

import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.stateMachine.competition.event.EventCompetition;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import org.springframework.statemachine.StateMachine;

public interface StateManagerCompetition {

    void sendCompetitionEvent(Competition competition, EventCompetition eventEnum);

    StateMachine<StateCompetition, EventCompetition> build(Competition competition);

}
