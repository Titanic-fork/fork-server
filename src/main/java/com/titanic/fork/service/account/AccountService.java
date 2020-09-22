package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.NoSuchAccountException;
import com.titanic.fork.exception.account.LoginAuthenticationFail;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.web.controller.LoginRequest;
import com.titanic.fork.web.dto.request.account.NewPasswordRequest;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public void changePhoneNumber(NewPhoneNumberRequest newPhoneNumberRequest) {
        Account foundAccount = accountRepository.findByEmail(newPhoneNumberRequest.getEmail());
        foundAccount.changePhoneNumber(newPhoneNumberRequest.getPhoneNumber());
    }

    public void validateNameAndPassword(ValidateNameAndPasswordRequest validateNameAndPasswordRequest) {
        Account foundAccount = accountRepository.findByEmail(validateNameAndPasswordRequest.getEmail());
        if (!foundAccount.isEqualName(validateNameAndPasswordRequest.getName())) {
            throw new NoSuchAccountException();
        }
    }

    @Transactional(readOnly = true)
    public void changePassword(NewPasswordRequest newPasswordRequest) {
        Account foundAccount = accountRepository.findByEmail(newPasswordRequest.getEmail());
        foundAccount.changePassword(newPasswordRequest.getNewPassword());
    }

    @Transactional(readOnly = true)
    public void login(LoginRequest loginRequest) {
        Account foundAccount = accountRepository.findByEmail(loginRequest.getEmail());
        if (loginFail(loginRequest, foundAccount)) {
            throw new LoginAuthenticationFail();
        }
    }

    private boolean loginFail(LoginRequest loginRequest, Account foundAccount) {
        return !passwordEncoder.matches(loginRequest.getPassword(), foundAccount.getPassword());
    }
}
