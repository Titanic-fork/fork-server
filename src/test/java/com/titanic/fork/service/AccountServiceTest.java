package com.titanic.fork.service;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @DisplayName("전화번호변경이 성공했는지 확인하는 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,010-5678-5678"})
    void changePhone를_테스트한다(String email, String phoneNumber) {

        // given
        NewPhoneNumberRequest newPhoneNumberRequest = NewPhoneNumberRequest.of(email, phoneNumber);

        // when
        accountService.changePhoneNumber(newPhoneNumberRequest);
        Account foundAccount = accountRepository.findByEmail(newPhoneNumberRequest.getEmail());

        // then
        assertThat(foundAccount.getEmail()).isEqualTo(email);
    }
}
