# Notes - Remove Linked List Elements

## Problem Overview

Remove ALL occurrences of nodes with a specific value from a linked list. Unlike problem 237, we have access to the head and need to handle multiple deletions.

## Key Challenges

1. **Head node deletion:** The head itself might need to be deleted
2. **Multiple consecutive deletions:** Several nodes in a row might match
3. **All nodes match:** The entire list might need to be removed
4. **Empty list:** Need to handle null input

## Solution Breakdown

### Part 1: Clean Up the Head

```java
while (head != null && head.val == val) {
    head = head.next;
}
```

**Why?** The head is special - we can't use the "skip next node" trick because there's no previous node. We need to move the head pointer forward until we find a node that doesn't match (or reach the end).

**Example:**
```
Input: [6, 6, 1, 2, 6], val = 6

After this loop: head -> [1, 2, 6]
```

### Part 2: Clean Up the Rest

```java
ListNode temp = head;

while (temp != null && temp.next != null) {
    if (temp.next.val == val) {
        temp.next = temp.next.next;  // Skip the matching node
    } else {
        temp = temp.next;  // Move to next node
    }
}
```

**Key Insight:** We check `temp.next`, not `temp`, because we need to maintain a reference to the previous node to update its `next` pointer.

**Important:** We DON'T move `temp` forward when we delete a node. Why? Because `temp.next` might be another node to delete!

## Step-by-Step Example

Remove all 6's from: `[1, 2, 6, 3, 4, 5, 6]`

```
Initial: 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6 -> null

Step 1: head doesn't match, so skip head cleanup
        temp = 1

Step 2: temp.next = 2 (not 6), move forward
        temp = 2

Step 3: temp.next = 6 (match!), skip it
        2 -> 3 (bypassing 6)
        temp stays at 2, temp.next is now 3

Step 4: temp.next = 3 (not 6), move forward
        temp = 3

Step 5: temp.next = 4 (not 6), move forward
        temp = 4

Step 6: temp.next = 5 (not 6), move forward
        temp = 5

Step 7: temp.next = 6 (match!), skip it
        5 -> null (bypassing 6)
        temp stays at 5, temp.next is now null

Step 8: temp.next = null, exit loop

Result: 1 -> 2 -> 3 -> 4 -> 5 -> null
```

## Edge Cases Handled

### 1. Empty List
```java
Input: head = null, val = 1
Output: null
```
The first while loop condition `head != null` handles this.

### 2. All Nodes Match
```java
Input: head = [7, 7, 7], val = 7
Output: null
```
The first loop removes all nodes, head becomes null.

### 3. No Nodes Match
```java
Input: head = [1, 2, 3], val = 4
Output: [1, 2, 3]
```
First loop doesn't execute, second loop never enters the if statement.

### 4. Only Head Matches
```java
Input: head = [1, 2, 3], val = 1
Output: [2, 3]
```
First loop moves head to 2, second loop processes rest normally.

### 5. Consecutive Matches
```java
Input: head = [1, 6, 6, 6, 2], val = 6
Output: [1, 2]
```
When we delete 6, temp.next becomes the next 6, so we delete it again without moving temp.

## Common Mistakes

### ❌ Mistake 1: Moving temp after deletion
```java
if (temp.next.val == val) {
    temp.next = temp.next.next;
    temp = temp.next;  // WRONG! Might skip deletions
}
```

### ❌ Mistake 2: Not handling head separately
```java
// Starting directly with temp traversal misses head deletions
ListNode temp = head;
while (temp != null && temp.next != null) {
    // ...
}
```

### ❌ Mistake 3: Checking temp instead of temp.next
```java
while (temp != null) {
    if (temp.val == val) {  // Can't delete current node this way
        // How do we skip it without previous reference?
    }
}
```

## Alternative Approach: Dummy Node

```java
public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode temp = dummy;
    while (temp.next != null) {
        if (temp.next.val == val) {
            temp.next = temp.next.next;
        } else {
            temp = temp.next;
        }
    }

    return dummy.next;
}
```

**Pros:** Cleaner code, no special head handling
**Cons:** Uses O(1) extra space for dummy node

## Complexity Analysis

- **Time Complexity:** O(n) where n is the number of nodes
  - First loop: O(k) where k is consecutive head matches
  - Second loop: O(n-k)
  - Total: O(n)

- **Space Complexity:** O(1)
  - Only using a single pointer variable `temp`
  - No recursion, no extra data structures

## Related Problems

- Delete Node in a Linked List (LeetCode 237)
- Remove Nth Node From End of List (LeetCode 19)
- Remove Duplicates from Sorted List (LeetCode 83)
- Delete the Middle Node of a Linked List (LeetCode 2095)