# Notes - Swap Nodes in Pairs

## Problem Overview

Swap every two adjacent nodes in a linked list. **Important:** We must swap actual node positions, not just values!

**Input:** `1 → 2 → 3 → 4`
**Output:** `2 → 1 → 4 → 3`

## The Challenge

Unlike swapping values (which is trivial), swapping actual nodes requires careful pointer manipulation to avoid breaking the list.

## Why Dummy Node is Essential

### Without Dummy Node

```java
// What if we swap first two nodes?
// We need to update head!
if (head != null && head.next != null) {
    ListNode temp = head.next;
    head.next = temp.next;
    temp.next = head;
    head = temp;  // Special case! New head
}
// Then handle rest of the list...
```

### With Dummy Node

```java
// All pairs handled uniformly
ListNode dummy = new ListNode(-1);
dummy.next = head;
// Now first pair is just like any other pair!
```

The dummy node ensures we always have a `prev` pointer, even for the first pair.

## The Swapping Pattern

For each pair, we need to rewire **three pointers** in a specific order:

```
Before:
prev → first → second → rest

After:
prev → second → first → rest
```

### Critical: Order Matters!

```java
first.next = second.next;   // 1. Preserve connection to rest
second.next = first;         // 2. Point second back to first
prev.next = second;          // 3. Connect prev to new first of pair
```

**Why this order?**
- If we do step 3 first, we lose reference to `first`!
- If we do step 2 before step 1, we lose reference to `rest`!

## Visual Walkthrough

### Example: head = [1,2,3,4]

```
Initial Setup:
dummy → 1 → 2 → 3 → 4 → null
  ↑
prev

Iteration 1: Swap (1,2)

Step 0: Identify nodes
  first = prev.next = 1
  second = prev.next.next = 2

dummy → 1 → 2 → 3 → 4 → null
  ↑     ↑   ↑
prev  first second

Step 1: first.next = second.next
  1.next = 3

dummy → 1 ⤵
  ↑     ↓
prev    3 → 4 → null
  ↓
  2 (disconnected temporarily)

Step 2: second.next = first
  2.next = 1

dummy    2 → 1 → 3 → 4 → null
  ↑      ↑
prev   second

Step 3: prev.next = second
  dummy.next = 2

dummy → 2 → 1 → 3 → 4 → null
  ↑     ↑   ↑
prev  second first

Step 4: prev = first (move to next pair)

dummy → 2 → 1 → 3 → 4 → null
            ↑
          prev

Iteration 2: Swap (3,4)

Step 0: Identify nodes
  first = prev.next = 3
  second = prev.next.next = 4

dummy → 2 → 1 → 3 → 4 → null
            ↑   ↑   ↑
          prev first second

Step 1: first.next = second.next
  3.next = null

Step 2: second.next = first
  4.next = 3

Step 3: prev.next = second
  1.next = 4

dummy → 2 → 1 → 4 → 3 → null
            ↑   ↑   ↑
          prev second first

Step 4: prev = first

dummy → 2 → 1 → 4 → 3 → null
                    ↑
                  prev

Iteration 3: Check condition
  prev.next = null
  Loop exits

Return: dummy.next = 2 → 1 → 4 → 3 ✅
```

## Code Breakdown

```java
public ListNode swapPairs(ListNode head) {
    // Create dummy node to simplify head handling
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode prev = dummy;

    // While we have at least two nodes to swap
    while (prev.next != null && prev.next.next != null) {
        // Identify the pair
        ListNode first = prev.next;
        ListNode second = prev.next.next;

        // Rewire pointers (ORDER IS CRITICAL!)
        first.next = second.next;   // 1. Connect first to rest
        second.next = first;         // 2. Point second to first
        prev.next = second;          // 3. Connect prev to second

        // Move to next pair
        prev = first;  // first is now last node of swapped pair
    }

    return dummy.next;  // New head (might be different from original)
}
```

## Why Each Line is Important

### Line 1: `ListNode dummy = new ListNode(-1);`
Creates a stable reference point. Value doesn't matter (could be any value).

### Line 2: `dummy.next = head;`
Connects dummy to the list. Now `prev` can start at dummy.

### Line 3: `ListNode prev = dummy;`
`prev` always points to the node **before** the pair we're about to swap.

### Line 5: `while (prev.next != null && prev.next.next != null)`
- `prev.next != null`: At least one node remains
- `prev.next.next != null`: At least two nodes remain (a pair)

Both conditions needed! Without second condition, we'd crash on `second = prev.next.next`.

### Lines 6-7: Identify nodes
```java
ListNode first = prev.next;
ListNode second = prev.next.next;
```
These are the two nodes we'll swap.

### Line 9: `first.next = second.next;`
**Critical first step!** Preserve the connection to the rest of the list.
If we don't do this first, we'll lose access to nodes after `second`.

### Line 10: `second.next = first;`
Make `second` point back to `first`, reversing their connection.

### Line 11: `prev.next = second;`
Connect `prev` to `second`, which is now the first node of the pair.

### Line 13: `prev = first;`
Move `prev` forward. After swapping, `first` is now the last node of the pair, which becomes the new `prev` for the next iteration.

### Line 16: `return dummy.next;`
Return the new head. Could be different from original `head` if first pair was swapped.

## Edge Cases

### 1. Empty List
```java
Input: head = null
Output: null
```
✅ Works: `prev.next` is null, loop doesn't execute, returns null

### 2. Single Node
```java
Input: head = [1]
Output: [1]
```
✅ Works: `prev.next.next` is null, loop doesn't execute, returns [1]

### 3. Odd Number of Nodes
```java
Input: head = [1,2,3]
Output: [2,1,3]
```
✅ Works: Swaps (1,2), then `prev` is at 1, `prev.next.next` is null, loop exits, 3 stays in place

### 4. Even Number of Nodes
```java
Input: head = [1,2,3,4]
Output: [2,1,4,3]
```
✅ Works: Swaps (1,2) and (3,4), all pairs handled

### 5. Two Nodes
```java
Input: head = [1,2]
Output: [2,1]
```
✅ Works: Swaps the pair, loop exits, returns [2,1]

## Common Mistakes

### ❌ Mistake 1: Wrong pointer rewiring order

```java
prev.next = second;          // WRONG ORDER!
second.next = first;
first.next = second.next;    // Too late! second.next already changed
```

Always do `first.next = second.next` **first** to preserve the connection to rest of list.

### ❌ Mistake 2: Not using dummy node

```java
ListNode prev = null;  // WRONG! Can't handle first pair cleanly
```

Without dummy, you need special case handling for the first pair.

### ❌ Mistake 3: Wrong prev update

```java
prev = second;  // WRONG! Second is now first in pair
```

After swapping, `first` becomes the last node in the pair, which is the correct new `prev`.

### ❌ Mistake 4: Missing null checks

```java
while (prev.next != null) {  // WRONG! Missing second check
    ListNode second = prev.next.next;  // NullPointerException!
```

Need **both** conditions to ensure a pair exists.

### ❌ Mistake 5: Losing reference to nodes

```java
ListNode first = prev.next;
// Don't save second!
prev.next = prev.next.next;  // Lost reference to first!
```

Save references to both nodes before modifying any pointers.

### ❌ Mistake 6: Trying to swap values instead

```java
int temp = first.val;
first.val = second.val;
second.val = temp;
```

The problem explicitly states: "without modifying the values in the list's nodes"

## Complexity Analysis

**Time Complexity:** O(n)
- Visit each node exactly once
- Each swap operation is O(1)
- n/2 pairs to swap
- Total: O(n)

**Space Complexity:** O(1)
- Only using 4 pointer variables: `dummy`, `prev`, `first`, `second`
- No recursion (iterative solution)
- No extra data structures
- Space doesn't grow with input size

## Alternative Approach: Recursive

```java
public ListNode swapPairs(ListNode head) {
    // Base case: 0 or 1 node
    if (head == null || head.next == null) {
        return head;
    }

    // Identify pair
    ListNode first = head;
    ListNode second = head.next;

    // Recursively process rest
    first.next = swapPairs(second.next);

    // Swap current pair
    second.next = first;

    // Return new head of this segment
    return second;
}
```

**Pros:** More elegant, shorter code
**Cons:** O(n) space due to recursion stack

Your iterative solution is **more space-efficient**!

## Comparison: Iterative vs Recursive

| Aspect | Iterative (Your Solution) | Recursive |
|--------|---------------------------|-----------|
| **Time** | O(n) | O(n) |
| **Space** | O(1) | O(n) |
| **Code Length** | ~15 lines | ~10 lines |
| **Readability** | Clear loop | Elegant but abstract |
| **Interview** | Preferred (better space) | Good follow-up |

## The Pattern: Rewiring k Nodes

This problem is a special case of "reverse k nodes at a time":

**k=2:** This problem (swap pairs)
**k=3:** Reverse every 3 nodes
**k=n:** Reverse entire list

**Problem 25** (Reverse Nodes in k-Group) generalizes this pattern.

## Visual: Before vs After

```
Before swapping first pair:
dummy → 1 → 2 → 3 → 4

After swapping first pair:
dummy → 2 → 1 → 3 → 4
            ↑
          prev (ready for next pair)
```

The `prev = first` step is crucial - it positions us correctly for the next iteration.

## Interview Tips

1. **Start with the dummy node:**
   - "I'll use a dummy to avoid special-casing the head"

2. **Explain the three pointer rewires:**
   - "First, preserve connection to rest: `first.next = second.next`"
   - "Then, reverse pair: `second.next = first`"
   - "Finally, connect prev: `prev.next = second`"

3. **Draw the diagram:**
   ```
   prev → first → second → rest
          ↓       ↓
   prev → second → first → rest
   ```

4. **Walk through edge cases:**
   - "Odd length: last node stays in place"
   - "Single/zero nodes: loop doesn't run"

5. **Mention complexity:** "O(n) time, O(1) space - better than recursive"

6. **Discuss extension:** "This generalizes to k-group reversal"

## Related Problems with Hyperlinks

- [25. Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) (Hard) - Generalize to k nodes instead of 2
- [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) (Easy) - Foundation: reverse entire list
- [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/) (Medium) - Reverse a portion of list
- [1721. Swapping Nodes in a Linked List](https://leetcode.com/problems/swapping-nodes-in-a-linked-list/) (Medium) - Swap by values at specific positions
- [143. Reorder List](https://leetcode.com/problems/reorder-list/) (Medium) - Complex rearrangement pattern
- [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) (Medium) - Another pointer manipulation problem

## Key Takeaways

1. **Dummy node simplifies head handling** - No special cases needed
2. **Order of pointer rewiring is critical** - Do it wrong and you lose references
3. **Save references first** - Store `first` and `second` before modifying pointers
4. **Two null checks needed** - Ensure both nodes of pair exist
5. **Iterative beats recursive** - Same time, better space
6. **`prev = first` moves to next pair** - After swap, first is last node of pair
7. **Pattern extends to k-group** - Foundation for harder problems

Excellent iterative solution with optimal space complexity!