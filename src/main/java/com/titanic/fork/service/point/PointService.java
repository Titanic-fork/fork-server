package com.titanic.fork.service.point;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.point.Point;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.point.PointRepository;
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
    private final AccountRepository accountRepository;

    public PointResponse getTotalAndAvailablePoint(Long goalId, HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        Account foundAccount = accountRepository.findByEmail(userEmail);

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

    public PointResponse getMonthlySavedPoint(Long goalId, Integer month, HttpServletRequest request) {
        return null;
    }
}
