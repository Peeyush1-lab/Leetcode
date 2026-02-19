import java.util.*;
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        ListNode temp = head;
        ListNode curr;
        while(temp !=  null)
        {
            curr = temp.next;
            int max = 0;
            while(curr != null)
            {
                if(temp.val<curr.val)
                {
                    max = curr.val;
                    break;
                }
                curr = curr.next;
            }
            arr.add(max);
            temp = temp.next;
        }
        int n = arr.size();
        int nums[] = new int[n];
        for(int i = 0; i < n; i++)
        {
            nums[i] = arr.get(i);
        }
        return nums;
    }
}