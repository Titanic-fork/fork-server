package com.titanic.fork.web.dto.request.goal;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateGoalRequest {

    private String title;
    // 장소
    private String address;
    private Double longitude;
    private Double latitude;
    // 목표 시간
    private LocalTime targetTime;
    // 알림
    private LocalTime alarmTime;
    private String targetDayOfWeeks;
    private String content;

    @Builder
    public CreateGoalRequest(String title, String address, Double longitude, Double latitude, LocalTime targetTime,
                             LocalTime alarmTime, String targetDayOfWeeks, String content) {
        this.title = title;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.targetTime = targetTime;
        this.alarmTime = alarmTime;
        this.targetDayOfWeeks = targetDayOfWeeks;
        this.content = content;
    }
}
