package atm;

import java.util.List;

public class Deposit implements Transaction {

    @Override
    public boolean hasErrors() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> errors() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double newBalance() {
        // TODO Auto-generated method stub
        return 0;
    }

}
