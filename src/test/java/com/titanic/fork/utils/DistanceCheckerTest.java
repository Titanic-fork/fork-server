package com.titanic.fork.utils;

import com.titanic.fork.utils.checker.MeterDistanceChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DistanceCheckerTest {

    @Spy
    private MeterDistanceChecker meterDistanceChecker;

    private Logger log = LoggerFactory.getLogger(DistanceCheckerTest.class);

    @DisplayName("getDistance 메서드 테스트")
    @ParameterizedTest
    @CsvSource({"37.51064,127.11324,37.51056,127.11285"})
    void getDistance(double lat1, double lon1, double lat2, double lon2) {

        // when
        double distance = meterDistanceChecker.getDistance(lat1, lon1, lat2, lon2);

        // then
        log.info("distance : {}", distance);
        assertThat(distance).isLessThan(50);
    }
}
