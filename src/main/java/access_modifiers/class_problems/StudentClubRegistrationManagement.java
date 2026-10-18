import java.util.*;

public class StudentClubRegistrationManagement {
    static class Student {
        String rollNumber;
        String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;

            Student other = (Student) obj;
            return rollNumber.equals(other.rollNumber);
        }

        @Override
        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new LinkedHashSet<>();
        List<String> containsResults = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");
            Student s = new Student(p[1], p[2]);

            if (p[0].equals("ADD")) {
                if (members.add(s)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (p[0].equals("CONTAINS")) {
                containsResults.add(
                    "contains: " + members.contains(s));
            }
        }

        System.out.println("member count " + members.size());

        for (String result : containsResults) {
            System.out.println(result);
        }

        sc.close();
    }
}