# 1019. Next Greater Node In Linked List

**Difficulty:** Medium

## Problem Description

You are given the `head` of a linked list with `n` nodes.

For each node in the list, find the value of the **next greater node**. That is, for each node, find the value of the first node that is next to it and has a **strictly larger** value than it.

Return an integer array `answer` where `answer[i]` is the value of the next greater node of the `i`th node (**1-indexed**). If the `i`th node does not have a next greater node, set `answer[i] = 0`.

## Examples

### Example 1:
```
Input: head = [2,1,5]
Output: [5,5,0]
Explanation:
- For node with value 2, next greater is 5
- For node with value 1, next greater is 5
- For node with value 5, there is no next greater (return 0)
```

### Example 2:
```
Input: head = [2,7,4,3,5]
Output: [7,0,5,5,0]
Explanation:
- For 2, next greater is 7
- For 7, no next greater (it's the largest)
- For 4, next greater is 5
- For 3, next greater is 5
- For 5, no next greater
```

## Constraints

- The number of nodes in the list is `n`.
- `1 <= n <= 10^4`
- `1 <= Node.val <= 10^9`

## Topics
- Array
- Linked List
- Stack
- Monotonic Stack

## Solution Approach

### Your Approach: Brute Force (Nested Loop)

For each node:
1. Start from the next node
2. Search forward until finding a larger value
3. If found, add it to result; otherwise add 0
4. Convert ArrayList to array and return

**Time Complexity:** O(n²) - for each node, potentially scan rest of list
**Space Complexity:** O(n) - ArrayList to store results

### Optimal Approach: Monotonic Stack

Using a stack to track indices/values in reverse:
1. Convert list to array
2. Use stack to find next greater in O(n) time
3. Process from right to left

**Time Complexity:** O(n) - single pass with stack
**Space Complexity:** O(n) - stack and result array

Your brute force solution is correct and straightforward! For large inputs, the stack-based approach would be more efficient.

|**Previous:** [Day 49](../Day_49/) | **Next:** [Day 51](../Day_51/)|
|---|---|

|**Date:** February 19, 2026|
|---|