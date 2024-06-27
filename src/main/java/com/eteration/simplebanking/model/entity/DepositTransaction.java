package com.eteration.simplebanking.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Deposit")
public class DepositTransaction extends Transaction {
    public DepositTransaction() {
        super();
    }

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Deposit of " + getAmount() + " on " + getDate();
    }

    @Override
    public String getTransactionType() {
        return "DepositTransaction";
    }
}
