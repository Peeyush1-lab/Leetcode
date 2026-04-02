# Fair Candy Swap - Study Notes

## 1. Why This Approach (Math + HashSet)

### The Core Problem
Find candy boxes to swap so Alice and Bob have **equal** totals.

### The Math Formula

Let:
- `sumA` = Alice's total candies
- `sumB` = Bob's total candies
- `a` = candy box Alice gives
- `b` = candy box Bob gives

After swap:
- Alice has: `sumA - a + b`
- Bob has: `sumB - b + a`

For equality:
```
sumA - a + b = sumB - b + a
sumA + b - a = sumB + a - b
sumA + 2b = sumB + 2a
2b - 2a = sumB - sumA
2(b - a) = sumB - sumA
b - a = (sumB - sumA) / 2
b = a + (sumB - sumA) / 2
```

**Therefore**: `b = a + diff` where `diff = (sumB - sumA) / 2`

### Algorithm Strategy
1. Calculate sum of both arrays
2. Calculate `diff = (sumB - sumA) / 2`
3. Put Bob's candies in HashSet for O(1) lookup
4. For each candy `a` in Alice's array:
   - Check if `a + diff` exists in Bob's set
   - If yes, found answer: `[a, a + diff]`

### Why This Works
- We know what `b` must be given any `a`
- HashSet gives O(1) lookup to check if that `b` exists
- Guaranteed to find answer (problem states one exists)

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Brute Force ✓ (Works but Slow)
```java
public int[] bruteForce(int[] alice, int[] bob) {
    int sumA = sum(alice), sumB = sum(bob);

    for (int a : alice) {
        for (int b : bob) {
            if (sumA - a + b == sumB - b + a) {
                return new int[]{a, b};
            }
        }
    }

    return new int[]{};
}
```
**Problem**: O(n × m) time where n, m are array lengths
**Why less ideal**: Doesn't use the math relationship

### Approach 2: Sort + Binary Search ✓ (Works)
```java
public int[] sortAndSearch(int[] alice, int[] bob) {
    int sumA = sum(alice), sumB = sum(bob);
    int diff = (sumB - sumA) / 2;

    Arrays.sort(bob);

    for (int a : alice) {
        int target = a + diff;
        if (Arrays.binarySearch(bob, target) >= 0) {
            return new int[]{a, target};
        }
    }

    return new int[]{};
}
```
**Works**: O(m log m + n log m) time
**Why less ideal**: Sorting overhead, modifies array

### Approach 3: Sort Both + Two Pointers ❌ (Complex)
```java
public int[] twoPointers(int[] alice, int[] bob) {
    Arrays.sort(alice);
    Arrays.sort(bob);

    // Need complex logic to find pair with specific difference
    // Not straightforward like Two Sum
}
```
**Problem**: Not naturally suited for this
**Why fails**: Finding specific difference with two pointers is awkward

### Approach 4: Without Math Formula ❌ (Inefficient)
```java
// Try to find pair by checking sum equality directly
for (int a : alice) {
    for (int b : bob) {
        if ((sumA - a + b) == (sumB - b + a)) {
            return new int[]{a, b};
        }
    }
}
```
**Problem**: Still O(n × m), doesn't leverage math
**Why less ideal**: Same as brute force but with more computation

### Why Math + HashSet Wins
- ✅ **O(n + m) time** - optimal
- ✅ **O(m) space** - acceptable for HashSet
- ✅ **Clean code** - simple and readable
- ✅ **Uses math** - reduces problem to lookup
- ✅ **No sorting** - preserves original arrays

## 3. How to Handle Changes

### Change 1: Return All Valid Pairs
**Modification**: Multiple answers possible

```java
public List<int[]> findAllSwaps(int[] alice, int[] bob) {
    List<int[]> result = new ArrayList<>();
    int sumA = sum(alice), sumB = sum(bob);
    int diff = (sumB - sumA) / 2;

    Set<Integer> setB = new HashSet<>();
    for (int b : bob) setB.add(b);

    for (int a : alice) {
        if (setB.contains(a + diff)) {
            result.add(new int[]{a, a + diff});
        }
    }

    return result;
}
```

### Change 2: Swap Two Items Each
**Modification**: Exchange two boxes each

```java
public int[][] swapTwoEach(int[] alice, int[] bob) {
    int sumA = sum(alice), sumB = sum(bob);

    // After swapping 2 items each, sums should be equal
    // sumA - a1 - a2 + b1 + b2 = sumB - b1 - b2 + a1 + a2
    // This becomes more complex - need to iterate pairs

    for (int i = 0; i < alice.length; i++) {
        for (int j = i + 1; j < alice.length; j++) {
            for (int k = 0; k < bob.length; k++) {
                for (int l = k + 1; l < bob.length; l++) {
                    if (sumA - alice[i] - alice[j] + bob[k] + bob[l] ==
                        sumB - bob[k] - bob[l] + alice[i] + alice[j]) {
                        return new int[][]{{alice[i], alice[j]}, {bob[k], bob[l]}};
                    }
                }
            }
        }
    }

    return new int[][]{};
}
```

### Change 3: Minimize Difference in Swap
**Modification**: Find swap closest to making them equal

```java
public int[] minDifferenceSwap(int[] alice, int[] bob) {
    int sumA = sum(alice), sumB = sum(bob);
    int targetDiff = (sumB - sumA) / 2;

    Set<Integer> setB = new HashSet<>();
    for (int b : bob) setB.add(b);

    int[] best = null;
    int minDifference = Integer.MAX_VALUE;

    for (int a : alice) {
        for (int b : bob) {
            int newDiff = Math.abs((sumA - a + b) - (sumB - b + a));
            if (newDiff < minDifference) {
                minDifference = newDiff;
                best = new int[]{a, b};
            }
        }
    }

    return best;
}
```

### Change 4: Three People (Alice, Bob, Charlie)
**Modification**: Three-way exchange

```java
public int[] threeWaySwap(int[] alice, int[] bob, int[] charlie) {
    int sumA = sum(alice);
    int sumB = sum(bob);
    int sumC = sum(charlie);

    int target = (sumA + sumB + sumC) / 3;

    // Each person needs to reach target
    // Alice gives a, gets something from Bob/Charlie
    // This becomes complex - need to solve system of equations

    // ... implementation depends on specific exchange rules

    return new int[]{};
}
```

### Change 5: Check If Fair Swap Possible
**Modification**: Just return true/false

```java
public boolean canSwapFairly(int[] alice, int[] bob) {
    int sumA = sum(alice), sumB = sum(bob);

    // Check if difference is even (required for integer division)
    if ((sumB - sumA) % 2 != 0) return false;

    int diff = (sumB - sumA) / 2;
    Set<Integer> setB = new HashSet<>();
    for (int b : bob) setB.add(b);

    for (int a : alice) {
        if (setB.contains(a + diff)) {
            return true;
        }
    }

    return false;
}
```

## Visual Walkthrough

**Input**: `alice = [1,2]`, `bob = [2,3]`

```
Step 1: Calculate sums
  sumA = 1 + 2 = 3
  sumB = 2 + 3 = 5

Step 2: Calculate difference
  diff = (sumB - sumA) / 2 = (5 - 3) / 2 = 1

Step 3: Build Bob's set
  setB = {2, 3}

Step 4: Check each of Alice's candies
  For a = 1:
    Need b = a + diff = 1 + 1 = 2
    Check: setB.contains(2)? Yes! ✓
    Return [1, 2]

Verification:
  Alice before: 1 + 2 = 3
  Bob before: 2 + 3 = 5

  Alice gives 1, gets 2: 3 - 1 + 2 = 4
  Bob gives 2, gets 1: 5 - 2 + 1 = 4

  Equal! ✓
```

**Input**: `alice = [1,1]`, `bob = [2,2]`

```
Step 1: Calculate sums
  sumA = 1 + 1 = 2
  sumB = 2 + 2 = 4

Step 2: Calculate difference
  diff = (4 - 2) / 2 = 1

Step 3: Build Bob's set
  setB = {2}

Step 4: Check each of Alice's candies
  For a = 1:
    Need b = 1 + 1 = 2
    Check: setB.contains(2)? Yes! ✓
    Return [1, 2]

Verification:
  Alice: 2 - 1 + 2 = 3
  Bob: 4 - 2 + 1 = 3 ✓
```

## Key Patterns

**Pattern 1: Mathematical Relationship**
- Derive formula to reduce search space
- `b = a + diff` relationship is key

**Pattern 2: HashSet for Target Lookup**
- Know what we're looking for
- O(1) existence check

**Pattern 3: Sum Difference**
- `(sumB - sumA) / 2` determines required swap difference
- Works because we want equal final sums

**Pattern 4: Guaranteed Solution**
- Problem states answer exists
- Can return first match found

## Complexity Analysis

- **Time**: O(n + m)
  - Calculate sumA: O(n)
  - Calculate sumB: O(m)
  - Build HashSet: O(m)
  - Search for answer: O(n)
  - Total: O(n + m)

- **Space**: O(m)
  - HashSet stores Bob's candies

## Common Mistakes

1. **Wrong formula derivation**: Not simplifying to `b = a + diff`
2. **Forgetting division by 2**: Using `sumB - sumA` instead of half
3. **Building Alice's set instead**: Should use the array we're searching in
4. **Not checking existence**: Assuming answer exists without verification
5. **Integer division issues**: Not checking if difference is even

## Interview Tips

1. **Derive the formula**: Show the math on whiteboard
2. **Explain the insight**: Why `b = a + diff` reduces problem
3. **Mention alternatives**: Brute force, sorting approaches
4. **Discuss optimization**: Why HashSet over binary search
5. **Handle edge cases**: What if no solution? (guaranteed to exist here)

## Helper Function

```java
private int sum(int[] arr) {
    int total = 0;
    for (int num : arr) total += num;
    return total;
    // Or: return Arrays.stream(arr).sum();
}
```