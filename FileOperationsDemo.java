import java.io.*;
import java.util.*;

class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) {
        super(msg);
    }
}

class LowBalanceException extends Exception {
    LowBalanceException(String msg) {
        super(msg);
    }
}

class User {
    int id;
    String name;
    double balance;

    User(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
}

public class BankFileSystem {
    static Scanner sc = new Scanner(System.in);
    static User currentUser = null;

    public static void main(String[] args) {
        new File("accounts").mkdir();

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: register(); break;
                case 2: login(); break;
                case 3: System.out.println("Exit"); return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    static void register() {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            File file = new File("accounts/" + id + ".txt");
            if (file.exists()) {
                System.out.println("User already exists!");
                return;
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            double bal = sc.nextDouble();

            if (bal < 0)
                throw new InvalidAmountException("Negative amount not allowed");

            PrintWriter pw = new PrintWriter(file);
            pw.println(id + "," + name + "," + bal);
            pw.close();

            System.out.println("Account Created!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void login() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        File file = new File("accounts/" + id + ".txt");

        if (!file.exists()) {
            System.out.println("User not found!");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String data = br.readLine();
            br.close();

            String[] arr = data.split(",");
            currentUser = new User(
                Integer.parseInt(arr[0]),
                arr[1],
                Double.parseDouble(arr[2])
            );

            System.out.println("Welcome " + currentUser.name);
            menu();

        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    static void menu() {
        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Balance\n4. Logout");
            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1: deposit(); break;
                    case 2: withdraw(); break;
                    case 3: System.out.println("Balance: ₹" + currentUser.balance); break;
                    case 4: return;
                    default: System.out.println("Invalid");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void deposit() throws InvalidAmountException {
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();

        if (amt < 0)
            throw new InvalidAmountException("Cannot deposit negative");

        currentUser.balance += amt;
        saveData();
        System.out.println("Updated Balance: ₹" + currentUser.balance);
    }

    static void withdraw() throws InvalidAmountException, LowBalanceException {
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();

        if (amt < 0)
            throw new InvalidAmountException("Cannot withdraw negative");

        if (amt > currentUser.balance)
            throw new LowBalanceException("Insufficient balance");

        currentUser.balance -= amt;
        saveData();
        System.out.println("Updated Balance: ₹" + currentUser.balance);
    }

    static void saveData() {
        try {
            PrintWriter pw = new PrintWriter("accounts/" + currentUser.id + ".txt");
            pw.println(currentUser.id + "," + currentUser.name + "," + currentUser.balance);
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving data");
        }
    }
}
