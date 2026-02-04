# Day 35 | Remove Duplicates from Sorted List

**LeetCode #83** | Easy | Linked List

## Problem Statement

Given the `head` of a **sorted** linked list, delete all duplicates such that each element appears only once. Return the linked list **sorted** as well.

**Examples:**

```
Example 1:
Input: head = [1,1,2]
Output: [1,2]

Example 2:
Input: head = [1,1,2,3,3]
Output: [1,2,3]

Example 3:
Input: head = []
Output: []

Example 4:
Input: head = [1,2,3]
Output: [1,2,3]
Explanation: No duplicates
```

**Constraints:**
- Number of nodes: `[0, 300]`
- `-100 <= Node.val <= 100`
- List is guaranteed to be **sorted** in ascending order

## Solution Overview

**Algorithm:** Single Pointer with Conditional Advancement

**Key Insight:** Since the list is **sorted**, all duplicates are **adjacent**. Only need to check `temp.val == temp.next.val`.

**Your Solution:**
```java
public ListNode deleteDuplicates(ListNode head) {
    ListNode temp = head;
    while(temp != null && temp.next != null) {
        if(temp.val == temp.next.val) {
            temp.next = temp.next.next;  // Skip duplicate
        } else {
            temp = temp.next;            // Move to next
        }
    }
    return head;
}
```

**Why this works:** The sorted property means duplicates are always consecutive. Skip duplicates in-place without needing to search the entire list.

## How It Works

### Core Strategy

**Two actions based on comparison:**
1. **If duplicate found:** Skip the next node (don't move temp)
2. **If no duplicate:** Move temp to next node

**Why not always move temp?**
```
List: [1,1,1,2]

If we always move:
temp at [1], next is [1] → skip
temp moves to [1], next is [1] → skip
temp moves to [1], next is [2] → keep
Result: [1,1,2] ❌ (still has duplicate!)

Correct approach:
temp at [1], next is [1] → skip, stay at temp
temp at [1], next is [1] → skip, stay at temp
temp at [1], next is [2] → no dup, move temp
Result: [1,2] ✓
```

### Loop Condition
```java
while(temp != null && temp.next != null)
```
- `temp != null`: List not exhausted
- `temp.next != null`: Can safely check next value
- Both needed to avoid NullPointerException

### Duplicate Detection
```java
if(temp.val == temp.next.val)
```
**Only possible because list is sorted:**
- Sorted: [1,1,2,3,3] - duplicates adjacent ✓
- Unsorted: [1,3,1,2,3] - duplicates scattered ✗

### Deletion Logic
```java
temp.next = temp.next.next;
```
**Skips the duplicate node:**
```
Before: [1] → [1] → [2]
         temp  dup

After:  [1] → [2]
         temp
```

### Pointer Advancement
```java
else {
    temp = temp.next;
}
```
**Only move when no duplicate found:**
- Ensures all consecutive duplicates are removed
- Prevents leaving duplicates behind

## Visual Walkthrough

### Example 1: [1,1,2]

```
Initial:
temp
 ↓
[1] → [1] → [2] → null

Step 1: temp.val (1) == temp.next.val (1)
temp.next = temp.next.next
temp
 ↓
[1] → [2] → null

Step 2: temp.val (1) != temp.next.val (2)
temp = temp.next
      temp
       ↓
[1] → [2] → null

Step 3: temp.next == null → Stop

Result: [1] → [2] ✓
```

### Example 2: [1,1,2,3,3]

```
Initial:
temp
 ↓
[1] → [1] → [2] → [3] → [3] → null

Step 1: 1 == 1, skip
temp
 ↓
[1] → [2] → [3] → [3] → null

Step 2: 1 != 2, move
      temp
       ↓
[1] → [2] → [3] → [3] → null

Step 3: 2 != 3, move
            temp
             ↓
[1] → [2] → [3] → [3] → null

Step 4: 3 == 3, skip
            temp
             ↓
[1] → [2] → [3] → null

Step 5: temp.next == null → Stop

Result: [1] → [2] → [3] ✓
```

### Example 3: Multiple Consecutive Duplicates [1,1,1,1,2]

```
Initial:
temp
 ↓
[1] → [1] → [1] → [1] → [2] → null

Step 1: 1 == 1, skip (stay at temp)
temp
 ↓
[1] → [1] → [1] → [2] → null

Step 2: 1 == 1, skip (stay at temp)
temp
 ↓
[1] → [1] → [2] → null

Step 3: 1 == 1, skip (stay at temp)
temp
 ↓
[1] → [2] → null

Step 4: 1 != 2, move
      temp
       ↓
[1] → [2] → null

Step 5: temp.next == null → Stop

Result: [1] → [2] ✓
```

## Why This Solution is Optimal

### Leverages Sorted Property

**Sorted list:**
- Only check adjacent nodes
- Time: O(n)
- Space: O(1)

**Unsorted list:**
- Need to check all pairs OR use hash set
- Time: O(n²) or O(n) with O(n) space
- Much more complex

### In-Place Modification

**Your approach:**
- Modifies links directly
- No new nodes created
- O(1) space

**Alternative (not needed):**
- Create new list with unique values
- O(n) space
- More work, less efficient

### Single Pass

**One traversal:**
- Visit each node once
- Make decision immediately
- Can't do better than O(n)

## Complexity Analysis

**Time Complexity: O(n)**
- Visit each node exactly once
- Constant work per node
- Best possible for this problem

**Space Complexity: O(1)**
- Only one pointer variable (temp)
- Modifies in-place
- No recursion, no extra structures

**Comparison:**

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| **Your solution** | **O(n)** | **O(1)** | **Optimal** |
| Hash set | O(n) | O(n) | Unnecessary for sorted |
| Nested loops | O(n²) | O(1) | Too slow |
| Create new list | O(n) | O(n) | Wasteful |

## Key Implementation Details

### 1. Conditional Pointer Advancement

**Critical insight:**
```java
if (duplicate found) {
    temp.next = temp.next.next;  // Skip, DON'T move temp
} else {
    temp = temp.next;            // No dup, move temp
}
```

**Why this matters:**
- Staying at temp handles multiple consecutive duplicates
- Moving prematurely leaves duplicates behind

### 2. Loop Condition Order

```java
while(temp != null && temp.next != null)
```

**Must check temp first:**
- If temp is null, temp.next throws error
- Short-circuit evaluation prevents this

### 3. Return Head Unchanged

```java
return head;
```

**Head never changes:**
- First node is never a duplicate of previous (no previous exists)
- All deletions happen after head
- No need for dummy node

## Edge Cases Analysis

### Case 1: Empty List
```java
Input: head = null
Loop: temp = null → while condition false → exit immediately
Return: null ✓
```

### Case 2: Single Node
```java
Input: head = [1]
Loop: temp = [1], temp.next = null → while condition false
Return: [1] ✓
```

### Case 3: No Duplicates
```java
Input: head = [1,2,3]
Every comparison: temp.val != temp.next.val
Action: Always move temp forward
Result: [1,2,3] (unchanged) ✓
```

### Case 4: All Same Values
```java
Input: head = [1,1,1,1]
All comparisons: temp.val == temp.next.val
Action: Keep skipping, never move temp
Result: [1] ✓
```

### Case 5: Two Nodes (Duplicate)
```java
Input: head = [1,1]
Comparison: 1 == 1
Action: Skip second node
Result: [1] ✓
```

## Common Mistakes to Avoid

### Mistake 1: Always Moving Temp
```java
// ❌ Wrong - leaves duplicates
while(temp != null && temp.next != null) {
    if(temp.val == temp.next.val) {
        temp.next = temp.next.next;
    }
    temp = temp.next;  // Always moves!
}
```

**Problem:** For [1,1,1,2], produces [1,1,2] instead of [1,2]

### Mistake 2: Wrong Loop Condition
```java
// ❌ Missing temp != null check
while(temp.next != null) {
    // If temp becomes null, crashes
}
```

### Mistake 3: Not Checking Adjacent Only
```java
// ❌ Unnecessary for sorted list
// Checking non-adjacent nodes wastes time
```

**Your solution correctly only checks temp and temp.next!**

## Pattern Recognition

**Single pointer with conditional advancement:**

This pattern applies when:
- Need to process list sequentially
- Decision at each node affects advancement
- Modifications are in-place

**Similar problems:**
- Remove Elements (#203) - Skip nodes with target value
- Delete Node (#237) - Different deletion technique
- Partition List (#86) - Reorder based on condition

## Related Problems

**Direct variations:**
- **Remove Duplicates from Sorted List II (#82)** - Remove ALL duplicates (harder)
- **Remove Duplicates from Sorted Array (#26)** - Same concept, array version
- **Remove Element (#27)** - Remove specific value

**Different approaches needed:**
- Remove duplicates from **unsorted** list - Need hash set
- Keep only duplicates - Inverse logic
- Count unique elements - Add counter

## Interview Tips

**What to mention:**
1. "List is sorted, so duplicates are adjacent"
2. "Only need to check temp vs temp.next"
3. "Skip duplicates, move only when different"
4. "O(n) time, O(1) space"

**Follow-up questions:**
- What if list was unsorted? (Need hash set, O(n) space)
- What if need to remove all duplicates entirely? (See #82, track count)
- Can you do it recursively? (Yes, but iterative is cleaner)

## Key Takeaways

### Algorithm Fundamentals
- Leverage input properties (sorted order)
- Conditional pointer advancement prevents bugs
- In-place modification saves space
- Simple logic for complex scenarios

### Problem-Solving Strategy
- Identify what makes problem easier (sorted)
- Compare adjacent elements only
- Skip vs move decision is key
- Edge cases handled naturally

### Coding Best Practices
- Clear variable naming (temp)
- Correct condition order (short-circuit)
- Minimal, efficient code
- No unnecessary complexity

## Daily Reflection

**What Went Well:**
- Clean implementation
- Correct conditional advancement
- Optimal complexity achieved
- Edge cases handled naturally

**Skills Demonstrated:**
- Leveraging input properties
- In-place modification
- Pointer manipulation
- Efficient algorithm design

**Linked List Journey Progress:**
- Day 30: Design operations
- Day 31: Three-pointer reversal
- Day 32: Two-pass/gap method
- Day 33: Fast/slow with deletion
- Day 34: Pure fast/slow
- Day 35: **Conditional advancement** ✓

**Pattern Collection:**
- ✓ Three-pointer reversal
- ✓ Two-pointer gap
- ✓ Fast/slow technique
- ✓ Conditional advancement (NEW!)
- **Comprehensive toolkit complete!**

## Next Steps

**Recommended progression:**
1. **Remove Duplicates from Sorted List II (#82)** - Harder variation
2. **Remove Linked List Elements (#203)** - Similar pattern
3. **Merge Two Sorted Lists (#21)** - Leverage sorted property
4. **Partition List (#86)** - Reorder with conditions

**Skill development:**
- Master all in-place modification patterns
- Explore two-pointer on two lists
- Study recursive alternatives
- Practice follow-up variations


**Milestone:** Two full weeks of consistent practice! Linked list foundation is rock solid. Time to explore new territories or dive deeper into hard variations.

|**Last Updated:** February 04, 2026|
|---|