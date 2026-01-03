# 🔢 Day 3 (Problem 2): Count Primes

## 📋 Problem Statement

Given an integer `n`, return the number of prime numbers that are strictly less than `n`.

A **prime number** is a natural number greater than 1 that is not a product of two smaller natural numbers.

## 💡 Examples

### Example 1: 🎯
**Input:** `n = 10`
**Output:** `4`
**Explanation:** There are 4 prime numbers less than 10: `2, 3, 5, 7`.

### Example 2: 🎯
**Input:** `n = 0`
**Output:** `0`
**Explanation:** There are no prime numbers less than 0.

### Example 3: 🎯
**Input:** `n = 1`
**Output:** `0`
**Explanation:** There are no prime numbers less than 1.

### Example 4: 💡
**Input:** `n = 20`
**Output:** `8`
**Explanation:** Primes less than 20: `2, 3, 5, 7, 11, 13, 17, 19`.

## ⚠️ Constraints

- `0 <= n <= 5 * 10^6`

## 🔑 Key Points

1. 🔢 Find all primes **strictly less than** n (not including n)
2. ✨ Prime: number > 1 with no divisors except 1 and itself
3. 🎯 2 is the only even prime number
4. 💡 **Sieve of Eratosthenes** - ancient, efficient algorithm
5. ⚡ Optimization: Only check up to √n for marking multiples
6. 🚀 Start marking from i² (smaller multiples already marked)

## 🛠️ Approach: Sieve of Eratosthenes

This solution uses the **Sieve of Eratosthenes** algorithm:

### 📋 Algorithm Steps
1. 📦 Create boolean array assuming all numbers are prime
2. 🎯 Start from 2 (first prime)
3. 🔄 For each prime `i`, mark all multiples as non-prime
4. ⚡ Optimization: Start marking from `i²` (earlier multiples already marked)
5. ⏹️ Only check up to √n (any composite has factor ≤ √n)
6. 📊 Count remaining primes

### 🎨 Visual Example (n = 10)

```
Numbers:  2  3  4  5  6  7  8  9
Initial: [T  T  T  T  T  T  T  T]

i=2: Mark multiples of 2 (4,6,8)
        [T  T  F  T  F  T  F  T]

i=3: Mark multiples of 3 (9)
        [T  T  F  T  F  T  F  F]

i=4: Skip (already marked as composite)

Result: Count T's = 4 primes (2,3,5,7)
```

### ⏱️ Complexity
- **Time:** O(n log log n) - Sieve of Eratosthenes
- **Space:** O(n) - Boolean array

## 🌟 Why Sieve of Eratosthenes?

| Approach | Time | For n=10^6 |
|----------|------|-----------|
| Trial Division (naive) | O(n × √n) | ≈ 10^9 operations |
| Sieve of Eratosthenes | O(n log log n) | ≈ 10^6 operations |

**1000x faster!** 🚀