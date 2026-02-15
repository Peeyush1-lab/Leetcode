# 142. Linked List Cycle II

**Difficulty:** Medium

## Problem Description

Given the `head` of a linked list, return the node where the cycle begins. If there is no cycle, return `null`.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to (**0-indexed**). It is `-1` if there is no cycle. **Note that `pos` is not passed as a parameter**.

**Do not modify** the linked list.

## Examples

### Example 1:
```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

### Example 2:
```
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

### Example 3:
```
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

## Constraints

- The number of the nodes in the list is in the range `[0, 10^4]`.
- `-10^5 <= Node.val <= 10^5`
- `pos` is `-1` or a **valid index** in the linked-list.

## Follow up
Can you solve it using `O(1)` (i.e. constant) memory?

## Topics
- Hash Table
- Linked List
- Two Pointers

## Solution Approach

This solution uses **Floyd's Cycle Detection Algorithm** (also known as the "tortoise and hare" algorithm) with a mathematical twist to find the cycle start.

### Algorithm Steps:

1. **Phase 1: Detect if a cycle exists**
   - Use slow (moves 1 step) and fast (moves 2 steps) pointers
   - If they meet, a cycle exists
   - If fast reaches null, no cycle exists

2. **Phase 2: Find the cycle start**
   - Reset slow pointer to head
   - Move both slow and fast one step at a time
   - The point where they meet is the cycle start

### Why This Works (Mathematical Proof)

Let's say:
- Distance from head to cycle start = `a`
- Distance from cycle start to meeting point = `b`
- Remaining cycle length = `c`

When slow and fast meet:
- Slow traveled: `a + b`
- Fast traveled: `a + b + c + b` (went around cycle once more)

Since fast moves twice as fast: `2(a + b) = a + b + c + b`

Simplifying: `a = c`

This means the distance from head to cycle start equals the distance from meeting point to cycle start!

**Time Complexity:** O(n)
**Space Complexity:** O(1)

|**Previous:** [Day 45](../Day_45/) | **Next:** [Day 47](../Day_47/)|
|---|---|

|**Date:** February 15, 2026|
|---|