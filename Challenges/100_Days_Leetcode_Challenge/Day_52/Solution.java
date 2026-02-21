// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode temp1 = list1;
        ListNode temp2 = list1;
        ListNode temp3 = list2;
        while(a != 1)
        {
            temp1 = temp1.next;
            a--;
        }
        while(b != -1){
            temp2 = temp2.next;
            b--;
        }
        while(temp3.next != null)
        {
            temp3 = temp3.next;
        }
        temp1.next = list2;
        temp3.next = temp2;
        return list1;
    }
}