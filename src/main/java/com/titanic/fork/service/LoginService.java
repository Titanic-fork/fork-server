package com.titanic.fork.service;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.NoSuchAccountException;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final AccountRepository accountRepository;

    public ResponseEntity<Void> validateNameAndPassword(ValidateNameAndPasswordRequest validateNameAndPasswordRequest) {

        Account foundAccount = accountRepository.findByEmail(validateNameAndPasswordRequest.getEmail());
        if (!foundAccount.isEqualName(validateNameAndPasswordRequest.getName())) {
            throw new NoSuchAccountException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
