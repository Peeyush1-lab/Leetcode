// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    private ListNode reverse_ll(ListNode head)
    {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while(curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode doubleIt(ListNode head) {
        int carry = 0;
        int sum;
        ListNode temp = reverse_ll(head);
        ListNode temp2 = temp;
        while(temp2 != null)
        {
            sum = (temp2.val*2)+carry;
            temp2.val = sum%10;
            carry = sum/10;
            if(temp2.next == null)
            {
                break;
            }
            temp2 = temp2.next;
        }
        if(carry > 0)
        {
            temp2.next = new ListNode(carry);
        }
        return reverse_ll(temp);
    }
}