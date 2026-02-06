// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    private static ListNode reverseLL(ListNode head)
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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
        {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode lefthead = reverseLL(slow);
        ListNode temp = head;
        while(lefthead != null)
        {
            if(lefthead.val != temp.val)
            {
                return false;
            }
            lefthead = lefthead.next;
            temp = temp.next;
        }
        return true;
    }
}