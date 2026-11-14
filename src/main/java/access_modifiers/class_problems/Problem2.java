
class DNode {
    int data;
    DNode prev, next;

    DNode(int data) {
        this.data = data;
    }
}

public class Problem2 {

    static DNode insertAtTailDoubly(DNode head, int value) {
        DNode newNode = new DNode(value);

        if (head == null)
            return newNode;

        DNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        newNode.prev = current;

        return head;
    }

    static void display(DNode head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DNode head = new DNode(1);
        head.next = new DNode(2);
        head.next.prev = head;
        head.next.next = new DNode(3);
        head.next.next.prev = head.next;

        head = insertAtTailDoubly(head, 4);
        display(head);

        DNode empty = null;
        empty = insertAtTailDoubly(empty, 7);
        display(empty);
    }
}
