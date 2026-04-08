# 167. Two Sum II - Input Array Is Sorted

**Difficulty:** Medium
**Day:** 97
**Topics:** Array, Two Pointers, Binary Search

## Problem Description

Given a **1-indexed** array of integers `numbers` that is already **sorted in non-decreasing order**, find two numbers such that they add up to a specific `target` number. Let these two numbers be `numbers[index₁]` and `numbers[index₂]` where `1 <= index₁ < index₂ <= numbers.length`.

Return the indices of the two numbers, `index₁` and `index₂`, **added by one** as an integer array `[index₁, index₂]` of length 2.

The tests are generated such that there is **exactly one solution**. You **may not** use the same element twice.

Your solution must use only constant extra space.

## Examples

### Example 1:

**Input:**
```
numbers = [2,7,11,15], target = 9
```

**Output:**
```
[1,2]
```

**Explanation:**
```
The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
```

### Example 2:

**Input:**
```
numbers = [2,3,4], target = 6
```

**Output:**
```
[1,3]
```

**Explanation:**
```
The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
```

### Example 3:

**Input:**
```
numbers = [-1,0], target = -1
```

**Output:**
```
[1,2]
```

**Explanation:**
```
The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
```

## Constraints

- `2 <= numbers.length <= 3 * 10⁴`
- `-1000 <= numbers[i] <= 1000`
- `numbers` is sorted in **non-decreasing order**.
- `-1000 <= target <= 2000`
- The tests are generated such that there is **exactly one solution**.

## Topics

- Array
- Two Pointers
- Binary Search

## Similar Problems

- [1. Two Sum](https://leetcode.com/problems/two-sum/) - Easy
- [15. 3Sum](https://leetcode.com/problems/3sum/) - Medium
- [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest/) - Medium
- [18. 4Sum](https://leetcode.com/problems/4sum/) - Medium
- [653. Two Sum IV - BST](https://leetcode.com/problems/two-sum-iv-bst/) - Easy
- [1099. Two Sum Less Than K](https://leetcode.com/problems/two-sum-less-than-k/) - Easy

## Related Topics Problems

**Two Pointers:**
- [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/) - Medium
- [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) - Easy
- [27. Remove Element](https://leetcode.com/problems/remove-element/) - Easy
- [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/) - Easy
- [344. Reverse String](https://leetcode.com/problems/reverse-string/) - Easy
- [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) - Easy

**Array:**
- [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) - Easy
- [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) - Easy
- [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) - Easy

---

|**Previous:** [Day 96](../Day_96/) | **Next:** [Day 98](../Day_98/)|
|---|---|

|**Date:** April 08, 2026|
|---|