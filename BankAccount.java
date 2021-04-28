package FinalProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class BankAccount {
    protected static int accountsCreated;
    protected String name;
    protected double balance;
    protected int acctNum;
    protected Date date;

    public BankAccount(String name) {
        this.name = name;
        acctNum = generateAcctNum();
        date = new Date();
        accountsCreated++;
    }

    public BankAccount(String name, double balance, int acctNum, String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.name = name;
        this.balance = balance;
        this.acctNum = acctNum;
        this.date = format.parse(date);
    }

    public BankAccount() {

    }

    public static int getAccountsCreated() {
        return accountsCreated;
    }

    public abstract void updateAccount();

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return acctNum;
    }

    public boolean deposit(double amt) {
        if (amt < 0) return false;
        this.balance += amt;
        return true;
    }

    public boolean withdraw(double amt) {
        if (amt < 0 || amt > this.balance) return false;
        this.balance -= amt;
        return true;
    }

    public boolean transfer(BankAccount that, double amt) {
        if (that == null || amt < 0 || amt > this.balance) return false;
        this.balance -= amt;
        that.balance += amt;
        return true;
    }

 /*   public boolean equals(sample.BankAccount that) {
        return this.acctNum == that.acctNum;
    }
*/

    public String toString() {
        return name + " [" + acctNum + "]\n" + date + "\n" + String.format("$%,.2f", balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return acctNum == that.acctNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(acctNum);
    }

    public int compareTo(BankAccount that) {
        return this.acctNum - that.acctNum;
    }

    private int generateAcctNum() {
        return (int) (Math.random() * 900000000) + 100000000;
    }

    public abstract String toText();

}