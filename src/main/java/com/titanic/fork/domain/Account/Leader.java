package com.titanic.fork.domain.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Leader extends Account {

    @Builder
    public Leader(String password, String email, String name, String phoneNumber) {
        super(password, email, name, phoneNumber);
    }
}
