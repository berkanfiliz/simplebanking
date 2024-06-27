package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.entity.BankAccount;
import com.eteration.simplebanking.model.entity.DepositTransaction;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.entity.WithdrawalTransaction;

import com.eteration.simplebanking.model.strategy.DepositTransactionStrategy;
import com.eteration.simplebanking.model.strategy.TransactionStrategy;
import com.eteration.simplebanking.model.strategy.WithdrawalTransactionStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {

	@Test
	public void testCreateAccountAndSetBalance0() {
		BankAccount account = new BankAccount("Kerem Karaca", "17892");
		assertTrue(account.getOwner().equals("Kerem Karaca"));
		assertTrue(account.getAccountNumber().equals("17892"));
		assertTrue(account.getBalance() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() {
		BankAccount account = new BankAccount("Demet Demircan", "9834");
		account.credit(100);
		assertTrue(account.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		BankAccount account = new BankAccount("Demet Demircan", "9834");
		account.credit(100);
		assertTrue(account.getBalance() == 100);
		account.debit(50);
		assertTrue(account.getBalance() == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows(InsufficientBalanceException.class, () -> {
			BankAccount account = new BankAccount("Demet Demircan", "9834");
			account.credit(100);
			account.debit(500);
		});
	}

	@Test
	public void testTransactions() throws InsufficientBalanceException {
		// Create account
		BankAccount account = new BankAccount("Canan Kaya", "1234");
		assertTrue(account.getTransactions().size() == 0);

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction(100);
		TransactionStrategy strategyDeposit = new DepositTransactionStrategy();

		assertTrue(depositTrx.getDate() != null);
		account.post(depositTrx, strategyDeposit);
		assertTrue(account.getBalance() == 100);
		assertTrue(account.getTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
		TransactionStrategy strategyDrawal = new WithdrawalTransactionStrategy();

		assertTrue(withdrawalTrx.getDate() != null);
		account.post(withdrawalTrx,strategyDrawal);
		assertTrue(account.getBalance() == 40);
		assertTrue(account.getTransactions().size() == 2);
	}
}
