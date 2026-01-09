# 📝 Solution Explanation & Problem-Solving Notes

## 🎯 Solution Breakdown

### 📊 Algorithm Overview
This solution uses a **brute force approach** with nested loops to calculate the sum for each position.

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n × k) |
| **Space Complexity** | O(n) |
| **Approach** | Nested loops with modulo operations |

### 🔄 Step-by-Step Logic

| Step | Action | Description |
|------|--------|-------------|
| 1 | Initialize | Create result array of size n (default values 0) |
| 2 | Handle k=0 | Early return with array of zeros |
| 3 | Iterate positions | For each position i in the array |
| 4 | Calculate sum | Sum next k elements (if k>0) or previous \|k\| elements (if k<0) |
| 5 | Handle wrapping | Use modulo operator for circular array navigation |

### 💻 Code Walkthrough

| Code Section | Purpose | Key Point |
|--------------|---------|-----------|
| `int n = code.length;`<br>`int[] result = new int[n];` | Setup | Store length and create result array |
| `if (k == 0) return result;` | Early exit | Handle k=0 case immediately |
| `for (int j = i + 1; j <= i + k; j++)`<br>`result[i] += code[j % n];` | Positive k | Sum next k elements using `j % n` for wrapping |
| `for (int j = i - Math.abs(k); j < i; j++)`<br>`result[i] += code[(j + n) % n];` | Negative k | Sum previous \|k\| elements using `(j + n) % n` |

---

## ⚠️ Problems Encountered & Solutions

### ❌ Problem 1: Understanding Circular Array Behavior

| Aspect | Details |
|--------|---------|
| **Issue** | Struggled with array wrapping at boundaries |
| **Example** | Array: `[5,7,1,4]`, k=3, i=3<br>Need indices 4,5,6 → Should map to 0,1,2 |
| **Confusion** | How do indices beyond array length wrap back? |
| **Solution** | Use modulo operator `j % n` for automatic wrapping |
| **Result** | ✓ Correctly handles all boundary cases |

---

### ❌ Problem 2: Handling Negative K Values

| Aspect | Details |
|--------|---------|
| **Issue** | Negative indices caused IndexOutOfBoundsException |
| **Why** | Direct use of negative values like -1, -2 invalid in Java |
| **Example** | Array: `[2,4,9,3]`, k=-2, i=0<br>Need indices -2 and -1 |
| **Wrong Approach** | Using `code[j]` directly with negative j |
| **Correct Solution** | Use `(j + n) % n` formula |
| **Proof** | j=-2: `(-2+4)%4=2` ✓<br>j=-1: `(-1+4)%4=3` ✓ |

---

### ❌ Problem 3: Off-by-One Errors in Loop Bounds

| Scenario | Wrong Bound | Correct Bound | Why |
|----------|-------------|---------------|-----|
| **Positive k** | `j < i + k` | `j <= i + k` | Need exactly k elements (inclusive) |
| **Negative k** | `j <= i` | `j < i` | Exclude current element |
| **Start (k>0)** | `j = i` | `j = i + 1` | Start from NEXT element |
| **Start (k<0)** | `j = i - k` | `j = i - \|k\|` | Start \|k\| positions back |

**Impact:** Wrong bounds caused incorrect sums or extra elements included.

---

### ❌ Problem 4: Simultaneous Replacement Confusion

| Question | Answer | Explanation |
|----------|--------|-------------|
| Do we modify the original array? | ❌ No | Must keep original values |
| Do we use updated values? | ❌ No | Always read from original |
| Why separate arrays? | ✓ Required | "All numbers replaced **simultaneously**" |
| Current implementation? | ✓ Correct | Read from `code`, write to `result` |

**Key insight:** Maintain separate input (`code`) and output (`result`) arrays.

---

### ❌ Problem 5: Edge Case Testing

| Edge Case | Input | Expected Output | Challenge |
|-----------|-------|-----------------|-----------|
| Single element | `[5]`, k=1 | `[5]` | Next element wraps to itself |
| k = array length | `[1,2,3,4]`, k=4 | Sum all except self | Full rotation |
| Maximum negative k | `[1,2,3]`, k=-2 | Previous elements | Negative wrapping |
| k = 0 | `[5,7,1,4]`, k=0 | `[0,0,0,0]` | All zeros |
| Small array | `[1,2]`, k=1 | `[2,1]` | Minimal wrapping |

**Status:** ✅ All edge cases handled correctly by modulo operations.

---

## 🔀 Alternative Approaches Considered

| Approach | Time | Space | Pros | Cons | Decision |
|----------|------|-------|------|------|----------|
| **Current: Brute Force** | O(n×k) | O(n) | Simple, clear | Slower for large k | ✓ **Chosen** |
| **Sliding Window** | O(n) | O(n) | Optimal speed | More complex | ❌ Overkill for n≤100 |
| **Circular Deque** | O(n×k) | O(n+k) | Cleaner navigation | Extra memory | ❌ Unnecessary complexity |
| **Prefix Sums** | O(n) | O(n) | Fast queries | Tricky with circular | ❌ Complex implementation |

**Verdict:** Brute force is sufficient given constraints (n ≤ 100, k ≤ 99).

---

## 💡 Key Takeaways

| # | Lesson | Application |
|---|--------|-------------|
| 1️⃣ | **🔄 Modulo for circular arrays** | `j % n` handles forward wrapping |
| 2️⃣ | **➖ Negative index handling** | `(j + n) % n` converts negative to positive |
| 3️⃣ | **🎯 Loop bounds precision** | Carefully verify inclusive/exclusive bounds |
| 4️⃣ | **🧪 Edge case testing** | Test boundaries, minimums, maximums |
| 5️⃣ | **📦 Separate arrays for simultaneous updates** | Never modify input when all changes happen "at once" |
| 6️⃣ | **✨ Simplicity over optimization** | Choose clear code when constraints allow |

---

## ⏱️ Complexity Analysis Summary

```
┌─────────────────────────────────────────────────────────┐
│ Best Case:     O(n) when k = 0                         │
│ Average Case:  O(n × k)                                 │
│ Worst Case:    O(n × k) where k ≈ n                    │
│ Space:         O(n) for result array                    │
└─────────────────────────────────────────────────────────┘
```

**⚡ Bottleneck:** Nested loop structure - outer loop runs n times, inner loop runs k times.