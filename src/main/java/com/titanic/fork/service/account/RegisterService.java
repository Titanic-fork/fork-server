package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.Member;
import com.titanic.fork.exception.AlreadyExistedException;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.service.JwtService;
import com.titanic.fork.web.dto.request.account.RegisterRequestDto;
import com.titanic.fork.web.login.LoginEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterService {

    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequestDto registerRequestDto, HttpServletResponse response) {
        validateDuplicateEmail(registerRequestDto.getEmail());
//        String rawPassword = registerRequestDto.getPassword();

        // 비밀번호 encode
        String encodePassword = passwordEncoder.encode(registerRequestDto.getPassword());
        registerRequestDto.setPassword(encodePassword);

//        // 비밀번호 decode 가능 여부 파악 -> 로그인 로직에 추가하기
//        boolean result = passwordEncoder.matches(rawPassword, encodePassword);
//        System.out.println("result" + result);

        Member account = Member.from(registerRequestDto);
        accountRepository.save(account);
        String jwtTokenWithEmail = jwtService.createJwtTokenWithEmail(account.getEmail());
        response.setHeader(LoginEnum.AUTHORIZATION.getValue(), jwtTokenWithEmail);
    }

    private void validateDuplicateEmail(String email) {
        List<Account> foundAccounts = accountRepository.findDuplicatedEmail(email);
        if (!foundAccounts.isEmpty()) {
            throw new AlreadyExistedException();
        }
    }
}
