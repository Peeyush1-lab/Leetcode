# 3217. Delete Nodes From Linked List Present in Array

**Difficulty:** Medium

## Problem Description

You are given an array of integers `nums` and the `head` of a linked list. Return the `head` of the modified linked list after **removing** all nodes from the linked list that have a value that exists in `nums`.

## Examples

### Example 1:
```
Input: nums = [1,2,3], head = [1,2,3,4,5]
Output: [4,5]
Explanation: Remove the nodes with values 1, 2, and 3.
```

### Example 2:
```
Input: nums = [1], head = [1,2,1,2,1,2]
Output: [2,2,2]
Explanation: Remove the nodes with value 1.
```

### Example 3:
```
Input: nums = [5], head = [1,2,3,4]
Output: [1,2,3,4]
Explanation: No node has value 5, so the linked list remains unchanged.
```

## Constraints

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^5`
- All elements in `nums` are unique.
- The number of nodes in the given list is in the range `[1, 10^5]`.
- `1 <= Node.val <= 10^5`
- The input is generated such that there is at least one node in the linked list that has a value not present in `nums`.

## Topics
- Array
- Hash Table
- Linked List

## Solution Approach

The solution uses a **HashSet + Dummy Node** approach:

1. **Convert array to HashSet** for O(1) lookup time
2. **Use a dummy node** to handle edge cases (like removing the head)
3. **Traverse the list** with prev and curr pointers
4. **Delete nodes** whose values are in the HashSet by updating prev.next
5. **Keep prev unchanged** when deleting, move it only when keeping a node

### Why Use a Dummy Node?

A dummy node simplifies the code by:
- Eliminating special handling for head deletion
- Providing a consistent starting point
- Making the final return straightforward (`dummy.next`)

### Key Insight

When we delete a node, we DON'T move `prev` forward because `prev` still needs to point to the last "kept" node. We only move `prev` when we keep a node.

**Time Complexity:** O(n + m) where n is the list length and m is the array length
**Space Complexity:** O(m) for the HashSet storing the array values

|**Previous:** [Day 46](../Day_46/) | **Next:** [Day 48](../Day_48/)|
|---|---|

|**Date:** February 16, 2026|
|---|