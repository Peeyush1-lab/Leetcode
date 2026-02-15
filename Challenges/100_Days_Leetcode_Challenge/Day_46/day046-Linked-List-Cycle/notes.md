# Notes - Linked List Cycle

## Problem Overview

Detect if a linked list contains a cycle. This is the simpler version of **LeetCode 142** (Linked List Cycle II), where we only need to detect the cycle, not find where it starts.

## Floyd's Cycle Detection Algorithm

Also known as the **"Tortoise and Hare"** algorithm - one of the most elegant algorithms in computer science!

### The Core Idea

Imagine two runners on a circular track:
- **Tortoise (slow):** Runs at 1 step per iteration
- **Hare (fast):** Runs at 2 steps per iteration

**If the track is circular (has a cycle):** The fast runner will eventually lap the slow runner and they'll meet.

**If the track is straight (no cycle):** The fast runner reaches the end first.

## Visual Walkthrough

### Example 1: List with Cycle

```
List: 3 → 2 → 0 → -4
          ↑________|

Initial: slow = 3, fast = 3

Iteration 1:
  slow = 2 (moved 1 step)
  fast = 0 (moved 2 steps)
  slow != fast, continue

Iteration 2:
  slow = 0 (moved 1 step)
  fast = 2 (moved 2 steps: -4 → 2)
  slow != fast, continue

Iteration 3:
  slow = -4 (moved 1 step)
  fast = -4 (moved 2 steps: 0 → -4)
  slow == fast → CYCLE DETECTED!

Return: true
```

### Example 2: List without Cycle

```
List: 1 → 2 → 3 → null

Initial: slow = 1, fast = 1

Iteration 1:
  slow = 2
  fast = 3
  slow != fast, continue

Iteration 2:
  slow = 3
  fast would be null (can't move 2 steps)
  Loop exits because fast.next == null

Return: false
```

## Code Breakdown

```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    // Continue while fast can move 2 steps
    while(fast != null && fast.next != null) {
        slow = slow.next;        // Move 1 step
        fast = fast.next.next;   // Move 2 steps

        if(slow == fast) {
            return true;  // Cycle detected!
        }
    }

    return false;  // Reached end, no cycle
}
```

### Key Points

1. **Initial Setup:** Both pointers start at head
2. **Loop Condition:** `fast != null && fast.next != null`
   - Need to check `fast.next` because we do `fast.next.next`
   - If either is null, we've reached the end (no cycle)
3. **Movement:** Move pointers BEFORE checking equality
4. **Cycle Detection:** If `slow == fast`, cycle exists

## Why This Works - Proof

### Case 1: No Cycle

If there's no cycle, the list is finite:
```
1 → 2 → 3 → ... → n → null
```

- Fast pointer moves 2x as fast as slow
- Fast will reach `null` first
- Loop exits, return `false`

### Case 2: Cycle Exists

```
Before cycle: a nodes
Cycle length: c nodes
```

Once both pointers enter the cycle:
- Slow is at position `i` in cycle
- Fast is at position `j` in cycle
- Distance between them: `d = (j - i) % c`

Each iteration:
- Slow moves 1 step
- Fast moves 2 steps
- Distance decreases by 1

Eventually, distance becomes 0 → they meet!

**Why they must meet:**
- Fast gains 1 position on slow per iteration
- In a cycle of length `c`, after at most `c` iterations, fast catches slow

## Edge Cases

### 1. Empty List
```java
Input: head = null
Output: false
```
Loop doesn't execute, returns `false`

### 2. Single Node, No Cycle
```java
Input: head = [1], pos = -1
Output: false
```
`fast.next == null`, loop exits

### 3. Single Node with Self-Loop
```java
Input: head = [1], pos = 0
Output: true
```
After iteration 1: slow = fast = head, returns `true`

### 4. Two Nodes with Cycle
```java
Input: head = [1,2], pos = 0
Output: true
```
Works correctly

### 5. Large List, No Cycle
```java
Input: head = [1,2,3,...,10000], pos = -1
Output: false
```
Fast reaches end in ~5000 iterations

## Common Mistakes

### ❌ Mistake 1: Not checking fast.next

```java
while(fast != null) {  // Missing fast.next check
    fast = fast.next.next;  // NullPointerException if fast.next is null!
}
```

**Fix:** Always check `fast != null && fast.next != null`

### ❌ Mistake 2: Checking equality before moving

```java
while(fast != null && fast.next != null) {
    if(slow == fast) {  // Checks at head, always true!
        return true;
    }
    slow = slow.next;
    fast = fast.next.next;
}
```

**Fix:** Move first, then check

### ❌ Mistake 3: Using value comparison instead of reference

```java
if(slow.val == fast.val) {  // Wrong! Different nodes can have same value
    return true;
}
```

**Fix:** Use `slow == fast` to compare references

### ❌ Mistake 4: Initializing pointers differently

```java
ListNode slow = head;
ListNode fast = head.next;  // Starting at different positions can cause issues
```

**Fix:** Start both at `head`

## Alternative Approach: HashSet

```java
public boolean hasCycle(ListNode head) {
    Set<ListNode> visited = new HashSet<>();

    ListNode current = head;
    while(current != null) {
        if(visited.contains(current)) {
            return true;  // Visited this node before = cycle
        }
        visited.add(current);
        current = current.next;
    }

    return false;  // Reached end, no cycle
}
```

**Pros:**
- Very intuitive and easy to understand
- Straightforward logic

**Cons:**
- O(n) space complexity (stores all nodes)
- Doesn't meet the follow-up requirement of O(1) space
- Slower due to HashSet operations

## Complexity Analysis

### Floyd's Algorithm (Two Pointers)

**Time Complexity:** O(n)
- No cycle: Fast reaches end in n/2 steps
- With cycle: At most n steps to enter + c steps to meet (c ≤ n)
- Total: O(n)

**Space Complexity:** O(1)
- Only using two pointer variables
- No extra data structures

### HashSet Approach

**Time Complexity:** O(n)
- Visit each node once

**Space Complexity:** O(n)
- Store up to n nodes in the set

## Why Floyd's Algorithm is Better

1. ✅ **Constant space** - O(1) vs O(n)
2. ✅ **No extra memory allocation** - just two pointers
3. ✅ **Cache-friendly** - sequential access pattern
4. ✅ **Elegant** - beautiful mathematical proof
5. ✅ **Interview favorite** - shows deeper understanding

## Related Problems

- **Linked List Cycle II (LeetCode 142)** - Find WHERE the cycle starts (harder version)
- **Happy Number (LeetCode 202)** - Uses same cycle detection concept
- **Find the Duplicate Number (LeetCode 287)** - Floyd's algorithm on arrays
- **Intersection of Two Linked Lists (LeetCode 160)** - Similar two-pointer technique

## Comparison with Cycle II (LeetCode 142)

| Aspect | Cycle I (141) | Cycle II (142) |
|--------|---------------|----------------|
| **Goal** | Detect cycle | Find cycle start |
| **Return** | boolean | ListNode |
| **Difficulty** | Easy | Medium |
| **Phases** | 1 phase | 2 phases |
| **After meeting** | Return true | Reset slow, find start |

**This problem (141) is the foundation** - master it before tackling 142!

## Key Takeaways

1. **Floyd's Algorithm** is the optimal solution (O(1) space)
2. The "tortoise and hare" metaphor makes it intuitive
3. Always check **both** `fast != null` and `fast.next != null`
4. Move pointers **before** checking if they're equal
5. Compare **references**, not values
6. This is a **classic interview question** - be prepared to:
   - Explain why it works
   - Analyze time and space complexity
   - Compare with the HashSet approach
   - Handle edge cases

## Interview Tips

When asked this question:

1. **Start with the intuition:** "Like two runners on a track"
2. **Mention the naive approach:** HashSet with O(n) space
3. **Introduce Floyd's algorithm:** O(1) space optimization
4. **Code it up carefully:** Don't forget the `fast.next` check!
5. **Walk through an example:** Show both cycle and no-cycle cases
6. **Discuss complexity:** O(n) time, O(1) space
7. **Mention the follow-up:** "This can be extended to find the cycle start (LeetCode 142)"

This demonstrates you understand the problem deeply and can optimize!