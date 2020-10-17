package com.titanic.fork.web.dto.response.point;

import com.titanic.fork.domain.point.Point;
import com.titanic.fork.domain.point.SavingPoint;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PointHistory {

    private String status;
    private int amount;
    private String createdAt;

    @Builder
    public PointHistory(String status, int amount, String createdAt) {
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public static PointHistory from(Point point) {
        LocalDateTime createdDate = point.getCreatedDate();
        String createdAt = createdDate.getYear() + "년 " + createdDate.getMonth() + "월 " + createdDate.getDayOfMonth() + "일 " +
                createdDate.getHour() + "시";

        String status = "";
        int amount;

        if (point.getClass().getSimpleName().split("\\$")[0].equals("SavingPoint")) {
            status = "적립";
            amount = point.getAmount();
        } else {
            status = "지출";
            amount = -point.getAmount();
        }

        return PointHistory.builder()
                .status(status)
                .amount(amount)
                .createdAt(createdAt)
                .build();
    }
}
