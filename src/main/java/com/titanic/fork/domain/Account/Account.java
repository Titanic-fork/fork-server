package com.titanic.fork.domain.Account;

import lombok.Getter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue
    @Column(name = "Account_id")
    private Long id;

    private String password;
    private String email;
    private String name;
    private String phoneNumber;
    @OneToMany(mappedBy = "account")
    private List<AccountGoal> accountGoals = new ArrayList<>();
}
