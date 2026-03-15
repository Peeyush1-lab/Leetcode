# Notes - 1652. Defuse the Bomb

## Intuition

The problem is straightforward once you handle the **circular indexing**. For each position `i`, sum either the next `k` or previous `|k|` elements, wrapping around the array using modulo arithmetic.

---

## Approach — Simulation with Circular Indexing

Three distinct cases based on `k`:

- `k == 0` → return an array of all zeros immediately.
- `k > 0` → for each `i`, sum `code[(i+1) % n]` through `code[(i+k) % n]`.
- `k < 0` → for each `i`, sum `code[(i-|k|+n) % n]` through `code[(i-1+n) % n]`.

The `+ n` before modulo when going backwards prevents negative indices in Java (since Java's `%` can return negative values for negative operands).

---

## Complexity

| | Value |
|---|---|
| Time | O(n * k) — for each of n elements, sum k neighbors |
| Space | O(n) — for the result array |

Given the constraints (`n <= 100`, `|k| <= n`), this is at most O(10^4) — well within limits.

---

## Key Observations

- `j % n` handles forward wrapping cleanly since `j` is always positive.
- `(j + n) % n` handles backward wrapping — adding `n` ensures we never pass a negative value to `%`.
- A sliding window prefix sum optimization is possible for larger inputs, but the brute force is perfectly fine here given the small constraints.

---

## Edge Cases

- `k == 0` → all zeros, handled as an early return.
- `|k| == n` → each element sums all other elements in the array.
- Single element array → result is always `[0]` regardless of `k` (it wraps back to itself, but since `|k| <= n`, for `n=1`, `k` must be 1 or -1, summing `code[0]` itself).