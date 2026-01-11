# 🔍 Day 11 (Problem 1): Contains Duplicate

## 📋 Problem Statement

Given an integer array `nums`, return `true` if any value appears **at least twice** in the array, and return `false` if every element is distinct.

## 💡 Examples

### Example 1: ✅
**Input:** `nums = [1,2,3,1]`
**Output:** `true`

### Example 2: ❌
**Input:** `nums = [1,2,3,4]`
**Output:** `false`

### Example 3: ✅
**Input:** `nums = [1,1,1,3,3,4,3,2,4,2]`
**Output:** `true`

## ⚠️ Constraints

- `1 <= nums.length <= 10^5`
- `-10^9 <= nums[i] <= 10^9`

## 🔑 Key Points

1. 🎯 Find if **any duplicate** exists
2. ✨ HashSet perfect for O(1) lookup
3. 💡 `add()` returns false if element exists
4. ⚡ Early return on first duplicate

## 🛠️ Approach: HashSet

### Strategy
- Use HashSet to track seen elements
- Leverage `add()` return value
- Return true immediately when duplicate found

### ⏱️ Complexity
- **Time:** O(n) - Single pass
- **Space:** O(n) - HashSet storage