# Day 73 - 1652. Defuse the Bomb

**Difficulty:** Easy
**Topic Tags:** Array, Sliding Window

---

## Problem Statement

You have a circular array `code` of length `n` and a key `k`. To decrypt the code, you must replace every number with the sum of the **next** `k` numbers if `k > 0`, the sum of the **previous** `|k|` numbers if `k < 0`, or `0` if `k == 0`.

Since the array is **circular**, the next element of `code[n-1]` is `code[0]`, and the previous element of `code[0]` is `code[n-1]`.

Return the decrypted code as a new array.

---

## Examples

**Example 1:**
```
Input:  code = [5,7,1,4], k = 3
Output: [12,10,16,13]

Explanation:
result[0] = 7 + 1 + 4 = 12
result[1] = 1 + 4 + 5 = 10
result[2] = 4 + 5 + 7 = 16
result[3] = 5 + 7 + 1 = 13
```

**Example 2:**
```
Input:  code = [1,2,3,4], k = 0
Output: [0,0,0,0]
```

**Example 3:**
```
Input:  code = [2,4,9,3], k = -2
Output: [12,5,6,13]

Explanation:
result[0] = 9 + 3 = 12  (2 elements before index 0, wrapping around)
result[1] = 2 + 3 = 5   (wait — previous 2 before index 1: index -1=3, index -2=9? No.)
            Actually: previous 2 of index 1 → index 0=2, index 3=3 → 2+3=5
result[2] = 4 + 2 = 6
result[3] = 9 + 4 = 13
```

---

## Constraints

- `n == code.length`
- `1 <= n <= 100`
- `1 <= |k| <= n`
- `0 <= code[i] <= 100`

|**Previous:** [Day 72](../Day_72/) | **Next:** [Day 74](../Day_74/)|
|---|---|

|**Date:** March 15, 2026|
|---|