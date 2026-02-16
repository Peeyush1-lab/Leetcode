import java.util.*;
// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> arr = new HashSet<Integer>();
        for(int i : nums)
        {
            arr.add(i);
        }
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        temp.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next;
        while(curr != null)
        {
            next = curr.next;
            if(arr.contains(curr.val))
            {
                prev.next = next;
            }
            else
            {
                prev = curr;
            }
            curr = next;
        }
        return dummy.next;
    }
}