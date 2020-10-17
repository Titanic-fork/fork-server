package com.titanic.fork.domain.point;

import com.titanic.fork.domain.Account.AccountGoal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("saving_point")
@SuperBuilder
public class SavingPoint extends Point {

    public static Point of(AccountGoal accountGoal, int amount) {
        return SavingPoint.builder()
                .accountGoal(accountGoal)
                .amount(amount)
                .createdDate(LocalDateTime.now())
                .build();
    }
}
