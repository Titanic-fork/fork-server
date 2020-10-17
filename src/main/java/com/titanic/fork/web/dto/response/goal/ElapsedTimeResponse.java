package com.titanic.fork.web.dto.response.goal;

import lombok.*;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ElapsedTimeResponse {

    private int elapsedHour;
    private int elapsedMinute;

    @Builder
    public ElapsedTimeResponse(int elapsedHour, int elapsedMinute) {
        this.elapsedHour = elapsedHour;
        this.elapsedMinute = elapsedMinute;
    }

    public static ElapsedTimeResponse from(LocalTime elapsedTime) {
        return ElapsedTimeResponse.builder()
                .elapsedHour(elapsedTime.getHour())
                .elapsedMinute(elapsedTime.getMinute())
                .build();
    }
}
