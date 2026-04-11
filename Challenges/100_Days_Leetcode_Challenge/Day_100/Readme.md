# 287. Find the Duplicate Number

**Difficulty:** Medium
**Day:** 100
**Topics:** Array, Two Pointers, Binary Search, Bit Manipulation

## Problem Description

Given an array of integers `nums` containing `n + 1` integers where each integer is in the range `[1, n]` inclusive.

There is only **one repeated number** in `nums`, return this repeated number.

You must solve the problem **without** modifying the array `nums` and uses only constant extra space.

## Examples

### Example 1:

**Input:**
```
nums = [1,3,4,2,2]
```

**Output:**
```
2
```

### Example 2:

**Input:**
```
nums = [3,1,3,4,2]
```

**Output:**
```
3
```

### Example 3:

**Input:**
```
nums = [3,3,3,3,3]
```

**Output:**
```
3
```

## Constraints

- `1 <= n <= 10⁵`
- `nums.length == n + 1`
- `1 <= nums[i] <= n`
- All the integers in `nums` appear only **once** except for **precisely one integer** which appears **two or more** times.

## Follow up

- How can we prove that at least one duplicate number must exist in `nums`?
- Can you solve the problem in linear runtime complexity?

## Topics

- Array
- Two Pointers
- Binary Search
- Bit Manipulation

## Similar Problems

- [41. First Missing Positive](https://leetcode.com/problems/first-missing-positive/) - Hard
- [136. Single Number](https://leetcode.com/problems/single-number/) - Easy
- [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) - Medium
- [268. Missing Number](https://leetcode.com/problems/missing-number/) - Easy
- [645. Set Mismatch](https://leetcode.com/problems/set-mismatch/) - Easy

## Related Topics Problems

**Cycle Detection:**
- [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) - Easy
- [202. Happy Number](https://leetcode.com/problems/happy-number/) - Easy
- [457. Circular Array Loop](https://leetcode.com/problems/circular-array-loop/) - Medium

**Array:**
- [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) - Easy
- [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) - Easy
- [442. Find All Duplicates in an Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/) - Medium

---

|**Previous:** [Day 99](../Day_99/) | **Next:** [Day 101](../Day_101/)|
|---|---|

|**Date:** April 11, 2026|
|---|