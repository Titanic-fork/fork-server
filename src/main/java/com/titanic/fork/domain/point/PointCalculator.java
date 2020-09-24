package com.titanic.fork.domain.point;

import com.titanic.fork.domain.Account.AccountGoal;
import com.titanic.fork.repository.point.PointRepository;
import com.titanic.fork.utils.DateSupplier;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.response.point.EachMonthlySavedPointStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PointCalculator implements Calculator {

    private final DateSupplier dateSupplier;
    private final PointRepository pointRepository;

    @Override
    public int sumCalculate(List<Point> points) {
        return points.stream()
                .mapToInt(Point::getAmount)
                .sum();
    }

    /*
     * 반복문에서 각 accountGoal의 월간 적립 포인트를 찾은 뒤
     * MonthlyPointResponse에 저장한다.
     */
    @Override
    public List<EachMonthlySavedPointStatus> getRankingOfPoints(List<AccountGoal> foundAccountGoals,
                                                                int year, int month) {
        int nextMonth = month + 1;

        List<EachMonthlySavedPointStatus> eachMonthlySavedPoints = new ArrayList<>();
        for (AccountGoal accountGoal : foundAccountGoals) {
            List<Point> monthlySavedPoints = pointRepository.findAllMonthlySavedPoint(accountGoal.getId(),
                    dateSupplier.localDateTime(year,month), dateSupplier.localDateTime(year, nextMonth));
            int savedPointSum = sumCalculate(monthlySavedPoints);

            EachMonthlySavedPointStatus eachMonthlySavedPointStatus
                    = EachMonthlySavedPointStatus.of(accountGoal, savedPointSum);
            eachMonthlySavedPoints.add(eachMonthlySavedPointStatus);
        }
        eachMonthlySavedPoints.sort((o1, o2) -> Integer.compare(o2.getMonthlySavedPoint(), o1.getMonthlySavedPoint()));
        return eachMonthlySavedPoints;
    }

    @Override
    public AchievementResponse calculateAchievement(AccountGoal foundAccountGoal, int todayTime, int weeklyTime) {
        return null;
    }
}
