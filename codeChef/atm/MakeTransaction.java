package atm;

import program.ProgramUtility;
import type.StringUtility;

public class MakeTransaction {
	
	public static void main(String[] args) {
		if (args.length != 3) {
			ProgramUtility.error("Usage: java atm.MakeTransaction [deposit | withdraw] [amount] [initial balance]");
		}
		
		String error = "";
		if (!StringUtility.isInteger(args[1])) {
			error = error + "Transaction amount has to be an integer: " + args[1] + ".\n";
		}
		if (!StringUtility.isFloat(args[2])) {
			error = error + "Balance amount has to be a floating point number: " + args[2] + ".\n";
		}
		if (error.length() > 0) {
			ProgramUtility.error(error.substring(0, error.length() - 1));
		}
		
		int amount = Integer.parseInt(args[1]);
		double balance = Double.parseDouble(args[2]);
		
		Transaction transaction = null;
		String operation = args[0].toLowerCase();

		if (operation.equals("deposit")) {
			ProgramUtility.error("The \"deposit\" operation is not yet implemented.");
		} else if (operation.equals("withdraw")) {
			transaction = new Withdraw(balance, amount);
		} else {
			ProgramUtility.error("No such operation possible: " + operation + ".");
		}
		
		if (transaction.hasErrors()) {
			for (String transactionError : transaction.errors()) {
				System.out.println(balance);
				// System.out.println(transactionError);
			}
			System.exit(0);
		}
		
		System.out.println("Transaction successful. New balance: " + transaction.newBalance() + ".");
	}
	
	private MakeTransaction() {
		/*
		 * Main class should be run, not instantiated.
		 */
	}
}
