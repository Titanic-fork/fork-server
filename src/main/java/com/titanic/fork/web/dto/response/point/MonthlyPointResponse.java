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
}
