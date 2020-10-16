package com.titanic.fork.web.controller.account;

import com.titanic.fork.utils.LocalEnum;
import com.titanic.fork.utils.LocalTestEnum;
import com.titanic.fork.web.dto.request.account.*;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AccountControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String REQUEST_MAPPING = "/account";
    private static final String token = LocalTestEnum.JWT_TOKEN_LOCAL_TEST_1.token;
    private Logger log = LoggerFactory.getLogger(AccountControllerIntegrationTest.class);

    @DisplayName("회원가입API를_테스트한다")
    @ParameterizedTest
    @CsvSource({"localTest1@gmail.com,password,hyunjun,010-7720-7957"})
    void register(String email, String password, String name, String phoneNumber) {

        // given
        String localRequestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING;
        RegisterRequest registerRequest = RegisterRequest.of(email,password,name,phoneNumber);

        // when
        EntityExchangeResult<ResponseEntity> registerResponse = webTestClient.post()
                .uri(localRequestUrl)
                .body(Mono.just(registerRequest), RegisterRequest.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        /* then
         * 회원가입 성공 후 Header.Authorization에 Jwt토큰이 담기는 지 테스트
         * 실패 시 HttpStatus = 500(BAD_REQUEST)
         */
        assertThat(registerResponse.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @DisplayName("로그인API테스트")
    @ParameterizedTest
    @CsvSource({"localTest1@gmail.com,password"})
    void login(String email, String password) {

        // given
        String requestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING + "/login";
        LoginRequest loginRequest = LoginRequest.of(email, password);

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.post()
                .uri(requestUrl)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        /* then
         * 로그인 성공 후 Header.Authorization에 Jwt토큰이 담기는 지 테스트
         * 실패 시 HttpStatus = 500(BAD_REQUEST)
         */
        log.info("token = {} ", responseEntity.getResponseHeaders().get(LoginEnum.AUTHORIZATION.getValue()));
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("중복된 이메일인지 확인하는 API 테스트")
    @ParameterizedTest
    @CsvSource({"localTest1@gmail.com"})
    void validateDuplicatedEmail(String email) {

        // given
        String localRequestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING + "/email/" + email;

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.get()
                .uri(localRequestUrl)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();
        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.ACCEPTED);
    }

    @DisplayName("로그인된 상태에서 핸드폰 번호를 수정하는 API 테스트")
    @ParameterizedTest
    @CsvSource({"010-1234-5678"})
    void changePhoneNumber(String phoneNumber) {

        // given
        String requestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING + "/phone-number";
        NewPhoneNumberRequest newPhoneNumberRequest = NewPhoneNumberRequest.from(phoneNumber);

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.put()
                .uri(requestUrl)
                .body(Mono.just(newPhoneNumberRequest), NewPhoneNumberRequest.class)
                .header(LoginEnum.AUTHORIZATION.getValue(), token)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({"hyunjun,localTest1@gmail.com"})
    void validateNameAndPassword(String name, String email) {

        // given
        String requestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING + "/authentication";
        ValidateNameAndPasswordRequest validateNameAndPasswordRequest = ValidateNameAndPasswordRequest.of(name, email);

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.put()
                .uri(requestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), token)
                .body(Mono.just(validateNameAndPasswordRequest), ValidateNameAndPasswordRequest.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        /* then
         * 이름과 이메일이 일치하면 OK(200) / 아니면 401(UnAuthorized)
         */
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.OK);
        log.info("token : {}", responseEntity.getResponseHeaders().get(LoginEnum.AUTHORIZATION.getValue()));
    }

    @ParameterizedTest
    @CsvSource({"guswns1653@gmail.com,newPassword"})
    void changePassword(String email, String newPassword) {

        // given
        String requestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING +"/password";
        NewPasswordRequest newPasswordRequest = NewPasswordRequest.of(email, newPassword);

        // when
        EntityExchangeResult<ResponseEntity> newPasswordRequestEntityExchangeResult = webTestClient.put()
                .uri(requestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), token)
                .body(Mono.just(newPasswordRequest), NewPasswordRequest.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(newPasswordRequestEntityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
    }
}
