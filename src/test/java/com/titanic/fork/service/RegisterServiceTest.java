package com.titanic.fork.service;

import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.web.dto.request.account.RegisterRequestDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Rollback(false)
@Transactional
public class RegisterServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RegisterService registerService;

    @ParameterizedTest
    @CsvSource({"guswns1659,password,hyunjun,010-7720-7957"})
    void register메서드를_테스트한다(String email, String password, String name, String phoneNumber,
                            HttpServletResponse response) {
        // given
        RegisterRequestDto registerRequestDto = RegisterRequestDto.of(email,password,name,phoneNumber);

        // when
//        ResponseEntity<Void> responseEntity = registerService.register(registerRequestDto, response);

        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
