package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateNameAndPasswordRequest {

    private String name;
    private String email;

    @Builder
    public ValidateNameAndPasswordRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static ValidateNameAndPasswordRequest of(String name, String email) {
        return ValidateNameAndPasswordRequest.builder()
                .name(name)
                .email(email)
                .build();
    }
}
