package com.titanic.fork.repository.accountGoal;

import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.exception.accountGoal.NoSuchAccountGoalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountGoalRepository {

    private final EntityManager entityManager;

    public AccountGoal findByAccountIdAndGoalId(Long accountId, Long goalId) {
        List<AccountGoal> foundAccountGoal = entityManager.createQuery("select ag from AccountGoal as ag where ag.account.id = :accountId and ag.goal.id = :goalId", AccountGoal.class)
                .setParameter("accountId", accountId)
                .setParameter("goalId", goalId)
                .getResultList();

        if (foundAccountGoal.size() == 0) {
            throw new NoSuchAccountGoalException();
        }

        return foundAccountGoal.get(0);
    }
}