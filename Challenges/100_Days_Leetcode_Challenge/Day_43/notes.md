# Day 43 | Merge Nodes in Between Zeros

**LeetCode #2181** | Medium | Linked List, Simulation

## Problem Statement

Given the head of a linked list containing a series of integers, where each zero represents a separator, merge all nodes between each pair of consecutive zeros into a single node with value equal to the sum of the merged nodes.

Remove all zeros and return the modified linked list.

**Examples:**
```
Input: head = [0,3,1,0,4,5,2,0]
Output: [4,11]
Explanation:
  Sum between 1st and 2nd zero: 3+1 = 4
  Sum between 2nd and 3rd zero: 4+5+2 = 11

Input: head = [0,1,0,3,0,2,2,0]
Output: [1,3,4]
```

**Constraints:**
- Number of nodes: `[3, 2 × 10^5]`
- `0 <= Node.val <= 1000`
- First and last nodes have value 0
- At least two consecutive nodes with value 0

## Solution Overview

**Algorithm:** In-Place Segment Merging

**Strategy:**
1. Start with prev at head, temp at head.next
2. Accumulate values between zeros
3. When zero found, store sum in prev node
4. Move prev forward for next segment
5. Handle final zero termination
6. Return head (modified in-place)

**Key Insight:** Reuse existing nodes by updating their values instead of creating new ones.

## Implementation Breakdown

### Initialization
```java
int sum = 0;
ListNode prev = head;      // Result builder
ListNode temp = head.next; // Traverser
```

### Main Loop
```java
while(temp != null) {
    if(temp.val != 0) {
        sum += temp.val;   // Accumulate
    } else {
        prev.val = sum;    // Store sum
        sum = 0;           // Reset
        if(temp.next == null) {
            prev.next = null;  // Last zero
        } else {
            prev = prev.next;  // Move to next segment
        }
    }
    temp = temp.next;      // Always advance
}
```

## Visual Walkthrough

### Example: [0,3,1,0,4,5,2,0]

**Initial:**
```
prev  temp
 ↓     ↓
[0] → [3] → [1] → [0] → [4] → [5] → [2] → [0]
sum = 0
```

**Processing Segment 1:**
```
temp at [3]: sum = 0 + 3 = 3
temp at [1]: sum = 3 + 1 = 4
temp at [0]: Store sum in prev
  prev.val = 4
  sum = 0
  prev = prev.next

State:
[4] → [3] → [1] → [0] → [4] → [5] → [2] → [0]
       prev
                     temp
```

**Processing Segment 2:**
```
temp at [4]: sum = 0 + 4 = 4
temp at [5]: sum = 4 + 5 = 9
temp at [2]: sum = 9 + 2 = 11
temp at [0]: Store sum in prev
  prev.val = 11
  sum = 0
  temp.next == null → prev.next = null

State:
[4] → [11] → null
       prev
               temp
```

**Result:** [4,11] ✓

## In-Place Transformation

**Key technique:**
```
Original: [0,3,1,0,4,5,2,0]
          ↓
Reuse:    [4,11,?,?,?,?,?,?]
          ↓
Truncate: [4,11] → null

No new nodes created!
```

**How it works:**
1. First node (0) becomes first result (4)
2. Fourth node (0) becomes second result (11)
3. Other nodes become garbage (no references)
4. List truncated by setting prev.next = null

## Complexity Analysis

**Time: O(n)**
- Visit each node once
- Constant work per node
- Single pass

**Space: O(1)**
- Only sum, prev, temp variables
- No new nodes created
- Reuses existing structure
- Optimal space

## Why In-Place is Optimal

**Alternative (New List):**
```java
ListNode dummy = new ListNode();
ListNode tail = dummy;
// ... create new nodes ...
return dummy.next;

Space: O(k) where k = number of segments
```

**Your Approach (In-Place):**
```java
// Reuse existing nodes
prev.val = sum;
// Adjust pointers only

Space: O(1) ✓
```

## Pointer Movement Pattern

**Prev (Slow):**
- Moves once per segment
- Tracks result positions
- Updated only at zeros

**Temp (Fast):**
- Moves every iteration
- Traverses entire list
- Always advancing

**Synergy:**
```
Segment 1: prev stays, temp moves → sum accumulated
Zero found: prev updated and moved once
Segment 2: prev stays, temp moves → sum accumulated
...
```

## Edge Cases Handled

**Minimum input:** `[0,1,0]` → `[1]` ✓

**Single values:** `[0,5,0,3,0]` → `[5,3]` ✓

**Large sums:** `[0,999,999,0]` → `[1998]` ✓

**Multiple segments:** `[0,1,0,2,0,3,0]` → `[1,2,3]` ✓

**Final zero:** Always present, handled by `prev.next = null` ✓

## Key Takeaways

**In-Place Optimization:**
- Reuse nodes instead of creating new
- O(n) to O(1) space improvement
- Memory-efficient solution
- Industry best practice

**Segment Processing:**
- Markers define boundaries (zeros)
- Accumulate between markers
- Store results at markers
- Clean pattern for similar problems

**Two-Pointer Technique:**
- Different roles: builder vs traverser
- Different speeds: slow vs fast
- Synergy creates efficient algorithm

**Problem Solving:**
- Recognize in-place opportunity
- Understand marker-based processing
- Manage pointers carefully
- Handle termination correctly

## Interview Tips

**Presentation:**
1. "I'll process segments between zeros"
2. "Reuse nodes for O(1) space"
3. "Prev builds result, temp traverses"
4. "Store sums in prev positions"

**Follow-ups:**
- Q: Can you optimize space? A: Already O(1)!
- Q: What if zeros not guaranteed? A: Add validation
- Q: Time complexity? A: O(n), single pass

## Next Steps

**Similar problems:**
- Remove Zero Sum Consecutive Nodes (#1171)
- Split Linked List in Parts (#725)
- Partition List (#86)

**Skills gained:**
- In-place list transformation
- Segment-based processing
- Space optimization techniques
- Marker-based algorithms

|**Date:** February 12, 2026|
|---|