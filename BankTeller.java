package FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class BankTeller {

    // small sample.Bank initially holds four accounts.
    private static Bank bank = new Bank(4);

    public static void main(String[] args) {
        char command = 0;
        int acct = 0;
        String filename = "";
        Scanner input = new Scanner(System.in);
        printMenu();
        do {
            // ask a user to choose a command
            System.out.println("\nPlease enter a command or type ?");
            command = input.nextLine().toLowerCase().charAt(0);

            switch (command) {
                case 'a': // add an Account
                    int accountType = -1;
                    while (accountType < 1 || accountType > 2) {
                        System.out.print("\nEnter 1 for Savings Account or 2 for Checking Account: ");
                        accountType = Integer.parseInt(input.nextLine());
                    }
                    System.out.print("Enter account holder name: ");
                    String name = input.nextLine();

                    BankAccount a;
                    if (accountType == 1) {
                        System.out.print("Enter interest rate: ");
                        double r = Double.parseDouble(input.nextLine());
                        a = new SavingsAccount(name, r);
                    } else {
                        System.out.print("Enter starting check number: ");
                        int c = Integer.parseInt(input.nextLine());
                        a = new CheckingAccount(name, c);
                    }
                    if (bank.add(a)) System.out.print("\nsample.Bank Account successfully added.\n");
                    else System.out.print("sample.Bank Account not added. No duplicates please.\n");
                    break;
                case 'b': // remove a sample.BankAccount
                    System.out.print("\nEnter account number: ");
                    acct = Integer.parseInt(input.nextLine());
                    if (bank.remove(bank.find(acct)))
                        System.out.print("\nsample.Bank Account successfully removed.\n");
                    else System.out.print("sample.Bank Account not found. Cannot remove.\n");
                    break;
                case 'c': // display the BankAccounts
                    System.out.println(bank.toString());
                    break;
                case 'd': // count the BankAccounts
                    System.out.println("\nThere are " + bank.getCount() + " BankAccounts in the bank");
                    break;
                case 'e': // sort the BankAccounts
                    bank.sort();
                    System.out.println("Accounts sorted.");
                    break;
                case 'f': // update an account
                    System.out.print("\nEnter account number: ");
                    acct = Integer.parseInt(input.nextLine());
                    BankAccount b = bank.find(acct);
                    if (b == null) {
                        System.out.print("sample.Bank Account not found. Cannot update.\n");
                        break;
                    }

                    int action = -1;
                    while (action < 1 || action > 2) {
                        System.out.print("\nEnter 1 for deposit or 2 for withdraw:");
                        action = Integer.parseInt(input.nextLine());
                    }
                    System.out.println("Enter amount:");
                    double amt = Double.parseDouble(input.nextLine());
                    boolean result;
                    if (action == 1) result = b.deposit(amt);
                    else result = b.withdraw(amt);
                    if (result) System.out.println("Transaction successful");
                    else System.out.println("Transaction invalid");


                    SavingsAccount sa = new SavingsAccount("", 0);
                    CheckingAccount ca = new CheckingAccount("", 0);

                    if (b.getClass() == sa.getClass()) {
                        sa = (SavingsAccount) b;
                        sa.addInterest();
                        System.out.println("Savings Account Updated:\n" + sa.toString());
                    } else if (b.getClass() == ca.getClass()) {
                        ca = (CheckingAccount) b;
                        ca.writeCheck();
                        System.out.println("Checking Account Updated:\n" + ca.toString());
                    }
                    break;
                case 'h':
                    System.out.print("Please enter file name: ");
                    String fileOut = input.nextLine();
                    try {
                        System.out.println("Accounts have been exported" + bank.writeAccounts(fileOut));
                    } catch (FileNotFoundException e) {
                        System.err.println("Invalid input");
                    }
                    break;
                case 'g':
                    System.out.print("Please enter file name: ");
                    String fileIn = input.nextLine();
                    try {
                        System.out.println("Accounts have been imported" + readAccounts(fileIn));
                    } catch (FileNotFoundException e) {
                        System.err.println("File invaild");
                    }


                case '?':
                    printMenu();
                    break;
                case 'q':
                    System.out.println("GOOD BYE!");
                    break;
                default:
                    System.out.println("Invalid Input");

            }

        } while (command != 'q');

        input.close();
    }


    // this method prints out the menu to a user
    // we put it here to keep the main method clean.
    public static void printMenu() {
        System.out.print("\nsample.Bank Teller Options\n" + "-----------------------------------\n"
                + "a: add an account to the bank\n" + "b: remove an account from the bank\n"
                + "c: display the accounts in the bank\n" + "d: count the accounts in the bank\n"
                + "e: sort the accounts in the bank\n" + "f: update an account in the bank\n"
                + "?: display the menu again\n" + "q: quit this program\n\n");

    } // end of the printMenu method

    public static int readAccounts(String filename) throws FileNotFoundException {
        int count = 0;
        File nfile = new File(filename);
        Scanner sText = new Scanner(nfile);
        while (sText.hasNextLine()) {
            String data = sText.nextLine();
            try {
                bank.add(parseBankAccount(data));
                count++;
            } catch (BankFileFormatException | ParseException e) {
                System.out.println("line skipped");
            }
        }
        sText.close();
        return count;

    }

    public static BankAccount parseBankAccount(String data) throws BankFileFormatException, ParseException {
        String[] tokens = data.split("\t");
        if (tokens.length != 6)
            throw new BankFileFormatException("Account data format invalid");
        if (tokens[0].equals("SAVINGS")) {
            return new SavingsAccount(tokens);
        } else {
            return new CheckingAccount(tokens);
        }
    }
    public static String displayAccounts(){
        return bank.toString();
    }


}
