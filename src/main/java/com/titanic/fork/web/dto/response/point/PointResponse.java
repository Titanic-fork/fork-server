package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointResponse {

    private int totalPoint;
    private int availablePoint;

    @Builder
    public PointResponse(int totalPoint, int availablePoint) {
        this.totalPoint = totalPoint;
        this.availablePoint = availablePoint;
    }

    public static PointResponse of(int totalPoint, int usedPoint) {
        return PointResponse.builder()
                .totalPoint(totalPoint)
                .availablePoint(totalPoint - usedPoint)
                .build();
    }
}
