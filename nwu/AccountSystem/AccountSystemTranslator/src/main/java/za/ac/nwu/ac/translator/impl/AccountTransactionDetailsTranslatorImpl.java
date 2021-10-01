package za.ac.nwu.ac.translator.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.persistence.AccountTransactionDetails;
import za.ac.nwu.ac.repo.persistence.AccountTransactionDetailsRepository;
import za.ac.nwu.ac.translator.AccountTransactionDetailsTranslator;

@Component
public class AccountTransactionDetailsTranslatorImpl implements AccountTransactionDetailsTranslator {

    private AccountTransactionDetailsRepository repo;

    public AccountTransactionDetailsTranslatorImpl(AccountTransactionDetailsRepository accountTransactionDetailsRepository){
        this.repo = accountTransactionDetailsRepository;
    }


    @Override
    public AccountTransactionDetails save(AccountTransactionDetails accountTransactionDetails) {
        try {
            return repo.save(accountTransactionDetails);
        }catch (Exception e){
            throw new RuntimeException("Unable to save to the DB", e);
        }
    }
}
