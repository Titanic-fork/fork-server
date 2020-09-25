package com.titanic.fork.web.controller.account;

import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.request.account.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final RegisterService registerService;

    @ApiOperation(value = "회원가입 API",
            notes = "201 : 성공, Header Authorization에 JWT 토큰 응답 \n" +
                    "500 : 서버 에러")
    @PostMapping()
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto registerRequestDto,
                                         HttpServletResponse response) {
        registerService.register(registerRequestDto, response);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "로그인 API",
            notes = "200 : 성공, Header Authorization에 JWT 토큰 응답 \n" +
                    "401 : 이메일과 패스워드가 다른 경우 \n" +
                    "500 : 서버 에러")
    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        accountService.login(loginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 수정을 위해 이름과 이메일이 같은 지 확인하는 API",
            notes = "200 : 성공, Header Authorization에 JWT 토큰 응답 \n" +
                    "401 : 이름과 이메일이 다른 경우")
    @PutMapping("/authentication")
    public ResponseEntity<Void> validateNameAndPassword(@RequestBody ValidateNameAndPasswordRequest
                                                                validateNameAndPasswordRequest) {
        accountService.validateNameAndPassword(validateNameAndPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "비밀번호 수정API",
                    notes = "200 : 성공" +
                            "500 : 서버 에러 (두 비밀번호가 같다는 전제")
    @ApiImplicitParam(name = "Authorization", value = "Jwt token", required = true,
            paramType = "header", dataType = "string", example = "testToken")
    @PutMapping("password")
    public ResponseEntity<Void> changePassword(@RequestBody NewPasswordRequest newPasswordRequest) {
        accountService.changePassword(newPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "핸드폰 번호를 수정하는 API",
                    notes = "200 : 성공 \n" +
                            "500 : 서버 에러")
    @ApiImplicitParam(name = "Authorization", value = "Jwt token", required = true,
            paramType = "header", dataType = "string", example = "testToken")
    @PutMapping("phone-number")
    public ResponseEntity<Void> changePhoneNumber(@RequestBody NewPhoneNumberRequest newPhoneNumberRequest) {
        accountService.changePhoneNumber(newPhoneNumberRequest);
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
