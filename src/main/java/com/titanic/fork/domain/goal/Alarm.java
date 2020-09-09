package com.titanic.fork.domain.goal;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Alarm {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "TARGET_DAY_OF_WEEK", joinColumns = @JoinColumn(name = "alarm_id"))
    @Column(name = "DAY_OF_WEEKS")
    private List<String> targetDayOfWeeks = new ArrayList<>();

    private LocalTime alarmTime;

    @Column(name = "ALARM_CONTENT", columnDefinition = "VARCHAR(500)")
    private String content;
}
