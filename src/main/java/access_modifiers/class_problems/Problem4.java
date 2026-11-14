
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class Problem4 {

    static Node deleteNthNode(Node head, int n) {
        if (n == 1)
            return head.next;

        Node current = head;

        for (int i = 0; i < n - 2; i++) {
            current = current.next;
        }

        current.next = current.next.next;

        return head;
    }

    static void display(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        head = deleteNthNode(head, 3);
        display(head);

        head = deleteNthNode(head, 1);
        display(head);
    }
}
