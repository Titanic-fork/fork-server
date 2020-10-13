package com.titanic.fork.domain.goal;

import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @NoArgsConstructor
public class Alarm {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "TARGET_DAY_OF_WEEK", joinColumns = @JoinColumn(name = "ALARM_ID"))
    @Column(name = "DAY_OF_WEEKS")
    private List<String> targetDayOfWeeks = new ArrayList<>();

    private LocalTime alarmTime;

    @Column(name = "ALARM_CONTENT", columnDefinition = "VARCHAR(500)")
    private String content;

    @Builder
    public Alarm(List<String> targetDayOfWeeks, LocalTime alarmTime, String content) {
        this.targetDayOfWeeks = targetDayOfWeeks;
        this.alarmTime = alarmTime;
        this.content = content;
    }
}
