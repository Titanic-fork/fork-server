package com.titanic.fork.service.point;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.repository.GoalRepository;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.point.PointRepository;
import com.titanic.fork.web.dto.response.point.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {

    private final PointRepository pointRepository;
    private final GoalRepository goalRepository;
    private final AccountGoalRepository accountGoalRepository;
    private final AccountRepository accountRepository;

    public PointResponse getTotalAndAvailablePoint(Long goalId, HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        Account foundAccount = accountRepository.findByEmail(userEmail);

        // accountID와 goalId로 1개 accountGoal을 찾는다.
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId(foundAccount.getId(), goalId);
        pointRepository.findSavingPointByAccountGoalId(foundAccount.getId());


        return null;
    }
}
