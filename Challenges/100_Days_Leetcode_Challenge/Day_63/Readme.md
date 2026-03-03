# 2211. Count Collisions on a Road

**Difficulty:** Medium
**Day:** 63
**Topics:** String, Stack, Simulation

## Problem Description

There are `n` cars on an infinitely long road. The cars are numbered from `0` to `n - 1` from left to right and each car is present at a **unique** point.

You are given a **0-indexed** string `directions` of length `n`. `directions[i]` can be either `'L'`, `'R'`, or `'S'` denoting whether the ith car is moving towards the **left**, towards the **right**, or **staying** at its current point respectively. Each moving car has the **same speed**.

The number of collisions can be calculated as follows:

- When two cars moving in **opposite** directions collide with each other, the number of collisions increases by `2`.
- When a moving car collides with a stationary car, the number of collisions increases by `1`.

After a collision, the cars involved can no longer move and will stay at the point where they collided. Other than that, cars cannot change their state or direction of motion.

Return the **total number of collisions** that will happen on the road.

## Examples

### Example 1:

**Input:**
```
directions = "RLRSLL"
```

**Output:**
```
5
```

**Explanation:**
```
The collisions that will happen on the road are:
- Cars 0 and 1 collide, and since they are moving in opposite directions, the number of collisions becomes 0 + 2 = 2.
- Cars 2 and 3 collide, and since car 3 is stationary, the number of collisions becomes 2 + 1 = 3.
- Cars 3 and 4 collide, and since car 3 is stationary, the number of collisions becomes 3 + 1 = 4.
- Cars 4 and 5 collide, and since car 4 is stationary, the number of collisions becomes 4 + 1 = 5.
```

### Example 2:

**Input:**
```
directions = "LLRR"
```

**Output:**
```
0
```

**Explanation:**
```
No cars will collide with each other. So, the total number of collisions is 0.
```

## Constraints

- `1 <= directions.length <= 10⁵`
- `directions[i]` is either `'L'`, `'R'`, or `'S'`.

## Topics

- String
- Stack
- Simulation
- Two Pointers

## Similar Problems

- [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) - Medium
- [682. Baseball Game](https://leetcode.com/problems/baseball-game/) - Easy
- [844. Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/) - Easy
- [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences/) - Medium
- [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) - Easy
- [1544. Make The String Great](https://leetcode.com/problems/make-the-string-great/) - Easy

## Related Topics Problems

**Stack Simulation:**
- [150. Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) - Medium
- [394. Decode String](https://leetcode.com/problems/decode-string/) - Medium
- [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) - Medium

**Two Pointers:**
- [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) - Medium
- [344. Reverse String](https://leetcode.com/problems/reverse-string/) - Easy
- [977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/) - Easy

---

|**Previous:** [Day 62](../Day_62/) | **Next:** [Day 64](../Day_64/)|
|---|---|

|**Date:** March 04, 2026|
|---|