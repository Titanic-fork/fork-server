package com.titanic.fork.service.goal;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.goal.Alarm;
import com.titanic.fork.domain.goal.Goal;
import com.titanic.fork.repository.GoalRepository;
import com.titanic.fork.domain.goal.Location;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import com.titanic.fork.web.login.LoginEnum;
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

    public void create(CreateGoalRequest createGoalRequest, HttpServletRequest request) {
        String userEmail = (String) request.getAttribute(LoginEnum.USER_EMAIL.getValue());
        Account foundAccount = accountRepository.findByEmail(userEmail);

        Location location = Location.builder()
                .address(createGoalRequest.getAddress())
                .latitude(createGoalRequest.getLatitude())
                .longitude(createGoalRequest.getLongitude())
                .build();

        List<String> targetDayOfWeeks = new ArrayList<>();
        targetDayOfWeeks.add(createGoalRequest.getTargetDayOfWeeks());

        Alarm alarm = Alarm.builder()
                .targetDayOfWeeks(targetDayOfWeeks)
                .alarmTime(createGoalRequest.getAlarmTime())
                .content(createGoalRequest.getContent())
                .build();

        AccountGoal accountGoal = AccountGoal.builder()
                .alarm(alarm)
                .targetTime(createGoalRequest.getTargetTime())
                .build();

        // account에 accountGoal를 추가
        AccountGoal savedAccountGoal = foundAccount.addAccountGoal(accountGoal);
        accountRepository.save(foundAccount);

        Goal newGoal = CreateGoalRequest.toEntity(createGoalRequest, location);

        // account에 추가한 accountGoal를 추가해야 테이블에서 정상처리된다.
        newGoal.addAccountGoal(savedAccountGoal);

        goalRepository.save(newGoal);
    }
}
