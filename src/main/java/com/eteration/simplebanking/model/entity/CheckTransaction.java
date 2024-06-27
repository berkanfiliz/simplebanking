package com.eteration.simplebanking.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("Check")
public class CheckTransaction extends Transaction {

    @NotNull(message = "Check number is not null")
    @NotBlank(message = "Check number is not blank")
    private String checkNumber;

    public CheckTransaction() {
        super();
    }

    public CheckTransaction(double amount, String checkNumber) {
        super(amount);
        this.checkNumber = checkNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    @Override
    public String toString() {
        return "Check of " + getAmount() + " with number " + getCheckNumber() + " on " + getDate();
    }

    @Override
    public String getTransactionType() {
        return "CheckTransaction";
    }
}
