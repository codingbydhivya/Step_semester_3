import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
            "[Email → " +
            student.getName() + "] " +
            notice.getTitle()
        );
    }
}

class SmsChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
            "[SMS → " +
            student.getName() + "] " +
            notice.getTitle()
        );
    }
}

class AppChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
            "[App → " +
            student.getName() + "] " +
            notice.getTitle()
        );
    }
}

class Student {

    private String name;
    private String department;
    private List<NotificationChannel> channels;

    Student(String name, String department) {
        this.name = name;
        this.department = department;
        channels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {

    private String title;
    private Set<String> departments;

    Notice(String title, Set<String> departments) {
        this.title = title;
        this.departments = departments;
    }

    public String getTitle() {
        return title;
    }

    public boolean isValid() {

        return title != null &&
               !title.trim().isEmpty() &&
               departments != null &&
               !departments.isEmpty();
    }

    public boolean targets(String department) {
        return departments.contains(department);
    }
}

class NoticeBoard {

    private List<Student> students;

    NoticeBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        if (!notice.isValid()) {
            System.out.println(
                "Cannot post notice: At least one target department is required."
            );
            return;
        }

        System.out.println(
            "Notice '" + notice.getTitle() +
            "' posted."
        );

        for (Student student : students) {

            if (notice.targets(
                    student.getDepartment())) {

                for (NotificationChannel channel :
                     student.getChannels()) {

                    channel.send(student, notice);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        Student asha =
            new Student("Asha", "CSE");

        Student ravi =
            new Student("Ravi", "ECE");

        asha.addChannel(
            new EmailChannel()
        );

        asha.addChannel(
            new AppChannel()
        );

        ravi.addChannel(
            new SmsChannel()
        );

        NoticeBoard board =
            new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        Set<String> cse =
            new HashSet<>();

        cse.add("CSE");

        Notice notice1 =
            new Notice(
                "Lab Closed Tomorrow",
                cse
            );

        board.postNotice(notice1);

        Set<String> cseEce =
            new HashSet<>();

        cseEce.add("CSE");
        cseEce.add("ECE");

        Notice notice2 =
            new Notice(
                "Fee Deadline Extended",
                cseEce
            );

        board.postNotice(notice2);

        Set<String> empty =
            new HashSet<>();

        Notice notice3 =
            new Notice(
                "Sports Day",
                empty
            );

        board.postNotice(notice3);
    }
}