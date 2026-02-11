# Day 42 | Insert Greatest Common Divisors in Linked List

**LeetCode #2807** | Medium | Linked List, Math

## Problem Statement

Given the head of a linked list, insert the **greatest common divisor** (GCD) of each adjacent pair of nodes between them.

Return the head of the modified linked list.

**Examples:**
```
Input: head = [18,6,10,3]
Output: [18,6,6,2,10,1,3]
Explanation:
  GCD(18,6) = 6 → insert between
  GCD(6,10) = 2 → insert between
  GCD(10,3) = 1 → insert between

Input: head = [7]
Output: [7]
Explanation: Single node, no pairs
```

**Constraints:**
- Number of nodes: `[1, 5000]`
- `1 <= Node.val <= 1000`

## Solution Overview

**Algorithm:** Two-Pointer Traversal with GCD Insertion

**Strategy:**
1. Implement GCD helper function (Euclidean algorithm)
2. Traverse with prev and curr pointers
3. Calculate GCD of adjacent values
4. Insert new node with GCD between them
5. Move pointers forward
6. Return original head

**Key Insight:** Process adjacent pairs, insert GCD node between each pair.

## GCD Implementation

### Euclidean Algorithm
```java
public int gcd(int a, int b) {
    if (a % b == 0) return b;
    if (b % a == 0) return a;
    while (b != 0) {
        int temp = b;
        b = a % b;
        a = temp;
    }
    return a;
}
```

**How it works:**
```
GCD(18, 6):
  18 % 6 = 0 → return 6

GCD(6, 10):
  10 % 6 = 4
  6 % 4 = 2
  4 % 2 = 0 → return 2

GCD(10, 3):
  10 % 3 = 1
  3 % 1 = 0 → return 1
```

## Main Algorithm

### Node Insertion Process
```java
ListNode prev = head;
ListNode curr = head.next;
while (curr != null) {
    ListNode nn = new ListNode(gcd(prev.val, curr.val));
    nn.next = prev.next;
    prev.next = nn;
    prev = curr;
    curr = curr.next;
}
```

**Pointer movement:**
1. Start: prev at head, curr at second node
2. Calculate GCD of prev and curr values
3. Create node, insert between prev and curr
4. Move: prev to curr, curr to next
5. Repeat until curr is null

## Visual Walkthrough

### Example: [18,6,10,3]

**Initial:**
```
prev  curr
 ↓     ↓
[18] → [6] → [10] → [3] → null
```

**Iteration 1: Insert GCD(18,6) = 6**
```
Calculate GCD(18,6) = 6
Create node [6]
Insert: 18 → 6 → 6 → 10 → 3

Move pointers:
       prev  curr
         ↓     ↓
[18] → [6] → [6] → [10] → [3]
```

**Iteration 2: Insert GCD(6,10) = 2**
```
Calculate GCD(6,10) = 2
Create node [2]
Insert: 18 → 6 → 6 → 2 → 10 → 3

Move pointers:
              prev  curr
                ↓     ↓
[18] → [6] → [6] → [2] → [10] → [3]
```

**Iteration 3: Insert GCD(10,3) = 1**
```
Calculate GCD(10,3) = 1
Create node [1]
Insert: 18 → 6 → 6 → 2 → 10 → 1 → 3

Move pointers:
                       prev  curr=null
                         ↓
[18] → [6] → [6] → [2] → [10] → [1] → [3]
```

**Result:** [18,6,6,2,10,1,3] ✓

## Insertion Mechanism

**Step-by-step insertion:**
```
Before: prev → curr → next
        [A]  → [B]  → [C]

Step 1: Create new node with GCD
        nn = new Node(gcd(A, B))

Step 2: Point nn to curr
        nn.next = prev.next
        [GCD] → [B]

Step 3: Point prev to nn
        prev.next = nn
        [A] → [GCD] → [B] → [C]

Result: GCD node inserted between A and B
```

## Complexity Analysis

**Time: O(n × log(min(a,b)))**
- n = number of nodes
- Process n-1 pairs
- Each GCD: O(log(min(a,b)))
- Total: O(n × log(min))

**Space: O(n)**
- Create n-1 new nodes
- Required output
- Constant variables only

## GCD Algorithm Details

**Euclidean Algorithm:**
- Based on: GCD(a,b) = GCD(b, a mod b)
- Base case: GCD(a,0) = a
- Time: O(log(min(a,b)))

**Your optimizations:**
```java
if (a % b == 0) return b;  // Early exit
if (b % a == 0) return a;  // Early exit
// Main algorithm for other cases
```

**Why this is efficient:**
- Modulo operation reduces problem size
- Logarithmic time complexity
- Much faster than checking all divisors

## Edge Cases Handled

**Single node:** `[7]` → No pairs, return as-is ✓

**Two nodes:** `[6,9]` → Insert GCD(6,9)=3 → [6,3,9] ✓

**Coprime numbers:** `[7,11]` → GCD=1 → [7,1,11] ✓

**Same numbers:** `[5,5]` → GCD=5 → [5,5,5] ✓

**Powers:** `[8,4,2]` → GCD(8,4)=4, GCD(4,2)=2 → [8,4,4,2,2] ✓

## Key Takeaways

**Algorithm Integration:**
- Math algorithm (GCD) + Data structure (list)
- Helper function for clean separation
- Demonstrates cross-domain skills

**Insertion Pattern:**
- Two-pointer technique
- Insert between adjacent nodes
- Maintain pointer integrity
- One pass solution

**Code Organization:**
- Separate GCD calculation
- Clean main algorithm
- Single responsibility
- Easy to test and debug

**Problem Solving:**
- Recognize need for helper function
- Apply known algorithms
- Combine different concepts
- Handle edge cases naturally

## Interview Tips

**Presentation:**
1. "I'll implement GCD using Euclidean algorithm"
2. "Use two pointers to traverse adjacent pairs"
3. "Insert GCD node between each pair"
4. "O(n log min(a,b)) time complexity"

**Follow-ups:**
- Q: GCD algorithm? A: Euclidean, O(log min) time
- Q: Why not array? A: List allows efficient insertion
- Q: Space complexity? A: O(n) for new nodes

## Next Steps

**Similar problems:**
- Merge In Between Lists (#1669)
- Split Linked List in Parts (#725)
- Design Linked List (#707)

**Skills gained:**
- GCD algorithm implementation
- Node insertion between pairs
- Math + data structure combo
- Helper function design

|**Date:** February 11, 2026|
|---|