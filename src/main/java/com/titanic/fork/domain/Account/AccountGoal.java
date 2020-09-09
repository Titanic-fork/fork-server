package com.titanic.fork.domain.Account;

import com.titanic.fork.domain.goal.Alarm;
import com.titanic.fork.domain.goal.Goal;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
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

    @OneToOne(fetch = FetchType.LAZY)
    private Alarm alarm;

    private LocalTime targetTime;
}
