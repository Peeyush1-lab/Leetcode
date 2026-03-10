# 2073. Time Needed to Buy Tickets

**Difficulty:** Easy
**Day:** 68
**Topics:** Array, Queue, Simulation

## Problem Description

There are `n` people in a line queuing to buy tickets, where the `0th` person is at the **front** of the line and the `(n - 1)th` person is at the **back** of the line.

You are given a **0-indexed** integer array `tickets` of length `n` where the number of tickets that the `ith` person would like to buy is `tickets[i]`.

Each person takes **exactly 1 second** to buy a ticket. A person can only buy **1 ticket at a time** and has to go back to **the end of the line** (which happens **instantaneously**) in order to buy more tickets. If a person does not have any more tickets to buy, the person will **leave** the line.

Return the **time taken** for the person at position `k` **(0-indexed)** to finish buying tickets.

## Examples

### Example 1:

**Input:**
```
tickets = [2,3,2], k = 2
```

**Output:**
```
6
```

**Explanation:**
```
- In the first pass, everyone in the line buys a ticket and the line becomes [1, 2, 1].
- In the second pass, everyone in the line buys a ticket and the line becomes [0, 1, 0].
- The person at position 2 has successfully bought 2 tickets and it took 3 + 3 = 6 seconds.
```

### Example 2:

**Input:**
```
tickets = [5,1,1,1], k = 0
```

**Output:**
```
8
```

**Explanation:**
```
- In the first pass, everyone in the line buys a ticket and the line becomes [4, 0, 0, 0].
- In the next 4 passes, only the person in position 0 is buying tickets.
- The person at position 0 has successfully bought 5 tickets and it took 4 + 1 + 1 + 1 + 1 = 8 seconds.
```

## Constraints

- `n == tickets.length`
- `1 <= n <= 100`
- `1 <= tickets[i] <= 100`
- `0 <= k < n`

## Topics

- Array
- Queue
- Simulation
- Math

## Similar Problems

- [950. Reveal Cards In Increasing Order](https://leetcode.com/problems/reveal-cards-in-increasing-order/) - Medium
- [1700. Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/) - Easy
- [2335. Minimum Amount of Time to Fill Cups](https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/) - Easy
- [649. Dota2 Senate](https://leetcode.com/problems/dota2-senate/) - Medium

## Related Topics Problems

**Queue Simulation:**
- [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) - Easy
- [622. Design Circular Queue](https://leetcode.com/problems/design-circular-queue/) - Medium
- [641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque/) - Medium
- [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/) - Easy

**Mathematical Optimization:**
- [1523. Count Odd Numbers in an Interval Range](https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/) - Easy
- [1154. Day of the Year](https://leetcode.com/problems/day-of-the-year/) - Easy
- [1716. Calculate Money in Leetcode Bank](https://leetcode.com/problems/calculate-money-in-leetcode-bank/) - Easy

**Array Processing:**
- [1572. Matrix Diagonal Sum](https://leetcode.com/problems/matrix-diagonal-sum/) - Easy
- [1480. Running Sum of 1d Array](https://leetcode.com/problems/running-sum-of-1d-array/) - Easy
- [1920. Build Array from Permutation](https://leetcode.com/problems/build-array-from-permutation/) - Easy

---

|**Previous:** [Day 67](../Day_67/) | **Next:** [Day 69](../Day_69/)|
|---|---|

|**Date:** March 09, 2026|
|---|