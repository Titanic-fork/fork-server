package com.titanic.fork.web.dto.response.point;

import com.titanic.fork.domain.Account.AccountGoal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EachMonthlySavedPointStatus {

    private Long accountId;
    private String name;
    private int monthlySavedPoint;

    @Builder
    public EachMonthlySavedPointStatus(Long accountId, String name, int monthlySavedPoint) {
        this.accountId = accountId;
        this.name = name;
        this.monthlySavedPoint = monthlySavedPoint;
    }

    public static EachMonthlySavedPointStatus of(AccountGoal accountGoal, int savedPointSum) {
        return EachMonthlySavedPointStatus.builder()
                .accountId(accountGoal.getAccount().getId())
                .name(accountGoal.getAccount().getName())
                .monthlySavedPoint(savedPointSum)
                .build();
    }
}
