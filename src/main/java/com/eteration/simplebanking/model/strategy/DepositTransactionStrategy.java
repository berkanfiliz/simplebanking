package com.eteration.simplebanking.model.strategy;

import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.Transaction;

public class DepositTransactionStrategy implements TransactionStrategy {
    @Override
    public void execute(BankAccount account, Transaction transaction) {
        account.credit(transaction.getAmount());
    }
}

