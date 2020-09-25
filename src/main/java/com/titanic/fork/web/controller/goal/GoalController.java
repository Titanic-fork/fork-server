package com.titanic.fork.web.controller.goal;

import com.titanic.fork.service.goal.GoalService;
import com.titanic.fork.utils.DeployTestEnum;
import com.titanic.fork.web.dto.request.goal.AchievementResponse;
import com.titanic.fork.web.dto.request.goal.CreateGoalRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    // 스웨거에 사용될 jwt example, Enum 사용이 안되서 부득히하게 선언
    private static final String JWT_TOKEN = "eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1M0BnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjUzQGdtYWlsLmNvbSIsImV4cCI6MTYwMTg5NzYxNywiaWF0IjoxNjAxMDMzNjE3fQ.nfrrASV5ltnTCmffrXshuyNDrWo6pAcggtvzMk1_M9o";

    @ApiOperation(value = "목표를 추가하는 API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody CreateGoalRequest createGoalRequest,
                                       HttpServletRequest request) {
        goalService.create(createGoalRequest, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "메인화면 속 일일, 주간 목표달성률 API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goalId}/achievement")
    public AchievementResponse getAchievement(@PathVariable Long goalId,
                                              @RequestParam(value = "today-elapsedtime") Integer todayElapsedTime,
                                              @RequestParam(value = "weekly-elapsedtime") Integer weeklyElapsedTime,
                                              HttpServletRequest request) {
        return goalService.getAchievement(goalId, todayElapsedTime, weeklyElapsedTime, request);
    }
}
