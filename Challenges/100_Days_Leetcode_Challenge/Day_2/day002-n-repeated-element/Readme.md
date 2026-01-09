# 🔁 Day 2 (Problem 1): N-Repeated Element in Size 2N Array

## 📋 Problem Statement

You are given an integer array `nums` with the following properties:

- `nums.length == 2 * n`
- `nums` contains `n + 1` unique elements
- Exactly one element of `nums` is repeated `n` times

Return the element that is repeated `n` times`.

## 💡 Examples

### Example 1: 🎯
**Input:** `nums = [1,2,3,3]`
**Output:** `3`
**Explanation:** The element 3 is repeated 2 times (n = 2).

### Example 2: 🎯
**Input:** `nums = [2,1,2,5,3,2]`
**Output:** `2`
**Explanation:** The element 2 is repeated 3 times (n = 3).

### Example 3: 🎯
**Input:** `nums = [5,1,5,2,5,3,5,4]`
**Output:** `5`
**Explanation:** The element 5 is repeated 4 times (n = 4).

## ⚠️ Constraints

- `2 <= n <= 5000`
- `nums.length == 2 * n`
- `0 <= nums[i] <= 10^4`
- `nums` contains `n + 1` unique elements and one of them is repeated exactly `n` times

## 🔑 Key Points

1. 📊 Array length is always `2 * n`
2. 🔢 Contains `n + 1` unique elements
3. 🎯 One element appears exactly `n` times
4. ✨ All other elements appear exactly once
5. 🎪 The repeated element appears `n` times in an array of size `2n` (50% of array)
6. 💡 First duplicate found is always the answer

## 🛠️ Approach

The solution uses a **HashSet** for efficient duplicate detection:
- 🔄 Iterate through the array once
- 📦 Use HashSet to track seen elements
- ⚡ When `add()` returns `false`, we've found a duplicate
- 🎯 Since only one element repeats (n times), the first duplicate is our answer
- ⏱️ **Time Complexity:** O(n)
- 💾 **Space Complexity:** O(n)