package com.titanic.fork.service;

import com.titanic.fork.service.point.PointService;
import com.titanic.fork.web.dto.response.point.PointHistoriesResponse;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PointServiceTest {

    @Autowired
    private PointService pointService;

    @Mock
    private HttpServletRequest request;

    private Logger log = LoggerFactory.getLogger(PointServiceTest.class);

    @ParameterizedTest
    @CsvSource({"1,1"})
    void getPointHistory(int goalId, int expectedSize) {
        // when
        when(request.getAttribute(LoginEnum.USER_EMAIL.value)).thenReturn("localTest1@gmail.com");
        PointHistoriesResponse pointHistory = pointService.getPointHistory((long) goalId, request);

        // then
        log.info("pointHistory : {}", pointHistory.getPointHistories());
        assertThat(pointHistory.getPointHistories().size()).isEqualTo(expectedSize);
    }
}
