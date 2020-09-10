package com.titanic.fork.domain.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    public Account(String password, String email, String name, String phoneNumber) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
