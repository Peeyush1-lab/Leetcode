# Notes - 3652. Best Time to Buy and Sell Stock using Strategy

## Intuition

The base profit without any modification is `Σ prices[i] * strategy[i]`. A modification replaces a `k`-length window: the first `k/2` days become `0` (hold) and the last `k/2` days become `1` (sell).

The profit **change** from applying a modification at window ending at index `i` is:

```
Δ = -(original profit of the window) + (sum of prices in last k/2 days of window)
  = -(profitSum[i+1] - profitSum[i-k+1]) + (priceSum[i+1] - priceSum[i-k/2+1])
```

We want to maximize `baseProfit + Δ` over all valid windows.

---

## Approach — Prefix Sums

- Build two prefix sum arrays:
  - `profitSum[i]` = sum of `prices[j] * strategy[j]` for `j < i`
  - `priceSum[i]` = sum of `prices[j]` for `j < i`
- Start with `res = profitSum[n]` (no modification).
- For each valid window right endpoint `i` (from `k-1` to `n-1`):
  - Compute `leftProfit` = profit before the window.
  - Compute `rightProfit` = profit after the window.
  - Compute `changeProfit` = sum of prices in the last `k/2` positions of the window.
  - Update `res` with `leftProfit + changeProfit + rightProfit`.

---

## Complexity

| | Value |
|---|---|
| Time | O(n) — one prefix sum pass + one enumeration pass |
| Space | O(n) — two prefix sum arrays |

---

## Key Observations

- The modification only affects the `k`-length window — outside the window the profit stays the same, so we can precompute left and right profit contributions using prefix sums.
- The first `k/2` days of the window get strategy `0`, contributing `0` profit regardless of price. So the new profit for the whole window is just the sum of prices in the **last `k/2` days** (each with strategy `1`).
- `res` is initialized to `profitSum[n]` to handle the case where no modification improves the profit.
- `long` is necessary — with `n = 10^5` and prices up to `10^4`, the max profit can reach ~`10^9`, which overflows `int`.

---

## Edge Cases

- Modification doesn't help → result equals the original profit.
- `k == n` → only one window to consider.
- All strategies already optimal → no modification gives a better result.

---

## Problem Link
[3652. Best Time to Buy and Sell Stock using Strategy](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/)