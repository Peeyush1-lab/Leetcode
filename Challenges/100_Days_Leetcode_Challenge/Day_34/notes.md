# Day 34 | Middle of the Linked List

**LeetCode #876** | Easy | Linked List, Two Pointers

## Problem Statement

Given the `head` of a singly linked list, return the **middle node** of the linked list.

If there are **two middle nodes**, return the **second middle** node.

**Examples:**

```
Example 1: [1,2,3,4,5] → [3,4,5]
Explanation: Middle node is 3

Example 2: [1,2,3,4,5,6] → [4,5,6]
Explanation: Two middle nodes (3 and 4), return second one

Example 3: [1] → [1]
Example 4: [1,2] → [2]
```

**Constraints:**
- Number of nodes: `[1, 100]`
- `1 <= Node.val <= 100`

## Solution Overview

**Algorithm:** Fast and Slow Pointer (Tortoise and Hare)

**Core Concept:** When fast moves at 2× speed and slow at 1× speed, slow will be at the middle when fast reaches the end.

**Implementation:**
```java
public ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
```

**Why this is perfect:** This is the **canonical** fast/slow pointer implementation. Only 7 lines, handles all cases automatically.

## How It Works

### Pointer Movement
- **slow** moves 1 step per iteration
- **fast** moves 2 steps per iteration
- When fast reaches end, slow is at middle

### Loop Condition
```java
while(fast != null && fast.next != null)
```
- `fast != null`: Handles even-length lists
- `fast.next != null`: Handles odd-length lists
- Both needed to cover all cases
- **Order matters:** Check `fast` first to avoid NullPointerException

### Visual Walkthrough

**Odd Length [1,2,3,4,5]:**
```
Start:    slow,fast → [1]
Step 1:   slow → [2], fast → [3]
Step 2:   slow → [3], fast → [5]
Stop:     fast.next == null
Return:   slow = [3] ✓
```

**Even Length [1,2,3,4,5,6]:**
```
Start:    slow,fast → [1]
Step 1:   slow → [2], fast → [3]
Step 2:   slow → [3], fast → [5]
Step 3:   slow → [4], fast → null
Stop:     fast == null
Return:   slow = [4] (second middle) ✓
```

## Mathematical Reasoning

**For list of length n:**
- Fast travels at 2× speed
- Slow travels at 1× speed
- When fast completes n steps, slow completes n/2 steps
- Slow position: ⌊n/2⌋ = middle

**Odd length (n=5):** Slow at position 2 (exact middle)
**Even length (n=6):** Slow at position 3 (second middle)

## Complexity Analysis

**Time Complexity: O(n)**
- Single pass through list
- Fast pointer covers full list in n/2 iterations

**Space Complexity: O(1)**
- Only two pointer variables
- No recursion or extra data structures

**Optimality:** This is the best possible solution. Cannot find middle faster than O(n) time.

## Why This Solution is Optimal

### Advantages
1. Minimal code (7 lines)
1. One pass solution
1. O(1) space
1. No edge case handling needed
1. Self-documenting logic
1. Interview gold standard

### Comparison with Alternatives

| Approach | Time | Space | Passes | Code Lines |
|----------|------|-------|--------|-----------|
| Count + traverse | O(n) | O(1) | 2 | ~15 |
| Array storage | O(n) | O(n) | 1 | ~10 |
| **Fast/slow** | **O(n)** | **O(1)** | **1** | **7** |

## Connection to Day 33

**Day 34 (Today) - Find Middle:**
```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;  // Just return middle
```

**Day 33 (Yesterday) - Delete Middle:**
```java
ListNode slow = head, fast = head, prev = null;
while (fast != null && fast.next != null) {
    prev = slow;       // Track previous
    slow = slow.next;
    fast = fast.next.next;
}
prev.next = slow.next; // Delete middle
return head;
```

**Insight:** Day 34 is the **foundation**. Day 33 **extends** it with deletion logic. Same core algorithm, different application.

## Fast/Slow Pattern Template

**Universal template:**
```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
return slow;  // slow is at middle
```

**Variations for different problems:**
- **Cycle detection (#141):** Add `if (slow == fast) return true;`
- **Delete middle (#2095):** Track `prev`, delete with `prev.next = slow.next`
- **Palindrome (#234):** Find middle, then reverse second half
- **Reorder list (#143):** Find middle, split list at slow

## Common Mistakes

### 1. Wrong Loop Condition
```java
// ❌ Only checks fast.next - fails on even lists
while (fast.next != null)

// ✓ Checks both conditions
while (fast != null && fast.next != null)
```

### 2. Incorrect Condition Order
```java
// ❌ NullPointerException if fast is null
while (fast.next != null && fast != null)

// ✓ Short-circuit evaluation protects
while (fast != null && fast.next != null)
```

## Interview Tips

**What to say:**
1. "I'll use fast and slow pointers"
2. "Fast moves 2×, slow 1×, slow ends at middle"
3. "O(n) time, O(1) space, one pass"

**How to present:**
- Write the clean solution (7 lines)
- Walk through odd and even examples
- Mention it handles edge cases automatically
- Connect to cycle detection if asked

## Related Problems

**Direct applications:**
- Linked List Cycle (#141)
- Palindrome Linked List (#234)
- Reorder List (#143)
- Delete Middle Node (#2095) - Day 33

**Similar patterns:**
- Find Duplicate Number (#287)
- Happy Number (#202)
- Linked List Cycle II (#142)

## Key Takeaways

### Algorithm Mastery
- Fast/slow is a fundamental pattern
- This is the purest, simplest form
- Building block for complex problems
- Minimal code = deep understanding

### Problem-Solving Insights
1. Simple problems teach essential patterns
1. Foundation enables complex solutions
1. Less code often means better algorithm
1. Complex → Simple reinforces learning

### Linked List Journey Complete
- Day 30: Design operations ✓
- Day 31: Three-pointer reversal ✓
- Day 32: Two-pass/gap method ✓
- Day 33: Fast/slow with deletion ✓
- Day 34: **Pure fast/slow mastery** ✓

**Result:** Complete linked list toolkit assembled!

## Daily Reflection

**What Went Well:**
- Perfect implementation
- Minimal, elegant code
- Foundation pattern mastered
- Connection to Day 33 understood

**Skills Demonstrated:**
- Pattern recognition
- Clean code writing
- Algorithm optimization
- Conceptual understanding

**Next Steps:**
- Linked List Cycle (#141) - Same pattern, add cycle check
- Palindrome List (#234) - Use middle to split
- Advanced cycle detection (#142)



**Milestone:** Linked list fundamentals complete! From design to all pointer patterns in 5 days. Solid foundation achieved!

|**Last Updated:** February 03, 2026|
|---|