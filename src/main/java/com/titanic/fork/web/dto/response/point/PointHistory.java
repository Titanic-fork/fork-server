package com.titanic.fork.web.dto.response.point;

import com.titanic.fork.domain.point.Point;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PointHistory {

    private String type;
    private int amount;
    private String createdAt;
    private String content;

    @Builder
    public PointHistory(String type, int amount, String createdAt, String content) {
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.content = content;
    }

    public static PointHistory from(Point point) {
        LocalDateTime createdDate = point.getCreatedDate();
        String createdAt = createdDate.getYear() + "년 " + createdDate.getMonth().getValue() + "월 " + createdDate.getDayOfMonth() + "일 " +
                createdDate.getHour() + "시";

        String type = "";
        int amount;

        if (point.getClass().getSimpleName().split("\\$")[0].equals("SavingPoint")) {
            type = "적립";
            amount = point.getAmount();
        } else {
            type = "지출";
            amount = -point.getAmount();
        }

        return PointHistory.builder()
                .type(type)
                .amount(amount)
                .createdAt(createdAt)
                .content((point.getContent() == null) ? "없음" : point.getContent())
                .build();
    }
}
