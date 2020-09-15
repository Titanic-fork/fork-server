package com.titanic.fork.domain.Account;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {

    @Id
    @GeneratedValue
    @Column(name = "Account_id")
    protected Long id;

    protected String password;

    @Column(unique = true)
    protected String email;
    protected String name;
    protected String phoneNumber;

    @OneToMany(mappedBy = "account")
    protected final List<AccountGoal> accountGoals = new ArrayList<>();

    public Account(String password, String email, String name, String phoneNumber) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public boolean isEqualName(String name) {
        return this.name.equals(name);
    }

    public abstract void changePassword(String newPassword);
}
