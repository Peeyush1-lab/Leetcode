# Find First and Last Position of Element in Sorted Array - Study Notes

## 1. Why This Approach (Modified Binary Search)

### The Core Problem
Find the **range** `[start, end]` of target value in sorted array in O(log n) time.

**Key Insight**: Use binary search **twice**:
1. Find **leftmost** (first) occurrence
2. Find **rightmost** (last) occurrence

### Standard vs Modified Binary Search

**Standard Binary Search**:
- Stops when target found
- Returns any occurrence

**Modified Binary Search**:
- Continues searching even after finding target
- Finds specific boundary (first or last)

### The Modification

When `nums[mid] == target`:
- **Finding first**: Continue searching left (`right = mid - 1`)
- **Finding last**: Continue searching right (`left = mid + 1`)
- Track the found index in `idx`

### Why This Works

**Finding first occurrence**:
```
[5, 7, 7, 7, 8, 10], target = 7

When mid finds 7 at index 2:
  - Don't stop! There might be 7s to the left
  - Set idx = 2 (save this position)
  - Continue: right = mid - 1 (search left)
  - Eventually find first 7 at index 1
```

**Finding last occurrence**:
```
Same array:
When mid finds 7 at index 2:
  - Don't stop! There might be 7s to the right
  - Set idx = 2 (save this position)
  - Continue: left = mid + 1 (search right)
  - Eventually find last 7 at index 3
```

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Linear Scan ❌ (Too Slow)
```java
public int[] linearSearch(int[] nums, int target) {
    int first = -1, last = -1;

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == target) {
            if (first == -1) first = i;
            last = i;
        }
    }

    return new int[]{first, last};
}
```
**Problem**: O(n) time - violates requirement
**Why fails**: Must be O(log n)

### Approach 2: Binary Search + Linear Expansion ❌
```java
public int[] hybridSearch(int[] nums, int target) {
    int mid = binarySearch(nums, target);  // O(log n)
    if (mid == -1) return new int[]{-1, -1};

    // Expand left and right - O(n) worst case!
    int left = mid, right = mid;
    while (left > 0 && nums[left-1] == target) left--;
    while (right < nums.length-1 && nums[right+1] == target) right++;

    return new int[]{left, right};
}
```
**Problem**: O(n) in worst case (all elements same)
**Example**: `[7,7,7,7,7,7,7]`, target=7

### Approach 3: Two Separate Simple Binary Searches ❌
```java
// First search finds ANY occurrence
int any = standardBinarySearch(nums, target);
// Then expand - same problem as Approach 2
```
**Problem**: Still needs linear expansion
**Why fails**: Doesn't leverage sorted property for range

### Approach 4: Recursion ✓ (Works but More Complex)
```java
private int[] recursiveBinarySearch(int[] nums, int target, int left, int right) {
    if (left > right) return new int[]{-1, -1};

    int mid = left + (right - left) / 2;

    if (nums[mid] == target) {
        int[] leftRange = recursiveBinarySearch(nums, target, left, mid - 1);
        int[] rightRange = recursiveBinarySearch(nums, target, mid + 1, right);

        int start = leftRange[0] == -1 ? mid : leftRange[0];
        int end = rightRange[1] == -1 ? mid : rightRange[1];

        return new int[]{start, end};
    } else if (nums[mid] < target) {
        return recursiveBinarySearch(nums, target, mid + 1, right);
    } else {
        return recursiveBinarySearch(nums, target, left, mid - 1);
    }
}
```
**Works**: O(log n) but more code
**Why less ideal**: Recursion overhead, harder to understand

### Why Two Modified Binary Searches Win
- ✅ **O(log n) time** - guaranteed
- ✅ **Simple iterative** - no recursion
- ✅ **Reusable function** - same code for both searches
- ✅ **Clean separation** - find first, find last independently

## 3. How to Handle Changes

### Change 1: Count Occurrences
**Modification**: Return count instead of range

```java
public int countOccurrences(int[] nums, int target) {
    int first = binarySearch(nums, target, true);
    if (first == -1) return 0;

    int last = binarySearch(nums, target, false);
    return last - first + 1;
}
```

### Change 2: Find Range of Values (Not Exact Match)
**Modification**: Find range where values are within threshold

```java
public int[] findRangeWithinThreshold(int[] nums, int target, int threshold) {
    int first = findFirstGreaterOrEqual(nums, target - threshold);
    int last = findLastLessOrEqual(nums, target + threshold);

    return new int[]{first, last};
}

private int findFirstGreaterOrEqual(int[] nums, int value) {
    int left = 0, right = nums.length - 1;
    int result = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] >= value) {
            result = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return result;
}
```

### Change 3: Return All Indices
**Modification**: Return list of all positions

```java
public List<Integer> findAllIndices(int[] nums, int target) {
    List<Integer> indices = new ArrayList<>();

    int first = binarySearch(nums, target, true);
    if (first == -1) return indices;

    int last = binarySearch(nums, target, false);

    for (int i = first; i <= last; i++) {
        indices.add(i);
    }

    return indices;
}
```

### Change 4: Find Range in 2D Matrix
**Modification**: Sorted 2D matrix

```java
public int[][] searchRange2D(int[][] matrix, int target) {
    // Treat as 1D array
    int m = matrix.length, n = matrix[0].length;

    int first = binarySearch2D(matrix, target, true);
    int last = binarySearch2D(matrix, target, false);

    if (first == -1) return new int[][]{{-1, -1}};

    return new int[][]{
        {first / n, first % n},
        {last / n, last % n}
    };
}
```

### Change 5: Multiple Targets
**Modification**: Find ranges for multiple targets

```java
public int[][] searchMultipleRanges(int[] nums, int[] targets) {
    int[][] results = new int[targets.length][2];

    for (int i = 0; i < targets.length; i++) {
        results[i][0] = binarySearch(nums, targets[i], true);
        results[i][1] = binarySearch(nums, targets[i], false);
    }

    return results;
}
```

## Visual Walkthrough

**Input**: `nums = [5,7,7,8,8,10]`, `target = 8`

### Finding First Position

```
Initial: left=0, right=5, idx=-1

Iteration 1:
  mid = 0 + (5-0)/2 = 2
  nums[2] = 7 < 8
  left = 3

Iteration 2:
  mid = 3 + (5-3)/2 = 4
  nums[4] = 8 == target ✓
  idx = 4 (save this)
  Finding first, so: right = 3 (search left)

Iteration 3:
  mid = 3 + (3-3)/2 = 3
  nums[3] = 8 == target ✓
  idx = 3 (update!)
  Finding first, so: right = 2

Iteration 4:
  left=3, right=2 → left > right, STOP

First position: idx = 3 ✓
```

### Finding Last Position

```
Initial: left=0, right=5, idx=-1

Iteration 1:
  mid = 2
  nums[2] = 7 < 8
  left = 3

Iteration 2:
  mid = 4
  nums[4] = 8 == target ✓
  idx = 4 (save this)
  Finding last, so: left = 5 (search right)

Iteration 3:
  mid = 5 + (5-5)/2 = 5
  nums[5] = 10 > 8
  right = 4

Iteration 4:
  left=5, right=4 → left > right, STOP

Last position: idx = 4 ✓
```

**Result**: `[3, 4]` ✓

## Key Patterns

**Pattern 1: Boundary Binary Search**
- Keep searching after finding target
- Move toward desired boundary

**Pattern 2: Save and Continue**
- `idx = mid` saves current find
- Continue searching for better answer

**Pattern 3: Direction Based on Goal**
- First: search left (`right = mid - 1`)
- Last: search right (`left = mid + 1`)

**Pattern 4: Reusable Template**
- Same function with boolean flag
- DRY principle

## Complexity Analysis

- **Time**: O(log n)
  - First binary search: O(log n)
  - Second binary search: O(log n)
  - Total: O(log n)

- **Space**: O(1)
  - Only variables, no extra structures

## Common Mistakes

1. **Syntax error in your code**: `int result[0] = ...` should be `result[0] = ...`
2. **Not continuing after finding target**: Stops at first match
3. **Wrong boundary update**: Using `mid` instead of `mid ± 1`
4. **Forgetting to save idx**: Losing track of found position
5. **Not initializing idx to -1**: Wrong result when not found

## Interview Tips

1. **Explain the modification**: How it differs from standard binary search
2. **Draw the array**: Visual helps understanding
3. **Walk through one search**: Show first OR last, not both
4. **Mention edge cases**: Empty array, target not found, all same
5. **Optimize**: Mention early exit if first search returns -1