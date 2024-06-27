package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.dto.TransactionStatus;
import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.DepositTransaction;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.entity.WithdrawalTransaction;
import com.eteration.simplebanking.services.BankAccountService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests {

    @Spy
    @InjectMocks
    private AccountController controller;

    @Mock
    private BankAccountService service;

    @Test
    public void givenId_Credit_thenReturnJson() throws Exception {
        BankAccount bankAccount = new BankAccount("Kerem Karaca", "17892");
        doReturn(bankAccount).when(service).findAccount("17892");
        ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
        verify(service, times(1)).findAccount("17892");
        assertEquals("OK", result.getBody().getStatus());
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson() throws Exception {
        BankAccount bankAccount = new BankAccount("Kerem Karaca", "17892");
        doReturn(bankAccount).when(service).findAccount("17892");
        ResponseEntity<TransactionStatus> result = controller.credit("17892", new DepositTransaction(1000.0));
        ResponseEntity<TransactionStatus> result2 = controller.debit("17892", new WithdrawalTransaction(50.0));
        verify(service, times(2)).findAccount("17892");
        assertEquals("OK", result.getBody().getStatus());
        assertEquals("OK", result2.getBody().getStatus());
    }

    @Test
    public void givenId_GetAccount_thenReturnJson() throws Exception {
        BankAccount bankAccount = new BankAccount("Kerem Karaca", "17892");
        doReturn(bankAccount).when(service).findAccount("17892");
        ResponseEntity<BankAccount> result = controller.findAccount("17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(bankAccount, result.getBody());
    }
}
