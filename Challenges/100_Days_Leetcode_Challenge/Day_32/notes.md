# Day 32 - Detailed Notes

## Problem: Remove Nth Node From End of List

**LeetCode Problem:** [19](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | **Difficulty:** Medium

### 📝 Problem Statement

Given the `head` of a linked list, remove the `n-th` node from the **end** of the list and return its head.

**Follow-up:** Could you do this in one pass?

**Constraints:**
- Number of nodes: `[1, 30]`
- `1 <= n <= number of nodes`


### 📊 Examples

#### Example 1
```
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Explanation:
Remove 2nd from end (node 4)
[1] → [2] → [3] → [4] → [5] → null
                    ↑ remove
[1] → [2] → [3] → [5] → null
```

#### Example 2
```
Input: head = [1], n = 1
Output: []
```

#### Example 3
```
Input: head = [1,2], n = 1
Output: [1]
```

### 💡 Approach

**Strategy:** Two-Pass Solution with Size Calculation

**Core Idea:**
1. Calculate total size of list
2. Convert "nth from end" to "nth from start"
3. Traverse to node before target
4. Delete target node

#### Index Conversion

**Key insight:** nth from end = (size - n)th from start

```
List: [1] → [2] → [3] → [4] → [5]
Size: 5, n = 2 (2nd from end)

From end:   5th  4th  3rd  2nd  1st
From start: 1st  2nd  3rd  4th  5th

2nd from end = (5-2) = 3rd from start = node [4]
```

### 🔄 Algorithm Steps

**Step 1: Calculate Size**
```java
private int Sizeof(ListNode head) {
    ListNode temp = head;
    int i = 0;
    while (temp != null) {
        temp = temp.next;
        i++;
    }
    return i;
}
```

**Step 2: Handle Head Removal**
```java
if (n1 == n) {  // Removing head (nth from end = size)
    head = head.next;
    return head;
}
```

**Step 3: Traverse to Previous Node**
```java
ListNode curr = head;
while (n1 - n != 1) {  // Stop at node before target
    curr = curr.next;
    n++;
}
```

**Step 4: Delete Target**
```java
curr.next = curr.next.next;
return head;
```


### 🔍 Visual Walkthrough

**Example: Remove 2nd from end in [1,2,3,4,5]**

```
Step 1: Calculate size
n1 = 5

Step 2: Check head removal
n = 2, n1 = 5
n1 != n, so not removing head

Step 3: Traverse to previous node
Target from start: n1 - n = 5 - 2 = 3 (position 4)
Need position 3 (node before target)

Loop: n1 - n != 1
Initial: n1=5, n=2, diff=3
  curr=[1], n=3, diff=2
  curr=[2], n=4, diff=1 (stop)

curr is now at [3]

Step 4: Delete
curr.next = curr.next.next
[3].next = [5]

Result: [1] → [2] → [3] → [5] ✓
```

### ⚙️ Complexity Analysis

- **Time Complexity:** O(n)
  - First pass: O(n) to calculate size
  - Second pass: O(n) to reach target
  - Total: O(n)

- **Space Complexity:** O(1)
  - Only pointer variables

### 🐛 Edge Cases Handled

| Case | Example | Handling |
|------|---------|----------|
| Single node | `[1], n=1` | `n1==n`, return null |
| Remove head | `[1,2,3], n=3` | Special case check |
| Remove tail | `[1,2,3], n=1` | Normal traversal |
| Two nodes | `[1,2], n=2` | Remove head |

### 🔄 Problems Encountered & Solutions

#### Problem 1: Loop Condition Understanding
**Issue:** When to stop traversal?

**Solution:** Stop at node before target. When `n1 - n == 1`, we're at the correct position.

#### Problem 2: Why Increment n?
**Your approach:** Reuses `n` as counter, incrementing to close gap.

**Alternative:** Could use separate counter `for (int i = 0; i < n1-n-1; i++)`

Both work!

### 🎓 Alternative Approach: One-Pass Two-Pointer

**Strategy:** Maintain gap of n between two pointers

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode fast = dummy;
    ListNode slow = dummy;

    // Move fast n+1 steps ahead
    for (int i = 0; i <= n; i++) {
        fast = fast.next;
    }

    // Move both until fast reaches end
    while (fast != null) {
        fast = fast.next;
        slow = slow.next;
    }

    // slow is at node before target
    slow.next = slow.next.next;

    return dummy.next;
}
```

**Visual:**
```
Remove 2nd from end: [1,2,3,4,5]

Create gap of n+1=3:
        slow              fast
dummy → [1] → [2] → [3] → [4] → [5] → null

Move together:
                       slow              fast=null
dummy → [1] → [2] → [3] → [4] → [5]

Delete: slow.next = slow.next.next
Result: [1] → [2] → [3] → [5] ✓
```

>**Why dummy node?**
- Handles head removal without special case
- Simplifies logic
- Common linked list pattern


### 📊 Approach Comparison

| Approach | Passes | Complexity | Edge Cases | Code |
|----------|--------|-----------|-----------|------|
| Two-pass (My Approch) | 2 | Simple | Explicit | Clear |
| One-pass | 1 | Medium | Dummy handles | Elegant |

**Both:** O(n) time, O(1) space

**My approach advantages:**
- ✅ Easy to understand
- ✅ Clear logic flow
- ✅ Good for learning

**One-pass advantages:**
- ✅ Single traversal
- ✅ No head special case
- ✅ Interview impressive

### 🎓 Pattern Recognition

**Two-pass pattern:**
- Calculate global property first
- Use that info for second pass
- Simpler logic

**Two-pointer gap pattern:**
- Maintain fixed distance between pointers
- When first reaches end, second is at target
- More elegant

**Similar problems:**
- Find Middle (#876) - Fast/slow pointers
- Palindrome List (#234) - Find middle, reverse
- Intersection (#160) - Size calculation

## 📝 Daily Reflection

### 💡 Key Realizations

**Multiple Valid Solutions:**
- Two-pass: Simple, clear
- One-pass: Elegant, impressive
- Trade-offs exist, both correct

**Linked List Foundation Growing:**
- Day 30: Design operations
- Day 31: Reversal pattern
- Day 32: Deletion strategies
- **Ready for advanced problems**

### 🎯 Tomorrow's Focus
- Try one-pass implementation
- Detect Cycle problem (#141)
- Fast/slow pointer technique
- Or explore binary trees


### 💭 Key Takeaway

**Both approaches are valid.** Your two-pass solution is correct and clear. The one-pass is more elegant but requires the gap technique. In interviews, explaining trade-offs between approaches demonstrates deep understanding. The clearest solution is often the best solution!

|Last Updated: February 01, 2026|
|---|