package com.titanic.fork.web.controller.goal;

import com.titanic.fork.utils.DeployTestEnum;
import com.titanic.fork.utils.LocalEnum;
import com.titanic.fork.utils.LocalTestEnum;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "30000")
public class GoalControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String requestMapping = "/goal";
    private static final String token = LocalTestEnum.JWT_TOKEN_LOCAL_TEST_1.token;

    private static Stream<Arguments> setUpGoal() {
        return Stream.of(
                Arguments.of(CreateGoalRequest.builder()
                        .title("goalTitle")
                        .address("서울시 백제고분로45길 22-15")
                        .latitude(37.51064)
                        .longitude(127.11324)
                        .targetTimeHour(15)
                        .targetTimeMinute(30)
                        .alarmTimeHour(15)
                        .alarmTimeMinute(30)
                        .targetDayOfWeeks("일")
                        .content("goalContent")
                        .build())
        );
    }

    @DisplayName("로컬에_목표추가API를_테스트한다")
    @ParameterizedTest
    @MethodSource("setUpGoal")
    void createAtLocal(CreateGoalRequest createGoalRequest) {
        String localRequestUrl = LocalEnum.LOCALHOST.getValue() + port + requestMapping;

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.post()
                .uri(localRequestUrl)
                .body(Mono.just(createGoalRequest), CreateGoalRequest.class)
                .header(LoginEnum.AUTHORIZATION.getValue(), token)
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.CREATED);
    }

    @DisplayName("배포서버에_목표추가API를_테스트한다")
    @ParameterizedTest
    @MethodSource("setUpGoal")
    void createAtService(CreateGoalRequest createGoalRequest) {
        String serviceRequestUrl = DeployTestEnum.SERVICE_URL.getValue() + requestMapping;

        // when
        EntityExchangeResult<ResponseEntity> responseEntity = webTestClient.post()
                .uri(serviceRequestUrl)
                .body(Mono.just(createGoalRequest), CreateGoalRequest.class)
                .header(LoginEnum.AUTHORIZATION.getValue(), DeployTestEnum.JWT_TOKEN_GUSWNS1653.getValue())
                .exchange()
                .expectBody(ResponseEntity.class)
                .returnResult();

        // then
        assertThat(responseEntity.getStatus()).isEqualTo(HttpStatus.CREATED);
    }

    @DisplayName("메인화면 일일, 주간 목표달성률API")
    @ParameterizedTest
    @CsvSource({"1,6.0,6.0"})
    void getAchievement(int goalId, float today, float weekly) {

        // given
        String localRequestUrl = LocalEnum.LOCALHOST.getValue() + port + requestMapping + "/" + goalId +
                "/achievement?today-elapsedtime=60&weekly-elapsedtime=420";

        // when
        AchievementResponse achievementResponse = webTestClient.get()
                .uri(localRequestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), token)
                .exchange()
                .expectBody(AchievementResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(achievementResponse.getTodayAchievement()).isEqualTo(today);
        assertThat(achievementResponse.getWeeklyAchievement()).isEqualTo(weekly);
    }
}
