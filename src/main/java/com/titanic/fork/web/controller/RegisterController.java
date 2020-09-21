package com.titanic.fork.web.controller;

import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.web.dto.request.account.RegisterRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @ApiOperation(value = "회원가입API",
                    notes = "회원가입 성공 시 HttpStatus = 201(Created), Header에 Authorization에 JWT 토큰 응답 \n" +
                            "회원가입 실패 시 HttpStatus = 500(Internal Sever Error)")
    @PostMapping("account")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto registerRequestDto,
                                         HttpServletResponse response) {
        registerService.register(registerRequestDto, response);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
