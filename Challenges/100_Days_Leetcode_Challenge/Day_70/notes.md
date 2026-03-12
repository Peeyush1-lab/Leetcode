# Notes - 1910. Remove All Occurrences of a Substring

## Intuition

The key insight is that removing a substring can **expose new occurrences** that weren't there before — so a single left-to-right pass won't work if we just track indices. We need something that can "look back" after each character is added.

A **StringBuilder used as a stack** is perfect here: append characters one by one, and after each append check if the last `m` characters equal `part`. If yes, delete them. This naturally handles newly formed matches.

---

## Approach — StringBuilder as Stack

- Iterate over each character of `s`.
- Append it to a `StringBuilder`.
- After each append, if the builder's length >= `m` and the last `m` characters equal `part`, delete those characters.
- Return the builder as the final string.

This mimics a stack where we "pop" the matched suffix whenever it forms the target pattern.

---

## Complexity

| | Value |
|---|---|
| Time | O(n * m) — for each of the n characters we do a substring comparison of length m |
| Space | O(n) — for the StringBuilder |

---

## Key Observations

- Naive approach: repeatedly call `s.indexOf(part)` and rebuild — works but O(n²) in the worst case.
- The stack approach is cleaner and avoids rebuilding the whole string on every removal.
- The `delete` on StringBuilder is O(m) but m ≤ 1000 and n ≤ 1000, so it's fine within constraints.

---

## Edge Cases

- `part` never appears in `s` → return `s` unchanged.
- `part` equals `s` → return empty string.
- Removing `part` creates a new occurrence → handled naturally by the stack approach.