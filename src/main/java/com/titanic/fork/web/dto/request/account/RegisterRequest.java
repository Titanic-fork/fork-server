package com.titanic.fork.web.dto.request.account;

import com.titanic.fork.web.dto.request.AccountRequestDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class RegisterRequest extends AccountRequestDto {

    private String name;
    private String phoneNumber;

    public RegisterRequest(String email, String password, String name, String phoneNumber) {
        super(email, password);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static RegisterRequest of(String email, String password, String name, String phoneNumber) {
        return RegisterRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
