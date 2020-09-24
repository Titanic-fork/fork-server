package com.titanic.fork.domain.goal;

import com.titanic.fork.domain.Account.AccountGoal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue
    @Column(name = "GOAL_ID")
    private Long id;
    private String title;

    @Embedded
    private Location location;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private final List<AccountGoal> accountGoals = new ArrayList<>();

    @Builder
    public Goal (String title, Location location) {
        this.title = title;
        this.location = location;
    }

    public void addAccountGoal(AccountGoal accountGoal) {
        this.accountGoals.add(accountGoal);
        accountGoal.addGoal(this);
    }
}
