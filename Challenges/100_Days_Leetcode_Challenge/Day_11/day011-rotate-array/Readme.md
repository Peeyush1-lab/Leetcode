# 🔄 Day 11 (Problem 2): Rotate Array

## 📋 Problem Statement

Given an integer array `nums`, rotate the array to the **right** by `k` steps, where `k` is non-negative.

## 💡 Examples

### Example 1:
**Input:** `nums = [1,2,3,4,5,6,7]`, `k = 3`
**Output:** `[5,6,7,1,2,3,4]`
**Explanation:**
- rotate 1 steps to the right: `[7,1,2,3,4,5,6]`
- rotate 2 steps to the right: `[6,7,1,2,3,4,5]`
- rotate 3 steps to the right: `[5,6,7,1,2,3,4]`

### Example 2:
**Input:** `nums = [-1,-100,3,99]`, `k = 2`
**Output:** `[3,99,-1,-100]`
**Explanation:**
- rotate 1 steps to the right: `[99,-1,-100,3]`
- rotate 2 steps to the right: `[3,99,-1,-100]`

## ⚠️ Constraints

- `1 <= nums.length <= 10^5`
- `-2^31 <= nums[i] <= 2^31 - 1`
- `0 <= k <= 10^5`

**Follow up:** Try to come up with as many solutions as you can. There are at least **three** different ways to solve this problem. Could you do it **in-place** with O(1) extra space?

## 🔑 Key Points

1. 🔄 Rotate **right** by k steps
2. 💡 **In-place** - modify original array
3. 🎯 **k % length** handles k > array length
4. ✨ **Reverse trick** - Elegant O(1) space solution

## 🛠️ Approach: Three Reversals

### Brilliant Strategy
Instead of moving elements one by one, use **three reversals**!

```
Original: [1,2,3,4,5,6,7], k=3

Step 1: Reverse entire array
        [7,6,5,4,3,2,1]

Step 2: Reverse first k elements
        [5,6,7,4,3,2,1]

Step 3: Reverse remaining elements
        [5,6,7,1,2,3,4] ✓
```

### ⏱️ Complexity
- **Time:** O(n) - Three passes
- **Space:** O(1) - In-place reversal