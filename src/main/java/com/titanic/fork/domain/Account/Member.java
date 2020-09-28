package com.titanic.fork.domain.Account;

import com.titanic.fork.web.dto.request.account.RegisterRequest;
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



    public static Member of(RegisterRequest registerRequest, String encodedPassword) {
        return Member.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .password(encodedPassword)
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }
}
