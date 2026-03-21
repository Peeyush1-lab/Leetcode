# Matrix Diagonal Sum - Study Notes

## Approach: Single Pass with Overlap Handling

### Key Insight

A square matrix has **two diagonals**:
1. **Primary diagonal**: Elements where `row == col` (top-left to bottom-right)
2. **Secondary diagonal**: Elements where `row + col == n-1` (top-right to bottom-left)

**Problem**: In odd-sized matrices, the center element is on BOTH diagonals.
**Solution**: Add both diagonals, then subtract the center if matrix size is odd.

## Algorithm

1. Iterate through rows (i from 0 to n-1)
2. Add primary diagonal: `mat[i][i]`
3. Add secondary diagonal: `mat[n-i-1][i]`
4. If n is odd, subtract center element: `mat[n/2][n/2]`

## Visual Example

**Input**: `mat = [[1,2,3],[4,5,6],[7,8,9]]` (n=3, odd)

```
Primary diagonal (↘):
  mat[0][0] = 1
  mat[1][1] = 5
  mat[2][2] = 9

Secondary diagonal (↙):
  mat[2][0] = 7
  mat[1][1] = 5  ← Overlap!
  mat[0][2] = 3

Sum = 1 + 5 + 9 + 7 + 5 + 3 = 30
Center counted twice: mat[1][1] = 5
Final = 30 - 5 = 25
```

**Iteration breakdown**:
```
i=0: mat[0][0]=1 + mat[2][0]=7 → sum=8
i=1: mat[1][1]=5 + mat[1][1]=5 → sum=18
i=2: mat[2][2]=9 + mat[0][2]=3 → sum=30
Odd size: subtract mat[1][1]=5 → 25
```

## Formula Breakdown

**Secondary diagonal column**: `n - i - 1`
- When i=0: column = n-1 (rightmost)
- When i=1: column = n-2
- When i=n-1: column = 0 (leftmost)

**Center element**: `mat[n/2][n/2]` (only when n is odd)

## Complexity

- **Time**: O(n) - single pass through n rows
- **Space**: O(1) - only sum variable

## Edge Cases

1. **Single element**: n=1 → counted once, no subtraction
2. **Even size**: n=4 → no overlap, no subtraction
3. **All same values**: Sum = value × (2n - 1) for odd n