package com.titanic.fork.domain.point;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("used_point")
public class UsedPoint extends Point {

    @ElementCollection
    @CollectionTable(name = "POINT_IMAGE", joinColumns = @JoinColumn(name = "POINT_ID"))
    @Column(name = "POINT_IMAGE_URL")
    private List<String> images = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POINT_STATUS")
    private PointStatus status;

    private LocalDateTime approvalDate;

    @Override
    public boolean isPeriod(int year, int month) {
        int first = 1;
        int zero = 0;
        int nextMonth = month + 1;
        return createdDate.isAfter(LocalDateTime.of(year,month,first,zero,zero))
                && createdDate.isBefore(LocalDateTime.of(year,nextMonth,first,zero,zero));
    }
}
