
class HallTicket {

    String studentName;
    int seatNumber;


    // Constructor
    HallTicket(String studentName, int seatNumber) {

        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}


public class Problem4_HallTicket {

    public static void main(String[] args) {

        // Create one object
        HallTicket priya =
            new HallTicket("Priya", 0);


        // Copy the reference
        HallTicket copy = priya;


        // Change using second reference
        copy.seatNumber = 45;


        // Create a separate object
        HallTicket separate =
            new HallTicket("Priya", 45);


        System.out.println(
            "Priya's seatNumber (via first variable): "
            + priya.seatNumber
        );

        System.out.println(
            "copy == priya: "
            + (copy == priya)
        );

        System.out.println(
            "separate == priya: "
            + (separate == priya)
        );
    }
}
