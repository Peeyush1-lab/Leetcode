# Notes - Next Greater Node In Linked List

## 🎉 Congratulations on Day 50! 🎉

You're halfway through your 100-day challenge! This is a great milestone! 🚀

## Problem Overview

For each node in a linked list, find the **next node** (to the right) that has a **strictly larger value**. If no such node exists, return 0.

**Key Point:** "Next greater" means the **first** node with a larger value, not the largest overall.

## Your Solution: Brute Force Approach

### Strategy

For each node, linearly search forward until finding a larger value.

### Step-by-Step Walkthrough

#### Example: head = [2,7,4,3,5]

```
Node 1 (val=2):
  temp = 2, curr starts at 7
  Is 2 < 7? YES → max = 7, break
  Result: 7

Node 2 (val=7):
  temp = 7, curr starts at 4
  Is 7 < 4? NO
  curr = 3, Is 7 < 3? NO
  curr = 5, Is 7 < 5? NO
  curr = null → max stays 0
  Result: 0

Node 3 (val=4):
  temp = 4, curr starts at 3
  Is 4 < 3? NO
  curr = 5, Is 4 < 5? YES → max = 5, break
  Result: 5

Node 4 (val=3):
  temp = 3, curr starts at 5
  Is 3 < 5? YES → max = 5, break
  Result: 5

Node 5 (val=5):
  temp = 5, curr = null
  No next nodes → max = 0
  Result: 0

Final: [7, 0, 5, 5, 0] ✅
```

## Code Breakdown

```java
public int[] nextLargerNodes(ListNode head) {
    ArrayList<Integer> arr = new ArrayList<Integer>();
    ListNode temp = head;
    ListNode curr;

    // Outer loop: process each node
    while(temp != null) {
        curr = temp.next;  // Start searching from next node
        int max = 0;       // Default: no greater found

        // Inner loop: search for first greater value
        while(curr != null) {
            if(temp.val < curr.val) {
                max = curr.val;  // Found it!
                break;           // Stop searching
            }
            curr = curr.next;
        }

        arr.add(max);       // Add result for this node
        temp = temp.next;   // Move to next node
    }

    // Convert ArrayList to array
    int n = arr.size();
    int nums[] = new int[n];
    for(int i = 0; i < n; i++) {
        nums[i] = arr.get(i);
    }
    return nums;
}
```

### Key Design Choices

1. **ArrayList for dynamic sizing** - don't know list length initially
2. **Nested loop** - outer for each node, inner to search forward
3. **`max = 0` default** - if no greater found, 0 is returned
4. **`break` on first match** - we want the FIRST greater, not all of them
5. **Final conversion** - ArrayList → int[] for return type

## Why Your Solution Works

✅ **Correct logic** - finds first greater value for each node
✅ **Handles edge cases** - last node automatically gets 0
✅ **Clear and readable** - straightforward nested loop approach
✅ **No complex data structures** - just ArrayList and pointers

## Complexity Analysis

### Your Brute Force Solution

**Time Complexity:** O(n²)
- Outer loop: n nodes
- Inner loop: worst case n-1 comparisons per node
- Total: O(n × n) = O(n²)

**Worst case:** Sorted in descending order [5,4,3,2,1]
- Each node searches all remaining nodes
- 4 + 3 + 2 + 1 + 0 = 10 comparisons for 5 nodes

**Space Complexity:** O(n)
- ArrayList: O(n) for results
- Array: O(n) for final return
- Pointers: O(1)
- Total: O(n)

## Optimal Solution: Monotonic Stack

For comparison, here's the O(n) approach:

```java
public int[] nextLargerNodes(ListNode head) {
    // Step 1: Convert list to ArrayList
    ArrayList<Integer> list = new ArrayList<>();
    while(head != null) {
        list.add(head.val);
        head = head.next;
    }

    // Step 2: Initialize result and stack
    int n = list.size();
    int[] result = new int[n];
    Stack<Integer> stack = new Stack<>();  // Stores indices

    // Step 3: Traverse from right to left
    for(int i = n - 1; i >= 0; i--) {
        // Pop elements smaller than or equal to current
        while(!stack.isEmpty() && list.get(stack.peek()) <= list.get(i)) {
            stack.pop();
        }

        // If stack not empty, top is the next greater
        result[i] = stack.isEmpty() ? 0 : list.get(stack.peek());

        // Push current index
        stack.push(i);
    }

    return result;
}
```

### How Monotonic Stack Works

Process from **right to left**, maintaining stack of indices in **decreasing value order**:

```
Array: [2, 7, 4, 3, 5]
Index:  0  1  2  3  4

Start from right (i=4, val=5):
  Stack is empty → result[4] = 0
  Push 4
  Stack: [4]

i=3, val=3:
  Stack top (4) has value 5, which is > 3
  result[3] = 5
  Push 3
  Stack: [4, 3]

i=2, val=4:
  Stack top (3) has value 3, which is < 4 → pop
  Stack top (4) has value 5, which is > 4
  result[2] = 5
  Push 2
  Stack: [4, 2]

i=1, val=7:
  Stack top (2) has value 4 < 7 → pop
  Stack top (4) has value 5 < 7 → pop
  Stack is empty → result[1] = 0
  Push 1
  Stack: [1]

i=0, val=2:
  Stack top (1) has value 7 > 2
  result[0] = 7
  Push 0
  Stack: [1, 0]

Result: [7, 0, 5, 5, 0] ✅
```

**Time:** O(n) - each element pushed/popped once
**Space:** O(n) - stack can hold up to n elements

## When to Use Each Approach

### Brute Force (Your Solution)
✅ **Simple to understand and implement**
✅ **Good for small inputs** (n ≤ 100)
✅ **Interview: good starting point** - optimize after
❌ **Slow for large inputs** - O(n²)

### Monotonic Stack
✅ **Optimal for large inputs** - O(n)
✅ **Shows advanced knowledge** - stack-based optimization
❌ **More complex** - harder to understand initially
❌ **Requires array conversion** - can't work directly on list

## Edge Cases

### 1. Single Node
```java
Input: head = [5]
Output: [0]
```
✅ Your solution: temp.next is null, max stays 0

### 2. Ascending Order
```java
Input: head = [1,2,3,4,5]
Output: [2,3,4,5,0]
```
✅ Your solution: Each finds next immediately

### 3. Descending Order (Worst Case)
```java
Input: head = [5,4,3,2,1]
Output: [0,0,0,0,0]
```
✅ Your solution: Each searches to end, finds nothing

### 4. All Same Values
```java
Input: head = [5,5,5,5,5]
Output: [0,0,0,0,0]
```
✅ Your solution: Condition is `<`, not `<=`, so none match

### 5. Two Nodes
```java
Input: head = [1,3]
Output: [3,0]
```
✅ Works perfectly

## Common Mistakes

### ❌ Mistake 1: Using `<=` instead of `<`

```java
if(temp.val <= curr.val)  // WRONG! Must be strictly greater
```

The problem asks for **strictly larger**, so equal values don't count.

### ❌ Mistake 2: Not breaking after finding first greater

```java
while(curr != null) {
    if(temp.val < curr.val) {
        max = curr.val;
        // Missing break! Will keep searching
    }
    curr = curr.next;
}
```

This would find the **last** greater value, not the **first (next)** one.

### ❌ Mistake 3: Wrong default value

```java
int max = -1;  // WRONG! Should be 0
```

Problem specifies: if no next greater exists, return **0**.

### ❌ Mistake 4: Not handling last node

```java
// If you forget that last node has no next
// Inner loop handles it: curr starts as null, loop doesn't run, max stays 0 ✅
```

Your code handles this correctly!

## Comparison with Similar Problems

### Next Greater Element I (LeetCode 496)
- **Input:** Two arrays
- **Output:** Next greater in one array for elements of another
- **Similar:** Uses monotonic stack

### Next Greater Element II (LeetCode 503)
- **Input:** Circular array
- **Output:** Next greater with wraparound
- **Trick:** Process array twice or use modulo

### Daily Temperatures (LeetCode 739)
- **Input:** Array of temperatures
- **Output:** Days until warmer temperature
- **Similar:** Next greater concept, returns index difference

## Interview Tips

### If Asked This Problem

1. **Start with brute force** (your approach)
   - "For each node, I'll search forward for the first larger value"
   - Code it up cleanly

2. **Analyze complexity**
   - "This is O(n²) time, O(n) space"

3. **Mention optimization**
   - "For large inputs, we could use a monotonic stack for O(n)"
   - If asked, explain the stack approach

4. **Show you understand trade-offs**
   - "Brute force is simpler and fine for small n"
   - "Stack is faster but more complex"

This shows problem-solving progression from simple to optimal!

## Key Takeaways

1. **Brute force is valid** - especially for medium-sized inputs
2. **"Next greater" means FIRST greater** - use break to stop early
3. **Default value is 0** - when no greater element exists
4. **Monotonic stack is the optimization** - good to know for similar problems
5. **Your solution is clear and correct** - a great starting point!

## Related Problems

- **496. Next Greater Element I (Easy)** - Array version
- **503. Next Greater Element II (Medium)** - Circular array
- **739. Daily Temperatures (Medium)** - Temperature version
- **901. Online Stock Span (Medium)** - Stock price spans

Great work on reaching Day 50! Your solution is clean and correct!