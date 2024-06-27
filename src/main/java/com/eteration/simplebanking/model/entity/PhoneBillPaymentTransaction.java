package com.eteration.simplebanking.model.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("PhoneBillPayment")
public class PhoneBillPaymentTransaction extends Transaction {

    @NotNull(message = "Phone number is not null")
    @NotBlank(message = "Phone number is not blank")
    private String phoneNumber;

    public PhoneBillPaymentTransaction() {
        super();
    }

    public PhoneBillPaymentTransaction(double amount, String phoneNumber) {
        super(amount);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone Bill Payment of " + getAmount() + " to " + getPhoneNumber() + " on " + getDate();
    }

    @Override
    public String getTransactionType() {
        return "PhoneBillPaymentTransaction";
    }
}
