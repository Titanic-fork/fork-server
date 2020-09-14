package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckNameAndPasswordWantDto {

    private String name;
    private String email;

    @Builder
    public CheckNameAndPasswordWantDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static CheckNameAndPasswordWantDto of(String name, String email) {
        return CheckNameAndPasswordWantDto.builder()
                .name(name)
                .email(email)
                .build();
    }
}
