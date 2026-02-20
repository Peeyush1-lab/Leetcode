# 147. Insertion Sort List

**Difficulty:** Medium

## Problem Description

Given the `head` of a singly linked list, sort the list using **insertion sort**, and return the sorted list's head.

The steps of the **insertion sort** algorithm:

1. Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
2. At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
3. It repeats until no input elements remain.

## Examples

### Example 1:
```
Input: head = [4,2,1,3]
Output: [1,2,3,4]
```

### Example 2:
```
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
```

## Constraints

- The number of nodes in the list is in the range `[1, 5000]`.
- `-5000 <= Node.val <= 5000`

## Topics
- Linked List
- Sorting

## Solution Approach

This solution implements **insertion sort on a linked list** using:

1. **Dummy node** - simplifies insertion at the beginning
2. **Two-pointer traversal:**
   - `prev`: Points to the last node in the sorted portion
   - `curr`: The current node being processed
3. **Two cases:**
   - **Already sorted:** If `curr.val >= prev.val`, just move pointers forward
   - **Out of order:** Remove `curr` from its position, find correct insertion point in sorted portion, insert it there

### Key Steps

For each node that's out of order:
1. **Remove** the node from its current position
2. **Find** the correct insertion point (start from dummy)
3. **Insert** the node at the correct position
4. **Continue** with the next node

**Time Complexity:** O(n²) - for each node, may scan the sorted portion
**Space Complexity:** O(1) - sorting in place with only pointer variables

## Related Problems

- [148. Sort List](https://leetcode.com/problems/sort-list/) (Medium) - Merge sort on linked list
- [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) (Easy)
- [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) (Hard)
- [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/) (Easy)

|**Previous:** [Day 50](../Day_50/) | **Next:** [Day 52](../Day_52/)|
|---|---|

|**Date:** February 20 2026|
|---|