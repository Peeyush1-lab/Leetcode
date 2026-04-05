# 1539. Kth Missing Positive Number

**Difficulty:** Easy  
**Day:** 94  
**Topics:** Array, Binary Search

## Problem Description

Given an array `arr` of positive integers sorted in a **strictly increasing order**, and an integer `k`.

Return the `kth` **positive** integer that is **missing** from this array.

## Examples

### Example 1:

**Input:**
```
arr = [2,3,4,7,11], k = 5
```

**Output:**
```
9
```

**Explanation:**
```
The missing positive integers are [1,5,6,8,9,10,12,13,...]. 
The 5th missing positive integer is 9.
```

### Example 2:

**Input:**
```
arr = [1,2,3,4], k = 2
```

**Output:**
```
6
```

**Explanation:**
```
The missing positive integers are [5,6,7,...]. 
The 2nd missing positive integer is 6.
```

## Constraints

- `1 <= arr.length <= 1000`
- `1 <= arr[i] <= 1000`
- `1 <= k <= 1000`
- `arr[i] < arr[j]` for `1 <= i < j <= arr.length`

## Follow up

Could you solve this problem in less than O(n)?

## Topics

- Array
- Binary Search

## Similar Problems

- [268. Missing Number](https://leetcode.com/problems/missing-number/) - Easy
- [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) - Easy
- [1060. Missing Element in Sorted Array](https://leetcode.com/problems/missing-element-in-sorted-array/) - Medium
- [2195. Append K Integers With Minimal Sum](https://leetcode.com/problems/append-k-integers-with-minimal-sum/) - Medium
- [2554. Maximum Number of Integers to Choose From a Range I](https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i/) - Medium

## Related Topics Problems

**Binary Search:**
- [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) - Easy
- [278. First Bad Version](https://leetcode.com/problems/first-bad-version/) - Easy
- [704. Binary Search](https://leetcode.com/problems/binary-search/) - Easy
- [744. Find Smallest Letter Greater Than Target](https://leetcode.com/problems/find-smallest-letter-greater-than-target/) - Easy

**Missing Numbers:**
- [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/) - Hard
- [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) - Medium
- [645. Set Mismatch](https://leetcode.com/problems/set-mismatch/) - Easy

---

|**Previous:** [Day 93](../Day_93/) | **Next:** [Day 95](../Day_95/)|
|---|---|

|**Date:** April 05, 2026|
|---|