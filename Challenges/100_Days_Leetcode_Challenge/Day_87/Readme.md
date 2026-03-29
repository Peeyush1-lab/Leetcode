# 486. Predict the Winner

**Difficulty:** Medium
**Day:** 87
**Topics:** Array, Math, Dynamic Programming, Recursion, Game Theory

## Problem Description

You are given an integer array `nums`. Two players are playing a game with this array: player 1 and player 2.

Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of `0`. At each turn, the player takes one of the numbers from either end of the array (i.e., `nums[0]` or `nums[nums.length - 1]`) which reduces the size of the array by `1`. The player adds the chosen number to their score. The game ends when there are no more elements in the array.

Return `true` if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return `true`. You may assume that both players are playing optimally.

## Examples

### Example 1:

**Input:**
```
nums = [1,5,2]
```

**Output:**
```
false
```

**Explanation:**
```
Initially, player 1 can choose between 1 and 2.
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
Hence, player 1 will never be the winner and you need to return false.
```

### Example 2:

**Input:**
```
nums = [1,5,233,7]
```

**Output:**
```
true
```

**Explanation:**
```
Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return true representing player1 can win.
```

## Constraints

- `1 <= nums.length <= 20`
- `0 <= nums[i] <= 10⁷`

## Topics

- Array
- Math
- Dynamic Programming
- Recursion
- Game Theory
- Minimax

## Similar Problems

- [877. Stone Game](https://leetcode.com/problems/stone-game/) - Medium
- [1140. Stone Game II](https://leetcode.com/problems/stone-game-ii/) - Medium
- [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii/) - Hard
- [1510. Stone Game IV](https://leetcode.com/problems/stone-game-iv/) - Hard
- [1563. Stone Game V](https://leetcode.com/problems/stone-game-v/) - Hard
- [1686. Stone Game VI](https://leetcode.com/problems/stone-game-vi/) - Medium
- [1690. Stone Game VII](https://leetcode.com/problems/stone-game-vii/) - Medium
- [1872. Stone Game VIII](https://leetcode.com/problems/stone-game-viii/) - Hard
- [2029. Stone Game IX](https://leetcode.com/problems/stone-game-ix/) - Medium

## Related Topics Problems

**Game Theory:**
- [292. Nim Game](https://leetcode.com/problems/nim-game/) - Easy
- [294. Flip Game II](https://leetcode.com/problems/flip-game-ii/) - Medium
- [375. Guess Number Higher or Lower II](https://leetcode.com/problems/guess-number-higher-or-lower-ii/) - Medium
- [464. Can I Win](https://leetcode.com/problems/can-i-win/) - Medium

**Dynamic Programming:**
- [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) - Easy
- [198. House Robber](https://leetcode.com/problems/house-robber/) - Medium
- [213. House Robber II](https://leetcode.com/problems/house-robber-ii/) - Medium
- [322. Coin Change](https://leetcode.com/problems/coin-change/) - Medium

---

|**Previous:** [Day 86](../Day_86/) | **Next:** [Day 88](../Day_88/)|
|---|---|

|**Date:** March 29, 2026|
|---|