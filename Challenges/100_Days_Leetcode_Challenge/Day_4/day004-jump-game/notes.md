# 📝 Solution Explanation & Problem-Solving Notes

## 🎯 Solution Breakdown

### 📊 Algorithm Overview

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) |
| **Space Complexity** | O(1) |
| **Approach** | Greedy (Backwards) |
| **Pattern** | Array, Dynamic Programming, Greedy |

---

## 💡 Intuition

**The Key Insight:** Instead of exploring all possible forward jumps (which would be exponential), we work **backwards** from the goal!

### 🧠 Core Idea
> "If I can reach position X, and position X can reach the goal, then position X becomes my new goal!"

By moving the goal backwards, we simplify the problem dramatically. We just need to check if we can ever move the goal all the way back to index 0.

---

## 📋 Step-by-Step Approach

### Step 1️⃣: Initialization
```java
int goal = nums.length - 1;
```
- Set `goal` to the **last index** of the array
- This is our ultimate target position

### Step 2️⃣: Iterate Backwards
```java
for(int i = nums.length-2; i >= 0; i--)
```
- Start from **second-last position** (index `n-2`)
- Move backwards to index 0
- We're checking: "Can each position reach the current goal?"

### Step 3️⃣: Check Reachability
```java
if(i + nums[i] >= goal)
```
**The Magic Condition!** ✨

This checks:
- `i` = current position
- `nums[i]` = maximum jump from position i
- `i + nums[i]` = farthest position we can reach from i
- If this reaches or exceeds `goal`, we can reach it!

### Step 4️⃣: Update Goal
```java
goal = i;
```
- If we can reach the goal from position `i`, then `i` becomes our **new goal**
- Why? Because if we can reach `i`, we can reach the old goal!
- This "chains" our reachability backwards

### Step 5️⃣: Final Check
```java
return goal == 0;
```
- If `goal` moved all the way back to index 0, we can reach the end!
- If `goal` is stuck somewhere > 0, we can't reach the end

---

## 🎨 Detailed Visual Walkthrough

### Example 1: `nums = [2,3,1,1,4]`

```
Initial Setup:
Index:  0  1  2  3  4
Array: [2, 3, 1, 1, 4]
                    ↑
                  goal = 4

────────────────────────────────

Iteration 1: i = 3
[2, 3, 1, 1, 4]
          ↑  ↑
          i  goal

Check: i + nums[i] >= goal
       3 + 1 >= 4
       4 >= 4  ✓

Update: goal = 3
[2, 3, 1, 1, 4]
          ↑
        goal = 3

💭 Insight: If we reach index 3, we can reach index 4!

────────────────────────────────

Iteration 2: i = 2
[2, 3, 1, 1, 4]
       ↑  ↑
       i  goal

Check: i + nums[i] >= goal
       2 + 1 >= 3
       3 >= 3  ✓

Update: goal = 2
[2, 3, 1, 1, 4]
       ↑
     goal = 2

💭 Insight: If we reach index 2, we can reach index 3, which can reach index 4!

────────────────────────────────

Iteration 3: i = 1
[2, 3, 1, 1, 4]
    ↑  ↑
    i  goal

Check: i + nums[i] >= goal
       1 + 3 >= 2
       4 >= 2  ✓

Update: goal = 1
[2, 3, 1, 1, 4]
    ↑
  goal = 1

💭 Insight: Index 1 can jump to index 4 directly! But we don't need to - we just know it can reach our current goal.

────────────────────────────────

Iteration 4: i = 0
[2, 3, 1, 1, 4]
 ↑  ↑
 i  goal

Check: i + nums[i] >= goal
       0 + 2 >= 1
       2 >= 1  ✓

Update: goal = 0
[2, 3, 1, 1, 4]
 ↑
goal = 0

💭 Insight: From start (index 0), we can reach index 1, which chains all the way to the end!

────────────────────────────────

Final Check:
goal == 0  ✓
Return: true ✅
```

---

### Example 2: `nums = [3,2,1,0,4]` (Cannot reach)

```
Initial Setup:
Index:  0  1  2  3  4
Array: [3, 2, 1, 0, 4]
                    ↑
                  goal = 4

────────────────────────────────

Iteration 1: i = 3
[3, 2, 1, 0, 4]
          ↑  ↑
          i  goal

Check: i + nums[i] >= goal
       3 + 0 >= 4
       3 >= 4  ✗

Goal stays: goal = 4
💭 Index 3 has 0 jump power - can't reach goal!

────────────────────────────────

Iteration 2: i = 2
[3, 2, 1, 0, 4]
       ↑     ↑
       i   goal

Check: i + nums[i] >= goal
       2 + 1 >= 4
       3 >= 4  ✗

Goal stays: goal = 4
💭 Index 2 can only reach index 3, not index 4!

────────────────────────────────

Iteration 3: i = 1
[3, 2, 1, 0, 4]
    ↑        ↑
    i      goal

Check: i + nums[i] >= goal
       1 + 2 >= 4
       3 >= 4  ✗

Goal stays: goal = 4
💭 Index 1 can reach up to index 3, but that's a dead end!

────────────────────────────────

Iteration 4: i = 0
[3, 2, 1, 0, 4]
 ↑           ↑
 i         goal

Check: i + nums[i] >= goal
       0 + 3 >= 4
       3 >= 4  ✗

Goal stays: goal = 4
💭 Even from start, we can only reach index 3, which is trapped!

────────────────────────────────

Final Check:
goal == 0  ✗
goal = 4 (stuck!)
Return: false ❌
```

---

## ⚠️ Problems Encountered & Solutions

### ❌ Problem 1: Understanding the Greedy Approach

| Aspect | Details |
|--------|---------|
| **Initial Thought** | Try all possible jump combinations (BFS/DFS) |
| **Problem** | Exponential time complexity - would timeout |
| **Example** | From index 0 with value 3, can jump to 1, 2, or 3 |
| **Realization** | Don't need to find the path, just if it's possible! |
| **Breakthrough** | Working backwards simplifies everything |
| **Key Insight** | If position X can reach goal, X becomes new goal |
| **Result** | ✅ Linear time instead of exponential |

**Why backwards is better:**
```
Forward (Complex):
- Try all jumps from each position
- Exponential branches
- Need to track all paths

Backward (Simple):
- One check per position
- Linear scan
- No path tracking needed
```

---

### ❌ Problem 2: The Condition Logic

| Aspect | Details |
|--------|---------|
| **Confusion** | Why `i + nums[i] >= goal` and not `==`? |
| **Question** | Can we overshoot the goal? |
| **Answer** | Yes! We can jump past the goal |
| **Example** | Position 1, value 5, goal at index 3 → Can reach! |
| **Understanding** | `>=` covers both exact reach and overshoot |
| **Wrong Condition** | Using `== goal` misses valid jumps |
| **Result** | ✅ `>=` correctly handles all cases |

---

### ❌ Problem 3: Starting the Loop

| Aspect | Details |
|--------|---------|
| **Question** | Why start from `nums.length - 2`? |
| **Initial Mistake** | Started from `nums.length - 1` |
| **Problem** | Goal is already at last index! |
| **Logic** | Last index doesn't need to jump anywhere |
| **Correct Start** | Second-last position (`n - 2`) |
| **Edge Case** | Array with length 1: loop doesn't run, returns true ✓ |
| **Result** | ✅ Proper initialization avoids unnecessary check |

---

### ❌ Problem 4: The Final Return Condition

| Aspect | Details |
|--------|---------|
| **Question** | Why check if `goal == 0`? |
| **Understanding** | Goal represents closest position we must reach |
| **Success Case** | If goal reaches index 0, we can reach end from start |
| **Failure Case** | If goal stuck at index > 0, we can't reach that position |
| **Example** | `[3,2,1,0,4]` → goal stays at 4 → can't reach from 0 |
| **Alternative** | Could use `goal == 0 ? true : false` but shorter is cleaner |
| **Result** | ✅ Simple boolean check determines answer |

---

### ❌ Problem 5: Edge Cases

| Edge Case | Input | Expected | Handling |
|-----------|-------|----------|----------|
| Single element | `[0]` | `true` | Already at goal ✓ |
| All zeros except first | `[1,0,0,0]` | `false` | Goal stays at end ✓ |
| Large jumps | `[10,1,1,1,1]` | `true` | Can overshoot ✓ |
| Exact reach needed | `[2,0,0]` | `true` | `>=` handles it ✓ |
| Start with 0 | `[0,1]` | `false` | Can't move from start ✓ |

---

## 🔀 Alternative Approaches Considered

| Approach | Time | Space | Pros | Cons | Decision |
|----------|------|-------|------|------|----------|
| **Greedy Backwards** | O(n) | O(1) | Optimal, simple | Less intuitive | ✅ **Chosen** |
| **Greedy Forwards** | O(n) | O(1) | Track max reach | More complex logic | ❌ More complex |
| **BFS** | O(n²) | O(n) | Intuitive | Too slow | ❌ Timeout |
| **DFS + Memo** | O(n²) | O(n) | Finds path | Unnecessary | ❌ Overkill |
| **Dynamic Programming** | O(n²) | O(n) | Bottom-up | Slower than greedy | ❌ Not optimal |

### Forward Greedy (Alternative O(n) solution)
```java
int maxReach = 0;
for(int i = 0; i <= maxReach && i < nums.length; i++) {
    maxReach = Math.max(maxReach, i + nums[i]);
    if(maxReach >= nums.length - 1) return true;
}
return false;
```
**Why backwards is cleaner:**
- No need to track `maxReach` variable
- No need for `i <= maxReach` condition
- Simpler loop logic

---

## 💡 Key Takeaways

| # | Lesson | Application |
|---|--------|-------------|
| 1️⃣ | **🔄 Think backwards** | Sometimes reversing the problem simplifies it |
| 2️⃣ | **🎯 Greedy works** | Don't need optimal path, just if path exists |
| 3️⃣ | **⚡ Move the goal** | Shift target instead of exploring all options |
| 4️⃣ | **📊 Linear is possible** | Array traversal can solve seemingly complex problems |
| 5️⃣ | **🧮 Simple condition** | `i + nums[i] >= goal` is all we need |
| 6️⃣ | **✨ Elegance** | Best solutions are often the simplest |

---

## ⏱️ Complexity Analysis Summary

```
┌─────────────────────────────────────────────────────────┐
│ Time Complexity:   O(n)                                 │
│   - Single backwards pass through array                │
│   - Each position checked exactly once                 │
│   - Constant work per position                         │
│                                                          │
│ Space Complexity:  O(1)                                 │
│   - Only one variable (goal)                           │
│   - No recursion stack                                 │
│   - No additional data structures                      │
│                                                          │
│ Optimal: YES ✓                                          │
│   - Cannot do better than O(n) - must check all       │
│   - Cannot use less than O(1) space                    │
└─────────────────────────────────────────────────────────┘
```

---

## 🎓 Learning Moments

### Before This Problem
- ❓ Would've tried BFS/DFS approach
- ❓ Thought we need to find actual path
- ❓ Didn't consider backwards approach
- ❓ Would've used more complex tracking

### After This Problem
- ✅ Learned greedy backwards technique
- ✅ Understood that existence ≠ finding path
- ✅ Recognized when to reverse problem direction
- ✅ Appreciated simplicity of moving goal
- ✅ Saw how O(1) space beats O(n) when possible

---

## 🧪 Test Cases Walkthrough

| Input | Expected | Reason | Result |
|-------|----------|--------|--------|
| `[2,3,1,1,4]` | `true` | Multiple valid paths | ✅ |
| `[3,2,1,0,4]` | `false` | Trapped at index 3 | ✅ |
| `[0]` | `true` | Already at goal | ✅ |
| `[2,0,0]` | `true` | Jump over zeros | ✅ |
| `[1,1,1,1]` | `true` | Hop step by step | ✅ |
| `[0,1]` | `false` | Can't start | ✅ |
| `[10]` | `true` | Single element | ✅ |

---

## 🔗 Related Problems

| Problem | Similarity | Difficulty |
|---------|-----------|-----------|
| Jump Game II | Find minimum jumps | 🟡 Medium |
| Jump Game III | Jump with constraints | 🟡 Medium |
| Jump Game IV | Jump with same values | 🔴 Hard |
| Jump Game V | Jump with heights | 🔴 Hard |
| Minimum Number of Taps | Similar greedy pattern | 🔴 Hard |

---

## 🌟 Pattern Recognition

This problem exemplifies the **Greedy Backwards** pattern:

**When to use:**
- ✅ Need to determine if goal is reachable
- ✅ Don't need to find optimal path
- ✅ Can verify reachability with local decisions
- ✅ Working backwards simplifies logic

**Similar pattern appears in:**
- Gas Station
- Best Time to Buy/Sell Stock
- Minimum Platforms

---

<div align="center">

### ✅ Problem Solved Successfully!

**Time Taken:** ~40 minutes
**Attempts:** 1
**Key Learning:** Greedy backwards approach - move the goal instead of exploring paths!

**Difficulty:** 🟡 Medium | **Pattern:** Greedy, Array

</div>

---

**Last Updated:** January 04, 2026