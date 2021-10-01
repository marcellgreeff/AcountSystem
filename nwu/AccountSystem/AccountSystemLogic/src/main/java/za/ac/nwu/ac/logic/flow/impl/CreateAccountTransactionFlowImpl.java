package za.ac.nwu.ac.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;
import za.ac.nwu.ac.translator.AccountTransactionTranslator;

import javax.transaction.Transactional;

@Transactional
@Component
public class CreateAccountTransactionFlowImpl implements CreateAccountTransactionFlow {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountTransactionFlowImpl.class);

    private final AccountTransactionTranslator accountTransactionTranslator;
    private final AccountTransactionDetailsTranslator accountTransactionDetailsTranslator;
    private final FetchAccountTypeFlow fetchAccountTypeFlow;

    public CreateAccountTransactionFlowImpl(AccountTransactionTranslator accountTransactionTranslator,
                                            AccountTransactionDetailsTranslator accountTransactionDetailsTranslator,
                                            FetchAccountTypeFlow fetchAccountTypeFlow){
        this.accountTransactionTranslator = accountTransactionTranslator;
        this.accountTransactionDetailsTranslator = accountTransactionDetailsTranslator;
        this.fetchAccountTypeFlow = fetchAccountTypeFlow;
    }

    @Override
    public AccountTransactionDto create(AccountTransactionDto accountTransactionDto) {
        if(LOGGER.isDebugEnabled()) {
            String outputForLogging = "null";
            if((null != accountTransactionDto) && (null != accountTransactionDto.getDetails())){
                outputForLogging = accountTransactionDto.getDetails().toString();
            }
            LOGGER.debug("The input object was {} and the Details is {}", accountTransactionDto, outputForLogging);
        }

        accountTransactionDto.setTransactionId(null);

        AccountType accountType = fetchAccountTypeFlow.getAccountTypeDbEntityByMnemonic(
                accountTransactionDto.getAccountTypeMnemonic());
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Got AccountType for {} and the AccountTypeID {}", accountTransactionDto.getAccountTypeMnemonic(), accountType.getAccountTypeId());
        }

        AccountTransaction accountTransaction = accountTransactionDto.buildAccountTransaction(accountType);
        AccountTransaction createdAccountTransaction = accountTransactionTranslator.save(accountTransaction);

        if(null != accountTransactionDto.getDetails()){
            AccountTransactionDetails accountTransactionDetails = accountTransactionDto.getDetails().buildAccountTransactionDetails(createdAccountTransaction);
            createdAccountTransaction.setDetails(accountTransactionDetails);
            accountTransactionDetailsTranslator.save(createdAccountTransaction.getDetails());
        }

        AccountTransactionDto results = new AccountTransactionDto(createdAccountTransaction);
        LOGGER.info("The return object is {}", results);
        return results;
    }
}
