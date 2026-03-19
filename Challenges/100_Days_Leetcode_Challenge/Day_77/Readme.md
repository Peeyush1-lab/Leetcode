# Day 77 - 2110. Number of Smooth Descent Periods of a Stock

**Difficulty:** Medium
**Topic Tags:** Array, Math, Dynamic Programming

---

## Problem Statement

You are given an integer array `prices` representing the daily price history of a stock. A **smooth descent period** of a stock consists of **one or more contiguous days** such that the price on each day is lower than the price on the preceding day by exactly `1`. In other words, a smooth descent period is a subarray where `prices[i] = prices[i-1] - 1` for all days in the period.

Return the **number of smooth descent periods**.

---

## Examples

**Example 1:**
```
Input:  prices = [3,2,1,4]
Output: 7

Explanation:
- [3], [2], [1], [4]         → 4 single-day periods
- [3,2], [2,1], [3,2,1]      → 3 multi-day periods
Total = 7
```

**Example 2:**
```
Input:  prices = [8,6,7,7]
Output: 4

Explanation: Only single-element subarrays qualify — [8], [6], [7], [7]
```

**Example 3:**
```
Input:  prices = [1]
Output: 1
```

---

## Constraints

- `1 <= prices.length <= 10^5`
- `1 <= prices[i] <= 10^5`

---

|**Previous:** [Day 76](../Day_76/) | **Next:** [Day 78](../Day_78/)|
|---|---|

|**Date:** March 19, 2026|
|---|