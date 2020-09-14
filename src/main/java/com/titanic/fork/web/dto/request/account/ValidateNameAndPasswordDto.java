package com.titanic.fork.web.dto.request.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateNameAndPasswordDto {

    private String name;
    private String email;

    @Builder
    public ValidateNameAndPasswordDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static ValidateNameAndPasswordDto of(String name, String email) {
        return ValidateNameAndPasswordDto.builder()
                .name(name)
                .email(email)
                .build();
    }
}
