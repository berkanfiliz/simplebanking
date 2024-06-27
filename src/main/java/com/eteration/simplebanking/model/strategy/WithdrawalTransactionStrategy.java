package com.eteration.simplebanking.model.strategy;

import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.Transaction;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;

public class WithdrawalTransactionStrategy implements TransactionStrategy {
    @Override
    public void execute(BankAccount account, Transaction transaction) throws InsufficientBalanceException {
        account.debit(transaction.getAmount());
    }
}

