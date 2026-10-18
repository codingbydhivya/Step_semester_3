import java.util.*;

public class PayrollRegisterSystem {
    static abstract class Staff {
        String name;

        Staff(String name) {
            this.name = name;
        }

        abstract long calculatePay();
        abstract String type();

        @Override
        public String toString() {
            return "Payslip[name=" + name +
                ", type=" + type() +
                ", pay=" + calculatePay() + "]";
        }
    }

    static class FullTime extends Staff {
        long salary;

        FullTime(String name, long salary) {
            super(name);
            this.salary = salary;
        }

        long calculatePay() { return salary; }
        String type() { return "FullTime"; }
    }

    static class PartTime extends Staff {
        long hours, rate;

        PartTime(String name, long hours, long rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        long calculatePay() { return hours * rate; }
        String type() { return "PartTime"; }
    }

    static class Intern extends Staff {
        long stipend;

        Intern(String name, long stipend) {
            super(name);
            this.stipend = stipend;
        }

        long calculatePay() { return stipend; }
        String type() { return "Intern"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            switch (p[0]) {
                case "FullTime":
                    staffList.add(new FullTime(
                        p[1], Long.parseLong(p[2])));
                    break;

                case "PartTime":
                    staffList.add(new PartTime(
                        p[1],
                        Long.parseLong(p[2]),
                        Long.parseLong(p[3])));
                    break;

                case "Intern":
                    staffList.add(new Intern(
                        p[1], Long.parseLong(p[2])));
                    break;
            }
        }

        long total = 0;
        Staff topper = null;

        for (Staff s : staffList) {
            System.out.println(s);
            total += s.calculatePay();

            if (topper == null ||
                s.calculatePay() > topper.calculatePay()) {
                topper = s;
            }
        }

        System.out.println("Total " + total);

        if (topper != null) {
            System.out.println("top earner " + topper.name);
        }

        sc.close();
    }
}