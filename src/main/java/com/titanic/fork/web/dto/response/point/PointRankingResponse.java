package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRankingResponse {

    private List<EachMonthlySavedPointStatus> eachMonthlySavedPoints;

    @Builder
    public PointRankingResponse(List<EachMonthlySavedPointStatus> eachMonthlySavedPoints) {
        this.eachMonthlySavedPoints = eachMonthlySavedPoints;
    }

    public static PointRankingResponse of(List<EachMonthlySavedPointStatus> eachMonthlySavedPoints) {
        return PointRankingResponse.builder()
                .eachMonthlySavedPoints(eachMonthlySavedPoints)
                .build();
    }
}
