# 2762. Continuous Subarrays

**Difficulty:** Medium
**Day:** 84
**Topics:** Array, Queue, Sliding Window, Heap (Priority Queue), Ordered Set, Monotonic Queue

## Problem Description

You are given a **0-indexed** integer array `nums`. A subarray of `nums` is called **continuous** if:

Let `i`, `i + 1`, ..., `j` be the indices in the subarray. Then, for each pair of indices `i <= i1, i2 <= j`, `0 <= |nums[i1] - nums[i2]| <= 2`.

Return the total number of **continuous** subarrays.

A subarray is a contiguous **non-empty** sequence of elements within an array.

## Examples

### Example 1:

**Input:**
```
nums = [5,4,2,4]
```

**Output:**
```
8
```

**Explanation:**
```
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
Total continuous subarrays = 4 + 3 + 1 = 8.

Note: [5,4,2] is not continuous because |5 - 2| = 3 > 2.
```

### Example 2:

**Input:**
```
nums = [1,2,3]
```

**Output:**
```
6
```

**Explanation:**
```
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.
```

## Constraints

- `1 <= nums.length <= 10⁵`
- `1 <= nums[i] <= 10⁹`

## Topics

- Array
- Queue
- Sliding Window
- Heap (Priority Queue)
- Ordered Set
- Monotonic Queue

## Similar Problems

- [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Hard
- [395. Longest Substring with At Least K Repeating Characters](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/) - Medium
- [713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) - Medium
- [904. Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) - Medium
- [992. Subarrays with K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) - Hard
- [1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) - Medium

## Related Topics Problems

**Monotonic Queue:**
- [862. Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) - Hard
- [1696. Jump Game VI](https://leetcode.com/problems/jump-game-vi/) - Medium
- [1825. Finding MK Average](https://leetcode.com/problems/finding-mk-average/) - Hard

**Sliding Window:**
- [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) - Medium
- [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Hard
- [209. Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) - Medium
- [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) - Medium

---

|**Previous:** [Day 83](../Day_83/) | **Next:** [Day 85](../Day_85/)|
|---|---|

|**Date:** March 26, 2026|
|---|