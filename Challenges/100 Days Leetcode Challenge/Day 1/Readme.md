# 💣 Day 1: Defuse the Bomb

## 📋 Problem Statement

You have a bomb to defuse, and your time is running out! Your informer will provide you with a **circular** array `code` of length `n` and a key `k`.

To decrypt the code, you must replace every number. All the numbers are replaced **simultaneously**.

- If `k > 0`, replace the `i-th` number with the sum of the **next** `k` numbers.
- If `k < 0`, replace the `i-th` number with the sum of the **previous** `k` numbers.
- If `k == 0`, replace the `i-th` number with `0`.

As `code` is circular, the next element of `code[n-1]` is `code[0]`, and the previous element of `code[0]` is `code[n-1]`.

Given the circular array `code` and an integer key `k`, return the decrypted code to defuse the bomb!

## 💡 Examples

### Example 1: ➡️
**Input:** `code = [5,7,1,4], k = 3`
**Output:** `[12,10,16,13]`
**Explanation:**
- Each number is replaced by the sum of the next 3 numbers.
- The decrypted code is `[7+1+4, 1+4+5, 4+5+7, 5+7+1]` = `[12,10,16,13]`

### Example 2: 🔄
**Input:** `code = [1,2,3,4], k = 0`
**Output:** `[0,0,0,0]`
**Explanation:**
- When `k` is zero, all numbers are replaced with 0.

### Example 3: ⬅️
**Input:** `code = [2,4,9,3], k = -2`
**Output:** `[12,5,6,13]`
**Explanation:**
- Each number is replaced by the sum of the previous 2 numbers.
- The decrypted code is `[3+9, 2+3, 4+2, 9+4]` = `[12,5,6,13]`

## ⚠️ Constraints

- `n == code.length`
- `1 <= n <= 100`
- `1 <= code[i] <= 100`
- `-(n - 1) <= k <= n - 1`

## 🔑 Key Points

1. 🔄 The array is **circular** - this is crucial for handling edge cases
2. ⚡ The replacement happens **simultaneously** for all elements
3. 🎯 Three distinct cases to handle: `k > 0`, `k < 0`, and `k == 0`
4. ➡️ When `k > 0`: sum the **next** k elements
5. ⬅️ When `k < 0`: sum the **previous** k elements (in absolute value)
6. 🔢 Modulo operation is essential for circular array navigation

## 🛠️ Approach

The solution uses a brute force approach with nested loops:
- 🔁 Outer loop iterates through each position in the array
- ➕ Inner loop calculates the sum of k elements (next or previous based on sign of k)
- 🔢 Uses modulo operator `%` to handle the circular nature of the array
- ➖ For negative k, adds `n` before modulo to handle negative indices correctly