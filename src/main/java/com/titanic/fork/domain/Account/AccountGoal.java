package com.titanic.fork.domain.Account;

import com.titanic.fork.domain.goal.Alarm;
import com.titanic.fork.domain.goal.Goal;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ALARM_ID")
    private Alarm alarm;

    private LocalTime targetTime;

    @Builder
    public AccountGoal(Account account, Goal goal, Alarm alarm, LocalTime targetTime) {
        this.account = account;
        this.goal = goal;
        this.alarm = alarm;
        this.targetTime = targetTime;
    }

    public static AccountGoal of(Alarm alarm, CreateGoalRequest createGoalRequest) {
        return AccountGoal.builder()
                .alarm(alarm)
                .targetTime(createGoalRequest.getTargetTime())
                .build();
    }

    public void addAccount(Account account) {
        this.account = account;
    }

    public void addGoal(Goal goal) {
        this.goal = goal;
    }
}
