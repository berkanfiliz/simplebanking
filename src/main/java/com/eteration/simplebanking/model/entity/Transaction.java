package com.eteration.simplebanking.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction {

    @Id
    private String approvalCode;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @NotNull(message = "Amount is not null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNumber")
    @JsonIgnore
    private BankAccount bankAccount;

    public Transaction() {
        this.date = new Date();
    }

    public Transaction(double amount) {
        this();
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @JsonProperty("type")
    public abstract String getTransactionType();

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public abstract String toString();
}
