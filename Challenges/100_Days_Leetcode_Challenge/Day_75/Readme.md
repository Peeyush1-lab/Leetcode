# Day 75 - 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

**Difficulty:** Medium
**Topic Tags:** Array, Sliding Window

---

## Problem Statement

Given an array of integers `arr`, an integer `k`, and an integer `threshold`, return the **number of sub-arrays of size `k`** whose average is greater than or equal to `threshold`.

---

## Examples

**Example 1:**
```
Input:  arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
Output: 3

Explanation: Sub-arrays [2,5,5], [5,5,5], [5,5,8] have averages 4, 5, 6 — all >= 4
```

**Example 2:**
```
Input:  arr = [1,1,1,1,1], k = 1, threshold = 0
Output: 5
```

---

## Constraints

- `1 <= arr.length <= 10^5`
- `1 <= arr[i] <= 10^4`
- `1 <= k <= arr.length`
- `0 <= threshold <= 10^4`

---

|**Previous:** [Day 74](../Day_74/) | **Next:** [Day 76](../Day_76/)|
|---|---|

|**Date:** March 17, 2026|
|---|