# 1346. Check If N and Its Double Exist

**Difficulty:** Easy
**Day:** 90
**Topics:** Array, Hash Table, Two Pointers, Binary Search, Sorting

## Problem Description

Given an array `arr` of integers, check if there exist two indices `i` and `j` such that:

- `i != j`
- `0 <= i, j < arr.length`
- `arr[i] == 2 * arr[j]`

## Examples

### Example 1:

**Input:**
```
arr = [10,2,5,3]
```

**Output:**
```
true
```

**Explanation:**
```
For i = 0 and j = 2, arr[0] == 10 == 2 * 5 == 2 * arr[2]
```

### Example 2:

**Input:**
```
arr = [3,1,7,11]
```

**Output:**
```
false
```

**Explanation:**
```
There is no i and j that satisfy the conditions.
```

## Constraints

- `2 <= arr.length <= 500`
- `-10³ <= arr[i] <= 10³`

## Topics

- Array
- Hash Table
- Two Pointers
- Binary Search
- Sorting

## Similar Problems

- [1. Two Sum](https://leetcode.com/problems/two-sum/) - Easy
- [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) - Medium
- [653. Two Sum IV - BST](https://leetcode.com/problems/two-sum-iv-bst/) - Easy
- [2154. Keep Multiplying Found Values by Two](https://leetcode.com/problems/keep-multiplying-found-values-by-two/) - Easy
- [2367. Number of Arithmetic Triplets](https://leetcode.com/problems/number-of-arithmetic-triplets/) - Easy

## Related Topics Problems

**Hash Table:**
- [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) - Easy
- [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) - Easy
- [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) - Easy
- [350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/) - Easy

**Two Pointers:**
- [15. 3Sum](https://leetcode.com/problems/3sum/) - Medium
- [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/) - Easy
- [344. Reverse String](https://leetcode.com/problems/reverse-string/) - Easy
- [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) - Easy

---

|**Previous:** [Day 89](../Day_89/) | **Next:** [Day 91](../Day_91/)|
|---|---|

|**Date:** April 01, 2026|
|---|