package com.titanic.fork.domain.Account;

import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
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
    @Column(name = "ACCOUNT_ID")
    protected Long id;

    protected String password;

    @Column(unique = true)
    protected String email;
    protected String name;
    protected String phoneNumber;

    /* CascadeType.ALL 사용 이유
     * Account가 저장, 삭제, 변경될 때 AccountGoal도 저장, 삭제, 변경되기 때문이다.
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
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

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changePhoneNumber(NewPhoneNumberRequest newPhoneNumberRequest) {
        this.phoneNumber = newPhoneNumberRequest.getPhoneNumber();
    }

    public AccountGoal addAccountGoal(AccountGoal accountGoal) {
        this.accountGoals.add(accountGoal);
        accountGoal.addAccount(this);
        return accountGoal;
    }
}
