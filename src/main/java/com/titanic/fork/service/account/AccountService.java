package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.NoSuchAccountException;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.web.dto.request.account.NewPasswordRequest;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Void> changePhoneNumber(NewPhoneNumberRequest newPhoneNumberRequest) {
        Account foundAccount = accountRepository.findByEmail(newPhoneNumberRequest.getEmail());
        foundAccount.changePhoneNumber(newPhoneNumberRequest.getPhoneNumber());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Void> validateNameAndPassword(ValidateNameAndPasswordRequest validateNameAndPasswordRequest) {
        Account foundAccount = accountRepository.findByEmail(validateNameAndPasswordRequest.getEmail());
        if (!foundAccount.isEqualName(validateNameAndPasswordRequest.getName())) {
            throw new NoSuchAccountException();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Void> changePassword(NewPasswordRequest newPasswordRequest) {
        Account foundAccount = accountRepository.findByEmail(newPasswordRequest.getEmail());
        foundAccount.changePassword(newPasswordRequest.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
