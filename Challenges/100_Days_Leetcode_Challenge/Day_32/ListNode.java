// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    private int Sizeof(ListNode head) {
        ListNode temp = head;
        int i = 0;
        while (temp != null) {
            temp = temp.next;
            i++;
        }
        return i;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int n1 = Sizeof(head);
        if (n1 == n) {
            head = head.next;
            return head;
        }
        ListNode curr = head;
        while (n1 - n != 1) {
            curr = curr.next;
            n++;
        }
        curr.next = curr.next.next;
        return head;
    }
}