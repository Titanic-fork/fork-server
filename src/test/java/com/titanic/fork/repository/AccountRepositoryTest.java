package com.titanic.fork.repository;

import com.titanic.fork.domain.Account.Account;
import com.titanic.fork.repository.account.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AccountRepository.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EntityManager entityManager;

    @DisplayName("중복되는 이메일 조회 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1653@gmail.com,1"})
    void 중복_이메일_조회(String email, int size) {
        // given, when
        List<Account> foundEmails = accountRepository.findDuplicatedEmail(email);

        // then
        assertThat(foundEmails.size()).isEqualTo(size);
    }

    @DisplayName("중복되지 않는 이메일 조회")
    @ParameterizedTest
    @CsvSource({"guswns1700@gmail.com,0"})
    void 비중복_이메일_조회(String email, int size) {
        // given, when
        List<Account> foundEmails = accountRepository.findDuplicatedEmail(email);

        // then
        assertThat(foundEmails.size()).isEqualTo(size);
    }
}
