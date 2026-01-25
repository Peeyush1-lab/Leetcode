# Day 25 - LeetCode Challenge

**Date:** January 25, 2026

---

## 📋 Problem Solved

### ✅ Best Time to Buy and Sell Stock using Strategy
- **LeetCode:** [#3652](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/)
- **Difficulty:** Medium
- **Status:** Solved
- **Topics:** Prefix Sum, Sliding Window, Greedy, Array Optimization
- **Key Concept:** Optimize profit by strategically changing buy/sell decisions in a k-length window

---

## 📊 Daily Stats
- **Problems Attempted:** 1
- **Problems Solved:** 1
- **Time Spent:** _Add your time_
- **Concepts Practiced:** Prefix Sum Arrays, Range Query Optimization, Strategic Window Selection

---

## 🎯 Learning Focus
- **Primary:** Prefix sum optimization for O(1) range queries
- **Secondary:** Three-part decomposition (left + change + right)
- **Advanced:** Strategic partial modification of subarrays

---

## 📁 Repository Structure
```
Day25/
├── README.md           ← You are here
├── notes.md            ← Detailed explanations & problem-solving
└── Solution3652.java   ← Best Time to Buy and Sell Stock using Strategy
```

---

## 🔗 Quick Links
- [Problem Discussion](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/discuss/)
- [Prefix Sum Tutorial](https://leetcode.com/discuss/study-guide/1341317/prefix-sum-technique)
- [Range Query Optimization Guide](https://cp-algorithms.com/data_structures/segment_tree.html)

---

## 💡 Key Takeaways
- **Prefix sums** transform O(k) range queries into O(1) operations
- **Two prefix arrays** can track different aspects simultaneously (profit & price)
- **Three-part decomposition** simplifies complex optimization problems
- Using `long` prevents integer overflow in cumulative calculations
- Strategic **partial window modification** can optimize better than full changes

---

## ⚙️ Complexity Analysis
- **Time Complexity:** O(n) - Single pass after O(n) preprocessing
- **Space Complexity:** O(n) - Two prefix sum arrays

**Optimization achieved:**
```
Brute Force:   O(n × k) - Recalculate for each window
Prefix Sums:   O(n)     - O(1) per window after preprocessing
```

---

## 🎓 Pattern Recognition

**This problem teaches:**
- Prefix sum optimization for range sum queries
- Sliding window with strategic modification
- Decomposing complex calculations into manageable parts

**Similar problems:**
- Maximum Subarray (#53)
- Subarray Sum Equals K (#560)
- Range Sum Query - Immutable (#303)
- Maximum Sum of 3 Non-Overlapping Subarrays (#689)

---

## 🔥 Streak Tracker
- **Days 22-25:** 4-day problem-solving streak! 🎉🎉
- **Medium problems solved:** 5
- **Hard problems attempted:** 2
- **New patterns learned:** Prefix Sum, Heap optimization

---

## 📈 Progress Journey

| Day | Pattern | Data Structure |
|-----|---------|----------------|
| 22 | Simulation | Array + Index |
| 23 | Greedy + Expiration | Min-Heap |
| 24 | Top K Selection | Min-Heap (size K) |
| 25 | Range Optimization | Prefix Sum |

---

**Previous:** [Day 24](../Day_24/) | **Next:** [Day 26](../Day_26/)