# Day 35 - LeetCode Challenge

## 📋 Problem Solved

### ✅ Remove Duplicates from Sorted List
- **LeetCode:** [#83](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)
- **Difficulty:** Easy
- **Status:** Solved
- **Topics:** Linked List, Pointer Manipulation
- **Key Concept:** In-place duplicate removal using single pointer traversal

## 📊 Daily Stats
- **Problems Attempted:** 1
- **Problems Solved:** 1
- **Time Spent:** _Add your time_
- **Concepts Practiced:** Pointer manipulation, In-place modification, Sorted list properties

## 🎯 Learning Focus
- **Primary:** Leveraging sorted property for duplicate detection
- **Secondary:** Smart pointer advancement (skip vs move)
- **Advanced:** In-place list modification without extra space

## 📁 Repository Structure
```
Day35/
├── README.md           ← You are here
├── notes.md            ← Detailed explanations & problem-solving
└── Solution83.java     ← Remove Duplicates from Sorted List
```

## 🔗 Quick Links
- [Problem Discussion](https://leetcode.com/problems/remove-duplicates-from-sorted-list/discuss/)
- [Linked List Patterns](https://leetcode.com/discuss/study-guide/1800120/linked-list-problems)
- [In-Place Algorithms](https://leetcode.com/discuss/study-guide/1151183/in-place-algorithms)

## 💡 Key Takeaways
- **Sorted property crucial:** Only need to check adjacent nodes
- **Smart pointer advancement:** Move only when no duplicate found
- **In-place deletion:** Skip duplicates by updating next pointer
- **Single traversal:** O(n) time with one pass
- **No extra space:** O(1) space complexity

## ⚙️ Complexity Analysis
- **Time Complexity:** O(n) - Single pass through list
- **Space Complexity:** O(1) - Only one pointer variable

**Optimization:**
```
Unsorted list: O(n²) or O(n) with hash set (extra space)
Sorted list: O(n) with O(1) space (your solution) ✓
```

## 🎓 Pattern Recognition

**This problem teaches:**
- Leveraging input properties (sorted order)
- Conditional pointer advancement
- In-place list modification
- Adjacent element comparison

**Similar problems:**
- Remove Duplicates from Sorted Array (#26)
- Remove Duplicates from Sorted List II (#82) - Remove all duplicates
- Merge Two Sorted Lists (#21)
- Delete Node in Linked List (#237)

## 📈 Progress Journey

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 32 | Remove Nth From End | Medium | Two-Pass/Gap |
| 33 | Delete Middle Node | Medium | Fast/Slow + Prev |
| 34 | Middle of Linked List | Easy | Pure Fast/Slow |
| 35 | Remove Duplicates | Easy | Single Pointer |

**Linked List Mastery Arc:**
- Days 30-34: Core pointer patterns ✓
- Day 35: In-place modification ✓
- **Foundation complete, ready for advanced problems!**

## 🌟 Why This Problem Matters

**Core Concept:**
- Shows power of leveraging input properties
- Sorted property simplifies O(n²) to O(n)
- Common in data cleaning scenarios

**Interview Context:**
- Tests understanding of sorted data
- Checks pointer manipulation skills
- Common follow-up: unsorted version or remove all duplicates

|**Previous:** [Day 34](../Day_34/) | **Next:** [Day 36](../Day_36/)|
|---|---|

|**Date:** February 04, 2026|
|---|