# Notes - 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

## Intuition

Since every subarray has the same fixed size `k`, instead of computing the average directly, we can compare the **sum** against `threshold * k`. This avoids floating point and keeps everything in integer arithmetic.

---

## Approach — Fixed-Size Sliding Window

- Seed the first window by summing the first `k` elements.
- Check if that window qualifies, then slide one element at a time: subtract the outgoing left element, add the incoming right element.
- After each slide, check the new sum against `threshold * k` and increment count if valid.

---

## Complexity

| | Value |
|---|---|
| Time | O(n) — single pass after the initial seed |
| Space | O(1) — only a few variables |

---

## Key Observations

- `sum >= threshold * k` is equivalent to `sum / k >= threshold` but avoids integer division truncation issues. For example, `sum=7, k=3, threshold=3` → `7/3 = 2` (truncated), which incorrectly fails — but `7 >= 9` correctly fails too. In this case both agree, but using `sum >= threshold * k` is always safer and more precise.
- The two-pointer style here (`i` and `j` as separate variables) is a clean alternative to the single-loop `right - k` index pattern — both are correct.
- This is the simplest form of a fixed-size sliding window: no shrinking logic needed since the window size never changes.

---

## Edge Cases

- `k == arr.length` → only one window to check.
- All elements equal to `threshold` → every window qualifies.
- `threshold == 0` → all subarrays qualify since elements are positive.

---

## Problem Link
[1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/)