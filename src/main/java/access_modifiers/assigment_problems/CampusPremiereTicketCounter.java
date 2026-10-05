import java.util.*;

abstract class Seat {
    protected String seatNumber;

    Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {

    RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat extends Seat {

    PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat extends Seat {

    ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {
    private String showTime;
    private Set<String> bookedSeats;

    Show(String showTime) {
        this.showTime = showTime;
        bookedSeats = new HashSet<>();
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(
            seat.getSeatNumber()
        );
    }

    public boolean bookSeat(Seat seat) {

        if (!isAvailable(seat)) {
            return false;
        }

        bookedSeats.add(seat.getSeatNumber());
        return true;
    }

    public void releaseSeat(Seat seat) {
        bookedSeats.remove(seat.getSeatNumber());
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;

    Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
        seats = new ArrayList<>();
    }

    public boolean addSeat(Seat seat) {

        if (seats.size() >= 6) {
            System.out.println(
                "Cannot book more than 6 seats."
            );
            return false;
        }

        if (!show.bookSeat(seat)) {
            System.out.println(
                "Seat " + seat.getSeatNumber() +
                " is already booked for this show."
            );
            return false;
        }

        seats.add(seat);
        return true;
    }

    public double getTotal() {

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void confirm() {

        System.out.print(
            "Booking confirmed for " +
            customer.getName() + ": "
        );

        for (Seat seat : seats) {
            System.out.print(
                seat.getSeatNumber() + " "
            );
        }

        System.out.printf(
            "%nTotal: ₹%.2f%n",
            getTotal()
        );
    }

    public void cancel() {

        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        System.out.println(
            customer.getName() +
            "'s booking cancelled."
        );

        System.out.println(
            "All booked seats released."
        );
    }
}

public class CampusPremiereTicketCounter {

    public static void main(String[] args) {

        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking =
            new Booking(asha, show);

        ashaBooking.addSeat(
            new RegularSeat("A1")
        );

        ashaBooking.addSeat(
            new RegularSeat("A2")
        );

        ashaBooking.addSeat(
            new PremiumSeat("F5")
        );

        ashaBooking.confirm();

        Booking raviBooking =
            new Booking(ravi, show);

        raviBooking.addSeat(
            new RegularSeat("A2")
        );

        raviBooking.addSeat(
            new ReclinerSeat("R1")
        );

        raviBooking.confirm();

        ashaBooking.cancel();

        Booking nehaBooking =
            new Booking(neha, show);

        nehaBooking.addSeat(
            new RegularSeat("A2")
        );

        nehaBooking.confirm();
    }
}