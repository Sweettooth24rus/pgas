package com.kkoz.pgas.stateMachine.application.component;

import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.stateMachine._state_machine_enum.GuardEnum;
import com.kkoz.pgas.stateMachine.application.guards.variables.GuardApplicationEnum;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * класс для валидации заявления, работает перед каждым переходом
 * возвращает Map со значениями для нужного Guard
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateApplication {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public Map<GuardEnum, Boolean> checkEntity(Application entity) {
        Map<GuardEnum, Boolean> guardVar = new HashMap<>();

        guardVar.put(GuardApplicationEnum.DRAFT_TO_IN_PROCESSING, this.checkDraftToInProcessing(entity));

        return guardVar;
    }

    private Boolean checkDraftToInProcessing(Application entity) {
        Validator validator = validatorFactory.getValidator();
        //Set<ConstraintViolation<DtoApplication>> violations = validator.validate(new DtoApplication(entity));

        //return violations.size() == 0;
        return true;
        //TODO Изменить
    }
}
