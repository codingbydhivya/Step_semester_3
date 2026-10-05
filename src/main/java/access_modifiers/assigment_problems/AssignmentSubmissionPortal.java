abstract class Assignment {
    protected String title;
    protected int maxMarks;
    protected int dueDay;

    Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public abstract double applyPenalty(
        double marks,
        int lateDays
    );
}

class CodingAssignment extends Assignment {

    CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    public double applyPenalty(double marks, int lateDays) {
        double penalty = lateDays * 0.10;
        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {

    WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    public double applyPenalty(double marks, int lateDays) {
        double penalty = lateDays * 0.20;
        return marks * (1 - penalty);
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {

    enum Status {
        SUBMITTED,
        GRADED
    }

    private Student student;
    private Assignment assignment;
    private int submissionDay;
    private Status status;

    Submission(Student student,
               Assignment assignment,
               int submissionDay) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = Status.SUBMITTED;
    }

    public void grade(double awardedMarks) {

        if (status == Status.GRADED) {
            System.out.println(
                "Submission has already been graded."
            );
            return;
        }

        int lateDays =
            Math.max(0, submissionDay - assignment.dueDay);

        double finalMarks =
            assignment.applyPenalty(
                awardedMarks,
                lateDays
            );

        status = Status.GRADED;

        System.out.printf(
            "%s graded: %.0f/%d. Status: Graded.%n",
            student.getName(),
            finalMarks,
            assignment.maxMarks
        );
    }

    public void resubmit() {

        if (status == Status.GRADED) {
            System.out.println(
                "Cannot resubmit: '" +
                assignment.title +
                "' has already been graded."
            );
        }
    }
}

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
            new CodingAssignment(
                "Linked List Lab",
                50,
                10
            );

        Assignment written =
            new WrittenAssignment(
                "Design Essay",
                50,
                12
            );

        Submission s1 =
            new Submission(asha, coding, 10);

        System.out.println(
            "Asha's submission for 'Linked List Lab' received (on time)."
        );
        System.out.println("Status: Submitted.");

        Submission s2 =
            new Submission(ravi, written, 14);

        System.out.println(
            "Ravi's submission for 'Design Essay' received (2 days late)."
        );
        System.out.println("Status: Submitted.");

        s1.grade(45);

        s2.grade(40);

        s1.resubmit();
    }
}