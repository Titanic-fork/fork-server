package com.titanic.fork.service;

import com.titanic.fork.domain.Account.Member;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.web.dto.request.account.RegisterWantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterService {

    private final AccountRepository accountRepository;

    public ResponseEntity<Void> register(RegisterWantDto registerWantDto) {
        Member account = Member.from(registerWantDto);
        accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
