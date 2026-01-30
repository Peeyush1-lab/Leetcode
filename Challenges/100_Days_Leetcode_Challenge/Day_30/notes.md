# Day 30 - Detailed Notes

## Problem: Design Linked List

**LeetCode:** [#707](https://leetcode.com/problems/design-linked-list/) | **Difficulty:** Medium

### 📝 Problem Statement

Design your implementation of a singly linked list with these operations:
- `get(index)` - Get value at index (return -1 if invalid)
- `addAtHead(val)` - Add node at beginning
- `addAtTail(val)` - Add node at end
- `addAtIndex(index, val)` - Insert node at index
- `deleteAtIndex(index)` - Delete node at index

### 📊 Example

```
MyLinkedList list = new MyLinkedList();
list.addAtHead(1);      // List: 1
list.addAtTail(3);      // List: 1 → 3
list.addAtIndex(1, 2);  // List: 1 → 2 → 3
list.get(1);            // Returns 2
list.deleteAtIndex(1);  // List: 1 → 3
list.get(1);            // Returns 3
```

### 💡 Approach

**Strategy:** Singly Linked List with Size Tracking

**Key Design Decisions:**
1. **Inner Node class** - Encapsulates node structure
2. **Size tracking** - O(1) validation instead of O(n)
3. **Head pointer only** - Simpler than tail pointer

### 🔍 Implementation Highlights

#### Data Structure
```
Node:               MyLinkedList:
┌──────────┐        ┌──────────┐
│ data: int│        │ head: Node
│ next: Node        │ size: int│
└──────────┘        └──────────┘

Example: head → [1|→] → [2|→] → [3|null], size = 3
```

#### Critical Pointer Order (Insertion)
```java
// CORRECT ORDER:
nn.next = temp.next;  // 1. Link new node first
temp.next = nn;       // 2. Update previous node

// WRONG ORDER (loses reference):
temp.next = nn;       // Breaks the chain!
nn.next = temp.next;  // Points to itself
```

#### Visual Example - addAtIndex(2, 5)
```
Before: [1] → [2] → [3] → [4]

Traverse to index-1 (node at position 1):
temp → [2]

Insert:
nn.next = temp.next   →  [5] → [3]
temp.next = nn        →  [2] → [5]

After: [1] → [2] → [5] → [3] → [4] ✓
```

### ⚙️ Complexity Analysis

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| `get(index)` | O(n) | O(1) | Traverse to index |
| `addAtHead(val)` | O(1) | O(1) | Direct head update |
| `addAtTail(val)` | O(n) | O(1) | Traverse to end |
| `addAtIndex(i, val)` | O(n) | O(1) | Traverse to i-1 |
| `deleteAtIndex(i)` | O(n) | O(1) | Traverse to i-1 |

### 🎯 Key Insights

1. **Size Tracking is Essential**
   - Without: O(n) traversal to check bounds
   - With: O(1) validation
   - Must update in every operation!

2. **Pointer Order Matters**
   - Link new node BEFORE updating previous
   - Wrong order loses chain references

3. **Head Operations are Special**
   - No previous node exists
   - Direct head manipulation
   - Separate handling in most methods

4. **Traverse to index-1 for Modifications**
   - Need previous node to update its `next`
   - Exception: head operations


### 🐛 Edge Cases Handled

| Case | Handling |
|------|----------|
| Empty list | Check `head == null` |
| Delete head | `head = head.next` |
| Add at tail position | Allow `index == size` |
| Invalid indices | Early return with validation |

### 🔄 Problems Encountered & Solutions

#### Problem 1: Pointer Order in Insertion
**Issue:** Updated pointers in wrong order.

**Wrong:**
```java
temp.next = nn;       // ❌ Lose reference!
nn.next = temp.next;
```

**Correct:**
```java
nn.next = temp.next;  // ✓ Save reference first
temp.next = nn;
```

#### Problem 2: Off-by-One in Traversal
**Issue:** Confused about stopping at `index` vs `index-1`.

**Clarification:**
- **get:** Stop AT index (return that node)
- **insert/delete:** Stop at index-1 (need previous node)

#### Problem 3: Size Bounds for addAtIndex
**Issue:** Used `index >= size` instead of `index > size`.

**Fix:** `addAtIndex(size, val)` should append to tail, so allow `index == size`.

### 🎓 Alternative Approaches

| Approach | Add Tail | Space/Node | Complexity |
|----------|----------|------------|------------|
| Singly (yours) | O(n) | 8 bytes | Medium |
| Doubly linked | O(n) | 16 bytes | High |
| With tail pointer | O(1) | 8 bytes | Medium-High |
| With dummy head | O(n) | 8 bytes | Low |

**Verdict:** Your approach is clean and efficient for this problem.


### 🎓 Pattern Recognition

**Linked list patterns:**
- **Traversal:** Move pointer until condition met
- **Insertion:** Two-pointer update in correct order
- **Deletion:** Skip over node (pointer bypass)
- **Head operations:** Direct manipulation

**Similar problems:**
- Reverse Linked List (#206) - Pointer reversal
- Remove Nth Node (#19) - Index-based deletion
- Merge Two Sorted Lists (#21) - Pointer management
- Detect Cycle (#141) - Two-pointer traversal

## 📝 Daily Reflection

### ✅ What Went Well
- Clean implementation with proper encapsulation
- Size tracking for efficient validation
- Correct pointer manipulation order
- All edge cases handled

### 🏆 Day 30 Milestone Achievement!

**Progress:**
- 9-day streak maintained
- 10 problems solved (8 medium, 2 easy)
- 9+ patterns mastered
- Strong fundamentals + advanced techniques

**Skills Evolution:**
- Week 1: Data structures, optimization, recursion
- Week 2: Fundamentals reinforcement (strings, linked lists)

### 🎯 Tomorrow's Focus
- More linked list problems (reverse, cycle detection)
- Or explore binary trees (natural next step)
- Consider two-pointer techniques

### 💭 Key Takeaway
**Pointer manipulation is foundational.** The patterns learned today (correct order, traversal to index-1, head special cases) appear in dozens of linked list problems. Mastering these basics enables solving complex variations.

**Last Updated:** January 30, 2026