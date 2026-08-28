
class Employee {

    // Instance fields
    String empName;
    double salary;

    // Static fields
    static String companyName =
        "Bright Horizon Technologies";

    static int employeeCount = 0;


    // Constructor
    Employee(String empName, double salary) {

        this.empName = empName;
        this.salary = salary;

        employeeCount++;
    }


    // Static method
    static void printCompanyInfo() {

        System.out.println(companyName);

        System.out.println(
            "Employees on record: "
            + employeeCount
        );
    }
}


public class Problem5_StaticEmployee {

    public static void main(String[] args) {

        Employee employee1 =
            new Employee("Divya", 65000);

        Employee employee2 =
            new Employee("Arjun", 55000);

        Employee employee3 =
            new Employee("Priya", 60000);


        // Call static method using class name
        Employee.printCompanyInfo();
    }
}
