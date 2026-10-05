
abstract class Employee {
    protected String name;

    Employee(String name) {
        this.name = name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {

    FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {

    PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 15;
    }
}

class Contractor extends Employee {

    Contractor(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class LeaveRequest {

    enum Status {
        PENDING, APPROVED, REJECTED
    }

    Employee employee;
    String startDate;
    String endDate;
    int days;
    Status status;

    LeaveRequest(Employee employee, String startDate,
                 String endDate, int days) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = Status.PENDING;
    }

    public void approve() {

        if (status != Status.PENDING) {
            System.out.println("Cannot approve an already processed request.");
            return;
        }

        if (!employee.canTakeLeave(days)) {
            System.out.println("Leave policy does not allow this request.");
            return;
        }

        status = Status.APPROVED;

        System.out.println(
            employee.name + "'s leave request (" +
            startDate + "-" + endDate + ") approved."
        );
    }

    public void reject() {

        if (status != Status.PENDING) {
            System.out.println("Request has already been processed.");
            return;
        }

        status = Status.REJECTED;

        System.out.println(
            employee.name + "'s leave request (" +
            startDate + "-" + endDate + ") rejected."
        );
    }

    public void changeToPending() {

        if (status != Status.PENDING) {
            System.out.println(
                "Cannot change leave request status from " +
                status + " to Pending."
            );
        }
    }
}

public class EmployeeLeaveSystem {

    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest request1 =
            new LeaveRequest(john, "Jan 1", "Jan 5", 5);

        System.out.println(
            "Leave request submitted for John (Jan 1-5). Status: Pending."
        );

        request1.approve();

        request1.changeToPending();

        LeaveRequest request2 =
            new LeaveRequest(jane, "Feb 10", "Feb 11", 2);

        System.out.println(
            "Leave request submitted for Jane (Feb 10-11). Status: Pending."
        );

        request2.reject();
    }
}