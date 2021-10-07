package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;

import java.time.LocalDate;

public interface ModifyAccountTypeFlow {

    Integer update(String mnemonic, Long miles);

   Integer deleteAccountTypeByMnemonic(String mnemonic);
}
