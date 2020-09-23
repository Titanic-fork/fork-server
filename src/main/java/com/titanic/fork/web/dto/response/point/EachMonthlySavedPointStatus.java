package com.titanic.fork.web.dto.response.point;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EachMonthlySavedPointStatus {

    private Long AccountId;
    private String name;
    private int monthlySavedPoint;
}
