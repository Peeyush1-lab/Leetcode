# 82. Remove Duplicates from Sorted List II

**Difficulty:** Medium

## Problem Description

Given the `head` of a sorted linked list, **delete all nodes that have duplicate numbers**, leaving only distinct numbers from the original list. Return the linked list **sorted** as well.

## Examples

### Example 1:
```
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
Explanation: 3 and 4 are duplicates, so all occurrences are removed.
```

### Example 2:
```
Input: head = [1,1,1,2,3]
Output: [2,3]
Explanation: 1 appears 3 times, so all three occurrences are removed.
```

## Constraints

- The number of nodes in the list is in the range `[0, 300]`.
- `-100 <= Node.val <= 100`
- The list is guaranteed to be **sorted** in ascending order.

## Topics
- Linked List
- Two Pointers

## Solution Approach

This solution uses a **Dummy Node + Two Pointers** approach:

1. **Create a dummy node** to handle edge cases (like when head is a duplicate)
2. **Use two pointers:**
   - `prev`: Points to the last node we know is unique
   - `curr`: Explores nodes to detect duplicates
3. **When duplicate is found:**
   - Skip ALL occurrences of that value using inner while loop
   - Update `prev.next` to skip the entire duplicate group
4. **When node is unique:**
   - Move `prev` forward to include this node in result
5. **Always move curr forward** to continue traversal

### Key Insight

The difference from "Remove Duplicates I" (LeetCode 83):
- **Problem 83:** Keep ONE copy of each duplicate
- **Problem 82:** Remove ALL copies if duplicates exist

**Time Complexity:** O(n) - Single pass through the list
**Space Complexity:** O(1) - Only using pointer variables

|**Previous:** [Day 48](../Day_48/) | **Next:** [Day 50](../Day_50/)|
|---|---|

|**Date:** February 18, 2026|
|---|