# 203. Remove Linked List Elements

**Difficulty:** Easy

## Problem Description

Given the `head` of a linked list and an integer `val`, remove all the nodes of the linked list that has `Node.val == val`, and return the new head.

## Examples

### Example 1:
```
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
```

### Example 2:
```
Input: head = [], val = 1
Output: []
```

### Example 3:
```
Input: head = [7,7,7,7], val = 7
Output: []
```

## Constraints

- The number of nodes in the list is in the range `[0, 10^4]`.
- `1 <= Node.val <= 50`
- `0 <= val <= 50`

## Topics
- Linked List
- Recursion

## Solution Approach

This solution uses an iterative approach with two main steps:

1. **Handle head nodes:** Remove all nodes from the beginning of the list that match the target value
2. **Handle remaining nodes:** Traverse the list and remove any matching nodes

### Algorithm Steps:

1. Move head forward while it matches the target value
2. Use a pointer to traverse the remaining list
3. If next node matches, skip it by updating the next pointer
4. Otherwise, move to the next node
5. Return the new head

**Time Complexity:** O(n) - We traverse the list once
**Space Complexity:** O(1) - Only use a constant amount of extra space

|**Previous:** [Day 43](../Day_43/) | **Next:** [Day 45](../Day_45/)|
|---|---|

|**Date:** February 13, 2026|
|---|