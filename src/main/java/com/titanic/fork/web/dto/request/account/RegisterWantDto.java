package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterWantDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    @Builder
    public RegisterWantDto(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static RegisterWantDto of(String email, String password, String name, String phoneNumber) {
        return RegisterWantDto.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
