// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {

            if (curr.val >= prev.val) {
                prev = curr;
                curr = curr.next;
            } else {
                prev.next = curr.next;
                ListNode temp = dummy;

                while (temp.next.val < curr.val) {
                    temp = temp.next;
                }

                curr.next = temp.next;
                temp.next = curr;
                curr = prev.next;
            }
        }
        return dummy.next;
    }
}