package atm;

public class Account {
	private final String owner;
	private double balance;

	public Account(String owner) {
		this(owner, 0);
	}

	public Account(String owner, double initialBalance) {
		this.owner = owner;
		this.balance = initialBalance;
	}

	public double balance() {
		return balance;
	}

	public String owner() {
		return owner;
	}

	public Withdraw withdraw(double amount) {
		return new Withdraw(balance, amount);
	}

}
