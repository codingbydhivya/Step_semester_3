

import java.util.*;

public class problem3 {

    static class Editor {
        String[] history;
        int top;
        int capacity;

        Editor(int n) {
            capacity = n;
            history = new String[n];
            top = -1;
        }

        void type(String word) {
            if (top == capacity - 1) {
                System.out.println("rejected: history full");
                return;
            }

            history[++top] = word;
            display();
        }

        void undo() {
            if (top == -1) {
                System.out.println("rejected: nothing to undo");
                return;
            }

            history[top] = null;
            top--;
            display();
        }

        void display() {
            if (top == -1) {
                System.out.println("(empty)");
                return;
            }

            for (int i = 0; i <= top; i++) {
                System.out.print(history[i]);

                if (i < top)
                    System.out.print(" ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int commands = sc.nextInt();

        Editor editor = new Editor(n);

        for (int i = 0; i < commands; i++) {
            String operation = sc.next();

            if (operation.equals("TYPE")) {
                String word = sc.next();
                editor.type(word);
            }
            else if (operation.equals("UNDO")) {
                editor.undo();
            }
        }

        sc.close();
    }
}
