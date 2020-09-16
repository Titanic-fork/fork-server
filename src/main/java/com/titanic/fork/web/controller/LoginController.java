package com.titanic.fork.web.controller;

import com.titanic.fork.service.account.LoginService;
import com.titanic.fork.web.dto.request.account.NewPasswordRequest;
import com.titanic.fork.web.dto.request.account.ValidateNameAndPasswordRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(value = "비밀번호 수정을 위한 인증API")
    @PostMapping("find")
    public ResponseEntity<Void> validateNameAndPassword(@RequestBody ValidateNameAndPasswordRequest
                                                                validateNameAndPasswordRequest) {
        return loginService.validateNameAndPassword(validateNameAndPasswordRequest);
    }

    @ApiOperation(value = "비밀번호 수정API")
    @PutMapping("find")
    public ResponseEntity<Void> changePassword(@RequestBody NewPasswordRequest newPasswordRequest) {
        return loginService.changePassword(newPasswordRequest);
    }

    /*
     * AccountRepository에서 해당 계정이 없을 때 발생하는 에러를 핸들링하는 메서드
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Void> noResultAccount() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
