# 1290. Convert Binary Number in a Linked List to Integer

**Difficulty:** Easy

## Problem Description

Given `head` which is a reference node to a singly-linked list. The value of each node in the linked list is either `0` or `1`. The linked list holds the binary representation of a number.

Return the **decimal value** of the number in the linked list.

The **most significant bit** is at the head of the linked list.

## Examples

### Example 1:
```
Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10
```

### Example 2:
```
Input: head = [0]
Output: 0
```

## Constraints

- The Linked List is not empty.
- Number of nodes will not exceed `30`.
- Each node's value is either `0` or `1`.

## Topics
- Linked List
- Math

## Solution Approach

This solution uses the **positional binary-to-decimal conversion** approach:

1. **Find the length** of the linked list using a helper function
2. **Traverse the list** from head (MSB) to tail (LSB)
3. **For each node with value 1**, add `2^(length-1)` to the result
4. **Decrement** the power after each node
5. **Handle the last node** separately to avoid checking `head.next != null`

### Binary to Decimal Formula

For a binary number like `1 0 1` (length = 3):
```
1 × 2² + 0 × 2¹ + 1 × 2⁰
= 4 + 0 + 1
= 5
```

**Time Complexity:** O(n) - two passes through the list (findLength + main loop)
**Space Complexity:** O(1) - only using a few integer variables

|**Previous:** [Day 47](../Day_47/) | **Next:** [Day 49](../Day_49/)|
|---|---|

|**Date:** February 17, 2026|
|---|