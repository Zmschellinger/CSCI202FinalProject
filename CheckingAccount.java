package FinalProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckingAccount extends BankAccount {

    private int checkNumber;

    public CheckingAccount(String name, int cn) {
        super(name);
        if (cn > 0) checkNumber = cn;
        else checkNumber = 1000;
    }

    public CheckingAccount(String[] data) throws ParseException {
        super(data[1], Double.parseDouble(data[2]), Integer.parseInt(data[3]), data[4]);
        checkNumber = Integer.parseInt(data[5]);
    }

    public int getCheckNumber() {
        return checkNumber;
    }

    public void writeCheck() {
        checkNumber++;
    }

    @Override
    public void updateAccount() {
        writeCheck();
    }

    public String toString() {
        return "Checking Account\n" + super.toString() + "\nNext Check Number:\t#" + checkNumber;
    }

    @Override
    public String toText() {
        return "CHECKING\t" + name + "\t" + balance + "\t" + acctNum + "\t" + (new SimpleDateFormat("yyyy-MM-dd").format(date)) + "\t" + checkNumber;
    }
}
