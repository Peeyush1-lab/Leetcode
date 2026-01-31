# Day 31 - Detailed Notes

## Problem: Reverse Linked List

**LeetCode:** [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) | **Difficulty:** Easy


### 📝 Problem Statement

Given the `head` of a singly linked list, reverse the list and return the reversed list.

**Follow-up:** Can you solve it both iteratively and recursively?

### 📊 Examples

#### Example 1
```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Visual:
Before: 1 → 2 → 3 → 4 → 5 → null
After:  5 → 4 → 3 → 2 → 1 → null
```

#### Example 2
```
Input: head = [1,2]
Output: [2,1]
```

#### Example 3
```
Input: head = []
Output: []
```

### 💡 Approach

**Strategy:** Iterative Three-Pointer Reversal

**Core Idea:**
- Use three pointers: `prev`, `temp` (current), `next`
- Traverse list once, reversing pointers as we go
- Return `prev` as new head

#### Why Three Pointers?

**Problem:** Reversing breaks the chain
```
1 → 2 → 3
If we do: 1 ← 2
We lose reference to 3!
```

**Solution:** Save `next` before reversing
```
next = temp.next   // Save 3
temp.next = prev   // Reverse 2 → 1
```

### 🔄 Algorithm Steps

**Initialize:**
```java
ListNode temp = head;   // Current node
ListNode prev = null;   // Previous node (new head will be here)
ListNode next = null;   // Next node (temporary storage)
```

**Loop while temp != null:**
1. Save next: `next = temp.next`
2. Reverse pointer: `temp.next = prev`
3. Move prev forward: `prev = temp`
4. Move temp forward: `temp = next`

**Return:** `prev` (new head)

### 🔍 Visual Walkthrough

**Initial State:**
```
prev    temp           next
null     ↓             null
        [1] → [2] → [3] → [4] → null
```

**Iteration 1:**
```
next = temp.next        →  next points to [2]
temp.next = prev        →  [1] → null
prev = temp             →  prev at [1]
temp = next             →  temp at [2]

Result: null ← [1]    [2] → [3] → [4] → null
              prev    temp
```

**Iteration 2:**
```
next = temp.next        →  next points to [3]
temp.next = prev        →  [2] → [1]
prev = temp             →  prev at [2]
temp = next             →  temp at [3]

Result: null ← [1] ← [2]    [3] → [4] → null
                    prev    temp
```

**Iteration 3:**
```
next = temp.next        →  next points to [4]
temp.next = prev        →  [3] → [2]
prev = temp             →  prev at [3]
temp = next             →  temp at [4]

Result: null ← [1] ← [2] ← [3]    [4] → null
                          prev    temp
```

**Iteration 4:**
```
next = temp.next        →  next is null
temp.next = prev        →  [4] → [3]
prev = temp             →  prev at [4]
temp = next             →  temp is null (exit loop)

Result: null ← [1] ← [2] ← [3] ← [4]
                                prev   temp=null
```

**Return prev:** Points to [4], which is new head ✓

### ⚙️ Complexity Analysis

- **Time Complexity:** O(n)
  - Visit each node exactly once
  - Constant work per node

- **Space Complexity:** O(1)
  - Only three pointer variables
  - No recursion stack
  - No additional data structures
  - True in-place reversal

### 🎯 Key Insights

1. **Three Pointers are Essential:**
   - `prev`: Tracks reversed portion
   - `temp`: Current node being processed
   - `next`: Prevents losing rest of list

2. **Order of Operations Matters:**
   ```java
   // CORRECT ORDER:
   next = temp.next;     // 1. Save next
   temp.next = prev;     // 2. Reverse pointer
   prev = temp;          // 3. Move prev
   temp = next;          // 4. Move temp
   ```

3. **Why Return `prev` not `temp`:**
   - When loop ends, `temp` is null
   - `prev` is at last node (new head)

4. **Handles Edge Cases Naturally:**
   - Empty list: `temp` is null, return null immediately
   - Single node: One iteration, works correctly

### 🐛 Edge Cases Handled

| Case | Input | Behavior |
|------|-------|----------|
| Empty list | `null` | Returns null |
| Single node | `[1]` | Returns `[1]` |
| Two nodes | `[1,2]` | Returns `[2,1]` |
| Long list | `[1..1000]` | O(n) reversal |

### 🔄 Problems Encountered & Solutions

#### Problem 1: Lost Reference to Rest of List
**Issue:** Reversing pointer without saving next loses the chain.

**Wrong approach:**
```java
temp.next = prev;  // ❌ Lost reference to rest!
next = temp.next;  // ❌ next is now prev, not the rest
```

**Correct:**
```java
next = temp.next;  // ✓ Save FIRST
temp.next = prev;  // ✓ Then reverse
```

#### Problem 2: Returning Wrong Pointer
**Issue:** Initially returned `head` or `temp` instead of `prev`.

**Why wrong:**
- `head`: Still points to old head (now tail)
- `temp`: Is null after loop
- `prev`: Points to new head ✓

#### Problem 3: Infinite Loop
**Issue:** Forgot to move `temp` forward.

**Wrong:**
```java
while (temp != null) {
    next = temp.next;
    temp.next = prev;
    prev = temp;
    // Missing: temp = next;  ❌ Infinite loop!
}
```

### 🎓 Alternative Approach: Recursion

```java
public ListNode reverseList(ListNode head) {
    // Base case
    if (head == null || head.next == null) {
        return head;
    }

    // Recursive call
    ListNode newHead = reverseList(head.next);

    // Reverse the pointer
    head.next.next = head;
    head.next = null;

    return newHead;
}
```

**Recursion visualization:**
```
reverseList([1,2,3])
  → reverseList([2,3])
    → reverseList([3])
      → return [3]
    ← set 2.next.next = 2, return [3]
  ← set 1.next.next = 1, return [3]

Result: 3 → 2 → 1 → null
```

**Complexity:**
- Time: O(n)

### 📊 Approach Comparison

| Approach | Time | Space | Difficulty | Interview Preference |
|----------|------|-------|-----------|---------------------|
| Iterative (yours) | O(n) | O(1) | Easy | ⭐⭐⭐⭐⭐ |
| Recursive | O(n) | O(n) | Medium | ⭐⭐⭐ |

**Verdict:** Your iterative approach is preferred for interviews due to O(1) space.

### 🎓 Pattern Recognition

**Three-pointer reversal pattern applies to:**
- Reverse Linked List II (#92) - Reverse portion [m,n]
- Palindrome Linked List (#234) - Reverse second half
- Reorder List (#143) - Reverse and merge
- Reverse Nodes in k-Group (#25) - Reverse in groups

**General pattern:**
```java
ListNode prev = null;
ListNode temp = head;
ListNode next = null;

while (temp != null) {
    next = temp.next;      // Save
    temp.next = prev;      // Reverse
    prev = temp;           // Advance prev
    temp = next;           // Advance temp
}

return prev;
```

### 💡 Real-World Applications

**Where list reversal is used:**
1. **Undo operations** - Reverse action stack
2. **Browser history** - Back navigation
3. **Text editors** - Reverse text selection
4. **Data processing** - Reverse order processing
## 📝 Daily Reflection

### ✅ What Went Well
- Clean iterative implementation
- Correct three-pointer technique
- O(1) space complexity achieved
- All edge cases handled naturally
- Proper pointer advancement

### 💡 Key Realizations

**Pointer Order is Everything:**
- Save before modifying
- Reverse after saving
- Advance in correct sequence

**Simplicity Wins:**
- Iterative cleaner than recursive for this
- No need for complex logic
- Classic pattern worth memorizing

### 🎯 Tomorrow's Focus
- More linked list variations
- Two-pointer techniques
- Or explore binary trees

### 💭 Key Takeaway

**This is THE linked list problem.** The three-pointer reversal technique appears in countless variations. Mastering this pattern unlocks an entire category of problems. Practicing until it becomes second nature is time well spent.

---

**Last Updated:** January 31, 2026