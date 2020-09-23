package com.titanic.fork.web.controller.account;

import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.request.account.LoginRequest;
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
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "30000")
@Transactional
public class LoginControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AccountRepository accountRepository;

    private final static String requestMapping = "/account";

    @DisplayName("로그인API테스트")
    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,password1"})
    void 로그인_테스트한다(String email, String password) {

        // given
        String requestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping + "/login";
        LoginRequest loginRequest = LoginRequest.of(email, password);

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.post()
                .uri(requestUrl)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_GUSWNS1653.getValue())
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
//        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
