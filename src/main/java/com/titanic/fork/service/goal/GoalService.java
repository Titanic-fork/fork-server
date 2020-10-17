package com.titanic.fork.service.goal;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.goal.Alarm;
import com.titanic.fork.domain.goal.Goal;
import com.titanic.fork.domain.point.Point;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.goal.GoalRepository;
import com.titanic.fork.domain.goal.Location;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.repository.point.PointRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.domain.goal.AchievementCalculator;
import com.titanic.fork.utils.checker.CheckerFactory;
import com.titanic.fork.utils.checker.DistanceChecker;
import com.titanic.fork.utils.checker.DistanceUnit;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import com.titanic.fork.web.dto.response.goal.ElapsedTimeResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.Checker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
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
    private final PointRepository pointRepository;

    public void create(CreateGoalRequest createGoalRequest, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);
        // 위치정보 생성
        Location location = Location.from(createGoalRequest);

        // 알람 목표 요일 생성
        List<String> targetDayOfWeeks = new ArrayList<>();
        targetDayOfWeeks.add(createGoalRequest.getTargetDayOfWeeks());

        // 알람 생성
        Alarm alarm = CreateGoalRequest.toEntity(targetDayOfWeeks, createGoalRequest);
        // AccountGoal 생성
        AccountGoal accountGoal = CreateGoalRequest.toEntity(alarm, createGoalRequest);

        // account에 accountGoal를 추가
        AccountGoal savedAccountGoal = foundAccount.addAccountGoal(accountGoal);
        accountRepository.save(foundAccount);

        // 목표 생성
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

    public void start(Long goalId, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        // 더티 체킹으로 자동으로 변경 SQL 실행.
        foundAccountGoal.start();
    }

    /** 종료버튼 메서드
     *  종료버튼 누르면 해당 AccountGoal의 startTime으로 소요시간 계산
     * @return ElapsedTimeResponse
     */
    public ElapsedTimeResponse end(Long goalId, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        LocalTime elapsedTime = foundAccountGoal.calculateElapsedTime();
        // 포인트 적립하는 로직
        Point savingPoint = foundAccountGoal.createSavingPoint(elapsedTime);
        // 더티 체킹으로 변경감지 일어나서 save 안해도 될 듯?
        pointRepository.save(savingPoint);
        return ElapsedTimeResponse.from(elapsedTime);
    }

    /** 사용자와 목표의 거리 계산 메서드
     *  기준 거리 : 50m
     */
    public boolean calculateDistance(Long goalId, double latitude, double longitude, HttpServletRequest request) {
        Goal foundGoal = goalRepository.findById(goalId);
        DistanceChecker distanceChecker = CheckerFactory.getInstance().getChecker(DistanceUnit.METER.unit);
        double distance = distanceChecker.getDistance(foundGoal.getLocation().getLatitude(), foundGoal.getLocation().getLongitude(),
                latitude, longitude);
        return distanceChecker.isPossible(distance);
    }
}
