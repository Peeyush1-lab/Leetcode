# Day 26 - Detailed Notes

---

## Problem: Transpose Matrix

**LeetCode:** [#867](https://leetcode.com/problems/transpose-matrix/) | **Difficulty:** Easy

---

### 📝 Problem Statement

Given a 2D integer array `matrix`, return the **transpose** of `matrix`.

The **transpose** of a matrix is obtained by flipping the matrix over its main diagonal, switching the row and column indices.

**Formally:** `transpose[i][j] = matrix[j][i]`

---

### 📊 Examples

#### Example 1
```
Input: matrix = [[1,2,3],
                 [4,5,6],
                 [7,8,9]]

Output: [[1,4,7],
         [2,5,8],
         [3,6,9]]

Visualization:
Original (3×3):        Transpose (3×3):
1  2  3                1  4  7
4  5  6       →        2  5  8
7  8  9                3  6  9

Rows become columns!
```

#### Example 2
```
Input: matrix = [[1,2,3],
                 [4,5,6]]

Output: [[1,4],
         [2,5],
         [3,6]]

Visualization:
Original (2×3):        Transpose (3×2):
1  2  3                1  4
4  5  6       →        2  5
                       3  6

2 rows × 3 cols → 3 rows × 2 cols
```

---

### 💡 Approach

**Strategy:** Create New Matrix with Swapped Dimensions

**Core Idea:**
- Original matrix: `m rows × n columns`
- Transpose matrix: `n rows × m columns`
- Each element at position `[row][col]` moves to `[col][row]`

#### Visual Understanding

```
Original matrix[2][3]:
       col0 col1 col2
row0:   1    2    3
row1:   4    5    6

Transpose arr[3][2]:
       col0 col1
row0:   1    4      ← Original column 0
row1:   2    5      ← Original column 1
row2:   3    6      ← Original column 2

Mapping:
matrix[0][0] = 1  →  arr[0][0] = 1
matrix[0][1] = 2  →  arr[1][0] = 2
matrix[0][2] = 3  →  arr[2][0] = 3
matrix[1][0] = 4  →  arr[0][1] = 4
matrix[1][1] = 5  →  arr[1][1] = 5
matrix[1][2] = 6  →  arr[2][1] = 6

Pattern: matrix[j][i] → arr[i][j]
```

---

### 🔄 Algorithm Steps

1. **Get Dimensions:**
   - `row = matrix.length` (number of rows in original)
   - `col = matrix[0].length` (number of columns in original)

2. **Create Result Matrix:**
   - Dimensions: `col × row` (swapped!)
   - `arr = new int[col][row]`

3. **Fill Transpose:**
   - Iterate through each column of original (becomes each row of result)
   - For each column `i`, iterate through each row `j`
   - Assign: `arr[i][j] = matrix[j][i]`

4. **Return Result**

---

### 🔍 Key Implementation Details

#### Dimension Calculation
```java
int row = matrix.length;        // Original rows
int col = matrix[0].length;     // Original columns
int arr[][] = new int[col][row]; // Swapped dimensions!
```
**Critical:** Result matrix dimensions are **swapped** from original

#### Index Swapping Logic
```java
for (int i = 0; i < col; i++) {       // Iterate through columns
    for (int j = 0; j < row; j++) {   // Iterate through rows
        arr[i][j] = matrix[j][i];     // Swap indices!
    }
}
```
**Key insight:**
- Loop variable `i` represents original **column** (new row)
- Loop variable `j` represents original **row** (new column)
- Assignment uses `[j][i]` from source, `[i][j]` in destination

#### Why This Order?
```java
// Your approach: Iterate by columns first
for (int i = 0; i < col; i++) {
    for (int j = 0; j < row; j++) {
        arr[i][j] = matrix[j][i];
    }
}

// Alternative: Iterate by rows first (also correct)
for (int i = 0; i < row; i++) {
    for (int j = 0; j < col; j++) {
        arr[j][i] = matrix[i][j];
    }
}
```
Both work! Your approach fills result row-by-row, alternative fills column-by-column.

---

### ⚙️ Complexity Analysis

- **Time Complexity:** O(m × n)
  - Must visit every element exactly once
  - No optimization possible - need to copy all elements

- **Space Complexity:** O(m × n)
  - New matrix of size n × m
  - Cannot be done in-place for non-square matrices

**Why can't we do in-place for rectangular matrices?**
```
Original: 2 rows × 3 cols = 6 elements
Transpose: 3 rows × 2 cols = 6 elements

BUT different shapes!
Original array structure: arr[2][3]
Needed structure: arr[3][2]

Java arrays have fixed dimensions → Need new array
```

**Special case - Square matrices:**
```
For n×n matrices, in-place is possible:
- Swap elements across diagonal: arr[i][j] ↔ arr[j][i]
- Only swap upper triangle to avoid double-swapping
```

---

### 🎯 Key Insights

1. **Dimension Swap is Essential:**
   - Forgetting to swap dimensions is a common mistake
   - `m × n` matrix MUST become `n × m` transpose

2. **Index Order Matters:**
   - `arr[i][j] = matrix[j][i]` swaps row and column
   - Getting the order wrong produces incorrect output

3. **Column-First Iteration:**
   - Your code iterates over columns of original matrix first
   - Each column becomes a row in the result
   - Intuitive way to think about transpose

4. **Can't Modify In-Place (Usually):**
   - Non-square matrices need new array
   - Square matrices CAN be transposed in-place
   - Your solution correctly creates new array

---

### 🐛 Edge Cases Handled

| Case | Example | Behavior |
|------|---------|----------|
| Square matrix | `[[1,2],[3,4]]` | 2×2 → 2×2 |
| Single row | `[[1,2,3]]` | 1×3 → 3×1 (column) |
| Single column | `[[1],[2],[3]]` | 3×1 → 1×3 (row) |
| Single element | `[[5]]` | 1×1 → 1×1 (unchanged) |
| Rectangular | `[[1,2,3],[4,5,6]]` | 2×3 → 3×2 |

---

### 🔄 Problems Encountered & Solutions

#### Problem 1: Initial Dimension Confusion
**Issue:** Might initially create result with same dimensions as original.

**Wrong approach:**
```java
int arr[][] = new int[row][col];  // ❌ Same dimensions!
```

**Correct approach:**
```java
int arr[][] = new int[col][row];  // ✓ Swapped dimensions!
```

**Why it matters:** A 2×3 matrix has 3 columns, so transpose needs 3 rows.

---

#### Problem 2: Index Ordering Confusion
**Issue:** Mixing up which index goes where in the assignment.

**Common mistakes:**
```java
arr[i][j] = matrix[i][j];  // ❌ Just copying, not transposing
arr[j][i] = matrix[i][j];  // ❌ Backwards assignment
```

**Correct:**
```java
arr[i][j] = matrix[j][i];  // ✓ Swaps row and column
```

**Memory trick:** "I iterate columns, J iterates rows, so read matrix[row][col]"

---

#### Problem 3: Loop Bound Mistakes
**Issue:** Using wrong dimensions in loop conditions.

**Wrong bounds:**
```java
for (int i = 0; i < row; i++) {      // ❌ Should be col
    for (int j = 0; j < col; j++) {  // ❌ Should be row
```

**Correct bounds (your code):**
```java
for (int i = 0; i < col; i++) {      // ✓ Columns become rows
    for (int j = 0; j < row; j++) {  // ✓ Rows become columns
```

**Understanding:** Outer loop iterates through the NEW matrix's rows (which correspond to original columns).

---

### 🎓 Alternative Approaches

#### Approach 2: Row-First Iteration
```java
// Alternative: Build by iterating original rows first
int arr[][] = new int[col][row];
for (int i = 0; i < row; i++) {
    for (int j = 0; j < col; j++) {
        arr[j][i] = matrix[i][j];
    }
}
```
- Fills transpose column-by-column instead of row-by-row
- Same complexity, just different iteration order

#### Approach 3: In-Place (Square Matrices Only)
```java
// Only works for n×n matrices
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        // Swap across diagonal
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }
}
```
- O(1) space instead of O(n²)
- Only for square matrices
- Your problem doesn't require this

---

### 🎓 Pattern Recognition

**This problem teaches:**
- **Matrix transformation** fundamentals
- **Dimension handling** in 2D arrays
- **Index relationship** between rows and columns
- **Space tradeoff** (in-place vs new array)

**Related matrix problems:**
- **Rotate Image (#48):** Rotate 90° = Transpose + Reverse rows
- **Spiral Matrix (#54):** Complex traversal pattern
- **Set Matrix Zeroes (#73):** In-place matrix modification
- **Search 2D Matrix (#74):** Binary search on matrix

**When you need transpose:**
- As step in matrix rotation
- Converting row-major to column-major data
- Mathematical matrix operations
- Image processing transformations

---

### 💡 Real-World Applications

**Where transpose is used:**
1. **Graphics:** Image rotations often use transpose + flip
2. **Data Science:** Converting row data to column data (pandas transpose)
3. **Linear Algebra:** Matrix operations in ML/AI
4. **Image Processing:** Applying filters in different orientations

---

## 📝 Daily Reflection

### ✅ What Went Well
- Clean, straightforward implementation
- Correctly swapped dimensions
- Proper index ordering in assignment
- No off-by-one errors in loop bounds
- Code is readable and efficient

### 💡 Key Realizations Today

**Back to Basics:**
- After complex problems (heaps, prefix sums), revisiting fundamentals
- Matrix operations are building blocks for more complex algorithms
- Simple problems reinforce index manipulation skills

**Dimension Awareness:**
- Must always track which dimension is rows vs columns
- Swapping dimensions is the KEY insight for transpose
- Index order `[j][i]` vs `[i][j]` determines the transformation

---

### 🧩 Pattern Recognition Progress

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 22 | Build Array | Medium | Array Simulation |
| 23 | Max Eaten Apples | Medium | Greedy + Min-Heap |
| 24 | Kth Largest | Medium | Top K with Heap |
| 25 | Stock Strategy | Medium | Prefix Sum Optimization |
| 26 | Transpose Matrix | Easy | Matrix Manipulation |

**Learning Arc:**
- Week 1 (Days 22-25): Advanced data structures and optimization
- Day 26: Fundamental review - staying sharp on basics

**Skills Reinforced:**
- ✅ 2D array traversal
- ✅ Dimension awareness
- ✅ Index manipulation
- ✅ Space complexity consideration

---

### 🎯 Tomorrow's Focus
- Continue with matrix problems (rotation, spiral)
- Or return to medium/hard optimization problems
- Consider exploring dynamic programming next

---

### 💭 Questions to Explore
1. How would in-place transpose work for square matrices?
2. What's the connection between transpose and matrix rotation?
3. Can we optimize space further for sparse matrices?
4. How does transpose relate to other matrix operations?

---

### 🔥 Progress Metrics
- **5-day streak** achieved! 🎉🎉🎉
- **1 easy problem** solved
- **5 medium problems** solved
- **Matrix pattern** added to toolkit
- **Balanced practice:** Complex + Fundamental problems

---

### 🌟 Today's Insight
Even "easy" problems have important lessons. Understanding fundamentals like matrix transpose deeply makes harder problems (like Rotate Image) much easier. Strong fundamentals = Strong problem solver.

---

**Last Updated:** January 26, 2026