// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public int getDecimalValue(ListNode head) {
        ListNode node = head;
        int length = findLength(node);
        int ans = 0;
        while(head.next != null){
            if(head.val == 1){
                ans += Math.pow(2, length-1);
            }
            head = head.next;
            length--;
        }
        if(head.val == 1){
            ans += 1;
        }
        return ans;
    }
    public int findLength(ListNode node){
        int l = 1;
        while(node.next != null){
            node = node.next;
            l++;
        }
        return l;
    }
}