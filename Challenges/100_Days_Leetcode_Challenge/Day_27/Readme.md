# Day 27 - LeetCode Challenge

**Date:** January 27, 2026


## 📋 Problem Solved

### ✅ Decode String
- **LeetCode:** [#394](https://leetcode.com/problems/decode-string/)
- **Difficulty:** Medium
- **Status:** Solved
- **Topics:** Stack, Recursion, String Parsing, DFS
- **Key Concept:** Recursive string decoding with nested brackets



## 📊 Daily Stats
- **Problems Attempted:** 1
- **Problems Solved:** 1
- **Time Spent:** 34
- **Concepts Practiced:** Recursion, String Building, Nested Structure Parsing


## 🎯 Learning Focus
- **Primary:** Recursive descent parsing
- **Secondary:** State management across recursive calls
- **Advanced:** Handling multi-digit numbers and nested brackets


## 📁 Repository Structure
```
Day27/
├── README.md           ← You are here
├── notes.md            ← Detailed explanations & problem-solving
└── Solution394.java    ← Decode String
```


## 🔗 Quick Links
- [Problem Discussion](https://leetcode.com/problems/decode-string/discuss/)
- [Recursion Patterns Guide](https://leetcode.com/discuss/study-guide/1733447/recursion-problems)
- [Stack vs Recursion Comparison](https://leetcode.com/discuss/study-guide/2122532/Stack-vs-Recursion)


## 💡 Key Takeaways
- **Class-level index** enables state sharing across recursive calls
- **Recursion naturally handles** nested bracket structures
- **Multi-digit numbers** require accumulation: `num = num * 10 + digit`
- **Early return on `]`** elegantly handles nested depth unwinding
- **StringBuilder** is efficient for repeated string concatenation


## ⚙️ Complexity Analysis
- **Time Complexity:** O(n × k) where n is string length, k is max repeat count
  - Each character processed once
  - Repeated strings built k times
- **Space Complexity:** O(n) for recursion stack depth and string building

**Why recursion fits perfectly:**
```
Each '[' starts a new recursive call
Each ']' returns from current recursion
Natural mapping: bracket depth = call stack depth
```


## 🎓 Pattern Recognition

**This problem teaches:**
- Recursive descent parsing technique
- Managing global state in recursion
- Nested structure traversal with early returns
- String manipulation with repetition

**Similar problems:**
- Basic Calculator (#224) - Recursive expression parsing
- Remove K Digits (#402) - Stack-based digit removal
- Nested List Weight Sum (#339) - Recursive nested structure
- Mini Parser (#385) - String to nested structure


## 🔥 Streak Tracker
- **Days 22-27:** 6-day problem-solving streak! 🎉🎉🎉
- **Easy problems solved:** 1
- **Medium problems solved:** 6
- **Hard problems attempted:** 2


## 📈 Progress Journey

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 22 | Build Array | Medium | Array Simulation |
| 23 | Max Eaten Apples | Medium | Greedy + Min-Heap |
| 24 | Kth Largest | Medium | Top K with Heap |
| 25 | Stock Strategy | Medium | Prefix Sum |
| 26 | Transpose Matrix | Easy | Matrix Manipulation |
| 27 | Decode String | Medium | Recursion + Parsing |

**Skills Evolution:**
- Days 22-24: Data structures (Heap, Queue)
- Day 25: Optimization (Prefix Sum)
- Day 26: Fundamentals (Matrix)
- Day 27: Recursion & Parsing


**Previous:** [Day 26](../Day_26/) | **Next:** [Day 28](../Day_28/)