# 1700. Number of Students Unable to Eat Lunch

**Difficulty:** Easy
**Day:** 67
**Topics:** Array, Stack, Queue, Simulation

## Problem Description

The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers `0` and `1` respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a **stack**. At each step:

- If the student at the front of the queue **prefers** the sandwich on the top of the stack, they will **take it** and leave the queue.
- Otherwise, they will **leave it** and go to the queue's end.

This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays `students` and `sandwiches` where `sandwiches[i]` is the type of the `ith` sandwich in the stack (`i = 0` is the top of the stack) and `students[j]` is the preference of the `jth` student in the initial queue (`j = 0` is the front of the queue). Return the **number of students that are unable to eat**.

## Examples

### Example 1:

**Input:**
```
students = [1,1,0,0]
sandwiches = [0,1,0,1]
```

**Output:**
```
0
```

**Explanation:**
```
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.
```

### Example 2:

**Input:**
```
students = [1,1,1,0,0,1]
sandwiches = [1,0,0,0,1,1]
```

**Output:**
```
3
```

## Constraints

- `1 <= students.length, sandwiches.length <= 100`
- `students.length == sandwiches.length`
- `students[i]` is `0` or `1`.
- `sandwiches[i]` is `0` or `1`.

## Topics

- Array
- Stack
- Queue
- Simulation
- Counting

## Similar Problems

- [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/) - Easy
- [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/) - Easy
- [622. Design Circular Queue](https://leetcode.com/problems/design-circular-queue/) - Medium
- [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/) - Easy
- [346. Moving Average from Data Stream](https://leetcode.com/problems/moving-average-from-data-stream/) - Easy

## Related Topics Problems

**Queue Simulation:**
- [641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque/) - Medium
- [649. Dota2 Senate](https://leetcode.com/problems/dota2-senate/) - Medium
- [950. Reveal Cards In Increasing Order](https://leetcode.com/problems/reveal-cards-in-increasing-order/) - Medium

**Counting Techniques:**
- [1512. Number of Good Pairs](https://leetcode.com/problems/number-of-good-pairs/) - Easy
- [1941. Check if All Characters Have Equal Number of Occurrences](https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/) - Easy

**Stack/Queue Problems:**
- [682. Baseball Game](https://leetcode.com/problems/baseball-game/) - Easy
- [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) - Medium
- [844. Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/) - Easy

---

|**Previous:** [Day 66](../Day_66/) | **Next:** [Day 68](../Day_68/)|
|---|---|

|**Date:** March 08, 2026|
|---|