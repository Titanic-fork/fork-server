package com.titanic.fork.web.controller;

import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    @PutMapping("phoneNumber")
    public ResponseEntity<Void> changePhoneNumber(@RequestBody NewPhoneNumberRequest newPhoneNumberRequest) {
        return accountService.changePhoneNumber(newPhoneNumberRequest);
    }
}
