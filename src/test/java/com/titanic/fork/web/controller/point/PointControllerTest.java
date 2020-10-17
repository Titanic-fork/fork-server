package com.titanic.fork.web.controller.point;

import com.titanic.fork.domain.point.PointCalculator;
import com.titanic.fork.service.goal.GoalService;
import com.titanic.fork.service.point.PointService;
import com.titanic.fork.utils.LocalEnum;
import com.titanic.fork.utils.LocalTestEnum;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PointController.class)
public class PointControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PointService pointService;

    private final String REQUEST_MAPPING = "/point";
    private static final String token = LocalTestEnum.JWT_TOKEN_LOCAL_TEST_1.token;

    @DisplayName("사용자의 전체 포인트 조회")
    @ParameterizedTest
    @CsvSource({"1"})
    void getPointHistory(int goalId) throws Exception {

        // given
        String requestUrl = LocalEnum.LOCALHOST.value + REQUEST_MAPPING + "/" + goalId + "/point-history";

        // when, then
        mockMvc.perform(get(requestUrl)
                .header(LocalEnum.ORIGIN.value, LocalEnum.ALL.value)
                .header(LoginEnum.AUTHORIZATION.value, token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
