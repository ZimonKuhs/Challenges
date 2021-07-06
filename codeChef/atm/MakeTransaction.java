package atm;

import java.util.ArrayList;
import java.util.List;

import program.ProgramUtility;
import type.StringUtility;

public class MakeTransaction {

    public static void main(String[] args) {
        if (args.length != 3) {
            ProgramUtility.error(
                    "Usage: java atm.MakeTransaction [deposit | withdraw] [amount] [initial balance]");
        }

        List<String> errors = new ArrayList<String>();
        if (!StringUtility.isPositiveInteger(args[1])) {
            errors.add("Transaction amount has to be a positive integer: "
                    + args[1] + ".");
        }
        if (!StringUtility.isPositiveFloat(args[2])) {
            errors.add(
                    "Balance amount has to be a positive floating point number: "
                            + args[2] + ".");
        }
        if (errors.size() > 0) {
            ProgramUtility.error(errors);
        }

        int amount = Integer.parseInt(args[1]);
        double balance = Double.parseDouble(args[2]);

        Transaction transaction = null;
        String operation = args[0].toLowerCase();

        if (operation.equals("deposit")) {
            ProgramUtility
                    .error("The \"deposit\" operation is not yet implemented.");
        } else if (operation.equals("withdraw")) {
            transaction = new Withdraw(balance, amount);
        } else {
            ProgramUtility
                    .error("No such operation exists: " + operation + ".");
        }

        if (transaction.hasErrors()) {
            ProgramUtility.error(transaction.errors());
        }

        System.out.println("Transaction successful. New balance: "
                + transaction.newBalance() + ".");
    }

    private MakeTransaction() {
        /*
         * Main class should be run, not instantiated.
         */
    }
}
