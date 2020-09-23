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
        // 상속 관계가 싱글테이블 전략일 때 하위 클래스를 조회하는 법
        return entityManager.createQuery("select sum(p.amount) from SavingPoint as p left join fetch p.accountGoal as ag where ag.id = :accountGoalId", Point.class)
                .setParameter("accountGoalId", accountGoalId)
                .getResultList();
    }
}
