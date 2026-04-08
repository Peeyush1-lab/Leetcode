# Two Sum II - Input Array Is Sorted - Study Notes

## 1. Why This Approach (Two Pointers)

### The Core Problem
Find two numbers in a **sorted** array that add up to target.

### The Key Insight
**Sorted array** enables the two-pointer technique:
- Start with smallest (left) and largest (right) elements
- If sum too small → move left pointer right (increase sum)
- If sum too large → move right pointer left (decrease sum)
- If sum equals target → found answer!

### Why Two Pointers Work

**Correctness proof:**
- If `numbers[i] + numbers[j] < target`:
  - We need a larger sum
  - `numbers[i]` is too small when paired with largest available (`numbers[j]`)
  - `numbers[i]` won't work with any smaller element either
  - Safe to skip `i`, move to `i+1`

- If `numbers[i] + numbers[j] > target`:
  - We need a smaller sum
  - `numbers[j]` is too large when paired with smallest available (`numbers[i]`)
  - `numbers[j]` won't work with any larger element either
  - Safe to skip `j`, move to `j-1`

### The 1-Indexed Return
```java
return new int[]{i+1, j+1};
```
Problem specifies **1-indexed** output, but array is 0-indexed internally.

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: HashMap (Like Two Sum I) ✓ (Works but O(n) space)
```java
public int[] twoSumHashMap(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < numbers.length; i++) {
        int complement = target - numbers[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement) + 1, i + 1};
        }
        map.put(numbers[i], i);
    }

    return new int[]{};
}
```
**Problem**: Uses O(n) space, violates "constant extra space" requirement
**Why less ideal**: Doesn't leverage sorted property

### Approach 2: Nested Loops ✓ (Works but O(n²))
```java
public int[] bruteForce(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
        for (int j = i + 1; j < numbers.length; j++) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            }
        }
    }
    return new int[]{};
}
```
**Problem**: O(n²) time
**Why less ideal**: Too slow for large arrays

### Approach 3: Binary Search for Each Element ✓ (Works but O(n log n))
```java
public int[] binarySearchApproach(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
        int complement = target - numbers[i];
        int j = binarySearch(numbers, i + 1, complement);

        if (j != -1) {
            return new int[]{i + 1, j + 1};
        }
    }
    return new int[]{};
}
```
**Works**: O(n log n) time, O(1) space
**Why less ideal**: More complex than two pointers, slower

### Approach 4: Three Pointers ❌ (Overcomplicated)
```java
// Use middle pointer somehow?
// Doesn't simplify the problem
```
**Problem**: Two pointers are sufficient
**Why overkill**: Adds unnecessary complexity

### Why Two Pointers Wins
- ✅ **O(n) time** - linear scan
- ✅ **O(1) space** - only pointers
- ✅ **Simple code** - clean and elegant
- ✅ **Optimal** - best possible complexity for this problem

## 3. How to Handle Changes

### Change 1: Return Values Instead of Indices
**Modification**: Return the actual numbers

```java
public int[] twoSumValues(int[] numbers, int target) {
    int i = 0, j = numbers.length - 1;

    while (i < j) {
        int sum = numbers[i] + numbers[j];

        if (sum == target) {
            return new int[]{numbers[i], numbers[j]};
        } else if (sum < target) {
            i++;
        } else {
            j--;
        }
    }

    return new int[]{};
}
```

### Change 2: Find All Pairs
**Modification**: Multiple pairs can sum to target

```java
public List<int[]> findAllPairs(int[] numbers, int target) {
    List<int[]> result = new ArrayList<>();
    int i = 0, j = numbers.length - 1;

    while (i < j) {
        int sum = numbers[i] + numbers[j];

        if (sum == target) {
            result.add(new int[]{i + 1, j + 1});
            i++;
            j--;
        } else if (sum < target) {
            i++;
        } else {
            j--;
        }
    }

    return result;
}
```

### Change 3: Count Pairs Equal to Target
**Modification**: Return count instead of indices

```java
public int countPairs(int[] numbers, int target) {
    int count = 0;
    int i = 0, j = numbers.length - 1;

    while (i < j) {
        int sum = numbers[i] + numbers[j];

        if (sum == target) {
            count++;
            i++;
            j--;
        } else if (sum < target) {
            i++;
        } else {
            j--;
        }
    }

    return count;
}
```

### Change 4: Two Sum Less Than K
**Modification**: Find pair with sum < K that's closest to K

```java
public int twoSumLessThanK(int[] numbers, int k) {
    int i = 0, j = numbers.length - 1;
    int maxSum = -1;

    while (i < j) {
        int sum = numbers[i] + numbers[j];

        if (sum < k) {
            maxSum = Math.max(maxSum, sum);
            i++;
        } else {
            j--;
        }
    }

    return maxSum;
}
```

### Change 5: Three Sum
**Modification**: Find three numbers that sum to target

```java
public List<List<Integer>> threeSum(int[] numbers, int target) {
    List<List<Integer>> result = new ArrayList<>();

    for (int k = 0; k < numbers.length - 2; k++) {
        int newTarget = target - numbers[k];
        int i = k + 1, j = numbers.length - 1;

        while (i < j) {
            int sum = numbers[i] + numbers[j];

            if (sum == newTarget) {
                result.add(Arrays.asList(numbers[k], numbers[i], numbers[j]));
                i++;
                j--;
            } else if (sum < newTarget) {
                i++;
            } else {
                j--;
            }
        }
    }

    return result;
}
```

## Visual Walkthrough

**Input**: `numbers = [2,7,11,15]`, `target = 9`

```
Initial: i=0, j=3

Step 1:
  Array: [2, 7, 11, 15]
          ↑           ↑
          i           j

  Sum: 2 + 15 = 17
  17 > 9? Yes → j--

Step 2:
  Array: [2, 7, 11, 15]
          ↑       ↑
          i       j

  Sum: 2 + 11 = 13
  13 > 9? Yes → j--

Step 3:
  Array: [2, 7, 11, 15]
          ↑   ↑
          i   j

  Sum: 2 + 7 = 9
  9 == 9? Yes! Found it!

  Return [i+1, j+1] = [1, 2] ✓
```

**Input**: `numbers = [2,3,4]`, `target = 6`

```
Initial: i=0, j=2

Step 1:
  Array: [2, 3, 4]
          ↑     ↑
          i     j

  Sum: 2 + 4 = 6
  6 == 6? Yes!

  Return [1, 3] ✓
```

## Key Patterns

**Pattern 1: Two Pointers on Sorted Array**
- Start at both ends
- Move inward based on comparison

**Pattern 2: Sum Comparison**
- Too small → increase smaller element
- Too large → decrease larger element

**Pattern 3: 1-Indexed vs 0-Indexed**
- Internal work: 0-indexed
- Return values: add 1 for 1-indexed

**Pattern 4: Guaranteed Solution**
- No need to handle "not found" case
- Can return empty array for safety

## Complexity Analysis

- **Time**: O(n)
  - Each pointer moves at most n positions
  - Total operations: O(n)

- **Space**: O(1)
  - Only two pointers
  - No extra data structures

Compare to alternatives:
- HashMap: O(n) time, O(n) space
- Brute force: O(n²) time, O(1) space
- Binary search: O(n log n) time, O(1) space
- Two pointers: **O(n) time, O(1) space** ← Optimal!

## Common Mistakes

1. **Forgetting +1 for 1-indexed**: Returning `[i, j]` instead of `[i+1, j+1]`
2. **Wrong loop condition**: Using `i <= j` instead of `i < j`
3. **Not handling duplicates**: When array has duplicate values
4. **Integer overflow**: Adding large numbers without checking
5. **Moving both pointers**: When sum equals target, should return immediately

## Interview Tips

1. **Mention sorted property**: Key to using two pointers
2. **Explain pointer movement**: Why it's safe to skip elements
3. **Draw diagram**: Show pointer movements
4. **Discuss alternatives**: HashMap vs two pointers tradeoff
5. **Handle 1-indexed**: Remember to add 1 to indices
6. **Edge cases**: Two elements only, negative numbers, duplicates

## Why This is Better Than Two Sum I

**Two Sum I (Unsorted):**
- Must use HashMap
- O(n) time, O(n) space

**Two Sum II (Sorted):**
- Can use two pointers
- O(n) time, O(1) space ← Better space complexity!

The sorted property is powerful!