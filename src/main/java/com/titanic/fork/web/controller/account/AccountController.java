package com.titanic.fork.web.controller.account;

import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.web.dto.request.account.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final RegisterService registerService;
    // 스웨거에 사용될 jwt example, Enum 사용이 안되서 부득히하게 선언
    private static final String JWT_TOKEN = "eyJIUzI1NiI6IkhTMjU2IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJBdXRob3JpemF0aW9uIjoiZ3Vzd25zMTY1MUBnbWFpbC5jb20iLCJzdWIiOiJndXN3bnMxNjUxQGdtYWlsLmNvbSIsImV4cCI6MTYzNDQ4NjI5MiwiaWF0IjoxNjAyOTUwMjkyfQ.AX1YqEp8X129RCL5_HqJiMlJmJO5jnZP0oabi32TQB8";

    @ApiOperation(value = "회원가입 API",
            notes = "201 : 성공 \n" +
                    "500 : 서버 에러")
    @PostMapping()
    public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest,
                                         HttpServletResponse response) {
        registerService.register(registerRequest, response);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "중복 이메일 확인 API",
            notes = "200 : 중복 이메일 아님 \n" +
                    "202 : 중복된 이메일 존재")
    @GetMapping("/email/{email}")
    public ResponseEntity<Void> validateDuplicatedEmail(@PathVariable String email) {
        registerService.validateDuplicatedEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "로그인 API",
            notes = "200 : 성공, Header Authorization에 JWT 토큰 응답 \n" +
                    "204 : 해당 이메일이 DB에 없는 경우" +
                    "401 : 이메일과 패스워드가 다른 경우 \n" +
                    "500 : 서버 에러")
    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest,
                                      HttpServletResponse response) {
        accountService.login(loginRequest, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 수정을 위해 이름과 이메일이 같은 지 확인하는 API",
            notes = "200 : 성공, Header Authorization에 JWT 토큰 응답 \n" +
                    "401 : 이름과 이메일이 다른 경우")
    @PutMapping("/authentication")
    public ResponseEntity<Void> validateNameAndPassword(@RequestBody ValidateNameAndPasswordRequest
                                                                validateNameAndPasswordRequest,
                                                        HttpServletResponse response) {
        accountService.validateNameAndPassword(validateNameAndPasswordRequest, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 수정API",
                    notes = "200 : 성공 \n" +
                            "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @PutMapping("password")
    public ResponseEntity<Void> changePassword(@RequestBody NewPasswordRequest newPasswordRequest,
                                               HttpServletRequest request) {
        accountService.changePassword(newPasswordRequest, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "핸드폰 번호를 수정하는 API",
                    notes = "200 : 성공 \n" +
                            "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "test jwt token", required = true,
            paramType = "header", dataType = "string", example = JWT_TOKEN)
    @PutMapping("phone-number")
    public ResponseEntity<Void> changePhoneNumber(@RequestBody NewPhoneNumberRequest newPhoneNumberRequest,
                                                  HttpServletRequest request) {
        accountService.changePhoneNumber(newPhoneNumberRequest, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * getSingleResult() 했을 때 해당 Account의 조회 결과가 없을 경우 : 204, No Content
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Void> noResultAccount() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
