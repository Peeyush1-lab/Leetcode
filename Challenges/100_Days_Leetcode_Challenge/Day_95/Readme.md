# 657. Robot Return to Origin

**Difficulty:** Easy
**Day:** 95
**Topics:** String, Simulation

## Problem Description

There is a robot starting at the position `(0, 0)`, the origin, on a 2D plane. Given a sequence of its moves, judge if this robot **ends up at** `(0, 0)` after it completes its moves.

You are given a string `moves` that represents the move sequence of the robot where `moves[i]` represents its `ith` move. Valid moves are `'R'` (right), `'L'` (left), `'U'` (up), and `'D'` (down).

Return `true` if the robot returns to the origin after it finishes all of its moves, or `false` otherwise.

**Note:** The way that the robot is "facing" is irrelevant. `'R'` will always make the robot move to the right once, `'L'` will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.

## Examples

### Example 1:

**Input:**
```
moves = "UD"
```

**Output:**
```
true
```

**Explanation:**
```
The robot moves up once, and then down once. All moves have the same magnitude, so it ended up at the origin where it started. Therefore, we return true.
```

### Example 2:

**Input:**
```
moves = "LL"
```

**Output:**
```
false
```

**Explanation:**
```
The robot moves left twice. It ends up two "moves" to the left of the origin. We return false because it is not at the origin at the end of its moves.
```

## Constraints

- `1 <= moves.length <= 2 * 10⁴`
- `moves` only contains the characters `'U'`, `'D'`, `'L'` and `'R'`.

## Topics

- String
- Simulation

## Similar Problems

- [1041. Robot Bounded In Circle](https://leetcode.com/problems/robot-bounded-in-circle/) - Medium
- [1266. Minimum Time Visiting All Points](https://leetcode.com/problems/minimum-time-visiting-all-points/) - Easy
- [2731. Movement of Robots](https://leetcode.com/problems/movement-of-robots/) - Medium

## Related Topics Problems

**Simulation:**
- [54. Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) - Medium
- [59. Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/) - Medium
- [73. Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) - Medium
- [289. Game of Life](https://leetcode.com/problems/game-of-life/) - Medium
- [498. Diagonal Traverse](https://leetcode.com/problems/diagonal-traverse/) - Medium

**String Processing:**
- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Easy
- [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) - Easy
- [344. Reverse String](https://leetcode.com/problems/reverse-string/) - Easy
- [387. First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/) - Easy

---

|**Previous:** [Day 94](../Day_94/) | **Next:** [Day 96](../Day_96/)|
|---|---|

|**Date:** April 06, 2026|
|---|