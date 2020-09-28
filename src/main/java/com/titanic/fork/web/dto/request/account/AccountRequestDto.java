package com.titanic.fork.web.dto.request.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class AccountRequestDto {

    private String email;
    private String password;

    public AccountRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
