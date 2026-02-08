# Day 39 | Reverse Linked List II

**LeetCode #92** | Medium | Linked List

## Problem Statement

Given the `head` of a singly linked list and two integers `left` and `right` where `left <= right`, reverse the nodes of the list from position `left` to position `right`, and return the reversed list.

**Examples:**
```
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Input: head = [5], left = 1, right = 1
Output: [5]
```

**Constraints:**
- Number of nodes: `[1, 500]`
- `-500 <= Node.val <= 500`
- `1 <= left <= right <= n`

## Solution Overview

**Algorithm:** Dummy Node + Iterative Node Repositioning

**Strategy:**
1. Create dummy node pointing to head
2. Navigate to position before reversal (left-1)
3. Iteratively move nodes to front of reversed section
4. Repeat (right-left) times
5. Return dummy.next

**Key Insight:** Instead of three-pointer reversal, extract and reposition nodes one by one at the front.

## Implementation Breakdown

### Step 1: Dummy Node Setup
```java
ListNode dummy = new ListNode(-1);
dummy.next = head;
```

**Purpose:** Handles edge case where left = 1 (reversing from head). Dummy ensures we always have a node before reversal start.

### Step 2: Navigate to Position
```java
ListNode prev = dummy;
for(int i = 1; i < left; i++) {
    prev = prev.next;
}
```

**Result:** `prev` points to node at position (left-1), right before reversal begins.

### Step 3: Reversal Loop
```java
ListNode curr = prev.next;
ListNode next;
for (int i = 0; i < right - left; i++) {
    next = curr.next;           // Node to move
    curr.next = next.next;      // Skip next node
    next.next = prev.next;      // Point to front
    prev.next = next;           // Insert at front
}
```

**What happens:** Each iteration extracts one node and moves it to the front of the reversed section.

## Visual Walkthrough

### Example: [1,2,3,4,5], left = 2, right = 4

**Initial Setup:**
```
dummy → [1] → [2] → [3] → [4] → [5]
        prev
```

**After navigation (prev at position 1):**
```
dummy → [1] → [2] → [3] → [4] → [5]
        prev  curr
```

**Iteration 1: Move node 3 to front**
```
Before:
prev → [2] → [3] → [4] → [5]
       curr  next

Operations:
1. next = curr.next → [3]
2. curr.next = next.next → [2] → [4]
3. next.next = prev.next → [3] → [2]
4. prev.next = next → prev → [3]

After:
prev → [3] → [2] → [4] → [5]
       curr stays at [2]
```

**Iteration 2: Move node 4 to front**
```
Before:
prev → [3] → [2] → [4] → [5]
              curr  next

Operations:
1. next = curr.next → [4]
2. curr.next = next.next → [2] → [5]
3. next.next = prev.next → [4] → [3]
4. prev.next = next → prev → [4]

After:
prev → [4] → [3] → [2] → [5]
              curr stays at [2]
```

**Final Result:**
```
dummy → [1] → [4] → [3] → [2] → [5]

Return dummy.next → [1,4,3,2,5] ✓
```

## Why This Technique Works

**Key Insight:**
- `curr` stays at original first node of reversal
- Each iteration extracts the next node and inserts at front
- After (right-left) iterations, section is reversed

**Comparison with three-pointer:**
```
Three-pointer: Reverse pointers in place
Your approach: Move nodes to front position

Both O(n) time, O(1) space
Your approach more intuitive
```

## Complexity Analysis

**Time: O(n)**
- Navigate to position: O(left)
- Reverse operations: O(right - left)
- Total: O(n) worst case

**Space: O(1)**
- Only pointer variables
- Dummy node is O(1)
- In-place modification

## Dummy Node Pattern

**Why Dummy Node:**

**Problem without dummy:**
```
If left = 1, reversing from head
Need special case handling
More complex edge case logic
```

**Solution with dummy:**
```
Dummy points to head
No special case needed
Uniform handling for all positions
Always return dummy.next
```

## Edge Cases Handled

**Single node:** `left = right = 1` → No reversal needed, works ✓

**Reverse from head:** `left = 1` → Dummy handles this ✓

**Reverse entire list:** `left = 1, right = n` → Works like full reversal ✓

**Adjacent positions:** `left = 2, right = 3` → One iteration, works ✓

## Connection to Day 31

**Day 31 - Full Reversal:**
```
Reverse entire list
Three-pointer technique
Foundation pattern
```

**Day 39 - Partial Reversal:**
```
Reverse [left, right] only
Dummy node + repositioning
Extension of reversal
Boundary management added
```

**Evolution:** Basic pattern → Constrained pattern

## Common Mistakes Avoided

❌ No dummy node: Special case for left = 1
✓ Your solution: Dummy eliminates special case

❌ Wrong iteration count: Off-by-one errors
✓ Your solution: Exactly (right - left) iterations

❌ Lost connections: Forgetting to maintain links
✓ Your solution: All connections preserved

## Key Takeaways

**Algorithm Design:**
- Dummy node simplifies edge cases
- Node repositioning is intuitive
- Iteration count matches reversal size
- One-pass solution

**Pattern Recognition:**
- Extension of Day 31's reversal
- Dummy node pattern mastered
- Boundary condition handling
- In-place modification technique

**Interview Tips:**

Presentation:
1. "I'll use dummy node to handle edge cases"
2. "Navigate to position before reversal"
3. "Iteratively move nodes to front"
4. "O(n) time, O(1) space"

Follow-ups:
- Q: Why dummy node? A: Handles left = 1 without special case
- Q: Space complexity? A: O(1), in-place operations
- Q: What if left = right? A: No reversal, returns original

## Next Steps

**Similar problems:**
- Reverse Nodes in k-Group (#25) - Multiple reversals
- Swap Nodes in Pairs (#24) - Adjacent swaps
- Rotate List (#61) - Circular rotation

**Skills gained:**
- Partial list reversal
- Dummy node pattern
- Node repositioning technique
- Boundary management

|**Last Updated:** February 08, 2026|
|---|