# ➕ Day 2 (Problem 2): Plus One

## 📋 Problem Statement

You are given a **large integer** represented as an integer array `digits`, where each `digits[i]` is the `i-th` digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading `0`'s.

Increment the large integer by one and return the resulting array of digits.

## 💡 Examples

### Example 1: 🎯
**Input:** `digits = [1,2,3]`
**Output:** `[1,2,4]`
**Explanation:** The array represents the integer 123. Incrementing by one gives 123 + 1 = 124.
Thus, the result should be `[1,2,4]`.

### Example 2: 🎯
**Input:** `digits = [4,3,2,1]`
**Output:** `[4,3,2,2]`
**Explanation:** The array represents the integer 4321. Incrementing by one gives 4321 + 1 = 4322.

### Example 3: 💥
**Input:** `digits = [9]`
**Output:** `[1,0]`
**Explanation:** The array represents the integer 9. Incrementing by one gives 9 + 1 = 10.
Thus, the result should be `[1,0]`.

### Example 4: 💥
**Input:** `digits = [9,9,9]`
**Output:** `[1,0,0,0]`
**Explanation:** The array represents the integer 999. Incrementing by one gives 999 + 1 = 1000.
Thus, the result should be `[1,0,0,0]`.

## ⚠️ Constraints

- `1 <= digits.length <= 100`
- `0 <= digits[i] <= 9`
- `digits` does not contain any leading `0`'s

## 🔑 Key Points

1. 🔢 Array represents a large integer (most significant digit first)
2. ➕ Need to add 1 to the entire number (not just last digit)
3. 🔄 Handle carry propagation (9 + 1 = 10, carry 1)
4. 💥 Edge case: All 9's require new array with extra length
5. ⚡ Optimize: Stop early if no carry needed
6. 🎯 Work backwards: Start from least significant digit (rightmost)

## 🛠️ Approach

The solution simulates **addition with carry propagation**:
- ⬅️ Start from the rightmost digit (least significant)
- ➕ If digit < 9: Simply increment and return
- 🔄 If digit = 9: Set to 0 and continue (carry forward)
- 💥 If all digits are 9: Create new array with leading 1
- ⏱️ **Time Complexity:** O(n) worst case, O(1) best case
- 💾 **Space Complexity:** O(1) or O(n) if overflow occurs