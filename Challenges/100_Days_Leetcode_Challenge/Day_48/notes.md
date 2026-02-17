# Notes - Convert Binary Number in a Linked List to Integer

## Problem Overview

Convert a binary number stored in a linked list (MSB at head) into its decimal equivalent. Each node holds a single binary digit (0 or 1).

## Core Concept: Binary to Decimal

### How Binary Works

Each bit position represents a power of 2, starting from the rightmost bit (position 0):

```
Binary:   1    0    1
Position: 2    1    0
Value:    4  + 0  + 1  = 5
```

### General Formula

For a binary number with `n` digits:
```
decimal = b[0] × 2^(n-1) + b[1] × 2^(n-2) + ... + b[n-1] × 2^0
```

Where `b[0]` is the most significant bit (head of the list).

## Your Solution Approach

### Two-Pass Strategy

**Pass 1:** `findLength()` - Count total nodes to know starting power of 2
**Pass 2:** `getDecimalValue()` - Traverse and compute decimal value

### Step-by-Step Walkthrough

#### Example: head = [1, 0, 1]

```
findLength returns: 3

Pass 2 starts: length = 3, ans = 0

Iteration 1: head.val = 1, length-1 = 2
  ans += 2^2 = 4
  ans = 4
  head moves to next, length = 2

Iteration 2: head.val = 0, length-1 = 1
  val is 0, skip
  head moves to next, length = 1

Loop exits (head.next == null)

Last node: head.val = 1
  ans += 1
  ans = 5

Return: 5  ✅
```

#### Example: head = [1, 1, 1]

```
findLength returns: 3, ans = 0

Iteration 1: head.val = 1 → ans += 2^2 = 4 → ans = 4
Iteration 2: head.val = 1 → ans += 2^1 = 2 → ans = 6

Last node: head.val = 1 → ans += 1 → ans = 7

Return: 7  ✅ (binary 111 = 7)
```

## Code Breakdown

```java
public int getDecimalValue(ListNode head) {
    ListNode node = head;
    int length = findLength(node);  // Get total number of bits
    int ans = 0;

    // Process all nodes except the last
    while(head.next != null){
        if(head.val == 1){
            ans += Math.pow(2, length-1);  // Add 2^(position) if bit is 1
        }
        head = head.next;
        length--;  // Decrease power for next position
    }

    // Handle last node separately (2^0 = 1)
    if(head.val == 1){
        ans += 1;
    }
    return ans;
}

public int findLength(ListNode node){
    int l = 1;  // Start at 1 to count the first node
    while(node.next != null){
        node = node.next;
        l++;
    }
    return l;
}
```

### Key Design Choices

1. **`length` starts at total number of bits** - So the first node uses `2^(length-1)`
2. **Loop condition is `head.next != null`** - Stops before last node
3. **Last node handled separately** - Its power is always `2^0 = 1`
4. **Only add when `val == 1`** - No need to add `0 × 2^n`

## Common Mistakes

### ❌ Mistake 1: Wrong starting power

```java
// If length = 3, first node should use 2^2, not 2^3
ans += Math.pow(2, length);  // Wrong!
ans += Math.pow(2, length-1);  // Correct!
```

### ❌ Mistake 2: Off-by-one in findLength

```java
int l = 0;  // Wrong! Misses the first node
while(node.next != null){
    node = node.next;
    l++;
}
// Returns length - 1
```

**Fix:** Start `l = 1` to count the head node.

### ❌ Mistake 3: Forgetting to handle the last node

```java
// If loop exits at last node, its value is never processed!
while(head.next != null){
    if(head.val == 1) ans += Math.pow(2, length-1);
    head = head.next;
    length--;
}
// Missing: handle last node
```

## Alternative Approaches

### Approach 1: Bit Shifting (Most Elegant) ✅

```java
public int getDecimalValue(ListNode head) {
    int ans = 0;
    while(head != null) {
        ans = (ans << 1) | head.val;
        head = head.next;
    }
    return ans;
}
```

**How it works:**
- Left shift `ans` by 1 (multiply by 2)
- OR with current bit (adds 0 or 1)
- Single pass, no need to find length!

**Example:** `[1, 0, 1]`
```
Start:       ans = 0
Node 1 (1):  ans = (0 << 1) | 1 = 0 | 1 = 1
Node 2 (0):  ans = (1 << 1) | 0 = 2 | 0 = 2
Node 3 (1):  ans = (2 << 1) | 1 = 4 | 1 = 5
Return: 5 ✅
```

**Time:** O(n), **Space:** O(1), **Passes:** 1

### Approach 2: String Conversion

```java
public int getDecimalValue(ListNode head) {
    StringBuilder sb = new StringBuilder();
    while(head != null) {
        sb.append(head.val);
        head = head.next;
    }
    return Integer.parseInt(sb.toString(), 2);  // Parse as base-2
}
```

**Pros:** Very readable
**Cons:** Extra string allocation, less efficient

### Approach 3: Recursive

```java
public int getDecimalValue(ListNode head) {
    return helper(head, 0);
}

private int helper(ListNode node, int current) {
    if(node == null) return current;
    return helper(node.next, (current << 1) | node.val);
}
```

**Pros:** Clean functional style
**Cons:** O(n) stack space

## Approach Comparison

| Approach | Time | Space | Passes | Complexity |
|----------|------|-------|--------|------------|
| Your solution (positional) | O(n) | O(1) | 2 | Medium |
| Bit shifting | O(n) | O(1) | 1 | Simple |
| String conversion | O(n) | O(n) | 1 | Easiest |
| Recursive | O(n) | O(n) | 1 | Medium |

## Why the Bit Shift Approach is Cleaner

Your approach is correct and works perfectly! The bit shift approach is slightly more elegant because:

1. **Single pass** - no need for `findLength()`
2. **No `Math.pow()`** - uses bitwise operations instead
3. **Handles last node naturally** - no special case needed
4. **More concise** - fewer lines of code

But your solution demonstrates a solid understanding of **binary number theory** - knowing which position maps to which power of 2. That's a valuable insight!

## Complexity Analysis

### Your Solution

**Time Complexity:** O(n)
- `findLength()`: O(n) - traverse entire list
- Main loop: O(n) - traverse entire list again
- Total: O(2n) = O(n)

**Space Complexity:** O(1)
- Only `length`, `ans`, and pointer variables
- No extra data structures

## Key Takeaways

1. **MSB is at head** - first node has the highest power of 2
2. **`length - 1` gives correct starting power** - for a 3-bit number, start at `2^2`
3. **Two-pass approach works** - find length first, then compute
4. **Bit shifting is the optimal single-pass solution** - worth knowing!
5. **Only add when bit is 1** - no need to handle 0 bits explicitly

## Related Problems

- **1017. Convert to Base -2 (Medium)** - Number base conversion
- **67. Add Binary (Easy)** - Binary addition
- **190. Reverse Bits (Easy)** - Bit manipulation
- **405. Convert a Number to Hexadecimal (Easy)** - Base conversion

## Interview Tips

1. **Start with your approach** - explain the positional logic clearly
2. **Mention the bit shift optimization** - shows deeper bit manipulation knowledge
3. **Explain the two-pass trade-off** - first pass gets length, second computes value
4. **Walk through an example** - trace `[1,0,1]` → 5
5. **Handle edge cases** - single node `[0]` and `[1]`

Great solution! The positional approach shows strong understanding of binary number systems!