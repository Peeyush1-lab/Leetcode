# Notes - 1695. Maximum Erasure Value

## Intuition

We need the maximum sum subarray with all unique elements. This is a classic variable-size sliding window problem — expand the window greedily and shrink it only when a duplicate is detected.

---

## Approach — Sliding Window + HashSet

- Maintain a window `[start, end]` where all elements are unique.
- Use a `HashSet` to track which elements are currently in the window.
- Also track `currentSum` — the sum of elements inside the window.
- When `nums[end]` is already in the set, shrink from the left: remove `nums[start]`, subtract it from `currentSum`, advance `start` — until the duplicate is gone.
- Add `nums[end]` to the set and update `currentSum`, then update `result`.

---

## Complexity

| | Value |
|---|---|
| Time | O(n) — each element is added and removed from the set at most once |
| Space | O(n) — HashSet holds at most n elements |

---

## Key Observations

- Tracking `currentSum` alongside the window avoids recomputing the sum from scratch on each step — keeps it O(1) per move.
- The `while` loop shrinks until the duplicate is fully evicted, which may take multiple steps if the duplicate is far from `start`.
- This is essentially the same pattern as **Longest Substring Without Repeating Characters** (LC 3), just maximizing sum instead of length.

---

## Edge Cases

- All elements unique → the entire array is the answer.
- All elements identical → every single element is its own valid subarray; answer is `max(nums)`.
- Single element → return that element.

---

## Problem Link
[1695. Maximum Erasure Value](https://leetcode.com/problems/maximum-erasure-value/)