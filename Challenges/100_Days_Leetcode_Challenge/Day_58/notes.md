# Notes - Build an Array With Stack Operations

## Problem Overview

Given a target array and stream `[1, 2, 3, ..., n]`, return the sequence of stack operations ("Push", "Pop") needed to build the target array.

**Key constraint:** Target is strictly increasing and contains values from 1 to n.

## The Challenge

We have a sequential stream but need specific numbers. How do we skip unwanted numbers?

**Answer:** Push and immediately Pop unwanted numbers!

## The Clever Optimization

### Naive Thought

Process all numbers from 1 to n:
```java
for(int i = 1; i <= n; i++)  // Process all n numbers
```

### Your Optimization

Only process up to the last target number:
```java
for(int i = 1; i <= target[target.length-1]; i++)  // Stop early!
```

**Why?** We don't need any numbers beyond the last target number!

**Example:** target = [1, 3], n = 100
- Naive: Process 1 to 100 (99 iterations)
- Optimized: Process 1 to 3 (3 iterations) ✓

## Step-by-Step Walkthrough

### Example 1: target = [1,3], n = 3

```
Initial state:
  target = [1, 3]
  n (index) = 0
  list = []
  last target number = 3

Iteration i=1:
  i == target[0] (1 == 1)? YES
  n = 1 (increment)
  list = ["Push"]

Iteration i=2:
  i == target[1] (2 == 3)? NO
  list = ["Push", "Pop", "Push", "Pop"]

Wait, that's not right. Let me reconsider...

Actually with n=0 initially and incrementing:

Iteration i=1:
  i == target[n] where n=0? (1 == 1)? YES
  n++ → n=1
  Add "Push"
  list = ["Push"]

Iteration i=2:
  i == target[n] where n=1? (2 == 3)? NO
  Add "Push", "Pop"
  list = ["Push", "Push", "Pop"]

Iteration i=3:
  i == target[n] where n=1? (3 == 3)? YES
  n++ → n=2
  Add "Push"
  list = ["Push", "Push", "Pop", "Push"]

Loop ends (i <= target[target.length-1] = 3)

Return: ["Push", "Push", "Pop", "Push"] ✅
```

### Example 2: target = [1,2,3], n = 3

```
Initial:
  target = [1, 2, 3]
  n = 0
  last = 3

i=1:
  1 == target[0] (1)? YES
  n=1, add "Push"
  list = ["Push"]

i=2:
  2 == target[1] (2)? YES
  n=2, add "Push"
  list = ["Push", "Push"]

i=3:
  3 == target[2] (3)? YES
  n=3, add "Push"
  list = ["Push", "Push", "Push"]

Return: ["Push", "Push", "Push"] ✅
```

### Example 3: target = [2,3,4], n = 5

```
Initial:
  target = [2, 3, 4]
  n = 0
  last = 4

i=1:
  1 == target[0] (2)? NO
  Add "Push", "Pop"
  list = ["Push", "Pop"]

i=2:
  2 == target[0] (2)? YES
  n=1, add "Push"
  list = ["Push", "Pop", "Push"]

i=3:
  3 == target[1] (3)? YES
  n=2, add "Push"
  list = ["Push", "Pop", "Push", "Push"]

i=4:
  4 == target[2] (4)? YES
  n=3, add "Push"
  list = ["Push", "Pop", "Push", "Push", "Push"]

Loop ends (i <= 4)

Return: ["Push", "Pop", "Push", "Push", "Push"] ✅
```

## Code Breakdown

```java
public List<String> buildArray(int[] target, int n) {
    List<String> list = new ArrayList<>();
    n = 0;  // Reuse n as index pointer for target array

    // Only iterate up to last target number (optimization!)
    for(int i = 1; i <= target[target.length-1]; i++) {

        // Check if current stream number matches target
        if(i == target[n]) {
            // Match! Keep this number
            n++;  // Move to next target
            list.add("Push");
        } else {
            // No match! Discard this number
            list.add("Push");
            list.add("Pop");
        }
    }

    return list;
}
```

## Why This Works

### The Sequential Stream Property

The stream is **sequential** (1, 2, 3, 4, ...):
- We process numbers in order
- We can't skip ahead in the stream
- To ignore a number, we must Push and Pop it

### The Target is Sorted Property

Target is **strictly increasing**:
- Once we match target[i], next match is target[i+1]
- We never need to go back
- Simple index tracking works!

### The Early Termination Optimization

We **stop at the last target number**:
- Numbers beyond aren't needed
- Saves unnecessary operations
- Example: target=[1,3], n=100 → only process up to 3!

## Variable Naming Note

**Important:** The parameter `n` is reused as the index pointer!

```java
public List<String> buildArray(int[] target, int n) {
    n = 0;  // Now n is the index, not the stream limit
```

This is a bit confusing because:
- `n` parameter originally represents stream limit
- But we reuse it as array index
- Works fine, but could be clearer with different name

**Better naming:**
```java
public List<String> buildArray(int[] target, int n) {
    List<String> list = new ArrayList<>();
    int targetIndex = 0;  // Clearer!

    for(int i = 1; i <= target[target.length-1]; i++) {
        if(i == target[targetIndex]) {
            targetIndex++;
            list.add("Push");
        } else {
            list.add("Push");
            list.add("Pop");
        }
    }
    return list;
}
```

## Edge Cases

### 1. Target Contains Only 1
```java
Input: target = [1], n = 1
Output: ["Push"]
```
✅ Loop runs once (i=1), matches, adds "Push"

### 2. Target Contains Consecutive Numbers
```java
Input: target = [1,2,3,4,5], n = 5
Output: ["Push","Push","Push","Push","Push"]
```
✅ All match, no Pop needed

### 3. Target with Gaps
```java
Input: target = [1,3,5], n = 5
Output: ["Push","Push","Pop","Push","Push","Pop","Push"]
```
✅ Skip 2 and 4 with Push-Pop

### 4. Target Starts from 1
```java
Input: target = [1], n = 10
Output: ["Push"]
```
✅ Stops after first match (optimization!)

### 5. Target Doesn't Start from 1
```java
Input: target = [5,6,7], n = 7
Output: ["Push","Pop","Push","Pop","Push","Pop","Push","Pop","Push","Push","Push"]
```
✅ Skip 1,2,3,4 then push 5,6,7

## Common Mistakes

### ❌ Mistake 1: Processing all n numbers

```java
for(int i = 1; i <= n; i++)  // WRONG! Processes too many
```

Should stop at `target[target.length-1]` for efficiency.

### ❌ Mistake 2: Not incrementing index

```java
if(i == target[n]) {
    // Missing: n++;
    list.add("Push");
}
```

Without incrementing, we'd keep comparing to the same target element!

### ❌ Mistake 3: Forgetting both Push and Pop

```java
else {
    list.add("Pop");  // WRONG! Need Push first
}
```

You must Push before you can Pop!

### ❌ Mistake 4: Using wrong loop condition

```java
for(int i = 0; i < target.length; i++)  // WRONG! Not stream values
```

Loop should be over stream values (1 to max), not array indices.

### ❌ Mistake 5: Not handling the else case

```java
if(i == target[n]) {
    n++;
    list.add("Push");
}
// Missing else case - numbers get stuck in stack!
```

Must handle non-matching numbers with Push-Pop.

## Complexity Analysis

**Time Complexity:** O(m)
- Where m = target[target.length-1] (last target number)
- Loop runs from 1 to m
- Each iteration: O(1) operations
- Total: O(m)

**Best case:** target = [1] → O(1)
**Worst case:** target = [1, 2, ..., k] → O(k)

**Space Complexity:** O(m)
- Result list can have up to 2m operations
- Example: target = [100] with many skips
- Operations: Push-Pop for 1-99, Push for 100
- Total: 2×99 + 1 = 199 operations
- O(m) space

## Why Medium Difficulty?

This problem seems easy, so why medium?

**Difficulty comes from:**
1. Understanding the simulation (stream concept)
2. Realizing we can stop early (optimization)
3. Managing the index correctly
4. Understanding Push-Pop discards numbers

It's more about problem comprehension than algorithmic complexity.

## Simulation vs Real Stack

**Note:** We don't actually use a stack data structure!

We just **generate the operations** that would build the target if executed on a stack.

**Why not use actual stack?**
- Don't need to track stack state
- Just need operation sequence
- More efficient to generate directly

## Interview Tips

1. **Clarify the problem:**
   - "We're generating operations, not executing them"
   - "Stream is sequential [1,2,3,...]"

2. **Mention the optimization:**
   - "We only need to go up to the last target number"
   - "Don't need to process all n numbers"

3. **Explain the strategy:**
   - "For target numbers: Push"
   - "For non-target numbers: Push and Pop"

4. **Walk through example:**
   - Show target = [1,3] with n=3
   - Demonstrate how each number is handled

5. **Discuss complexity:** "O(m) time and space, where m is max target value"

6. **Mention edge cases:**
   - Consecutive numbers (no Pops)
   - Large gaps (many Push-Pops)

## Related Problems with Hyperlinks

- [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences/) (Medium) - Check if operations produce a sequence
- [1673. Find the Most Competitive Subsequence](https://leetcode.com/problems/find-the-most-competitive-subsequence/) (Medium) - Stack-based sequence building
- [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) (Easy) - Stack operations and structure
- [155. Min Stack](https://leetcode.com/problems/min-stack/) (Medium) - Stack with additional operations
- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) (Easy) - Classic stack problem
- [1209. Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/) (Medium) - Stack simulation

## Key Takeaways

1. **Simulation problems** - Generate operations without executing them
2. **Early termination optimization** - Stop at last needed value
3. **Sequential stream handling** - Process numbers in order
4. **Push-Pop pattern** - Discard unwanted values
5. **Index tracking** - Simple pointer for sorted target
6. **Understanding vs implementation** - Problem is more about comprehension than complex algorithms

Excellent simulation with smart optimization! 