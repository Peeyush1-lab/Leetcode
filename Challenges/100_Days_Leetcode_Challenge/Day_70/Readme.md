# Day 70 - 1910. Remove All Occurrences of a Substring

**Difficulty:** Medium
**Topic Tags:** String, Simulation

---

## Problem Statement

Given two strings `s` and `part`, perform the following operation repeatedly until `part` no longer occurs in `s`:

- Find the **leftmost** occurrence of `part` in `s` and remove it.

Return `s` after all such operations have been performed.

---

## Examples

**Example 1:**
```
Input:  s = "daabcbaabcbc", part = "abc"
Output: "dab"

Explanation:
- "daabcbaabcbc" → remove "abc" at index 2 → "dabaabcbc"
- "dabaabcbc"    → remove "abc" at index 4 → "dababc"  (wait — re-check)
- Actually step by step:
  "daabcbaabcbc" → "dabaabcbc" → "dababc" → "dab"
```

**Example 2:**
```
Input:  s = "axxxxyyyyb", part = "xy"
Output: "ab"
```

---

## Constraints

- `1 <= s.length <= 1000`
- `1 <= part.length <= 1000`
- `s` and `part` consist of lowercase English letters only.

|**Previous:** [Day 69](../Day_69/) | **Next:** [Day 71](../Day_71/)|
|---|---|

|**Date:** March 12, 2026|
|---|