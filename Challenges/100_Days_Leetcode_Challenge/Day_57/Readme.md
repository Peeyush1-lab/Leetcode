# 316. Remove Duplicate Letters

**Difficulty:** Medium

## Problem Description

Given a string `s`, remove duplicate letters so that every letter appears once and only once. You must make sure your result is **the smallest in lexicographical order** among all possible results.

## Examples

### Example 1:
```
Input: s = "bcabc"
Output: "abc"
```

### Example 2:
```
Input: s = "cbacdcbc"
Output: "acdb"
```

## Constraints

- `1 <= s.length <= 10^4`
- `s` consists of lowercase English letters.

## Topics
- String
- Stack
- Greedy
- Monotonic Stack

## Solution Approach

This solution uses a **Monotonic Stack + Greedy** approach with three key data structures:

### Key Data Structures

1. **Frequency Array (`freq`):** Track remaining occurrences of each character
2. **Visited Array (`visited`):** Track if character is already in result
3. **Stack (`st`):** Build the lexicographically smallest result

### Algorithm

1. **Count frequencies** of all characters
2. **For each character:**
   - Decrease its frequency (we've seen one occurrence)
   - If already in result, skip it (avoid duplicates)
   - While stack top is greater than current char AND stack top appears later:
     - Pop stack top (we can use it later)
     - Mark it as not visited
   - Push current character
   - Mark it as visited
3. **Convert stack to string**

### Why This Works

The greedy strategy: **Remove larger characters that appear later to make room for smaller characters now.**

The stack maintains a **monotonically increasing** (or equal) sequence where we only violate it if we have future opportunities to add the removed character.

**Time Complexity:** O(n) - Each character pushed/popped at most once
**Space Complexity:** O(1) - Arrays of fixed size 26

## Related Problems

- [1081. Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) (Medium) - Identical problem
- [402. Remove K Digits](https://leetcode.com/problems/remove-k-digits/) (Medium) - Similar monotonic stack pattern
- [321. Create Maximum Number](https://leetcode.com/problems/create-maximum-number/) (Hard) - Extension of the concept
- [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) (Easy) - Monotonic stack basics
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) (Medium) - Monotonic stack application

|**Previous:** [Day 56](../Day_56/) | **Next:** [Day 58](../Day_58/)|
|---|---|

|**Date:** February 26, 2026|
|---|