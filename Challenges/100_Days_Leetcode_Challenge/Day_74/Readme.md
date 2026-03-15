# Day 74 - 1695. Maximum Erasure Value

**Difficulty:** Medium
**Topic Tags:** Array, Sliding Window, Hash Table

---

## Problem Statement

You are given an array of positive integers `nums`. You want to erase a **subarray** containing **unique elements** only. The score you get by erasing the subarray is equal to the **sum of its elements**.

Return the **maximum score** you can get by erasing exactly one subarray.

A subarray is a contiguous part of the array.

---

## Examples

**Example 1:**
```
Input:  nums = [4,2,4,5,6]
Output: 17

Explanation: Erase subarray [2,4,5,6] → score = 17
```

**Example 2:**
```
Input:  nums = [5,2,1,2,5,2,1,2,5]
Output: 8

Explanation: Erase subarray [5,2,1] or [1,2,5] → score = 8
```

---

## Constraints

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^4`

|**Previous:** [Day 73](../Day_73/) | **Next:** [Day 75](../Day_75/)|
|---|---|

|**Date:** March 16, 2026|
|---|