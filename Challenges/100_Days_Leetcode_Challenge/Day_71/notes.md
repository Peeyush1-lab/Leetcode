# Notes - 424. Longest Repeating Character Replacement

## Intuition

For any window to be valid, the number of characters we need to **replace** equals:

```
window size - count of most frequent character in window
```

If that value is `<= k`, the window is valid — we can replace all non-dominant characters using at most `k` operations.

So the goal becomes: find the largest window where `(window size - maxCount) <= k`.

---

## Approach — Sliding Window

- Use two pointers `left` and `right` to define the window.
- Maintain a `freq[]` array of size 26 to count character frequencies in the current window.
- Track `maxCount` — the highest frequency of any single character seen so far.
- If the window becomes invalid (`window size - maxCount > k`), shrink from the left.
- Update `best` with the largest valid window size seen.

---

## Complexity

| | Value |
|---|---|
| Time | O(n) — each character is visited at most twice |
| Space | O(1) — fixed-size array of 26 characters |

---

## Key Observations

- `maxCount` is never decremented even when the window shrinks. This is intentional — we only care about finding a window **larger** than our current best, so there's no point shrinking `maxCount`. It acts as a lower bound on the dominant frequency needed to beat `best`.
- The window never shrinks below the size of the current best answer, so `best` always equals the maximum window size reached.
- This trick makes the algorithm O(n) instead of O(n²).

---

## Edge Cases

- `k = 0` → window is valid only if all characters are the same.
- `k >= s.length` → entire string is valid, return `s.length()`.
- Single character string → always return 1.