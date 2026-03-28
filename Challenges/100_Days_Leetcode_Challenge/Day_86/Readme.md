# 724. Find Pivot Index

**Difficulty:** Easy
**Day:** 86
**Topics:** Array, Prefix Sum

## Problem Description

Given an array of integers `nums`, calculate the **pivot index** of this array.

The **pivot index** is the index where the sum of all the numbers **strictly** to the left of the index is equal to the sum of all the numbers **strictly** to the right of the index.

If the index is on the left edge of the array, then the left sum is `0` because there are no elements to the left. This also applies to the right edge of the array.

Return the **leftmost pivot index**. If no such index exists, return `-1`.

## Examples

### Example 1:

**Input:**
```
nums = [1,7,3,6,5,6]
```

**Output:**
```
3
```

**Explanation:**
```
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11
```

### Example 2:

**Input:**
```
nums = [1,2,3]
```

**Output:**
```
-1
```

**Explanation:**
```
There is no index that satisfies the conditions in the problem statement.
```

### Example 3:

**Input:**
```
nums = [2,1,-1]
```

**Output:**
```
0
```

**Explanation:**
```
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0
```

## Constraints

- `1 <= nums.length <= 10⁴`
- `-1000 <= nums[i] <= 1000`

## Note

This question is the same as [1991. Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/).

## Topics

- Array
- Prefix Sum

## Similar Problems

- [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) - Medium
- [1991. Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) - Easy
- [2270. Number of Ways to Split Array](https://leetcode.com/problems/number-of-ways-to-split-array/) - Medium
- [2574. Left and Right Sum Differences](https://leetcode.com/problems/left-and-right-sum-differences/) - Easy

## Related Topics Problems

**Prefix Sum:**
- [303. Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/) - Easy
- [304. Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) - Medium
- [525. Contiguous Array](https://leetcode.com/problems/contiguous-array/) - Medium
- [974. Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) - Medium
- [1480. Running Sum of 1d Array](https://leetcode.com/problems/running-sum-of-1d-array/) - Easy

**Array Manipulation:**
- [27. Remove Element](https://leetcode.com/problems/remove-element/) - Easy
- [485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/) - Easy
- [561. Array Partition](https://leetcode.com/problems/array-partition/) - Easy
- [1071. Greatest Common Divisor of Strings](https://leetcode.com/problems/greatest-common-divisor-of-strings/) - Easy

---

|**Previous:** [Day 85](../Day_85/) | **Next:** [Day 87](../Day_87/)|
|---|---|

|**Date:** March 28, 2026|
|---|