# Day 28 - LeetCode Challenge

**Date:** January 28, 2026

---

## 📋 Problem Solved

### ✅ Find Kth Bit in Nth Binary String
- **LeetCode:** [#1545](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/)
- **Difficulty:** Medium
- **Status:** Solved
- **Topics:** Recursion, Bit Manipulation, String, Divide and Conquer
- **Key Concept:** Recursive pattern recognition without building the entire string

---

## 📊 Daily Stats
- **Problems Attempted:** 1
- **Problems Solved:** 1
- **Time Spent:** 34
- **Concepts Practiced:** Recursive Patterns, Binary String Manipulation, Mathematical Optimization

---

## 🎯 Learning Focus
- **Primary:** Pattern-based recursion without explicit string construction
- **Secondary:** Bit manipulation and string symmetry
- **Advanced:** Space optimization from O(2^n) to O(n)

---

## 📁 Repository Structure
```
Day28/
├── README.md           ← You are here
├── notes.md            ← Detailed explanations & problem-solving
└── Solution1545.java   ← Find Kth Bit in Nth Binary String
```

---

## 🔗 Quick Links
- [Problem Discussion](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/discuss/)
- [Recursion Patterns](https://leetcode.com/discuss/study-guide/1733447/recursion-problems)
- [Bit Manipulation Guide](https://leetcode.com/discuss/study-guide/1161092/bit-manipulation)

---

## 💡 Key Takeaways
- **Avoid building the entire string** - use pattern recognition instead
- **Length formula:** `len = 2^n` for the nth string
- **Three-part structure:** Left half (recursive) + Middle '1' + Right half (inverted mirror)
- **Bit shift optimization:** `1 << n` computes `2^n` efficiently
- **Mirror property:** Position `k` in right half corresponds to `len - k` in left half (inverted)

---

## ⚙️ Complexity Analysis
- **Time Complexity:** O(n) - At most n recursive calls
- **Space Complexity:** O(n) - Recursion stack depth

**Optimization achieved:**
```
Brute Force:    O(2^n) time, O(2^n) space - Build entire string
Your Solution:  O(n) time, O(n) space - Never build string!
```

**Example:** For n=20, k=1000:
- Brute force: Build string of length 2^20 = 1,048,576 characters
- Your solution: Just 20 recursive calls!

---

## 🎓 Pattern Recognition

**This problem teaches:**
- Recursive pattern analysis without materialization
- String construction rules can be inverted
- Middle element as pivot point
- Symmetry and mirroring in recursive structures

**Similar problems:**
- K-th Symbol in Grammar (#779) - Similar recursive pattern
- Pow(x, n) (#50) - Divide and conquer recursion
- Reverse Bits (#190) - Bit manipulation
- Binary String With Substrings Representing 1 To N (#1016)

---

## 🔥 Streak Tracker
- **Days 22-28:** 7-day problem-solving streak! 🎉🎉🎉🎉
- **Easy problems solved:** 1
- **Medium problems solved:** 7
- **Hard problems attempted:** 2
- **Full week completed!** 🏆

---

## 📈 Progress Journey

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 22 | Build Array | Medium | Array Simulation |
| 23 | Max Eaten Apples | Medium | Greedy + Min-Heap |
| 24 | Kth Largest | Medium | Top K with Heap |
| 25 | Stock Strategy | Medium | Prefix Sum |
| 26 | Transpose Matrix | Easy | Matrix Manipulation |
| 27 | Decode String | Medium | Recursion + Parsing |
| 28 | Find Kth Bit | Medium | Mathematical Recursion |

**Weekly Theme - Recursion Mastery:**
- Day 27: Recursion with state management (Decode String)
- Day 28: Recursion with mathematical patterns (Find Kth Bit)

**Skills Evolution:**
- Week 1: Data structures & optimization
- Week 2 Start: Advanced recursion techniques ✨

---

**Previous:** [Day 27](../Day_27/) | **Next:** [Day 29](../Day_29/)