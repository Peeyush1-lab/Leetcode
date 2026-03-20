# Day 78 - 3652. Best Time to Buy and Sell Stock using Strategy

**Difficulty:** Medium
**Topic Tags:** Array, Prefix Sum, Sliding Window

---

## Problem Statement

You are given two integer arrays `prices` and `strategy`, where:

- `prices[i]` is the price of a stock on the `i-th` day.
- `strategy[i]` represents a trading action on the `i-th` day:
  - `-1` → buy one unit
  - `0` → hold
  - `1` → sell one unit

You are also given an even integer `k`. You may perform **at most one modification** to `strategy`:

- Select exactly `k` consecutive elements.
- Set the **first `k/2` elements** to `0` (hold).
- Set the **last `k/2` elements** to `1` (sell).

The profit is defined as `Σ strategy[i] * prices[i]` across all days.

Return the **maximum possible profit** you can achieve.

> **Note:** There are no constraints on budget or stock ownership — all operations are always feasible.

---

## Examples

**Example 1:**
```
Input:  prices = [4,3,2,10], strategy = [-1,0,0,1], k = 2
Output: 10

Explanation: Modify window [0,1] → strategy becomes [0,1,0,1]
Profit = 0*4 + 1*3 + 0*2 + 1*10 = 13...
(check with original: -1*4+0*3+0*2+1*10 = 6)
Best modification gives profit = 10
```

**Example 2:**
```
Input:  prices = [1,2,3], strategy = [-1,0,1], k = 2
Output: 9

Explanation: No modification improves the original strategy.
```

---

## Constraints

- `2 <= prices.length <= 10^5`
- `prices.length == strategy.length`
- `1 <= prices[i] <= 10^4`
- `strategy[i]` is `-1`, `0`, or `1`
- `2 <= k <= prices.length`
- `k` is even

---

|**Previous:** [Day 77](../Day_77/) | **Next:** [Day 79](../Day_79/)|
|---|---|

|**Date:** March 20, 2026|
|---|