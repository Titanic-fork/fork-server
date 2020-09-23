package com.titanic.fork.web.controller.point;

import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.response.point.PointResponse;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
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
    @CsvSource({"3000,1000"})
    void 사용자의_누적및사용가능_포인트조회API를_테스트한다(int totalPoint, int availablePoint) {

        // given
        String localRequestUrl = TestEnum.LOCALHOST.getValue() + port + requestMapping + "/1";

        // when
        PointResponse pointResponse = webTestClient.get()
                .uri(localRequestUrl)
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_EXAMPLE.getValue())
                .exchange()
                .expectBody(PointResponse.class)
                .returnResult()
                .getResponseBody();

        // then
//        assertThat(pointResponse.getTotalPoint()).isEqualTo(totalPoint);
//        assertThat(pointResponse.getAvailablePoint()).isEqualTo(availablePoint);
    }
}
