# 1351. Count Negative Numbers in a Sorted Matrix

**Difficulty:** Easy
**Day:** 83
**Topics:** Array, Binary Search, Matrix

## Problem Description

Given a `m x n` matrix `grid` which is sorted in **non-increasing** order both row-wise and column-wise, return the number of **negative** numbers in `grid`.

## Examples

### Example 1:

**Input:**
```
grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
```

**Output:**
```
8
```

**Explanation:**
```
There are 8 negatives number in the matrix.
```

### Example 2:

**Input:**
```
grid = [[3,2],[1,0]]
```

**Output:**
```
0
```

### Example 3:

**Input:**
```
grid = [[1,-1],[-1,-1]]
```

**Output:**
```
3
```

## Constraints

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 100`
- `-100 <= grid[i][j] <= 100`

## Follow-up

Could you find an `O(n + m)` solution?

## Topics

- Array
- Binary Search
- Matrix

## Similar Problems

- [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) - Medium
- [240. Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) - Medium
- [378. Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) - Medium
- [1337. The K Weakest Rows in a Matrix](https://leetcode.com/problems/the-k-weakest-rows-in-a-matrix/) - Easy
- [1346. Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/) - Easy

## Related Topics Problems

**Matrix Search:**
- [48. Rotate Image](https://leetcode.com/problems/rotate-image/) - Medium
- [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) - Medium
- [73. Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) - Medium
- [766. Toeplitz Matrix](https://leetcode.com/problems/toeplitz-matrix/) - Easy

**Binary Search:**
- [35. Search Insert Position](https://leetcode.com/problems/search-insert-position/) - Easy
- [704. Binary Search](https://leetcode.com/problems/binary-search/) - Easy
- [1095. Find in Mountain Array](https://leetcode.com/problems/find-in-mountain-array/) - Hard
- [1283. Find the Smallest Divisor Given a Threshold](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/) - Medium

**Sorted Matrix:**
- [668. Kth Smallest Number in Multiplication Table](https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/) - Hard
- [878. Nth Magical Number](https://leetcode.com/problems/nth-magical-number/) - Hard

---

|**Previous:** [Day 82](../Day_82/) | **Next:** [Day 84](../Day_84/)|
|---|---|

|**Date:** March 24, 2026|
|---|