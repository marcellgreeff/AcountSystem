package za.ac.nwu.ac.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Component
public class FetchAccountTransactionFlowImpl implements FetchAccountTransactionFlow {

    private final AccountTransactionTranslator translator;
    public FetchAccountTransactionFlowImpl(AccountTransactionTranslator translator){ this.translator = translator; }

    @Override
    public List<AccountTransactionDto> getAllAccountTransactions() {
        return translator.getAllAccountTransactions();
    }
   /* public List<AccountTransactionDto> getAllAccountTransactions() {
        List<AccountTransactionDto> accountTransactionDtos = new ArrayList<>();
        for (AccountTransaction accountTransaction : translator.getAllAccountTransactions()){
            accountTransactionDtos.add(new AccountTransactionDto(accountTransaction));
        }
        return accountTransactionDtos;
    }*/

    @Override
    public List<AccountTransactionDto> getAccountTransactionById(Long accountTypeId) {
        return translator.getAccountTransactionById(accountTypeId);
    }
}
