package com.titanic.fork.web.controller.point;


import com.titanic.fork.service.point.PointService;
import com.titanic.fork.web.dto.response.point.MonthlyPointResponse;
import com.titanic.fork.web.dto.response.point.PointRankingResponse;
import com.titanic.fork.web.dto.response.point.PointResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("point")
public class PointController {

    private final PointService pointService;
    private static final String JWT_TOKEN = "eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ" +
            ".eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1M0BnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjUzQGdtYWlsLmNvbSIsImV4cCI6MTYwMTYzODI4OCwiaWF0IjoxNjAwNzc0Mjg4fQ" +
            ".A6dxOdl0HnHqogXln8imccrZJ_WxVdYKbE9f728duXg";

    @ApiOperation(value = "메인화면 속 사용자의 누적 및 사용가능 포인트 조회 API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goalId}")
    public PointResponse getTotalAndAvailablePoint(@PathVariable Long goalId,
                                                   HttpServletRequest request) {
        return pointService.getTotalAndAvailablePoint(goalId, request);
    }

    @ApiOperation(value = "사용자의 월간 적립 포인트 조회API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goalId}/saved/{year}/{month}")
    public MonthlyPointResponse getMonthlySavedPoint(@PathVariable Long goalId,
                                                     @PathVariable Integer year,
                                                     @PathVariable Integer month,
                                                     HttpServletRequest request) {
        return pointService.getMonthlySavedPoint(goalId,year,month,request);
    }

    @ApiOperation(value = "사용자의 월간 사용 포인트 조회API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goalId}/used/{year}/{month}")
    public MonthlyPointResponse getMonthlyUsedPoint(@PathVariable Long goalId,
                                                    @PathVariable Integer year,
                                                    @PathVariable Integer month,
                                                    HttpServletRequest request) {
        return pointService.getMonthlyUsedPoint(goalId, year, month, request);
    }

    @ApiOperation(value = "해당 목표 회원들의 월간 누적 포인트 랭킹 API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goalId}/ranking/{year}/{month}")
    public PointRankingResponse getMonthlyPointRanking(@PathVariable Long goalId,
                                                       @PathVariable Integer year,
                                                       @PathVariable Integer month,
                                                       HttpServletRequest request) {
        return pointService.getMonthlyPointRanking(goalId, year, month, request);
    }
}
