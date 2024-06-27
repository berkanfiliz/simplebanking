package com.eteration.simplebanking.model.strategy;

import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.CheckTransaction;
import com.eteration.simplebanking.model.entity.Transaction;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;

public class CheckTransactionStrategy implements TransactionStrategy {
    @Override
    public void execute(BankAccount account, Transaction transaction) throws InsufficientBalanceException {
        CheckTransaction checkTransaction = (CheckTransaction) transaction;
        account.debit(checkTransaction.getAmount());
    }
}
