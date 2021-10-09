package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Component
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);
    private final AccountTransactionTranslator accountTransactionTranslator;
    private final FetchAccountTypeFlow fetchAccountTypeFlow;

    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator,
                                            FetchAccountTypeFlow fetchAccountTypeFlow){
        this.accountTransactionTranslator = accountTransactionTranslator;
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto) {

        accountTransactionDto.setTransactionId(null);
        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(
                accountTransactionDto.getAccountTypeMnemonic());
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Got AccountType for {} and the AccountTypeID {}", accountTransactionDto.getAccountTypeMnemonic(), accountType.getAccountTypeId());
        }
        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType);
        AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);
        AccountTransactionDto results = new AccountTransactionDto(createdAccountTransaction);
        LOGGER.info("The return object is {}", results);
        return results;
    }

    @Override
    public void create(Long accountTransactionId, Long miles, Boolean date, LocalDate currentDate) {
        accountTransactionTranslator.create(accountTransactionId, miles, date, currentDate);
    }
}
