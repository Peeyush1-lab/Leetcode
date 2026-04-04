# 1608. Special Array With X Elements Greater Than or Equal X

**Difficulty:** Easy
**Day:** 93
**Topics:** Array, Binary Search, Sorting

## Problem Description

You are given an array `nums` of non-negative integers. `nums` is considered **special** if there exists a number `x` such that there are **exactly** `x` numbers in `nums` that are **greater than or equal to** `x`.

Notice that `x` **does not** have to be an element in `nums`.

Return `x` if the array is **special**, otherwise, return `-1`. It can be proven that if `nums` is special, the value for `x` is **unique**.

## Examples

### Example 1:

**Input:**
```
nums = [3,5]
```

**Output:**
```
2
```

**Explanation:**
```
There are 2 values (3 and 5) that are greater than or equal to 2.
```

### Example 2:

**Input:**
```
nums = [0,0]
```

**Output:**
```
-1
```

**Explanation:**
```
No numbers fit the criteria for x.
If x = 0, there should be 0 numbers >= 0, but there are 2.
If x = 1, there should be 1 number >= 1, but there are 0.
If x = 2, there should be 2 numbers >= 2, but there are 0.
x cannot be greater since there are only 2 numbers in nums.
```

### Example 3:

**Input:**
```
nums = [0,4,3,0,4]
```

**Output:**
```
3
```

**Explanation:**
```
There are 3 values that are greater than or equal to 3.
```

## Constraints

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 1000`

## Topics

- Array
- Binary Search
- Sorting
- Counting

## Similar Problems

- [1299. Replace Elements with Greatest Element on Right Side](https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/) - Easy
- [1534. Count Good Triplets](https://leetcode.com/problems/count-good-triplets/) - Easy
- [2274. Maximum Consecutive Floors Without Special Floors](https://leetcode.com/problems/maximum-consecutive-floors-without-special-floors/) - Medium

## Related Topics Problems

**Counting:**
- [169. Majority Element](https://leetcode.com/problems/majority-element/) - Easy
- [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Medium
- [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) - Easy
- [1189. Maximum Number of Balloons](https://leetcode.com/problems/maximum-number-of-balloons/) - Easy

**Array:**
- [1. Two Sum](https://leetcode.com/problems/two-sum/) - Easy
- [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) - Easy
- [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/) - Easy

---

|**Previous:** [Day 92](../Day_92/) | **Next:** [Day 94](../Day_94/)|
|---|---|

|**Date:** April 04, 2026|
|---|