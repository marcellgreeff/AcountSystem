package za.ac.nwu.ac.repo.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

/*@Query(value = "SELECT" +
    " ACCOUNT_TYPE_ID," +
    " ACCOUNT_NAME," +
    " CREATION_DATE," +
    " MILES," +
    " MNEMONIC" +
    " FROM" +
    " MARCELL.ACCOUNT_TYPE" +
    " WHERE MNEMONIC = :mnemonic ", nativeQuery = true)
    AccountType getAccountTypeByMnemonicNativeQuery(String mnemonic);*/

    @Query(value = "SELECT" +
            " ACCOUNT_TYPE_ID" +
            " FROM" +
            " MARCELL.ACCOUNT_TYPE" +
            " WHERE MNEMONIC = :mnemonic ", nativeQuery = true)
    AccountType getAccountTypeByMnemonicNativeQuery(String mnemonic);


    @Query(value = "SELECT" +
            " at" +
            " FROM " +
            " AccountType at" +
            " WHERE at.mnemonic = :mnemonic")
    AccountType getAccountTypeByMnemonic(String mnemonic);

    /*@Query(value = "DELETE FROM ACCOUNT_TYPE WHERE MNEMONIC = :mnemonic", nativeQuery = true)*/
    @Query(value = "DELETE FROM ACCOUNT_TYPE WHERE MNEMONIC = :mnemonic",nativeQuery = true)
    @Modifying
    Integer deleteAccountTypeByMnemonic(@Param("mnemonic") String mnemonic);


/*@Query(value = "SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto( " +
        " at.mnemonic," +
        " at.accountTypeName, " +
        " at.creationDate )" +
        " FROM " +
        " AccountType at" +
        " WHERE at.mnemonic = :mnemonic ")*/
AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);

    AccountType getAccountTypeDbByMnemonic(String mnemonic);

    @Query(value = "SELECT" +
            " at" +
            " FROM " +
            " AccountType at" )
    List<AccountType> findAll();
/*
    @Query(value = "UPDATE ACCOUNT_TYPE set MILES = 100", nativeQuery = true)
    AccountTypeDto save(AccountTypeDto accountType);*/

   @Query(value = "UPDATE ACCOUNT_TYPE set MILES = :miles WHERE MNEMONIC = :mnemonic", nativeQuery = true)
   @Modifying
    Integer update(@Param("mnemonic") String mnenomic, @Param("miles") Long miles);
}
