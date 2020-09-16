package com.titanic.fork.domain.Account;

import com.titanic.fork.web.dto.request.account.RegisterWantDto;
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

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public static Member from(RegisterWantDto registerWantDto) {
        return Member.builder()
                .email(registerWantDto.getEmail())
                .name(registerWantDto.getName())
                .password(registerWantDto.getPassword())
                .phoneNumber(registerWantDto.getPhoneNumber())
                .build();
    }
}
