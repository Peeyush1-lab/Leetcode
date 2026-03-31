# 744. Find Smallest Letter Greater Than Target

**Difficulty:** Easy
**Day:** 89
**Topics:** Array, Binary Search

## Problem Description

You are given an array of characters `letters` that is sorted in **non-decreasing order**, and a character `target`. There are **at least two different** characters in `letters`.

Return the smallest character in `letters` that is lexicographically greater than `target`. If such a character does not exist, return the first character in `letters`.

## Examples

### Example 1:

**Input:**
```
letters = ["c","f","j"], target = "a"
```

**Output:**
```
"c"
```

**Explanation:**
```
The smallest character that is lexicographically greater than 'a' in letters is 'c'.
```

### Example 2:

**Input:**
```
letters = ["c","f","j"], target = "c"
```

**Output:**
```
"f"
```

**Explanation:**
```
The smallest character that is lexicographically greater than 'c' in letters is 'f'.
```

### Example 3:

**Input:**
```
letters = ["x","x","y","y"], target = "z"
```

**Output:**
```
"x"
```

**Explanation:**
```
There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].
```

## Constraints

- `2 <= letters.length <= 10⁴`
- `letters[i]` is a lowercase English letter.
- `letters` is sorted in **non-decreasing** order.
- `letters` contains at least two different characters.
- `target` is a lowercase English letter.

## Topics

- Array
- Binary Search

## Similar Problems

- [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) - Easy
- [278. First Bad Version](https://leetcode.com/problems/first-bad-version/) - Easy
- [852. Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) - Medium
- [2300. Successful Pairs of Spells and Potions](https://leetcode.com/problems/successful-pairs-of-spells-and-potions/) - Medium

## Related Topics Problems

**Binary Search:**
- [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) - Medium
- [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) - Medium
- [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/) - Easy
- [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) - Medium
- [704. Binary Search](https://leetcode.com/problems/binary-search/) - Easy

**Ceiling/Floor Problems:**
- [374. Guess Number Higher or Lower](https://leetcode.com/problems/guess-number-higher-or-lower/) - Easy
- [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/) - Medium
- [1539. Kth Missing Positive Number](https://leetcode.com/problems/kth-missing-positive-number/) - Easy

---

|**Previous:** [Day 88](../Day_88/) | **Next:** [Day 90](../Day_90/)|
|---|---|

|**Date:** March 30, 2026|
|---|