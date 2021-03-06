package com.titanic.fork.service;

import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.service.goal.GoalService;
import com.titanic.fork.web.dto.response.goal.ElapsedTimeResponse;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GoalServiceTest {

    @Autowired
    private GoalService goalService;

    @Autowired
    private AccountGoalRepository accountGoalRepository;

    @Mock
    private HttpServletRequest request;
    private Logger log = LoggerFactory.getLogger(GoalServiceTest.class);

    @DisplayName("시작버튼 누르면 해당 AccountGoal에 startTime 필드에 값추가 테스트")
    @ParameterizedTest
    @CsvSource({"1,1"})
    void start(int goalId, int accountId) {

        // when
        when(request.getAttribute(LoginEnum.USER_EMAIL.value)).thenReturn("localTest1@gmail.com");
        goalService.start((long) goalId, request);
        AccountGoal foundAccountGoal = accountGoalRepository.findByAccountIdAndGoalId((long) accountId, (long) goalId);

        // then
        log.info("startTime {}", foundAccountGoal.getStartTime());
        assertThat(foundAccountGoal.getStartTime()).isBefore(LocalDateTime.now());
    }

    @DisplayName("종료버튼 누르면 소요시간을 리턴하는 테스트")
    @ParameterizedTest
    @CsvSource({"1"})
    void end(int goalId) {

        // when
        when(request.getAttribute(LoginEnum.USER_EMAIL.value)).thenReturn("localTest1@gmail.com");
        ElapsedTimeResponse elapsedTimeResponse = goalService.end((long) goalId, request);

        // then
        log.info("elapsedTimeResponse {}", elapsedTimeResponse);
        assertThat(elapsedTimeResponse.getElapsedHour()).isLessThan(23);
        assertThat(elapsedTimeResponse.getElapsedMinute()).isLessThan(59);
    }

    @DisplayName("사용자와 목표의 거리를 계산하는 테스트")
    @ParameterizedTest
    @CsvSource({"1,37.51056,127.11285"})
    void calculateDistance(int goalId, double latitude, double longitude) {
        // when
        when(request.getAttribute(LoginEnum.USER_EMAIL.value)).thenReturn("localTest1@gmail.com");
        boolean result = goalService.calculateDistance((long) goalId, latitude, longitude, request);

        // then
        assertThat(result).isTrue();
    }
}
