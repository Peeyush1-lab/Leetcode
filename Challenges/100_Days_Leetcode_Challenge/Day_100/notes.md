# Find the Duplicate Number - Study Notes

## 1. Why This Approach (Floyd's Cycle Detection - Tortoise and Hare)

### The Core Problem
Find the duplicate in an array where:
- `n+1` integers in range `[1, n]`
- Cannot modify array
- Must use O(1) space
- One number appears twice or more

### The Brilliant Insight
**Treat the array as a linked list!**

Each index points to the value `nums[i]`:
- `nums[0]` → index `nums[0]`
- `nums[nums[0]]` → index `nums[nums[0]]`
- Continue...

**Why this creates a cycle:**
- Since there are n+1 values in range [1,n], by pigeonhole principle, one must repeat
- The duplicate value creates multiple "pointers" to the same node
- This forms a cycle in our implicit linked list

### Floyd's Cycle Detection Algorithm

**Phase 1: Detect cycle exists**
```java
slow = nums[slow];        // Move 1 step
fast = nums[nums[fast]];  // Move 2 steps
```
Continue until `slow == fast` (they meet inside the cycle)

**Phase 2: Find cycle entrance**
```java
slow = 0;  // Reset one pointer to start
// Move both at same speed
while (slow != fast) {
    slow = nums[slow];
    fast = nums[fast];
}
return slow;  // Meeting point is the duplicate
```

### Why This Works

**Key insight:** The duplicate number is the **entrance to the cycle**!

Example: `[1,3,4,2,2]`
```
Index: 0  1  2  3  4
Value: 1  3  4  2  2

Implicit linked list:
0 → 1 → 3 → 2 → 4 → 2 (cycle back)
            ↑_________|

The duplicate (2) is where the cycle begins!
```

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: HashSet ❌ (Violates Space Constraint)
```java
public int hashSet(int[] nums) {
    Set<Integer> seen = new HashSet<>();

    for (int num : nums) {
        if (seen.contains(num)) {
            return num;
        }
        seen.add(num);
    }

    return -1;
}
```
**Problem**: O(n) space
**Why fails**: Violates "constant extra space" requirement

### Approach 2: Sort ❌ (Modifies Array)
```java
public int sort(int[] nums) {
    Arrays.sort(nums);

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i-1]) {
            return nums[i];
        }
    }

    return -1;
}
```
**Problem**: Modifies array
**Why fails**: Violates "without modifying array" requirement

### Approach 3: Marking (Negation) ❌ (Modifies Array)
```java
public int marking(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        int index = Math.abs(nums[i]);
        if (nums[index] < 0) {
            return index;
        }
        nums[index] = -nums[index];
    }
    return -1;
}
```
**Problem**: Modifies array
**Why fails**: Changes original array values

### Approach 4: Binary Search ✓ (Works, O(n log n))
```java
public int binarySearch(int[] nums) {
    int left = 1, right = nums.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;
        int count = 0;

        // Count numbers <= mid
        for (int num : nums) {
            if (num <= mid) count++;
        }

        if (count > mid) {
            right = mid;  // Duplicate in [left, mid]
        } else {
            left = mid + 1;  // Duplicate in [mid+1, right]
        }
    }

    return left;
}
```
**Works**: O(n log n) time, O(1) space
**Why less ideal**: Slower than Floyd's O(n)

### Why Floyd's Cycle Detection Wins
- ✅ **O(n) time** - optimal
- ✅ **O(1) space** - meets requirement
- ✅ **No modification** - read-only
- ✅ **Elegant** - beautiful algorithm
- ✅ **Meets all constraints** - perfect solution

## 3. How to Handle Changes

### Change 1: Find All Duplicates
**Modification**: Multiple duplicates possible

```java
public List<Integer> findAllDuplicates(int[] nums) {
    List<Integer> duplicates = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
        int index = Math.abs(nums[i]) - 1;
        if (nums[index] < 0) {
            duplicates.add(index + 1);
        } else {
            nums[index] = -nums[index];
        }
    }

    // Restore array if needed
    for (int i = 0; i < nums.length; i++) {
        nums[i] = Math.abs(nums[i]);
    }

    return duplicates;
}
```

### Change 2: Count Occurrences
**Modification**: How many times does duplicate appear?

```java
public int countDuplicate(int[] nums) {
    int duplicate = findDuplicate(nums);
    int count = 0;

    for (int num : nums) {
        if (num == duplicate) count++;
    }

    return count;
}
```

### Change 3: Find Missing Number
**Modification**: Find what's missing instead

```java
public int findMissing(int[] nums) {
    // XOR approach
    int xor = 0;
    int n = nums.length - 1;

    for (int i = 0; i < nums.length; i++) {
        xor ^= nums[i];
    }

    for (int i = 1; i <= n; i++) {
        xor ^= i;
    }

    return xor;
}
```

### Change 4: Return Both Duplicate and Missing
**Modification**: Array has one duplicate, one missing

```java
public int[] findErrorNums(int[] nums) {
    int duplicate = -1, missing = -1;

    for (int i = 0; i < nums.length; i++) {
        int index = Math.abs(nums[i]) - 1;
        if (nums[index] < 0) {
            duplicate = index + 1;
        } else {
            nums[index] = -nums[index];
        }
    }

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            missing = i + 1;
        }
        nums[i] = Math.abs(nums[i]);
    }

    return new int[]{duplicate, missing};
}
```

### Change 5: Works with Negative Numbers
**Modification**: Handle negative values

```java
// Floyd's still works if we have values in range [-n, -1] or [1, n]
// Just need to ensure no value is 0 (which would create self-loop)
public int findDuplicateWithNegatives(int[] nums) {
    // Same algorithm, works with negatives!
    int slow = 0, fast = 0;

    do {
        slow = Math.abs(nums[slow]);
        fast = Math.abs(nums[Math.abs(nums[fast])]);
    } while (slow != fast);

    slow = 0;
    while (slow != fast) {
        slow = Math.abs(nums[slow]);
        fast = Math.abs(nums[fast]);
    }

    return slow;
}
```

## Visual Walkthrough

**Input**: `nums = [1,3,4,2,2]`

```
Array as implicit linked list:
Index: 0  1  2  3  4
Value: 1  3  4  2  2

Graph representation:
0 → 1 → 3 → 2 → 4
        ↑       ↓
        └───────┘

Cycle entrance: index 2 (value 2)

Phase 1: Find meeting point
slow  fast
0     0
↓     ↓
1     3    (slow: 0→1, fast: 0→1→3)
↓     ↓
3     4    (slow: 1→3, fast: 3→2→4)
↓     ↓
2     2    (slow: 3→2, fast: 4→2→4→2)

Meeting at index 2!

Phase 2: Find entrance
slow = 0 (reset)
fast = 2 (from meeting point)

Step 1:
slow: 0 → 1
fast: 2 → 4

Step 2:
slow: 1 → 3
fast: 4 → 2

Step 3:
slow: 3 → 2
fast: 2 → 4

Step 4:
slow: 2
fast: 4 → 2

They meet at 2!
Duplicate = 2 ✓
```

## Key Patterns

**Pattern 1: Array as Linked List**
- Treat indices as nodes
- Values as "next" pointers
- Creates implicit linked list

**Pattern 2: Cycle Detection**
- Floyd's algorithm (slow/fast pointers)
- Meeting in cycle guaranteed

**Pattern 3: Cycle Entrance**
- Duplicate is where cycle begins
- Two-phase algorithm finds it

**Pattern 4: Do-While Loop**
- Start both at 0 (same position)
- Move first, then check
- Ensures at least one iteration

## Complexity Analysis

- **Time**: O(n)
  - Phase 1: O(n) to find meeting point
  - Phase 2: O(n) to find entrance
  - Total: O(n)

- **Space**: O(1)
  - Only two pointers

Compare to alternatives:
- HashSet: O(n) time, O(n) space ❌
- Sort: O(n log n) time, O(1) space, modifies array ❌
- Binary Search: O(n log n) time, O(1) space ✓
- Floyd's: **O(n) time, O(1) space, no modification** ✓✓✓

## Common Mistakes

1. **Using while instead of do-while**: Misses first iteration when both start at 0
2. **Not resetting slow in phase 2**: Both pointers must start fresh
3. **Moving both at same speed in phase 1**: Need different speeds to detect cycle
4. **Returning fast instead of slow**: Both work, but convention is slow
5. **Starting at wrong index**: Must start at index 0, not index 1

## Interview Tips

1. **Explain linked list insight**: Key to understanding the algorithm
2. **Draw the graph**: Visual helps immensely
3. **Mention Floyd's algorithm**: Show knowledge of classic algorithm
4. **Discuss constraints**: Why other approaches don't work
5. **Walk through phases**: Explain why two phases needed
6. **Prove correctness**: Why duplicate is cycle entrance

## Why This is Brilliant

This problem is a masterclass in **creative problem transformation**:

1. **See beyond the obvious**: Array → Linked List
2. **Apply classic algorithm**: Floyd's Cycle Detection
3. **Meet all constraints**: O(n) time, O(1) space, no modification
4. **Beautiful mathematics**: Cycle theory proves correctness

**The "Aha!" moment**: Realizing the duplicate creates the cycle entrance!