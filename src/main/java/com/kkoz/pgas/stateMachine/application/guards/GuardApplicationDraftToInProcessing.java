package com.kkoz.pgas.stateMachine.application.guards;

import com.kkoz.pgas.stateMachine._state_machine_enum.StateMachineEnum;
import com.kkoz.pgas.stateMachine.application.event.EventApplication;
import com.kkoz.pgas.stateMachine.application.guards.variables.GuardApplicationEnum;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

@Slf4j
public class GuardApplicationDraftToInProcessing implements Guard<StateApplication, EventApplication> {

    private final String className = this.getClass().getSimpleName();

    @Override
    public boolean evaluate(StateContext<StateApplication, EventApplication> context) {
        boolean flagDraftToInProcessing
                = (boolean) context.getExtendedState().getVariables().getOrDefault(GuardApplicationEnum.DRAFT_TO_IN_PROCESSING, true);

        if (!flagDraftToInProcessing) {
            context.getExtendedState().getVariables().put(StateMachineEnum.EXCEPTION, false);
            context.getExtendedState().getVariables().put(StateMachineEnum.EXCEPTION_MESSAGE,
                    "Переход заявки 'Черновик -> В рассмотрении' не выполнен. Заполните все обязательные поля."
            );

            log.debug("  _.Guard - [{}] is [{}]", className, false);

            return false;
        }

        log.debug("  _.Guard - [{}] is [{}]", className, true);

        return true;
    }

}
