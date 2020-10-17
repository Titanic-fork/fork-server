package com.titanic.fork.repository;

import com.titanic.fork.repository.accountGoal.AccountGoalRepository;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AccountGoalRepository.class)
public class AccountGoalRepositoryTest {
}
