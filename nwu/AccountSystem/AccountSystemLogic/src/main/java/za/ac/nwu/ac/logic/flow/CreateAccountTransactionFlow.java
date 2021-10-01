package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;

import java.io.Serializable;

public interface CreateAccountTransactionFlow {

    AccountTransactionDto create(AccountTransactionDto accountTransaction);
}
