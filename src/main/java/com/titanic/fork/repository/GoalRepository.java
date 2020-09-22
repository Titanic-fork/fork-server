package com.titanic.fork.repository;

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
}
