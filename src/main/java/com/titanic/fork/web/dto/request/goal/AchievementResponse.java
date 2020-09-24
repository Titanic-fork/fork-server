package com.titanic.fork.web.dto.request.goal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AchievementResponse {

    private int todayAchievement;
    private int weeklyAchievement;

    @Builder
    public AchievementResponse(int todayAchievement, int weeklyAchievement) {
        this.todayAchievement = todayAchievement;
        this.weeklyAchievement = weeklyAchievement;
    }
}
