# Day 99 - 3577. Count the Number of Computer Unlocking Permutations

**Difficulty:** Medium
**Topic Tags:** Array, Math, Combinatorics

---

## Problem Statement

You are given an array `complexity` of length `n`, where `complexity[i]` represents the complexity of the `i-th` computer. Computer `0` is initially unlocked.

A computer `i` can be unlocked if there exists a previously unlocked computer `j` such that `complexity[j] < complexity[i]`.

Return the number of permutations of computers `1` to `n-1` that can all be unlocked starting from computer `0`. Since the answer may be large, return it **modulo `10^9 + 7`**.

If it is impossible to unlock all computers, return `0`.

---

## Examples

**Example 1:**
```
Input:  complexity = [1, 2, 3]
Output: 2

Explanation: Both orderings [1,2] and [2,1] work since complexity[0]=1
             is less than all others.
```

**Example 2:**
```
Input:  complexity = [2, 2, 3]
Output: 0

Explanation: complexity[1] = 2 = complexity[0], so computer 1 can never
             be unlocked. Return 0.
```

**Example 3:**
```
Input:  complexity = [1, 3, 2, 4]
Output: 6

Explanation: All 3! = 6 permutations of computers 1,2,3 are valid.
```

---

## Constraints

- `2 <= n <= 10^5`
- `1 <= complexity[i] <= 10^6`

---

|**Previous:** [Day 98](../Day_98/) | **Next:** [Day 100](../Day_100/)|
|---|---|

|**Date:** April 10, 2026|
|---|