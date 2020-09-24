package com.titanic.fork.service.point;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.point.Point;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.point.PointRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.web.dto.response.point.EachMonthlySavedPointStatus;
import com.titanic.fork.web.dto.response.point.MonthlyPointResponse;
import com.titanic.fork.web.dto.response.point.PointRankingResponse;
import com.titanic.fork.web.dto.response.point.PointResponse;
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
public class PointService {

    private final PointRepository pointRepository;
    private final AccountGoalRepository accountGoalRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public PointResponse getTotalAndAvailablePoint(Long goalId, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);

        // accountID와 goalId로 1개 accountGoal을 찾는다.
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        // accountGoal로 해당 목표에 쌓은 사용자의 누적포인트 및 사용된 포인트 조회
        List<Point> savingPoints = pointRepository.findSavingPointByAccountGoalId(foundAccountGoal.getId());
        List<Point> usedPoints = pointRepository.findUsedPointByAccountGoalId(foundAccount.getId());

        int totalPoint = savingPoints.stream()
                .mapToInt(Point::getAmount)
                .sum();

        int usedPoint = usedPoints.stream()
                .mapToInt(Point::getAmount)
                .sum();

        return PointResponse.of(totalPoint, usedPoint);
    }

    // 사용자가 해당 년,월 동안 적립한 포인트 조회
    public MonthlyPointResponse getMonthlySavedPoint(Long goalId, Integer year, Integer month, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);

        // accountID와 goalId로 1개 accountGoal을 찾는다.
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        List<Point> monthlySavedPoints = pointRepository.findMonthlySavedPoint(foundAccountGoal.getId());

        int savedPointSum = monthlySavedPoints.stream()
                .filter(point -> point.isPeriod(year,month))
                .mapToInt(Point::getAmount)
                .sum();

        return MonthlyPointResponse.builder()
                .savedPoint(savedPointSum)
                .build();
    }

    // 사용자가 해당 년,월 동안 지출한 포인트 조회
    public MonthlyPointResponse getMonthlyUsedPoint(Long goalId, Integer year, Integer month, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);

        // accountID와 goalId로 1개 accountGoal을 찾는다.
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        List<Point> monthlyUsedPoints = pointRepository.findMonthlyUsedPoint(foundAccountGoal.getId());

        int usedPointSum = monthlyUsedPoints.stream()
                .filter(point -> point.isPeriod(year,month))
                .mapToInt(Point::getAmount)
                .sum();

        return MonthlyPointResponse.builder()
                .usedPoint(usedPointSum)
                .build();
    }

    public PointRankingResponse getMonthlyPointRanking(Long goalId, Integer year, Integer month, HttpServletRequest request) {
        List<AccountGoal> foundAccountGoals = accountGoalRepository.findByGoalId(goalId);
        List<EachMonthlySavedPointStatus> eachMonthlySavedPoints = new ArrayList<>();

        /*
         * 반복문에서 각 accountGoal의 월간 적립 포인트를 찾은 뒤
         * MonthlyPointResponse에 저장한다.
         */
        for (AccountGoal accountGoal : foundAccountGoals) {
            List<Point> monthlySavedPoints = pointRepository.findMonthlySavedPoint(accountGoal.getId());
            int savedPointSum = monthlySavedPoints.stream()
                    .filter(point -> point.isPeriod(year,month))
                    .mapToInt(Point::getAmount)
                    .sum();

            EachMonthlySavedPointStatus eachMonthlySavedPointStatus
                    = EachMonthlySavedPointStatus.of(accountGoal, savedPointSum);

            eachMonthlySavedPoints.add(eachMonthlySavedPointStatus);
        }
        // 각 누적 포인트 순으로 정렬
        eachMonthlySavedPoints.sort((o1, o2) -> Integer.compare(o2.getMonthlySavedPoint(), o1.getMonthlySavedPoint()));

        return PointRankingResponse.builder()
                .eachMonthlySavedPoints(eachMonthlySavedPoints)
                .build();
    }
}
