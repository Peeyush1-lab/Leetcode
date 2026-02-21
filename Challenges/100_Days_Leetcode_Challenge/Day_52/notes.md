# 1669. Merge In Between Linked Lists

**Difficulty:** Medium

## Problem Description

You are given two linked lists: `list1` and `list2` of sizes `n` and `m` respectively.

Remove `list1`'s nodes from the `a`th node to the `b`th node, and put `list2` in their place.

The blue edges and nodes in the following figure indicate the result:

Build the result list and return its head.

## Examples

### Example 1:
```
Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
Output: [10,1,13,1000000,1000001,1000002,5]
Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place.
The blue edges show the result after the merge.
```

### Example 2:
```
Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
Explanation: The blue edges show the result after merging.
Note that values of nodes are not relevant, we only care about their positions.
```

## Constraints

- `3 <= list1.length <= 10^4`
- `1 <= a <= b < list1.length - 1`
- `1 <= list2.length <= 10^4`

## Topics
- Linked List

## Solution Approach

This solution uses a **three-pointer technique**:

1. **Find the node before position `a`** (where to connect list2's head)
   - Use `temp1` pointer, stop at position `a-1`

2. **Find the node after position `b`** (where to connect list2's tail)
   - Use `temp2` pointer, stop at position `b+1`

3. **Find the tail of list2**
   - Use `temp3` pointer, traverse to end of list2

4. **Connect the pieces:**
   - `temp1.next = list2` (connect before removed section to list2's head)
   - `temp3.next = temp2` (connect list2's tail to after removed section)

### Key Insight

The clever decrementing strategy:
- `a` decrements to 1 (not 0) - stops at node BEFORE position `a`
- `b` decrements to -1 (not 0) - stops at node AFTER position `b`

This positioning makes the connection logic simple and elegant.

**Time Complexity:** O(n + m) where n is list1 length, m is list2 length
**Space Complexity:** O(1) - only using pointer variables

## Related Problems

- [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) (Easy)
- [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) (Easy)
- [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/) (Medium)
- [86. Partition List](https://leetcode.com/problems/partition-list/) (Medium)
- [61. Rotate List](https://leetcode.com/problems/rotate-list/) (Medium)