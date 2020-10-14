package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.Member;
import com.titanic.fork.exception.AlreadyExistedException;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.utils.JwtProvider;
import com.titanic.fork.web.dto.request.account.RegisterRequest;
import lombok.RequiredArgsConstructor;
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
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest, HttpServletResponse response) {
        // 비밀번호 encode 로직 //
        String encodePassword = passwordEncoder.encode(registerRequest.getPassword());
        Member account = Member.of(registerRequest, encodePassword);
        accountRepository.save(account);
//        jwtProvider.loadJwtToHeader(response, registerRequest);
    }

    public void validateDuplicatedEmail(String email) {
        List<Account> foundAccounts = accountRepository.findDuplicatedEmail(email);
        if (!foundAccounts.isEmpty()) {
            throw new AlreadyExistedException("중복되는 이메일입니다.");
        }
    }
}
