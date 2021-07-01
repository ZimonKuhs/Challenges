package atm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Withdraw implements Transaction {
	private List<String> errors;
	private double newBalance;

	public Withdraw(double balance, double amount) {
		this.errors = Collections.unmodifiableList(collectErrors(balance, amount));
		if (errors.isEmpty()) {
			this.newBalance = balance - amount - Constants.TRANSACTION_FEE;
			++Constants.NUMBER_OF_TRANSACTIONS;
		} else {
			newBalance = balance;
		}
	}

	private List<String> collectErrors(double balance, double amount) {
		List<String> errors = new ArrayList<String>();

		if (amount <= 0) {
			errors.add("No funds on account.");
		} else if (amount > Constants.MAX_WITHDRAW) {
			errors.add("Requested amount exceeds withdraw limit of " + Constants.MAX_WITHDRAW + ".");
		} else if (amount > balance) {
			errors.add("Insufficient funds, current balance is " + balance + ".");
		} else if (amount < Constants.MIN_WITHDRAW) {
			errors.add("Can not withdraw less than " + Constants.MIN_WITHDRAW + ".");
		}

		return errors;
	}

	public double newBalance() {
		return newBalance;
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public List<String> errors() {
		return errors;
	}

}
