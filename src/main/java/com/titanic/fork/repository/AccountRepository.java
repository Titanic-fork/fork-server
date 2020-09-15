package com.titanic.fork.repository;

import com.titanic.fork.domain.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager entityManager;

    public long save(Account account) {
        entityManager.persist(account);
        return account.getId();
    }

    /*
     * getSingleResult할 때 0일 경우 원래 에러가 발생하는데 Optional로 하면 null로 들어가면 findByEmail로 통일해도 된다.
     */
    public List<Account> findByEmail(String email) {
        return entityManager.createQuery("select a from Account a where a.email = :email", Account.class)
                .setParameter("email", email)
                .getResultList();
    }

//    public Optional<Account> findByEmail(String email) {
//        return Optional.ofNullable(entityManager.createQuery("select a from Account a where a.email = :email", Account.class)
//                .setParameter("email", email)
//                .getSingleResult());
//    }
}
