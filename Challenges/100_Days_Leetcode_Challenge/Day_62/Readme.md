# 682. Baseball Game

**Difficulty:** Easy
**Topics:** Array, Stack, Simulation

## Problem Description

You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.

You are given a list of strings `operations`, where `operations[i]` is the ith operation you must apply to the record and is one of the following:

- An integer `x`: Record a new score of `x`.
- `'+'`: Record a new score that is the sum of the previous two scores.
- `'D'`: Record a new score that is the double of the previous score.
- `'C'`: Invalidate the previous score, removing it from the record.

Return the sum of all the scores on the record after applying all the operations.

The test cases are generated such that the answer and all intermediate calculations fit in a **32-bit** integer and that all operations are valid.

## Examples

### Example 1:

**Input:**
```
operations = ["5","2","C","D","+"]
```

**Output:**
```
30
```

**Explanation:**
```
"5" - Add 5 to the record, record is now [5].
"2" - Add 2 to the record, record is now [5, 2].
"C" - Invalidate and remove the previous score, record is now [5].
"D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
```

### Example 2:

**Input:**
```
operations = ["5","-2","4","C","D","9","+","+"]
```

**Output:**
```
27
```

**Explanation:**
```
"5" - Add 5 to the record, record is now [5].
"-2" - Add -2 to the record, record is now [5, -2].
"4" - Add 4 to the record, record is now [5, -2, 4].
"C" - Invalidate and remove the previous score, record is now [5, -2].
"D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
"9" - Add 9 to the record, record is now [5, -2, -4, 9].
"+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
"+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
```

### Example 3:

**Input:**
```
operations = ["1","C"]
```

**Output:**
```
0
```

**Explanation:**
```
"1" - Add 1 to the record, record is now [1].
"C" - Invalidate and remove the previous score, record is now [].
Since the record is empty, the total sum is 0.
```

## Constraints

- `1 <= operations.length <= 1000`
- `operations[i]` is `"C"`, `"D"`, `"+"`, or a string representing an integer in the range `[-3 * 10⁴, 3 * 10⁴]`.
- For operation `"+"`, there will always be at least two previous scores on the record.
- For operations `"C"` and `"D"`, there will always be at least one previous score on the record.

## Topics

- Array
- Stack
- Simulation

## Similar Problems

- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Easy
- [155. Min Stack](https://leetcode.com/problems/min-stack/) - Medium
- [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) - Medium
- [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/) - Easy
- [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) - Medium
- [1021. Remove Outermost Parentheses](https://leetcode.com/problems/remove-outermost-parentheses/) - Easy

## Related Topics Problems

**Stack Problems:**
- [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Hard
- [394. Decode String](https://leetcode.com/problems/decode-string/) - Medium
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Medium

**Simulation Problems:**
- [657. Robot Return to Origin](https://leetcode.com/problems/robot-return-to-origin/) - Easy
- [1041. Robot Bounded In Circle](https://leetcode.com/problems/robot-bounded-in-circle/) - Medium

|**Previous:** [Day 61](../Day_61/) | **Next:** [Day 63](../Day_63/)|
|---|---|

|**Date:** March 03, 2026|
|---|