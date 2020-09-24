package com.titanic.fork.domain.Account;

import com.titanic.fork.web.dto.request.account.RegisterRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Account {

    @Builder
    public Member(String password, String email, String name, String phoneNumber) {
        super(password, email, name, phoneNumber);
    }



    public static Member from(RegisterRequestDto registerRequestDto) {
        return Member.builder()
                .email(registerRequestDto.getEmail())
                .name(registerRequestDto.getName())
                .password(registerRequestDto.getPassword())
                .phoneNumber(registerRequestDto.getPhoneNumber())
                .build();
    }
}
