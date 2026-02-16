# Notes - Delete Nodes From Linked List Present in Array

## Problem Overview

Remove all nodes from a linked list whose values appear in a given array. This combines array lookup with linked list manipulation.

## Solution Strategy

### Two Key Components

1. **Fast Lookup:** HashSet for O(1) value checking
2. **Safe Deletion:** Dummy node to handle all cases uniformly

## The Dummy Node Technique

### Why Use a Dummy Node?

**Problem without dummy:**
```java
// What if head needs to be deleted?
if (nums contains head.val) {
    head = head.next;  // Special case!
}
// Then handle the rest...
```

**Solution with dummy:**
```java
ListNode dummy = new ListNode(-1);
dummy.next = head;
// Now head is just another node! No special case needed.
```

### Benefits

✅ **Uniform handling:** Head deletion is no different from any other deletion
✅ **Cleaner code:** No if-else for "is this the head?" checks
✅ **Easy return:** Just return `dummy.next` (the new head)
✅ **Edge case safety:** Works even if all nodes are deleted

## Step-by-Step Walkthrough

### Example: nums = [1,2,3], head = [1,2,3,4,5]

```
Step 0: Setup
HashSet: {1, 2, 3}

dummy → 1 → 2 → 3 → 4 → 5 → null
 ↑      ↑
prev   curr

Step 1: curr.val = 1 (in HashSet, DELETE)
prev.next = curr.next (skip 1)
prev stays at dummy

dummy → 2 → 3 → 4 → 5 → null
 ↑      ↑
prev   curr

Step 2: curr.val = 2 (in HashSet, DELETE)
prev.next = curr.next (skip 2)
prev stays at dummy

dummy → 3 → 4 → 5 → null
 ↑      ↑
prev   curr

Step 3: curr.val = 3 (in HashSet, DELETE)
prev.next = curr.next (skip 3)
prev stays at dummy

dummy → 4 → 5 → null
 ↑      ↑
prev   curr

Step 4: curr.val = 4 (NOT in HashSet, KEEP)
prev = curr (move prev forward)

dummy → 4 → 5 → null
        ↑   ↑
       prev curr

Step 5: curr.val = 5 (NOT in HashSet, KEEP)
prev = curr (move prev forward)

dummy → 4 → 5 → null
            ↑   ↑
           prev curr

Step 6: curr = null, exit loop

Result: dummy.next = 4 → 5 → null
```

## Code Breakdown

```java
public ListNode modifiedList(int[] nums, ListNode head) {
    // Step 1: Build HashSet for O(1) lookup
    Set<Integer> arr = new HashSet<Integer>();
    for(int i : nums) {
        arr.add(i);
    }

    // Step 2: Create dummy node
    ListNode dummy = new ListNode(-1);
    ListNode temp = dummy;  // temp is not used in your code
    temp.next = head;       // Could just do: dummy.next = head;

    // Step 3: Initialize pointers
    ListNode prev = dummy;  // Previous "kept" node
    ListNode curr = head;   // Current node being examined
    ListNode next;          // Next node to visit

    // Step 4: Traverse and delete
    while(curr != null) {
        next = curr.next;  // Save next before we potentially break the link

        if(arr.contains(curr.val)) {
            // DELETE: Skip current node
            prev.next = next;
            // IMPORTANT: Don't move prev! It still points to last kept node
        } else {
            // KEEP: Move prev forward
            prev = curr;
        }

        curr = next;  // Move to next node
    }

    // Step 5: Return new head
    return dummy.next;
}
```

## Why This Pattern Works

### The Critical Insight

```
When deleting:    prev.next = next;  (skip curr)
                  prev STAYS put

When keeping:     prev = curr;       (move prev forward)
```

**Why?** `prev` always points to the **last node we decided to keep**. When we delete, the last kept node is still the same!

## Visual: Delete vs Keep

### Deleting a Node

```
Before:
prev → curr → next → ...

After deletion:
prev -------→ next → ...
(prev didn't move, just updated its next pointer)
```

### Keeping a Node

```
Before:
prev → curr → next → ...

After keeping:
       prev → next → ...
       (prev moved to curr)
```

## Edge Cases Handled

### 1. Delete All Nodes Except Last

```java
Input: nums = [1,2,3,4], head = [1,2,3,4,5]
Output: [5]
```
✅ Works: dummy.next points to 5

### 2. Delete Nothing

```java
Input: nums = [6,7,8], head = [1,2,3,4,5]
Output: [1,2,3,4,5]
```
✅ Works: No deletions, prev moves through entire list

### 3. Delete Head Only

```java
Input: nums = [1], head = [1,2,3]
Output: [2,3]
```
✅ Works: dummy.next becomes 2

### 4. Consecutive Deletions

```java
Input: nums = [1,2], head = [1,2,3,4]
Output: [3,4]
```
✅ Works: prev stays at dummy while deleting 1 and 2

### 5. Alternating Delete/Keep

```java
Input: nums = [2,4], head = [1,2,3,4,5]
Output: [1,3,5]
```
✅ Works: prev moves on 1, stays on 1 during deletion of 2, moves to 3, etc.

## Common Mistakes

### ❌ Mistake 1: Moving prev when deleting

```java
if(arr.contains(curr.val)) {
    prev.next = next;
    prev = prev.next;  // WRONG! Prev should stay put
}
```

This breaks the chain of kept nodes.

### ❌ Mistake 2: Not using dummy node

```java
// Trying to handle head deletion separately
if(arr.contains(head.val)) {
    head = head.next;
}
ListNode curr = head;
// ... more code
```

This is error-prone and requires special cases.

### ❌ Mistake 3: Not saving next before deletion

```java
if(arr.contains(curr.val)) {
    prev.next = curr.next;
}
curr = curr.next;  // If curr was deleted, curr.next might be wrong!
```

**Fix:** Save `next` at the beginning of each iteration.

### ❌ Mistake 4: Using ArrayList instead of HashSet

```java
List<Integer> arr = new ArrayList<>();  // O(n) lookup!
```

This makes the overall complexity O(n×m) instead of O(n+m).

## Complexity Analysis

**Time Complexity:** O(n + m)
- Building HashSet: O(m) where m = `nums.length`
- Traversing list: O(n) where n = number of nodes
- Each contains() call: O(1) with HashSet
- Total: O(m + n)

**Space Complexity:** O(m)
- HashSet stores m unique values from nums array
- Dummy node and pointers: O(1)
- Total: O(m)

## Alternative Approaches

### Approach 1: Without Dummy Node (More Complex)

```java
public ListNode modifiedList(int[] nums, ListNode head) {
    Set<Integer> set = new HashSet<>();
    for(int num : nums) set.add(num);

    // Handle head deletions separately
    while(head != null && set.contains(head.val)) {
        head = head.next;
    }

    if(head == null) return null;

    // Handle rest of the list
    ListNode curr = head;
    while(curr != null && curr.next != null) {
        if(set.contains(curr.next.val)) {
            curr.next = curr.next.next;
        } else {
            curr = curr.next;
        }
    }

    return head;
}
```

**Cons:** More code, special head handling, less elegant

### Approach 2: Recursive (Less Efficient)

```java
public ListNode modifiedList(int[] nums, ListNode head) {
    Set<Integer> set = new HashSet<>();
    for(int num : nums) set.add(num);
    return helper(head, set);
}

private ListNode helper(ListNode node, Set<Integer> set) {
    if(node == null) return null;

    node.next = helper(node.next, set);

    if(set.contains(node.val)) {
        return node.next;  // Skip this node
    }
    return node;  // Keep this node
}
```

**Cons:** O(n) space for recursion stack, less intuitive

## Why Your Solution is Optimal

✅ **Uses dummy node** - clean and elegant
✅ **HashSet for fast lookup** - O(1) contains
✅ **Correct pointer management** - prev only moves when keeping
✅ **Single pass** - traverse list once
✅ **Handles all edge cases** - no special conditions needed

## Related Problems

- **Remove Linked List Elements (LeetCode 203)** - Delete nodes with specific value
- **Delete Node in a Linked List (LeetCode 237)** - Delete given node
- **Remove Duplicates from Sorted List (LeetCode 83)** - Remove duplicate values
- **Remove Nth Node From End of List (LeetCode 19)** - Position-based deletion

## Key Takeaways

1. **Dummy nodes** simplify linked list deletion problems
2. **HashSet** enables O(1) lookup for array values
3. **prev pointer** tracks the last "kept" node
4. Only move `prev` when **keeping** a node, not when deleting
5. Save `next` before modifying pointers to avoid losing the chain
6. Pattern: Convert array → HashSet → Traverse with dummy node

## Interview Tips

When presenting this solution:

1. **Explain the dummy node benefit:** "It eliminates special head handling"
2. **Justify the HashSet:** "O(1) lookup vs O(m) with array search"
3. **Walk through an example:** Show delete and keep scenarios
4. **Highlight the prev logic:** "Only move prev when we keep a node"
5. **Mention complexity:** O(n+m) time, O(m) space
6. **Compare alternatives:** Show you understand tradeoffs

Great job solving this problem!