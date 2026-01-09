# 🔀 Day 7: Sort an Array

## 📋 Problem Statement

Given an array of integers `nums`, sort the array in **ascending order** and return it.

You must solve the problem **without using any built-in** functions in O(n log n) time complexity and with the smallest space complexity possible.

## 💡 Examples

### Example 1: ⬆️
**Input:** `nums = [5,2,3,1]`
**Output:** `[1,2,3,5]`
**Explanation:** After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).

### Example 2: ⬆️
**Input:** `nums = [5,1,1,2,0,0]`
**Output:** `[0,0,1,1,2,5]`
**Explanation:** Note that the values of nums are not necessarily unique.

## ⚠️ Constraints

- `1 <= nums.length <= 5 * 10^4`
- `-5 * 10^4 <= nums[i] <= 5 * 10^4`

## 🔑 Key Points

1. 🚫 **Cannot use built-in sort** (like Arrays.sort())
2. ⏱️ Must be **O(n log n)** time complexity
3. 💾 Minimize space complexity
4. 🔄 **Stable sort** preferred (maintains relative order of equal elements)
5. 📊 Handle **duplicate values**
6. ➖ Handle **negative numbers**

## 🛠️ Approach: Merge Sort

### 💡 Core Intuition
**Divide and Conquer!** Split array in half, recursively sort each half, then merge them back together in sorted order.

### 🎯 Why Merge Sort?
- ✅ Guaranteed O(n log n) time
- ✅ Stable sorting algorithm
- ✅ Works well with large datasets
- ✅ Predictable performance (no worst-case O(n²))

### 📋 Algorithm Steps
1. **Base Case:** If array has ≤ 1 element, already sorted
2. **Divide:** Split array into two halves at midpoint
3. **Conquer:** Recursively sort both halves
4. **Merge:** Combine sorted halves into single sorted array

### ⏱️ Complexity
- **Time:** O(n log n) - Guaranteed
- **Space:** O(n) - Temporary array for merging

## 🎨 Visual Example

```
Input: [5, 2, 3, 1]

Step 1: Divide
        [5, 2, 3, 1]
           /      \
      [5, 2]      [3, 1]
       /  \        /  \
     [5]  [2]    [3]  [1]

Step 2: Merge (sorting as we combine)
     [5]  [2]    [3]  [1]
       \  /        \  /
      [2, 5]      [1, 3]
           \      /
        [1, 2, 3, 5]

Result: [1, 2, 3, 5] ✅
```

## 🔄 Merge Process

```
Left:  [2, 5]    Right: [1, 3]
        ↓                ↓
Compare 2 vs 1 → Take 1 → [1]
Compare 2 vs 3 → Take 2 → [1, 2]
Compare 5 vs 3 → Take 3 → [1, 2, 3]
Left exhausted → Take 5 → [1, 2, 3, 5]
```