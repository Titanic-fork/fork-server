package com.titanic.fork.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Slf4j
@Transactional
public class Scheduler {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 매일 자정에 todayElapsedTime 0으로 초기화
     */
    @Scheduled(cron = "0 0 0 */1 * *", zone = "Asia/Seoul")
    public void resetTodayElapsedTime() {
        entityManager.createNativeQuery("update account_goal set today_elapsed_time = :value")
                .setParameter("value", 0)
                .executeUpdate();
    }

    /**
     * 매주 일요일 자정에 weeklyElapsedTime 0으로 초기화
     */
    @Scheduled(cron = "0 0 0 * * SUN", zone = "Asia/Seoul")
    public void resetWeeklyElapsedTime() {
        entityManager.createNativeQuery("update account_goal set weekly_elapsed_time = :value")
                .setParameter("value", 0)
                .executeUpdate();
    }
}
