# Find Smallest Letter Greater Than Target - Study Notes

## 1. Why This Approach (Binary Search with Ceiling)

### The Core Problem
Find the **smallest character** lexicographically **greater than** target in a sorted array.

**Special case**: If no such character exists, return the **first** character (wrap around).

### Key Insight: This is a "Ceiling" Problem
In binary search terminology:
- **Floor**: Largest element ≤ target
- **Ceiling**: Smallest element > target

We need the **ceiling** of target.

### The Wrap-Around Trick
```java
return letters[start % letters.length];
```

After binary search:
- If ceiling found: `start` points to it
- If not found (all elements ≤ target): `start == letters.length`
- Modulo wraps: `letters.length % letters.length = 0` → returns `letters[0]`

### Why Binary Search?
Array is sorted → O(log n) search possible
- Linear scan would be O(n)
- Binary search achieves O(log n)

### Algorithm Logic

When `letters[mid] > target`:
- Mid could be answer
- But there might be smaller valid answer to left
- Move: `end = mid - 1`

When `letters[mid] <= target`:
- Mid is NOT answer (we need strictly greater)
- Answer must be to right
- Move: `start = mid + 1`

After loop ends:
- `start` points to smallest element > target
- Or `start == letters.length` if none exists

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Linear Scan ✓ (Works but Slow)
```java
public char linearSearch(char[] letters, char target) {
    for (int i = 0; i < letters.length; i++) {
        if (letters[i] > target) {
            return letters[i];
        }
    }
    return letters[0];  // Wrap around
}
```
**Works**: Correct but O(n)
**Why less ideal**: Doesn't use sorted property

### Approach 2: Standard Binary Search ❌ (Doesn't Handle Ceiling)
```java
public char standardBinarySearch(char[] letters, char target) {
    int left = 0, right = letters.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (letters[mid] == target) {
            return letters[mid + 1];  // Wrong! May be out of bounds
        } else if (letters[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return letters[0];
}
```
**Problem**: Doesn't correctly find ceiling
**Why fails**: Logic assumes exact match exists

### Approach 3: Binary Search + Linear Check ❌
```java
public char hybrid(char[] letters, char target) {
    int idx = binarySearchClosest(letters, target);

    // Linear search from idx
    for (int i = idx; i < letters.length; i++) {
        if (letters[i] > target) return letters[i];
    }

    return letters[0];
}
```
**Problem**: Can be O(n) in worst case
**Example**: `['a','a','a',...,'a','b']`, target='a'

### Approach 4: Recursive Binary Search ✓ (Works but Overkill)
```java
public char recursive(char[] letters, char target) {
    int result = findCeiling(letters, target, 0, letters.length - 1);
    return result == letters.length ? letters[0] : letters[result];
}

private int findCeiling(char[] letters, char target, int left, int right) {
    if (left > right) return left;

    int mid = left + (right - left) / 2;

    if (letters[mid] > target) {
        return findCeiling(letters, target, left, mid - 1);
    } else {
        return findCeiling(letters, target, mid + 1, right);
    }
}
```
**Works**: Correct
**Why less ideal**: Recursion overhead, more complex

### Why Binary Search with Modulo Wins
- ✅ **O(log n) time** - optimal
- ✅ **O(1) space** - no extra structures
- ✅ **Elegant wrap-around** - single line with modulo
- ✅ **Clean iterative** - no recursion

## 3. How to Handle Changes

### Change 1: Find Largest Letter Smaller Than Target
**Modification**: Floor instead of ceiling

```java
public char findFloor(char[] letters, char target) {
    int start = 0, end = letters.length - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2;

        if (letters[mid] >= target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }

    // end points to largest < target
    return end < 0 ? letters[letters.length - 1] : letters[end];
}
```

### Change 2: Return Index Instead of Character
**Modification**: Return position

```java
public int nextGreatestLetterIndex(char[] letters, char target) {
    int start = 0, end = letters.length - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2;

        if (letters[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }

    return start % letters.length;  // Index instead of character
}
```

### Change 3: No Wrap-Around (Return Null If Not Found)
**Modification**: Return special value when not found

```java
public Character nextGreatestLetterNoWrap(char[] letters, char target) {
    int start = 0, end = letters.length - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2;

        if (letters[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }

    return start < letters.length ? letters[start] : null;
}
```

### Change 4: Find Next Greater or Equal (Not Strictly Greater)
**Modification**: Include equal as valid

```java
public char nextGreaterOrEqual(char[] letters, char target) {
    int start = 0, end = letters.length - 1;

    while (start <= end) {
        int mid = start + (end - start) / 2;

        if (letters[mid] >= target) {  // Changed condition
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }

    return letters[start % letters.length];
}
```

### Change 5: Find Kth Next Greater Letter
**Modification**: Skip k letters greater than target

```java
public char kthNextGreater(char[] letters, char target, int k) {
    int start = 0, end = letters.length - 1;

    // Find first greater
    while (start <= end) {
        int mid = start + (end - start) / 2;

        if (letters[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }

    // Skip k-1 more
    return letters[(start + k - 1) % letters.length];
}
```

## Visual Walkthrough

**Input**: `letters = ['c','f','j']`, `target = 'a'`

```
Initial: start=0, end=2

Iteration 1:
  mid = 0 + (2-0)/2 = 1
  letters[1] = 'f' > 'a' ✓
  end = 0

Iteration 2:
  mid = 0 + (0-0)/2 = 0
  letters[0] = 'c' > 'a' ✓
  end = -1

Loop ends: start=0, end=-1
Return: letters[0 % 3] = letters[0] = 'c' ✓
```

**Input**: `letters = ['c','f','j']`, `target = 'c'`

```
Initial: start=0, end=2

Iteration 1:
  mid = 1
  letters[1] = 'f' > 'c' ✓
  end = 0

Iteration 2:
  mid = 0
  letters[0] = 'c' NOT > 'c' (equal)
  start = 1

Loop ends: start=1, end=0
Return: letters[1 % 3] = letters[1] = 'f' ✓
```

**Input**: `letters = ['x','x','y','y']`, `target = 'z'`

```
Initial: start=0, end=3

Iteration 1:
  mid = 1
  letters[1] = 'x' NOT > 'z'
  start = 2

Iteration 2:
  mid = 2 + (3-2)/2 = 2
  letters[2] = 'y' NOT > 'z'
  start = 3

Iteration 3:
  mid = 3
  letters[3] = 'y' NOT > 'z'
  start = 4

Loop ends: start=4, end=3
Return: letters[4 % 4] = letters[0] = 'x' ✓ (wrap around!)
```

## Key Patterns

**Pattern 1: Ceiling Binary Search**
- Find smallest element > target
- Move end when element valid
- Final answer at start position

**Pattern 2: Modulo for Circular Array**
- `index % length` wraps to start
- Elegant handling of "not found" case

**Pattern 3: Strict vs Non-Strict**
- `>` for strictly greater
- `>=` for greater or equal
- Small change, big difference

**Pattern 4: Pointer After Loop**
- `start` points to insertion position
- `end` points to largest < target

## Complexity Analysis

- **Time**: O(log n)
  - Binary search: O(log n)
  - Single modulo operation: O(1)

- **Space**: O(1)
  - Only variables: start, end, mid

## Common Mistakes

1. **Using `==` instead of `>`**: Wrong comparison
2. **Forgetting modulo**: IndexOutOfBounds when wrapping
3. **Using `start - 1`**: Should be `start` for ceiling
4. **Wrong initialization**: start/end at wrong indices
5. **Not handling duplicates**: Works anyway due to > condition

## Interview Tips

1. **Clarify wrap-around**: Confirm circular behavior
2. **Explain ceiling**: Relate to floor/ceiling concept
3. **Walk through edge case**: Show wrap-around example
4. **Mention optimization**: Binary search vs linear
5. **Discuss variations**: Floor, index, no wrap