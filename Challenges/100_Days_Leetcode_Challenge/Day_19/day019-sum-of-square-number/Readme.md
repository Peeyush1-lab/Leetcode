# Day 19 (Problem 2): Sum of Square Numbers

## Problem Statement

Given a non-negative integer `c`, decide whether there are two integers `a` and `b` such that `a² + b² = c`.

## Examples

### Example 1
**Input:** `c = 5`
**Output:** `true`
**Explanation:** `1² + 2² = 1 + 4 = 5`

### Example 2
**Input:** `c = 3`
**Output:** `false`

### Example 3
**Input:** `c = 4`
**Output:** `true`
**Explanation:** `0² + 2² = 0 + 4 = 4`

## Constraints

- `0 <= c <= 2^31 - 1`

## Key Points

- Find if c can be expressed as sum of two squares
- Numbers can be 0
- Two pointer approach works well
- Need to handle large numbers (use long)

## Approach

**Strategy:** Two Pointers

1. Start with `a = 0`, `b = √c`
2. Calculate `sum = a² + b²`
3. If sum equals c, found!
4. If sum < c, increment a
5. If sum > c, decrement b

**Complexity:**
- Time: O(√c)
- Space: O(1)