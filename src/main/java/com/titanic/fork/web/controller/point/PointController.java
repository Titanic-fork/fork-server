package com.titanic.fork.web.controller.point;


import com.titanic.fork.service.point.PointService;
import com.titanic.fork.web.dto.response.point.MonthlyPointResponse;
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

    @ApiOperation(value = "사용자의 누적 및 사용가능 포인트 조회API",
            notes = "성공 시 HttpStatus = 200(OK) \n" +
                    "실패 시 HttpStatus = ???(Internal Server Error")
    @ApiImplicitParam(name = "Authorization", value = "Jwt token", required = true,
            paramType = "header", dataType = "string", example = "testToken")
    @GetMapping("{goalId}")
    public PointResponse getTotalAndAvailablePoint(@PathVariable Long goalId,
                                                   HttpServletRequest request) {
        return pointService.getTotalAndAvailablePoint(goalId, request);
    }

    @ApiOperation(value = "사용자의 월간 적립된 포인트 조회API",
            notes = "성공 시 HttpStatus = 200(OK) \n" +
                    "실패 시 HttpStatus = ???(Internal Server Error")
    @ApiImplicitParam(name = "Authorization", value = "Jwt token", required = true,
            paramType = "header", dataType = "string", example = "testToken")
    @GetMapping("{goalId}/saved/{year}/{month}")
    public MonthlyPointResponse getMonthlySavedPoint(@PathVariable Long goalId,
                                                     @PathVariable Integer year,
                                                     @PathVariable Integer month,
                                                     HttpServletRequest request) {
        return pointService.getMonthlySavedPoint(goalId,year,month,request);
    }
}
