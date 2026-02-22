# 1721. Swapping Nodes in a Linked List

**Difficulty:** Medium

## Problem Description

You are given the `head` of a linked list, and an integer `k`.

Return the head of the linked list after **swapping** the values of the `k`th node from the beginning and the `k`th node from the end (the list is **1-indexed**).

## Examples

### Example 1:
```
Input: head = [1,2,3,4,5], k = 2
Output: [1,4,3,2,5]
Explanation: Swap the 2nd node from beginning (value 2) with 2nd node from end (value 4).
```

### Example 2:
```
Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
Output: [7,9,6,6,8,7,3,0,9,5]
Explanation: Swap the 5th node from beginning (value 7) with 5th node from end (value 8).
```

## Constraints

- The number of nodes in the list is `n`.
- `1 <= k <= n <= 10^5`
- `0 <= Node.val <= 100`

## Topics
- Linked List
- Two Pointers

## Solution Approach

This solution uses an **elegant two-pointer technique** with a clever twist:

### Phase 1: Find kth node from beginning
- Traverse k-1 steps to reach the kth node
- Store this node in `start`

### Phase 2: Find kth node from end using "Runner" technique
- Use `start` as a reference point
- Start `end` at head
- Move both `temp` (starting from `start`) and `end` together
- When `temp` reaches the end, `end` will be at kth node from end

### Phase 3: Swap values
- Simply swap the values of `start` and `end` nodes

### Key Insight: The Runner Technique

By starting `temp` at the kth position and `end` at head, then moving them together until `temp` reaches the end, we ensure that `end` is exactly k positions from the end.

**Distance from start to temp at end = n - k**
**Distance from head to end = n - k**
**Therefore: end is at position n - k + 1 = kth from end** ✅

**Time Complexity:** O(n) - Single pass through the list
**Space Complexity:** O(1) - Only using pointer variables

## Related Problems

- [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) (Medium) - Similar "nth from end" concept
- [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) (Easy) - Two-pointer technique
- [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) (Medium) - Node swapping
- [234. Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) (Easy) - Finding middle and reversing
- [143. Reorder List](https://leetcode.com/problems/reorder-list/) (Medium) - Complex list manipulation

|**Previous:** [Day 52](../Day_52/) | **Next:** [Day 54](../Day_54/)|
|---|---|

|**Date:** February 22 2026|
|---|