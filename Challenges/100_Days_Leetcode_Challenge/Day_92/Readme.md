# 1385. Find the Distance Value Between Two Arrays

**Difficulty:** Easy  
**Day:** 92  
**Topics:** Array, Two Pointers, Binary Search, Sorting

## Problem Description

Given two integer arrays `arr1` and `arr2`, and the integer `d`, return the **distance value** between the two arrays.

The distance value is defined as the number of elements `arr1[i]` such that there is **not** any element `arr2[j]` where `|arr1[i] - arr2[j]| <= d`.

## Examples

### Example 1:

**Input:**
```
arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
```

**Output:**
```
2
```

**Explanation:**
```
For arr1[0] = 4 we have:
|4 - 10| = 6 > d = 2
|4 - 9| = 5 > d = 2
|4 - 1| = 3 > d = 2
|4 - 8| = 4 > d = 2
So arr1[0] = 4 is valid.

For arr1[1] = 5 we have:
|5 - 10| = 5 > d = 2
|5 - 9| = 4 > d = 2
|5 - 1| = 4 > d = 2
|5 - 8| = 3 > d = 2
So arr1[1] = 5 is valid.

For arr1[2] = 8 we have:
|8 - 10| = 2 <= d = 2
So arr1[2] = 8 is NOT valid.

We return 2.
```

### Example 2:

**Input:**
```
arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
```

**Output:**
```
2
```

### Example 3:

**Input:**
```
arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
```

**Output:**
```
1
```

## Constraints

- `1 <= arr1.length, arr2.length <= 500`
- `-1000 <= arr1[i], arr2[j] <= 1000`
- `0 <= d <= 100`

## Topics

- Array
- Two Pointers
- Binary Search
- Sorting

## Similar Problems

- [1855. Maximum Distance Between a Pair of Values](https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/) - Medium
- [2563. Count the Number of Fair Pairs](https://leetcode.com/problems/count-the-number-of-fair-pairs/) - Medium
- [2856. Minimum Array Length After Pair Removals](https://leetcode.com/problems/minimum-array-length-after-pair-removals/) - Medium

## Related Topics Problems

**Binary Search:**
- [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) - Easy
- [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/) - Easy
- [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target/) - Easy
- [278. First Bad Version](https://leetcode.com/problems/first-bad-version/) - Easy

**Array Distance:**
- [530. Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/) - Easy
- [532. K-diff Pairs in an Array](https://leetcode.com/problems/k-diff-pairs-in-an-array/) - Medium
- [1198. Find Smallest Common Element in All Rows](https://leetcode.com/problems/find-smallest-common-element-in-all-rows/) - Medium

---

|**Previous:** [Day 91](../Day_91/) | **Next:** [Day 93](../Day_93/)|
|---|---|

|**Date:** April 03, 2026|
|---|