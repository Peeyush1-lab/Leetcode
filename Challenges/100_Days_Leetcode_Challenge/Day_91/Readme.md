# 888. Fair Candy Swap

**Difficulty:** Easy
**Day:** 91
**Topics:** Array, Hash Table, Binary Search, Sorting

## Problem Description

Alice and Bob have a different total number of candies. You are given two integer arrays `alice` and `bob` where `alice[i]` is the `ith` candy that Alice has and `bob[j]` is the `jth` candy that Bob has.

Since they are friends, they would like to exchange one candy box each so that after the exchange, they both have the **same** total amount of candy. The total amount of candy a person has is the sum of the number of candies in each box they have.

Return an integer array `answer` where `answer[0]` is the number of candies in the box that Alice must exchange, and `answer[1]` is the number of candies in the box that Bob must exchange. If there are multiple answers, you may **return any** one of them. It is guaranteed that at least one answer exists.

## Examples

### Example 1:

**Input:**
```
alice = [1,1], bob = [2,2]
```

**Output:**
```
[1,2]
```

**Explanation:**
```
Alice swaps a box with 1 candy with Bob's box with 2 candies.
Total candies:
- Alice: 1 - 1 + 2 = 2
- Bob: 2 + 2 - 2 + 1 = 3 (wait, this doesn't work...)

Actually:
- Alice has: 1 + 1 = 2 candies
- Bob has: 2 + 2 = 4 candies
After swap (1 from Alice, 2 from Bob):
- Alice: 2 - 1 + 2 = 3 candies
- Bob: 4 - 2 + 1 = 3 candies
```

### Example 2:

**Input:**
```
alice = [1,2], bob = [2,3]
```

**Output:**
```
[1,2]
```

### Example 3:

**Input:**
```
alice = [2], bob = [1,3]
```

**Output:**
```
[2,3]
```

## Constraints

- `1 <= alice.length, bob.length <= 10⁴`
- `1 <= alice[i], bob[j] <= 10⁵`
- Alice and Bob have a different total number of candies.
- There will be at least one valid answer for the given input.

## Topics

- Array
- Hash Table
- Binary Search
- Sorting

## Similar Problems

- [1. Two Sum](https://leetcode.com/problems/two-sum/) - Easy
- [1099. Two Sum Less Than K](https://leetcode.com/problems/two-sum-less-than-k/) - Easy
- [1711. Count Good Meals](https://leetcode.com/problems/count-good-meals/) - Medium
- [2395. Find Subarrays With Equal Sum](https://leetcode.com/problems/find-subarrays-with-equal-sum/) - Easy

## Related Topics Problems

**Hash Table:**
- [349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/) - Easy
- [350. Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/) - Easy
- [1346. Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist/) - Easy

**Math + Array:**
- [453. Minimum Moves to Equal Array Elements](https://leetcode.com/problems/minimum-moves-to-equal-array-elements/) - Medium
- [462. Minimum Moves to Equal Array Elements II](https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/) - Medium
- [1877. Minimize Maximum Pair Sum in Array](https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/) - Medium

---

|**Previous:** [Day 90](../Day_90/) | **Next:** [Day 92](../Day_92/)|
|---|---|

|**Date:** April 02, 2026|
|---|