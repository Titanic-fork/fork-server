package com.titanic.fork.web.controller.point;


import com.titanic.fork.service.point.PointService;
import com.titanic.fork.web.dto.response.point.PointResponse;
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

    @GetMapping("{goalId}")
    public PointResponse getTotalAndAvailablePoint(@PathVariable Long goalId,
                                                   HttpServletRequest request) {
        return pointService.getTotalAndAvailablePoint(request);
    }
}
