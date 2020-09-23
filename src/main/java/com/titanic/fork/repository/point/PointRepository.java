package com.titanic.fork.repository.point;

import com.titanic.fork.domain.point.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointRepository {

    private final EntityManager entityManager;

    public List<Point> findSavingPointByAccountGoalId(Long accountGoalId) {
        List<Point> points = entityManager.createQuery("select p from Point as p left join fetch p.accountGoal as ag where ag.id = :accountGoalId", Point.class)
                .setParameter("accountGoalId", accountGoalId)
                .getResultList();
        return null;
    }
}
