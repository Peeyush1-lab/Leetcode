# Solution Explanation & Problem-Solving Notes

## Solution Breakdown

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |
| Approach | Monotonic Stack |
| Difficulty | Medium |
| Time Taken | ~25 minutes |

---

## Core Intuition

**The Monotonic Stack Pattern:** Use a stack to efficiently find the "next greater element" for each position.

**Key insight:**
- Stack stores indices of days waiting for warmer temperature
- When we find a warmer day, we can resolve all waiting days with lower temperatures
- Stack maintains decreasing order of temperatures

---

## Algorithm Walkthrough

### Step 1: Initialize

```java
int len = temperatures.length;
int ans[] = new int[len];
Stack<Integer> arr = new Stack<>();
```

- `ans[]`: Result array (defaults to 0)
- `arr`: Stack stores indices of unresolved days

### Step 2: Process Each Day

```java
for(int i = 0; i < len; i++) {
    while(!arr.isEmpty() &&
          temperatures[i] > temperatures[arr.peek()]) {
        int idx = arr.pop();
        ans[idx] = i - idx;
    }
    arr.push(i);
}
```

**Logic:**
1. Check if current temperature warmer than stack top
2. If yes, pop and calculate wait days
3. Repeat until no more warmer days to resolve
4. Push current index to stack

---

## Visual Example

### Example: `temperatures = [73,74,75,71,69,72,76,73]`

```
i=0: temp=73
Stack: [0]
ans: [0,0,0,0,0,0,0,0]

───────────────────────────────

i=1: temp=74
74 > 73 (at index 0)
  Pop 0, ans[0] = 1-0 = 1
Stack: [1]
ans: [1,0,0,0,0,0,0,0]

───────────────────────────────

i=2: temp=75
75 > 74 (at index 1)
  Pop 1, ans[1] = 2-1 = 1
Stack: [2]
ans: [1,1,0,0,0,0,0,0]

───────────────────────────────

i=3: temp=71
71 < 75 (at index 2)
  No pop, push 3
Stack: [2,3]
ans: [1,1,0,0,0,0,0,0]

───────────────────────────────

i=4: temp=69
69 < 71 (at index 3)
  No pop, push 4
Stack: [2,3,4]
ans: [1,1,0,0,0,0,0,0]

───────────────────────────────

i=5: temp=72
72 > 69 (at index 4)
  Pop 4, ans[4] = 5-4 = 1
72 > 71 (at index 3)
  Pop 3, ans[3] = 5-3 = 2
72 < 75 (at index 2)
  Stop popping, push 5
Stack: [2,5]
ans: [1,1,0,2,1,0,0,0]

───────────────────────────────

i=6: temp=76
76 > 72 (at index 5)
  Pop 5, ans[5] = 6-5 = 1
76 > 75 (at index 2)
  Pop 2, ans[2] = 6-2 = 4
Stack: [6]
ans: [1,1,4,2,1,1,0,0]

───────────────────────────────

i=7: temp=73
73 < 76 (at index 6)
  No pop, push 7
Stack: [6,7]
ans: [1,1,4,2,1,1,0,0]

───────────────────────────────

Final: [1,1,4,2,1,1,0,0]
Indices 6 and 7 remain in stack → No warmer day → 0
```

---

## Why This Works

### Monotonic Stack Property

**Stack maintains decreasing temperature order:**

```
Example at i=4:
Stack: [2, 3, 4]
temps: [75, 71, 69]  (decreasing!)

When we see 72 at i=5:
- 72 > 69 → Pop 4
- 72 > 71 → Pop 3
- 72 < 75 → Stop
```

**Why this is efficient:**
- Each element pushed once: O(n)
- Each element popped once: O(n)
- Total: O(2n) = O(n)

---

## Stack State Visualization

```
temperatures = [73, 74, 75, 71, 69, 72, 76, 73]

After each iteration:
i=0: Stack=[0]          temps=[73]
i=1: Stack=[1]          temps=[74]
i=2: Stack=[2]          temps=[75]
i=3: Stack=[2,3]        temps=[75,71]
i=4: Stack=[2,3,4]      temps=[75,71,69]
i=5: Stack=[2,5]        temps=[75,72]
i=6: Stack=[6]          temps=[76]
i=7: Stack=[6,7]        temps=[76,73]

Notice: Temperatures in stack are always decreasing!
```

---

## Alternative Approaches

### Approach 1: Monotonic Stack (Your solution)
**Time:** O(n), **Space:** O(n)

Pros:
- Optimal time complexity
- Clean implementation
- Each element processed once

### Approach 2: Brute Force
```java
for(int i = 0; i < n; i++) {
    for(int j = i+1; j < n; j++) {
        if(temperatures[j] > temperatures[i]) {
            ans[i] = j - i;
            break;
        }
    }
}
```
**Time:** O(n²), **Space:** O(1)

Cons:
- Too slow for large inputs

### Approach 3: Array Instead of Stack
```java
int[] stack = new int[n];
int top = -1;
// Similar logic but with array
```
**Time:** O(n), **Space:** O(n)

Same complexity, slightly faster but less clean

**Your stack solution is optimal and clean!**

---

## Complexity Analysis

```
Time Complexity: O(n)
- Single pass through array: O(n)
- Each index pushed to stack once: O(n)
- Each index popped from stack once: O(n)
- Total operations: O(3n) = O(n)

Space Complexity: O(n)
- Result array: O(n)
- Stack: O(n) in worst case
- Worst case: decreasing temperatures (all in stack)
```

**Amortized Analysis:**
- Although nested while loop exists
- Each element pushed exactly once
- Each element popped at most once
- Total operations across all iterations: 2n
- Amortized O(1) per iteration

---

## Edge Cases

| Case | Input | Output | Handled? |
|------|-------|--------|----------|
| Single day | `[30]` | `[0]` | Yes - no warmer day |
| All increasing | `[30,40,50]` | `[1,1,0]` | Yes - immediate next day |
| All decreasing | `[50,40,30]` | `[0,0,0]` | Yes - no warmer days |
| All same | `[30,30,30]` | `[0,0,0]` | Yes - not strictly warmer |
| Peak in middle | `[30,40,30]` | `[1,0,0]` | Yes |

---

## What Went Right

- Recognized monotonic stack pattern
- Correct stack operations
- Proper index tracking
- Efficient O(n) solution
- Clean implementation

---

## Key Takeaways

**Core Concepts:**
1. Monotonic stack for "next greater" problems
2. Store indices, not values
3. Each element processed exactly twice
4. Amortized O(1) per element

**Pattern Recognition:**
- Next greater/smaller element
- Stack-based optimization
- Index difference calculations
- Monotonic property maintenance

**When to Use Monotonic Stack:**
- Finding next greater/smaller element
- Stock span problems
- Histogram area problems
- Temperature-like comparisons

---

## Related Problems

- Next Greater Element I/II
- Online Stock Span
- Largest Rectangle in Histogram
- Trapping Rain Water
- Remove K Digits

---

## Summary

**Problem Solved Successfully**

Time: ~25 minutes
Difficulty: Medium
Approach: Monotonic decreasing stack

**Key Achievement:** Optimal O(n) solution using stack pattern for "next greater element" problem

---

**Last Updated:** January 21, 2026