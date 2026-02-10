# Day 41 | Add Two Numbers

**LeetCode #2** | Medium | Linked List, Math

## Problem Statement

Given two non-empty linked lists representing two non-negative integers, where digits are stored in **reverse order** and each node contains a single digit, add the two numbers and return the sum as a linked list.

**Examples:**
```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807

Input: l1 = [9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,1]
Explanation: 999 + 9999 = 10998
```

**Constraints:**
- Number of nodes: `[1, 100]`
- `0 <= Node.val <= 9`
- No leading zeros except 0 itself

## Solution Overview

**Algorithm:** Three-Phase Addition with Dummy Node

**Strategy:**
1. Use dummy node to build result list
2. Add digits from both lists while both exist
3. Process remaining digits from longer list
4. Add final carry if needed
5. Return dummy.next

**Key Insight:** Numbers stored in reverse (LSD first) means we can add directly without reversal!

## Implementation Breakdown

### Phase 0: Setup
```java
ListNode head = new ListNode(-1);  // Dummy
ListNode temp = head;               // Tail pointer
int carry = 0, sum = 0;
```

### Phase 1: Both Lists Active
```java
while(temp1 != null && temp2 != null) {
    sum = temp1.val + temp2.val + carry;
    ListNode nn = new ListNode(sum % 10);
    carry = sum / 10;
    temp.next = nn;
    temp = temp.next;
    temp1 = temp1.next;
    temp2 = temp2.next;
}
```

**Process:** Add corresponding digits, handle carry, build result.

### Phase 2: First List Remaining
```java
while(temp1 != null) {
    sum = temp1.val + carry;
    ListNode nn = new ListNode(sum % 10);
    carry = sum / 10;
    temp.next = nn;
    temp = temp.next;
    temp1 = temp1.next;
}
```

### Phase 3: Second List Remaining
```java
while(temp2 != null) {
    sum = temp2.val + carry;
    ListNode nn = new ListNode(sum % 10);
    carry = sum / 10;
    temp.next = nn;
    temp = temp.next;
    temp2 = temp2.next;
}
```

### Phase 4: Final Carry
```java
if(carry > 0) {
    temp.next = new ListNode(carry);
}
```

## Visual Walkthrough

### Example: [2,4,3] + [5,6,4]

**Setup:**
```
l1: 2 → 4 → 3  (342)
l2: 5 → 6 → 4  (465)
dummy → null
```

**Iteration 1: Add 2 + 5**
```
sum = 2 + 5 + 0 = 7
digit = 7, carry = 0
dummy → 7
```

**Iteration 2: Add 4 + 6**
```
sum = 4 + 6 + 0 = 10
digit = 0, carry = 1
dummy → 7 → 0
```

**Iteration 3: Add 3 + 4**
```
sum = 3 + 4 + 1 = 8
digit = 8, carry = 0
dummy → 7 → 0 → 8
```

**No remaining digits, no final carry**

**Result:** [7,0,8] representing 807 ✓

### Example with Unequal Lengths: [9,9,9] + [9,9,9,9]

**Phase 1: Both active (3 iterations)**
```
9 + 9 + 0 = 18 → digit: 8, carry: 1
9 + 9 + 1 = 19 → digit: 9, carry: 1
9 + 9 + 1 = 19 → digit: 9, carry: 1
Result so far: 8 → 9 → 9
```

**Phase 2: l2 remaining (1 iteration)**
```
9 + 1 = 10 → digit: 0, carry: 1
Result: 8 → 9 → 9 → 0
```

**Phase 3: Final carry**
```
carry = 1 → create node
Result: 8 → 9 → 9 → 0 → 1
```

**Final:** [8,9,9,0,1] representing 10998 ✓

## Why No Reversal Needed

**Key Difference from Day 40:**

**Day 40 (Double Number):**
```
List stores MSB first: [1,8,9] = 189
Need reversal to process LSD first
```

**Day 41 (Add Numbers):**
```
List stores LSB first: [2,4,3] = 342
Already in processing order!
```

**Addition naturally proceeds from LSD to MSB with carry.**

## Complexity Analysis

**Time: O(max(m, n))**
- m = length of l1, n = length of l2
- Process all digits once
- Three phases sum to max(m,n)

**Space: O(max(m, n))**
- Result list length
- Required output space
- Constant extra variables

## Three-Phase Pattern Benefits

**Why separate phases?**

**Alternative (Complex):**
```java
while(temp1 != null || temp2 != null) {
    int val1 = (temp1 != null) ? temp1.val : 0;
    int val2 = (temp2 != null) ? temp2.val : 0;
    // Complex null checking...
}
```

**Your approach (Clean):**
```
Phase 1: Both exist → simple addition
Phase 2: Only l1 → simpler logic
Phase 3: Only l2 → simpler logic
Each phase focused, no complex conditions
```

## Edge Cases Handled

**Equal lengths:** [1,2] + [3,4] → [4,6] ✓

**Unequal lengths:** [9,9] + [1] → [0,0,1] ✓

**Different by many:** [9] + [9,9,9,9] → [8,0,0,0,1] ✓

**Final carry:** [5] + [5] → [0,1] ✓

**No carry:** [1,2,3] + [4,5,6] → [5,7,9] ✓

## Common Mistakes Avoided

❌ **Not handling unequal lengths**
✓ Your solution: Separate phases for remaining digits

❌ **Forgetting final carry**
✓ Your solution: Check after all phases

❌ **Complex null checking in single loop**
✓ Your solution: Clean three-phase structure

❌ **No dummy node**
✓ Your solution: Dummy simplifies building

## Key Takeaways

**Algorithm Design:**
- Three-phase structure is clean
- Dummy node simplifies result building
- Carry flows naturally through phases
- Each phase handles one scenario

**Pattern Recognition:**
- Reverse order enables direct processing
- Different from Day 40's approach
- Same carry concept, different structure
- Tail pointer for efficient building

**Problem Solving:**
- Break complex logic into phases
- Handle edge cases systematically
- Use proven patterns (dummy node)
- Test with various length combinations

## Comparison: Day 40 vs Day 41

| Aspect | Day 40 (Double) | Day 41 (Add) |
|--------|----------------|--------------|
| Input | 1 list | 2 lists |
| Order | MSB first | LSB first |
| Need reversal? | Yes (twice) | No |
| Operation | Multiply × 2 | Add two numbers |
| Carry | Similar concept | Similar concept |
| Phases | 3 (reverse-process-reverse) | 4 (both-l1-l2-carry) |

**Common:** Both handle carry, both build result list

## Interview Tips

**Presentation:**
1. "Lists are in reverse, can add directly"
2. "Use dummy node for clean result building"
3. "Three phases: both active, l1 remaining, l2 remaining"
4. "Handle final carry for overflow"

**Follow-ups:**
- Q: What if MSB first? A: See problem #445, need reversal
- Q: Can you do one loop? A: Yes, but more complex null checks
- Q: Space complexity? A: O(max(m,n)) for result

## Next Steps

**Similar problems:**
- Add Two Numbers II (#445) - MSB first
- Multiply Two Numbers (#43) - Multiplication
- Plus One Linked List (#369) - Increment

**Skills gained:**
- Two-list simultaneous traversal
- Unequal length handling
- Direct carry propagation
- Three-phase processing pattern

|**Last Updated:** February 10, 2026|
|---|