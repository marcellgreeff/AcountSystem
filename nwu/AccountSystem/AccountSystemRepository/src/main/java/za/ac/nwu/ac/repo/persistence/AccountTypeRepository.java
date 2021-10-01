package za.ac.nwu.ac.repo.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

@Query(value = "SELECT" +
    " ACCOUNT_TYPE_ID," +
    " ACCOUNT_NAME," +
    " CREATION_DATE," +
    " MNEMONIC" +
    " FROM" +
    " MARCELL.ACCOUNT_TYPE" +
    " WHERE MNEMONIC = :mnemonic ", nativeQuery = true)
    AccountType getAccountTypeByMnemonicNativeQuery(String mnemonic);

@Query(value = "SELECT" +
    " at" +
    " FROM" +
    " ACCOUNT_TYPE at" +
    " WHERE at.MNEMONIC = :MNEMONIC")
AccountType getAccountTypeByMnemonic(String mnemonic);

@Query(value = "SELECT new za.ac.nwu.ac.domain.dto.AccountTypeDto( " +
        "at.MNEMONIC," +
        "at.ACCOUNT_NAME, " +
        "at.CREATION_DATE, " +
        "FROM " +
        "ACCOUNT_TYPE at " +
        "WHERE at.MNEMONIC = :MNEMONIC ")
AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);

    AccountType getAccountTypeDbByMnemonic(String mnemonic);
}
