package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EachMonthlySavedPointStatus {

    private Long AccountId;
    private String name;
    private int monthlySavedPoint;

    @Builder
    public EachMonthlySavedPointStatus(Long accountId, String name, int monthlySavedPoint) {
        AccountId = accountId;
        this.name = name;
        this.monthlySavedPoint = monthlySavedPoint;
    }
}
