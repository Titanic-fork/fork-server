package com.titanic.fork.repository.goal;

import com.titanic.fork.domain.goal.Goal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class GoalRepository {

    private final EntityManager entityManager;

    public void save(Goal newGoal) {
        entityManager.persist(newGoal);
    }

    public Goal findById(Long goalId) {
        return entityManager.find(Goal.class, goalId);
    }
}
