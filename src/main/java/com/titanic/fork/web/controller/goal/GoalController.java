package com.titanic.fork.web.controller.goal;

import com.titanic.fork.service.goal.GoalService;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("goal")
public class GoalController {

    private final GoalService goalService;

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody CreateGoalRequest createGoalRequest,
                                       HttpServletRequest request) {
        goalService.create(createGoalRequest, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("achievement")
    public AchievementResponse getAchievement(@RequestParam Integer todayTime,
                                              @RequestParam Integer weeklyTime) {
        return goalService.getAchievement(todayTime, weeklyTime);
    }
}
