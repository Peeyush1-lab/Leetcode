class MyLinkedList {
    class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        Node temp = head;
        while (index-- > 0) temp = temp.next;
        return temp.data;
    }

    public void addAtHead(int val) {
        Node nn = new Node(val);
        nn.next = head;
        head = nn;
        size++;
    }

    public void addAtTail(int val) {
        Node nn = new Node(val);
        if (head == null) {
            head = nn;
        } else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = nn;
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        if (index == 0) {
            addAtHead(val);
            return;
        }
        Node nn = new Node(val);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) temp = temp.next;
        nn.next = temp.next;
        temp.next = nn;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) {
            head = head.next;
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) temp = temp.next;
            temp.next = temp.next.next;
        }
        size--;
    }
}
