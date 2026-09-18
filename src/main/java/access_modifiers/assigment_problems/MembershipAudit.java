
class LibraryMember {

    private int booksBorrowed;
    protected int borrowLimit;

    private static int nextMemberNumber = 101;

    public final String memberNumber;

    public LibraryMember(int borrowLimit) {

        this.borrowLimit = borrowLimit;

        memberNumber = "LIB-" + nextMemberNumber;
        nextMemberNumber++;
    }

    public void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {

        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static boolean isValidRenewalCode(
            String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {

        return nextMemberNumber - 101;
    }
}

class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class MembershipAudit {

    public static String processNightlyAudit(
            LibraryMember[] members) {

        StringBuilder result = new StringBuilder();

        for (LibraryMember member : members) {

            if (member == null) {
                continue;
            }

            if (member instanceof FacultyMember) {

                FacultyMember faculty =
                        (FacultyMember) member;

                result.append("Faculty: ")
                        .append(faculty.getDepartment());

            } else {

                result.append("Regular Member");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        LibraryMember m1 =
                new LibraryMember(3);

        System.out.println(
                m1.memberNumber
        );

        System.out.println(
                LibraryMember.getMembersEnrolled()
        );

        System.out.println(
                LibraryMember.isValidRenewalCode("R12A")
        );

        System.out.println(
                LibraryMember.isValidRenewalCode("R1A")
        );

        System.out.println(
                LibraryMember.isValidRenewalCode("X12A")
        );

        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println(
                m1.getBooksBorrowed()
        );

        FacultyMember faculty =
                new FacultyMember(
                        5, "Physics");

        LibraryMember[] members = {
                m1, null, faculty
        };

        System.out.println(
                processNightlyAudit(members)
        );
    }
}