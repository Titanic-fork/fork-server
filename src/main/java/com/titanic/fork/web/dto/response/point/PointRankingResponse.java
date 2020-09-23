package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRankingResponse {

    private List<EachMonthlySavedPointStatus> eachMonthlyPoints;
}
