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
public class UsedPoint extends Point {

    @ElementCollection
    @CollectionTable(name = "POINT_IMAGE", joinColumns = @JoinColumn(name = "POINT_ID"))
    @Column(name = "POINT_IMAGE_URL")
    private List<String> images = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POINT_STATUS")
    private PointStatus status;

    private LocalDateTime approvalDate;
}
