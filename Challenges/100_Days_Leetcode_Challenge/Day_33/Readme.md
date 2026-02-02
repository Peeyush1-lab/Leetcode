# Day 33 - LeetCode Challenge

## 📋 Problem Solved

### ✅ Delete the Middle Node of a Linked List
- **LeetCode:** [#2095](https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/)
- **Difficulty:** Medium
- **Status:** Solved
- **Topics:** Linked List, Two Pointers, Fast and Slow Pointers
- **Key Concept:** Fast/slow pointer technique to find and delete middle node in one pass

## 📊 Daily Stats
- **Problems Attempted:** 1
- **Problems Solved:** 1
- **Time Spent:** 32 min
- **Concepts Practiced:** Two-pointer technique, Fast/slow pointers, One-pass deletion

## 🎯 Learning Focus
- **Primary:** Fast and slow pointer technique (tortoise and hare)
- **Secondary:** Finding middle node in single traversal
- **Advanced:** Previous pointer tracking for deletion

## 📁 Folder Structure
```
Day33/
├── README.md           ← You are here
├── notes.md            ← Detailed explanations & problem-solving
└── Solution2095.java   ← Delete Middle Node
```

## 🔗 Quick Links
- [Problem Discussion](https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/discuss/)
- [Two Pointer Technique](https://leetcode.com/discuss/study-guide/1688903/two-pointers)
- [Fast and Slow Pointers](https://leetcode.com/discuss/study-guide/1212004/Fast-and-Slow-Pointers)

## 💡 Key Takeaways
- **Fast/slow pointer pattern:** Fast moves 2x speed, slow at 1x - when fast ends, slow is at middle
- **Previous pointer crucial:** Track node before slow to enable deletion
- **One-pass solution:** O(n) time with single traversal, no size calculation needed
- **Edge cases handled:** Empty list and single node return null
- **Elegant algorithm:** Combines finding middle with deletion in one pass

## ⚙️ Complexity Analysis
- **Time Complexity:** O(n) - Single pass through list
- **Space Complexity:** O(1) - Only three pointer variables

**Optimization:**
```
Two-pass approach: O(n) - Calculate size, then traverse to middle
Your approach: O(n) - Find and delete in single pass ✓
Same complexity, but more elegant!
```

## 🎓 Pattern Recognition

**Fast and slow pointer pattern (Tortoise and Hare):**
- Fast moves twice as fast as slow
- When fast reaches end, slow is at middle
- Classic pattern for finding middle, detecting cycles

**This problem teaches:**
- Fast/slow pointer technique
- Tracking previous node for deletion
- One-pass optimization
- Edge case handling

**Similar problems:**
- Middle of the Linked List (#876) - Find middle only
- Linked List Cycle (#141) - Detect cycle with fast/slow
- Linked List Cycle II (#142) - Find cycle start
- Palindrome Linked List (#234) - Find middle, then reverse
- Happy Number (#202) - Fast/slow on numbers

## 📈 Progress Journey

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 30 | Design Linked List | Medium | Data Structure Design |
| 31 | Reverse Linked List | Easy | Three Pointers |
| 32 | Remove Nth From End | Medium | Two-Pass/Gap Method |
| 33 | Delete Middle Node | Medium | Fast/Slow Pointers |

**Linked List Mastery Arc:**
- Day 30: Build from scratch ✓
- Day 31: Reversal pattern ✓
- Day 32: Two-pointer gap ✓
- Day 33: Fast/slow technique ✓
- **Complete foundation achieved!**

## 🌟 Why This Problem Matters

**Core Algorithm Pattern:**
- Fast/slow pointers appear in 20+ LeetCode problems
- Fundamental for cycle detection
- Essential for middle-finding algorithms
- Used in Floyd's cycle detection algorithm

**Interview Frequency:**
- Asked by: Amazon, Microsoft, Google, Meta
- Common follow-up: "Can you do it in one pass?"
- Tests understanding of pointer speed manipulation

|**Previous:** [Day 32](../Day_32/) | **Next:** [Day 34](../Day_34/)|
|---|---|

|**Date:** February 02, 2026|
|---|