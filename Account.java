import java.io.*;
import java.util.*;

class Account {
    private String name;
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(String name, int accountNumber, int pin, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws IOException {
        if (amount > 0) {
            balance += amount;
            saveTransaction("Deposited: " + amount);
            System.out.println("Deposit Successful!");
        }
    }

    public void withdraw(double amount) throws IOException {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            saveTransaction("Withdrawn: " + amount);
            System.out.println("Withdrawal Successful!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public void showBalance() {
        System.out.println("Current Balance: " + balance);
    }

    private void saveTransaction(String message) throws IOException {
        FileWriter fw = new FileWriter("transactions_" + accountNumber + ".txt", true);
        fw.write(message + "\n");
        fw.close();
    }

    public void showTransactions() throws IOException {
        File file = new File("transactions_" + accountNumber + ".txt");
        if (!file.exists()) {
            System.out.println("No Transactions Yet.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        System.out.println("\n--- Transaction History ---");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    public String toFileString() {
        return name + "," + accountNumber + "," + pin + "," + balance;
    }
}
