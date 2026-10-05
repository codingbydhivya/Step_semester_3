package week8class;
import java.util.*;

abstract class Vehicle {
    protected String vehicleId;
    protected boolean available;

    Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String vehicleId) {
        super(vehicleId);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class SUV extends Vehicle {
    SUV(String vehicleId) {
        super(vehicleId);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class Truck extends Vehicle {
    Truck(String vehicleId) {
        super(vehicleId);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Customer customer;
    Vehicle vehicle;
    int days;

    Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public void showRental() {
        System.out.println(
            vehicle.vehicleId + " rented successfully by " + customer.name
        );
        System.out.println("Rental charge: $" + vehicle.calculateCharge(days));
    }
}

public class VehicleRentalSystem {

    public static void rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.vehicleId + " is currently unavailable.");
            return;
        }

        vehicle.rent();

        Rental rental = new Rental(customer, vehicle, days);
        rental.showRental();
    }

    public static void returnVehicle(Customer customer, Vehicle vehicle) {
        vehicle.returnVehicle();
        System.out.println(
            vehicle.vehicleId + " returned by " + customer.name
        );
    }

    public static void main(String[] args) {

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        rentVehicle(c1, sedanA, 3);

        rentVehicle(c2, sedanA, 2);

        returnVehicle(c1, sedanA);

        rentVehicle(c3, suvB, 5);
    }
}