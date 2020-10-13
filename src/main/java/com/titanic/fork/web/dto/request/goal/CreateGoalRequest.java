package com.titanic.fork.web.dto.request.goal;


import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.goal.Alarm;
import com.titanic.fork.domain.goal.Goal;
import com.titanic.fork.domain.goal.Location;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateGoalRequest {

    private String title;
    // 장소
    private String address;
    private Double longitude;
    private Double latitude;
    // 목표 시간
    private int targetTimeHour;
    private int targetTimeMinute;

    // 알림
    private int alarmTimeHour;
    private int alarmTimeMinute;
    private String targetDayOfWeeks;
    private String content;

    @Builder
    public CreateGoalRequest(String title, String address, Double longitude, Double latitude,
                             int targetTimeHour, int targetTimeMinute, int alarmTimeHour,
                             int alarmTimeMinute, String targetDayOfWeeks, String content) {
        this.title = title;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.targetTimeHour = targetTimeHour;
        this.targetTimeMinute = targetTimeMinute;
        this.alarmTimeHour = alarmTimeHour;
        this.alarmTimeMinute = alarmTimeMinute;
        this.targetDayOfWeeks = targetDayOfWeeks;
        this.content = content;
    }

    public static Goal toEntity(CreateGoalRequest createGoalRequest, Location location) {
        return Goal.builder()
                .title(createGoalRequest.getTitle())
                .location(location)
                .build();
    }

    public static AccountGoal toEntity(Alarm alarm, CreateGoalRequest createGoalRequest) {
        LocalTime targetTime = LocalTime.of(createGoalRequest.getTargetTimeHour(),
                createGoalRequest.getTargetTimeMinute());
        return AccountGoal.builder()
                .alarm(alarm)
                .targetTime(targetTime)
                .build();
    }

    public static Alarm toEntity(List<String> targetDayOfWeeks, CreateGoalRequest createGoalRequest) {
        LocalTime alarmTime = LocalTime.of(createGoalRequest.getAlarmTimeHour(),
                createGoalRequest.getAlarmTimeMinute());
        return Alarm.builder()
                .targetDayOfWeeks(targetDayOfWeeks)
                .alarmTime(alarmTime)
                .content(createGoalRequest.getContent())
                .build();
    }
}
