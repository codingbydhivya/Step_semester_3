package week6;
import java.util.Arrays;

import LibraryMember;
import StudentMember;

class LibraryMember {

    private String memberId;
    protected int borrowLimit;

    private int[] fineHistory = new int[10];
    private int fineCount;

    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {

        fineHistory[fineCount] = amount;
        fineCount++;
    }

    public int[] getFineHistory() {

        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }

        return total;
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

    @Override
    protected void chargeFine(int amount) {

        super.chargeFine(amount / 2);
    }
}

public class StudentFineLedger {

    public static void main(String[] args) {

        StudentMember s =
                new StudentMember(
                        "STU5", 3, "CSE");

        s.chargeFine(100);

        System.out.println(
                s.getTotalFine()
        );

        int[] history = s.getFineHistory();

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        s.getFineHistory()
                )
        );
    }
}
