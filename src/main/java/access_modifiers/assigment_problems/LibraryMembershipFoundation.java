class LibraryMember {
    private String memberId;
    protected int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
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

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + (memberIds.length - rejected)
                + " | Rejected: " + rejected;
    }
}

class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
}

public class LibraryMembershipFoundation {

    public static void main(String[] args) {

        try {
            LibraryMember m = new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        StudentMember s =
                new StudentMember("STU10", 3, "CSE");

        s.borrowBook();
        s.borrowBook();

        System.out.println(s.getBooksBorrowed());

        String[] ids = {
                "STU1", "LB1", "STU2", " ", "STU3"
        };

        System.out.println(
                LibraryMember.enrollBatch(ids, 3)
        );
    }
}