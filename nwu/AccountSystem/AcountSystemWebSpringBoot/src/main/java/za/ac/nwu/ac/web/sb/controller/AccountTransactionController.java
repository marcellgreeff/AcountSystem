package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTransactionFlow;
import za.ac.nwu.ac.logic.flow.impl.CreateAccountTransactionFlowImpl;

import java.util.List;

@RestController
@RequestMapping("account-transaction")
public class AccountTransactionController {

    private final FetchAccountTransactionFlow fetchAccountTransactionFlow;

    @Autowired
    public AccountTransactionController(FetchAccountTransactionFlow fetchAccountTransactionFlow){
        this.fetchAccountTransactionFlow = fetchAccountTransactionFlow;
    }

    @GetMapping("All")
    @ApiOperation(value =  "Gets all the configured Account Types.", notes = "Return a list of account types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAll() {
        List<AccountTransactionDto> accountTransaction = fetchAccountTransactionFlow.getAllAccountTransactions();
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<>(true, accountTransaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("accountTypeId")
    @ApiOperation(value =  "Fetches the specified Account Transactions.", notes = "Fetches the AccountTransactions corresponding to the given AccountType Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "AccountTransaction Found", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<List<AccountTransactionDto>>> getAccountTransaction(
        @ApiParam(value = "The accountTypeId that uniquely identifies the AccountTransaction.",
                example = "50002",
                name = "accountTypeId",
                required = true)
        @RequestParam("accountTypeId") final Long accountTypeId){

        List<AccountTransactionDto> accountTransactionDto = fetchAccountTransactionFlow.getAccountTransactionById(accountTypeId);
        GeneralResponse<List<AccountTransactionDto>> response = new GeneralResponse<>(true, accountTransactionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
