package com.titanic.fork.service;

import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.web.dto.request.account.RegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RegisterService registerService;

    @Mock
    private HttpServletResponse response;

    @DisplayName("register메서드를_테스트한다")
    @ParameterizedTest
    @CsvSource({"guswns1659,password,hyunjun,010-7720-7957"})
    void register(String email, String password, String name, String phoneNumber) {
        // given
        RegisterRequest registerRequest = RegisterRequest.of(email,password,name,phoneNumber);

        // when
        registerService.register(registerRequest, response);

        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
