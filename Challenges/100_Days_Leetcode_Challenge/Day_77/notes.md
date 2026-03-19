# Notes - 2110. Number of Smooth Descent Periods of a Stock

## Intuition

Every single day is a valid smooth descent period on its own. For consecutive days where the price drops by exactly 1, the number of new valid periods added equals the current streak length.

For example, a streak of length 3 `[5,4,3]` contributes:
- `[5]`, `[4]`, `[3]` → already counted in previous steps
- At index of `4`: adds 2 (`[4]`, `[5,4]`)
- At index of `3`: adds 3 (`[3]`, `[4,3]`, `[5,4,3]`)

So each position contributes exactly `prev` (current streak length) new periods.

---

## Approach — Linear Scan with Streak Counter

- Start with `res = 1` and `prev = 1` (the first day is always a valid period).
- For each subsequent day:
  - If `prices[i] == prices[i-1] - 1`, extend the streak: `prev++`.
  - Otherwise reset the streak: `prev = 1`.
  - Add `prev` to `res`.

---

## Complexity

| | Value |
|---|---|
| Time | O(n) — single pass |
| Space | O(1) — only two variables |

---

## Key Observations

- The contribution of each index is exactly the length of the smooth descent streak ending at that index. This is a classic **count subarrays ending at i** pattern.
- This same pattern appears in problems like "Number of Arithmetic Slices" — whenever a subarray property depends only on adjacent differences, a streak counter works.
- `res` is `long` because in the worst case (all prices form one big descent), the total count is `n*(n+1)/2`, which can overflow `int` for `n = 10^5`.

---

## Edge Cases

- Single element → return 1.
- Strictly decreasing by 1 → maximum result `n*(n+1)/2`.
- No two consecutive elements differ by exactly 1 → result equals `n` (all single-day periods).

---

## Problem Link
[2110. Number of Smooth Descent Periods of a Stock](https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/)