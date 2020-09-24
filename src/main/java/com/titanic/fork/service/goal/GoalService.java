package com.titanic.fork.service.goal;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.goal.Alarm;
import com.titanic.fork.domain.goal.Goal;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.goal.GoalRepository;
import com.titanic.fork.domain.goal.Location;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.domain.goal.AchievementCalculator;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalService {

    private final GoalRepository goalRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final AccountGoalRepository accountGoalRepository;
    private final AchievementCalculator achievementCalculator;

    public void create(CreateGoalRequest createGoalRequest, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);
        Location location = Location.from(createGoalRequest);

        List<String> targetDayOfWeeks = new ArrayList<>();
        targetDayOfWeeks.add(createGoalRequest.getTargetDayOfWeeks());

        Alarm alarm = Alarm.of(targetDayOfWeeks, createGoalRequest);

        AccountGoal accountGoal = AccountGoal.of(alarm, createGoalRequest);

        // account에 accountGoal를 추가
        AccountGoal savedAccountGoal = foundAccount.addAccountGoal(accountGoal);
        accountRepository.save(foundAccount);

        Goal newGoal = CreateGoalRequest.toEntity(createGoalRequest, location);

        // account에 추가한 accountGoal를 추가해야 테이블에서 정상처리된다.
        newGoal.addAccountGoal(savedAccountGoal);
        goalRepository.save(newGoal);
    }

    public AchievementResponse getAchievement(Long goalId, Integer todayTime, Integer weeklyTime, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);

        return achievementCalculator.calculateAchievement(foundAccountGoal, todayTime, weeklyTime);
    }
}
