# Notes - Remove Duplicates from Sorted List II

## Problem Overview

Remove **ALL occurrences** of duplicate values from a sorted linked list, keeping only nodes with unique values. This is different from the easier version (LeetCode 83) which keeps one copy of each duplicate.

## The Challenge

**Input:** `1 → 2 → 3 → 3 → 4 → 4 → 5`
**Output:** `1 → 2 → 5`

Notice:
- Values 3 and 4 appear multiple times
- **ALL instances** of 3 and 4 are removed
- Only truly unique values (1, 2, 5) remain

## Why Use a Dummy Node?

### The Problem Without Dummy

What if the head itself is a duplicate?

```
Input: [1,1,2,3]
Expected: [2,3]

Without dummy, you need special logic:
if (head is duplicate) {
    // Complex head handling
}
// Then handle rest
```

### The Solution With Dummy

```java
ListNode dummy = new ListNode(0, head);
// Now head is just another node!
// prev starts at dummy, which is never removed
```

**Benefits:**
- ✅ Uniform handling for all nodes (including head)
- ✅ No special cases
- ✅ Safe return value: `dummy.next` (the new head)

## Solution Strategy: Two Pointers

### Pointer Roles

1. **`prev`** (Previous keeper)
   - Points to the last node we're **certain** is unique
   - Only moves when we find a unique node
   - Its `next` pointer gets updated when we skip duplicates

2. **`curr`** (Current explorer)
   - Scans through the list
   - Detects duplicate groups
   - Always moves forward

### The Two Cases

**Case 1: Current node has duplicates**
```
Skip ALL occurrences of curr.val
prev.next = the node after last duplicate
Keep prev where it is
```

**Case 2: Current node is unique**
```
prev = prev.next (move prev forward)
```

## Visual Walkthrough

### Example: head = [1,2,3,3,4,4,5]

```
Initial Setup:
dummy → 1 → 2 → 3 → 3 → 4 → 4 → 5
  ↑     ↑
 prev  curr

Step 1: curr.val = 1, next.val = 2 (different → unique)
  prev = prev.next
  curr = curr.next

dummy → 1 → 2 → 3 → 3 → 4 → 4 → 5
        ↑   ↑
       prev curr

Step 2: curr.val = 2, next.val = 3 (different → unique)
  prev = prev.next
  curr = curr.next

dummy → 1 → 2 → 3 → 3 → 4 → 4 → 5
            ↑   ↑
           prev curr

Step 3: curr.val = 3, next.val = 3 (DUPLICATE!)
  Inner loop: skip all 3's
    curr = curr.next  (now at second 3)
    curr.next still = next 3, so continue
    curr = curr.next  (now at 4)

  prev.next = curr.next (skip all 3's)
  curr = curr.next

dummy → 1 → 2 → 4 → 4 → 5
            ↑   ↑
           prev curr

Step 4: curr.val = 4, next.val = 4 (DUPLICATE!)
  Inner loop: skip all 4's
    curr = curr.next  (now at second 4)
    curr = curr.next  (now at 5)

  prev.next = curr.next (skip all 4's)
  curr = curr.next

dummy → 1 → 2 → 5
            ↑   ↑
           prev curr

Step 5: curr.val = 5, next = null (unique)
  prev = prev.next
  curr = curr.next

dummy → 1 → 2 → 5
                ↑   ↑
               prev curr (null)

Loop exits (curr == null)

Return: dummy.next = 1 → 2 → 5 ✅
```

## Code Breakdown

```java
public ListNode deleteDuplicates(ListNode head) {
    // Create dummy node pointing to head
    ListNode dummy = new ListNode(0, head);

    ListNode prev = dummy;  // Last known unique node
    ListNode curr = head;   // Current node being examined

    while (curr != null) {
        // Check if current has duplicates
        if (curr.next != null && curr.val == curr.next.val) {
            // DUPLICATE GROUP FOUND

            // Skip ALL nodes with curr.val
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }

            // curr is now at the LAST duplicate
            // prev.next should point to the node AFTER last duplicate
            prev.next = curr.next;

            // DON'T move prev - it stays at last known unique

        } else {
            // UNIQUE NODE FOUND
            prev = prev.next;  // Move prev forward to include this node
        }

        // Always move curr forward
        curr = curr.next;
    }

    return dummy.next;  // New head of the list
}
```

## Critical Points

### 1. Why Two While Loops?

**Outer loop:** Traverses entire list
```java
while (curr != null)
```

**Inner loop:** Skips all duplicates of current value
```java
while (curr.next != null && curr.val == curr.next.val)
```

The inner loop stops at the **last occurrence** of the duplicate value.

### 2. Why `prev.next = curr.next` and Not `curr`?

After the inner loop, `curr` points to the **last duplicate**. We want to skip ALL duplicates, so we point to `curr.next` (the first different value).

```
Before: prev → 3 → 3 → 3 → 4
                    ↑
                   curr (last 3)

After: prev → 4
```

### 3. Why Don't We Move `prev` When Deleting?

`prev` should always point to a node we're **keeping**. When we delete duplicates, we haven't found the next keeper yet, so `prev` stays put!

## Edge Cases

### 1. Empty List
```java
Input: head = null
Output: null
```
✅ Works: `curr` is null, loop doesn't execute, returns `dummy.next = null`

### 2. All Nodes Are Duplicates
```java
Input: head = [1,1,1]
Output: null
```
✅ Works: All nodes skipped, `dummy.next` becomes null

### 3. No Duplicates
```java
Input: head = [1,2,3,4,5]
Output: [1,2,3,4,5]
```
✅ Works: Always enters the `else` branch, `prev` moves through entire list

### 4. Duplicates at Head
```java
Input: head = [1,1,2,3]
Output: [2,3]
```
✅ Works: `prev` stays at dummy, skips the 1's, `dummy.next` becomes 2

### 5. Duplicates at Tail
```java
Input: head = [1,2,3,3]
Output: [1,2]
```
✅ Works: Skips the 3's, `prev.next` becomes null

### 6. Single Node
```java
Input: head = [1]
Output: [1]
```
✅ Works: No next node, enters `else`, returns [1]

## Common Mistakes

### ❌ Mistake 1: Not using dummy node

```java
ListNode prev = null;
ListNode curr = head;
// How do you handle head being a duplicate?
```

**Fix:** Always use dummy node for deletion problems.

### ❌ Mistake 2: Moving prev when deleting

```java
if (curr.next != null && curr.val == curr.next.val) {
    while (curr.next != null && curr.val == curr.next.val) {
        curr = curr.next;
    }
    prev.next = curr.next;
    prev = prev.next;  // WRONG! Don't move prev here
}
```

**Fix:** Only move `prev` in the `else` branch (when keeping a node).

### ❌ Mistake 3: Wrong inner loop condition

```java
// This only skips ONE duplicate, not all!
if (curr.next != null && curr.val == curr.next.val) {
    curr = curr.next;  // Skips only 1
    prev.next = curr.next;
}
```

**Fix:** Use a while loop to skip **all** occurrences.

### ❌ Mistake 4: Setting prev.next = curr instead of curr.next

```java
while (curr.next != null && curr.val == curr.next.val) {
    curr = curr.next;
}
prev.next = curr;  // WRONG! curr is the last duplicate
```

**Fix:** Use `prev.next = curr.next` to skip past the last duplicate.

## Comparison: Problem 82 vs 83

| Aspect | 82 (This Problem) | 83 (Easier Version) |
|--------|-------------------|---------------------|
| **Remove** | ALL duplicates | Keep ONE copy |
| **Example** | [1,1,2] → [2] | [1,1,2] → [1,2] |
| **Difficulty** | Medium | Easy |
| **Dummy needed?** | Yes (head might be removed) | No (head is safe) |
| **Pointer logic** | Skip entire duplicate group | Skip extras, keep first |

## Complexity Analysis

**Time Complexity:** O(n)
- Outer loop: visits each node once
- Inner loop: each node visited at most once total
- Combined: O(n)

**Space Complexity:** O(1)
- Only using `dummy`, `prev`, `curr` pointers
- No recursion, no extra data structures

## Alternative Approach: Recursive

```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) return head;

    if (head.val == head.next.val) {
        // Skip all nodes with head.val
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        // Recurse on the node after all duplicates
        return deleteDuplicates(head.next);
    } else {
        // Keep this node, recurse on rest
        head.next = deleteDuplicates(head.next);
        return head;
    }
}
```

**Pros:** More concise, elegant
**Cons:** O(n) space due to recursion stack

## Key Takeaways

1. **Dummy node is essential** - handles head deletion cleanly
2. **Inner while loop** - skips ALL occurrences, not just one
3. **`prev` only moves when keeping** - stays put during deletions
4. **`prev.next = curr.next`** - skip past the last duplicate
5. **Sorted list property** - duplicates are consecutive, making detection easy
6. **Two pointer pattern** - `prev` tracks last keeper, `curr` explores

## Interview Tips

1. **Clarify the requirement:** "Remove ALL duplicates, not keep one copy?"
2. **Draw a diagram:** Visual helps explain the pointer movements
3. **Explain dummy node benefit:** "Eliminates special head handling"
4. **Walk through duplicate case:** Show inner loop skipping all occurrences
5. **Mention the sorted property:** "Makes duplicate detection O(1)"
6. **Compare with Problem 83:** Shows you understand the subtle difference

Excellent solution! The nested while loop pattern is perfect for this problem! 