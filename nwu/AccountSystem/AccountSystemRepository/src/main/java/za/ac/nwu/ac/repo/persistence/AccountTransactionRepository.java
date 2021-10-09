package za.ac.nwu.ac.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    @Modifying
    @Query(value = "INSERT INTO ACCOUNT_TRANSACTION(ACCOUNT_TYPE_ID, AMOUNT, TX_DATE) VALUES(:accountTransactionId, :miles, (TO_DATE(:date,'dd/mm/yyyy')))", nativeQuery = true)
    void create(@Param("accountTransactionId") Long accountTransactionId, @Param("miles") Long miles, @Param("date") LocalDate now);

    @Modifying
    @Query(value = "DELETE FROM ACCOUNT_TRANSACTION WHERE ACCOUNT_TYPE_ID = :accountTypeId", nativeQuery = true)
    Integer deleteAccountTransactionById(@Param("accountTypeId") Long accountTypeId);

    @Query(value = "SELECT * FROM ACCOUNT_TRANSACTION WHERE ACCOUNT_TYPE_ID = :accountTypeId", nativeQuery = true)
    List<AccountTransaction> getAccountTransactionById(@Param("accountTypeId") Long accountTypeId);

    @Query(value = "SELECT * FROM ACCOUNT_TRANSACTION",nativeQuery = true)
    AccountTransaction getAllAccountTransactions();
}
