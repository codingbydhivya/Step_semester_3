import java.util.*;

abstract class Question {
    protected String question;
    protected int marks;

    Question(String question, int marks) {
        this.question = question;
        this.marks = marks;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    MultipleChoiceQuestion(String question, int marks,
                           String correctAnswer) {
        super(question, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class TrueFalseQuestion extends Question {

    private String correctAnswer;

    TrueFalseQuestion(String question, int marks,
                      String correctAnswer) {
        super(question, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

class Answer {
    Question question;
    String answer;

    Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Attempt {

    Student student;
    List<Answer> answers = new ArrayList<>();
    boolean submitted = false;

    Attempt(Student student) {
        this.student = student;
    }

    public void addAnswer(Question question, String answer) {

        if (submitted) {
            System.out.println(
                "Cannot change answers for a submitted examination."
            );
            return;
        }

        answers.add(new Answer(question, answer));
        System.out.println("Answer recorded.");
    }

    public void submit() {

        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;

        int score = 0;
        int total = 0;

        for (Answer a : answers) {

            total += a.question.marks;

            if (a.question.evaluate(a.answer)) {
                score += a.question.marks;
                System.out.println(
                    "Question: Correct (" + a.question.marks + " points)"
                );
            } else {
                System.out.println("Question: Incorrect (0 points)");
            }
        }

        System.out.println(
            "Total score: " + score + "/" + total
        );
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Question q1 = new MultipleChoiceQuestion(
            "Which is a programming language?",
            5,
            "C"
        );

        Question q2 = new TrueFalseQuestion(
            "Java supports OOP.",
            5,
            "True"
        );

        Attempt attempt = new Attempt(student);

        System.out.println("Exam A started by Student 1.");

        attempt.addAnswer(q1, "C");
        attempt.addAnswer(q2, "False");

        System.out.println("Exam A submitted by Student 1.");

        attempt.submit();

        attempt.addAnswer(q1, "Java");
    }
}
