import java.io.*;
import java.util.*;

class MinBalException extends Exception {
    MinBalException(String msg) {
        super(msg);
    }
}

class InsuffException extends Exception {
    InsuffException(String msg) {
        super(msg);
    }
}

class IdException extends Exception {
    IdException(String msg) {
        super(msg);
    }
}

class NegativeException extends Exception {
    NegativeException(String msg) {
        super(msg);
    }
}

class Customer {
    int id;
    String name;
    double bal;
    String pass;

    Customer(int id, String name, double bal, String pass) {
        this.id = id;
        this.name = name;
        this.bal = bal;
        this.pass = pass;
    }
}

public class FileOperationsDemo {
    static Scanner sc = new Scanner(System.in);
    static Customer current = null;

    public static void main(String[] args) {
        new File("users").mkdir();

        while (true) {
            System.out.println("\n1.Login 2.Register 3.Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1)
                login();
            else if (ch == 2)
                register();
            else if (ch == 3) {
                System.out.println("Bye!");
                break;
            } else
                System.out.println("Wrong input");
        }
    }

    static void login() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        File f = new File("users/" + id + ".txt");
        if (!f.exists()) {
            System.out.println("No user found");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = br.readLine();
            br.close();
            String[] data = line.split(",");

            if (data[3].equals(pass)) {
                current = new Customer(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2]), data[3]);
                System.out.println("Welcome " + current.name);
                menu();
            } else {
                System.out.println("Wrong password");
            }
        } catch (Exception e) {
            System.out.println("Error loading");
        }
    }

    static void register() {
        try {
            System.out.print("ID (1-20): ");
            int id = sc.nextInt();
            sc.nextLine();

            if (id < 1 || id > 20)
                throw new IdException("ID should be 1-20");

            File f = new File("users/" + id + ".txt");
            if (f.exists()) {
                System.out.println("ID taken");
                return;
            }

            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();
            System.out.print("Initial amount (smallest 1000): ");
            double amt = sc.nextDouble();

            if (amt < 0)
                throw new NegativeException("No negative amounts");
            if (amt < 1000)
                throw new MinBalException("Need at least 1000");

            PrintWriter pw = new PrintWriter(f);
            pw.println(id + "," + name + "," + amt + "," + pass);
            pw.close();

            System.out.println("Account created! Login now");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void menu() {
        while (true) {
            System.out.println("\n1.Deposit 2.Withdraw 3.Balance 4.Details 5.Logout");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            try {
                if (ch == 1)
                    deposit();
                else if (ch == 2)
                    withdraw();
                else if (ch == 3)
                    System.out.println("Balance: ₹" + current.bal);
                else if (ch == 4) {
                    System.out.println("ID: " + current.id);
                    System.out.println("Name: " + current.name);
                    System.out.println("Balance: ₹" + current.bal);
                } else if (ch == 5) {
                    System.out.println("Logged out");
                    current = null;
                    break;
                } else
                    System.out.println("Invalid");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void deposit() throws NegativeException {
        System.out.print("Amount: ");
        double amt = sc.nextDouble();
        if (amt < 0)
            throw new NegativeException("Can't deposit negative");
        current.bal += amt;
        updateFile();
        System.out.println("New balance: ₹" + current.bal);
    }

    static void withdraw() throws InsuffException, NegativeException {
        System.out.print("Amount: ");
        double amt = sc.nextDouble();
        if (amt < 0)
            throw new NegativeException("Can't withdraw negative");
        if (amt > current.bal)
            throw new InsuffException("Not enough money");
        current.bal -= amt;
        updateFile();
        System.out.println("New balance: ₹" + current.bal);
    }

    static void updateFile() {
        try {
            PrintWriter pw = new PrintWriter("users/" + current.id + ".txt");
            pw.println(current.id + "," + current.name + "," + current.bal + "," + current.pass);
            pw.close();
        } catch (Exception e) {
            System.out.println("Save failed");
        }
    }
}
