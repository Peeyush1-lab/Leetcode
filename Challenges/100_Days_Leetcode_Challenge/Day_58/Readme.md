# 1441. Build an Array With Stack Operations

**Difficulty:** Medium

## Problem Description

You are given an integer array `target` and an integer `n`.

You have an empty stack with the two following operations:

- **"Push"**: pushes an integer to the top of the stack from the stream `[1, 2, 3, ..., n]`.
- **"Pop"**: removes the top element from the stack.

Return the stack operations needed to build `target` following the mentioned rules. If there are multiple valid answers, return **any of them**.

## Examples

### Example 1:
```
Input: target = [1,3], n = 3
Output: ["Push","Push","Pop","Push"]
Explanation: Initially the stack is [], and the stream is [1,2,3].
- Push 1 → stack: [1]
- Push 2 → stack: [1,2]
- Pop → stack: [1]
- Push 3 → stack: [1,3]
```

### Example 2:
```
Input: target = [1,2,3], n = 3
Output: ["Push","Push","Push"]
Explanation: Push all three numbers in order.
```

### Example 3:
```
Input: target = [1,2], n = 4
Output: ["Push","Push"]
Explanation: We only need 1 and 2, so we stop after pushing them.
```

## Constraints

- `1 <= target.length <= 100`
- `1 <= n <= 100`
- `1 <= target[i] <= n`
- `target` is strictly increasing.

## Topics
- Array
- Stack
- Simulation

## Solution Approach

This solution uses a **simulation with optimization**:

### Key Insight

Since numbers come from a sequential stream `[1, 2, 3, ..., n]`, we can:
1. **For numbers in target:** Just push them
2. **For numbers not in target:** Push and immediately pop

### Algorithm

1. **Iterate from 1 to the last target number** (no need to go further!)
2. **For each number `i`:**
   - If `i` matches `target[index]`: Add "Push", increment index
   - If `i` doesn't match: Add "Push" then "Pop" (skip this number)
3. **Return the operations list**

### Why This Works

- We process the stream sequentially (1, 2, 3, ...)
- For target numbers: we keep them (just Push)
- For non-target numbers: we discard them (Push + Pop)
- We stop at the last target number (no need to process beyond)

**Time Complexity:** O(m) where m is the maximum value in target
**Space Complexity:** O(m) for the operations list

## Related Problems

- [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences/) (Medium) - Validate if operations produce a sequence
- [1673. Find the Most Competitive Subsequence](https://leetcode.com/problems/find-the-most-competitive-subsequence/) (Medium) - Stack-based sequence building
- [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) (Easy) - Stack operations
- [155. Min Stack](https://leetcode.com/problems/min-stack/) (Medium) - Stack with special operations
- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) (Easy) - Stack fundamentals

|**Previous:** [Day 57](../Day_57/) | **Next:** [Day 59](../Day_59/)|
|---|---|

|**Date:** February 27, 2026|
|---|