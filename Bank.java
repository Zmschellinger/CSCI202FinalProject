package FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Bank {
    private BankAccount[] accounts;
    private int count;

    public Bank(int cap) {
        accounts = new BankAccount[cap];
        count = 0;
    }

    public boolean add(BankAccount a) {
        if (contains(a)) return false;
        if (full()) doubleCapacity();
        accounts[count++] = a;
        return true;
    }

    public boolean remove(BankAccount a) {
        if (!contains(a)) return false;
        accounts[indexOf(a)] = accounts[--count];
        return true;
    }

    public boolean contains(BankAccount a) {
        return indexOf(a) != -1;
    }


    public BankAccount find(int acct) {
        for (int i = 0; i < count; i++)
            if (accounts[i].getAccountNumber() == acct) return accounts[i];
        return null;
    }

    private int indexOf(BankAccount a) {
        if (a == null) return -1;
        for (int i = 0; i < count; i++)
            if (accounts[i].equals(a)) return i;
        return -1;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("sample.Bank Accounts\n");
        for (int i = 0; i < count; i++) {
            sb.append(accounts[i].toString());
            sb.append("\n    **************    \n");
        }
        return sb.toString();
    }

    public void sort() {
        for (int i = 1; i < count; i++) {
            BankAccount temp = accounts[i];
            int j;
            for (j = i - 1; j >= 0 && less(temp, accounts[j]); j--) {
                accounts[j + 1] = accounts[j];
            }
            accounts[j + 1] = temp;
        }
    }

    private void doubleCapacity() {
        BankAccount[] a = new BankAccount[accounts.length * 2];
        System.arraycopy(accounts, 0, a, 0, count);
        accounts = a;
    }

    private boolean less(BankAccount temp, BankAccount j) {
        return temp.getAccountNumber() < j.getAccountNumber();
    }

    private boolean full() {
        return count == accounts.length;
    }

    public int writeAccounts(String filename) throws FileNotFoundException {
        int count = 0;
        File file = new File(filename);
        PrintWriter out = new PrintWriter(file);
        for (BankAccount account : accounts) {
            out.println(account.toText());
            ++count;
        }
        out.close();
        return count;
    }


}


