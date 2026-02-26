# Notes - Remove Duplicate Letters

## Problem Overview

Remove duplicate letters from a string such that:
1. Each letter appears exactly once
2. The result is lexicographically smallest

**Example:** `"bcabc"` → `"abc"` (not `"bca"` or `"cab"`)

## The Challenge

This is NOT just about removing duplicates - we need the **lexicographically smallest** result!

**Naive approach:** Remove all but first occurrence
→ `"bcabc"` → `"bca"` ❌ (not smallest!)

**We need:** `"abc"` ✓ (smallest possible)

## The Greedy Insight

**Key question:** When should we remove a character even though we've already included it?

**Answer:** When:
1. A smaller character comes later, AND
2. The larger character appears again later (we can use it then)

**Example:** `"bcabc"`
```
See 'b' → add it
See 'c' → add it
See 'a' → 'a' < 'c' and 'c' appears later
  → Remove 'c', Remove 'b' (both appear later)
  → Add 'a'
Now we can add 'b' and 'c' later
```

## The Three Data Structures

### 1. Frequency Array (`freq[26]`)

Tracks how many times each character **still appears ahead**.

```java
freq[c - 'a']--;  // Decrease when we process character c
```

**Purpose:** Know if we can use a character later (if `freq > 0`)

### 2. Visited Array (`visited[26]`)

Tracks if a character is **already in our result**.

```java
visited[c - 'a'] = true;   // After adding to stack
visited[c - 'a'] = false;  // After removing from stack
```

**Purpose:** Ensure each character appears only once

### 3. Stack

Builds the result in lexicographically smallest order.

**Purpose:** Allow us to "undo" decisions (pop) when we find a better arrangement

## Step-by-Step Walkthrough

### Example: s = "cbacdcbc"

```
Phase 1: Count frequencies
freq: [2, 2, 4, 1, 0, ..., 0]
       a  b  c  d

Phase 2: Process each character

i=0, char='c':
  freq['c']-- → freq['c'] = 3
  visited['c'] = false → not in result yet
  stack empty → push 'c'
  visited['c'] = true
  stack: ['c']

i=1, char='b':
  freq['b']-- → freq['b'] = 1
  visited['b'] = false
  Check: 'c' > 'b' AND freq['c'] > 0?
    YES! Pop 'c', visited['c'] = false
  Push 'b'
  visited['b'] = true
  stack: ['b']

i=2, char='a':
  freq['a']-- → freq['a'] = 1
  visited['a'] = false
  Check: 'b' > 'a' AND freq['b'] > 0?
    YES! Pop 'b', visited['b'] = false
  Push 'a'
  visited['a'] = true
  stack: ['a']

i=3, char='c':
  freq['c']-- → freq['c'] = 2
  visited['c'] = false
  Check: 'a' > 'c'? NO
  Push 'c'
  visited['c'] = true
  stack: ['a', 'c']

i=4, char='d':
  freq['d']-- → freq['d'] = 0
  visited['d'] = false
  Check: 'c' > 'd'? NO
  Push 'd'
  visited['d'] = true
  stack: ['a', 'c', 'd']

i=5, char='c':
  freq['c']-- → freq['c'] = 1
  visited['c'] = true → SKIP (already in result)
  stack: ['a', 'c', 'd']

i=6, char='b':
  freq['b']-- → freq['b'] = 0
  visited['b'] = false
  Check: 'd' > 'b' AND freq['d'] > 0?
    NO (freq['d'] = 0, can't use later)
  Check: 'c' > 'b' AND freq['c'] > 0?
    YES! Pop 'c', visited['c'] = false
  Check: 'd' > 'b' AND freq['d'] > 0?
    NO (freq['d'] = 0)
  Push 'b'
  visited['b'] = true
  stack: ['a', 'd', 'b']

Wait, this doesn't seem right. Let me recalculate...

Actually, after step 4, we have stack: ['a', 'c', 'd']

i=6, char='b':
  freq['b']-- → freq['b'] = 0
  visited['b'] = false
  While loop:
    'd' > 'b'? YES, freq['d'] > 0? NO (= 0)
    Break (can't remove 'd')
  Push 'b'?

Wait, let me restart this example more carefully...
```

Let me redo this with correct tracking:

```
Initial frequencies (count all):
s = "cbacdcbc"
freq: a=2, b=2, c=4, d=1

Process character by character:

i=0, 'c':
  freq['c']=3 (decrease from 4)
  visited['c']=false → add to stack
  stack: ['c'], visited: c=true

i=1, 'b':
  freq['b']=1 (decrease from 2)
  visited['b']=false
  While: 'c'>'b' AND freq['c']>0? YES → pop 'c'
  stack: [], visited: c=false
  Add 'b'
  stack: ['b'], visited: b=true

i=2, 'a':
  freq['a']=1 (decrease from 2)
  visited['a']=false
  While: 'b'>'a' AND freq['b']>0? YES → pop 'b'
  stack: [], visited: b=false
  Add 'a'
  stack: ['a'], visited: a=true

i=3, 'c':
  freq['c']=2 (decrease from 3)
  visited['c']=false
  While: 'a'>'c'? NO → don't pop
  Add 'c'
  stack: ['a','c'], visited: a=true, c=true

i=4, 'd':
  freq['d']=0 (decrease from 1)
  visited['d']=false
  While: 'c'>'d'? NO → don't pop
  Add 'd'
  stack: ['a','c','d'], visited: a=true, c=true, d=true

i=5, 'c':
  freq['c']=1 (decrease from 2)
  visited['c']=true → SKIP (continue)
  stack: ['a','c','d']

i=6, 'b':
  freq['b']=0 (decrease from 1)
  visited['b']=false
  While: 'd'>'b' AND freq['d']>0? NO (freq['d']=0)
  → Can't remove 'd'
  Add 'b'
  stack: ['a','c','d','b'], visited: all true

i=7, 'c':
  freq['c']=0 (decrease from 1)
  visited['c']=true → SKIP
  stack: ['a','c','d','b']

Result: "acdb" ✅
```

## Code Breakdown

```java
public static String removeDuplicateLetters(String s) {
    // Track remaining occurrences of each character
    int[] freq = new int[26];
    // Track if character is in result
    boolean[] visited = new boolean[26];

    // Count frequency of each character
    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }

    Stack<Character> st = new Stack<>();

    for (char c : s.toCharArray()) {
        // Decrease frequency (we've seen this occurrence)
        freq[c - 'a']--;

        // Skip if already in result
        if (visited[c - 'a'])
            continue;

        // Remove characters that are:
        // 1. Greater than current (want smaller first)
        // 2. Appear later (can use them then)
        while (!st.isEmpty() &&
               st.peek() > c &&
               freq[st.peek() - 'a'] > 0) {
            visited[st.pop() - 'a'] = false;
        }

        // Add current character
        st.push(c);
        visited[c - 'a'] = true;
    }

    // Convert stack to string
    StringBuilder sb = new StringBuilder();
    for (char c : st)
        sb.append(c);
    return sb.toString();
}
```

## The Greedy Strategy Explained

### Decision at Each Character

When we see character `c`:

**Option 1:** Skip it (if already in result)
**Option 2:** Add it (if not in result)

When adding `c`, we check: **Can we improve by removing previous characters?**

### The While Loop Logic

```java
while (!st.isEmpty() && st.peek() > c && freq[st.peek() - 'a'] > 0)
```

This says: **"Remove larger characters that we can add later"**

**Three conditions:**
1. `!st.isEmpty()` - Something to remove
2. `st.peek() > c` - Top is larger (worse for lexicographic order)
3. `freq[st.peek() - 'a'] > 0` - Top appears later (safe to remove now)

## Why This Works - Proof

### Claim: Result is lexicographically smallest

**Proof by contradiction:**

Suppose there's a smaller result `R`.
Let `i` be the first position where our result `S` differs from `R`.
So `S[i] > R[i]`.

This means at position `i`, we chose character `S[i]` when `R[i]` was available.

Why didn't we choose `R[i]`?
- If `R[i]` hadn't appeared yet, we couldn't choose it
- If `R[i]` appeared earlier, we would have removed larger characters for it
- Contradiction!

Therefore, our result is optimal. ✓

## Complexity Analysis

**Time Complexity:** O(n)
- Counting frequencies: O(n)
- Main loop: O(n)
  - Each character pushed once: O(n)
  - Each character popped at most once: O(n)
  - Total operations: O(2n) = O(n)
- Building result: O(n)
- Total: O(n)

**Space Complexity:** O(1)
- `freq` array: O(26) = O(1)
- `visited` array: O(26) = O(1)
- `stack`: O(26) at most = O(1)
- `StringBuilder`: O(26) at most = O(1)
- All fixed size → O(1)

(If considering output, it's O(n), but typically we don't count output space)

## Edge Cases

### 1. All Same Character
```java
Input: s = "aaaa"
Output: "a"
```
✅ First 'a' added, rest skipped (visited=true)

### 2. Already Sorted
```java
Input: s = "abc"
Output: "abc"
```
✅ Never pops, just adds each character

### 3. Reverse Sorted
```java
Input: s = "cba"
Output: "abc"
```
✅ Each character pops all previous ones

### 4. No Duplicates
```java
Input: s = "abcd"
Output: "abcd"
```
✅ Just adds each character

### 5. Complex Pattern
```java
Input: s = "bcabc"
Output: "abc"
```
✅ Removes 'b' and 'c' when 'a' appears (they appear later)

## Common Mistakes

### ❌ Mistake 1: Forgetting to decrease frequency

```java
for (char c : s.toCharArray()) {
    // Missing: freq[c - 'a']--;
    if (visited[c - 'a']) continue;
```

Without decreasing, we don't know when a character won't appear again!

### ❌ Mistake 2: Not checking if character is already in result

```java
for (char c : s.toCharArray()) {
    freq[c - 'a']--;
    // Missing: if (visited[c - 'a']) continue;

    while (!st.isEmpty() && st.peek() > c && freq[st.peek() - 'a'] > 0) {
        visited[st.pop() - 'a'] = false;
    }
```

This would add duplicates!

### ❌ Mistake 3: Wrong while condition

```java
while (!st.isEmpty() && st.peek() > c) {  // Missing freq check!
    visited[st.pop() - 'a'] = false;
}
```

Without `freq > 0` check, we might remove a character that doesn't appear later!

### ❌ Mistake 4: Not marking as visited/unvisited

```java
st.push(c);
// Missing: visited[c - 'a'] = true;
```

OR

```java
visited[st.pop() - 'a'] = false;  // This line missing
```

Visited array must stay in sync with stack!

### ❌ Mistake 5: Using wrong comparison

```java
while (!st.isEmpty() && st.peek() < c && ...)  // WRONG!
```

We want to remove **larger** characters, so use `>`, not `<`.

## The Monotonic Stack Pattern

This problem uses a **monotonic increasing stack** with the twist that we can violate monotonicity if necessary (when freq=0).

**General pattern:**
- Maintain stack in increasing/decreasing order
- Pop elements that violate the order
- **Condition:** Only pop if safe (element appears later)

**Common in:**
- Next greater element problems
- Remove K digits
- Largest rectangle in histogram
- This problem!

## Alternative Approach: Recursion

```java
public String removeDuplicateLetters(String s) {
    // Find smallest character
    int pos = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) < s.charAt(pos)) pos = i;
    }

    // Find first position where smallest char
    // and all other chars appear after it
    int[] count = new int[26];
    for (char c : s.toCharArray()) count[c - 'a']++;

    for (int i = 0; i < s.length(); i++) {
        if (--count[s.charAt(i) - 'a'] == 0) {
            pos = i;
            break;
        }
        if (s.charAt(i) < s.charAt(pos)) pos = i;
    }

    // Recursively process rest
    return s.charAt(pos) +
           removeDuplicateLetters(
               s.substring(pos + 1).replaceAll("" + s.charAt(pos), "")
           );
}
```

**Pros:** Different perspective
**Cons:** String operations expensive, harder to understand

**Your iterative stack approach is better!**

## Interview Tips

1. **Explain the greedy insight:**
   - "Remove larger characters that appear later"
   - "Make room for smaller characters now"

2. **Describe the three data structures:**
   - freq: track remaining occurrences
   - visited: track if in result
   - stack: build result

3. **Walk through an example:**
   - Show "cbacdcbc" → "acdb"
   - Demonstrate popping when conditions met

4. **Explain the while loop:**
   - Three conditions must all be true
   - Each has a specific purpose

5. **Discuss complexity:** "O(n) time, O(1) space"

6. **Mention the pattern:** "Monotonic stack with greedy strategy"

## Related Problems with Hyperlinks

- [1081. Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) (Medium) - Identical problem with different name
- [402. Remove K Digits](https://leetcode.com/problems/remove-k-digits/) (Medium) - Similar monotonic stack strategy
- [321. Create Maximum Number](https://leetcode.com/problems/create-maximum-number/) (Hard) - Extension of monotonic stack
- [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) (Easy) - Monotonic stack basics
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) (Medium) - Monotonic stack application
- [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) (Hard) - Advanced monotonic stack
- [456. 132 Pattern](https://leetcode.com/problems/132-pattern/) (Medium) - Monotonic stack pattern

## Key Takeaways

1. **Greedy + Stack** - Powerful combination for order-dependent problems
2. **Three data structures working together** - freq, visited, stack
3. **Monotonic stack pattern** - Maintain order, pop when conditions met
4. **The while loop is key** - Three conditions ensure correctness
5. **O(n) time despite nested loops** - Each element pushed/popped once
6. **Lexicographically smallest** - Achieved by greedy removal strategy

Excellent use of monotonic stack with greedy strategy!