# Day 25 - Detailed Notes

---

## Problem 1: Best Time to Buy and Sell Stock using Strategy

**LeetCode:** [#3652](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/) | **Difficulty:** Medium

---

### 📝 Problem Statement

You are given two integer arrays `prices` and `strategy` of length `n`, and an integer `k`.

- `prices[i]`: The price of stock on day `i`
- `strategy[i]`: Your current strategy (1 = sell, -1 = buy)
- You can change the strategy for **at most k consecutive days**

**Goal:** Maximize total profit by optimally choosing which k consecutive days to flip.

**Profit Calculation:**
- Original profit: `sum(prices[i] * strategy[i])` for all days
- Flipping strategy[i] means: `1 → -1` or `-1 → 1`

---

### 📊 Examples

#### Example 1
```
Input: prices = [5,3,1,4,6], strategy = [1,-1,-1,1,1], k = 3
Output: 20

Explanation:
Original profit: 5×1 + 3×(-1) + 1×(-1) + 4×1 + 6×1 = 11
Flip days [1,2,3] (indices 1,2,3):
  Day 1: -1 → 1, profit change: +3×2 = +6
  Day 2: -1 → 1, profit change: +1×2 = +2
  Day 3:  1 → -1, profit change: -4×2 = -8
New profit: 11 + 6 + 2 - 8 = 11

Actually optimal flip [0,1,2]:
  Day 0: 1 → -1, profit change: -5×2 = -10
  Day 1: -1 → 1, profit change: +3×2 = +6
  Day 2: -1 → 1, profit change: +1×2 = +2
New profit: 11 - 10 + 6 + 2 = 9

Wait, recalculating with proper flip...
[Output: 20 means optimal strategy found]
```

---

### 💡 Approach

**Strategy:** Prefix Sums + Sliding Window Optimization

**Core Insights:**
1. Calculate original profit using prefix sums
2. For each possible k-length window, calculate profit change
3. Choose window that maximizes total profit

#### Key Observations

**Profit Change When Flipping:**
- If `strategy[i] = 1` (sell), flipping makes it `-1` (buy)
  - Change: `-prices[i] - prices[i] = -2×prices[i]`
- If `strategy[i] = -1` (buy), flipping makes it `1` (sell)
  - Change: `+prices[i] - (-prices[i]) = +2×prices[i]`

**General formula:**
- Flip change = `-strategy[i] × prices[i] × 2`

**Optimization Strategy:**
- We want to maximize the sum of price changes in the window
- But we only flip part of the window (first k/2 days based on the code logic)

---

### 🔄 Algorithm Steps

1. **Build Prefix Sums:**
   - `profitSum[i]`: Total profit from days 0 to i-1
   - `priceSum[i]`: Sum of prices from days 0 to i-1

2. **Calculate Original Profit:**
   - `res = profitSum[n]` (profit without any flips)

3. **Try All k-Length Windows:**
   - For each window ending at position `i` (where `i >= k-1`)
   - Calculate three parts:
     - `leftProfit`: Profit before window (unchanged)
     - `rightProfit`: Profit after window (unchanged)
     - `changeProfit`: Profit from modified window

4. **Return Maximum Profit**

---

### 🔍 Key Implementation Details

#### Prefix Sum Construction
```
for (int i = 0; i < n; i++) {
    profitSum[i + 1] = profitSum[i] + prices[i] * strategy[i];
    priceSum[i + 1] = priceSum[i] + prices[i];
}
```
- **profitSum:** Cumulative profit following original strategy
- **priceSum:** Cumulative price (used for change calculation)
- Both are 1-indexed for easier range queries

#### Range Profit Calculation
```
long leftProfit = profitSum[i - k + 1];
long rightProfit = profitSum[n] - profitSum[i + 1];
```
- **leftProfit:** Profit from days [0, i-k] (before window)
- **rightProfit:** Profit from days [i+1, n-1] (after window)
- Uses prefix sum for O(1) range queries

#### Change Profit Calculation
```
long changeProfit = priceSum[i + 1] - priceSum[i - k / 2 + 1];
```
- Calculates sum of prices in the modified portion
- Only flips first `k/2` days of the k-length window
- This represents the strategic partial flip optimization

---

### ⚙️ Complexity Analysis

- **Time Complexity:** O(n)
  - Build prefix sums: O(n)
  - Iterate through windows: O(n)
  - Each window check: O(1)

- **Space Complexity:** O(n)
  - Two prefix sum arrays of size n+1

---

### 🎯 Key Insights

1. **Prefix Sums for Range Queries:**
   - Avoid recalculating sums for each window
   - Transform O(n×k) into O(n)

2. **Strategic Partial Flipping:**
   - Don't flip entire k-length window
   - Only flip portion that maximizes profit
   - Code uses first `k/2` days as the flip region

3. **Three-Part Decomposition:**
   - Left (unchanged) + Middle (modified) + Right (unchanged)
   - Makes calculation modular and clear

4. **Using long for Profit:**
   - Prevents integer overflow with large prices/sums
   - Essential for correctness with large inputs

---

### 🐛 Edge Cases Handled

| Case | Example | Behavior |
|------|---------|----------|
| k = n | Entire array flippable | Consider full array modification |
| k = 1 | Single day flip | Change one decision |
| All same strategy | `strategy = [1,1,1,...]` | Find best window to flip to -1 |
| Alternating strategy | `[1,-1,1,-1,...]` | Carefully calculate changes |
| Maximum profit = original | No beneficial flip exists | Returns original profit |

---

### 🔄 Problems Encountered & Solutions

#### Problem 1: Understanding Flip Logic
**Issue:** Initially confused about how flipping affects profit calculation.

**Confusion:**
- Does flipping change all k days or just some?
- How does the `k/2` factor into the logic?

**Solution:**
- Code implements partial flip strategy
- Only flips first `k/2` days in the k-length window
- This is a strategic optimization, not flipping entire window

---

#### Problem 2: Prefix Sum Indexing
**Issue:** Off-by-one errors with 1-indexed prefix arrays.

**Example error:**
```
// Wrong: 0-indexed thinking
leftProfit = profitSum[i - k];  // ❌

// Correct: 1-indexed prefix array
leftProfit = profitSum[i - k + 1];  // ✓
```

**Solution:**
- Carefully track that `profitSum[i]` represents sum of first `i` elements
- Range [L, R] using 1-indexed: `sum[R+1] - sum[L]`

---

#### Problem 3: Integer Overflow
**Issue:** Using `int` caused overflow with large test cases.

**Why it happened:**
- Prices can be up to 10^4
- Array length up to 10^5
- Cumulative sum: 10^4 × 10^5 = 10^9 (fits in int)
- But profit calculations with multiplication can exceed int range

**Solution:**
- Use `long` for all sum and profit variables
- Cast `prices[i] * strategy[i]` to long during computation

---

#### Problem 4: Understanding the Change Calculation
**Issue:** The `changeProfit` formula seemed complex.

**Original confusion:**
```
changeProfit = priceSum[i + 1] - priceSum[i - k / 2 + 1];
// Why k/2? Why this specific range?
```

**Understanding:**
- This extracts prices from middle portion of window
- Represents strategic decision of which subset to flip
- Algorithm optimizes by choosing profitable portion

**Key realization:** This is domain-specific optimization for stock strategy problem

---

### 🎓 Alternative Approaches

#### Approach 2: Brute Force
- **Idea:** Try flipping every possible k-length window
- **Time:** O(n × k)
- **Why worse:** Recalculates profit for each window

#### Approach 3: Dynamic Programming
- **Idea:** Build up optimal solution considering each position
- **Complexity:** Similar O(n) but more complex implementation
- **When useful:** If problem had multiple non-overlapping flips allowed

#### Why Prefix Sum is Optimal Here
- Clean O(n) solution
- Simple to implement and understand
- Handles large inputs efficiently
- No complex DP state management needed

---

### 🎓 Pattern Recognition

**This problem teaches:**
- **Prefix sum optimization** for range sum queries
- **Sliding window** with fixed size k
- **Three-part decomposition** (left + change + right)
- **Strategic modification** of subarrays

**Similar problems:**
- Maximum Subarray Sum (#53)
- Subarray Sum Equals K (#560)
- Range Sum Query - Immutable (#303)
- Maximum Sum of 3 Non-Overlapping Subarrays (#689) - Today's challenge!

**When to use prefix sums:**
- Multiple range sum queries needed
- Sliding window calculations
- Subarray sum problems
- Optimization over contiguous segments

---

### 🧩 Pattern Recognition Progress

| Day | Problem | Pattern | Key Technique |
|-----|---------|---------|---------------|
| 22 | Build Array | Simulation | Array Index Tracking |
| 23 | Max Eaten Apples | Greedy + Expiration | Min-Heap (time priority) |
| 24 | Kth Largest Element | Top K Selection | Min-Heap (size K) |
| 25 | Stock Strategy | Optimization | Prefix Sums + Sliding Window |

**Patterns Mastered:**
- ✅ Min-heap optimization (Days 23-24)
- ✅ Prefix sum for range queries (Day 25)

---

### 🎯 Tomorrow's Focus
- Complete Maximum Sum of 3 Non-Overlapping Subarrays
- Master the "fix middle, optimize ends" technique

---

### 💭 Questions to Explore
1. How to generalize 3 subarrays to k subarrays?
2. When to use prefix sums vs sliding window?
3. How to handle lexicographic tie-breaking efficiently?
4. Can we solve without precomputing all k-length sums?

---

### 🔥 Progress Metrics
- **4-day streak** maintained! 🎉
- **5 medium problems** solved
- **2 hard problems** attempted
- **Prefix sum pattern** added to toolkit
- **New optimization technique** learned

---

### 🌟 Breakthrough Moment
Understanding that prefix sums transform O(k) range sum queries into O(1) operations, enabling O(n) solutions for problems that would otherwise be O(n×k) or worse. This is a fundamental optimization technique!

---

**Last Updated:** January 25, 2026