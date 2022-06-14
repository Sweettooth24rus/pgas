package com.kkoz.pgas.stateMachine.application.config;

import com.kkoz.pgas.stateMachine.application.event.EventApplication;
import com.kkoz.pgas.stateMachine.application.guards.*;
import com.kkoz.pgas.stateMachine.application.listener.ListenerApplication;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
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

import static com.kkoz.pgas.stateMachine.application.event.EventApplication.*;
import static com.kkoz.pgas.stateMachine.application.state.StateApplication.*;


@Slf4j
@Configuration
@EnableStateMachineFactory(name = "stateMachineFactoryApplication")
public class StateMachineConfigApplication extends EnumStateMachineConfigurerAdapter<StateApplication, EventApplication> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<StateApplication, EventApplication> config) throws Exception {
        config.withConfiguration()
                .listener(new ListenerApplication())
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<StateApplication, EventApplication> states) throws Exception {
        states.withStates()
                .initial(DRAFT)
                .end(CLOSED)
                .states(
                        EnumSet.allOf(
                                StateApplication.class
                        )
                );
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StateApplication, EventApplication> transitions) throws Exception {
        transitions
                .withExternal()
                .source(DRAFT)
                .target(IN_PROCESSING)
                .event(NEXT)
                .guard(this.guardDraftToInProcessing())

                .and()
                .withExternal()
                .source(DRAFT)
                .target(CLOSED)
                .event(TO_CLOSED)

                .and()
                .withExternal()
                .source(IN_PROCESSING)
                .target(ACCEPTED)
                .event(NEXT)

                .and()
                .withExternal()
                .source(IN_PROCESSING)
                .target(FOR_REVISION)
                .event(FOR_REV)

                .and()
                .withExternal()
                .source(IN_PROCESSING)
                .target(CLOSED)
                .event(TO_CLOSED)

                .and()
                .withExternal()
                .source(ACCEPTED)
                .target(CLOSED)
                .event(TO_CLOSED)
        ;
    }

    @Bean
    public Guard<StateApplication, EventApplication> guardDraftToInProcessing() {
        return new GuardApplicationDraftToInProcessing();
    }
}
