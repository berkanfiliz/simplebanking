package com.eteration.simplebanking.model.strategy;

import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.Transaction;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;

public interface TransactionStrategy {
    void execute(BankAccount account, Transaction transaction) throws InsufficientBalanceException;
}

