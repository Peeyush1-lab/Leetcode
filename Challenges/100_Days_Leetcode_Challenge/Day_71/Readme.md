# Day 71 - 424. Longest Repeating Character Replacement

**Difficulty:** Medium
**Topic Tags:** String, Sliding Window

---

## Problem Statement

You are given a string `s` and an integer `k`. You can choose **any character** in the string and change it to any other uppercase English letter. You can perform this operation **at most `k` times**.

Return the **length of the longest substring** containing the same letter you can get after performing the above operations.

---

## Examples

**Example 1:**
```
Input:  s = "ABAB", k = 2
Output: 4

Explanation: Replace the 2 'A's with 'B's or vice versa → "BBBB" or "AAAA"
```

**Example 2:**
```
Input:  s = "AABABBA", k = 1
Output: 4

Explanation: Replace the one 'A' in "AABA" → "AAAA", length = 4
```

---

## Constraints

- `1 <= s.length <= 10^5`
- `s` consists of only uppercase English letters.
- `0 <= k <= s.length`

|**Previous:** [Day 70](../Day_70/) | **Next:** [Day 72](../Day_72/)|
|---|---|

|**Date:** March 13, 2026|
|---|