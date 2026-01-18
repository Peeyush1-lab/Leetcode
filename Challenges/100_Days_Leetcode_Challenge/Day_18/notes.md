# Solution Explanation & Problem-Solving Notes

## Solution Breakdown

| Metric | Value |
|--------|-------|
| Time Complexity | O(n log n) |
| Space Complexity | O(1) |
| Approach | Greedy + Sorting |
| Difficulty | Medium |
| Time Taken | ~25 minutes |

---

## Core Intuition

**The Greedy Insight:** Sort balloons by end position, then greedily shoot arrows at the earliest end point that can burst overlapping balloons.

**Why this works:**
- Shooting at the end of the earliest-ending balloon ensures we burst it
- Also bursts all balloons that overlap with this end point
- Minimizes total arrows needed

---

## Algorithm Walkthrough

### Step 1: Sort by End Position

```java
Arrays.sort(points, (a,b) -> Integer.compare(a[1],b[1]));
```

**Why sort by end position?**
- We want to shoot arrow as late as possible within first balloon
- This maximizes chance of hitting overlapping balloons
- End position determines optimal shooting point

**Important:** Using `Integer.compare(a[1], b[1])` prevents integer overflow
- Safer than `a[1] - b[1]` for large values

**Example:**
```
Before: [[10,16],[2,8],[1,6],[7,12]]
After:  [[1,6],[2,8],[7,12],[10,16]]
         Sorted by end position (6 < 8 < 12 < 16)
```

### Step 2: Initialize

```java
int count = 1;
int lastPoint = points[0][1];
```

- Start with 1 arrow (need at least one)
- Shoot first arrow at end of first balloon

### Step 3: Check Overlap

```java
for(int i = 1; i < points.length; i++) {
    if(points[i][0] > lastPoint) {
        count++;
        lastPoint = points[i][1];
    }
}
```

**Logic:**
- If current balloon starts after last arrow position → No overlap
  - Need new arrow
  - Shoot at end of current balloon
- Otherwise → Balloon overlaps, current arrow bursts it
  - No new arrow needed

---

## Visual Example

### Example: `points = [[10,16],[2,8],[1,6],[7,12]]`

```
Step 1: Sort by end position
───────────────────────────────
Original: [[10,16], [2,8], [1,6], [7,12]]
Sorted:   [[1,6],   [2,8], [7,12], [10,16]]
           end=6    end=8  end=12  end=16

Step 2: Initialize
───────────────────────────────
count = 1
lastPoint = 6 (shoot arrow at x=6)

Visualization:
    1─────6
    2───────8
           7──────12
                10──────16
           ↑
        Arrow at x=6

Step 3: Process remaining balloons
───────────────────────────────

i=1: [2,8]
- Start=2 <= lastPoint=6? Yes (2 <= 6)
- Overlaps! Arrow at x=6 bursts this
- count stays 1

    1─────6
    2───────8
           ↑
     Both burst!

i=2: [7,12]
- Start=7 > lastPoint=6? Yes!
- No overlap, need new arrow
- count = 2
- lastPoint = 12 (shoot at x=12)

           7──────12
                 10──────16
                        ↑
                   Arrow at x=12

i=3: [10,16]
- Start=10 <= lastPoint=12? Yes (10 <= 12)
- Overlaps! Arrow at x=12 bursts this
- count stays 2

           7──────12
                 10──────16
                        ↑
                  Both burst!

Result: 2 arrows needed
```

---

## Why Greedy Works

**Proof of Optimality:**

1. **Earliest end point strategy:**
   - Shooting at earliest end ensures we burst that balloon
   - Maximizes overlap with other balloons

2. **Cannot do better:**
   - If we skip a balloon, need arrow for it later
   - Shooting later reduces overlap opportunities

3. **Locally optimal = Globally optimal:**
   - Each greedy choice (shoot at end) is best for that position
   - Chaining greedy choices gives optimal total

---

## Key Decision: Integer.compare

```java
// Your code (Correct!)
Arrays.sort(points, (a,b) -> Integer.compare(a[1],b[1]));

// Dangerous alternative
Arrays.sort(points, (a,b) -> a[1] - b[1]);
```

**Why `Integer.compare` is better:**

```
Example with overflow:
a[1] = 2147483647 (Integer.MAX_VALUE)
b[1] = -2147483648 (Integer.MIN_VALUE)

Using a[1] - b[1]:
2147483647 - (-2147483648) = Integer overflow!
Result: -1 (wrong!)

Using Integer.compare(a[1], b[1]):
Correctly returns positive value
```

---

## Alternative Approaches

### Approach 1: Greedy by End (Your solution)
```java
Arrays.sort(points, (a,b) -> Integer.compare(a[1],b[1]));
// Then greedy selection
```
**Time:** O(n log n), **Space:** O(1)

### Approach 2: Interval Merging
```java
// Sort by start, merge overlapping intervals
// Count non-overlapping groups
```
**Time:** O(n log n), **Space:** O(n)
More complex, same result

### Approach 3: Dynamic Programming
**Time:** O(n²), **Space:** O(n)
Overkill for this problem

**Your greedy approach is optimal!**

---

## Edge Cases

| Case | Input | Output | Handled? |
|------|-------|--------|----------|
| Single balloon | `[[1,2]]` | `1` | Yes - returns count=1 |
| No overlap | `[[1,2],[3,4]]` | `2` | Yes - separate arrows |
| Full overlap | `[[1,10],[2,9],[3,8]]` | `1` | Yes - one arrow |
| Touching edges | `[[1,2],[2,3]]` | `2` | Yes - start > lastPoint |
| Same intervals | `[[1,2],[1,2]]` | `1` | Yes |

**Note on touching edges:**
```
[1,2] and [2,3]
Arrow at x=2 bursts [1,2]
But [2,3] starts at 2, which is NOT > 2
So they overlap! One arrow bursts both.
```

Wait, let me reconsider:
```
if(points[i][0] > lastPoint)
```
If start > lastPoint, they DON'T overlap.

For [1,2] and [2,3]:
- Arrow at 2 (end of first)
- Second starts at 2, which is NOT > 2
- They overlap! One arrow works.

Actually your code handles this correctly!

---

## Complexity Analysis

```
Time Complexity: O(n log n)
- Sorting: O(n log n)
- Single pass: O(n)
- Total: O(n log n) dominated by sorting

Space Complexity: O(1) or O(log n)
- O(1): No extra space used (variables only)
- O(log n): Sorting algorithm's stack space
- Depends on sort implementation
```

---

## What Went Right

- Correct greedy strategy
- Safe integer comparison
- Clean loop logic
- Handles all edge cases
- Optimal solution

---

## Key Takeaways

**Core Concepts:**
1. Greedy works when local optimum = global optimum
2. Sorting by end position enables greedy choice
3. Interval overlap detection
4. `Integer.compare` prevents overflow

**Pattern Recognition:**
- Interval scheduling problem
- Greedy algorithm
- Activity selection pattern
- Sort + scan technique

---

## Related Problems

- Non-overlapping Intervals
- Merge Intervals
- Insert Interval
- Meeting Rooms II
- Activity Selection

---

## Summary

**Problem Solved Successfully**

Time: ~25 minutes
Difficulty: Medium
Approach: Greedy with sorting by end position

**Key Achievement:** Optimal greedy solution with overflow-safe comparison

---

**Last Updated:** January 18, 2026