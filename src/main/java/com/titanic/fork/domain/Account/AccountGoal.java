package com.titanic.fork.domain.Account;

import com.titanic.fork.domain.goal.Alarm;
import com.titanic.fork.domain.goal.Goal;
import com.titanic.fork.domain.point.Point;
import com.titanic.fork.domain.point.SavingPoint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@NoArgsConstructor
public class AccountGoal {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GOAL_ID")
    private Goal goal;

    /* CascadeType.ALL 사용 이유
     * AccountGoal이 Alarm을 참조하는 유일한 객체이기 때문이다.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ALARM_ID")
    private Alarm alarm;

    private LocalTime targetTime;
    private LocalDateTime startTime;
    /**
      * 소요시간은 시간만 필요하니까 int으로 설정
      */
    private int todayElapsedTime;
    private int weeklyElapsedTime;

    @Builder
    public AccountGoal(Account account, Goal goal, Alarm alarm, LocalTime targetTime) {
        this.account = account;
        this.goal = goal;
        this.alarm = alarm;
        this.targetTime = targetTime;
    }

    public static AccountGoal of() {
        return null;
    }

    public void addAccount(Account account) {
        this.account = account;
    }

    public void addGoal(Goal goal) {
        this.goal = goal;
    }

    public void start() {
        this.startTime = LocalDateTime.now();
    }

    /** 소요시간 계산 후 오늘 소요시간, 주간 소요시간 필드에 추가
     */
    public LocalTime calculateElapsedTime() {
        LocalDateTime endTime = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(startTime, endTime);
        // 시간 차이를 분 단위로 바꾼다. ex) 117이 나오면 hours * 60만큼 빼야 정확하다.
        long minutes = ChronoUnit.MINUTES.between(startTime, endTime) - (hours * 60);
        LocalTime elapsedTime = LocalTime.of((int)hours, (int)minutes);
        // 오늘, 주간 소요시간에 추가 로직
        addElapsedTime(elapsedTime);

        return elapsedTime;
    }

    public Point createSavingPoint(LocalTime elapsedTime) {
        int pointAmount = elapsedTime.getHour() * 10;
        // 관계의 주인이 관계를 설정하니 AccountGoal에서 List<Point>를 가지고 있을 필요가 없을 듯?
        return SavingPoint.of(this, pointAmount);
    }

    private void addElapsedTime(LocalTime elapsedTime) {
        todayElapsedTime += elapsedTime.getHour();
        weeklyElapsedTime += elapsedTime.getHour();
    }
}
