package atm;

import java.util.List;

public interface Transaction {
	public boolean hasErrors();

	public List<String> errors();

	public double newBalance();
}
