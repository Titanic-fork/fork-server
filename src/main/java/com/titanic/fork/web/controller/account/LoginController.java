package com.titanic.fork.web.controller.account;

import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.web.dto.request.account.LoginRequest;
import io.swagger.annotations.ApiImplicitParam;
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

    private final AccountService accountService;

    @ApiOperation(value = "로그인 API",
                    notes = "성공 시 HttpStatus = 200(OK) \n" +
                            "이메일 없을 시 HttpStatus = 204(No Content) \n" +
                            "로그인 실패 시 HttpStatus = 403(Forbidden)")
    @ApiImplicitParam(name = "Authorization", value = "Jwt token", required = true,
            paramType = "header", dataType = "string", example = "testToken")
    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        accountService.login(loginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * getSingleResult() 했을 때 해당 조회 결과가 없을 경우 : 204, No Content
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Void> noResultAccount() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
