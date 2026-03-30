# 34. Find First and Last Position of Element in Sorted Array

**Difficulty:** Medium
**Day:** 88
**Topics:** Array, Binary Search

## Problem Description

Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending position of a given `target` value.

If `target` is not found in the array, return `[-1, -1]`.

You must write an algorithm with `O(log n)` runtime complexity.

## Examples

### Example 1:

**Input:**
```
nums = [5,7,7,8,8,10], target = 8
```

**Output:**
```
[3,4]
```

### Example 2:

**Input:**
```
nums = [5,7,7,8,8,10], target = 6
```

**Output:**
```
[-1,-1]
```

### Example 3:

**Input:**
```
nums = [], target = 0
```

**Output:**
```
[-1,-1]
```

## Constraints

- `0 <= nums.length <= 10⁵`
- `-10⁹ <= nums[i] <= 10⁹`
- `nums` is a non-decreasing array.
- `-10⁹ <= target <= 10⁹`

## Topics

- Array
- Binary Search

## Similar Problems

- [278. First Bad Version](https://leetcode.com/problems/first-bad-version/) - Easy
- [702. Search in a Sorted Array of Unknown Size](https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/) - Medium
- [2055. Plates Between Candles](https://leetcode.com/problems/plates-between-candles/) - Medium
- [2143. Choose Numbers From Two Arrays in Range](https://leetcode.com/problems/choose-numbers-from-two-arrays-in-range/) - Hard

## Related Topics Problems

**Binary Search Variants:**
- [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) - Medium
- [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) - Easy
- [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/) - Easy
- [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) - Medium
- [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/) - Medium
- [704. Binary Search](https://leetcode.com/problems/binary-search/) - Easy

**Array Search:**
- [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) - Hard
- [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) - Medium
- [240. Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) - Medium
- [611. Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number/) - Medium

---

|**Previous:** [Day 87](../Day_87/) | **Next:** [Day 89](../Day_89/)|
|---|---|

|**Date:** March 30, 2026|
|---|