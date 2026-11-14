
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class Problem3 {

    static Node removeFirstKNodes(Node head, int k) {
        if (head == null || k == 0)
            return head;

        for (int i = 0; i < k; i++) {
            if (head == null)
                break;

            head = head.next;
        }

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
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        head = removeFirstKNodes(head, 2);
        display(head);
    }
}
