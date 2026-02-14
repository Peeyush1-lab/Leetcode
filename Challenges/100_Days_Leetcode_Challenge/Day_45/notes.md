# Notes - Intersection of Two Linked Lists

## Problem Overview

Find the node where two singly linked lists intersect. The lists may have different lengths before the intersection point. Once they intersect, they share the same nodes for the rest of the list.

## The Elegant Two-Pointer Solution

This is one of the most beautiful solutions in linked list problems. It eliminates the length difference between two lists using a simple trick.

### The Key Insight

**Problem:** Lists have different lengths, making it hard to align them.

**Solution:** Make both pointers traverse the same total distance!

- Pointer A travels: `List A → List B`
- Pointer B travels: `List B → List A`

Both traverse distance = `lengthA + lengthB`

## Visual Walkthrough

### Example 1: Lists with Intersection

```
List A: a1 → a2 → c1 → c2 → c3
List B: b1 → b2 → b3 → c1 → c2 → c3
```

**Length A = 5, Length B = 6, Intersection at c1**

```
Step-by-step pointer movement:

Iteration 1:  a = a1,  b = b1
Iteration 2:  a = a2,  b = b2
Iteration 3:  a = c1,  b = b3
Iteration 4:  a = c2,  b = c1
Iteration 5:  a = c3,  b = c2
Iteration 6:  a = null, b = c3
              a switches to headB

Iteration 7:  a = b1,  b = null
              b switches to headA

Iteration 8:  a = b2,  b = a1
Iteration 9:  a = b3,  b = a2
Iteration 10: a = c1,  b = c1  ← MATCH! Return c1
```

### Example 2: No Intersection

```
List A: 1 → 2 → 3
List B: 4 → 5
```

```
Iteration 1: a = 1, b = 4
Iteration 2: a = 2, b = 5
Iteration 3: a = 3, b = null → switches to headA
Iteration 4: a = null → switches to headB, b = 1
Iteration 5: a = 4, b = 2
Iteration 6: a = 5, b = 3
Iteration 7: a = null, b = null  ← MATCH! Return null
```

## Code Breakdown

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    // Handle edge case: if either list is empty, no intersection
    if (headA == null || headB == null) return null;

    ListNode a = headA;
    ListNode b = headB;

    // Keep moving until both pointers meet
    while (a != b) {
        // If pointer reaches end, switch to other list's head
        // Otherwise, move to next node
        a = (a == null) ? headB : a.next;
        b = (b == null) ? headA : b.next;
    }

    // When loop exits, either:
    // 1. a == b == intersection node
    // 2. a == b == null (no intersection)
    return a;
}
```

## Why This Works - Mathematical Proof

Let's say:
- Length of A before intersection = `a`
- Length of B before intersection = `b`
- Length of common part = `c`

**Case 1: Lists Intersect**

Total length of A = `a + c`
Total length of B = `b + c`

Pointer A travels: `a + c` (List A) then `b` (part of List B) = `a + c + b`
Pointer B travels: `b + c` (List B) then `a` (part of List A) = `b + c + a`

Both = `a + b + c` ← They meet at intersection!

**Case 2: No Intersection**

Total length of A = `a`
Total length of B = `b`

Pointer A travels: `a` then `b` = `a + b`
Pointer B travels: `b` then `a` = `b + a`

Both travel `a + b` steps and both end at `null` ← They meet at null!

## Alternative Approaches

### Approach 1: HashSet (O(n) space)

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    Set<ListNode> visited = new HashSet<>();

    // Add all nodes from list A to set
    while (headA != null) {
        visited.add(headA);
        headA = headA.next;
    }

    // Check if any node from list B is in set
    while (headB != null) {
        if (visited.contains(headB)) {
            return headB;
        }
        headB = headB.next;
    }

    return null;
}
```

**Pros:** Easy to understand
**Cons:** O(n) space complexity

### Approach 2: Calculate Length Difference (O(1) space)

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    // Calculate lengths
    int lenA = getLength(headA);
    int lenB = getLength(headB);

    // Align the longer list
    while (lenA > lenB) {
        headA = headA.next;
        lenA--;
    }
    while (lenB > lenA) {
        headB = headB.next;
        lenB--;
    }

    // Now move both pointers together
    while (headA != headB) {
        headA = headA.next;
        headB = headB.next;
    }

    return headA;
}

private int getLength(ListNode head) {
    int length = 0;
    while (head != null) {
        length++;
        head = head.next;
    }
    return length;
}
```

**Pros:** Also O(1) space
**Cons:** Requires multiple passes and more code

## Why the Two-Pointer Solution is Best

✅ **Single pass** (well, at most 2 passes combined)
✅ **O(1) space** - only two pointers
✅ **Elegant** - no need to calculate lengths
✅ **Clean code** - very few lines
✅ **Handles all edge cases** naturally

## Edge Cases Handled

1. **Different lengths:** ✅ Automatically handled by switching
2. **No intersection:** ✅ Both become null together
3. **One or both lists empty:** ✅ Checked at start
4. **Intersection at first node:** ✅ Works correctly
5. **Same length lists:** ✅ Still works (just no switching needed)

## Common Mistakes

### ❌ Mistake 1: Not handling null transition

```java
while (a != b) {
    a = a.next;  // Will throw NullPointerException when a is null
    b = b.next;
}
```

### ❌ Mistake 2: Using == for value comparison

```java
while (a.val != b.val)  // Wrong! Need to check node reference, not value
```

### ❌ Mistake 3: Forgetting edge cases

```java
// Not checking for null inputs
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode a = headA;
    ListNode b = headB;
    // If headA or headB is null, this will fail
```

## Complexity Analysis

- **Time Complexity:** O(m + n)
  - In the worst case (no intersection), both pointers traverse both lists completely
  - Best case (intersection at first node): O(1)
  - Average case: O(m + n)

- **Space Complexity:** O(1)
  - Only using two pointer variables
  - No additional data structures

## Key Takeaways

1. Sometimes the **path matters more than the destination**
2. Making both pointers traverse the **same total distance** eliminates length differences
3. The ternary operator `(a == null) ? headB : a.next` is perfect for switching logic
4. When `a == b`, it could be either the intersection node OR null (both valid results)

## Related Problems

- Linked List Cycle (LeetCode 141)
- Linked List Cycle II (LeetCode 142)
- Merge Two Sorted Lists (LeetCode 21)
- Palindrome Linked List (LeetCode 234)