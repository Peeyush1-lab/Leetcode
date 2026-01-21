# Day 21: Daily Temperatures

## Problem Statement

Given an array of integers `temperatures` represents the daily temperatures, return an array `answer` such that `answer[i]` is the number of days you have to wait after the `i-th` day to get a warmer temperature. If there is no future day for which this is possible, keep `answer[i] == 0` instead.

## Examples

### Example 1
**Input:** `temperatures = [73,74,75,71,69,72,76,73]`
**Output:** `[1,1,4,2,1,1,0,0]`
**Explanation:**
- Day 0: 73 → Day 1: 74 (warmer) → Wait 1 day
- Day 1: 74 → Day 2: 75 (warmer) → Wait 1 day
- Day 2: 75 → Day 6: 76 (warmer) → Wait 4 days
- Day 6: 76 → No warmer day → 0

### Example 2
**Input:** `temperatures = [30,40,50,60]`
**Output:** `[1,1,1,0]`

### Example 3
**Input:** `temperatures = [30,60,90]`
**Output:** `[1,1,0]`

## Constraints

- `1 <= temperatures.length <= 10^5`
- `30 <= temperatures[i] <= 100`

## Key Points

- For each day, find next warmer day
- Return number of days to wait
- Stack stores indices of days
- Monotonic decreasing stack pattern

## Approach

**Strategy:** Monotonic Stack

1. Use stack to store indices of unresolved days
2. For each day, pop all days with lower temperature
3. Calculate wait time as index difference
4. Push current day's index to stack

**Complexity:**
- Time: O(n) - Each element pushed/popped once
- Space: O(n) - Stack storage