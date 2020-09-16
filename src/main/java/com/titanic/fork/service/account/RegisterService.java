package com.titanic.fork.service.account;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.Member;
import com.titanic.fork.exception.AlreadyExistedException;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.service.JwtService;
import com.titanic.fork.web.dto.request.account.RegisterWantDto;
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

    public ResponseEntity<Void> register(RegisterWantDto registerWantDto, HttpServletResponse response) {
        Member account = Member.from(registerWantDto);
        validateDuplicateMember(account);
        accountRepository.save(account);
        String jwtTokenWithEmail = jwtService.createJwtTokenWithEmail(account.getEmail());
        response.setHeader(LoginEnum.AUTHORIZATION.getValue(), jwtTokenWithEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateDuplicateMember(Account account) {
        List<Account> foundAccounts = accountRepository.findDuplicatedEmail(account.getEmail());
        if (!foundAccounts.isEmpty()) {
            throw new AlreadyExistedException();
        }
    }
}
