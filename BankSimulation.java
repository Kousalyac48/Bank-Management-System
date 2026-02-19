import java.util.ArrayList;
import java.util.Scanner;

// Account Class
class Account {

    private String accountHolder;
    private int accountNumber;
    private double balance;
    private ArrayList<String> transactions;

    // Constructor
    public Account(String name, int accNo, double initialBalance) {
        this.accountHolder = name;
        this.accountNumber = accNo;
        this.balance = initialBalance;
        transactions = new ArrayList<>();
        transactions.add("Account Created with Balance: " + initialBalance);
    }

    // Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount);
            System.out.println("Deposit Successful!");
        } else {
            System.out.println("Invalid Deposit Amount!");
        }
    }

    // Withdraw Method
    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
            System.out.println("Withdrawal Successful!");
        } else {
            System.out.println("Insufficient Balance or Invalid Amount!");
        }
    }

    // Check Balance
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    // Transaction History
    public void showTransactions() {
        System.out.println("\n--- Transaction History ---");
        for (String t : transactions) {
            System.out.println(t);
        }
    }
}

// Main Class
public class BankSimulation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== BANK ACCOUNT SIMULATION =====");

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = sc.nextDouble();

        Account acc = new Account(name, accNo, initialBalance);

        int choice;

        do {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose Option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Deposit Amount: ");
                    double dep = sc.nextDouble();
                    acc.deposit(dep);
                    break;

                case 2:
                    System.out.print("Enter Withdrawal Amount: ");
                    double with = sc.nextDouble();
                    acc.withdraw(with);
                    break;

                case 3:
                    acc.checkBalance();
                    break;

                case 4:
                    acc.showTransactions();
                    break;

                case 5:
                    System.out.println("Thank You for Banking!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
