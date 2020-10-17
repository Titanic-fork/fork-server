package com.titanic.fork.service;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.repository.account.AccountRepository;
import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import com.titanic.fork.repository.goal.GoalRepository;
import com.titanic.fork.service.goal.GoalService;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
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
}
