// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeNodes(ListNode head) {
        int sum = 0;
        ListNode prev = head;
        ListNode temp = head.next;
        sum = 0;
        while(temp != null)
        {
            if(temp.val != 0)
            {
                sum += temp.val;
            }
            else
            {
                prev.val = sum;
                sum = 0;
                if(temp.next == null)
                {
                    prev.next = null;
                }
                else
                {
                    prev = prev.next;
                }
            }
            temp = temp.next;
        }
        return head;
    }
}