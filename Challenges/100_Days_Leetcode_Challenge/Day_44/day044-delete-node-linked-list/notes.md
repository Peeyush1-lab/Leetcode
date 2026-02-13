# Notes - Delete Node in a Linked List

## Key Insights

### The Trick
This problem has a clever twist: we're NOT given the head of the list or the previous node. We only have access to the node that needs to be "deleted". This makes traditional deletion impossible.

### The Solution
Instead of actually deleting the node, we:
1. **Copy the next node's value** into the current node
2. **Remove the next node** from the linked list

This makes it appear as if the current node was deleted!

## Step-by-Step Walkthrough

Given: `4 -> 5 -> 1 -> 9`, delete node with value `5`

```
Step 1: We have node = 5
        4 -> [5] -> 1 -> 9
             ^
             node

Step 2: Copy next node's value (1) to current node
        4 -> [1] -> 1 -> 9
             ^
             node (now has value 1)

Step 3: Skip the next node
        4 -> [1] -----> 9
             ^
             node

Result: 4 -> 1 -> 9
```

## Code Breakdown

```java
public void deleteNode(ListNode node) {
    // Copy the value from next node to current node
    node.val = node.next.val;

    // Remove the next node by skipping it
    node.next = node.next.next;
}
```

## Why This Works

- We can't access the previous node to update its `next` pointer
- But we CAN modify the current node's value and next pointer
- By copying the next node's data and removing the next node, we achieve the same result
- The list appears to have the current node deleted

## Important Edge Cases

The problem guarantees:
- ✅ The node is NOT the tail (so `node.next` always exists)
- ✅ The node is in the list
- ✅ All values are unique

So we don't need to handle:
- ❌ Deleting the last node (impossible with this approach)
- ❌ Node not found
- ❌ Empty list

## Complexity Analysis

- **Time Complexity:** O(1) - Constant time, just two pointer operations
- **Space Complexity:** O(1) - No extra space used

## Common Mistakes to Avoid

1. ❌ Trying to find the previous node (we don't have access to head)
2. ❌ Setting `node = node.next` (this doesn't change the actual linked list)
3. ❌ Forgetting to update `node.next` (just copying value isn't enough)

## Variations to Consider

**Q: What if we need to delete the last node?**
A: This approach won't work because there's no next node to copy from. The problem guarantees this won't happen.

**Q: What if the list has duplicate values?**
A: The problem guarantees unique values, so this isn't a concern.

## Related Problems

- Remove Linked List Elements (LeetCode 203)
- Remove Nth Node From End of List (LeetCode 19)
- Delete the Middle Node of a Linked List (LeetCode 2095)