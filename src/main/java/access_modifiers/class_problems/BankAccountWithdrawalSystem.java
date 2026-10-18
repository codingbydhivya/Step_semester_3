import java.util.*;

public class BankAccountWithdrawalSystem {
    static abstract class Account {
        String id;
        double balance;

        Account(String id, double balance) {
            this.id = id;
            this.balance = balance;
        }

        abstract void withdraw(double amount);
    }

    static class Savings extends Account {
        Savings(String id, double balance) {
            super(id, balance);
        }

        void withdraw(double amount) {
            if (balance - amount < 1000) {
                System.out.println(id +
                    " rejected: minimum balance 1000");
            } else {
                balance -= amount;
                System.out.println(id + " balance " +
                    format(balance));
            }
        }
    }

    static class Current extends Account {
        Current(String id, double balance) {
            super(id, balance);
        }

        void withdraw(double amount) {
            if (balance - amount < -5000) {
                System.out.println(id +
                    " rejected: overdraft limit 5000");
            } else {
                balance -= amount;
                System.out.println(id + " balance " +
                    format(balance));
            }
        }
    }

    static String format(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("Savings")) {
                accounts.put(p[1],
                    new Savings(p[1], Double.parseDouble(p[2])));
            } else if (p[0].equals("Current")) {
                accounts.put(p[1],
                    new Current(p[1], Double.parseDouble(p[2])));
            } else if (p[0].equals("WITHDRAW")) {
                Account a = accounts.get(p[1]);

                if (a == null) {
                    System.out.println("Account not found");
                } else {
                    a.withdraw(Double.parseDouble(p[2]));
                }
            }
        }

        sc.close();
    }
}