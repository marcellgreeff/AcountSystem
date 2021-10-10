package za.ac.nwu.ac.web.sb.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import za.ac.nwu.ac.domain.dto.AccountTransactionDto;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.logic.flow.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountTransactionControllerTest {
    private static final String APP_URL = "/account-system/mvc";
    private static final String ACCOUNT_TRANSACTION_CONTROLLER_URL = APP_URL +
            "/account-transaction";
    @Mock
    private FetchAccountTransactionFlow fetchAccountTransactionFlowFlow;
    @Mock
    private CreateAccountTransactionFlow createAccountTransactionFlow;
    @InjectMocks
    private  AccountTransactionController controller;
    private MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void getAll() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":[{\"transactionId\":10,\"accountTypeMnemonic\":\"Miles\",\"amount\":50,\"transactionDate\":[2021,5,1]},{\"transactionId\":15,\"accountTypeMnemonic\":\"MILES\",\"amount\":55,\"transactionDate\":[2021,6,1]}]}";
        List<AccountTransactionDto> accountTransactions = new ArrayList<>();
        accountTransactions.add(new AccountTransactionDto(10L, "Miles", 50L,  LocalDate.parse("2021-05-01")));
        accountTransactions.add(new AccountTransactionDto(15L, "MILES", 55L,  LocalDate.parse("2021-06-01")));
        when(fetchAccountTransactionFlowFlow.getAllAccountTransactions()).thenReturn(accountTransactions);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", ACCOUNT_TRANSACTION_CONTROLLER_URL, "All")))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTransactionFlowFlow, times(1)).getAllAccountTransactions();
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }
/*

    @Test
    public void getAccountType() throws Exception {
        String expectedResponse = "{\"successful\":true,\"payload\":[{\"transactionId\":10,\"accountTypeMnemonic\":\"Miles\",\"amount\":50,\"transactionDate\":[2021,5,1]}}";
        AccountTransactionDto accountTransactions = new AccountTransactionDto(10L, "Miles", 50L,  LocalDate.parse("2021-05-01"));
        when(fetchAccountTransactionFlowFlow.getAccountTransactionById(anyString())).thenReturn(accountTransactions);
        MvcResult mvcResult = mockMvc.perform(get((String.format("%s/%s", ACCOUNT_TRANSACTION_CONTROLLER_URL, 10L)))
                        .servletPath(APP_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        verify(fetchAccountTransactionFlowFlow, times(1)).getAccountTransactionById(eq(10L));
        assertEquals(expectedResponse,
                mvcResult.getResponse().getContentAsString());
    }
*/

    }