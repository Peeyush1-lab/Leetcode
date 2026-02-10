// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        int carry = 0;
        int sum = 0;
        while(temp1 != null && temp2 != null)
        {
            sum = temp1.val + temp2.val + carry;
            ListNode nn = new ListNode(sum%10);
            carry = sum/10;
            temp1 = temp1.next;
            temp2 = temp2.next;
            temp.next = nn;
            temp = temp.next;
        }
        while(temp1 != null)
        {
            sum = temp1.val + carry;
            ListNode nn = new ListNode(sum%10);
            carry = sum/10;
            temp.next = nn;
            temp = temp.next;
            temp1 = temp1.next;
        }
        while(temp2 != null)
        {
            sum = temp2.val + carry;
            ListNode nn = new ListNode(sum%10);
            carry = sum/10;
            temp.next = nn;
            temp = temp.next;
            temp2 = temp2.next;
        }
        if(carry > 0)
        {
            temp.next = new ListNode(carry);

        }
        return head.next;
    }
}