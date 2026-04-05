# Kth Missing Positive Number - Study Notes

## 1. Why This Approach (Binary Search on Missing Count)

### The Core Problem
Find the kth positive integer **not** in the sorted array.

### The Key Formula
For any element at index `i`:
- **Expected value** if no numbers missing: `i + 1` (1-indexed)
- **Actual value**: `arr[i]`
- **Numbers missing before index i**: `arr[i] - (i + 1)` = `arr[i] - 1 - i`

### The Binary Search Strategy

We're searching for the **boundary** where:
- Left side: fewer than k numbers missing
- Right side: at least k numbers missing

After binary search, `left` points to the first position where we've seen k or more missing numbers.

### The Final Formula
```java
return left + k;
```

**Why?**
- `left` = how many array elements come before answer
- After `left` elements, we need `k` more to reach the kth missing
- Total: `left + k`

### How Binary Search Decides

```java
if (arr[mid] - 1 - mid < k)
    left = mid + 1;  // Not enough missing yet, search right
else
    right = mid - 1;  // Too many missing, search left
```

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Linear Scan ✓ (Works but O(n))
```java
public int linearScan(int[] arr, int k) {
    int missing = 0;
    int current = 1;
    int i = 0;

    while (missing < k) {
        if (i < arr.length && arr[i] == current) {
            i++;
        } else {
            missing++;
            if (missing == k) return current;
        }
        current++;
    }

    return current - 1;
}
```
**Works**: O(n) time
**Why less ideal**: Doesn't leverage sorted property fully

### Approach 2: HashSet ❌ (Wrong Approach)
```java
Set<Integer> set = new HashSet<>();
for (int num : arr) set.add(num);

int missing = 0;
for (int i = 1; ; i++) {
    if (!set.contains(i)) {
        missing++;
        if (missing == k) return i;
    }
}
```
**Problem**: May need to iterate very far (if arr has large numbers)
**Why fails**: O(max(arr)) time, unbounded

### Approach 3: Iterate and Count ✓ (Works, O(n))
```java
public int iterateCount(int[] arr, int k) {
    for (int i = 0; i < arr.length; i++) {
        int missingBefore = arr[i] - 1 - i;
        if (missingBefore >= k) {
            return i + k;
        }
    }
    return arr.length + k;
}
```
**Works**: O(n) linear scan
**Why less ideal**: Binary search is O(log n)

### Approach 4: Formula Without Search ❌ (Doesn't Handle All Cases)
```java
// Try to use pure formula
return arr[arr.length - 1] + k;  // Wrong!
```
**Problem**: Assumes all missing are after array
**Why fails**: Missing numbers can be before/between elements

### Why Binary Search Wins
- ✅ **O(log n) time** - optimal
- ✅ **O(1) space** - no extra structures
- ✅ **Leverages sorted property** - skip large portions
- ✅ **Clean formula** - `left + k` at the end

## 3. How to Handle Changes

### Change 1: Find All Missing Numbers Up to K
**Modification**: Return list of missing numbers

```java
public List<Integer> findAllMissing(int[] arr, int k) {
    List<Integer> missing = new ArrayList<>();
    int i = 0;
    int current = 1;

    while (missing.size() < k) {
        if (i < arr.length && arr[i] == current) {
            i++;
        } else {
            missing.add(current);
        }
        current++;
    }

    return missing;
}
```

### Change 2: Kth Missing From Specific Start
**Modification**: Start counting from value other than 1

```java
public int kthMissingFromStart(int[] arr, int k, int start) {
    int left = 0, right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;
        // Adjust formula for different start
        int missingCount = (arr[mid] - start) - mid;

        if (missingCount < k) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return left + k + start - 1;
}
```

### Change 3: Count Missing Numbers in Range
**Modification**: Count how many missing in [L, R]

```java
public int countMissingInRange(int[] arr, int L, int R) {
    int totalInRange = R - L + 1;
    int presentInRange = 0;

    for (int num : arr) {
        if (num >= L && num <= R) {
            presentInRange++;
        }
    }

    return totalInRange - presentInRange;
}
```

### Change 4: Kth Missing Negative Number
**Modification**: Array has negatives, find kth missing

```java
public int kthMissingNegative(int[] arr, int k) {
    // Similar logic but adjust for negative range
    // Need to know expected range (e.g., starting from -1000)
    int start = -1000;  // Or find min in array

    int left = 0, right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;
        int missingCount = (arr[mid] - start) - mid;

        if (missingCount < k) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return left + k + start - 1;
}
```

### Change 5: Return Index of Kth Missing
**Modification**: Return where kth missing would be inserted

```java
public int indexOfKthMissing(int[] arr, int k) {
    int left = 0, right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] - 1 - mid < k) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    // left is the index where kth missing would be inserted
    return left;
}
```

## Visual Walkthrough

**Input**: `arr = [2,3,4,7,11]`, `k = 5`

```
Missing numbers: [1, 5, 6, 8, 9, 10, ...]
We need the 5th one: 9

Binary Search:
Initial: left=0, right=4

Iteration 1:
  mid = 2
  arr[2] = 4
  Missing before index 2: arr[2] - 1 - 2 = 4 - 1 - 2 = 1
  1 < 5? Yes → left = 3

Iteration 2:
  left=3, right=4
  mid = 3
  arr[3] = 7
  Missing before index 3: 7 - 1 - 3 = 3
  3 < 5? Yes → left = 4

Iteration 3:
  left=4, right=4
  mid = 4
  arr[4] = 11
  Missing before index 4: 11 - 1 - 4 = 6
  6 < 5? No → right = 3

Loop ends: left=4, right=3

Answer: left + k = 4 + 5 = 9 ✓

Verification:
Position 4 means 4 array elements come before answer
After [2,3,4,7], we have 3 missing: [1,5,6]
Need 2 more missing to reach 5th: [8,9]
Answer: 9 ✓
```

**Input**: `arr = [1,2,3,4]`, `k = 2`

```
Missing numbers: [5, 6, 7, ...]
Need 2nd: 6

Binary Search:
Initial: left=0, right=3

Iteration 1:
  mid = 1
  arr[1] = 2
  Missing: 2 - 1 - 1 = 0
  0 < 2? Yes → left = 2

Iteration 2:
  left=2, right=3
  mid = 2
  arr[2] = 3
  Missing: 3 - 1 - 2 = 0
  0 < 2? Yes → left = 3

Iteration 3:
  left=3, right=3
  mid = 3
  arr[3] = 4
  Missing: 4 - 1 - 3 = 0
  0 < 2? Yes → left = 4

Loop ends: left=4, right=3

Answer: 4 + 2 = 6 ✓

All missing are after array: [5, 6, ...]
```

## Key Patterns

**Pattern 1: Missing Count Formula**
- `arr[i] - 1 - i` = count of missing before index i
- Assumes array should be [1,2,3,...]

**Pattern 2: Binary Search on Property**
- Not searching for value, searching for boundary
- Where missing count crosses k

**Pattern 3: Final Position Calculation**
- `left` = number of array elements before answer
- Add k to get the kth missing

**Pattern 4: Handle Edge Cases**
- All missing before array: left = 0
- All missing after array: left = arr.length

## Complexity Analysis

- **Time**: O(log n)
  - Binary search: O(log n)
  - Each iteration: O(1)

- **Space**: O(1)
  - Only variables: left, right, mid

Compare to alternatives:
- Linear scan: O(n)
- Binary search: **O(log n)** ← Optimal!

## Common Mistakes

1. **Wrong missing formula**: Using `arr[i] - i` instead of `arr[i] - 1 - i`
2. **Wrong final formula**: Using `right + k` or `mid + k`
3. **Off-by-one**: Forgetting the `-1` in missing count
4. **Not handling array end**: When all missing are after array
5. **Wrong comparison**: Using `<=` instead of `<` in condition

## Interview Tips

1. **Explain the formula**: Why `arr[i] - 1 - i` counts missing
2. **Draw number line**: Show expected vs actual positions
3. **Walk through binary search**: How it finds boundary
4. **Justify final answer**: Why `left + k` works
5. **Discuss optimization**: O(log n) vs O(n) linear scan
6. **Handle edge cases**: All missing before/after array

## Formula Derivation

```
If no numbers were missing:
Index 0 → value 1
Index 1 → value 2
Index i → value i+1

Actual value at index i: arr[i]
Expected value at index i: i+1

Numbers skipped = arr[i] - (i+1) = arr[i] - 1 - i

This counts how many positive integers are missing
before position i in the array.
```