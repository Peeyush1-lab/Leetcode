# 📝 Solution Explanation & Problem-Solving Notes

## 🎯 Solution Breakdown

### 📊 Algorithm Overview

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) |
| **Space Complexity** | O(n) |
| **Approach** | Iterative prefix sum |
| **Pattern** | Cumulative sum / Running total |

### 🔄 Step-by-Step Logic

| Step | Action | Description |
|------|--------|-------------|
| 1 | Store Length | Cache array length `n` |
| 2 | Create Result | New array `arr` of size `n` |
| 3 | Initialize First | Set `arr[0] = nums[0]` (base case) |
| 4 | Iterate | Loop from index 1 to n-1 |
| 5 | Accumulate | `arr[i] = arr[i-1] + nums[i]` |
| 6 | Return | Return the result array |

### 💻 Code Walkthrough

| Code Section | Purpose | Key Point |
|--------------|---------|-----------|
| `int n = nums.length;` | Cache length | Avoid repeated calls |
| `int arr[] = new int[n];` | Result array | Same size as input |
| `arr[0] = nums[0];` | Base case | First element unchanged |
| `for(int i = 1; i < n; i++)` | Iterate | Start from index 1 |
| `arr[i] = arr[i-1] + nums[i];` | Accumulation | Use previous sum + current |
| `return arr;` | Result | Return running sum array |

---

## ⚠️ Problems Encountered & Solutions

### ❌ Problem 1: Understanding Running Sum Concept

| Aspect | Details |
|--------|---------|
| **Issue** | Initially confused about what "running sum" means |
| **Confusion** | Is it sum of all elements or cumulative? |
| **Example** | `[1,2,3]` → Is it `[6,6,6]` or `[1,3,6]`? |
| **Clarification** | Running sum = prefix sum = cumulative sum |
| **Understanding** | Each position contains sum up to that point |
| **Solution** | ✓ Read examples carefully - pattern becomes clear |
| **Result** | ✅ Clear concept: `arr[i] = sum(nums[0...i])` |

---

### ❌ Problem 2: Starting Index for Loop

| Aspect | Details |
|--------|---------|
| **Initial Attempt** | Started loop from `i = 0` |
| **Problem** | `arr[i-1]` causes IndexOutOfBoundsException at i=0 |
| **Error** | Cannot access `arr[-1]` |
| **Debug Process** | Realized first element doesn't need calculation |
| **Solution** | Set `arr[0] = nums[0]` separately |
| **Fixed Loop** | Start from `i = 1` instead of `i = 0` |
| **Result** | ✅ No index errors, clean logic |

**Wrong Code:**
```java
for(int i = 0; i < n; i++) {
    arr[i] = arr[i-1] + nums[i];  // ❌ Fails at i=0
}
```

**Correct Code:**
```java
arr[0] = nums[0];  // Handle base case
for(int i = 1; i < n; i++) {
    arr[i] = arr[i-1] + nums[i];  // ✅ Works!
}
```

---

### ❌ Problem 3: Considering In-Place Modification

| Aspect | Details |
|--------|---------|
| **Question** | Can we modify `nums` array directly? |
| **Benefit** | Would save O(n) space |
| **Approach** | `nums[i] = nums[i-1] + nums[i]` |
| **Problem** | Loses original values |
| **Consideration** | Is preserving input important? |
| **Best Practice** | Generally avoid modifying input unless specified |
| **Decision** | ✗ Use separate array for clarity |
| **Trade-off** | O(n) space for better code practices |

**In-place version (space optimized):**
```java
for(int i = 1; i < n; i++) {
    nums[i] = nums[i-1] + nums[i];
}
return nums;  // Space: O(1) but modifies input
```

---

### ❌ Problem 4: Handling Negative Numbers

| Aspect | Details |
|--------|---------|
| **Constraint** | `-10^6 <= nums[i] <= 10^6` |
| **Question** | Does negative affect running sum logic? |
| **Example** | `[5, -3, 2]` → `[5, 2, 4]` |
| **Analysis** | Running sum naturally handles negatives |
| **Test Case** | `[10, -5, 3, -2]` → `[10, 5, 8, 6]` |
| **Insight** | Addition works regardless of sign |
| **Result** | ✅ No special handling needed |

---

### ❌ Problem 5: Integer Overflow Consideration

| Aspect | Details |
|--------|---------|
| **Constraint** | Array length up to 1000, values up to 10^6 |
| **Worst Case** | 1000 elements × 10^6 = 10^9 |
| **Java int Max** | 2,147,483,647 (≈ 2.1 × 10^9) |
| **Concern** | Could running sum overflow? |
| **Analysis** | 1000 × 10^6 = 10^9 < 2.1 × 10^9 ✓ |
| **Negative Case** | 1000 × (-10^6) = -10^9 > -2.1 × 10^9 ✓ |
| **Conclusion** | int is sufficient, no overflow risk |
| **Result** | ✅ No need for long type |

---

## 🔀 Alternative Approaches Considered

| Approach | Time | Space | Pros | Cons | Decision |
|----------|------|-------|------|------|----------|
| **Current: Separate Array** | O(n) | O(n) | Clean, preserves input | Extra space | ✅ **Chosen** |
| **In-place Modification** | O(n) | O(1) | Space efficient | Modifies input | ❌ Not preferred |
| **Nested Loops** | O(n²) | O(n) | Simple to understand | Inefficient | ❌ Too slow |
| **Recursion** | O(n) | O(n) | Elegant | Stack overhead | ❌ Iterative better |
| **Stream API** | O(n) | O(n) | Functional style | Less readable | ❌ Overkill |

### Nested Loop Approach (Inefficient)
```java
for(int i = 0; i < n; i++) {
    int sum = 0;
    for(int j = 0; j <= i; j++) {
        sum += nums[j];
    }
    arr[i] = sum;
}
// Time: O(n²) - recalculates sum each time
```

---

## 💡 Key Takeaways

| # | Lesson | Application |
|---|--------|-------------|
| 1️⃣ | **➕ Prefix sum pattern** | Reuse previous results instead of recalculating |
| 2️⃣ | **🎯 Handle base case separately** | First element often needs special treatment |
| 3️⃣ | **🔄 Dynamic programming foundation** | Current state depends on previous state |
| 4️⃣ | **📊 Trade-offs matter** | Space O(n) vs modifying input |
| 5️⃣ | **⚡ Avoid redundant calculations** | O(n) vs O(n²) by reusing results |
| 6️⃣ | **🧮 Consider overflow** | Check constraints for data type selection |

---

## ⏱️ Complexity Analysis Summary

```
┌─────────────────────────────────────────────────────────┐
│ Time Complexity:   O(n)                                 │
│   - Single pass through array                           │
│   - Each operation is O(1)                              │
│                                                          │
│ Space Complexity:  O(n)                                 │
│   - Result array of size n                              │
│   - Could be O(1) with in-place modification           │
│                                                          │
│ Optimal:           Yes for preserving input             │
└─────────────────────────────────────────────────────────┘
```

---

## 🎓 Learning Moments

### Before This Problem
- ❓ Might have used nested loops (O(n²))
- ❓ Didn't think about in-place optimization
- ❓ Forgot to handle base case separately

### After This Problem
- ✅ Learned prefix sum / running total pattern
- ✅ Understood importance of reusing previous calculations
- ✅ Recognized this as dynamic programming foundation
- ✅ Considered trade-offs between space and input preservation
- ✅ Identified a very common pattern in array problems

---

## 🎨 Visual Trace Example

### Input: `[3, 1, 2, 10, 1]`

```
Index:      0   1   2    3   4
           ┌───┬───┬───┬────┬───┐
nums:      │ 3 │ 1 │ 2 │ 10 │ 1 │
           └───┴───┴───┴────┴───┘

Step 1: arr[0] = 3
           ┌───┐
arr:       │ 3 │
           └───┘

Step 2: arr[1] = arr[0] + nums[1] = 3 + 1 = 4
           ┌───┬───┐
arr:       │ 3 │ 4 │
           └───┴───┘

Step 3: arr[2] = arr[1] + nums[2] = 4 + 2 = 6
           ┌───┬───┬───┐
arr:       │ 3 │ 4 │ 6 │
           └───┴───┴───┘

Step 4: arr[3] = arr[2] + nums[3] = 6 + 10 = 16
           ┌───┬───┬───┬────┐
arr:       │ 3 │ 4 │ 6 │ 16 │
           └───┴───┴───┴────┘

Step 5: arr[4] = arr[3] + nums[4] = 16 + 1 = 17
           ┌───┬───┬───┬────┬────┐
arr:       │ 3 │ 4 │ 6 │ 16 │ 17 │
           └───┴───┴───┴────┴────┘
```

---

## 🔗 Related Problems

| Problem | Similarity | Difficulty |
|---------|-----------|-----------|
| Range Sum Query - Immutable | Uses prefix sum | 🟢 Easy |
| Product of Array Except Self | Similar pattern | 🟡 Medium |
| Subarray Sum Equals K | Uses running sum | 🟡 Medium |
| Maximum Subarray | Kadane's algorithm variant | 🟡 Medium |
| Range Sum Query 2D | 2D prefix sum | 🟡 Medium |

---

## 🌟 Pattern Recognition

This problem introduces the **Prefix Sum** pattern, which is fundamental for:
- ✅ Range sum queries
- ✅ Subarray problems
- ✅ Cumulative calculations
- ✅ Dynamic programming problems
- ✅ Optimization techniques

**When to use Prefix Sum:**
- Multiple range queries
- Finding subarrays with specific sum
- Cumulative statistics
- Window-based calculations

---

<div align="center">

### ✅ Problem Solved Successfully!

**Time Taken:** ~15 minutes
**Attempts:** 1
**Key Learning:** Prefix sum pattern - foundation for many array problems

**Difficulty:** 🟢 Easy | **Pattern:** Prefix Sum

</div>

---

**Last Updated:** January 03, 2026