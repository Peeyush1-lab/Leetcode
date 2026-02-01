# Day 32 - LeetCode Challenge

**Date:** February 01, 2026

## 📋 Problem Solved

### ✅ Remove Nth Node From End of List
- **LeetCode Problem 19:** [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
- **Difficulty:** Medium
- **Status:** Solved
- **Topics:** Linked List, Two Pointers
- **Key Concept:** Two-pass approach - Calculate size then remove node



## 📁 Folder Structure
```
Day32/
├── README.md           ← You are here
├── notes.md            ← Detailed explanations & problem-solving
└── Solution.java       ← Remove Nth Node From End
```


## 🔗 Quick Links
- [Problem Discussion](https://leetcode.com/problems/remove-nth-node-from-end-of-list/discuss/)
- [Two Pointer Technique](https://leetcode.com/discuss/study-guide/1688903/two-pointers)
- [Linked List Patterns](https://leetcode.com/discuss/study-guide/1800120/linked-list-problems)

## 💡 Key Takeaways
- **Two-pass approach:** First calculate size, then remove node
- **Index conversion:** Nth from end = (size - n)th from start
- **Edge case critical:** Removing head requires special handling
- **Helper method:** `Sizeof()` separates concerns cleanly
- **Traverse to previous node:** Need node before target for deletion


## ⚙️ Complexity Analysis
- **Time Complexity:** O(n) - Two passes through list (size + removal)
- **Space Complexity:** O(1) - Only pointer variables used

**Trade-off:**
```
Your approach: Two passes, simple logic
One-pass approach: Single pass, more complex (two pointers with gap)
Both: O(n) time, O(1) space
```


## 🎓 Pattern Recognition

**This problem teaches:**
- Converting "from end" to "from start" index
- Two-pass vs one-pass trade-offs
- Edge case handling (head removal)
- Helper method design

**Similar problems:**
- Delete Node in a Linked List (#237) - Direct deletion
- Remove Duplicates from Sorted List (#83) - Deletion pattern
- Intersection of Two Linked Lists (#160) - Two-pointer
- Linked List Cycle II (#142) - Two-pointer advanced

## 📈 Progress Journey

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 29 | Length of Last Word | Easy | String Traversal |
| 30 | Design Linked List | Medium | Data Structure Design |
| 31 | Reverse Linked List | Easy | Pointer Reversal |
| 32 | Remove Nth From End | Medium | Two-Pass Deletion |

**Linked List Week:**
- Day 30: Build from scratch ✓
- Day 31: Reverse pattern ✓
- Day 32: Deletion from end ✓
- **Mastery building!**

## 🌟 Alternative Approach Preview

**One-Pass Two-Pointer Solution:**
```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode fast = dummy, slow = dummy;

for (int i = 0; i <= n; i++) fast = fast.next;

while (fast != null) {
    fast = fast.next;
    slow = slow.next;
}

slow.next = slow.next.next;
return dummy.next;
```
<br>

> Approch Algo:
>1. Fast pointer moves n steps ahead
>2. Then both move together
>3. When fast reaches end, slow is at target-1

**Comparison:**
- My approach: Easier to understand
- One-pass: More elegant, single traversal
- Both: Same complexity O(n)

---

**Previous:** [Day 31](../Day_31/) | **Next:** [Day 33](../Day_33/)