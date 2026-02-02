# Day 33 | Delete the Middle Node of a Linked List

**LeetCode #2095** | Medium | Linked List, Two Pointers

## Problem Statement

Given the `head` of a singly linked list, delete the **middle** node and return the head of the modified list.

The middle node of a linked list of size `n` is the `⌊n / 2⌋th` node from the start (0-indexed).

**Examples:**

```
Example 1:
Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation: Middle node is 7 (index 3)

Example 2:
Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation: Middle node is 3 (index 2)

Example 3:
Input: head = [2,1]
Output: [2]
Explanation: Middle node is 1 (index 1)

Example 4:
Input: head = [1]
Output: []
Explanation: Only one node, remove it
```

**Constraints:**
- Number of nodes: `[1, 10^5]`
- `1 <= Node.val <= 10^5`

## Solution Overview

**Algorithm:** Fast and Slow Pointer Technique (Tortoise and Hare)

**Key Insight:** When a fast pointer moves at 2× speed and a slow pointer at 1× speed, when fast reaches the end, slow will be at the exact middle of the list.

**Core Strategy:**
1. Use three pointers: `slow`, `fast`, and `prev`
2. Move `fast` two steps and `slow` one step each iteration
3. Track `prev` (node before slow) for deletion
4. When `fast` reaches end, delete `slow` using `prev`

## Implementation Breakdown

### Edge Case Handling
```java
if (head == null || head.next == null)
    return null;
```

**Why this check?**
- Empty list: No nodes to delete → return null
- Single node: Deleting it makes list empty → return null
- Two or more nodes: Proceed with algorithm

### Pointer Initialization
```java
ListNode slow = head;
ListNode fast = head;
ListNode prev = null;
```

**Pointer roles:**
- `slow`: Moves 1 step per iteration, ends at middle
- `fast`: Moves 2 steps per iteration, determines when to stop
- `prev`: Tracks node before slow, needed for deletion

### Main Loop
```java
while (fast != null && fast.next != null) {
    prev = slow;
    slow = slow.next;
    fast = fast.next.next;
}
```

**Loop mechanics:**
- **Condition:** `fast != null && fast.next != null`
  - Handles both odd and even length lists
  - Stops when fast can't move 2 more steps
- **Updates:**
  1. Save current slow as prev (before moving slow)
  2. Move slow one step forward
  3. Move fast two steps forward

### Deletion
```java
prev.next = slow.next;
return head;
```

**Deletion logic:**
- `prev.next = slow.next` bypasses the middle node
- Returns original head (unchanged)

## Visual Walkthrough

### Example 1: Odd Length List [1,2,3,4,5]

```
Initial State:
slow,fast
   ↓
[1] → [2] → [3] → [4] → [5] → null

Iteration 1:
prev slow    fast
  ↓   ↓       ↓
[1] → [2] → [3] → [4] → [5] → null

Iteration 2:
     prev slow         fast
       ↓   ↓            ↓
[1] → [2] → [3] → [4] → [5] → null

fast.next == null → STOP

slow is at [3] (middle)
prev.next = slow.next → [2] → [4]

Result: [1] → [2] → [4] → [5] → null ✓
```

### Example 2: Even Length List [1,2,3,4]

```
Initial State:
slow,fast
   ↓
[1] → [2] → [3] → [4] → null

Iteration 1:
prev slow    fast
  ↓   ↓       ↓
[1] → [2] → [3] → [4] → null

Iteration 2:
     prev slow         fast=null
       ↓   ↓
[1] → [2] → [3] → [4] → null

fast == null → STOP

slow is at [3] (middle, 0-indexed ⌊4/2⌋ = 2)
prev.next = slow.next → [2] → [4]

Result: [1] → [2] → [4] → null ✓
```

## Why This Algorithm Works

### Mathematical Proof

**For list of length n:**
- Fast pointer travels: 2k steps (where k = iterations)
- Slow pointer travels: k steps
- Fast stops when: 2k ≈ n
- Therefore: k ≈ n/2
- Slow position: k = ⌊n/2⌋ (exactly middle!)

### Loop Termination Conditions

| List Length | Final Fast Position | Final Slow Position |
|-------------|-------------------|-------------------|
| Odd (5 nodes) | null (after 5th) | 3rd node (⌊5/2⌋) |
| Even (4 nodes) | null (exactly) | 3rd node (⌊4/2⌋) |

**Why two conditions?**
- `fast != null`: Handles even-length lists (fast lands on null)
- `fast.next != null`: Handles odd-length lists (fast lands on last node)

## Complexity Analysis

### Time Complexity: O(n)
- Single traversal through list
- Fast pointer visits each node once (in 2-step jumps)
- Slow pointer visits half the nodes
- Total: n/2 iterations × O(1) work = O(n)

### Space Complexity: O(1)
- Only three pointer variables used
- No recursion or additional data structures
- Constant extra space

### Comparison with Alternatives

| Approach | Time | Space | Passes | Complexity |
|----------|------|-------|--------|-----------|
| Count size, then traverse | O(n) | O(1) | 2 | Simple |
| **Fast/slow (your solution)** | **O(n)** | **O(1)** | **1** | **Elegant** |
| Store in array | O(n) | O(n) | 1 | Wasteful |

## Critical Implementation Details

### 1. Why Track `prev`?

**Cannot delete a node from itself:**
```java
// Wrong - can't do this:
slow.next = slow.next.next;  // Doesn't delete slow itself!

// Correct - need previous node:
prev.next = slow.next;  // Bypasses slow ✓
```

### 2. Order of Operations in Loop

**Critical order:**
```java
prev = slow;          // 1. Save current slow (BEFORE moving)
slow = slow.next;     // 2. Move slow forward
fast = fast.next.next; // 3. Move fast forward
```

**Why this order matters:**
```java
// Wrong order:
slow = slow.next;     // Move slow first
prev = slow;          // ❌ Now prev points to NEW slow position!

// Correct order:
prev = slow;          // ✓ Save OLD slow position
slow = slow.next;     // Then move slow
```

### 3. Loop Condition Order

```java
while (fast != null && fast.next != null)
```

**Must check `fast != null` FIRST:**
- If `fast` is null, `fast.next` throws NullPointerException
- Short-circuit evaluation prevents error
- Order matters: `fast.next != null && fast != null` would fail!

## Edge Cases Analysis

### Case 1: Empty List
```java
Input: head = null
Check: head == null → return null ✓
```

### Case 2: Single Node
```java
Input: head = [1]
Check: head.next == null → return null ✓
Explanation: List becomes empty after deletion
```

### Case 3: Two Nodes
```java
Input: head = [1,2]

Initial: slow,fast at [1]
Iteration 1:
  prev = [1]
  slow = [2]
  fast = null (moved 2 steps)
Loop exits
Delete: prev.next = slow.next → [1] → null

Result: [1] ✓ (deleted 2nd node, which is middle)
```

### Case 4: Three Nodes
```java
Input: head = [1,2,3]

Initial: slow,fast at [1]
Iteration 1:
  prev = [1], slow = [2], fast = [3]
Iteration 2:
  fast.next = null → STOP
  slow at [2] (middle)

Delete: prev.next = slow.next → [1] → [3]

Result: [1,3] ✓
```

## Common Mistakes and Solutions

### Mistake 1: Not Tracking Previous
```java
// Wrong - no way to delete slow
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
// slow is at middle, but how to delete it? ❌
```

**Solution:** Always track `prev` before moving `slow`

### Mistake 2: Wrong Loop Condition
```java
// Wrong - doesn't handle both odd and even
while (fast.next != null) {
    // Only works for odd-length lists ❌
}
```

**Solution:** Use both conditions: `fast != null && fast.next != null`

### Mistake 3: Incorrect Edge Case Handling
```java
// Wrong - doesn't check for single node
if (head == null) return null;
// Forgets head.next == null case ❌
```

**Solution:** Check both `head == null` and `head.next == null`

## Fast/Slow Pointer Pattern - General Template

This pattern appears in many problems:

```java
// Template for fast/slow pointer
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;        // Move 1 step
    fast = fast.next.next;   // Move 2 steps

    // At loop exit:
    // - slow is at middle (for middle-finding)
    // - slow meets fast (for cycle detection)
}
```

**Variations:**
1. **Find middle:** Return slow after loop
2. **Delete middle:** Track prev, delete slow
3. **Detect cycle:** Check if slow == fast inside loop
4. **Palindrome check:** Reverse from middle using slow

## Related Problems Using This Pattern

### 1. Middle of the Linked List (#876)
```java
// Just return slow, don't delete
return slow;
```

### 2. Linked List Cycle (#141)
```java
// Check if pointers meet
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) return true;  // Cycle detected
}
return false;
```

### 3. Palindrome Linked List (#234)
```java
// Find middle with slow/fast
// Reverse second half starting from slow
// Compare two halves
```

### 4. Reorder List (#143)
```java
// Find middle with slow/fast
// Reverse second half
// Merge two halves alternately
```

## Performance Insights

**Why this is optimal:**

1. **Single Pass:** Can't do better than O(n) - must visit nodes
2. **Constant Space:** Can't use less than O(1) - need pointers
3. **No Size Calculation:** More elegant than two-pass
4. **In-Place:** No auxiliary data structures

**Interview advantages:**
- Shows knowledge of classic algorithm
- Demonstrates pointer manipulation skill
- Proves understanding of optimization
- Clean, readable code

## Key Takeaways

### Algorithm Fundamentals
1. Fast/slow pointer is a powerful technique
1. Speed differential (2× vs 1×) finds middle in one pass
1. Previous pointer enables deletion
1. Edge cases require careful handling

### Coding Best Practices
- Check edge cases first
- Initialize all pointers clearly
- Order of operations matters in loops
- Short-circuit evaluation prevents errors

## Daily Reflection

**What Went Well:**
- Perfect implementation of fast/slow pattern
- Correct edge case handling
- Clean, readable code
- One-pass optimization achieved

**Skills Demonstrated:**
- Two-pointer technique mastery
- Pointer manipulation
- Edge case analysis
- Algorithm optimization

**Linked List Journey:**
- Day 30: Design and basic operations
- Day 31: Reversal with three pointers
- Day 32: Two-pass deletion from end
- Day 33: **Fast/slow pointer mastery** ✓

**Pattern Collection Growing:**
- Three-pointer reversal ✓
- Two-pointer gap method ✓
- Fast/slow pointer technique ✓
- **Complete linked list toolkit assembled!**

## Next Steps

**Recommended Practice:**
1. Linked List Cycle (#141) - Apply fast/slow for cycle detection
2. Palindrome Linked List (#234) - Find middle then reverse
3. Intersection of Two Lists (#160) - Different two-pointer variant
4. Reorder List (#143) - Combines multiple patterns

**Skill Development:**
- Master all fast/slow variations
- Explore binary tree traversals (similar pointer concepts)
- Study Floyd's cycle detection algorithm
- Learn about dummy nodes pattern

**Milestone:** Complete linked list foundation built in 4 days! Ready for advanced variations.

|**Last Updated:** February 02, 2026|
|---|