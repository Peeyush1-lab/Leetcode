# Find Pivot Index - Study Notes

## 1. Why This Approach (Prefix Sum Pattern)

### The Core Problem
Find index where: `sum(left side) = sum(right side)`

**Key Insight**: Instead of calculating left and right sums separately for each index, we can use the total sum!

For any index `i`:
- `leftSum` = sum of elements before index i
- `rightSum` = sum of elements after index i
- `total` = leftSum + nums[i] + rightSum

Therefore: `rightSum = total - leftSum - nums[i]`

### Why This Works
We can calculate `rightSum` without explicitly summing right elements:
```
If leftSum == rightSum, then:
leftSum == total - leftSum - nums[i]
2 * leftSum == total - nums[i]
leftSum == (total - nums[i]) / 2
```

But we use the direct formula for simplicity: `rightSum = total - leftSum - nums[i]`

### Algorithm Steps
1. Calculate total sum once: O(n)
2. Iterate through array maintaining `leftSum`:
   - Calculate `rightSum` using formula
   - If `leftSum == rightSum`, found pivot!
   - Add `nums[i]` to `leftSum` for next iteration
3. Return -1 if no pivot found

### Single Pass After Initial Sum
We only traverse the array twice total:
- Once to calculate total
- Once to find pivot

This is optimal!

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Brute Force ❌ (Too Slow)
```java
for (int i = 0; i < nums.length; i++) {
    int leftSum = 0;
    for (int j = 0; j < i; j++) {
        leftSum += nums[j];
    }

    int rightSum = 0;
    for (int j = i + 1; j < nums.length; j++) {
        rightSum += nums[j];
    }

    if (leftSum == rightSum) return i;
}
```
**Problem**: O(n²) - recalculates sums for each index
**Why fails**: Inefficient for large arrays

### Approach 2: Two Prefix Sum Arrays ✓ (Works but Extra Space)
```java
int[] leftSum = new int[n];
int[] rightSum = new int[n];

// Build leftSum array
leftSum[0] = 0;
for (int i = 1; i < n; i++) {
    leftSum[i] = leftSum[i-1] + nums[i-1];
}

// Build rightSum array
rightSum[n-1] = 0;
for (int i = n-2; i >= 0; i--) {
    rightSum[i] = rightSum[i+1] + nums[i+1];
}

// Find pivot
for (int i = 0; i < n; i++) {
    if (leftSum[i] == rightSum[i]) return i;
}
```
**Works**: O(n) time, O(n) space
**Why less ideal**: Uses extra arrays when not needed

### Approach 3: Left and Right Pointers ❌ (Doesn't Work)
```java
int left = 0, right = nums.length - 1;
int leftSum = 0, rightSum = 0;

while (left < right) {
    if (leftSum < rightSum) {
        leftSum += nums[left++];
    } else {
        rightSum += nums[right--];
    }
}
```
**Problem**: Can't find pivot this way
**Why fails**: Two pointers don't guarantee finding all valid pivots

### Approach 4: Binary Search ❌ (Doesn't Apply)
```java
// Try to binary search for pivot
```
**Problem**: Array isn't sorted by pivot property
**Why fails**: No monotonic property to exploit

### Why Prefix Sum Pattern Wins
- ✅ **O(n) time** - two passes
- ✅ **O(1) space** - only variables
- ✅ **Clean and simple** - easy to understand
- ✅ **No extra data structures** - just running sum
- ✅ **Returns leftmost pivot** - processes left to right

## 3. How to Handle Changes

### Change 1: Find All Pivot Indices
**Modification**: Return list of all pivots

```java
public List<Integer> findAllPivots(int[] nums) {
    List<Integer> pivots = new ArrayList<>();
    int total = 0;
    for (int num : nums) {
        total += num;
    }

    int leftSum = 0;
    for (int i = 0; i < nums.length; i++) {
        int rightSum = total - leftSum - nums[i];
        if (leftSum == rightSum) {
            pivots.add(i);
        }
        leftSum += nums[i];
    }

    return pivots;
}
```

### Change 2: Find Rightmost Pivot
**Modification**: Return last valid pivot

```java
public int findRightmostPivot(int[] nums) {
    int total = 0;
    for (int num : nums) {
        total += num;
    }

    int pivot = -1;
    int leftSum = 0;

    for (int i = 0; i < nums.length; i++) {
        int rightSum = total - leftSum - nums[i];
        if (leftSum == rightSum) {
            pivot = i;  // Update instead of return
        }
        leftSum += nums[i];
    }

    return pivot;
}
```

### Change 3: Pivot with Closest Sums
**Modification**: Find index where |leftSum - rightSum| is minimum

```java
public int closestPivot(int[] nums) {
    int total = 0;
    for (int num : nums) {
        total += num;
    }

    int leftSum = 0;
    int minDiff = Integer.MAX_VALUE;
    int bestIndex = -1;

    for (int i = 0; i < nums.length; i++) {
        int rightSum = total - leftSum - nums[i];
        int diff = Math.abs(leftSum - rightSum);

        if (diff < minDiff) {
            minDiff = diff;
            bestIndex = i;
        }

        leftSum += nums[i];
    }

    return bestIndex;
}
```

### Change 4: Weighted Pivot (Elements Have Weights)
**Modification**: Consider element weights

```java
public int weightedPivot(int[] nums, int[] weights) {
    int total = 0;
    for (int i = 0; i < nums.length; i++) {
        total += nums[i] * weights[i];
    }

    int leftSum = 0;
    for (int i = 0; i < nums.length; i++) {
        int rightSum = total - leftSum - nums[i] * weights[i];
        if (leftSum == rightSum) {
            return i;
        }
        leftSum += nums[i] * weights[i];
    }

    return -1;
}
```

### Change 5: 2D Array Pivot (Row and Column Balance)
**Modification**: Find pivot in 2D grid

```java
public int[] find2DPivot(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;

    // Try each position as pivot
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            int top = 0, bottom = 0, left = 0, right = 0;

            // Sum top, bottom, left, right regions
            // ... calculate sums ...

            if (top == bottom && left == right) {
                return new int[]{i, j};
            }
        }
    }

    return new int[]{-1, -1};
}
```

## Visual Walkthrough

**Input**: `nums = [1,7,3,6,5,6]`

```
Step 1: Calculate total
total = 1 + 7 + 3 + 6 + 5 + 6 = 28

Step 2: Find pivot
leftSum = 0

i=0: nums[0]=1
  rightSum = 28 - 0 - 1 = 27
  leftSum(0) != rightSum(27)
  leftSum = 0 + 1 = 1

i=1: nums[1]=7
  rightSum = 28 - 1 - 7 = 20
  leftSum(1) != rightSum(20)
  leftSum = 1 + 7 = 8

i=2: nums[2]=3
  rightSum = 28 - 8 - 3 = 17
  leftSum(8) != rightSum(17)
  leftSum = 8 + 3 = 11

i=3: nums[3]=6
  rightSum = 28 - 11 - 6 = 11
  leftSum(11) == rightSum(11) ✓
  Return 3

Visual:
[1, 7, 3] | 6 | [5, 6]
  ↑        ↑      ↑
  11    (pivot)  11
```

**Example with edge case**: `nums = [2,1,-1]`

```
total = 2 + 1 + (-1) = 2

i=0: nums[0]=2
  rightSum = 2 - 0 - 2 = 0
  leftSum(0) == rightSum(0) ✓
  Return 0

Visual:
[] | 2 | [1, -1]
↑   ↑      ↑
0 (pivot)  0
```

## Key Patterns

**Pattern 1: Total Sum Relationship**
- `rightSum = total - leftSum - current`
- Eliminates need for separate right sum calculation

**Pattern 2: Running Sum**
- Maintain `leftSum` as we iterate
- Add current element after checking

**Pattern 3: Edge Handling**
- Empty left: leftSum = 0
- Empty right: rightSum = 0
- Formula handles both naturally

**Pattern 4: Early Return**
- Return immediately on first pivot found
- Guarantees leftmost result

## Complexity Analysis

- **Time**: O(n)
  - First pass: calculate total = O(n)
  - Second pass: find pivot = O(n)
  - Total: O(n)

- **Space**: O(1)
  - Only variables: total, leftSum, rightSum
  - No extra arrays or structures

Compare to alternatives:
- Brute force: O(n²) time, O(1) space
- Two arrays: O(n) time, O(n) space
- This approach: **O(n) time, O(1) space** ← Optimal!

## Common Mistakes

1. **Not updating leftSum after check**: Must add `nums[i]` at end of iteration
2. **Including current element in leftSum**: Should exclude during comparison
3. **Wrong rightSum formula**: Forgetting to subtract `nums[i]`
4. **Returning inside loop**: Should be after comparison, not before
5. **Integer overflow**: For very large arrays (use `long` if needed)

## Interview Tips

1. **Explain the insight**: "We can calculate right sum using total - left - current"
2. **Walk through example**: Show how leftSum evolves
3. **Discuss edge cases**: Empty sides, negative numbers, no pivot
4. **Mention optimization**: Why this is better than brute force
5. **Consider variants**: All pivots, rightmost pivot, closest sums