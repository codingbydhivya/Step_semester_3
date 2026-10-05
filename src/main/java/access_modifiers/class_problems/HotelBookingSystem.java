import java.util.*;

abstract class Room {
    protected String roomNumber;
    protected boolean available;

    Room(String roomNumber) {
        this.roomNumber = roomNumber;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void book() {
        available = false;
    }

    public void cancel() {
        available = true;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {

    StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {

    DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 150;
    }
}

class Suite extends Room {

    Suite(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * 250;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {

    Customer customer;
    Room room;
    String startDate;
    String endDate;
    int days;

    Reservation(Customer customer, Room room,
                String startDate, String endDate, int days) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public void showReservation() {

        System.out.println(
            "Reservation confirmed for " +
            customer.name + ", Room " + room.roomNumber
        );

        System.out.println(
            "Price: $" + room.calculatePrice(days)
        );
    }
}

public class HotelBookingSystem {

    public static void bookRoom(Customer customer, Room room,
                                String startDate,
                                String endDate,
                                int days) {

        if (!room.isAvailable()) {
            System.out.println(
                "Room " + room.roomNumber +
                " is not available."
            );
            return;
        }

        room.book();

        Reservation reservation =
            new Reservation(
                customer,
                room,
                startDate,
                endDate,
                days
            );

        reservation.showReservation();
    }

    public static void cancelRoom(Customer customer, Room room) {

        room.cancel();

        System.out.println(
            "Reservation for " + customer.name +
            ", Room " + room.roomNumber +
            " cancelled successfully."
        );
    }

    public static void main(String[] args) {

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        Room room101 = new StandardRoom("101");
        Room room201 = new DeluxeRoom("201");

        System.out.println(
            "Standard Room 101 is available from Jan 1 to Jan 5."
        );

        bookRoom(
            customerA,
            room101,
            "Jan 1",
            "Jan 5",
            4
        );

        bookRoom(
            customerB,
            room101,
            "Jan 3",
            "Jan 7",
            4
        );

        cancelRoom(customerA, room101);

        bookRoom(
            customerC,
            room201,
            "Feb 10",
            "Feb 12",
            2
        );
    }
}