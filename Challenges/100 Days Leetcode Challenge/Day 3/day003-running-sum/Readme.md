# 🏃 Day 3 (Problem 1): Running Sum of 1d Array

## 📋 Problem Statement

Given an array `nums`. We define a running sum of an array as `runningSum[i] = sum(nums[0]…nums[i])`.

Return the running sum of `nums`.

## 💡 Examples

### Example 1: 🎯
**Input:** `nums = [1,2,3,4]`
**Output:** `[1,3,6,10]`
**Explanation:**
- Running sum is obtained as follows: `[1, 1+2, 1+2+3, 1+2+3+4]` = `[1,3,6,10]`

### Example 2: 🎯
**Input:** `nums = [1,1,1,1,1]`
**Output:** `[1,2,3,4,5]`
**Explanation:**
- Running sum is obtained as follows: `[1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1]` = `[1,2,3,4,5]`

### Example 3: 🎯
**Input:** `nums = [3,1,2,10,1]`
**Output:** `[3,4,6,16,17]`
**Explanation:**
- `[3, 3+1, 3+1+2, 3+1+2+10, 3+1+2+10+1]` = `[3,4,6,16,17]`

## ⚠️ Constraints

- `1 <= nums.length <= 1000`
- `-10^6 <= nums[i] <= 10^6`

## 🔑 Key Points

1. 📊 **Running sum** = cumulative sum at each position
2. ➕ Each element is the sum of all previous elements plus current
3. 🎯 First element remains the same
4. 🔄 Each subsequent element builds on the previous sum
5. 💡 Can reuse previous result instead of recalculating
6. ⚡ **Prefix sum** technique - very common pattern

## 🛠️ Approach

The solution uses **iterative accumulation**:
- 📦 Create result array of same length
- 🎯 Set first element directly (no sum needed)
- ➕ For each position: `result[i] = result[i-1] + nums[i]`
- 🔄 Each step uses previous running sum
- ⏱️ **Time Complexity:** O(n)
- 💾 **Space Complexity:** O(n)

## 🎨 Visual Example

```
Input:  [1,  2,  3,  4]
         ↓   ↓   ↓   ↓
Step 1:  1
Step 2:  1 + 2 = 3
Step 3:  3 + 3 = 6
Step 4:  6 + 4 = 10
         ↓   ↓   ↓   ↓
Output: [1,  3,  6, 10]
```