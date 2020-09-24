package com.titanic.fork.repository.account;

import com.titanic.fork.domain.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager entityManager;
    final String findEmailQuery = "select a from Account a where a.email = :email";

    public long save(Account account) {
        entityManager.persist(account);
        return account.getId();
    }

    public List<Account> findDuplicatedEmail(String email) {
        return entityManager.createQuery(findEmailQuery, Account.class)
                .setParameter("email", email)
                .getResultList();
    }

    public Account findByEmail(String email) {
        return entityManager.createQuery(findEmailQuery, Account.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
