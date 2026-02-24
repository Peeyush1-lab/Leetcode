# 1700. Number of Students Unable to Eat Lunch

**Difficulty:** Easy

## Problem Description

The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers `0` and `1` respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a **stack**. At each step:

- If the student at the front of the queue **prefers** the sandwich on the top of the stack, they will **take it** and leave the queue.
- Otherwise, they will **leave it** and go to the queue's end.

This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays `students` and `sandwiches` where `sandwiches[i]` is the type of the `i`th sandwich in the stack (`i = 0` is the top of the stack) and `students[j]` is the preference of the `j`th student in the initial queue (`j = 0` is the front of the queue). Return the **number of students that are unable to eat**.

## Examples

### Example 1:
```
Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0
Explanation:
- Front student leaves the top sandwich and goes to the end, queue [1,0,0,1]
- Front student leaves the top sandwich and goes to the end, queue [0,0,1,1]
- Front student takes the top sandwich and leaves, queue [0,1,1]
  Stack [1,0,1]
- Front student leaves the top sandwich and goes to the end, queue [1,1,0]
- Front student takes the top sandwich and leaves, queue [1,0]
  Stack [0,1]
- Front student leaves the top sandwich and goes to the end, queue [0,1]
- Front student takes the top sandwich and leaves, queue [1]
  Stack [1]
- Front student takes the top sandwich and leaves, queue []
  Stack []
Hence all students are able to eat.
```

### Example 2:
```
Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3
```

## Constraints

- `1 <= students.length, sandwiches.length <= 100`
- `students.length == sandwiches.length`
- `sandwiches[i]` is `0` or `1`.
- `students[i]` is `0` or `1`.

## Topics
- Array
- Stack
- Queue
- Simulation

## Solution Approach

This solution uses a **counting optimization** instead of simulation:

### Key Insight

Students can rearrange themselves infinitely in the queue. What matters is:
- **Can we satisfy the top sandwich?**
- If yes, serve it
- If no (no students want it), everyone left is stuck

### Algorithm

1. **Count student preferences:** Count how many want `0` and how many want `1`
2. **Process sandwiches in order:**
   - For each sandwich, check if any student wants it
   - If yes: decrease that preference count
   - If no: all remaining students are stuck, return the count
3. **All served:** Return 0

**Why this works:** Students can cycle through the queue, so order doesn't matter - only counts matter!

**Time Complexity:** O(n) - Two passes through arrays
**Space Complexity:** O(1) - Only two counter variables

## Related Problems

- [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) (Easy) - Queue/Stack fundamentals
- [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/) (Easy) - Queue/Stack implementation
- [622. Design Circular Queue](https://leetcode.com/problems/design-circular-queue/) (Medium) - Queue design
- [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/) (Easy) - Queue application
- [950. Reveal Cards In Increasing Order](https://leetcode.com/problems/reveal-cards-in-increasing-order/) (Medium) - Queue simulation

|**Previous:** [Day 54](../Day_54/) | **Next:** [Day 56](../Day_56/)|
|---|---|

|**Date:** February 24, 2026|
|---|