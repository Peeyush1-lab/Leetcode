# 1572. Matrix Diagonal Sum

**Difficulty:** Easy
**Day:** 80
**Topics:** Array, Matrix

## Problem Description

Given a square matrix `mat`, return the sum of the matrix diagonals.

Only include the sum of all the elements on the **primary diagonal** and all the elements on the **secondary diagonal** that are not part of the primary diagonal.

## Examples

### Example 1:

![Example 1](https://assets.leetcode.com/uploads/2020/08/14/sample_1911.png)

**Input:**
```
mat = [[1,2,3],
       [4,5,6],
       [7,8,9]]
```

**Output:**
```
25
```

**Explanation:**
```
Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
Notice that element mat[1][1] = 5 is counted only once.
```

### Example 2:

**Input:**
```
mat = [[1,1,1,1],
       [1,1,1,1],
       [1,1,1,1],
       [1,1,1,1]]
```

**Output:**
```
8
```

### Example 3:

**Input:**
```
mat = [[5]]
```

**Output:**
```
5
```

## Constraints

- `n == mat.length == mat[i].length`
- `1 <= n <= 100`
- `1 <= mat[i][j] <= 100`

## Topics

- Array
- Matrix

## Similar Problems

- [867. Transpose Matrix](https://leetcode.com/problems/transpose-matrix/) - Easy
- [832. Flipping an Image](https://leetcode.com/problems/flipping-an-image/) - Easy
- [566. Reshape the Matrix](https://leetcode.com/problems/reshape-the-matrix/) - Easy
- [48. Rotate Image](https://leetcode.com/problems/rotate-image/) - Medium
- [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/) - Medium
- [2133. Check if Every Row and Column Contains All Numbers](https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/) - Easy

## Related Topics Problems

**Matrix Traversal:**
- [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) - Medium
- [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/) - Medium
- [885. Spiral Matrix III](https://leetcode.com/problems/spiral-matrix-iii/) - Medium
- [2326. Spiral Matrix IV](https://leetcode.com/problems/spiral-matrix-iv/) - Medium

**Matrix Operations:**
- [73. Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) - Medium
- [289. Game of Life](https://leetcode.com/problems/game-of-life/) - Medium
- [766. Toeplitz Matrix](https://leetcode.com/problems/toeplitz-matrix/) - Easy
- [1380. Lucky Numbers in a Matrix](https://leetcode.com/problems/lucky-numbers-in-a-matrix/) - Easy

**Array Sum Problems:**
- [1480. Running Sum of 1d Array](https://leetcode.com/problems/running-sum-of-1d-array/) - Easy
- [1991. Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) - Easy
- [1672. Richest Customer Wealth](https://leetcode.com/problems/richest-customer-wealth/) - Easy

---

|**Previous:** [Day 79](../Day_79/) | **Next:** [Day 81](../Day_81/)|
|---|---|

|**Date:** March 22, 2026|
|---|