# Notes - 1052. Grumpy Bookstore Owner

## Intuition

Split the problem into two independent parts:

1. **Base satisfied** — customers who are always satisfied regardless of the technique (minutes where `grumpy[i] == 0`). This is a fixed value.
2. **Extra gain** — the technique only helps during grumpy minutes. So we want to find the window of size `minutes` that covers the **most customers during grumpy minutes**.

The answer is simply `baseSatisfied + maxGain`.

---

## Approach — Fixed-Size Sliding Window

- First pass: accumulate `baseSatisfied` by summing `customers[i]` where `grumpy[i] == 0`.
- Second pass: slide a window of exactly size `minutes` across the array, tracking only the extra customers gained from grumpy minutes (`grumpy[i] == 1`) inside the window.
- As the window moves right, add the incoming element if grumpy, and subtract the outgoing element if it was grumpy.
- Track `maxGain` across all window positions.

---

## Complexity

| | Value |
|---|---|
| Time | O(n) — two linear passes |
| Space | O(1) — no extra data structures |

---

## Key Observations

- Customers at non-grumpy minutes are **already counted** in the base — adding them again inside the window would double-count. So the window only accumulates grumpy minutes.
- The window size is fixed at `minutes`, so there's no need for a `left` pointer — the element leaving the window is always at index `i - minutes`.
- The technique doesn't "undo" existing satisfaction, it only converts previously lost customers into satisfied ones.

---

## Edge Cases

- `minutes == n` → the entire array is the window; all customers are satisfied.
- All `grumpy[i] == 0` → `maxGain` stays 0, answer is just `baseSatisfied`.
- All `grumpy[i] == 1` → `baseSatisfied` is 0, answer is purely the best window sum.