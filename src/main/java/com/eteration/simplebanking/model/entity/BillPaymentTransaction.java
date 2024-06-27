package com.eteration.simplebanking.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("BillPayment")
public class BillPaymentTransaction extends Transaction {

    @NotNull(message = "Payee is not null")
    @NotBlank(message = "Payee is not blank")
    private String payee;

    public BillPaymentTransaction() {
        super();
    }

    public BillPaymentTransaction(String payee, double amount) {
        super(amount);
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    @Override
    public String toString() {
        return "Bill payment to " + getPayee() + " of " + getAmount() + " on " + getDate();
    }

    @Override
    public String getTransactionType() {
        return "BillPaymentTransaction";
    }
}
