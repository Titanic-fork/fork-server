package com.titanic.fork.web.controller;

import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
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
public class AccountControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String requestMapping = "/account";
    private final static String LOCALHOST = "http://localhost:";

    @DisplayName("로그인된 상태에서 핸드폰 번호를 수정하는 API 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,010-1234-5678"})
    void 핸드폰번호_수정API를_테스트한다(String email, String phoneNumber) {

        // given
        String requestUrl = LOCALHOST + port + requestMapping + "/phoneNumber";
        NewPhoneNumberRequest newPhoneNumberRequest = NewPhoneNumberRequest.of(email, phoneNumber);

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.put()
                .uri(requestUrl)
                .body(Mono.just(newPhoneNumberRequest), NewPhoneNumberRequest.class)
                .header(LoginEnum.AUTHORIZATION.getValue(), LoginEnum.JWT_TOKEN_EXAMPLE.getValue())
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.OK);
    }
}
