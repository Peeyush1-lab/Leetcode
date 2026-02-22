// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode start = head;
        ListNode end = head;
        for(int i = 1; i < k; i++)
        {
            start = start.next;
        }

        ListNode temp = start;
        while(temp.next != null)
        {
            temp = temp.next;
            end = end.next;
        }

        int tem = start.val;
        start.val = end.val;
        end.val = tem;
        return head;
    }
}