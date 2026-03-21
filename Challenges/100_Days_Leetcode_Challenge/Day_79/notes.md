# Flipping an Image - Study Notes

## Approach: In-Place Two Pointers

### Key Insight

We need to do **two operations** on each row:
1. **Flip horizontally** (reverse the row)
2. **Invert** (0→1, 1→0)

**Optimization**: Do both operations **simultaneously** using two pointers!

## Algorithm

For each row:
1. Use two pointers: `j` (left) and `mj` (right)
2. While `j <= mj`:
   - Swap `image[i][j]` and `image[i][mj]` (flip)
   - Invert both values: `1 - value` (invert)
   - Move pointers: `j++`, `mj--`

**Inversion formula**: `1 - x` where x ∈ {0, 1}
- `1 - 0 = 1` ✓
- `1 - 1 = 0` ✓

## Visual Example

**Input**: `[[1,1,0],[1,0,1],[0,0,0]]`

```
Row 0: [1, 1, 0]
  j=0, mj=2:
    temp = 1-image[0][0] = 1-1 = 0
    image[0][0] = 1-image[0][2] = 1-0 = 1
    image[0][2] = temp = 0
    Result: [1, 1, 0]

  j=1, mj=1 (middle element):
    temp = 1-image[0][1] = 1-1 = 0
    image[0][1] = 1-image[0][1] = 0
    Result: [1, 0, 0]

Row 1: [1, 0, 1]
  After processing: [0, 1, 0]

Row 2: [0, 0, 0]
  After processing: [1, 1, 1]

Final: [[1,0,0],[0,1,0],[1,1,1]]
```

## Why It Works

**Two-in-one operation**:
- Swap positions (flip)
- Invert values simultaneously

**Handles odd-length rows**:
- Middle element (`j==mj`) gets inverted only
- No swap needed (swapping with itself)

## Complexity

- **Time**: O(n²) - process each element once
- **Space**: O(1) - in-place modification

## Edge Cases

1. **Single element**: `[[1]]` → `[[0]]`
2. **All same**: `[[1,1],[1,1]]` → `[[0,0],[0,0]]`
3. **Already correct**: Some rows may not change
4. **Odd/even length**: Works for both