# Day 38 | Copy List with Random Pointer

**LeetCode #138** | Medium | Hash Table, Linked List

## Problem Statement

A linked list of length `n` is given where each node contains an additional random pointer, which could point to any node in the list, or `null`.

Construct a **deep copy** of the list. Return the head of the copied linked list.

**Node Structure:**
```java
class Node {
    int val;
    Node next;
    Node random;
}
```

**Examples:**
```
Input: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
```

## Solution Overview

**Algorithm:** HashMap-Based Two-Pass Deep Copy

**Strategy:**
1. **Pass 1:** Create all cloned nodes, store in HashMap (original → clone)
2. **Pass 2:** Set next and random pointers using HashMap lookups
3. Return cloned head from HashMap

**Why This Works:** Random pointers create dependencies - HashMap solves this by ensuring all nodes exist before setting pointers.

## Deep Copy vs Shallow Copy

**Shallow Copy (Wrong):**
```
Node clone = original;  // ❌ Same object, modifies original
```

**Deep Copy (Correct):**
```
Node clone = new Node(original.val);  // ✓ New object, independent
```

## Implementation Breakdown

### Pass 1: Create Nodes and Map Them
```java
HashMap<Node, Node> map = new HashMap<>();
Node temp = head;
while (temp != null) {
    map.put(temp, new Node(temp.val));
    temp = temp.next;
}
```

**What happens:** Create all new nodes without connections, store original → clone mapping.

### Pass 2: Connect Pointers
```java
temp = head;
while (temp != null) {
    Node clone = map.get(temp);
    clone.next = map.get(temp.next);
    clone.random = map.get(temp.random);
    temp = temp.next;
}
```

**What happens:** Use HashMap to find clones and set their pointers correctly.

## Why Two Passes Are Necessary

**Problem with one pass:**
```
Original: [7] → [13]
           ↓
          [13] (points forward!)

When processing [7]:
- Need clone of [13] for random pointer
- But [13]'s clone doesn't exist yet! ❌
```

**Two-pass solution:**
```
Pass 1: Create both [7'] and [13']
Pass 2: Now can set [7'].random = map.get([13]) ✓
```

## Visual Walkthrough

**Original Structure:**
```
[7] → [13] → [11]
 ↓     ↓      ↓
null  [7]   [11]
```

**After Pass 1 (nodes created, no connections):**
```
HashMap: {[7]→[7'], [13]→[13'], [11]→[11']}
All clones exist but isolated
```

**After Pass 2 (pointers connected):**
```
[7'] → [13'] → [11']
  ↓      ↓       ↓
null   [7']   [11']

Complete deep copy!
```

## Complexity Analysis

**Time: O(n)**
- Pass 1: n HashMap insertions
- Pass 2: n HashMap lookups
- Total: O(n)

**Space: O(n)**
- HashMap stores n entries
- Required output is also O(n)

## Alternative: O(1) Space Approach

**Interweaving Method:**
```
1. Insert clones between originals: A → A' → B → B' → C → C'
2. Set random: A'.random = A.random.next
3. Separate lists
```

**Trade-off:**
- O(1) space but more complex
- Your O(n) approach is cleaner and industry-standard

## Edge Cases Handled

**Empty list:** `if (head == null) return null;` ✓

**Single node:** HashMap handles naturally ✓

**Null random:** `map.get(null)` returns `null` ✓

**Self-pointing:** `map.get(node)` returns its own clone ✓

## Common Mistakes Avoided

❌ Shallow copy: `clone = temp`
✓ Your solution: `new Node(temp.val)`

❌ Setting pointers in pass 1: Forward references fail
✓ Your solution: Separate passes

❌ Pointing to original: `clone.next = temp.next`
✓ Your solution: `clone.next = map.get(temp.next)`

## Key Takeaways

**Algorithm Design:**
- Two-pass solves dependency problems
- HashMap enables O(1) lookup
- Separation of creation and connection

**Pattern Recognition:**
- Two-pass pattern: Create objects → Establish relationships
- HashMap for object mapping
- Deep copy fundamentals

**Your Solution Strengths:**
1. Industry-standard approach
1. Clean and maintainable
1. Handles all edge cases
1. Easy to extend to graphs

## Interview Tips

**Presentation:**
1. "HashMap maps original → clone"
2. "Pass 1 creates all nodes"
3. "Pass 2 sets pointers via lookups"

**Follow-ups:**

Q: O(1) space?
A: Interweaving - insert clones between originals

Q: Handle cycles?
A: HashMap handles naturally - each node visited once

Q: Complexity?
A: O(n) time, O(n) space

## Next Steps

**Similar problems:**
- Clone Graph (#133) - Extend to graphs
- Clone N-ary Tree (#1490) - Tree structure
- LRU Cache (#146) - HashMap + linked list

**Skills gained:**
- Deep copy concepts
- HashMap integration
- Two-pass algorithm pattern
- Advanced linked list operations

|**Last Updated:** February 07, 2026|
|---|