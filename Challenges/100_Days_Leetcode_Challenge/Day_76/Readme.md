# Day 76 - 1358. Number of Substrings Containing All Three Characters

**Difficulty:** Medium
**Topic Tags:** String, Sliding Window, Hash Table

---

## Problem Statement

Given a string `s` consisting only of characters `'a'`, `'b'`, and `'c'`, return the **number of substrings** that contain at least one occurrence of all three characters.

---

## Examples

**Example 1:**
```
Input:  s = "abcabc"
Output: 10

Explanation: Substrings containing all three: "abc", "abca", "abcab", "abcabc",
             "bca", "bcab", "bcabc", "cab", "cabc", "abc"
```

**Example 2:**
```
Input:  s = "aaacb"
Output: 3

Explanation: "aaacb", "aacb", "acb"
```

**Example 3:**
```
Input:  s = "abc"
Output: 1
```

---

## Constraints

- `3 <= s.length <= 5 * 10^4`
- `s` consists only of `'a'`, `'b'`, and `'c'`.

---

|**Previous:** [Day 75](../Day_75/) | **Next:** [Day 77](../Day_77/)|
|---|---|

|**Date:** March 18, 2026|
|---|