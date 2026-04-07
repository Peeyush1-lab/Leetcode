# Maximum Count of Positive Integer and Negative Integer - Study Notes

## 1. Why This Approach (Binary Search for Boundaries)

### The Core Problem
Find max(count of negatives, count of positives) in a sorted array.

### The Key Insight
Array is sorted: `[negatives... zeros... positives]`

Use **binary search twice**:
1. Find first position where `nums[i] >= 0` (first non-negative)
2. Find first position where `nums[i] >= 1` (first positive)

### The Counting Logic

```java
int neg = BinarySearch(nums, 0);        // Count of negatives
int pos = nums.length - BinarySearch(nums, 1);  // Count of positives
```

**Why this works:**
- `BinarySearch(nums, 0)` returns index of first element >= 0
  - All elements before this index are negative
  - Count of negatives = this index

- `BinarySearch(nums, 1)` returns index of first element >= 1
  - All elements from this index onward are positive
  - Count of positives = length - this index

### Binary Search Logic

Looking for **first position** where `nums[i] >= target`:

```java
if (nums[mid] < target)
    start = mid + 1;  // Answer is to the right
else
    end = mid - 1;    // This could be answer, search left for earlier one
```

After loop: `start` points to first element >= target (or length if none exist)

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Linear Scan ✓ (Works but O(n))
```java
public int linearScan(int[] nums) {
    int neg = 0, pos = 0;

    for (int num : nums) {
        if (num < 0) neg++;
        else if (num > 0) pos++;
    }

    return Math.max(neg, pos);
}
```
**Works**: O(n) time
**Why less ideal**: Doesn't use sorted property

### Approach 2: Count Zeros Too ✓ (Works but Unnecessary)
```java
public int countAllThree(int[] nums) {
    int neg = 0, pos = 0, zero = 0;

    for (int num : nums) {
        if (num < 0) neg++;
        else if (num > 0) pos++;
        else zero++;
    }

    return Math.max(neg, pos);
}
```
**Works**: But don't need zero count
**Why unnecessary**: Problem only asks for max(neg, pos)

### Approach 3: Single Binary Search ❌ (Incomplete)
```java
// Only find boundary of negatives
int firstNonNeg = binarySearch(nums, 0);
int neg = firstNonNeg;
// But how to count positives? Need to handle zeros!
```
**Problem**: Can't distinguish zeros from positives easily
**Why incomplete**: Need two searches or extra logic

### Approach 4: HashSet ❌ (Wrong Tool)
```java
Set<Integer> set = new HashSet<>();
// ...doesn't help with counting or leverage sorted property
```
**Problem**: Sorting information is wasted
**Why wrong**: Binary search is the right tool for sorted arrays

### Why Binary Search Twice Wins
- ✅ **O(log n) time** - meets follow-up requirement
- ✅ **O(1) space** - no extra structures
- ✅ **Clean separation** - negatives vs positives
- ✅ **Handles zeros** - naturally excluded from both counts

## 3. How to Handle Changes

### Change 1: Return Both Counts
**Modification**: Return array [neg, pos]

```java
public int[] getCounts(int[] nums) {
    int neg = binarySearch(nums, 0);
    int pos = nums.length - binarySearch(nums, 1);

    return new int[]{neg, pos};
}
```

### Change 2: Count Zeros
**Modification**: Also return count of zeros

```java
public int[] getCountsWithZeros(int[] nums) {
    int firstNonNeg = binarySearch(nums, 0);
    int firstPos = binarySearch(nums, 1);

    int neg = firstNonNeg;
    int zeros = firstPos - firstNonNeg;
    int pos = nums.length - firstPos;

    return new int[]{neg, zeros, pos};
}
```

### Change 3: Check If More Positives
**Modification**: Return boolean - are there more positives?

```java
public boolean morePositives(int[] nums) {
    int neg = binarySearch(nums, 0);
    int pos = nums.length - binarySearch(nums, 1);

    return pos > neg;
}
```

### Change 4: Find Difference
**Modification**: Return |pos - neg|

```java
public int differencePosNeg(int[] nums) {
    int neg = binarySearch(nums, 0);
    int pos = nums.length - binarySearch(nums, 1);

    return Math.abs(pos - neg);
}
```

### Change 5: Count By Range
**Modification**: Count numbers in range [L, R]

```java
public int countInRange(int[] nums, int L, int R) {
    // Find first >= L
    int leftIdx = binarySearch(nums, L);
    // Find first > R (which is first >= R+1)
    int rightIdx = binarySearch(nums, R + 1);

    return rightIdx - leftIdx;
}
```

## Visual Walkthrough

**Input**: `nums = [-2,-1,-1,1,2,3]`

```
Array: [-2, -1, -1, 1, 2, 3]
Index:  0   1   2   3  4  5

Step 1: Find first element >= 0
Binary search for 0:

  Initial: start=0, end=5

  Iteration 1:
    mid = 2, nums[2] = -1
    -1 < 0? Yes → start = 3

  Iteration 2:
    mid = 4, nums[4] = 2
    2 < 0? No → end = 3

  Iteration 3:
    mid = 3, nums[3] = 1
    1 < 0? No → end = 2

  Loop ends: start = 3

  First non-negative at index 3
  Count of negatives = 3 ✓

Step 2: Find first element >= 1
Binary search for 1:

  Initial: start=0, end=5

  Iteration 1:
    mid = 2, nums[2] = -1
    -1 < 1? Yes → start = 3

  Iteration 2:
    mid = 4, nums[4] = 2
    2 < 1? No → end = 3

  Iteration 3:
    mid = 3, nums[3] = 1
    1 < 1? No → end = 2

  Loop ends: start = 3

  First positive at index 3
  Count of positives = 6 - 3 = 3 ✓

Result: max(3, 3) = 3 ✓
```

**Input**: `nums = [-3,-2,-1,0,0,1,2]`

```
Array: [-3, -2, -1, 0, 0, 1, 2]
Index:  0   1   2   3  4  5  6

Binary search for 0:
  Returns index 3 (first >= 0)
  Negatives = 3

Binary search for 1:
  Returns index 5 (first >= 1)
  Positives = 7 - 5 = 2

Result: max(3, 2) = 3 ✓

Note: Zeros (at index 3,4) are excluded from both counts
```

## Key Patterns

**Pattern 1: Binary Search for First Occurrence**
- Find leftmost position where condition is true
- `start` points to answer after loop

**Pattern 2: Counting via Indices**
- Elements before index = count
- Elements from index onward = length - index

**Pattern 3: Two Searches for Boundaries**
- First search: boundary between negative and non-negative
- Second search: boundary between non-positive and positive

**Pattern 4: Zeros Handling**
- Naturally excluded: not in negative count, not in positive count

## Complexity Analysis

- **Time**: O(log n)
  - Two binary searches: O(log n) each
  - Total: O(log n)

- **Space**: O(1)
  - Only variables

Compare to alternatives:
- Linear scan: O(n)
- Binary search: **O(log n)** ← Meets follow-up!

## Common Mistakes

1. **Wrong target values**: Using 1 for both searches
2. **Incorrect count formula**: Using `start` for positives instead of `length - start`
3. **Not handling zeros**: Accidentally counting zeros in one category
4. **Wrong comparison in binary search**: Using `<=` instead of `<`
5. **Off-by-one**: Wrong index arithmetic for counts

## Interview Tips

1. **Explain the insight**: Two binary searches for boundaries
2. **Draw the array**: Show negative/zero/positive regions
3. **Walk through binary search**: Show how it finds first >= target
4. **Explain counting**: Why index = count for negatives
5. **Mention complexity**: O(log n) vs O(n) linear scan
6. **Handle edge cases**: All negative, all positive, all zeros

## Edge Cases

```java
// All negative
nums = [-5, -4, -3]
neg = 3, pos = 0 → return 3

// All positive
nums = [1, 2, 3]
neg = 0, pos = 3 → return 3

// All zeros
nums = [0, 0, 0]
neg = 0, pos = 0 → return 0

// Mixed with zeros
nums = [-1, 0, 0, 1]
neg = 1, pos = 1 → return 1
```