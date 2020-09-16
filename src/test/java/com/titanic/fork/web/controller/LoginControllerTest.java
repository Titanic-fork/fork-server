package com.titanic.fork.web.controller;

import com.titanic.fork.web.dto.request.account.NewPasswordRequest;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordRequest;
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
public class LoginControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String requestMapping = "/account";
    private final static String LOCALHOST = "http://localhost:";

    @ParameterizedTest
    @CsvSource({"hyunjun,guswns1651@gmail.com"})
    void 비밀번호변경을_위한_인증API을_테스트한다(String name, String email) {

        // given
        String requestUrl = LOCALHOST + port + requestMapping + "/find";
        ValidateNameAndPasswordRequest validateNameAndPasswordRequest = ValidateNameAndPasswordRequest.of(name, email);

        // when
        EntityExchangeResult<ResponseEntity> responseEntityEntityExchangeResult = webTestClient.post()
                .uri(requestUrl)
                .body(Mono.just(validateNameAndPasswordRequest), ValidateNameAndPasswordRequest.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        /* then
         * 이름과 이메일이 일치하면 OK(200) / 아니면 401(UnAuthorized)
         */
        assertThat(responseEntityEntityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,newPassword"})
    void 비밀번호변경API를_테스트한다(String email, String newPassword) {

        // given
        String requestUrl = LOCALHOST + port + requestMapping +"/find";
        NewPasswordRequest newPasswordRequest = NewPasswordRequest.of(email, newPassword);

        // when
        EntityExchangeResult<ResponseEntity> newPasswordRequestEntityExchangeResult = webTestClient.put()
                .uri(requestUrl)
                .body(Mono.just(newPasswordRequest), NewPasswordRequest.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(newPasswordRequestEntityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
    }
}
