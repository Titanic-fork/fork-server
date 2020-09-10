package com.titanic.fork.domain.point;

import com.titanic.fork.domain.Account.AccountGoal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Point {

    @Id
    @GeneratedValue
    @Column(name = "POINT_ID")
    private Long id;

    private int amount;
    private LocalDateTime createdDate;

    @Column(name = "POINT_CONTENT", columnDefinition = "VARCHAR(500)")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_GOAL_ID")
    private AccountGoal accountGoal;
}
