# Day 37 | Palindrome Linked List

**LeetCode #234** | Easy | Linked List, Two Pointers

## Problem Statement

Given the `head` of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

**Follow-up:** Could you do it in O(n) time and O(1) space?

**Examples:**

```
Example 1:
Input: head = [1,2,2,1]
Output: true
Explanation: Reads same forwards and backwards

Example 2:
Input: head = [1,2]
Output: false

Example 3:
Input: head = [1]
Output: true

Example 4:
Input: head = [1,2,3,2,1]
Output: true
```

**Constraints:**
- Number of nodes: `[1, 10^5]`
- `0 <= Node.val <= 9`

## Solution Overview

**Algorithm:** Fast/Slow Pointer + Reversal Combination

**Key Insight:** This problem combines two previously learned patterns:
- **Fast/Slow Pointer (Day 34)** - Find middle of list
- **List Reversal (Day 31)** - Reverse second half

**Your Solution Strategy:**
1. Handle edge cases (null, single node)
2. Find middle using fast/slow pointers
3. Reverse second half starting from middle
4. Compare first half with reversed second half
5. Return result based on comparison

**Why This Works:**
- Palindrome means same forwards and backwards
- Split list at middle
- Reverse second half
- If palindrome, first half == reversed second half

## Implementation Breakdown

### Helper Function: Reverse Linked List
```java
private static ListNode reverseLL(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    ListNode next;
    while(curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

**Purpose:** Reverse a linked list in-place
**Reused from:** Day 31 (Reverse Linked List problem)
**Time:** O(n), **Space:** O(1)

**This is the three-pointer reversal pattern:**
- Save next
- Reverse current's pointer
- Advance prev and curr

### Main Function: Check Palindrome

**Phase 1: Edge Cases**
```java
if(head == null || head.next == null) {
    return true;
}
```
- Empty list: Palindrome by definition
- Single node: Always palindrome
- Early exit prevents null checks later

**Phase 2: Find Middle**
```java
ListNode slow = head;
ListNode fast = head;
while(fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
```
- Fast/slow pointer pattern from Day 34
- When fast reaches end, slow is at middle
- For odd length: slow at exact middle
- For even length: slow at start of second half

**Phase 3: Reverse Second Half**
```java
ListNode lefthead = reverseLL(slow);
```
- Reverse from middle to end
- `lefthead` points to new head of reversed portion
- First half remains unchanged
- Now we have two lists to compare

**Phase 4: Compare Both Halves**
```java
ListNode temp = head;
while(lefthead != null) {
    if(lefthead.val != temp.val) {
        return false;
    }
    lefthead = lefthead.next;
    temp = temp.next;
}
return true;
```
- Compare node by node
- `lefthead` traverses reversed second half
- `temp` traverses first half
- Any mismatch → not palindrome
- If loop completes → palindrome

## Visual Walkthrough

### Example 1: [1,2,2,1] (Even Length, Palindrome)

**Initial State:**
```
1 → 2 → 2 → 1 → null
```

**Step 1: Find Middle**
```
Fast/slow pointers:
slow,fast
  ↓
1 → 2 → 2 → 1 → null

After iteration 1:
    slow    fast
      ↓       ↓
1 → 2 → 2 → 1 → null

After iteration 2:
           slow         fast=null
             ↓
1 → 2 → 2 → 1 → null

slow points to second 2 (start of second half)
```

**Step 2: Reverse Second Half**
```
Original second half: 2 → 1 → null
After reversal: 1 → 2 → null

Now we have:
First half:  1 → 2 → ...
Second half (reversed): 1 → 2 → null
             ↑
          lefthead
```

**Step 3: Compare**
```
temp (first half):    1 → 2
lefthead (reversed):  1 → 2

Compare:
1 == 1 ✓
2 == 2 ✓

Result: true (palindrome)
```

### Example 2: [1,2,3,2,1] (Odd Length, Palindrome)

**Initial State:**
```
1 → 2 → 3 → 2 → 1 → null
```

**Step 1: Find Middle**
```
Fast/slow movement:
Initial: both at 1
Step 1: slow at 2, fast at 3
Step 2: slow at 3, fast at 1
Step 3: fast.next is null, stop

slow at 3 (exact middle for odd length)
```

**Step 2: Reverse Second Half**
```
Second half from middle: 3 → 2 → 1 → null
After reversal: 1 → 2 → 3 → null
                ↑
             lefthead

First half unchanged: 1 → 2 → ...
```

**Step 3: Compare**
```
temp:     1 → 2 → 3
lefthead: 1 → 2 → 3

Compare all:
1 == 1 ✓
2 == 2 ✓
3 == 3 ✓ (middle counts in both, still works)

Result: true (palindrome)
```

### Example 3: [1,2] (Not Palindrome)

**Initial State:**
```
1 → 2 → null
```

**Step 1: Find Middle**
```
slow at 2, fast at null
```

**Step 2: Reverse Second Half**
```
Second half: 2 → null
Reversed: 2 → null (no change, single node)
lefthead points to 2
```

**Step 3: Compare**
```
temp at 1
lefthead at 2

1 != 2 ✗

Result: false (not palindrome)
```

## Why This Solution is Optimal

### Pattern Combination

**Reuses Two Learned Patterns:**
1. **Fast/Slow Pointer** - O(n/2) to find middle
2. **List Reversal** - O(n/2) to reverse second half

**Combined Complexity:**
- Time: O(n/2) + O(n/2) + O(n/2) = O(n)
- Space: O(1) (only pointers)

### Space Optimization Explained

**Naive approaches:**
```
Array conversion: O(n) space - store all values
Stack approach: O(n/2) space - push first half
Recursion: O(n) space - call stack

Your approach: O(1) space - only pointers!
```

**How O(1) is achieved:**
- No new data structures
- Reversal is in-place
- Only pointer variables used

### Handles Both Odd and Even Length

**Odd length:** [1,2,3,2,1]
- Middle at 3
- First half: 1,2,3
- Second half: 3,2,1 → reversed: 1,2,3
- Comparison includes middle node

**Even length:** [1,2,2,1]
- Split exactly in middle
- First half: 1,2
- Second half: 2,1 → reversed: 1,2
- Perfect comparison

**Algorithm handles both automatically!**

## Complexity Analysis

**Time Complexity: O(n)**
- Find middle: n/2 iterations
- Reverse second half: n/2 operations
- Compare: n/2 comparisons
- Total: O(n)

**Space Complexity: O(1)**
- `prev`, `curr`, `next` in reversal
- `slow`, `fast` for finding middle
- `lefthead`, `temp` for comparison
- All: constant space

**Optimal:** Cannot check palindrome faster than O(n) - must visit all nodes.

## Alternative Approaches Comparison

### Approach 1: My Solution (Optimal)
```
Time: O(n)
Space: O(1)
Pros: Optimal space, in-place
Cons: Modifies list (can restore)
```

### Approach 2: Stack-Based
```
Algorithm:
- Traverse list, push first half to stack
- Compare second half with stack

Time: O(n)
Space: O(n/2) = O(n)
Pros: Simpler to understand
Cons: Extra space, not optimal
```

### Approach 3: Array Conversion
```
Algorithm:
- Convert list to array
- Use two pointers from ends

Time: O(n)
Space: O(n)
Pros: Very simple
Cons: Defeats purpose of linked list
```

### Approach 4: Recursive
```
Algorithm:
- Use recursion to reach end
- Compare on return path

Time: O(n)
Space: O(n) recursion stack
Pros: Elegant
Cons: Stack overflow risk, not optimal space
```

**Interview ranking:**
1. My approach (optimal)
2. Stack-based (acceptable)
3. Array (acceptable but questioned)
4. Recursive (elegant but not optimal)

## Key Implementation Details

### Why Variable Named `lefthead`?

```java
ListNode lefthead = reverseLL(slow);
```

**Naming insight:**
- After reversal, second half is reversed
- `lefthead` points to what was the rightmost node
- Now reads left-to-right from the end
- Name reflects conceptual direction

### Why Loop on `lefthead`?

```java
while(lefthead != null) {
    if(lefthead.val != temp.val) return false;
    lefthead = lefthead.next;
    temp = temp.next;
}
```

**For odd length lists:**
- First half has one more node (middle)
- Second half (reversed) is shorter
- Looping on `lefthead` ensures we don't overrun
- Middle node gets checked twice (okay for palindrome)

**For even length lists:**
- Both halves equal length
- Either condition works
- `lefthead` is convention

### Helper Function Benefits

**Why separate `reverseLL()` function?**
1. **Code reuse** - Same as Day 31 solution
2. **Modularity** - Easy to test independently
3. **Readability** - Clear what each part does
4. **Maintainability** - Changes isolated

## Common Mistakes to Avoid

### Mistake 1: Not Handling Edge Cases
```java
// ❌ Crashes on null
public boolean isPalindrome(ListNode head) {
    ListNode slow = head;  // NPE if head is null
```

**Your solution handles this correctly!**

### Mistake 2: Wrong Middle Finding
```java
// ❌ Wrong loop condition
while(fast.next != null) {
    // Doesn't handle both odd and even
}
```

**Your solution uses correct condition:**
```java
while(fast != null && fast.next != null)
```

### Mistake 3: Comparing Wrong Lengths
```java
// ❌ Loop on temp instead of lefthead
while(temp != null) {
    // For odd length, temp is longer!
}
```

**Your solution correctly loops on shorter half!**

## Pattern Synthesis Insight

**This problem proves pattern mastery:**

**Day 31:** Learned reversal
```
reverseLL(head) → reverse entire list
```

**Day 34:** Learned fast/slow
```
Find middle node
```

**Day 37:** Combined both
```
1. Use fast/slow to find middle
2. Use reversal on second half
3. Compare results
```

**This is algorithmic thinking progression!**

## Interview Tips

**How to present solution:**
1. "I'll use fast/slow to find the middle"
2. "Then reverse the second half"
3. "Compare first half with reversed second half"
4. "This achieves O(n) time, O(1) space"

**Follow-up questions:**

**Q: Can you restore the original list?**
A: Yes, reverse the second half again after checking.

**Q: What if we can't modify the list?**
A: Need O(n) space - use stack or recursion.

**Q: How would you handle doubly linked list?**
A: Easier - traverse from both ends simultaneously.

**Q: Space complexity with recursion?**
A: O(n) due to call stack.

**What interviewers look for:**
- Pattern recognition (fast/slow + reversal)
- Code reuse (helper function)
- Edge case handling
- Space optimization awareness

## Key Takeaways

### Algorithm Design
- Complex problems combine simple patterns
- Breaking down problems is crucial
- Pattern library enables quick solutions
- Helper functions promote clean code

### Problem-Solving Strategy
- Recognize subproblems already solved
- Combine patterns for optimal solution
- Consider space-time trade-offs
- Always handle edge cases first

### Linked List Mastery
1. Fast/slow pointer versatility
1. In-place reversal applications
1. Comparison techniques
1. Pattern combination power

## Daily Reflection

**What Went Well:**
- Perfect pattern combination
- Optimal O(1) space achieved
- Clean helper function usage
- Edge cases handled correctly

**Pattern Evolution:**
- Day 31: Reversal (building block)
- Day 34: Fast/slow (building block)
- Day 37: **Combined both** (synthesis)

**Skills Demonstrated:**
- Pattern recognition
- Code reuse
- Problem decomposition
- Space optimization

**Learning Journey:**
```
Individual patterns → Pattern combination → Complex solutions

This is the path to algorithmic mastery!
```

## Linked List Journey Complete

**7-Day Pattern Collection:**
- Day 30: Design operations
- Day 31: Three-pointer reversal ✓ (reused today)
- Day 32: Two-pass/gap method
- Day 33: Fast/slow with deletion
- Day 34: Pure fast/slow ✓ (reused today)
- Day 35: Conditional advancement
- Day 36: Merge algorithm
- Day 37: **Pattern combination** ✓

**Mastery Achieved:**
- All individual patterns learned
- Pattern combination demonstrated
- Helper function reuse shown
- Space optimization proven

## Next Steps

**Similar Pattern Combinations:**
1. **Reorder List (#143)** - Find middle, reverse, merge (3 patterns!)
2. **Add Two Numbers II (#445)** - Reverse both, add, reverse result
3. **Sort List (#148)** - Merge sort with find middle
4. **Copy List with Random Pointer (#138)** - Multi-pattern synthesis

**Advanced Challenges:**
- Problems requiring 3+ pattern combinations
- Space-constrained variations
- In-place modifications
- Complex pattern synthesis

**Skill Mastery:**
- ✓ Individual pattern execution
- ✓ Two-pattern combination
- → Three-pattern combination (next)
- → Complex algorithmic synthesis

|**Last Updated:** February 06, 2026|
|---|