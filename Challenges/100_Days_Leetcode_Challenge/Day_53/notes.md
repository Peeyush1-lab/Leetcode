# Notes - Swapping Nodes in a Linked List

## Problem Overview

Swap the values of two nodes:
1. The **kth node from the beginning**
2. The **kth node from the end**

**Important:** The list is **1-indexed**, meaning the first node is at position 1, not 0.

## The Challenge

Finding the kth node from the **beginning** is easy (just traverse k steps).
Finding the kth node from the **end** is tricky without knowing the list length.

**Your solution cleverly avoids counting the list length!**

## The Runner Technique Explained

### Core Concept

If you have two pointers **k positions apart**, when the front pointer reaches the end, the back pointer will be **k positions from the end**.

```
List:  1 → 2 → 3 → 4 → 5
       ↑           ↑
      end        temp (k=2 apart)

When temp reaches end:
List:  1 → 2 → 3 → 4 → 5 → null
                   ↑       ↑
                  end    temp

end is now 2 positions from the end!
```

### Why This Works

**Given:** List of length `n`, want kth node from end

**Setup:**
- `temp` starts at position `k` (kth from beginning)
- `end` starts at position `1` (head)
- Distance between them: `k - 1` nodes

**When temp reaches end:**
- `temp` moved `n - k` steps (from position k to position n)
- `end` also moved `n - k` steps (from position 1)
- `end` is now at position `1 + (n - k) = n - k + 1`

**Position from end:**
- Total positions: `n`
- `end` at position `n - k + 1` from beginning
- From end: `n - (n - k + 1) + 1 = k` ✅

## Visual Walkthrough

### Example: head = [1,2,3,4,5], k = 2

```
Step 1: Find kth (2nd) node from beginning

i=1: start moves from 1 to 2
Loop exits (i < k is false)

Result: start at node 2
List:  1 → 2 → 3 → 4 → 5
           ↑
         start

Step 2: Find kth (2nd) node from end using runner

Initial setup:
  temp = start (at node 2)
  end = head (at node 1)

List:  1 → 2 → 3 → 4 → 5
       ↑   ↑
      end temp

Iteration 1: temp.next != null (is 3)
  temp = temp.next (node 3)
  end = end.next (node 2)

List:  1 → 2 → 3 → 4 → 5
           ↑   ↑
          end temp

Iteration 2: temp.next != null (is 4)
  temp = temp.next (node 4)
  end = end.next (node 3)

List:  1 → 2 → 3 → 4 → 5
               ↑   ↑
              end temp

Iteration 3: temp.next != null (is 5)
  temp = temp.next (node 5)
  end = end.next (node 4)

List:  1 → 2 → 3 → 4 → 5
                   ↑   ↑
                  end temp

Iteration 4: temp.next == null
  Loop exits

Result: end at node 4 (2nd from end) ✅

Step 3: Swap values

start.val = 2, end.val = 4
After swap: start.val = 4, end.val = 2

List:  1 → 4 → 3 → 2 → 5
           ↑       ↑
         start   end

Return: head → [1,4,3,2,5] ✅
```

## Code Breakdown

```java
public ListNode swapNodes(ListNode head, int k) {
    ListNode start = head;
    ListNode end = head;

    // Phase 1: Find kth node from beginning
    for(int i = 1; i < k; i++) {
        start = start.next;
    }
    // Now: start is at kth node from beginning

    // Phase 2: Find kth node from end
    ListNode temp = start;  // Start temp at kth position

    while(temp.next != null) {
        temp = temp.next;   // Move temp forward
        end = end.next;     // Move end forward (in sync)
    }
    // Now: end is at kth node from end

    // Phase 3: Swap values
    int tem = start.val;
    start.val = end.val;
    end.val = tem;

    return head;
}
```

## Why This Algorithm is Elegant

### ✅ Single Pass
- No need to count list length first
- One traversal to find both nodes

### ✅ Constant Space
- Only uses 4 pointer variables
- No extra data structures

### ✅ Clean Logic
- Each phase has a clear purpose
- No complex index calculations

### ✅ Handles Symmetry
- If k = n/2, swapping same node with itself (values stay same)
- Works correctly!

## Edge Cases

### 1. k = 1 (Swap first and last)
```java
Input: [1,2,3,4,5], k = 1
Output: [5,2,3,4,1]
```
✅ Works: start at 1, end at 5, swap values

### 2. k = n (Swap last and first)
```java
Input: [1,2,3,4,5], k = 5
Output: [5,2,3,4,1]
```
✅ Works: Same result as k=1 (symmetric)

### 3. Two nodes
```java
Input: [1,2], k = 1
Output: [2,1]
```
✅ Works: Swaps the two nodes

### 4. Single node
```java
Input: [1], k = 1
Output: [1]
```
✅ Works: start and end point to same node, swap has no effect

### 5. Middle node swapping itself
```java
Input: [1,2,3,4,5], k = 3
Output: [1,2,3,4,5]
```
✅ Works: 3rd from beginning = 3rd from end, no visible change

### 6. Adjacent nodes
```java
Input: [1,2,3,4], k = 2
Output: [1,3,2,4]
```
✅ Works: Swaps 2nd and 3rd nodes (2nd from start, 2nd from end)

## Common Mistakes

### ❌ Mistake 1: Swapping node references instead of values

```java
ListNode tempNode = start;
start = end;
end = tempNode;  // WRONG! Doesn't change the list structure
```

This only changes local variables, not the list itself!

**Fix:** Swap the **values**, not the node references.

### ❌ Mistake 2: Off-by-one in finding kth node

```java
for(int i = 0; i < k; i++)  // WRONG! Goes one too far
```

Starting at i=0 and going to k would move k+1 times (0,1,2,...,k).

**Fix:** Start at i=1, condition i < k

### ❌ Mistake 3: Not initializing end at head

```java
ListNode end;  // WRONG! end is null
while(temp.next != null) {
    end = end.next;  // NullPointerException!
}
```

**Fix:** Initialize `end = head`

### ❌ Mistake 4: Starting temp at head instead of start

```java
ListNode temp = head;  // WRONG! Loses the k-gap
```

This would make end end up at position n+1-k from beginning, which is wrong.

**Fix:** `temp = start` (maintains k-position gap)

### ❌ Mistake 5: Trying to swap actual nodes

```java
// Attempting to swap node positions in the list
// This is MUCH harder and unnecessary!
```

**Fix:** Just swap values - it's simpler and achieves the same result.

## Complexity Analysis

**Time Complexity:** O(n)
- Phase 1: O(k) to find kth node from beginning
- Phase 2: O(n-k) to find kth node from end
- Phase 3: O(1) to swap values
- Total: O(k) + O(n-k) + O(1) = O(n)

**Space Complexity:** O(1)
- Variables: `start`, `end`, `temp`, `tem`
- No recursion, no arrays
- Constant space regardless of input size

## Alternative Approach: Count Length First

```java
public ListNode swapNodes(ListNode head, int k) {
    // Step 1: Count length
    int length = 0;
    ListNode curr = head;
    while(curr != null) {
        length++;
        curr = curr.next;
    }

    // Step 2: Find kth from beginning
    ListNode start = head;
    for(int i = 1; i < k; i++) {
        start = start.next;
    }

    // Step 3: Find kth from end (which is (length-k+1)th from beginning)
    ListNode end = head;
    for(int i = 1; i < length - k + 1; i++) {
        end = end.next;
    }

    // Step 4: Swap
    int temp = start.val;
    start.val = end.val;
    end.val = temp;

    return head;
}
```

**Pros:** More straightforward logic
**Cons:** Two passes through the list

Your runner technique is **more efficient** (fewer iterations on average)!

## The Runner Pattern - General Use

The "runner" or "fast-slow pointer" pattern is useful for:

1. **Finding nth from end** (this problem) ✅
2. **Detecting cycles** (Floyd's algorithm)
3. **Finding middle** (fast moves 2x speed)
4. **Removing nth from end** (LeetCode 19)

**General idea:** Create a gap between pointers, maintain it while moving, use final positions.

## Why Swap Values Instead of Nodes?

### Swapping Values (Your Approach)
```java
int temp = start.val;
start.val = end.val;
end.val = temp;
```
✅ **3 lines of code**
✅ **Simple and clean**
✅ **No pointer manipulation**

### Swapping Actual Nodes
```java
// Need to find previous nodes
// Update 4+ pointers
// Handle edge cases (adjacent, head/tail)
// 20+ lines of complex code
```
❌ **Much more complex**
❌ **Easy to make mistakes**
❌ **No real benefit**

**Unless the problem explicitly requires swapping node positions** (not values), always swap values!

## Interview Tips

1. **Explain the runner technique:**
   - "I'll use two pointers with a k-gap"
   - "When front reaches end, back is k from end"

2. **Draw a diagram:**
   ```
   [1, 2, 3, 4, 5], k=2
    ↑     ↑
   end  start → move together → end at 4
   ```

3. **Walk through phases:**
   - "First, find kth from beginning"
   - "Then use runner to find kth from end"
   - "Finally, swap values"

4. **Mention complexity:** "O(n) time, O(1) space, single pass"

5. **Compare alternatives:** "Could count length first, but this is more elegant"

## Related Problems with Hyperlinks

- [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) (Medium) - Same runner technique for finding nth from end
- [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/) (Easy) - Runner technique for finding middle
- [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) (Medium) - Different type of node swapping
- [234. Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) (Easy) - Uses slow-fast pointers
- [143. Reorder List](https://leetcode.com/problems/reorder-list/) (Medium) - Complex list manipulation
- [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) (Easy) - Floyd's cycle detection (fast-slow pattern)

## Key Takeaways

1. **Runner technique** - Create a k-gap, move together, back pointer ends at kth from end
2. **No length calculation needed** - Elegant single-pass solution
3. **Swap values, not nodes** - Simpler and achieves same result
4. **1-indexed** - First node is position 1, not 0
5. **Handles all edge cases** - Including when start and end are the same node

Excellent use of the runner technique for finding nth from end! 🎯