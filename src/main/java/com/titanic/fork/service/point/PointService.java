package com.titanic.fork.service.point;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.point.Point;
import com.titanic.fork.domain.point.PointCalculator;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.point.PointRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.utils.DateSupplier;
import com.titanic.fork.web.dto.response.point.EachMonthlySavedPointStatus;
import com.titanic.fork.web.dto.response.point.MonthlyPointResponse;
import com.titanic.fork.web.dto.response.point.PointRankingResponse;
import com.titanic.fork.web.dto.response.point.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {

    private final PointRepository pointRepository;
    private final AccountGoalRepository accountGoalRepository;
    private final AccountService accountService;
    private final PointCalculator pointCalculator;
    private final DateSupplier dateSupplier;

    public MonthlyPointResponse getMonthlySavedPoint(Long goalId, Integer year, Integer month, HttpServletRequest request) {
        int nextMonth = month + 1;
        Account foundAccount = accountService.findByEmail(request);
        // accountID와 goalId로 1개 accountGoal을 찾는다.
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        List<Point> monthlySavedPoints = pointRepository.findAllMonthlySavedPoint(foundAccountGoal.getId(),
                dateSupplier.localDateTime(year, month), dateSupplier.localDateTime(year, nextMonth));

        int savedPointSum = pointCalculator.sumCalculate(monthlySavedPoints);
        return MonthlyPointResponse.toSavedPoint(savedPointSum);
    }

    public MonthlyPointResponse getMonthlyUsedPoint(Long goalId, Integer year, Integer month, HttpServletRequest request) {
        int nextMonth = month + 1;
        Account foundAccount = accountService.findByEmail(request);
        // accountID와 goalId로 1개 accountGoal을 찾는다.
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        List<Point> monthlyUsedPoints = pointRepository.findAllMonthlyUsedPoint(foundAccountGoal.getId(),
                dateSupplier.localDateTime(year,month), dateSupplier.localDateTime(year, nextMonth));
        int usedPointSum = pointCalculator.sumCalculate(monthlyUsedPoints);

        return MonthlyPointResponse.toUsedPoint(usedPointSum);
    }

    public PointResponse getTotalAndAvailablePoint(Long goalId, HttpServletRequest request) {
        Account foundAccount = accountService.findByEmail(request);

        // accountID와 goalId로 1개 accountGoal을 찾는다.
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        // accountGoal로 해당 목표에 쌓은 사용자의 누적포인트 및 사용된 포인트 조회
        List<Point> savingPoints = pointRepository.findAllSavingPointByAccountGoalId(foundAccountGoal.getId());
        List<Point> usedPoints = pointRepository.findAllUsedPointByAccountGoalId(foundAccount.getId());

        int totalPoint = pointCalculator.sumCalculate(savingPoints);
        int usedPoint = pointCalculator.sumCalculate(usedPoints);
        return PointResponse.of(totalPoint, usedPoint);
    }

    public PointRankingResponse getMonthlyPointRanking(Long goalId, Integer year, Integer month, HttpServletRequest request) {
        List<AccountGoal> foundAccountGoals = accountGoalRepository.findAllByGoalId(goalId);
        List<EachMonthlySavedPointStatus> eachMonthlySavedPoints =
                pointCalculator.getRankingOfPoints(foundAccountGoals, year, month);

        return PointRankingResponse.of(eachMonthlySavedPoints);
    }
}
