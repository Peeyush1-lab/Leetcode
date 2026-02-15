# Notes - Linked List Cycle II

## Problem Overview

Not only detect if a cycle exists in a linked list, but also find the exact node where the cycle begins. This is a harder version of LeetCode 141 (Linked List Cycle).

## Floyd's Cycle Detection Algorithm

This is one of the most elegant algorithms in computer science. It solves the problem in O(n) time and O(1) space!

## Two Phases Explained

### Phase 1: Detect the Cycle

Use the classic "tortoise and hare" approach:

```java
ListNode slow = head;
ListNode fast = head;

while(fast != null && fast.next != null) {
    slow = slow.next;        // Move 1 step
    fast = fast.next.next;   // Move 2 steps

    if(slow == fast) {
        // Cycle detected!
        break;
    }
}
```

**Why it works:** If there's a cycle, fast will eventually catch up to slow inside the cycle.

### Phase 2: Find the Cycle Start

Here's where the magic happens:

```java
// Reset slow to head
slow = head;

// Move both pointers one step at a time
while(slow != fast) {
    slow = slow.next;
    fast = fast.next;
}

// They meet at the cycle start!
return slow;
```

## The Mathematical Proof

This is the beautiful part that makes this algorithm work!

### Setup

```
Head → → → [Cycle Start] → → → [Meeting Point] → → →
           ↑___________________________________|

Let:
- a = distance from head to cycle start
- b = distance from cycle start to meeting point (inside cycle)
- c = remaining cycle distance (from meeting point back to cycle start)
```

### When Slow and Fast Meet

**Slow pointer traveled:** `a + b`

**Fast pointer traveled:** `a + b + (b + c)`
- It traveled `a + b` to reach the meeting point
- Then it went around the cycle once more: `b + c`
- Total: `a + 2b + c`

### The Key Relationship

Since fast moves twice as fast as slow:
```
2 × (slow distance) = fast distance
2(a + b) = a + 2b + c
2a + 2b = a + 2b + c
a = c
```

**Crucial insight:** The distance from head to cycle start (`a`) equals the distance from meeting point to cycle start (`c`)!

### Why Phase 2 Works

After detecting the cycle:
- Reset slow to head (distance `a` from cycle start)
- Fast is at meeting point (distance `c` from cycle start)
- Since `a = c`, they will meet exactly at the cycle start!

## Visual Walkthrough

### Example: head = [3,2,0,-4], cycle at index 1

```
Initial list with cycle:
3 → 2 → 0 → -4
    ↑__________|

Step-by-step Phase 1:

Iteration 1: slow = 3, fast = 3
Iteration 2: slow = 2, fast = 0
Iteration 3: slow = 0, fast = 2
Iteration 4: slow = -4, fast = -4  ← MEET!

Phase 1 complete: Cycle detected at node -4

Step-by-step Phase 2:

Reset slow to head
slow = 3, fast = -4

Iteration 1: slow = 2, fast = 2  ← MEET!

Result: Cycle starts at node 2 (index 1)
```

## Code Breakdown with Edge Cases

```java
public ListNode detectCycle(ListNode head) {
    // Edge case: empty list
    if(head == null) {
        return null;
    }

    ListNode slow = head;
    ListNode fast = head;

    // Phase 1: Detect cycle
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if(slow == fast) {
            break;  // Cycle found!
        }
    }

    // Check if we exited because no cycle exists
    if(fast == null || fast.next == null) {
        return null;
    }

    // Phase 2: Find cycle start
    slow = head;

    while(slow != fast) {
        slow = slow.next;
        fast = fast.next;  // Now move 1 step, not 2!
    }

    return slow;  // or fast, they're the same
}
```

## Edge Cases Handled

### 1. Empty List
```java
Input: head = null
Output: null
```
Handled by: `if(head == null) return null;`

### 2. Single Node, No Cycle
```java
Input: head = [1], pos = -1
Output: null
```
Handled by: `fast.next == null` in Phase 1

### 3. Single Node with Self-Loop
```java
Input: head = [1], pos = 0
Output: node 1
```
Works correctly: slow and fast meet at first node

### 4. Cycle at Head
```java
Input: head = [1,2], pos = 0
Output: node 1
```
Works correctly: Phase 2 returns immediately

### 5. No Cycle
```java
Input: head = [1,2,3,4,5], pos = -1
Output: null
```
Fast reaches null, return null

## Common Mistakes

### ❌ Mistake 1: Not checking fast.next

```java
while(fast != null) {  // Missing fast.next check
    fast = fast.next.next;  // NullPointerException!
}
```

**Fix:** Always check `fast != null && fast.next != null`

### ❌ Mistake 2: Moving fast 2 steps in Phase 2

```java
// In Phase 2
while(slow != fast) {
    slow = slow.next;
    fast = fast.next.next;  // WRONG! Should be fast.next
}
```

**Fix:** In Phase 2, both move 1 step at a time

### ❌ Mistake 3: Not checking for no cycle after Phase 1

```java
// Forgetting to check if cycle exists
slow = head;  // Might be wrong if no cycle!
while(slow != fast) {
    slow = slow.next;
    fast = fast.next;  // Could be null!
}
```

**Fix:** Check `if(fast == null || fast.next == null) return null;`

### ❌ Mistake 4: Resetting fast instead of slow

```java
// After detecting cycle
fast = head;  // WRONG! Should reset slow
while(slow != fast) {
    slow = slow.next;
    fast = fast.next;
}
```

**Fix:** Reset slow to head, keep fast at meeting point

## Alternative Approach: HashSet

```java
public ListNode detectCycle(ListNode head) {
    Set<ListNode> visited = new HashSet<>();

    ListNode current = head;
    while(current != null) {
        if(visited.contains(current)) {
            return current;  // First repeated node is cycle start
        }
        visited.add(current);
        current = current.next;
    }

    return null;  // No cycle
}
```

**Pros:**
- Easier to understand
- More intuitive

**Cons:**
- O(n) space complexity
- Doesn't meet the follow-up requirement of O(1) space

## Complexity Analysis

### Floyd's Algorithm (Two-Pointer)

**Time Complexity:** O(n)
- Phase 1: At most n iterations to detect cycle
- Phase 2: At most n iterations to find start
- Total: O(2n) = O(n)

**Space Complexity:** O(1)
- Only using two pointer variables
- No additional data structures

### HashSet Approach

**Time Complexity:** O(n)
- Single pass through the list

**Space Complexity:** O(n)
- Storing all visited nodes in the set

## Why Floyd's Algorithm is Brilliant

1. ✅ **Constant space** - no extra memory needed
2. ✅ **Linear time** - still efficient
3. ✅ **Elegant mathematics** - the proof is beautiful
4. ✅ **Two birds, one stone** - detects cycle AND finds start
5. ✅ **No modifications** - doesn't change the list structure

## Related Problems

- **Linked List Cycle (LeetCode 141)** - Detect if cycle exists (easier version)
- **Happy Number (LeetCode 202)** - Uses same cycle detection concept
- **Find the Duplicate Number (LeetCode 287)** - Uses Floyd's algorithm on array
- **Intersection of Two Linked Lists (LeetCode 160)** - Similar two-pointer technique

## Key Takeaways

1. **Floyd's Algorithm** is a powerful technique for cycle detection
2. The mathematical relationship `a = c` is the key insight
3. **Two phases:**
   - Phase 1: Detect cycle (fast moves 2 steps)
   - Phase 2: Find start (both move 1 step)
4. Always check `fast != null && fast.next != null` before moving fast
5. This problem is a **classic interview question** - understand it deeply!

## Interview Tips

When explaining this in an interview:

1. Start with the naive HashSet approach
2. Mention it uses O(n) space
3. Introduce Floyd's algorithm for O(1) space
4. Draw a diagram showing the distances a, b, c
5. Explain why a = c mathematically
6. Code it up with proper edge case handling
7. Walk through an example

This shows you understand multiple approaches and can optimize!