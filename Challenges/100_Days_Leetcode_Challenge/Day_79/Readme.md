# 832. Flipping an Image

**Difficulty:** Easy  
**Day:** 79  
**Topics:** Array, Two Pointers, Matrix, Simulation

## Problem Description

Given an `n x n` binary matrix `image`, flip the image **horizontally**, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.

- For example, flipping `[1,1,0]` horizontally results in `[0,1,1]`.

To invert an image means that each `0` is replaced by `1`, and each `1` is replaced by `0`.

- For example, inverting `[0,1,1]` results in `[1,0,0]`.

## Examples

### Example 1:

**Input:**
```
image = [[1,1,0],[1,0,1],[0,0,0]]
```

**Output:**
```
[[1,0,0],[0,1,0],[1,1,1]]
```

**Explanation:**
```
First reverse each row: [[0,1,1],[1,0,1],[0,0,0]]
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
```

### Example 2:

**Input:**
```
image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
```

**Output:**
```
[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
```

**Explanation:**
```
First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
```

## Constraints

- `n == image.length`
- `n == image[i].length`
- `1 <= n <= 20`
- `images[i][j]` is either `0` or `1`.

## Topics

- Array
- Two Pointers
- Matrix
- Simulation
- Bit Manipulation

## Similar Problems

- [867. Transpose Matrix](https://leetcode.com/problems/transpose-matrix/) - Easy
- [48. Rotate Image](https://leetcode.com/problems/rotate-image/) - Medium
- [566. Reshape the Matrix](https://leetcode.com/problems/reshape-the-matrix/) - Easy
- [1886. Determine Whether Matrix Can Be Obtained By Rotation](https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/) - Easy
- [2022. Convert 1D Array Into 2D Array](https://leetcode.com/problems/convert-1d-array-into-2d-array/) - Easy

## Related Topics Problems

**Matrix Manipulation:**
- [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) - Medium
- [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/) - Medium
- [73. Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) - Medium
- [289. Game of Life](https://leetcode.com/problems/game-of-life/) - Medium
- [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/) - Medium

**Two Pointers:**
- [344. Reverse String](https://leetcode.com/problems/reverse-string/) - Easy
- [345. Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string/) - Easy
- [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) - Easy
- [1679. Max Number of K-Sum Pairs](https://leetcode.com/problems/max-number-of-k-sum-pairs/) - Medium

**Bit Manipulation:**
- [136. Single Number](https://leetcode.com/problems/single-number/) - Easy
- [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/) - Easy
- [461. Hamming Distance](https://leetcode.com/problems/hamming-distance/) - Easy
- [476. Number Complement](https://leetcode.com/problems/number-complement/) - Easy

---

|**Previous:** [Day 78](../Day_78/) | **Next:** [Day 80](../Day_80/)|
|---|---|

|**Date:** March 20, 2026|
|---|