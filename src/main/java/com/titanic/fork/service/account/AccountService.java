package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.account.NoSuchAccountException;
import com.titanic.fork.exception.account.LoginAuthenticationFail;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.utils.JwtProvider;
import com.titanic.fork.web.dto.request.account.LoginRequest;
import com.titanic.fork.web.dto.request.account.NewPasswordRequest;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordRequest;
import com.titanic.fork.web.login.LoginEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    /**
     * 변경감지 방식으로 업데이트 메서드를 구현
     * 인터셉터로 넘어오는 이메일로 항상 DB에서 조회하기 때문에 자연스레 구현
     */
    public void changePhoneNumber(NewPhoneNumberRequest newPhoneNumberRequest, HttpServletRequest request) {
        Account foundAccount = findByEmail(request);
        foundAccount.changePhoneNumber(newPhoneNumberRequest);
    }

    /**
     * 이름과 이메일이 같은 경우 헤더에 JwtToken을 발행해서 비빌번호 변경 시 validation 처리를 한다.
     */
    public void validateNameAndPassword(ValidateNameAndPasswordRequest validateNameAndPasswordRequest,
                                        HttpServletResponse response) {
        Account foundAccount = accountRepository.findByEmail(validateNameAndPasswordRequest.getEmail());
        if (!foundAccount.isEqualName(validateNameAndPasswordRequest.getName())) {
            throw new NoSuchAccountException();
        }
        String jwtTokenWithEmail = jwtProvider.createJwtTokenWithEmail(foundAccount.getEmail());
        response.setHeader(LoginEnum.AUTHORIZATION.getValue(), jwtTokenWithEmail);
    }

    public void changePassword(NewPasswordRequest newPasswordRequest, HttpServletRequest request) {
//        Account foundAccount = accountRepository.findByEmail(newPasswordRequest.getEmail());
        Account foundAccount = findByEmail(request);
        String encodePassword = passwordEncoder.encode(newPasswordRequest.getNewPassword());
        foundAccount.changePassword(encodePassword);
    }

    public void login(LoginRequest loginRequest, HttpServletResponse response) {
        Account foundAccount = accountRepository.findByEmail(loginRequest.getEmail());
        jwtProvider.loadJwtToHeader(response, loginRequest);

        if (loginFail(loginRequest, foundAccount)) {
            throw new LoginAuthenticationFail("비밀번호가 일치하지 않습니다.");
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
