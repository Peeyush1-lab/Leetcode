# 🤝 Day 4 (Problem 2): Maximum Matching of Players With Trainers

## 📋 Problem Statement

You are given a **0-indexed** integer array `players`, where `players[i]` represents the **ability** of the `i-th` player. You are also given a **0-indexed** integer array `trainers`, where `trainers[j]` represents the **training capacity** of the `j-th` trainer.

The `i-th` player can **match** with the `j-th` trainer if the player's ability is **less than or equal to** the trainer's training capacity. Additionally, the `i-th` player can be matched with at most **one** trainer, and the `j-th` trainer can be matched with at most **one** player.

Return the **maximum** number of matchings between `players` and `trainers` that satisfy these conditions.

## 💡 Examples

### Example 1: 🎯
**Input:** `players = [4,7,9]`, `trainers = [8,2,5,8]`
**Output:** `2`
**Explanation:**
- Match player 0 (ability 4) with trainer 0 (capacity 8)
- Match player 1 (ability 7) with trainer 3 (capacity 8)
- Player 2 (ability 9) cannot be matched with remaining trainers
- Maximum matchings: 2

### Example 2: 🎯
**Input:** `players = [1,1,1]`, `trainers = [10]`
**Output:** `1`
**Explanation:**
- Only one trainer available
- Can match any one player with the trainer
- Maximum matchings: 1

### Example 3: 🎯
**Input:** `players = [5,3,1]`, `trainers = [2,4,6,8]`
**Output:** `3`
**Explanation:**
- Player 2 (ability 1) matches with trainer 0 (capacity 2)
- Player 1 (ability 3) matches with trainer 1 (capacity 4)
- Player 0 (ability 5) matches with trainer 2 (capacity 6)
- All players matched!

## ⚠️ Constraints

- `1 <= players.length, trainers.length <= 10^5`
- `1 <= players[i], trainers[j] <= 10^9`

## 🔑 Key Points

1. 🎯 Each player can match with **at most one** trainer
2. 🎯 Each trainer can match with **at most one** player
3. ✅ Match condition: `player[i] <= trainer[j]`
4. 📊 Want to **maximize** the number of matches
5. 🔄 This is a **matching problem** - need optimal pairing
6. 💡 **Greedy approach** works perfectly here

## 🛠️ Approach: Two Pointers + Sorting

### 💡 Core Intuition
**Key Insight:** Match the weakest available player with the weakest suitable trainer!

Why? If we use a strong trainer for a weak player, we might waste the trainer's capacity. By matching greedily from weakest to strongest, we maximize total matches.

### 📋 Algorithm Steps
1. **Sort** both arrays in ascending order
2. Use **two pointers**: one for players, one for trainers
3. For each trainer, try to match with current player
4. If `trainer >= player`: match found, move both pointers
5. If `trainer < player`: trainer too weak, try next trainer
6. Continue until one array exhausted

### ⏱️ Complexity
- **Time:** O(n log n + m log m) - Sorting dominates
- **Space:** O(1) - Only pointers (or O(log n) for sorting)

## Example

```
Input: players = [4,7,9], trainers = [8,2,5,8]

Step 1: Sort both arrays
players  = [4, 7, 9]
trainers = [2, 5, 8, 8]

Step 2: Use two pointers
           ↓
players  = [4, 7, 9]
trainers = [2, 5, 8, 8]
           ↓

Match attempts:
- trainer[0]=2 < player[0]=4 ❌ (skip trainer)
- trainer[1]=5 > player[0]=4 ✅ (match! count=1)
- trainer[2]=8 > player[1]=7 ✅ (match! count=2)
- trainer[3]=8 < player[2]=9 ❌ (no more trainers)

Result: 2 matches
```