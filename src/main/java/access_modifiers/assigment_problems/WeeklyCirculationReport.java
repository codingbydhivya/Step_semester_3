package week6;
class LibraryMember {

    private String memberId;
    protected int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(
            String memberId,
            int borrowLimit) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println(
                "General Member | Books Borrowed: "
                        + booksBorrowed
        );
    }
}

class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Student Member | Course: "
                        + course
                        + " | Books Borrowed: "
                        + getBooksBorrowed()
        );
    }
}

public class WeeklyCirculationReport {

    public static String batchPrint(
            LibraryMember[] members) {

        StringBuilder result = new StringBuilder();

        for (LibraryMember member : members) {

            if (member instanceof StudentMember) {

                StudentMember student =
                        (StudentMember) member;

                result.append("Student | ")
                        .append("Course: ")
                        .append(student.getCourse())
                        .append(" | Books: ")
                        .append(student.getBooksBorrowed())
                        .append(" [Course via downcast: ")
                        .append(student.getCourse())
                        .append("] | ");

            } else {

                result.append("General | Books: ")
                        .append(member.getBooksBorrowed())
                        .append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        LibraryMember plain =
                new LibraryMember("LB5", 3);

        StudentMember student =
                new StudentMember(
                        "STU6", 3, "ECE");

        LibraryMember[] members = {
                plain, student
        };

        System.out.println(
                batchPrint(members)
        );

        // This would cause ClassCastException:
        //
        // StudentMember bad = (StudentMember) plain;
    }
}