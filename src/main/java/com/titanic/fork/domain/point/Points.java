package com.titanic.fork.domain.point;

import com.titanic.fork.web.dto.response.point.PointHistory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Points {

    private List<Point> points;

    @Builder
    public Points(List<Point> points) {
        this.points = points;
    }

    public static Points from(List<Point> points) {
        return Points.builder()
                .points(points)
                .build();
    }

    public List<PointHistory> toPointHistory() {
        return points.stream()
                .map(PointHistory::from)
                .collect(Collectors.toList());
    }
}
