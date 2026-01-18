# Solution Explanation & Problem-Solving Notes

## Solution Breakdown

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |
| Approach | XOR Bit Manipulation |
| Difficulty | Easy |
| Time Taken | ~10 minutes |

---

## Core Intuition

**The Brilliant XOR Trick:** XOR has special properties that make this problem trivial!

**XOR Properties:**
```
a ^ a = 0        (same numbers cancel out)
a ^ 0 = a        (XOR with 0 returns the number)
a ^ b ^ a = b    (order doesn't matter - commutative)
```

**Example:**
```
[4, 1, 2, 1, 2]
= 4 ^ 1 ^ 2 ^ 1 ^ 2
= 4 ^ (1 ^ 1) ^ (2 ^ 2)
= 4 ^ 0 ^ 0
= 4
```

---

## Algorithm Walkthrough

### The Code
```java
int res = 0;
for(int i : nums) {
    res ^= i;
}
return res;
```

**Step-by-step:**
1. Start with `res = 0`
2. XOR each number with result
3. Duplicates cancel to 0
4. Single number remains

---

## Visual Example

### Example: `nums = [2, 2, 1]`

```
Iteration 1: res = 0 ^ 2 = 2
Binary: 000 ^ 010 = 010 (2)

Iteration 2: res = 2 ^ 2 = 0
Binary: 010 ^ 010 = 000 (0)

Iteration 3: res = 0 ^ 1 = 1
Binary: 000 ^ 001 = 001 (1)

Result: 1
```

### Example: `nums = [4, 1, 2, 1, 2]`

```
Step 1: 0 ^ 4 = 4
Step 2: 4 ^ 1 = 5
Step 3: 5 ^ 2 = 7
Step 4: 7 ^ 1 = 6    (1 cancels with earlier 1)
Step 5: 6 ^ 2 = 4    (2 cancels with earlier 2)

Result: 4
```

---

## XOR Truth Table

```
a | b | a^b
--|---|----
0 | 0 | 0
0 | 1 | 1
1 | 0 | 1
1 | 1 | 0
```

**Key observation:** Same bits produce 0, different bits produce 1

---

## Why This Works

**Mathematical Proof:**

```
nums = [a, b, c, b, c]

XOR all:
a ^ b ^ c ^ b ^ c

Rearrange (commutative property):
a ^ (b ^ b) ^ (c ^ c)

Apply a ^ a = 0:
a ^ 0 ^ 0

Apply a ^ 0 = a:
a
```

**Duplicates eliminate each other, single remains!**

---

## Alternative Approaches

### Approach 1: XOR (Your solution)
**Time:** O(n), **Space:** O(1)

Pros:
- Optimal time and space
- Elegant one-liner logic
- Uses bit manipulation cleverly

Cons:
- Requires understanding XOR properties

### Approach 2: HashSet
```java
Set<Integer> seen = new HashSet<>();
for(int num : nums) {
    if(!seen.add(num)) {
        seen.remove(num);
    }
}
return seen.iterator().next();
```
**Time:** O(n), **Space:** O(n)

Pros:
- More intuitive

Cons:
- Uses extra space
- Violates space constraint

### Approach 3: Sorting
```java
Arrays.sort(nums);
for(int i = 0; i < nums.length-1; i+=2) {
    if(nums[i] != nums[i+1])
        return nums[i];
}
return nums[nums.length-1];
```
**Time:** O(n log n), **Space:** O(1)

Cons:
- Slower than required

**Your XOR solution is optimal!**

---

## Complexity Analysis

```
Time Complexity: O(n)
- Single pass through array
- XOR operation is O(1)
- Total: O(n)

Space Complexity: O(1)
- Only one variable (res)
- No extra data structures
- Constant space regardless of input size
```

---

## Edge Cases

| Case | Input | Output | Handled? |
|------|-------|--------|----------|
| Single element | `[1]` | `1` | Yes - 0^1=1 |
| Two elements | `[1,1,2]` | `2` | Yes |
| All same except one | `[5,5,5,5,3]` | `3` | Yes |
| Negative numbers | `[-1,-1,2]` | `2` | Yes - XOR works on all integers |
| Large numbers | `[30000,-30000,30000]` | `-30000` | Yes |

---

## What Went Right

- Recognized XOR pattern immediately
- Minimal, elegant code
- Optimal time and space
- Handles all edge cases
- Clean implementation

---

## Key Takeaways

**Core Concepts:**
1. XOR properties for cancellation
2. Bit manipulation for optimization
3. Mathematical properties in coding
4. O(1) space solutions possible

**Pattern Recognition:**
- Finding unique element among duplicates
- Bit manipulation tricks
- Constant space requirements
- XOR for pairing problems

---

## Related Problems

- Single Number II (each appears 3 times)
- Single Number III (two unique numbers)
- Missing Number (XOR variant)
- Find the Duplicate Number

---

## Summary

**Problem Solved Successfully**

Time: ~10 minutes
Difficulty: Easy
Approach: XOR bit manipulation

**Key Achievement:** Optimal O(n) time, O(1) space solution using elegant bit manipulation

---

**Last Updated:** January 19, 2026