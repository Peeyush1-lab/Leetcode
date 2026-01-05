# 🔍 Day 5 (Problem 1): Search a 2D Matrix

## 📋 Problem Statement

You are given an `m x n` integer matrix `matrix` with the following two properties:

- Each row is sorted in non-decreasing order.
- The first integer of each row is greater than the last integer of the previous row.

Given an integer `target`, return `true` if `target` is in `matrix` or `false` otherwise.

You must write a solution in **O(log(m × n))** time complexity.

## 💡 Examples

### Example 1: ✅
**Input:** `matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]]`, `target = 3`
**Output:** `true`

```
Matrix:
[1,  3,  5,  7]
[10, 11, 16, 20]
[23, 30, 34, 60]

Target 3 found at position [0][1] ✓
```

### Example 2: ❌
**Input:** `matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]]`, `target = 13`
**Output:** `false`

```
Matrix:
[1,  3,  5,  7]
[10, 11, 16, 20]
[23, 30, 34, 60]

Target 13 not in matrix ✗
```

### Example 3: ✅
**Input:** `matrix = [[1]]`, `target = 1`
**Output:** `true`

## ⚠️ Constraints

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 100`
- `-10^4 <= matrix[i][j], target <= 10^4`

## 🔑 Key Points

1. 📊 **Sorted rows** - Each row is sorted
2. 📈 **Sorted between rows** - First of row N > last of row N-1
3. 🎯 This creates a **staircase pattern**
4. 💡 Can treat as sorted 1D array OR use staircase search
5. ⏱️ Need **O(log(m×n))** time (binary search approach)
6. 🚀 Alternative: **O(m+n)** staircase search from corner

## 🛠️ Approach: Staircase Search

### 💡 Core Intuition
Start from **bottom-left** or **top-right** corner! These positions have special properties:

**From bottom-left (chosen):**
- Elements to the **right** are larger
- Elements **above** are smaller
- Can eliminate entire row or column each step!

### 📋 Algorithm Steps
1. Start at **bottom-left corner** `(m-1, 0)`
2. Compare with target:
   - If `matrix[i][j] == target` → Found! Return true
   - If `matrix[i][j] < target` → Move right (j++)
   - If `matrix[i][j] > target` → Move up (i--)
3. Repeat until out of bounds
4. If not found, return false

### ⏱️ Complexity
- **Time:** O(m + n) - At most m+n steps
- **Space:** O(1) - Only two pointers

## Example

```
Matrix:          Start position (bottom-left):
[1,  3,  5,  7]  [1,  3,  5,  7]
[10, 11, 16, 20] [10, 11, 16, 20]
[23, 30, 34, 60] [23, 30, 34, 60]
                  ↑
                  i=2, j=0

Searching for target = 16:

Step 1: matrix[2][0]=23 > 16 → Move up
Step 2: matrix[1][0]=10 < 16 → Move right
Step 3: matrix[1][1]=11 < 16 → Move right
Step 4: matrix[1][2]=16 == 16 → Found! ✓
```