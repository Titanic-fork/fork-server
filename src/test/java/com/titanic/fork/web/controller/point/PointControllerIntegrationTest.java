package com.titanic.fork.web.controller.point;

import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.response.point.MonthlyPointResponse;
import com.titanic.fork.web.dto.response.point.PointRankingResponse;
import com.titanic.fork.web.dto.response.point.PointResponse;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "30000")
public class PointControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    private final static String requestMapping = "/point";

    @DisplayName("사용자 누적포인트, 사용가능 포인트 조회 API")
    @ParameterizedTest
    @CsvSource({"2000,1600"})
    void 사용자의_누적및사용가능_포인트조회API를_테스트한다(int totalPoint, int availablePoint) {

        // given
        String localRequestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping + "/1";

        // when
        PointResponse pointResponse = webTestClient.get()
                .uri(localRequestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_GUSWNS1653.getValue())
                .exchange()
                .expectBody(PointResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(pointResponse.getTotalPoint()).isEqualTo(totalPoint);
        assertThat(pointResponse.getAvailablePoint()).isEqualTo(availablePoint);
    }

    @DisplayName("사용자의 월간 적립 포인트 조회API")
    @ParameterizedTest
    @CsvSource({"1,2020,9,2000"})
    void 사용자의_월간적립포인트조회API를_테스트한다(int goalId, int year, int month, int savedPoint) {

        //given
        String localRequestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping + "/"
                + goalId + "/saved" + "/" + year + "/" + month;

        // when
        MonthlyPointResponse monthlyPointResponse = webTestClient.get()
                .uri(localRequestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_GUSWNS1653.getValue())
                .exchange()
                .expectBody(MonthlyPointResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(monthlyPointResponse.getSavedPoint()).isEqualTo(savedPoint);
    }

    @DisplayName("사용자의 월간 사용 포인트 조회API")
    @ParameterizedTest
    @CsvSource({"1,2020,9,400"})
    void 사용자의_월간사용_포인트조회API를_테스트한다(int goalId, int year, int month, int savedPoint) {

        //given
        String localRequestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping + "/"
                + goalId + "/used" + "/" + year + "/" + month;

        // when
        MonthlyPointResponse monthlyPointResponse = webTestClient.get()
                .uri(localRequestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_GUSWNS1653.getValue())
                .exchange()
                .expectBody(MonthlyPointResponse.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(monthlyPointResponse.getUsedPoint()).isEqualTo(savedPoint);
    }

    @DisplayName("해당 목표 사용자들의 월간 누적 포인트 랭킹API")
    @ParameterizedTest
    @CsvSource({"1,2020,9,4,3000,hyunjun"})
    void 해당목표_사용자들의_월간누적포인트랭킹API를_테스트한다(int goalId, int year, int month, int size, int savedPoint, String name) {

        // given
        String localRequestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping + "/"
                + goalId + "/ranking" + "/" + year + "/" + month;

        // when
        PointRankingResponse pointRankingResponse = webTestClient.get()
                .uri(localRequestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_GUSWNS1653.getValue())
                .exchange()
                .expectBody(PointRankingResponse.class)
                .returnResult()
                .getResponseBody();

        /* then
         * size()를 4로 한 이유는 account_id = 1인 계정이 같은 목표에 2개 들어가 있음. 조정이 필요
         */
        assertThat(pointRankingResponse.getEachMonthlySavedPoints().size()).isEqualTo(size);
        assertThat(pointRankingResponse.getEachMonthlySavedPoints().get(1).getMonthlySavedPoint()).isEqualTo(savedPoint);
        assertThat(pointRankingResponse.getEachMonthlySavedPoints().get(1).getName()).isEqualTo(name);
    }
}
