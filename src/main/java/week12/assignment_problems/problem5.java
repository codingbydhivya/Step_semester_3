
import java.util.*;

public class problem5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node partition(Node head, int passMark) {

        Node belowHead = null;
        Node belowTail = null;

        Node restHead = null;
        Node restTail = null;

        Node temp = head;

        while (temp != null) {
            Node nextNode = temp.next;
            temp.next = null;

            if (temp.data < passMark) {

                if (belowHead == null) {
                    belowHead = temp;
                    belowTail = temp;
                }
                else {
                    belowTail.next = temp;
                    belowTail = temp;
                }
            }
            else {

                if (restHead == null) {
                    restHead = temp;
                    restTail = temp;
                }
                else {
                    restTail.next = temp;
                    restTail = temp;
                }
            }

            temp = nextNode;
        }

        if (belowHead == null)
            return restHead;

        belowTail.next = restHead;

        return belowHead;
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

        int n = sc.nextInt();

        Node head = createList(sc, n);

        int passMark = sc.nextInt();

        head = partition(head, passMark);

        display(head);

        sc.close();
    }
}
