# Day 72 - 1052. Grumpy Bookstore Owner

**Difficulty:** Medium
**Topic Tags:** Array, Sliding Window

---

## Problem Statement

There is a bookstore owner with a shop open for `n` minutes. You are given two integer arrays `customers` and `grumpy`, both of length `n`, where:

- `customers[i]` is the number of customers that enter the shop during the `i-th` minute.
- `grumpy[i]` is `1` if the owner is grumpy during that minute, and `0` otherwise.

When the owner is grumpy, the customers in that minute are **not satisfied**. When the owner is not grumpy, they are always satisfied.

The owner knows a secret technique to keep themselves **not grumpy for `minutes` consecutive minutes**, but can only use it **once**.

Return the **maximum number of customers** that can be satisfied throughout the day.

---

## Examples

**Example 1:**
```
Input:  customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
Output: 16

Explanation: The owner uses the technique for the last 3 minutes (index 5,6,7).
Base satisfied (grumpy=0): 1+1+1+7 = 10
Extra from window [5,6,7]: 1+0+5 = 6  → total = 16
```

**Example 2:**
```
Input:  customers = [1], grumpy = [0], minutes = 1
Output: 1
```

---

## Constraints

- `n == customers.length == grumpy.length`
- `1 <= minutes <= n <= 2 * 10^4`
- `0 <= customers[i] <= 1000`
- `grumpy[i]` is either `0` or `1`

|**Previous:** [Day 71](../Day_71/) | **Next:** [Day 73](../Day_73/)|
|---|---|

|**Date:** March 14, 2026|
|---|