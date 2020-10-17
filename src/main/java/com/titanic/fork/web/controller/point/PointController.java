package com.titanic.fork.web.controller.point;


import com.titanic.fork.service.point.PointService;
import com.titanic.fork.web.dto.response.point.*;
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
    private static final String JWT_TOKEN = "eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1OUBnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjU5QGdtYWlsLmNvbSIsImV4cCI6MTYzNDIyMTA4MSwiaWF0IjoxNjAyNjg1MDgxfQ.Wq9MU5UJ7oUkKTiInSerINLdAfKEVJJmO5KLLiw3tz4";

    @ApiOperation(value = "사용자의 포인트 사용내역 API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goal-id}/point-history")
    public PointHistoriesResponse getPointHistory(@PathVariable("goal-id") Long goalId,
                                                  HttpServletRequest request) {
        return pointService.getPointHistory(goalId, request);
    }

    @ApiOperation(value = "메인화면 속 사용자의 누적 및 사용가능 포인트 조회 API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goal-id}")
    public PointResponse getTotalAndAvailablePoint(@PathVariable("goal-id") Long goalId,
                                                   HttpServletRequest request) {
        return pointService.getTotalAndAvailablePoint(goalId, request);
    }

    @ApiOperation(value = "사용자의 월간 적립 포인트 조회API",
            notes = "200 : 성공 \n" +
                    "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @GetMapping("{goal-id}/saved/{year}/{month}")
    public MonthlyPointResponse getMonthlySavedPoint(@PathVariable("goal-id") Long goalId,
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
    @GetMapping("{goal-id}/used/{year}/{month}")
    public MonthlyPointResponse getMonthlyUsedPoint(@PathVariable("goal-id") Long goalId,
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
    @GetMapping("{goal-id}/ranking/{year}/{month}")
    public PointRankingResponse getMonthlyPointRanking(@PathVariable("goal-id") Long goalId,
                                                       @PathVariable Integer year,
                                                       @PathVariable Integer month,
                                                       HttpServletRequest request) {
        return pointService.getMonthlyPointRanking(goalId, year, month, request);
    }
}
