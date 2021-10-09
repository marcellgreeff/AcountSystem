package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.persistence.AccountTransaction;

import java.time.LocalDate;
import java.util.List;

public interface AccountTransactionTranslator {
    AccountTransaction save(AccountTransaction accountTransaction);
    List<AccountTransactionDto> getAllAccountTransactions();

    void create(Long accountTransactionId, Long miles);

    Integer deleteAccountTransactionByAccountId(Long accountTypeId);

    List<AccountTransactionDto> getAccountTransactionById(Long accountTypeId);
}
