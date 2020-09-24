package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.NoSuchAccountException;
import com.titanic.fork.exception.account.LoginAuthenticationFail;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.web.dto.request.account.LoginRequest;
import com.titanic.fork.web.dto.request.account.NewPasswordRequest;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordRequest;
import com.titanic.fork.web.login.LoginEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

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

    public void changePassword(NewPasswordRequest newPasswordRequest) {
        Account foundAccount = accountRepository.findByEmail(newPasswordRequest.getEmail());
        foundAccount.changePassword(newPasswordRequest.getNewPassword());
    }

    public void login(LoginRequest loginRequest) {
        Account foundAccount = accountRepository.findByEmail(loginRequest.getEmail());
        if (loginFail(loginRequest, foundAccount)) {
            throw new LoginAuthenticationFail();
        }
    }

    private boolean loginFail(LoginRequest loginRequest, Account foundAccount) {
        return !passwordEncoder.matches(loginRequest.getPassword(), foundAccount.getPassword());
    }

    public Account findByEmail(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute(LoginEnum.USER_EMAIL.getValue());
        return accountRepository.findByEmail(userEmail);
    }

}
