import java.io.*;
import java.util.*;

public class BankSystem {

    static Scanner sc = new Scanner(System.in);
    static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        loadAccounts();

        int choice;
        do {
            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose Option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    saveAccounts();
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 3);
    }

    static void createAccount() {
        System.out.print("Enter Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Set 4-digit PIN: ");
        int pin = sc.nextInt();

        Account acc = new Account(name, accNo, pin, 0);
        accounts.add(acc);
        System.out.println("Account Created Successfully!");
    }

    static void login() throws Exception {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo && acc.getPin() == pin) {
                System.out.println("Login Successful!");
                accountMenu(acc);
                return;
            }
        }
        System.out.println("Invalid Credentials!");
    }

    static void accountMenu(Account acc) throws Exception {
        int choice;
        do {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance");
            System.out.println("4. Transactions");
            System.out.println("5. Logout");
            System.out.print("Choose Option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Amount: ");
                    acc.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter Amount: ");
                    acc.withdraw(sc.nextDouble());
                    break;
                case 3:
                    acc.showBalance();
                    break;
                case 4:
                    acc.showTransactions();
                    break;
                case 5:
                    System.out.println("Logged Out!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 5);
    }

    static void saveAccounts() throws Exception {
        FileWriter fw = new FileWriter("accounts.txt");
        for (Account acc : accounts) {
            fw.write(acc.toFileString() + "\n");
        }
        fw.close();
    }

    static void loadAccounts() throws Exception {
        File file = new File("accounts.txt");
        if (!file.exists()) return;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            accounts.add(new Account(
                    data[0],
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    Double.parseDouble(data[3])
            ));
        }
        br.close();
    }
}
