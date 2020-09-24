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

    // 사용자의 누적된 전체 포인트 조회
    public List<Point> findAllSavingPointByAccountGoalId(Long accountGoalId) {
        // 상속 관계가 싱글테이블 전략일 때 하위 클래스를 조회하는 법
        return entityManager.createQuery("select p from SavingPoint as p " +
                "left join fetch p.accountGoal as ag where ag.id = :accountGoalId", Point.class)
                .setParameter("accountGoalId", accountGoalId)
                .getResultList();
    }

    // 사용자가 사용한 포인트 조회
    public List<Point> findAllUsedPointByAccountGoalId(Long accountGoalId) {
        return entityManager.createQuery("select p from UsedPoint as p " +
                "left join fetch p.accountGoal as ag where ag.id = :accountGoalId", Point.class)
                .setParameter("accountGoalId", accountGoalId)
                .getResultList();
    }

    // 사용자가 1달 간 적립한 포인트 조회
    public List<Point> findAllMonthlySavedPoint(Long accountGoalId) {
        return entityManager.createQuery("select p from SavingPoint as p " +
                "left join fetch p.accountGoal as ag where ag.id = :accountGoalId", Point.class)
                .setParameter("accountGoalId", accountGoalId)
                .getResultList();
    }

    // 사용자가 1달 간 사용한 포인트 조회
    public List<Point> findAllMonthlyUsedPoint(Long accountGoalId) {
        return entityManager.createQuery("select p from UsedPoint as p " +
                "left join fetch p.accountGoal as ag where ag.id = :accountGoalId", Point.class)
                .setParameter("accountGoalId", accountGoalId)
                .getResultList();
    }
}
