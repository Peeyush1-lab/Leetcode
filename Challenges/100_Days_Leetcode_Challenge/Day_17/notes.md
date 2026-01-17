# Solution Explanation & Problem-Solving Notes

## Solution Breakdown

| Metric | Value |
|--------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |
| Approach | HashMap Frequency Counting |
| Difficulty | Medium |
| Time Taken | ~20 minutes |

---

## Core Intuition

**The Key Insight:** Elements appearing more than n/3 times means at most **2 elements** can satisfy this condition.

**Why?** If 3 elements each appeared > n/3 times:
```
3 × (n/3) = n
But we only have n elements total!
Contradiction - at most 2 elements possible
```

---

## Algorithm Walkthrough

### Step 1: Count Frequencies

```java
Map<Integer, Integer> elementCountMap = new HashMap<>();

for (int i = 0; i < nums.length; i++) {
    elementCountMap.put(nums[i],
        elementCountMap.getOrDefault(nums[i], 0) + 1);
}
```

**What this does:**
- Create HashMap to store element frequencies
- `getOrDefault(nums[i], 0)` returns current count or 0 if not present
- Increment count for each element

**Example:**
```
nums = [3,2,3]
After loop:
elementCountMap = {3: 2, 2: 1}
```

### Step 2: Find Majority Elements

```java
List<Integer> majorityElements = new ArrayList<>();

for (Map.Entry<Integer, Integer> entry : elementCountMap.entrySet()) {
    int element = entry.getKey();
    int count = entry.getValue();

    if (count > nums.length/3) {
        majorityElements.add(element);
    }
}
```

**What this does:**
- Iterate through all entries in HashMap
- Extract element and its count
- Check if count exceeds n/3 threshold
- Add qualifying elements to result list

**Example:**
```
nums.length = 3
threshold = 3/3 = 1

Check 3: count=2 > 1 → Add to result
Check 2: count=1 > 1 → Don't add

Result: [3]
```

### Step 3: Return Result

```java
return majorityElements;
```

---

## Visual Example

### Example: `nums = [1,1,1,3,3,2,2,2]`

```
Step 1: Count Frequencies
─────────────────────────
nums = [1, 1, 1, 3, 3, 2, 2, 2]

After counting:
elementCountMap = {
    1: 3,
    3: 2,
    2: 3
}

Step 2: Check Threshold
─────────────────────────
n = 8
threshold = 8/3 = 2 (integer division)
Need count > 2

Check element 1: count=3 > 2 → Add
Check element 3: count=2 > 2 → Don't add
Check element 2: count=3 > 2 → Add

Result: [1, 2]
```

---

## Code Improvements

### Current Code Quality

**Good practices:**
- Clear variable names (`elementCountMap`, `majorityElements`)
- Using `getOrDefault` for clean increment
- Proper HashMap iteration with `entrySet()`

**Can be simplified:**

```java
// Current
for (int i = 0; i < nums.length; i++) {
    elementCountMap.put(nums[i],
        elementCountMap.getOrDefault(nums[i], 0) + 1);
}

// Cleaner with enhanced for loop
for (int num : nums) {
    elementCountMap.put(num,
        elementCountMap.getOrDefault(num, 0) + 1);
}
```

---

## Alternative Approach: Boyer-Moore Voting

**O(1) space solution:**

```java
public List<Integer> majorityElement(int[] nums) {
    int count1 = 0, count2 = 0;
    Integer candidate1 = null, candidate2 = null;

    // Phase 1: Find potential candidates
    for (int num : nums) {
        if (candidate1 != null && num == candidate1) {
            count1++;
        } else if (candidate2 != null && num == candidate2) {
            count2++;
        } else if (count1 == 0) {
            candidate1 = num;
            count1 = 1;
        } else if (count2 == 0) {
            candidate2 = num;
            count2 = 1;
        } else {
            count1--;
            count2--;
        }
    }

    // Phase 2: Verify candidates
    count1 = 0;
    count2 = 0;
    for (int num : nums) {
        if (candidate1 != null && num == candidate1) count1++;
        else if (candidate2 != null && num == candidate2) count2++;
    }

    List<Integer> result = new ArrayList<>();
    if (count1 > nums.length / 3) result.add(candidate1);
    if (count2 > nums.length / 3) result.add(candidate2);

    return result;
}
```

**Comparison:**

| Approach | Time | Space | Complexity |
|----------|------|-------|------------|
| HashMap (Your solution) | O(n) | O(n) | Simple |
| Boyer-Moore | O(n) | O(1) | Complex |

---

## Mathematical Proof

**Why at most 2 elements?**

```
Let's say k elements appear > n/3 times
Each appears at least (n/3 + 1) times

Total occurrences ≥ k × (n/3 + 1)

This must be ≤ n (can't exceed array size)

k × (n/3 + 1) ≤ n
k × n/3 + k ≤ n
k ≤ n - k × n/3
k ≤ n(1 - k/3)
k ≤ 3n(1/3 - k/9)

For k=3: 3 ≤ 3n(1/3 - 1/3) = 0 → Contradiction!
For k=2: 2 ≤ 3n(1/3 - 2/9) = n/3 → Possible

Therefore: At most 2 elements
```

---

## Complexity Analysis

```
Time Complexity: O(n)
- First loop: O(n) to count frequencies
- Second loop: O(k) where k = unique elements
- k ≤ n, so total O(n)

Space Complexity: O(n)
- HashMap stores up to n unique elements
- Result list stores at most 2 elements
- Dominant: O(n) for HashMap

Optimized (Boyer-Moore): O(1) space
- Only stores 2 candidates and counters
- Two passes through array: still O(n) time
```

---

## Edge Cases

| Case | Input | Output | Handled? |
|------|-------|--------|----------|
| Single element | `[1]` | `[1]` | Yes |
| All same | `[1,1,1]` | `[1]` | Yes |
| Two elements | `[1,2]` | `[1,2]` | Yes |
| No majority | `[1,2,3]` | `[]` | Yes |
| Exactly n/3 | Element appears exactly n/3 times | Not included | Correct (needs >) |

---

## What Went Right

- Clean HashMap implementation
- Correct threshold check (>)
- Good variable naming
- Handles all edge cases
- O(n) time achieved

---

## Key Takeaways

**Core Concepts:**
1. Frequency counting with HashMap
2. Mathematical limit: at most 2 elements
3. `getOrDefault` for clean increment
4. Integer division for threshold

**Pattern Recognition:**
- Majority element problems
- Frequency analysis
- HashMap for counting
- Threshold-based filtering

---

## Related Problems

- Majority Element (> n/2)
- Top K Frequent Elements
- Find All Duplicates in Array
- Single Number variants

---

## Summary

**Problem Solved Successfully**

Time: ~20 minutes
Difficulty: Medium
Approach: HashMap frequency counting

**Key Achievement:** Clean O(n) solution with proper threshold checking

---

**Last Updated:** January 17, 2026