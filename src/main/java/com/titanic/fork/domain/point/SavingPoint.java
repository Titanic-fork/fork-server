package com.titanic.fork.domain.point;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("saving_point")
public class SavingPoint extends Point {

    @Override
    public boolean isPeriod(int year, int month) {
        int first = 1;
        int zero = 0;
        int nextMonth = month + 1;
        return createdDate.isAfter(LocalDateTime.of(year,month,first,zero,zero))
                && createdDate.isBefore(LocalDateTime.of(year,nextMonth,first,zero,zero));
    }
}
