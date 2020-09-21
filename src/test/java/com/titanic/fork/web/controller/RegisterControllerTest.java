package com.titanic.fork.web.controller;

import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.request.account.RegisterRequestDto;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "30000")
@Rollback(false)
public class RegisterControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String requestMapping = "/account";

    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,password,hyunjun,010-7720-7957"})
    void 회원가입API를_테스트한다(String email, String password, String name, String phoneNumber) {

        // given
        String localRequestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping;
//        String apiRequestUrl = TestEnum.SERVICE_URL.getValue() + requestMapping;
        RegisterRequestDto registerRequestDto = RegisterRequestDto.of(email,password,name,phoneNumber);

        // when
        EntityExchangeResult<ResponseEntity> registerResponse = webTestClient.post()
                .uri(localRequestUrl)
                .body(Mono.just(registerRequestDto), RegisterRequestDto.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        /* then
         * 회원가입 성공 후 Header.Authorization에 Jwt토큰이 담기는 지 테스트
         * 실패 시 HttpStatus = 500(BAD_REQUEST)
         */
        System.out.println("token = " + registerResponse.getResponseHeaders().get(LoginEnum.AUTHORIZATION.getValue()));
        assertThat(registerResponse.getStatus()).isEqualTo(HttpStatus.CREATED);
    }
}
