# Solution Explanation & Problem-Solving Notes

## Solution Breakdown

| Metric | Value |
|--------|-------|
| Time Complexity | O(√c) |
| Space Complexity | O(1) |
| Approach | Two Pointers |
| Difficulty | Medium |
| Time Taken | ~20 minutes |

---

## Core Intuition

**The Two Pointer Strategy:** Similar to two-sum on sorted array, but with squares!

**Key insights:**
- Maximum value for b is √c (since b² ≤ c)
- Start from extremes and move inward
- Adjust pointers based on sum comparison

---

## Algorithm Walkthrough

### Step 1: Initialize Pointers

```java
long a = 0;
long b = (long) Math.sqrt(c);
```

**Why these values?**
- `a = 0`: Smallest possible value
- `b = √c`: Largest possible value (since b² ≤ c)

**Why long?**
- `c` can be up to 2³¹ - 1
- `a * a` or `b * b` might overflow int
- Using long prevents overflow

### Step 2: Two Pointer Search

```java
while (a <= b) {
    long sum = a * a + b * b;
    if (sum == c) {
        return true;
    } else if (sum < c) {
        a++;
    } else {
        b--;
    }
}
```

**Logic:**
- Calculate sum of squares
- If exact match, found!
- If sum too small, increase a (need larger value)
- If sum too large, decrease b (need smaller value)

---

## Visual Example

### Example 1: `c = 5`

```
Initial: a=0, b=√5=2

Iteration 1:
a=0, b=2
sum = 0² + 2² = 0 + 4 = 4
4 < 5 → Increase a

Iteration 2:
a=1, b=2
sum = 1² + 2² = 1 + 4 = 5
5 == 5 → Found! Return true

Result: true (1² + 2² = 5)
```

### Example 2: `c = 3`

```
Initial: a=0, b=√3=1

Iteration 1:
a=0, b=1
sum = 0² + 1² = 0 + 1 = 1
1 < 3 → Increase a

Iteration 2:
a=1, b=1
sum = 1² + 1² = 1 + 1 = 2
2 < 3 → Increase a

Iteration 3:
a=2, b=1
a > b → Exit loop

Result: false (no valid combination)
```

### Example 3: `c = 13`

```
Initial: a=0, b=√13=3

Iteration 1: a=0, b=3 → 0+9=9 < 13 → a++
Iteration 2: a=1, b=3 → 1+9=10 < 13 → a++
Iteration 3: a=2, b=3 → 4+9=13 == 13 → Found!

Result: true (2² + 3² = 13)
```

---

## Why Use Long?

### Overflow Example

```java
// With int (dangerous!)
int c = 2147483647; // Max int
int b = (int) Math.sqrt(c); // 46340
int sum = b * b; // OVERFLOW! Negative value!

// With long (safe!)
long b = (long) Math.sqrt(c);
long sum = b * b; // Correct value
```

**Your code correctly uses long to prevent this!**

---

## Alternative Approaches

### Approach 1: Two Pointers (Your solution)
**Time:** O(√c), **Space:** O(1)

Pros:
- Optimal time and space
- Clean logic
- Handles overflow correctly

### Approach 2: Nested Loops
```java
for(long a = 0; a * a <= c; a++) {
    for(long b = 0; b * b <= c; b++) {
        if(a*a + b*b == c) return true;
    }
}
```
**Time:** O(c), **Space:** O(1)

Cons:
- Much slower

### Approach 3: HashSet
```java
Set<Long> squares = new HashSet<>();
for(long i = 0; i * i <= c; i++) {
    squares.add(i * i);
}
for(long i = 0; i * i <= c; i++) {
    if(squares.contains(c - i*i)) return true;
}
```
**Time:** O(√c), **Space:** O(√c)

Cons:
- Uses extra space

**Your two-pointer approach is optimal!**

---

## Complexity Analysis

```
Time Complexity: O(√c)
- Initial b = √c
- Loop runs at most √c iterations
- Each iteration does O(1) work
- Total: O(√c)

Space Complexity: O(1)
- Only 3 variables (a, b, sum)
- No extra data structures
- Constant space
```

---

## Edge Cases

| Case | Input | Output | Handled? |
|------|-------|--------|----------|
| Perfect square | `c = 4` | `true` | Yes - 0²+2²=4 |
| Prime number | `c = 3` | `false` | Yes |
| Zero | `c = 0` | `true` | Yes - 0²+0²=0 |
| One | `c = 1` | `true` | Yes - 0²+1²=1 or 1²+0²=1 |
| Large number | `c = 2147483647` | Varies | Yes - long prevents overflow |
| Sum of consecutive | `c = 5` | `true` | Yes - 1²+2²=5 |

---

## Mathematical Background

**Fermat's Theorem on Sums of Two Squares:**

A positive integer c can be represented as sum of two squares if and only if in its prime factorization, every prime of the form 4k+3 appears to an even power.

**Examples:**
- 5 = 1² + 2² ✓ (5 is prime of form 4k+1)
- 3 cannot be expressed ✗ (3 is prime of form 4k+3)
- 13 = 2² + 3² ✓ (13 is prime of form 4k+1)

Your algorithm finds the actual values without needing this theorem!

---

## What Went Right

- Correct two-pointer approach
- Proper overflow handling with long
- Clean loop logic
- Optimal time complexity
- Handles all edge cases

---

## Key Takeaways

**Core Concepts:**
1. Two pointers on mathematical range
2. Overflow prevention with larger types
3. Efficient search in sorted space
4. Square root as boundary

**Pattern Recognition:**
- Two pointer technique
- Mathematical search problems
- Type overflow considerations
- Boundary value calculations

---

## Related Problems

- 3Sum
- Valid Perfect Square
- Perfect Squares
- Happy Number

---

## Summary

**Problem Solved Successfully**

Time: ~20 minutes
Difficulty: Medium
Approach: Two pointers with overflow handling

**Key Achievement:** Optimal O(√c) solution with proper long usage to prevent overflow

---

**Last Updated:** January 19, 2026