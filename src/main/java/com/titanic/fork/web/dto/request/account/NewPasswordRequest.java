package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewPasswordRequest {

    private String email;
    private String newPassword;

    @Builder
    public NewPasswordRequest(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }

    public static NewPasswordRequest from(String email, String newPassword) {
        return NewPasswordRequest.builder()
                .email(email)
                .newPassword(newPassword)
                .build();
    }
}
