# Day 40 | Double a Number Represented as a Linked List

**LeetCode #2816** | Medium | Linked List, Math

## Problem Statement

Given the `head` of a non-empty linked list representing a non-negative integer without leading zeroes, return the head of the linked list after **doubling** it.

**Examples:**
```
Input: head = [1,8,9]
Output: [3,7,8]
Explanation: 189 × 2 = 378

Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: 999 × 2 = 1998
```

**Constraints:**
- Number of nodes: `[1, 10^4]`
- `0 <= Node.val <= 9`
- No leading zeros except the number 0 itself

## Solution Overview

**Algorithm:** Reverse-Process-Reverse Pattern

**Strategy:**
1. Reverse the list (to process from least significant digit)
2. Double each digit with carry propagation
3. Add new node if final carry exists
4. Reverse back to restore original order
5. Return result

**Key Insight:** Reversing allows left-to-right processing with natural carry propagation, just like manual multiplication.

## Implementation Breakdown

### Helper Function: Reverse List
```java
private ListNode reverse_ll(ListNode head) {
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

**Reused from Day 31:** Three-pointer reversal pattern.

### Main Algorithm

**Phase 1: Reverse**
```java
ListNode temp = reverse_ll(head);
```

**Phase 2: Process Digits**
```java
ListNode temp2 = temp;
int carry = 0, sum;
while(temp2 != null) {
    sum = (temp2.val * 2) + carry;
    temp2.val = sum % 10;
    carry = sum / 10;
    if(temp2.next == null) break;
    temp2 = temp2.next;
}
```

**Phase 3: Handle Final Carry**
```java
if(carry > 0) {
    temp2.next = new ListNode(carry);
}
```

**Phase 4: Reverse Back**
```java
return reverse_ll(temp);
```

## Visual Walkthrough

### Example: [1,8,9] → [3,7,8]

**Original:** 1 → 8 → 9 (represents 189)

**Step 1: Reverse**
```
9 → 8 → 1 (least significant first)
```

**Step 2: Process with carry**
```
Process 9:
  sum = 9 × 2 + 0 = 18
  digit = 18 % 10 = 8
  carry = 18 / 10 = 1
Result: 8 → 8 → 1

Process 8:
  sum = 8 × 2 + 1 = 17
  digit = 17 % 10 = 7
  carry = 17 / 10 = 1
Result: 8 → 7 → 1

Process 1:
  sum = 1 × 2 + 1 = 3
  digit = 3 % 10 = 3
  carry = 3 / 10 = 0
Result: 8 → 7 → 3

No final carry
```

**Step 3: Reverse Back**
```
3 → 7 → 8 (represents 378) ✓
```

### Example with Overflow: [9,9,9] → [1,9,9,8]

**Original:** 9 → 9 → 9 (999)

**After Reverse:** 9 → 9 → 9

**Process:**
```
9 × 2 = 18 → digit: 8, carry: 1
9 × 2 + 1 = 19 → digit: 9, carry: 1
9 × 2 + 1 = 19 → digit: 9, carry: 1

Final carry: 1 (create new node)
Result: 8 → 9 → 9 → 1
```

**After Reverse:** 1 → 9 → 9 → 8 (1998) ✓

## Why Reverse is Necessary

**Problem with forward processing:**
```
[1,8,9]
Process 1 first:
  1 × 2 = 2, but need to know if carry from right!
  Can't determine without seeing rest
```

**Solution with reversal:**
```
[9,8,1] (reversed)
Process 9 first (rightmost digit):
  Start with no carry
  Propagate carry forward naturally
  Just like manual multiplication!
```

## Complexity Analysis

**Time: O(n)**
- First reversal: O(n)
- Processing: O(n)
- Second reversal: O(n)
- Total: 3n = O(n)

**Space: O(1)**
- Only pointers and carry variable
- In-place modifications
- One potential new node (still O(1))

## Edge Cases Handled

**Single digit:** `[5] → [1,0]`
- Doubling creates carry
- New node added ✓

**No carry:** `[1,2,3] → [2,4,6]`
- No final carry
- Direct doubling ✓

**All nines:** `[9,9,9] → [1,9,9,8]`
- Maximum carry propagation
- New node for final carry ✓

**Zero:** `[0] → [0]`
- 0 × 2 = 0
- No carry ✓

## Pattern Reuse from Day 31

**Day 31 - Reverse Linked List:**
```
Learned three-pointer reversal
Created reusable function
```

**Day 40 - Double a Number:**
```
Reused reversal function TWICE
Shows pattern mastery
Demonstrates code modularity
```

**This is algorithmic thinking!**

## Alternative Approaches

**Approach 1: Stack**
```
Push all digits → Pop and process → Build result
Time: O(n), Space: O(n)
Less efficient, more space
```

**Approach 2: Recursive**
```
Recurse to end → Process on return → Propagate carry back
Time: O(n), Space: O(n) stack
Elegant but uses stack space
```

**Your approach: O(1) space is optimal!**

## Key Takeaways

**Pattern Application:**
- Reversal pattern applied to new domain
- Helper function reuse demonstrated
- Three-phase structure (reverse-process-reverse)

**Problem Decomposition:**
- Break into simpler subproblems
- Reuse proven solutions
- Combine for complete solution

**Carry Handling:**
- Process right-to-left naturally
- Track carry through iterations
- Handle overflow with new node

**Code Quality:**
- Helper function extraction
- Single responsibility
- Clean separation of concerns

## Interview Tips

**Presentation:**
1. "I'll reverse the list to process digits left-to-right"
2. "Double each digit with carry propagation"
3. "Add new node if final carry exists"
4. "Reverse back to restore order"

**Follow-ups:**
- Q: Why reverse? A: Enables natural carry propagation
- Q: Space complexity? A: O(1), only pointers
- Q: Alternative? A: Stack (O(n) space) or recursion

## Next Steps

**Similar problems:**
- Add Two Numbers (#2) - Similar pattern
- Add Two Numbers II (#445) - Reverse technique
- Plus One Linked List (#369) - Simpler version

**Skills gained:**
- Digit arithmetic in lists
- Carry propagation mastery
- Helper function reuse
- Pattern application across domains

|**Last Updated:** February 09, 2026|
|---|