package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public ResponseEntity<Void> changePhoneNumber(NewPhoneNumberRequest newPhoneNumberRequest) {
        Account foundAccount = accountRepository.findByEmail(newPhoneNumberRequest.getEmail());
        foundAccount.changePhoneNumber(newPhoneNumberRequest.getPhoneNumber());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
