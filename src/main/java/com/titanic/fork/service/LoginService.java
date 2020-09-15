package com.titanic.fork.service;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.NoSuchAccountException;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final AccountRepository accountRepository;
    private final static int ZERO = 0;

    public ResponseEntity<Void> validateNameAndPassword(ValidateNameAndPasswordDto validateNameAndPasswordDto) {

        List<Account> foundAccounts = accountRepository.findByEmail(validateNameAndPasswordDto.getEmail());

        if (foundAccounts.isEmpty()) {
            throw new NoSuchAccountException();
        }

        Account foundAccount = foundAccounts.get(ZERO);

        if (!foundAccount.isEqualName(validateNameAndPasswordDto.getName())) {
            throw new NoSuchAccountException();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
