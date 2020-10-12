package com.titanic.fork.service;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.exception.AlreadyExistedException;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.web.dto.request.account.LoginRequest;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.dto.request.account.RegisterRequest;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName(value = "AccountService 테스트")
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RegisterService registerService;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @DisplayName("전화번호변경이 성공했는지 확인하는 테스트")
    @ParameterizedTest
    @CsvSource({"010-5678-5678"})
    void changePassword(String phoneNumber) {

        // given
        NewPhoneNumberRequest newPhoneNumberRequest = NewPhoneNumberRequest.from(phoneNumber);

        // when
        when(request.getAttribute(LoginEnum.USER_EMAIL.getValue())).thenReturn("localTest1@gmail.com");
        accountService.changePhoneNumber(newPhoneNumberRequest, request);
        Account foundAccount = accountService.findByEmail(request);

        // then
        assertThat(foundAccount.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @DisplayName("중복된 이메일이면 예외가 출력되는 테스트")
    @ParameterizedTest
    @CsvSource({"localTest1@gmail.com"})
    void validateDuplicatedEmail(String email) {
        // given, when, then
        assertThatThrownBy(() ->
                registerService.validateDuplicatedEmail(email)).isInstanceOf(AlreadyExistedException.class);
    }

    @DisplayName("중복이 아닌 이메일이면 통과하는 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1700@gmail.com"})
    void validateDuplicatedEmail2(String email) {
        // given, when, then
        registerService.validateDuplicatedEmail(email);
    }
}
