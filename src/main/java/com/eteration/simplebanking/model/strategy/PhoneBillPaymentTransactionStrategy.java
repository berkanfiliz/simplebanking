package com.eteration.simplebanking.model.strategy;

import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.entity.Transaction;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;

public class PhoneBillPaymentTransactionStrategy implements TransactionStrategy {
    @Override
    public void execute(BankAccount account, Transaction transaction) throws InsufficientBalanceException {
        PhoneBillPaymentTransaction phoneBillPaymentTransaction = (PhoneBillPaymentTransaction) transaction;
        account.debit(phoneBillPaymentTransaction.getAmount());
    }
}
