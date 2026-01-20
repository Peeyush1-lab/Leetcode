# Notes: Relative Sort Array

## Problem Analysis

This is a **custom sorting** problem where we need to sort based on a specific order defined by `arr2`, with remaining elements sorted in ascending order.

### Requirements
1. Elements appearing in `arr2` must maintain their relative order from `arr2`
2. Elements NOT in `arr2` should be placed at the end in ascending order
3. If an element appears multiple times in `arr1`, all occurrences should be together

## Algorithm Breakdown

### Approach: Counting Sort

This solution uses **counting sort**, which is perfect for problems with a limited range of values.

### Why Array Size 1001?
```java
int arr[] = new int[1001];
```
- According to problem constraints: `0 <= arr1[i] <= 1000`
- We need indices from 0 to 1000 (inclusive)
- Array size = 1001

### Three-Phase Algorithm

## Phase 1: Count Frequencies

```java
for(int i : arr1) {
    arr[i]++;
}
```

**What it does:**
- Count how many times each number appears in `arr1`
- `arr[i]` stores the count of number `i`

**Example:**
```
arr1 = [2,3,1,3,2,4,6,7,9,2,19]

After counting:
arr[1] = 1  (one 1)
arr[2] = 3  (three 2s)
arr[3] = 2  (two 3s)
arr[4] = 1  (one 4)
arr[6] = 1  (one 6)
arr[7] = 1  (one 7)
arr[9] = 1  (one 9)
arr[19] = 1 (one 19)
```

## Phase 2: Place Elements from arr2

```java
int k = 0;
for(int j : arr2) {
    while(arr[j] != 0) {
        arr1[k++] = j;
        arr[j]--;
    }
}
```

**What it does:**
- Iterate through `arr2` in order
- For each element in `arr2`, place all its occurrences into `arr1`
- Decrement the count after each placement

**Example:**
```
arr2 = [2,1,4,3,9,6]
arr1 result = [?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?]

Process arr2[0] = 2:
  arr[2] = 3, so place 2 three times
  arr1 = [2,2,2,?,?,?,?,?,?,?,?]
  k = 3

Process arr2[1] = 1:
  arr[1] = 1, so place 1 once
  arr1 = [2,2,2,1,?,?,?,?,?,?,?]
  k = 4

Process arr2[2] = 4:
  arr[4] = 1, so place 4 once
  arr1 = [2,2,2,1,4,?,?,?,?,?,?]
  k = 5

Process arr2[3] = 3:
  arr[3] = 2, so place 3 twice
  arr1 = [2,2,2,1,4,3,3,?,?,?,?]
  k = 7

Process arr2[4] = 9:
  arr[9] = 1, so place 9 once
  arr1 = [2,2,2,1,4,3,3,9,?,?,?]
  k = 8

Process arr2[5] = 6:
  arr[6] = 1, so place 6 once
  arr1 = [2,2,2,1,4,3,3,9,6,?,?]
  k = 9
```

## Phase 3: Place Remaining Elements

```java
for(int i = 0; i < arr.length; i++) {
    while(arr[i] != 0) {
        arr1[k++] = i;
        arr[i]--;
    }
}
```

**What it does:**
- Iterate through the counting array from 0 to 1000
- Place any remaining elements (those not in `arr2`) in ascending order
- Since we iterate from smallest to largest, output is automatically sorted

**Example (continuing from above):**
```
Remaining counts:
arr[7] = 1
arr[19] = 1

Process i = 7:
  arr1 = [2,2,2,1,4,3,3,9,6,7,?]
  k = 10

Process i = 19:
  arr1 = [2,2,2,1,4,3,3,9,6,7,19]
  k = 11

Final result: [2,2,2,1,4,3,3,9,6,7,19]
```

## Complete Walkthrough

### Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]

**Step 1: Build frequency array**
```
arr[1]=1, arr[2]=3, arr[3]=2, arr[4]=1, arr[6]=1, arr[7]=1, arr[9]=1, arr[19]=1
```

**Step 2: Process arr2 elements**
```
2 appears 3 times → [2,2,2,_,_,_,_,_,_,_,_]
1 appears 1 time  → [2,2,2,1,_,_,_,_,_,_,_]
4 appears 1 time  → [2,2,2,1,4,_,_,_,_,_,_]
3 appears 2 times → [2,2,2,1,4,3,3,_,_,_,_]
9 appears 1 time  → [2,2,2,1,4,3,3,9,_,_,_]
6 appears 1 time  → [2,2,2,1,4,3,3,9,6,_,_]
```

**Step 3: Add remaining elements in ascending order**
```
7 (not in arr2) → [2,2,2,1,4,3,3,9,6,7,_]
19 (not in arr2) → [2,2,2,1,4,3,3,9,6,7,19]
```

## Key Techniques

### 1. Counting Sort Pattern
```java
// Count frequencies
for(int num : array) {
    count[num]++;
}

// Use counts to build result
while(count[num] > 0) {
    result[k++] = num;
    count[num]--;
}
```

### 2. Post-increment Operator
```java
arr1[k++] = j;
```
- First assigns `j` to `arr1[k]`
- Then increments `k`
- Equivalent to: `arr1[k] = j; k++;`

### 3. In-place Modification
- We reuse `arr1` for output instead of creating new array
- Saves space

## Time Complexity Analysis

1. **Phase 1 (Counting):** O(n) where n = arr1.length
2. **Phase 2 (arr2 order):** O(m) where m = arr2.length
3. **Phase 3 (Remaining):** O(k) where k = 1001 (constant)

**Total:** O(n + m + k) = O(n + m) since k is constant

## Space Complexity Analysis

- Counting array: O(1001) = O(1) constant space
- No additional space proportional to input size
- **Total:** O(1)

## Why This Approach Works

1. **Counting array handles duplicates automatically**
   - All occurrences counted together
   - All placed together when processing

2. **Order preservation**
   - arr2 order preserved in Phase 2
   - Ascending order for remaining in Phase 3

3. **Efficient for limited range**
   - When values are 0-1000, counting sort is O(n)
   - Much faster than comparison-based sorting O(n log n)

## Alternative Approaches

### Approach 1: Using HashMap + Custom Comparator
```java
// Less efficient: O(n log n)
Map<Integer, Integer> rank = new HashMap<>();
for(int i = 0; i < arr2.length; i++) {
    rank.put(arr2[i], i);
}
Arrays.sort(arr1, (a, b) -> {
    if(rank.containsKey(a) && rank.containsKey(b)) {
        return rank.get(a) - rank.get(b);
    } else if(rank.containsKey(a)) {
        return -1;
    } else if(rank.containsKey(b)) {
        return 1;
    } else {
        return a - b;
    }
});
```

**Why counting sort is better here:**
- O(n) vs O(n log n)
- Simpler code
- Takes advantage of limited value range

## Common Mistakes to Avoid

1. **Forgetting to decrement count**
   - Without `arr[j]--`, infinite loop occurs

2. **Wrong array size**
   - Array must be size 1001, not 1000
   - Handles values from 0 to 1000 inclusive

3. **Not handling duplicates properly**
   - While loop ensures all occurrences are placed

4. **Incorrect loop bounds**
   - Phase 3 must iterate through entire counting array
   - Otherwise some elements might be missed

## When to Use This Pattern

Use counting sort when:
- Input values have a limited, known range
- Range is not too large (like 0-1000)
- Need O(n) sorting instead of O(n log n)
- Custom ordering is required