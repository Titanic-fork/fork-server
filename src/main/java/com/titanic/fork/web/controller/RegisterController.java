package com.titanic.fork.web.controller;

import com.titanic.fork.service.RegisterService;
import com.titanic.fork.web.dto.request.account.RegisterWantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("account")
    public ResponseEntity<Void> register(@RequestBody RegisterWantDto registerWantDto,
                                         HttpServletResponse response) {
        return registerService.register(registerWantDto, response);
    }
}
