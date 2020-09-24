package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MonthlyPointResponse {

    private int savedPoint;
    private int usedPoint;

    @Builder
    public MonthlyPointResponse(int savedPoint, int usedPoint) {
        this.savedPoint = savedPoint;
        this.usedPoint = usedPoint;
    }

    public static MonthlyPointResponse toSavedPoint(int savedPointSum) {
        return MonthlyPointResponse.builder()
                .savedPoint(savedPointSum)
                .build();
    }

    public static MonthlyPointResponse toUsedPoint(int usedPointSum) {
        return MonthlyPointResponse.builder()
                .usedPoint(usedPointSum)
                .build();
    }
}
