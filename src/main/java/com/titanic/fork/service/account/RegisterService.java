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

    public ResponseEntity<Void> register(RegisterRequestDto registerRequestDto, HttpServletResponse response) {
        validateDuplicateEmail(registerRequestDto.getEmail());
        Member account = Member.from(registerRequestDto);
        accountRepository.save(account);
        String jwtTokenWithEmail = jwtService.createJwtTokenWithEmail(account.getEmail());
        response.setHeader(LoginEnum.AUTHORIZATION.getValue(), jwtTokenWithEmail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void validateDuplicateEmail(String email) {
        List<Account> foundAccounts = accountRepository.findDuplicatedEmail(email);
        if (!foundAccounts.isEmpty()) {
            throw new AlreadyExistedException();
        }
    }
}
