# 636. Exclusive Time of Functions

**Difficulty:** Medium
**Day:** 65
**Topics:** Array, Stack, Simulation

## Problem Description

On a **single-threaded** CPU, we execute a program containing `n` functions. Each function has a unique ID between `0` and `n-1`.

Function calls are stored in a call stack: when a function starts, its ID is pushed onto the stack, and when a function ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list `logs`, where `logs[i]` represents the ith log message formatted as a string `"{function_id}:{"start" | "end"}:{timestamp}"`. For example, `"0:start:3"` means a function call with function ID `0` **started at the beginning** of timestamp `3`, and `"1:end:2"` means a function call with function ID `1` **ended at the end** of timestamp `2`. Note that a function can be called **multiple times, possibly recursively**.

A function's **exclusive time** is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for `2` time units and another call executing for `1` time unit, the **exclusive time** is `2 + 1 = 3`.

Return the **exclusive time** of each function in an array, where the value at the ith index represents the exclusive time for the function with ID `i`.

## Examples

### Example 1:

![Example 1 Diagram](https://assets.leetcode.com/uploads/2019/04/05/diag1b.png)

**Input:**
```
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
```

**Output:**
```
[3,4]
```

**Explanation:**
```
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
```

### Example 2:

**Input:**
```
n = 1
logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
```

**Output:**
```
[8]
```

**Explanation:**
```
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.
Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
```

### Example 3:

**Input:**
```
n = 2
logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
```

**Output:**
```
[7,1]
```

### Example 4:

**Input:**
```
n = 2
logs = ["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
```

**Output:**
```
[8,1]
```

## Constraints

- `1 <= n <= 100`
- `1 <= logs.length <= 500`
- `0 <= function_id < n`
- `0 <= timestamp <= 10^9`
- No two start events will happen at the same timestamp.
- No two end events will happen at the same timestamp.
- Each function has an `"end"` log for each `"start"` log.

## Topics

- Array
- Stack
- Simulation
- String Parsing

## Similar Problems

- [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) - Medium
- [394. Decode String](https://leetcode.com/problems/decode-string/) - Medium
- [772. Basic Calculator III](https://leetcode.com/problems/basic-calculator-iii/) - Hard
- [726. Number of Atoms](https://leetcode.com/problems/number-of-atoms/) - Hard
- [1541. Minimum Insertions to Balance a Parentheses String](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/) - Medium

## Related Topics Problems

**Stack Simulation:**
- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Easy
- [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) - Medium
- [224. Basic Calculator](https://leetcode.com/problems/basic-calculator/) - Hard
- [682. Baseball Game](https://leetcode.com/problems/baseball-game/) - Easy

**Time Interval Problems:**
- [56. Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Medium
- [57. Insert Interval](https://leetcode.com/problems/insert-interval/) - Medium
- [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Medium
- [731. My Calendar II](https://leetcode.com/problems/my-calendar-ii/) - Medium

**String Parsing:**
- [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) - Medium
- [385. Mini Parser](https://leetcode.com/problems/mini-parser/) - Medium
- [439. Ternary Expression Parser](https://leetcode.com/problems/ternary-expression-parser/) - Medium

---

|**Previous:** [Day 64](../Day_64/) | **Next:** [Day 66](../Day_66/)|
|---|---|

|**Date:** March 06, 2026|
|---|