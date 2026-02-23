# 24. Swap Nodes in Pairs

**Difficulty:** Medium

## Problem Description

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem **without modifying the values** in the list's nodes (i.e., only nodes themselves may be changed.)

## Examples

### Example 1:
```
Input: head = [1,2,3,4]
Output: [2,1,4,3]
Explanation: Swap (1,2) and (3,4)
```

### Example 2:
```
Input: head = []
Output: []
```

### Example 3:
```
Input: head = [1]
Output: [1]
```

### Example 4:
```
Input: head = [1,2,3]
Output: [2,1,3]
Explanation: Swap (1,2), but 3 is alone so it stays
```

## Constraints

- The number of nodes in the list is in the range `[0, 100]`.
- `0 <= Node.val <= 100`

## Topics
- Linked List
- Recursion

## Solution Approach

This solution uses a **dummy node + pointer manipulation** approach:

### Key Steps (Per Pair):

1. **Identify the pair:** Use `prev.next` and `prev.next.next` as first and second
2. **Rewire three pointers:**
   - `first.next = second.next` (first points to node after pair)
   - `second.next = first` (second points back to first)
   - `prev.next = second` (prev points to second, which is now first in pair)
3. **Move to next pair:** `prev = first` (first is now the last node of swapped pair)

### Why Dummy Node?

The dummy node handles the special case where the head itself needs to be changed (when we swap the first two nodes).

### Loop Condition

`while (prev.next != null && prev.next.next != null)`
- Ensures both nodes of the pair exist
- Stops when we have 0 or 1 node remaining

**Time Complexity:** O(n) - Visit each node once
**Space Complexity:** O(1) - Only using pointer variables, iterative solution

## Related Problems

- [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) (Hard) - Generalization to k nodes
- [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) (Easy) - Basic reversal
- [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/) (Medium) - Reverse portion of list
- [1721. Swapping Nodes in a Linked List](https://leetcode.com/problems/swapping-nodes-in-a-linked-list/) (Medium) - Swap by values, not positions
- [143. Reorder List](https://leetcode.com/problems/reorder-list/) (Medium) - Complex rearrangement

|**Previous:** [Day 53](../Day_53/) | **Next:** [Day 55](../Day_55/)|
|---|---|

|**Date:** February 23 2026|
|---|