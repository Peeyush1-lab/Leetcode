# 📊 Day 8 (Problem 2): Continuous Subarray Sum

## 📋 Problem Statement

Given an integer array `nums` and an integer `k`, return `true` if `nums` has a **good subarray** or `false` otherwise.

A **good subarray** is a subarray where:
- Its length is **at least two**, and
- The sum of the elements of the subarray is a **multiple of** `k`.

**Note:** A subarray is a contiguous part of the array. An integer `x` is a multiple of `k` if there exists an integer `n` such that `x = n * k`. `0` is **always** a multiple of `k`.

## 💡 Examples

### Example 1: ✅
**Input:** `nums = [23,2,4,6,7]`, `k = 6`
**Output:** `true`
**Explanation:** `[2, 4]` is a continuous subarray of size 2 whose sum (6) is a multiple of 6.

### Example 2: ✅
**Input:** `nums = [23,2,6,4,7]`, `k = 6`
**Output:** `true`
**Explanation:** `[23, 2, 6, 4, 7]` is a continuous subarray of size 5 whose sum (42) is a multiple of 6.

### Example 3: ❌
**Input:** `nums = [23,2,6,4,7]`, `k = 13`
**Output:** `false`
**Explanation:** No subarray of size ≥ 2 has sum that's a multiple of 13.

## ⚠️ Constraints

- `1 <= nums.length <= 10^5`
- `0 <= nums[i] <= 10^9`
- `0 <= sum(nums[i]) <= 2^31 - 1`
- `1 <= k <= 2^31 - 1`

## 🔑 Key Points

1. 📏 Subarray must have **length ≥ 2**
2. 🔢 Sum must be **multiple of k**
3. 🎯 **Key insight**: Use prefix sum + modulo
4. 💡 **Pigeonhole principle**: Same remainder = multiple of k
5. 📦 Use **HashMap** to store remainders and indices
6. ⚡ 0 is always a multiple of k

## 🛠️ Approach: Prefix Sum + HashMap

### 💡 Core Intuition
**Mathematical Property:**
If `prefix[i] % k == prefix[j] % k`, then the subarray from `i+1` to `j` has sum that's a multiple of k!

**Why?**
```
(prefix[j] - prefix[i]) % k == 0
This means sum(nums[i+1...j]) is divisible by k!
```

### 🎯 Strategy
1. Track prefix sum modulo k
2. Store first occurrence of each remainder
3. If same remainder seen again with gap ≥ 2 → Found!

### 📋 Algorithm Steps
1. Initialize HashMap with `{0: -1}` (handle edge case)
2. For each element:
   - Add to prefix sum
   - Take modulo k (if k ≠ 0)
   - If remainder seen before AND gap ≥ 2 → return true
   - Otherwise, store this remainder's first index
3. Return false if no valid subarray found

### ⏱️ Complexity
- **Time:** O(n) - Single pass
- **Space:** O(min(n, k)) - HashMap size

## Example

```
nums = [23, 2, 4, 6, 7], k = 6

Prefix sums: [23, 25, 29, 35, 42]
Modulo 6:    [5,  1,  5,  5,  0]
              ↑        ↑
            Same remainder at i=0 and i=2!

Subarray [2, 4] from index 1 to 2
Sum = 6, which is 6 % 6 = 0 ✓
```