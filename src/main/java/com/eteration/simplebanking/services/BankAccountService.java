package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.entity.*;
import com.eteration.simplebanking.model.exception.AccountNotFoundException;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.strategy.*;
import com.eteration.simplebanking.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public String credit(BankAccount account, double amount) throws InsufficientBalanceException {
            TransactionStrategy strategy = new DepositTransactionStrategy();
            String approvalCode = account.post(new DepositTransaction(amount), strategy);
            bankAccountRepository.save(account);
            return approvalCode;
    }

    @Transactional
    public String debit(BankAccount account, double amount) throws InsufficientBalanceException {
        try {
            TransactionStrategy strategy = new WithdrawalTransactionStrategy();
            String approvalCode = account.post(new WithdrawalTransaction(amount), strategy);
            bankAccountRepository.save(account);
            return approvalCode;
        } catch (InsufficientBalanceException e) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal");
        }
    }

    public BankAccount findAccount(String accountNumber) throws AccountNotFoundException {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new NullPointerException("Account number cannot be null or empty");
        }
        Optional<BankAccount> account = bankAccountRepository.findByAccountNumber(accountNumber);
        return account.orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public BankAccount createAccount(String owner, String accountNumber) {
        BankAccount newAccount = new BankAccount(owner, accountNumber);
        return bankAccountRepository.save(newAccount);
    }

    @Transactional
    public String billPay(BankAccount account, String payee, double amount) throws InsufficientBalanceException {
        try {
            TransactionStrategy strategy = new BillPaymentTransactionStrategy();
            String approvalCode = account.post(new BillPaymentTransaction(payee, amount), strategy);
            bankAccountRepository.save(account);
            return approvalCode;
        } catch (InsufficientBalanceException e) {
            throw new InsufficientBalanceException("Insufficient balance for bill payment");
        }
    }

    @Transactional
    public String phoneBillPay(BankAccount account, String phoneNumber, double amount) throws InsufficientBalanceException {
        try {
            TransactionStrategy strategy = new PhoneBillPaymentTransactionStrategy();
            String approvalCode = account.post(new PhoneBillPaymentTransaction(amount, phoneNumber), strategy);
            bankAccountRepository.save(account);
            return approvalCode;
        } catch (InsufficientBalanceException e) {
            throw new InsufficientBalanceException("Insufficient balance for phone bill payment");
        }

    }
    @Transactional
    public String checkPay(BankAccount account, String checkNumber, double amount) throws InsufficientBalanceException {
        try {
            TransactionStrategy strategy = new CheckTransactionStrategy();
            String approvalCode = account.post(new CheckTransaction(amount, checkNumber), strategy);
            bankAccountRepository.save(account);
            return approvalCode;
        } catch (InsufficientBalanceException e) {
            throw new InsufficientBalanceException("Insufficient balance for check payment");
        }
    }
}