package com.titanic.fork.web.controller.account;

import com.titanic.fork.utils.LocalEnum;
import com.titanic.fork.utils.LocalTestEnum;
import com.titanic.fork.web.dto.request.account.*;
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
public class AccountControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String REQUEST_MAPPING = "/account";

    @DisplayName("회원가입API를_테스트한다")
    @ParameterizedTest
    @CsvSource({"localTest2@gmail.com,password,hyunjun,010-7720-7957"})
    void register(String email, String password, String name, String phoneNumber) {

        // given
        String localRequestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING;
//        String apiRequestUrl = TestEnum.SERVICE_URL.getValue() + requestMapping;
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
        System.out.println("token = " + registerResponse.getResponseHeaders().get(LoginEnum.AUTHORIZATION.getValue()));
        assertThat(registerResponse.getStatus()).isEqualTo(HttpStatus.CREATED);
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

        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.FORBIDDEN);
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
                .header(LoginEnum.AUTHORIZATION.getValue(), LocalTestEnum.JWT_TOKEN_LOCAL_TEST_3.getValue())
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @ParameterizedTest
    @CsvSource({"hyunjun,guswns1653@gmail.com"})
    void validateNameAndPassword(String name, String email) {

        // given
        String requestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING + "/authentication";
        ValidateNameAndPasswordRequest validateNameAndPasswordRequest = ValidateNameAndPasswordRequest.of(name, email);

        // when
        EntityExchangeResult<ResponseEntity> responseEntityEntityExchangeResult = webTestClient.put()
                .uri(requestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), LocalEnum.JWT_TOKEN_GUSWNS1653.getValue())
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
    @CsvSource({"guswns1653@gmail.com,newPassword"})
    void changePassword(String email, String newPassword) {

        // given
        String requestUrl = LocalEnum.LOCALHOST.getValue() + port + REQUEST_MAPPING +"/password";
        NewPasswordRequest newPasswordRequest = NewPasswordRequest.of(email, newPassword);

        // when
        EntityExchangeResult<ResponseEntity> newPasswordRequestEntityExchangeResult = webTestClient.put()
                .uri(requestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), LocalEnum.JWT_TOKEN_GUSWNS1653.getValue())
                .body(Mono.just(newPasswordRequest), NewPasswordRequest.class)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(newPasswordRequestEntityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
    }
}
