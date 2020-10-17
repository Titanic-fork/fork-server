package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
