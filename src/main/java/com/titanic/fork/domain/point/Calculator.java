package com.titanic.fork.domain.point;

import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.response.point.EachMonthlySavedPointStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Calculator {

    int sumCalculate(List<Point> points);

    List<EachMonthlySavedPointStatus> getRankingOfPoints(List<AccountGoal> foundAccountGoals, int year, int month);

    AchievementResponse calculateAchievement(AccountGoal foundAccountGoal, int todayTime, int weeklyTime);
}
