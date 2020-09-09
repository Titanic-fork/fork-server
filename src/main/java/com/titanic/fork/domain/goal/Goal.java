package com.titanic.fork.domain.goal;

import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.value.Location;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Goal {

    @Id
    @GeneratedValue
    @Column(name = "goal_id")
    private Long id;
    private String title;

    @Embedded
    private Location location;

    @OneToMany(mappedBy = "goal")
    private List<AccountGoal> accountGoals = new ArrayList<>();
}
