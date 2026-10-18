import java.util.*;

public class CampusVehiclePassSystem {
    interface Chargeable {
        void charge();
    }

    static abstract class Vehicle {
        String passNumber;
        String owner;

        Vehicle(String passNumber, String owner) {
            this.passNumber = passNumber;
            this.owner = owner;
        }

        abstract int passFee();
        abstract String type();

        void printPass() {
            System.out.println(passNumber + " (" + type() +
                ") pass fee " + passFee());
        }
    }

    static class Bike extends Vehicle {
        Bike(String p, String o) {
            super(p, o);
        }

        int passFee() { return 300; }
        String type() { return "Bike"; }
    }

    static class Car extends Vehicle {
        Car(String p, String o) {
            super(p, o);
        }

        int passFee() { return 1000; }
        String type() { return "Car"; }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String p, String o) {
            super(p, o);
        }

        int passFee() { return 300; }
        String type() { return "EBike"; }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String p, String o) {
            super(p, o);
        }

        int passFee() { return 1000; }
        String type() { return "ECar"; }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("PASS")) {
                Vehicle v = null;

                switch (p[1]) {
                    case "Bike":
                        v = new Bike(p[2], p[3]);
                        break;
                    case "Car":
                        v = new Car(p[2], p[3]);
                        break;
                    case "EBike":
                    case "E-Bike":
                        v = new EBike(p[2], p[3]);
                        break;
                    case "ECar":
                    case "E-Car":
                        v = new ECar(p[2], p[3]);
                        break;
                }

                if (v != null) {
                    vehicles.put(p[2], v);
                    v.printPass();
                }
            } else if (p[0].equals("CHARGE")) {
                Vehicle v = vehicles.get(p[1]);

                if (v instanceof Chargeable) {
                    ((Chargeable) v).charge();
                } else {
                    System.out.println(p[1] +
                        " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}