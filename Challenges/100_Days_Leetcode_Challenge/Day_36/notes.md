# Day 36 Notes| Merge Two Sorted Lists
**LeetCode #21** · Easy
**Topics:** Linked List, Two Pointers, Iteration, Recursion

## Problem Statement

You are given the heads of two sorted linked lists `list1` and `list2`.
Merge the two lists into one sorted list by splicing together their nodes and return the head of the merged list.

### Examples

Input: `list1 = [1,2,4], list2 = [1,3,4]`
Output: `[1,1,2,3,4,4]`

Input: `list1 = [], list2 = []`
Output: `[]`

Input: `list1 = [], list2 = [0]`
Output: `[0]`

### Constraints
- Total nodes: `0 ≤ n ≤ 50`
- `-100 ≤ Node.val ≤ 100`
- Both lists are sorted in non-decreasing order

## Solution Overview

**Approach:** Iterative merge by creating a new result list.
The algorithm compares nodes from both lists, appends the smaller value, and advances pointers accordingly.

This approach preserves the original input lists and prioritizes clarity.

## High-Level Algorithm

1. Handle edge cases where either list is empty
2. Initialize the head using the smaller first value
3. Merge lists while both contain nodes
4. Append remaining nodes from the non-empty list

## Code Structure

```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) return list2;
    if (list2 == null) return list1;

    // Initialize head
    // Merge while both lists exist
    // Append remaining elements
    return head;
}
```

## Implementation Logic

### Edge Case Handling
If one list is null, the other list is already sorted and returned immediately.

### Head Initialization
The smaller of the two first values becomes the head of the merged list.
A tail pointer is used to build the list incrementally.

### Merge Loop
While both lists are non-null:
- Compare current values
- Append the smaller value
- Advance the corresponding pointer
- Move the tail forward

### Append Remaining Nodes
Once one list ends, append the remaining nodes from the other list directly.

## Example Walkthrough

For `list1 = [1,2,4]` and `list2 = [1,3,4]`, nodes are compared sequentially and appended in sorted order, resulting in:
`1 → 1 → 2 → 3 → 4 → 4`

## Complexity Analysis

**Time Complexity:** `O(n + m)`
Each node is visited exactly once.

**Space Complexity:** `O(n + m)`
A new node is created for each element in the merged list.

## Optimized Approach (O(1) Space)

```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;

    while (list1 != null && list2 != null) {
        if (list1.val <= list2.val) {
            tail.next = list1;
            list1 = list1.next;
        } else {
            tail.next = list2;
            list2 = list2.next;
        }
        tail = tail.next;
    }

    tail.next = (list1 != null) ? list1 : list2;
    return dummy.next;
}
```

This version reuses existing nodes, avoids new allocations, and reduces space usage to `O(1)`.

## Recursive Approach

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```

## Comparison Summary

| Approach | Time | Space | Notes |
|--------|------|-------|------|
| New list | O(n+m) | O(n+m) | Preserves inputs |
| Reuse nodes | O(n+m) | O(1) | Modifies inputs |
| Recursive | O(n+m) | O(n+m) | Uses call stack |

## Key Takeaways

- Merge algorithm is fundamental in computer science
- Dummy nodes simplify linked list construction
- Trade-offs exist between clarity and optimization
- This pattern appears frequently in advanced problems

## Interview Notes

Interviewers expect:
- Clean pointer handling
- Proper edge case checks
- Awareness of space optimization

Both iterative and optimized approaches are acceptable depending on constraints.

## Progress Log

Day 36 completed as part of **#100DaysOfLeetCode**.
Linked list merge pattern mastered and ready for extension to multi-list problems.

|**Last Updated:** February 05, 2026|
|---|
