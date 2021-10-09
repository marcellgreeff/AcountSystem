package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class ModifyAccountTypeFlowImpl implements ModifyAccountTypeFlow {

    private final AccountTypeTranslator accountTypeTranslator;
    private final CreateAccountTransactionFlow createAccountTransactionFlow;
    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final AccountTransactionTranslator accountTransactionTranslator;

    public ModifyAccountTypeFlowImpl(AccountTypeTranslator accountTypeTranslator, CreateAccountTransactionFlow createAccountTransactionFlow, FetchAccountTypeFlow fetchAccountTypeFlow, AccountTransactionTranslator accountTransactionTranslator) {
        this.createAccountTransactionFlow = createAccountTransactionFlow;
        this.accountTypeTranslator = accountTypeTranslator;
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
        this.accountTransactionTranslator = accountTransactionTranslator;
    }

    @Override
    public Integer update(String mnemonic, Long miles, Boolean date, LocalDate currentDate) {
       Long accountTransactionId = fetchAccountTypeFlow.getIdByMnemonic(mnemonic);
       accountTypeTranslator.update(mnemonic, miles);
       createAccountTransactionFlow.create(accountTransactionId, miles, date, currentDate);
       return 1;
    }

    @Override
    public Integer deleteAccountTypeByMnemonic(String mnemonic) {
        if (mnemonic.equals("")) {
            System.out.println("The mnemonic does not exist!");
        }else{
            try {
                Long accountTypeId = fetchAccountTypeFlow.getIdByMnemonic(mnemonic);
                if (accountTypeId != null) {
                    accountTransactionTranslator.deleteAccountTransactionByAccountId(accountTypeId);
                }
                accountTypeTranslator.deleteAccountTypeByMnemonic(mnemonic);
            } catch (Exception e) {
                throw new RuntimeException("Something went wrong deleting the AccountType AND AccountTransactions", e);
            }
        }
        return 1;
    }


}
