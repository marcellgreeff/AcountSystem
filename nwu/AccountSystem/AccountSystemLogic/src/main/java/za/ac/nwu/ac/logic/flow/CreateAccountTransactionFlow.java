package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;

import java.io.Serializable;
import java.time.LocalDate;

public interface CreateAccountTransactionFlow {

    AccountTransactionDto create(AccountTransactionDto accountTransaction);

    void create(Long accountTransactionId, Long miles, Boolean date, LocalDate currentDate);
}
