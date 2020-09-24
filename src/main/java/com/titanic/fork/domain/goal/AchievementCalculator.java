package com.titanic.fork.domain.goal;

import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.domain.point.Calculator;
import com.titanic.fork.domain.point.Point;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.response.point.EachMonthlySavedPointStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AchievementCalculator implements Calculator {

    @Override
    public int sumCalculate(List<Point> points) {
        return 0;
    }

    @Override
    public List<EachMonthlySavedPointStatus> getRankingOfPoints(List<AccountGoal> foundAccountGoals, int year, int month) {
        return null;
    }

    @Override
    public AchievementResponse calculateAchievement(AccountGoal foundAccountGoal, int todayTime, int weeklyTime) {
        int hour = foundAccountGoal.getTargetTime().getHour();
        int minute = foundAccountGoal.getTargetTime().getMinute();
        float todayTargetMinute = (hour * 60) + minute;
        float weeklyTargetMinute = todayTargetMinute * 7;

        float todayAchievement = Math.round((todayTime / todayTargetMinute) * 100);
        float weeklyAchievement = Math.round((weeklyTime / weeklyTargetMinute) * 100);
        return AchievementResponse
                .of(todayAchievement, weeklyAchievement);
    }
}
