package com.kkoz.pgas.stateMachine.competition.config;

import com.kkoz.pgas.stateMachine.competition.event.EventCompetition;
import com.kkoz.pgas.stateMachine.competition.guards.*;
import com.kkoz.pgas.stateMachine.competition.listener.ListenerCompetition;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;

import static com.kkoz.pgas.stateMachine.competition.event.EventCompetition.NEXT;
import static com.kkoz.pgas.stateMachine.competition.state.StateCompetition.*;


/**
 * Настройки машины состояний конкурсов
 */

@Slf4j
@Configuration
@EnableStateMachineFactory(name = "stateMachineFactoryCompetition")
public class StateMachineConfigCompetition extends EnumStateMachineConfigurerAdapter<StateCompetition, EventCompetition> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<StateCompetition, EventCompetition> config) throws Exception {
        config.withConfiguration()
                .listener(new ListenerCompetition())
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<StateCompetition, EventCompetition> states) throws Exception {
        states.withStates()
                .initial(DRAFT)
                .end(CLOSED)
                .states(
                        EnumSet.allOf(
                                StateCompetition.class
                        )
                );
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StateCompetition, EventCompetition> transition) throws Exception {
        transition
                // "Подготовка" -> "Подача заявок"
                .withExternal()
                .source(DRAFT)
                .target(SUBMISSION)
                .event(NEXT)

                // "Подача заявок" -> "Подготовка"
                .and()
                .withExternal()
                .source(SUBMISSION)
                .target(EVALUATION)
                .event(NEXT)

                // "Подача заявок" -> "Модерация заявок"
                .and()
                .withExternal()
                .source(EVALUATION)
                .target(ALLOCATION)
                .event(NEXT)

                // "Модерация заявок" -> "Подача заявок"
                .and()
                .withExternal()
                .source(ALLOCATION)
                .target(CLOSED)
                .event(NEXT)
        ;
    }
}
