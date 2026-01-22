# Day 22 - Detailed Notes

## Problem 1: Build an Array With Stack Operations (1441)

### Problem Statement
Given an array `target` and an integer `n`, build the target array using push and pop operations on an initially empty stack. Return the operations needed.

### Approach
**Strategy:** Simulate the stack operations
- Iterate from 1 to the maximum value in target
- If current number matches target element → Push
- If current number doesn't match → Push then Pop
- Track position in target array with index

### Complexity Analysis
- **Time Complexity:** O(m) where m = target[target.length - 1]
- **Space Complexity:** O(1) excluding output list

### Key Insights
- No need to iterate beyond the last target element
- Push+Pop effectively skips unwanted numbers
- Index tracking is crucial for matching target elements

---

## Daily Reflection

### What Went Well
- Successfully solved Problem 1441 with clean logic
- Understanding of array index manipulation improved

### Tomorrow's Focus
- Complete Problem 442
- Review in-place array modification techniques