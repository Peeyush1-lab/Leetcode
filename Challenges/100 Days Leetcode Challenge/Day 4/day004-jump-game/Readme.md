# 🦘 Day 4 (Problem 1): Jump Game

## 📋 Problem Statement

You are given an integer array `nums`. You are initially positioned at the array's **first index**, and each element in the array represents your **maximum jump length** at that position.

Return `true` if you can reach the last index, or `false` otherwise.

## 💡 Examples

### Example 1: ✅
**Input:** `nums = [2,3,1,1,4]`
**Output:** `true`
**Explanation:** Jump 1 step from index 0 to 1, then 3 steps to the last index.

### Example 2: ❌
**Input:** `nums = [3,2,1,0,4]`
**Output:** `false`
**Explanation:** You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

### Example 3: ✅
**Input:** `nums = [0]`
**Output:** `true`
**Explanation:** Already at the last index.

### Example 4: ✅
**Input:** `nums = [2,0,0]`
**Output:** `true`
**Explanation:** Jump 2 steps from index 0 directly to index 2.

## ⚠️ Constraints

- `1 <= nums.length <= 10^4`
- `0 <= nums[i] <= 10^5`

## 🔑 Key Points

1. 🎯 Start at index 0, goal is to reach last index
2. 🦘 Each element represents **maximum** jump length (can jump less)
3. ❌ Value 0 can trap you if you can't jump over it
4. 🔄 Multiple paths may exist - just need one valid path
5. ⚡ Don't need to find the actual path, just if it's possible
6. 🧠 Greedy approach: Work backwards from goal

## 🛠️ Approach: Greedy (Backwards)

### 💡 Core Intuition
Instead of trying all possible forward jumps (exponential), **work backwards**! If we can reach position `i` and position `i` can reach the goal, then position `i` becomes our new goal.

### 🎯 Strategy
Move the goal position to the left whenever we find a position that can reach it.

### 📋 Algorithm Steps
1. Start with `goal = last index`
2. Iterate backwards from second-last position
3. For each position `i`, check: `i + nums[i] >= goal`
4. If true, update `goal = i` (we can reach old goal from here)
5. After loop, check if `goal == 0`

### ⏱️ Complexity
- **Time:** O(n) - Single pass backwards
- **Space:** O(1) - Only goal variable

## Example

```
Input: [2,3,1,1,4]

Step 1: goal = 4
[2,3,1,1,4]
       i g    ✓ 3+1 >= 4 → goal = 3

Step 2: goal = 3
[2,3,1,1,4]
     i g      ✓ 2+1 >= 3 → goal = 2

Step 3: goal = 2
[2,3,1,1,4]
   i g        ✓ 1+3 >= 2 → goal = 1

Step 4: goal = 1
[2,3,1,1,4]
 i g          ✓ 0+2 >= 1 → goal = 0

Final: goal = 0 ✅ Can reach!
```