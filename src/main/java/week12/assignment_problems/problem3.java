
import java.util.*;

public class problem3 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node merge(Node first, Node second) {
        Node dummy = new Node(0);
        Node tail = dummy;

        while (first != null && second != null) {

            if (first.data <= second.data) {
                tail.next = first;
                first = first.next;
            }
            else {
                tail.next = second;
                second = second.next;
            }

            tail = tail.next;
        }

        if (first != null)
            tail.next = first;
        else
            tail.next = second;

        return dummy.next;
    }

    static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    static Node createList(Scanner sc, int n) {
        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();

            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            }
            else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        Node first = createList(sc, n1);

        int n2 = sc.nextInt();
        Node second = createList(sc, n2);

        Node result = merge(first, second);

        display(result);

        sc.close();
    }
}
