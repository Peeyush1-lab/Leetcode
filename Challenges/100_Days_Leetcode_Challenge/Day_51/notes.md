# Notes - Insertion Sort List

## Problem Overview

Sort a linked list using the **insertion sort** algorithm. Unlike array insertion sort, we need to handle pointer manipulation instead of shifting elements.

## Insertion Sort Review

### How Insertion Sort Works (Arrays)

```
Array: [4, 2, 1, 3]

Step 1: [4] | 2, 1, 3     (4 is sorted)
Step 2: [2, 4] | 1, 3     (insert 2 before 4)
Step 3: [1, 2, 4] | 3     (insert 1 at beginning)
Step 4: [1, 2, 3, 4]      (insert 3 between 2 and 4)
```

**Concept:** Build sorted portion one element at a time by inserting each new element in its correct position.

### Adaptation for Linked Lists

**Array:** Shift elements to make space
**Linked List:** Change pointers to insert nodes

## Your Solution Strategy

### Key Idea

Maintain two portions of the list:
- **Sorted portion:** From dummy up to (and including) `prev`
- **Unsorted portion:** From `curr` onwards

For each `curr`:
- If already in order (`curr.val >= prev.val`): keep it, move forward
- If out of order: remove it, find insertion point, insert it

## Visual Walkthrough

### Example: head = [4,2,1,3]

```
Initial Setup:
dummy → 4 → 2 → 1 → 3 → null
        ↑   ↑
       prev curr

Step 1: curr.val = 2, prev.val = 4
  2 < 4, so OUT OF ORDER

  a) Remove curr from position:
     dummy → 4 ----→ 1 → 3
             ↑       ↑
            prev    curr (after removal)

  b) Find insertion point:
     temp starts at dummy
     temp.next.val (4) is NOT < 2, stop

  c) Insert 2:
     dummy → 2 → 4 → 1 → 3
             ↑   ↑   ↑
           temp prev curr

  Sorted: [2, 4], Unsorted: [1, 3]

Step 2: curr.val = 1, prev.val = 4
  1 < 4, so OUT OF ORDER

  a) Remove curr:
     dummy → 2 → 4 ----→ 3
                 ↑       ↑
                prev    curr

  b) Find insertion point:
     temp at dummy
     temp.next.val (2) is NOT < 1, stop at dummy

  c) Insert 1:
     dummy → 1 → 2 → 4 → 3
             ↑       ↑   ↑
           temp     prev curr

  Sorted: [1, 2, 4], Unsorted: [3]

Step 3: curr.val = 3, prev.val = 4
  3 < 4, so OUT OF ORDER

  a) Remove curr:
     dummy → 1 → 2 → 4 ----→ null
                     ↑       ↑
                    prev    curr

  b) Find insertion point:
     temp at dummy
     temp.next.val (1) < 3, move forward
     temp.next.val (2) < 3, move forward
     temp.next.val (4) is NOT < 3, stop

  c) Insert 3:
     dummy → 1 → 2 → 3 → 4 → null
                     ↑   ↑   ↑
                   temp prev curr

  Sorted: [1, 2, 3, 4], Unsorted: []

Step 4: curr = null, exit

Result: 1 → 2 → 3 → 4 ✅
```

## Code Breakdown

```java
public ListNode insertionSortList(ListNode head) {
    // Edge case: empty or single node
    if (head == null || head.next == null) return head;

    // Dummy node for easy insertion at beginning
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    // Pointers for traversal
    ListNode prev = head;       // Last node in sorted portion
    ListNode curr = head.next;  // Current node being processed

    while (curr != null) {

        // Case 1: Current node is already in correct position
        if (curr.val >= prev.val) {
            prev = curr;        // Extend sorted portion
            curr = curr.next;   // Move to next unsorted node
        }
        // Case 2: Current node is out of order
        else {
            // Step 1: Remove curr from its position
            prev.next = curr.next;

            // Step 2: Find correct insertion point
            ListNode temp = dummy;
            while (temp.next.val < curr.val) {
                temp = temp.next;
            }
            // temp.next is where curr should be inserted

            // Step 3: Insert curr after temp
            curr.next = temp.next;
            temp.next = curr;

            // Step 4: Move to next unsorted node
            curr = prev.next;
        }
    }

    return dummy.next;
}
```

## Key Design Choices

### 1. Why Dummy Node?

**Without dummy:**
```java
// What if we need to insert before head?
if (newNode < head) {
    newNode.next = head;
    head = newNode;  // Special case!
}
```

**With dummy:**
```java
// All insertions handled uniformly
temp.next = curr;  // Works even if inserting at beginning!
```

### 2. Why Keep `prev` Unchanged When Removing?

When we remove `curr` and insert it earlier:
- The sorted portion still ends at `prev`
- `prev.next` now points to the next unsorted node
- `prev` itself hasn't moved

```
Before removal:  ... → prev → curr → ...
After removal:   ... → prev ----→ ... (curr inserted earlier)
```

### 3. The Insertion Loop Logic

```java
while (temp.next.val < curr.val) {
    temp = temp.next;
}
```

This finds the **last node** with value less than `curr.val`.
Insert `curr` **after** this node.

**Example:** Insert 3 into [1, 2, 4]
```
temp at 1: 1 < 3, move forward
temp at 2: 2 < 3, move forward
temp at 4: 4 is NOT < 3, stop
Insert 3 between 2 and 4
```

## Edge Cases

### 1. Already Sorted
```java
Input: [1, 2, 3, 4]
Output: [1, 2, 3, 4]
```
✅ Always enters `if` branch, just moves forward, O(n) time

### 2. Reverse Sorted (Worst Case)
```java
Input: [4, 3, 2, 1]
Output: [1, 2, 3, 4]
```
✅ Every node out of order, each insertion scans from beginning, O(n²) time

### 3. Single Node
```java
Input: [1]
Output: [1]
```
✅ Early return at line 2

### 4. Two Nodes - Sorted
```java
Input: [1, 2]
Output: [1, 2]
```
✅ curr.val (2) >= prev.val (1), just move forward

### 5. Two Nodes - Unsorted
```java
Input: [2, 1]
Output: [1, 2]
```
✅ Remove 1, insert before 2

### 6. Duplicates
```java
Input: [3, 1, 2, 3]
Output: [1, 2, 3, 3]
```
✅ Works correctly, uses `>=` so equal values stay in order (stable sort)

## Common Mistakes

### ❌ Mistake 1: Not using dummy node

```java
ListNode sorted = head;  // No dummy
// Hard to insert before head!
```

**Fix:** Always use dummy for insertion problems.

### ❌ Mistake 2: Wrong insertion loop condition

```java
while (temp.next.val <= curr.val)  // WRONG!
```

This would insert equal values **after** existing ones, changing order.
Use `<` to maintain stability.

### ❌ Mistake 3: Moving prev when removing

```java
else {
    prev.next = curr.next;
    prev = prev.next;  // WRONG!
    // ... insert curr ...
}
```

`prev` should stay at the end of sorted portion, not move forward.

### ❌ Mistake 4: Forgetting to update curr

```java
else {
    // ... insert curr ...
    // Missing: curr = prev.next;
}
```

Without this, `curr` becomes disconnected from the list.

### ❌ Mistake 5: Not handling equal values

```java
if (curr.val > prev.val)  // WRONG! Misses equal case
```

Use `>=` to keep equal values in place (already sorted).

## Complexity Analysis

**Time Complexity:** O(n²)
- Outer loop: n nodes
- Inner loop (insertion): worst case O(n) for each node
- Best case: O(n) when already sorted
- Worst case: O(n²) when reverse sorted

**Space Complexity:** O(1)
- Only pointer variables: `dummy`, `prev`, `curr`, `temp`
- No recursion, no extra data structures
- In-place sorting

## Comparison: Insertion Sort vs Other Sorts

| Algorithm | Time (Best) | Time (Avg) | Time (Worst) | Space | Stable? |
|-----------|-------------|------------|--------------|-------|---------|
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ Yes |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ Yes |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ No |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ No |

**When to use Insertion Sort:**
✅ Small datasets (n < 50)
✅ Nearly sorted data (very efficient!)
✅ Stable sorting required
✅ Online sorting (data arrives one at a time)

## Why Insertion Sort on Linked Lists?

### Advantages
✅ **No shifting needed** - just pointer changes
✅ **O(1) insertion** - unlike arrays which need O(n) shifts
✅ **Stable** - maintains relative order of equal elements
✅ **In-place** - no extra space needed

### Disadvantages
❌ **Slow for large lists** - O(n²) average case
❌ **No random access** - must traverse to find position
❌ **Not cache-friendly** - scattered memory access

## Insertion Sort vs Merge Sort on Linked Lists

**Problem 148 (Sort List)** asks for O(n log n) time using merge sort:

```java
// Merge Sort has better time complexity
public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;

    // Find middle
    ListNode mid = findMiddle(head);
    ListNode left = head;
    ListNode right = mid.next;
    mid.next = null;

    // Recursively sort both halves
    left = sortList(left);
    right = sortList(right);

    // Merge sorted halves
    return merge(left, right);
}
```

**Insertion Sort:** O(n²) time, O(1) space, simpler code
**Merge Sort:** O(n log n) time, O(log n) space, more complex

## Interview Tips

1. **Explain insertion sort first** - "Build sorted portion one element at a time"
2. **Draw a diagram** - Show sorted/unsorted portions
3. **Explain dummy node benefit** - "Handles insertion at beginning uniformly"
4. **Walk through an example** - [4,2,1,3] works well
5. **Discuss complexity** - "O(n²) but good for small or nearly sorted lists"
6. **Mention alternatives** - "For larger lists, merge sort is O(n log n)"
7. **Highlight stability** - "Equal elements maintain their relative order"

## Related Problems with Hyperlinks

- [148. Sort List](https://leetcode.com/problems/sort-list/) (Medium) - Merge sort on linked list for O(n log n)
- [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/) (Easy) - Helper function for merge sort
- [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) (Hard) - Extension of merge concept
- [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/) (Easy) - Working with sorted lists
- [82. Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/) (Medium) - Remove all duplicates

## Key Takeaways

1. **Insertion sort builds sorted portion incrementally** - one node at a time
2. **Dummy node simplifies insertion** - no special head handling
3. **Two cases: in order (move forward) vs out of order (remove and insert)**
4. **O(n²) time but O(1) space** - good for small or nearly sorted lists
5. **Stable sorting** - maintains relative order of equal elements
6. **Pattern: sorted/unsorted portions** - common in sorting algorithms

Great implementation of insertion sort on a linked list! 