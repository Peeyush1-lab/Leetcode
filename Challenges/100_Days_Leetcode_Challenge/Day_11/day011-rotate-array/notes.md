# 📝 Solution Explanation & Problem-Solving Notes

## 🎯 Solution Breakdown

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) |
| **Space Complexity** | O(1) |
| **Approach** | Three Reversals |
| **Time Taken** | ~20 minutes |

---

## 💡 Core Intuition

**The Reversal Trick:** Instead of rotating elements one by one, use **three clever reversals**!

### Why This Works
```
Rotating right by k = Moving last k elements to front

Strategy:
1. Reverse entire array
2. Reverse first k elements
3. Reverse remaining elements

Result: Perfectly rotated! ✨
```

---

## 📋 Algorithm Walkthrough

### Step 1: Handle k > length
```java
k = k % (len + 1);
```
**Why?** If k > array length, rotation repeats!
- `k = 10`, length = 7 → Same as `k = 3`
- `k % length` gives effective rotation

### Step 2: Reverse Entire Array
```java
reverse(nums, 0, len);
```
**Example:** `[1,2,3,4,5,6,7]` → `[7,6,5,4,3,2,1]`

### Step 3: Reverse First k Elements
```java
reverse(nums, 0, k - 1);
```
**Example:** Reverse first 3: `[7,6,5,...]` → `[5,6,7,...]`

### Step 4: Reverse Remaining Elements
```java
reverse(nums, k, len);
```
**Example:** Reverse rest: `[...,4,3,2,1]` → `[...,1,2,3,4]`

### Helper Function: Reverse
```java
private void reverse(int arr[], int left, int right) {
    while(left < right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        left++;
        right--;
    }
}
```
**Classic two-pointer swap pattern**

---

## 🎨 Detailed Visual Trace

### Input: `[1,2,3,4,5,6,7]`, `k = 3`

```
Step 0: Original
[1, 2, 3, 4, 5, 6, 7]

Step 1: Reverse entire array (0 to 6)
[7, 6, 5, 4, 3, 2, 1]
 ↕           ↕

Step 2: Reverse first k=3 elements (0 to 2)
[5, 6, 7, 4, 3, 2, 1]
 ↕     ↕

Step 3: Reverse remaining elements (3 to 6)
[5, 6, 7, 1, 2, 3, 4] ✓
          ↕        ↕

Final: [5, 6, 7, 1, 2, 3, 4]
Last 3 elements moved to front!
```

---

## 🎓 Key Insights

### Why Three Reversals Work

**Mathematical proof:**
```
Original: [A, B] where A = first n-k, B = last k
Want: [B, A]

Step 1: Reverse all → [B', A'] (reversed A and B)
Step 2: Reverse B' → [B, A']
Step 3: Reverse A' → [B, A] ✓
```

### The Modulo Trick
```java
k = k % (len + 1);
```
**Handles edge cases:**
- `k = 0` → No rotation
- `k = length` → Full rotation (same as original)
- `k > length` → Reduces to effective rotation

---

## ✅ What Went Right

- ✅ **Elegant solution** - Three reversals trick
- ✅ **O(1) space** - In-place modification
- ✅ **Handles edge cases** - Modulo for large k
- ✅ **Clean helper function** - Reusable reverse

---

## 🔀 Alternative Approaches

| Approach | Time | Space | Pros | Cons |
|----------|------|-------|------|------|
| **Three Reversals** | O(n) | O(1) | ✅ Optimal, elegant | Less intuitive |
| **Extra Array** | O(n) | O(n) | Simple | Uses extra space |
| **Cyclic Replace** | O(n) | O(1) | O(1) space | Complex logic |

### Extra Array Approach
```java
int[] temp = new int[n];
for (int i = 0; i < n; i++) {
    temp[(i + k) % n] = nums[i];
}
// Copy back to nums
```

---

## 💡 Key Takeaways

| # | Lesson |
|---|--------|
| 1️⃣ | 🔄 **Three reversals trick** - Elegant rotation |
| 2️⃣ | 🎯 **Modulo handles cycles** - k % length |
| 3️⃣ | 💪 **Two-pointer swap** - Standard reverse pattern |
| 4️⃣ | ✨ **In-place possible** - O(1) space achievable |

---

## ⏱️ Complexity Analysis

```
┌─────────────────────────────────────────────────────────┐
│ Time Complexity:   O(n)                                 │
│   - First reverse: O(n)                                 │
│   - Second reverse: O(k)                                │
│   - Third reverse: O(n-k)                               │
│   - Total: O(n)                                         │
│                                                         │
│ Space Complexity:  O(1)                                 │
│   - Only swap variables                                 │
│   - In-place modification                               │
│   - No extra arrays                                     │
└─────────────────────────────────────────────────────────┘
```

---

## 🔗 Related Problems

- **Rotate List** - Same concept for linked lists
- **Reverse String** - Basic reversal pattern
- **Reverse Words in String** - Multiple reversals

---

<div align="center">

### ✅ Problem Solved Successfully!

**Time:** ~20 minutes
**Issues:** 0 🎉
**Key Skill:** Three reversals trick - elegant and optimal!

**Difficulty:** 🟡 Medium | **Pattern:** Array, Two Pointers

</div>

---

**Last Updated:** January 11, 2026