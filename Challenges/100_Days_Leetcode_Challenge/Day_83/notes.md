# Count Negative Numbers in a Sorted Matrix - Study Notes

## 1. Why This Approach (Staircase Search)

### The Core Problem
Matrix is sorted in **non-increasing order** (descending):
- Each row: numbers decrease left to right
- Each column: numbers decrease top to bottom

```
Example:
[ 4,  3,  2, -1]
[ 3,  2,  1, -1]
[ 1,  1, -1, -2]
[-1, -1, -2, -3]
```

### The "Staircase" Pattern
Start from **top-right corner**:
- If current element is **negative**: All elements **below** it in that column are also negative
- If current element is **non-negative**: Move **down** to find negatives

This creates a "staircase" movement pattern:
```
Start here →  [4,  3,  2, -1]
              [3,  2,  1, -1]
              [1,  1, -1, -2]
              [-1, -1, -2, -3]

Step 1: grid[0][3] = -1 (negative)
  → Count: 4 rows below (including current) = 4 negatives in column 3
  → Move left: col--

Step 2: grid[0][2] = 2 (positive)
  → Move down: row++

Step 3: grid[1][2] = 1 (positive)
  → Move down: row++

Step 4: grid[2][2] = -1 (negative)
  → Count: 2 rows = 2 negatives in column 2
  → Move left: col--

And so on...
```

### Why This Works
**Key insight**: When we find a negative at `grid[row][col]`:
- All cells `grid[row+1][col]`, `grid[row+2][col]`, ... are also negative (column sorted)
- Count them all: `grid.length - row` negatives
- No need to check them individually!

### Time Complexity: O(m + n)
- Worst case: traverse entire first row + entire last column
- At most `m + n` steps (one dimension decreases each step)

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Brute Force ❌ (Too Slow)
```java
int count = 0;
for (int i = 0; i < grid.length; i++) {
    for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] < 0) count++;
    }
}
```
**Problem**: O(m × n) time - checks every cell
**Why less ideal**: Doesn't use sorted property

### Approach 2: Binary Search Each Row ✓ (Works but Slower)
```java
int count = 0;
for (int i = 0; i < grid.length; i++) {
    int left = 0, right = grid[0].length - 1;
    int firstNegative = grid[0].length;

    // Binary search for first negative in row
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (grid[i][mid] < 0) {
            firstNegative = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    count += grid[0].length - firstNegative;
}
```
**Works**: O(m × log n) time
**Why less ideal**: Slower than O(m + n), more code

### Approach 3: Start from Bottom-Left ✓ (Equivalent)
```java
int row = grid.length - 1, col = 0;
int count = 0;

while (row >= 0 && col < grid[0].length) {
    if (grid[row][col] < 0) {
        count += grid[0].length - col;  // All to the right are negative
        row--;
    } else {
        col++;
    }
}
```
**Works**: Also O(m + n), symmetric to top-right approach
**Why equivalent**: Just different starting corner

### Approach 4: Diagonal Traversal ❌ (Complicated)
```java
// Try to traverse diagonally
```
**Problem**: Complex logic, no real benefit
**Why fails**: Harder to count negatives, same complexity

### Why Top-Right Staircase Wins
- ✅ **O(m + n) time** - optimal
- ✅ **O(1) space** - no extra structures
- ✅ **Intuitive** - clear movement pattern
- ✅ **Exploits sorted property** - uses both row and column sorting
- ✅ **Simple code** - easy to implement

## 3. How to Handle Changes

### Change 1: Count Positive Numbers Instead
**Modification**: Count positives in sorted matrix

```java
public int countPositives(int[][] grid) {
    int row = 0, col = grid[0].length - 1;
    int count = 0;

    while (row < grid.length && col >= 0) {
        if (grid[row][col] > 0) {
            count += (col + 1);  // All elements to the left are positive
            row++;
        } else {
            col--;
        }
    }
    return count;
}
```

### Change 2: Count Zeros
**Modification**: Count specific value

```java
public int countZeros(int[][] grid) {
    int row = 0, col = grid[0].length - 1;
    int count = 0;

    while (row < grid.length && col >= 0) {
        if (grid[row][col] < 0) {
            col--;
        } else if (grid[row][col] > 0) {
            row++;
        } else {  // grid[row][col] == 0
            // Count zeros in this row starting from col
            int tempCol = col;
            while (tempCol >= 0 && grid[row][tempCol] == 0) {
                count++;
                tempCol--;
            }
            row++;
        }
    }
    return count;
}
```

### Change 3: Return Positions of Negatives
**Modification**: Store coordinates instead of count

```java
public List<int[]> getNegativePositions(int[][] grid) {
    List<int[]> positions = new ArrayList<>();
    int row = 0, col = grid[0].length - 1;

    while (row < grid.length && col >= 0) {
        if (grid[row][col] < 0) {
            // Add all positions in this column from row to end
            for (int r = row; r < grid.length; r++) {
                positions.add(new int[]{r, col});
            }
            col--;
        } else {
            row++;
        }
    }
    return positions;
}
```

### Change 4: Count Numbers Less Than Target
**Modification**: Flexible threshold instead of just negative

```java
public int countLessThan(int[][] grid, int target) {
    int row = 0, col = grid[0].length - 1;
    int count = 0;

    while (row < grid.length && col >= 0) {
        if (grid[row][col] < target) {
            count += (grid.length - row);
            col--;
        } else {
            row++;
        }
    }
    return count;
}
```

### Change 5: Non-Decreasing Order (Ascending)
**Modification**: Matrix sorted in increasing order

```java
public int countNegativesAscending(int[][] grid) {
    // Start from top-left or bottom-right
    int row = grid.length - 1, col = 0;
    int count = 0;

    while (row >= 0 && col < grid[0].length) {
        if (grid[row][col] < 0) {
            count += (col + 1);  // All to the left are negative
            row--;
        } else {
            col++;
        }
    }
    return count;
}
```

## Visual Walkthrough

**Input**: `grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]`

```
Initial State:
     col 0  col 1  col 2  col 3
row 0:  4      3      2     -1  ← Start here
row 1:  3      2      1     -1
row 2:  1      1     -1     -2
row 3: -1     -1     -2     -3

Step 1: row=0, col=3, grid[0][3]=-1 (negative)
  ✓ Found negative!
  Count: grid.length - row = 4 - 0 = 4 negatives in column 3
  Total count: 4
  Move: col-- (col=2)

Step 2: row=0, col=2, grid[0][2]=2 (non-negative)
  Move: row++ (row=1)

Step 3: row=1, col=2, grid[1][2]=1 (non-negative)
  Move: row++ (row=2)

Step 4: row=2, col=2, grid[2][2]=-1 (negative)
  ✓ Found negative!
  Count: 4 - 2 = 2 negatives in column 2
  Total count: 4 + 2 = 6
  Move: col-- (col=1)

Step 5: row=2, col=1, grid[2][1]=1 (non-negative)
  Move: row++ (row=3)

Step 6: row=3, col=1, grid[3][1]=-1 (negative)
  ✓ Found negative!
  Count: 4 - 3 = 1 negative in column 1
  Total count: 6 + 1 = 7
  Move: col-- (col=0)

Step 7: row=3, col=0, grid[3][0]=-1 (negative)
  ✓ Found negative!
  Count: 4 - 3 = 1 negative in column 0
  Total count: 7 + 1 = 8
  Move: col-- (col=-1)

Step 8: col < 0, STOP

Final count: 8 ✓
```

## Key Patterns

**Pattern 1: Corner Start**
- Start from top-right (or bottom-left)
- These corners allow bidirectional movement

**Pattern 2: Conditional Movement**
- Found what we're looking for → count and move perpendicular
- Haven't found it → move parallel

**Pattern 3: Column/Row Counting**
- When condition met, count entire remaining column/row
- Leverage sorted property to avoid checking each cell

**Pattern 4: Monotonic Pointer Movement**
- Each pointer only moves in one direction
- `row` only increases OR `col` only decreases

## Complexity Analysis

- **Time**: O(m + n)
  - Worst case: traverse first row (n steps) + last column (m steps)
  - Each step moves one pointer closer to boundary

- **Space**: O(1)
  - Only using 3 variables: row, col, count

Compare to alternatives:
- Brute force: O(m × n) time
- Binary search each row: O(m × log n) time
- Staircase: **O(m + n) time** ← Optimal!

## Common Mistakes

1. **Starting from wrong corner**: Top-left or bottom-right won't work efficiently
2. **Wrong counting formula**: Using `col + 1` instead of `grid.length - row`
3. **Boundary conditions**: Forgetting `col >= 0` check
4. **Sorted order confusion**: Assuming ascending when it's descending

## Interview Tips

1. **Clarify sorted order**: Ask if rows/columns are ascending or descending
2. **Draw the matrix**: Visualize movement pattern
3. **Explain corner choice**: Why top-right (or bottom-left) works
4. **Compare approaches**: Mention brute force → binary search → staircase
5. **Discuss edge cases**: Empty matrix, all negatives, no negatives