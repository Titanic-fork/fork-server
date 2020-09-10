package com.titanic.fork.web.controller;

import com.titanic.fork.web.dto.request.account.RegisterWantDto;
import com.titanic.fork.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("account")
    public ResponseEntity<Void> register(@RequestBody RegisterWantDto registerWantDto) {
        return registerService.register(registerWantDto);
    }
}
