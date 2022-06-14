package com.kkoz.pgas.stateMachine.competition.component;

import com.kkoz.pgas.entities.application.Application;
import com.kkoz.pgas.entities.competition.Competition;
import com.kkoz.pgas.repositories.RepoApplication;
import com.kkoz.pgas.repositories.RepoCompetition;
import com.kkoz.pgas.stateMachine._state_machine_enum.GuardEnum;
import com.kkoz.pgas.stateMachine.application.state.StateApplication;
import com.kkoz.pgas.stateMachine.competition.guards.GuardCompetitionEnum;
import com.kkoz.pgas.stateMachine.competition.state.StateCompetition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * класс для валидации конкурса, работает перед каждым переходом
 * возвращает Map со значениями для нужного Guard
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateCompetitionSM {

    private final RepoApplication repoApplication;

    public Map<GuardEnum, Boolean> checkEntity(Competition entity) {
        Map<GuardEnum, Boolean> guardVar = new HashMap<>();

        guardVar.put(GuardCompetitionEnum.ALLOCATION_TO_CLOSED, this.checkAllocationToClosedSubsidyAllocation(entity));

        return guardVar;
    }

    private boolean checkAllocationToClosedSubsidyAllocation(Competition entity) {

        List<Application> applications = repoApplication.findAllByCompetition(entity);
        for (Application application:
             applications) {
            application.setStatus(StateApplication.CLOSED);
        }
        return true;
    }

}
