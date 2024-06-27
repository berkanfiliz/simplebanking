package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.dto.BankAccountRequest;
import com.eteration.simplebanking.model.dto.TransactionStatus;
import com.eteration.simplebanking.model.entity.*;
import com.eteration.simplebanking.model.exception.AccountNotFoundException;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;
import com.eteration.simplebanking.services.BankAccountService;
import com.eteration.simplebanking.util.AccountNumberGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final BankAccountService accountService;

    public AccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> findAccount(@PathVariable String accountNumber) throws AccountNotFoundException {
        BankAccount account = accountService.findAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/create")
    public ResponseEntity<BankAccount> createAccount(@Valid @RequestBody BankAccountRequest request) {
        String accountNumber = AccountNumberGenerator.generateAccountNumber();
        BankAccount newAccount = accountService.createAccount(request.getOwner(), accountNumber);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber,@Valid @RequestBody DepositTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        BankAccount account = accountService.findAccount(accountNumber);
        String approvalCode = accountService.credit(account, transaction.getAmount());
        return ResponseEntity.ok(new TransactionStatus("OK", approvalCode));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber,@Valid @RequestBody WithdrawalTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        BankAccount account = accountService.findAccount(accountNumber);
        String approvalCode = accountService.debit(account, transaction.getAmount());
        return ResponseEntity.ok(new TransactionStatus("OK", approvalCode));
    }
    @PostMapping("/billpay/{accountNumber}")
    public ResponseEntity<TransactionStatus> billPay(@PathVariable String accountNumber,@Valid @RequestBody BillPaymentTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        BankAccount account = accountService.findAccount(accountNumber);
        String approvalCode = accountService.billPay(account, transaction.getPayee(), transaction.getAmount());
        return ResponseEntity.ok(new TransactionStatus("OK", approvalCode));
    }
    @PostMapping("/phonebillpay/{accountNumber}")
    public ResponseEntity<TransactionStatus> phoneBillPay(@PathVariable String accountNumber,@Valid @RequestBody PhoneBillPaymentTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        BankAccount account = accountService.findAccount(accountNumber);
        String approvalCode = accountService.phoneBillPay(account, transaction.getPhoneNumber(), transaction.getAmount());
        return ResponseEntity.ok(new TransactionStatus("OK", approvalCode));
    }
    @PostMapping("/checkpay/{accountNumber}")
    public ResponseEntity<TransactionStatus> checkPay(@PathVariable String accountNumber,@Valid @RequestBody CheckTransaction transaction) throws InsufficientBalanceException, AccountNotFoundException {
        BankAccount account = accountService.findAccount(accountNumber);
        String approvalCode = accountService.checkPay(account, transaction.getCheckNumber(), transaction.getAmount());
        return ResponseEntity.ok(new TransactionStatus("OK", approvalCode));
    }

}
