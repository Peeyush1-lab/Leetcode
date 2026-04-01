# Check If N and Its Double Exist - Study Notes

## 1. Why This Approach (HashSet Single Pass)

### The Core Problem
Check if there exist two **different** elements where one is **double** of the other.

For element `x`, we need to check:
- Does `2 * x` exist? (x's double)
- Does `x / 2` exist? (x is double of something)

### Why HashSet?
**O(1) lookup** for checking existence:
- As we iterate, store seen elements
- For current element, check if its double/half exists in set
- If yes, found pair!
- If no, add current to set and continue

### The Division Check
```java
i % 2 == 0 && set.contains(i/2)
```

**Why check `i % 2 == 0`?**
- Only check `i/2` if `i` is even
- Avoids incorrect matches from integer division
- Example: `i = 5`, `i/2 = 2` (wrong! 2*2=4, not 5)

### Algorithm Flow
```
For each element i:
1. Check if i*2 exists in set → found pair
2. Check if i is even AND i/2 exists → found pair
3. Add i to set
4. Continue to next element
Return false if no pair found
```

### Why This Works
- Single pass: O(n)
- HashSet operations: O(1)
- Checks both directions: x→2x and x→x/2
- Adding after checking prevents matching with itself

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Brute Force Nested Loops ✓ (Works but Slow)
```java
public boolean bruteForce(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
            if (i != j && arr[i] == 2 * arr[j]) {
                return true;
            }
        }
    }
    return false;
}
```
**Works**: Correct
**Why less ideal**: O(n²) time

### Approach 2: Sort + Binary Search ✓ (Works but Complex)
```java
public boolean sortAndSearch(int[] arr) {
    Arrays.sort(arr);

    for (int i = 0; i < arr.length; i++) {
        int target = arr[i] * 2;
        int idx = Arrays.binarySearch(arr, target);

        if (idx >= 0 && idx != i) {
            return true;
        }
    }

    return false;
}
```
**Works**: O(n log n) time
**Why less ideal**: Modifies array, more code, sorting overhead

### Approach 3: Sort + Two Pointers ❌ (Doesn't Work Well)
```java
public boolean twoPointers(int[] arr) {
    Arrays.sort(arr);
    int left = 0, right = arr.length - 1;

    // Can't easily find double relationship with two pointers
    // Would need to track which direction to move
}
```
**Problem**: Not suited for double relationship
**Why fails**: Two Sum works for sum, not for multiplication

### Approach 4: HashMap Count ❌ (Overcomplicated)
```java
public boolean hashMapCount(int[] arr) {
    Map<Integer, Integer> count = new HashMap<>();

    for (int num : arr) {
        count.put(num, count.getOrDefault(num, 0) + 1);
    }

    for (int num : count.keySet()) {
        if (count.containsKey(num * 2)) {
            // Need special handling for 0
            if (num == 0 && count.get(0) < 2) continue;
            return true;
        }
    }

    return false;
}
```
**Works**: But more complex
**Why less ideal**: Two passes, special case handling

### Approach 5: Add Without Checking After ❌ (Wrong)
```java
public boolean wrongApproach(int[] arr) {
    HashSet<Integer> set = new HashSet<>();

    for (int i : arr) {
        set.add(i);  // Add BEFORE checking - WRONG!
        if (set.contains(i * 2) || set.contains(i / 2)) {
            return true;
        }
    }

    return false;
}
```
**Problem**: Matches element with itself
**Example**: `arr = [5]`, `i=5`, after adding 5, checks if 10 or 2.5 exists

### Why HashSet Single Pass Wins
- ✅ **O(n) time** - optimal
- ✅ **O(n) space** - acceptable
- ✅ **Single pass** - efficient
- ✅ **Clean logic** - easy to understand
- ✅ **No sorting** - preserves original array

## 3. How to Handle Changes

### Change 1: Find All Pairs
**Modification**: Return list of all valid pairs

```java
public List<int[]> findAllPairs(int[] arr) {
    List<int[]> pairs = new ArrayList<>();
    HashSet<Integer> set = new HashSet<>();

    for (int i = 0; i < arr.length; i++) {
        if (set.contains(arr[i] * 2)) {
            pairs.add(new int[]{arr[i], arr[i] * 2});
        }
        if (arr[i] % 2 == 0 && set.contains(arr[i] / 2)) {
            pairs.add(new int[]{arr[i] / 2, arr[i]});
        }
        set.add(arr[i]);
    }

    return pairs;
}
```

### Change 2: Check N and Its Triple
**Modification**: Check for 3x relationship

```java
public boolean checkIfTripleExists(int[] arr) {
    HashSet<Integer> set = new HashSet<>();

    for (int i : arr) {
        if (set.contains(i * 3) || (i % 3 == 0 && set.contains(i / 3))) {
            return true;
        }
        set.add(i);
    }

    return false;
}
```

### Change 3: Count How Many Valid Pairs
**Modification**: Return count instead of boolean

```java
public int countPairs(int[] arr) {
    int count = 0;
    HashSet<Integer> set = new HashSet<>();

    for (int i : arr) {
        if (set.contains(i * 2)) count++;
        if (i % 2 == 0 && set.contains(i / 2)) count++;
        set.add(i);
    }

    return count;
}
```

### Change 4: Check With Any Multiplier K
**Modification**: Flexible multiplier

```java
public boolean checkMultiplier(int[] arr, int k) {
    HashSet<Integer> set = new HashSet<>();

    for (int i : arr) {
        if (set.contains(i * k) || (i % k == 0 && set.contains(i / k))) {
            return true;
        }
        set.add(i);
    }

    return false;
}
```

### Change 5: Return Indices Instead of Boolean
**Modification**: Return positions of valid pair

```java
public int[] findIndices(int[] arr) {
    Map<Integer, Integer> indexMap = new HashMap<>();

    for (int i = 0; i < arr.length; i++) {
        if (indexMap.containsKey(arr[i] * 2)) {
            return new int[]{indexMap.get(arr[i] * 2), i};
        }
        if (arr[i] % 2 == 0 && indexMap.containsKey(arr[i] / 2)) {
            return new int[]{indexMap.get(arr[i] / 2), i};
        }
        indexMap.put(arr[i], i);
    }

    return new int[]{-1, -1};
}
```

## Visual Walkthrough

**Input**: `arr = [10,2,5,3]`

```
Iteration 0: i=10
  set = {}
  Check: set.contains(20)? No
  Check: 10%2==0 && set.contains(5)? Yes (10%2==0) but No (5 not in set)
  Add 10 to set
  set = {10}

Iteration 1: i=2
  set = {10}
  Check: set.contains(4)? No
  Check: 2%2==0 && set.contains(1)? Yes (2%2==0) but No (1 not in set)
  Add 2 to set
  set = {10, 2}

Iteration 2: i=5
  set = {10, 2}
  Check: set.contains(10)? Yes! ✓
  Found pair: 5 and 10 (10 = 2 * 5)
  Return true
```

**Input**: `arr = [3,1,7,11]`

```
Iteration 0: i=3
  set = {}
  No matches
  set = {3}

Iteration 1: i=1
  set = {3}
  Check: set.contains(2)? No
  Check: 1%2==0? No (odd)
  set = {3, 1}

Iteration 2: i=7
  set = {3, 1}
  Check: set.contains(14)? No
  Check: 7%2==0? No
  set = {3, 1, 7}

Iteration 3: i=11
  set = {3, 1, 7}
  Check: set.contains(22)? No
  Check: 11%2==0? No
  set = {3, 1, 7, 11}

Return false (no pair found)
```

## Key Patterns

**Pattern 1: HashSet for Existence Check**
- O(1) lookup beats nested loops
- Build set as you go

**Pattern 2: Check Before Add**
- Prevents self-matching
- Important for correctness

**Pattern 3: Both Directions**
- Check `x*2` (is double in set?)
- Check `x/2` (am I double of something in set?)

**Pattern 4: Even Number Check**
- Only divide when even
- Avoids integer division errors

## Complexity Analysis

- **Time**: O(n)
  - Single pass through array
  - HashSet operations: O(1) each

- **Space**: O(n)
  - HashSet stores up to n elements
  - Worst case: all unique, no duplicates

## Common Mistakes

1. **Adding before checking**: Matches element with itself
2. **Forgetting even check**: `5/2=2`, but `2*2≠5`
3. **Only checking one direction**: Missing `x/2` check
4. **Using `i != j` check in single pass**: Can't compare indices in this approach
5. **Not handling zero correctly**: `0*2=0`, special case

## Interview Tips

1. **Clarify constraints**: Can array have duplicates? Negatives?
2. **Explain two checks**: Why both `i*2` and `i/2`
3. **Discuss even check**: Why modulo is important
4. **Mention alternatives**: Brute force, sorting
5. **Handle edge cases**: Single element, all duplicates, zeros