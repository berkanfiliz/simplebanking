package com.eteration.simplebanking.model.entity;

import com.eteration.simplebanking.model.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.strategy.TransactionStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class BankAccount {

    @Id
    @Column(name = "accountNumber")
    private String accountNumber;

    @NotNull(message = "Owner is not null")
    @NotBlank(message = "Owner is not blank")
    private String owner;
    private double balance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bankAccount")
    private List<Transaction> transactions;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public BankAccount() {
        this.transactions = new ArrayList<>();
    }

    public BankAccount(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String post(Transaction transaction, TransactionStrategy strategy) throws InsufficientBalanceException {
        transaction.setBankAccount(this);
        String approvalCode = UUID.randomUUID().toString();
        transaction.setApprovalCode(approvalCode);
        strategy.execute(this, transaction);
        transactions.add(transaction);
        return approvalCode;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) throws InsufficientBalanceException {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException("Insufficient balance for debit");
        }
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}