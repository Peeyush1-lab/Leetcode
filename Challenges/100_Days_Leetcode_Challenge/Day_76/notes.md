# Notes - 1358. Number of Substrings Containing All Three Characters

## Intuition

Once a window `[left, right]` contains all three characters, **every extension of that window to the right is also valid**. So instead of counting each valid substring individually, we can count all substrings starting at `left` that end at `right` or beyond in one shot: that's `n - right` substrings.

---

## Approach — Shrinking Sliding Window

- Expand `right` one character at a time, updating a frequency array of size 3.
- Whenever the window contains all three characters (`count[0] > 0 && count[1] > 0 && count[2] > 0`):
  - Add `n - right` to result — all substrings from `[left..right]` to `[left..n-1]` are valid.
  - Shrink from the left to find the next smallest valid window.
  - Repeat the check until the window is no longer valid.

---

## Complexity

| | Value |
|---|---|
| Time | O(n) — each character is added and removed at most once |
| Space | O(1) — fixed array of size 3 |

---

## Key Observations

- The key insight is `result += n - right`: once `[left, right]` is valid, we don't need to enumerate all longer substrings — they're all valid and we count them mathematically.
- The `while` loop (not `if`) is important — after shrinking, the window might still be valid with the new `left`, so we keep shrinking and accumulating until it breaks.
- This is a **shrink-when-valid** pattern, opposite to the more common shrink-when-invalid pattern seen in problems like LC 424 or LC 1695.

---

## Edge Cases

- No valid substring possible → result stays 0 (e.g., string has only two distinct characters).
- Minimum valid string `"abc"` → exactly 1 substring.
- All characters the same → 0 valid substrings.

---

## Problem Link
[1358. Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/)