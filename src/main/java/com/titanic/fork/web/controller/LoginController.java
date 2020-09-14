package com.titanic.fork.web.controller;

import com.titanic.fork.service.LoginService;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("find")
    public ResponseEntity<Void> validateNameAndPassword(@RequestBody ValidateNameAndPasswordDto
                                                                    validateNameAndPasswordDto) {
        return loginService.validateNameAndPassword(validateNameAndPasswordDto);
    }
}
