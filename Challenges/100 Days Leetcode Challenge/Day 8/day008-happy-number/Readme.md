# 😊 Day 8 (Problem 1): Happy Number

## 📋 Problem Statement

Write an algorithm to determine if a number `n` is **happy**.

A **happy number** is a number defined by the following process:
- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals 1 (where it will stay), or it **loops endlessly in a cycle** which does not include 1.
- Those numbers for which this process **ends in 1** are happy.

Return `true` if `n` is a happy number, and `false` if not.

## 💡 Examples

### Example 1: ✅
**Input:** `n = 19`
**Output:** `true`
**Explanation:**
```
1² + 9² = 1 + 81 = 82
8² + 2² = 64 + 4 = 68
6² + 8² = 36 + 64 = 100
1² + 0² + 0² = 1 ✓ Happy!
```

### Example 2: ❌
**Input:** `n = 2`
**Output:** `false`
**Explanation:**
```
2² = 4
4² = 16
1² + 6² = 1 + 36 = 37
3² + 7² = 9 + 49 = 58
5² + 8² = 25 + 64 = 89
8² + 9² = 64 + 81 = 145
1² + 4² + 5² = 1 + 16 + 25 = 42
4² + 2² = 16 + 4 = 20
2² + 0² = 4 ← Cycle! Back to 4
```

## ⚠️ Constraints

- `1 <= n <= 2^31 - 1`

## 🔑 Key Points

1. 🔄 Process repeats with sum of squares
2. 😊 **Happy** if reaches 1
3. 😢 **Unhappy** if enters infinite loop
4. 🎯 **Critical insight**: All unhappy numbers eventually reach 4!
5. 💡 Can detect cycle OR use known cycle number (4)
6. ⚡ No need to track all seen numbers

## 🛠️ Approach: Cycle Detection with Magic Number

### 💡 Core Intuition
**Mathematical Discovery:** All unhappy numbers enter a cycle that contains the number **4**!

If we detect we've reached 4, we know we're in the unhappy cycle.

### 🎯 Why This Works
- Happy numbers reach 1
- Unhappy numbers reach 4 (proven mathematically)
- No need to track history - just check for 1 or 4!

### 📋 Algorithm Steps
1. Loop while `n ≠ 1` and `n ≠ 4`
2. Calculate sum of squares of digits
3. Replace n with this sum
4. If we exit at 1 → Happy ✅
5. If we exit at 4 → Unhappy ❌

### ⏱️ Complexity
- **Time:** O(log n) - Digits processing
- **Space:** O(1) - No extra storage

## Example

```
n = 19 (Happy)
─────────────────
19 → 1² + 9² = 82
82 → 8² + 2² = 68
68 → 6² + 8² = 100
100 → 1² + 0² + 0² = 1 ✓

n = 2 (Unhappy)
─────────────────
2 → 2² = 4 ✗
(Would cycle: 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4)
```