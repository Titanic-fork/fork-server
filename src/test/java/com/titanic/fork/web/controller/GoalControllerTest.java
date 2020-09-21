package com.titanic.fork.web.controller;

import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "30000")
@Rollback(false)
public class GoalControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String requestMapping = "/goal";

    private static Stream<Arguments> setUpGoal() {
        return Stream.of(
                Arguments.of(CreateGoalRequest.builder()
                .title("goalTitle")
                .address("서울시 백제고분로45길 22-15")
                .latitude(37.51064)
                .longitude(127.11324)
                .targetTime(LocalTime.of(15,30))
                .targetDayOfWeeks("일")
                .alarmTime(LocalTime.of(13,30))
                .content("goalContent")
                .build())
        );
    }

    @ParameterizedTest
    @MethodSource("setUpGoal")
    void 목표추가API를_테스트한다(CreateGoalRequest createGoalRequest) {
        String localRequestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping;

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.post()
                .uri(localRequestUrl)
                .body(Mono.just(createGoalRequest), CreateGoalRequest.class)
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_EXAMPLE.getValue())
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.CREATED);
    }
}
