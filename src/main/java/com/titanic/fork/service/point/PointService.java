package com.titanic.fork.service.point;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.point.Point;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.point.PointRepository;
import com.titanic.fork.web.dto.response.point.MonthlyPointResponse;
import com.titanic.fork.web.dto.response.point.PointRankingResponse;
import com.titanic.fork.web.dto.response.point.PointResponse;
import com.titanic.fork.web.login.LoginEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {

    private final PointRepository pointRepository;
    private final AccountGoalRepository accountGoalRepository;
    private final AccountRepository accountRepository;

    public PointResponse getTotalAndAvailablePoint(Long goalId, HttpServletRequest request) {
        Account foundAccount = findByEmail(request);

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

    private Account findByEmail(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute(LoginEnum.USER_EMAIL.getValue());
        return accountRepository.findByEmail(userEmail);
    }

    // 사용자가 해당 년,월 동안 적립한 포인트 조회
    public MonthlyPointResponse getMonthlySavedPoint(Long goalId, Integer year, Integer month, HttpServletRequest request) {
        Account foundAccount = findByEmail(request);

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
        Account foundAccount = findByEmail(request);

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
        return null;
    }
}
