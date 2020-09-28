package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NewPhoneNumberRequest {

    private String phoneNumber;

    @Builder
    public NewPhoneNumberRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static NewPhoneNumberRequest from(String phoneNumber) {
        return NewPhoneNumberRequest.builder()
                .phoneNumber(phoneNumber)
                .build();
    }
}
