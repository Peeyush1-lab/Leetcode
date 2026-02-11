/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        if (b % a == 0) {
            return a;
        }
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            ListNode nn = new ListNode(gcd(prev.val, curr.val));
            nn.next = prev.next;
            prev.next = nn;
            prev = curr;
            curr = curr.next;
        }
        return head;
    }
}