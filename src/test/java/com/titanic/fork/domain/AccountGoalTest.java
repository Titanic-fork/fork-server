package com.titanic.fork.domain;

import com.titanic.fork.domain.Account.AccountGoal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountGoalTest {

    @Spy
    private AccountGoal accountGoal;

    @Test
    void start() {
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        accountGoal.start();

        // then
        assertThat(accountGoal.getStartTime()).isAfter(now);
    }

    @Test
    void calculateElapsedTime() {
        // given
        LocalDateTime start = LocalDateTime.now();

        // when
        accountGoal.calculateElapsedTime();
    }
}
