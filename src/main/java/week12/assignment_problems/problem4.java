
import java.util.*;

public class problem4 {

    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    static class Browser {
        Node head;
        Node cur;

        void open(String name) {
            Node newTab = new Node(name);

            if (cur == null) {
                head = newTab;
                cur = newTab;
            }
            else {
                newTab.prev = cur;
                newTab.next = cur.next;

                if (cur.next != null)
                    cur.next.prev = newTab;

                cur.next = newTab;
                cur = newTab;
            }

            display("OPEN " + name + ":");
        }

        void close() {
            if (cur == null)
                return;

            String closed = cur.name;

            if (cur.next != null) {
                Node nextTab = cur.next;

                nextTab.prev = cur.prev;

                if (cur.prev != null)
                    cur.prev.next = nextTab;
                else
                    head = nextTab;

                cur = nextTab;
            }
            else {
                if (cur.prev != null) {
                    cur = cur.prev;
                    cur.next = null;
                }
                else {
                    head = null;
                    cur = null;
                }
            }

            display("CLOSE " + closed + ":");
        }

        void back() {
            if (cur == null || cur.prev == null) {
                display("BACK: already at first tab");
                return;
            }

            cur = cur.prev;
            display("BACK:");
        }

        void forward() {
            if (cur == null || cur.next == null) {
                display("FORWARD: already at last tab");
                return;
            }

            cur = cur.next;
            display("FORWARD:");
        }

        void display(String message) {
            System.out.print(message + " ");

            Node temp = head;

            while (temp != null) {
                if (temp == cur)
                    System.out.print("[" + temp.name + "]");
                else
                    System.out.print(temp.name);

                if (temp.next != null)
                    System.out.print(" <-> ");

                temp = temp.next;
            }

            System.out.println();
        }

        void printBackward() {
            System.out.print("Backward: ");

            if (head == null) {
                System.out.println("(empty)");
                return;
            }

            Node temp = head;

            while (temp.next != null)
                temp = temp.next;

            while (temp != null) {
                System.out.print(temp.name);

                if (temp.prev != null)
                    System.out.print(" <-> ");

                temp = temp.prev;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Browser browser = new Browser();

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String command = sc.nextLine().trim();

            if (command.startsWith("OPEN ")) {
                String name = command.substring(5);
                browser.open(name);
            }
            else if (command.equals("CLOSE")) {
                browser.close();
            }
            else if (command.equals("BACK")) {
                browser.back();
            }
            else if (command.equals("FORWARD")) {
                browser.forward();
            }
            else if (command.equals("PRINT BACKWARD")) {
                browser.printBackward();
            }
        }

        sc.close();
    }
}
