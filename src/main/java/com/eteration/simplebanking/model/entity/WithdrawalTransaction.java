package com.eteration.simplebanking.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Withdrawal")
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction() {
        super();
    }

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Withdrawal of " + getAmount() + " on " + getDate();
    }

    @Override
    public String getTransactionType() {
        return "WithdrawalTransaction";
    }
}
