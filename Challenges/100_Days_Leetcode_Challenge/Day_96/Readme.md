# 2529. Maximum Count of Positive Integer and Negative Integer

**Difficulty:** Easy
**Day:** 96
**Topics:** Array, Binary Search, Counting

## Problem Description

Given an array `nums` sorted in **non-decreasing** order, return the maximum between the number of positive integers and the number of negative integers.

- In other words, if the number of positive integers in `nums` is `pos` and the number of negative integers is `neg`, then return the maximum of `pos` and `neg`.

**Note** that `0` is neither positive nor negative.

## Examples

### Example 1:

**Input:**
```
nums = [-2,-1,-1,1,2,3]
```

**Output:**
```
3
```

**Explanation:**
```
There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
```

### Example 2:

**Input:**
```
nums = [-3,-2,-1,0,0,1,2]
```

**Output:**
```
3
```

**Explanation:**
```
There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
```

### Example 3:

**Input:**
```
nums = [5,20,66,1314]
```

**Output:**
```
4
```

**Explanation:**
```
There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
```

## Constraints

- `1 <= nums.length <= 2000`
- `-2000 <= nums[i] <= 2000`
- `nums` is sorted in a **non-decreasing** order.

## Follow up

Can you solve the problem in `O(log(n))` time complexity?

## Topics

- Array
- Binary Search
- Counting

## Similar Problems

- [34. Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) - Medium
- [278. First Bad Version](https://leetcode.com/problems/first-bad-version/) - Easy
- [374. Guess Number Higher or Lower](https://leetcode.com/problems/guess-number-higher-or-lower/) - Easy
- [1351. Count Negative Numbers in a Sorted Matrix](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/) - Easy

## Related Topics Problems

**Binary Search:**
- [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) - Easy
- [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/) - Easy
- [704. Binary Search](https://leetcode.com/problems/binary-search/) - Easy
- [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target/) - Easy

**Counting:**
- [169. Majority Element](https://leetcode.com/problems/majority-element/) - Easy
- [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) - Easy
- [1534. Count Good Triplets](https://leetcode.com/problems/count-good-triplets/) - Easy

---

|**Previous:** [Day 95](../Day_95/) | **Next:** [Day 97](../Day_97/)|
|---|---|

|**Date:** April 07, 2026|
|---|