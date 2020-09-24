package com.titanic.fork.web.dto.request.goal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AchievementResponse {

    private float todayAchievement;
    private float weeklyAchievement;

    @Builder
    public AchievementResponse(float todayAchievement, float weeklyAchievement) {
        this.todayAchievement = todayAchievement;
        this.weeklyAchievement = weeklyAchievement;
    }

    public static AchievementResponse of(float todayAchievement, float weeklyAchievement) {
        return AchievementResponse.builder()
                .todayAchievement(todayAchievement)
                .weeklyAchievement(weeklyAchievement)
                .build();
    }
}
