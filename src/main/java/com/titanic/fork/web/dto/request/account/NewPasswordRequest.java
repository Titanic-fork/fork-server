package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewPasswordRequest {

    private String newPassword;

    @Builder
    public NewPasswordRequest(String newPassword) {
        this.newPassword = newPassword;
    }

    public static NewPasswordRequest from(String newPassword) {
        return NewPasswordRequest.builder()
                .newPassword(newPassword)
                .build();
    }
}
