package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.util.List;

public interface AccountTypeTranslator {

    List<AccountTypeDto> getAllAccountTypes();

    AccountTypeDto create(AccountTypeDto accountType);

    AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic);

    AccountTypeDto getAccountTypeByMnemonic(String mnemonic);

    AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic);

    AccountTypeDto save(AccountType accountType);

    AccountType getAccountTypeDbByMnemonic(String mnemonic);

    Integer update(String mnemonic, Long miles);

    void someMethod();

    AccountTypeDto delete(String mnemonic);

    Integer deleteAccountTypeByMnemonic(String mnemonic);
}
