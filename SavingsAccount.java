package FinalProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String name, double rate) {
        super(name);
        if (rate > 0 && rate < 0.1) interestRate = rate;
        else interestRate = .01;
    }

    public SavingsAccount(String[] data) throws ParseException {
        super(data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), data[4]);
        interestRate = Double.parseDouble(data[5]);
    }

    public double getRate() {
        return interestRate;
    }

    public void addInterest() {
        balance *= (1.0 + interestRate);
    }

    @Override
    public void updateAccount() {
        addInterest();
    }

    public String toString() {
        return "Savings Account\n" + super.toString() + "\nInterest Rate:\t%"
                + String.format("%.3f", 100 * interestRate);
    }

    @Override
    public String toText() {

        return "SAVINGS\t" + name + "\t" + balance + "\t" + acctNum + "\t" + (new SimpleDateFormat("yyyy-MM-dd").format(date)) + "\t" + interestRate;
    }
}
