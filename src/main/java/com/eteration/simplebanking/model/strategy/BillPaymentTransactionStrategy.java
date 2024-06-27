package com.eteration.simplebanking.model.strategy;

import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.BillPaymentTransaction;
import com.eteration.simplebanking.model.entity.Transaction;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;

public class BillPaymentTransactionStrategy implements TransactionStrategy {
    @Override
    public void execute(BankAccount account, Transaction transaction) throws InsufficientBalanceException {
        BillPaymentTransaction billPaymentTransaction = (BillPaymentTransaction) transaction;
        account.debit(billPaymentTransaction.getAmount());
    }
}

