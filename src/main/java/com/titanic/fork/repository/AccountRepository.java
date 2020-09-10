package com.titanic.fork.repository;

import com.titanic.fork.domain.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager entityManager;

    public long save(Account account) {
        entityManager.persist(account);
        return account.getId();
    }
}
