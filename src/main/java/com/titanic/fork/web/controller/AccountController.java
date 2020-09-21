package com.titanic.fork.web.controller;

import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.web.dto.request.account.NewPasswordRequest;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
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
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "핸드폰번호를 수정하는 API",
                    notes = "성공 시 HttpStatus = 200(OK) \n" +
                            "실패 시 HttpStatus = 500(Internal Server Error")
    @PutMapping("phone-number")
    public ResponseEntity<Void> changePhoneNumber(@RequestBody NewPhoneNumberRequest newPhoneNumberRequest) {
        accountService.changePhoneNumber(newPhoneNumberRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 수정을 위한 인증API",
                    notes = "이름과 이메일이 같은 지 확인하는 API")
    @PutMapping("/authentication")
    public ResponseEntity<Void> validateNameAndPassword(@RequestBody ValidateNameAndPasswordRequest
                                                                validateNameAndPasswordRequest) {
        accountService.validateNameAndPassword(validateNameAndPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 수정API")
    @PutMapping("password")
    public ResponseEntity<Void> changePassword(@RequestBody NewPasswordRequest newPasswordRequest) {
        accountService.changePassword(newPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * AccountRepository에서 해당 계정이 없을 때 발생하는 에러를 핸들링하는 메서드
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Void> noResultAccount() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
