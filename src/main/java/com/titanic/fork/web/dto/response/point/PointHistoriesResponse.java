package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistoriesResponse {

    private List<PointHistory> pointHistories;

    @Builder
    public PointHistoriesResponse(List<PointHistory> pointHistories) {
        this.pointHistories = pointHistories;
    }
}
