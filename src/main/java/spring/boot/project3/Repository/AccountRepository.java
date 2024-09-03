package spring.boot.project3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.project3.Model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);

    @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId")
    List<Account> findAccountsByCustomerId(Integer customerId);
}
