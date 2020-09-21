package com.titanic.fork.web.dto.request.account;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterRequestDto {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    @Builder
    public RegisterRequestDto(String email, String password, String name, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static RegisterRequestDto of(String email, String password, String name, String phoneNumber) {
        return RegisterRequestDto.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
