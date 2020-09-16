package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewPhoneNumberRequest {

    private String email;
    private String phoneNumber;

    @Builder
    public NewPhoneNumberRequest(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static NewPhoneNumberRequest of(String email, String phoneNumber) {
        return NewPhoneNumberRequest.builder()
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
