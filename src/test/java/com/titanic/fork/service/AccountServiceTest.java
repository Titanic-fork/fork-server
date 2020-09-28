package com.titanic.fork.service;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.AlreadyExistedException;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.web.dto.request.account.LoginRequest;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;

@DisplayName(value = "AccountService 테스트")
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RegisterService registerService;

    @Mock
    private HttpServletResponse response;

    @DisplayName("전화번호변경이 성공했는지 확인하는 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,010-5678-5678"})
    void changePhone를_테스트한다(String email, String phoneNumber) {

        // given
        NewPhoneNumberRequest newPhoneNumberRequest = NewPhoneNumberRequest.of(email, phoneNumber);

        // when
        accountService.changePhoneNumber(newPhoneNumberRequest);
        Account foundAccount = accountRepository.findByEmail(newPhoneNumberRequest.getEmail());

        // then
        assertThat(foundAccount.getEmail()).isEqualTo(email);
    }

    @DisplayName("로그인 성공하면 JWT 토큰이 담기는지 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1654@gmail.com,password"})
    void login성공시_JWT토큰_테스트한다(String email, String password) {
        // given
        LoginRequest loginRequest = LoginRequest.of(email, password);

        // when
        accountService.login(loginRequest, response);

        // then (결과가 void라 메서드 내 log로 확인하기)
    }

    @DisplayName("중복된 이메일이면 예외가 출력되는 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1653@gmail.com"})
    void 중복_이메일_예외(String email) {
        // given, when, then
        assertThatThrownBy(() ->
                registerService.validateDuplicatedEmail(email)).isInstanceOf(AlreadyExistedException.class);
    }

    @DisplayName("중복된 이메일이면 예외가 출력되는 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1700@gmail.com"})
    void 중복_이메일_통과(String email) {
        // given, when, then
        assertThatThrownBy(() ->
                registerService.validateDuplicatedEmail(email)).isInstanceOf(AlreadyExistedException.class);
    }
}
