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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "points")
public abstract class Point {

    @Id
    @GeneratedValue
    @Column(name = "POINT_ID")
    protected Long id;

    protected int amount;
    protected LocalDateTime createdDate;

    @Column(name = "POINT_CONTENT", columnDefinition = "VARCHAR(500)")
    protected String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_GOAL_ID")
    protected AccountGoal accountGoal;

    public boolean isPeriod(int year, int month) {
        int first = 1;
        int zero = 0;
        int nextMonth = month + 1;
        return createdDate.isAfter(LocalDateTime.of(year,month,first,zero,zero))
                && createdDate.isBefore(LocalDateTime.of(year,nextMonth,first,zero,zero));
    }
}
