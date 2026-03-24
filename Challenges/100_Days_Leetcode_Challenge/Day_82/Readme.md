# 42. Trapping Rain Water

**Difficulty:** Hard
**Day:** 82
**Topics:** Array, Two Pointers, Dynamic Programming, Stack, Monotonic Stack

## Problem Description

Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

## Examples

### Example 1:

![Trapping Rain Water](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

**Input:**
```
height = [0,1,0,2,1,0,1,3,2,1,2,1]
```

**Output:**
```
6
```

**Explanation:**
```
The elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.
```

### Example 2:

**Input:**
```
height = [4,2,0,3,2,5]
```

**Output:**
```
9
```

## Constraints

- `n == height.length`
- `1 <= n <= 2 * 10⁴`
- `0 <= height[i] <= 10⁵`

## Topics

- Array
- Two Pointers
- Dynamic Programming
- Stack
- Monotonic Stack

## Similar Problems

- [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/) - Medium
- [407. Trapping Rain Water II](https://leetcode.com/problems/trapping-rain-water-ii/) - Hard
- [755. Pour Water](https://leetcode.com/problems/pour-water/) - Medium
- [1219. Path with Maximum Gold](https://leetcode.com/problems/path-with-maximum-gold/) - Medium
- [2245. Maximum Trailing Zeros in a Cornered Path](https://leetcode.com/problems/maximum-trailing-zeros-in-a-cornered-path/) - Medium

## Related Topics Problems

**Two Pointers:**
- [15. 3Sum](https://leetcode.com/problems/3sum/) - Medium
- [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) - Medium
- [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) - Easy
- [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/) - Easy

**Stack/Monotonic Stack:**
- [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Hard
- [85. Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/) - Hard
- [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) - Easy
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Medium

**Dynamic Programming:**
- [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) - Medium
- [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) - Easy
- [198. House Robber](https://leetcode.com/problems/house-robber/) - Medium

---

|**Previous:** [Day 81](../Day_81/) | **Next:** [Day 83](../Day_83/)|
|---|---|

|**Date:** March 23, 2026|
|---|