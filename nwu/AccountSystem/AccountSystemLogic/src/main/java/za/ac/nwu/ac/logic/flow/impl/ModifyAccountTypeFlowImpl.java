package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {

    private final AccountTypeTranslator accountTypeTranslator;
    private final CreateAccountTransactionFlow createAccountTransactionFlow;
    private final FetchAccountTypeFlow fetchAccountTypeFlow;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator, CreateAccountTransactionFlow createAccountTransactionFlow, FetchAccountTypeFlow fetchAccountTypeFlow) {
        this.createAccountTransactionFlow = createAccountTransactionFlow;
        this.accountTypeTranslator = accountTypeTranslator;
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
    }

    @Override
    public Integer update(String mnemonic, Long miles) {
       Long accountTransactionId = fetchAccountTypeFlow.getIdByMnemonic(mnemonic);
       accountTypeTranslator.update(mnemonic, miles);
       createAccountTransactionFlow.create(accountTransactionId, miles);
       return 1;
    }

    @Override
    public Integer deleteAccountTypeByMnemonic(String mnemonic) {
       accountTypeTranslator.deleteAccountTypeByMnemonic(mnemonic);
       return 1;
    }


}
