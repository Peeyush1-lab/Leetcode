# 232. Implement Queue using Stacks

**Difficulty:** Easy
**Day:** 67
**Topics:** Stack, Design, Queue

## Problem Description

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (`push`, `peek`, `pop`, and `empty`).

Implement the `MyQueue` class:

- `void push(int x)` Pushes element x to the back of the queue.
- `int pop()` Removes the element from the front of the queue and returns it.
- `int peek()` Returns the element at the front of the queue.
- `boolean empty()` Returns `true` if the queue is empty, `false` otherwise.

**Notes:**

- You must use **only** standard operations of a stack, which means only `push to top`, `peek/pop from top`, `size`, and `is empty` operations are valid.
- Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

## Examples

### Example 1:

**Input:**
```
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
```

**Output:**
```
[null, null, null, 1, 1, false]
```

**Explanation:**
```
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek();  // return 1
myQueue.pop();   // return 1, queue is [2]
myQueue.empty(); // return false
```

## Constraints

- `1 <= x <= 9`
- At most `100` calls will be made to `push`, `pop`, `peek`, and `empty`.
- All the calls to `pop` and `peek` are valid.

## Follow-up

Can you implement the queue such that each operation is **amortized** `O(1)` time complexity? In other words, performing `n` operations will take overall `O(n)` time even if one of those operations may take longer.

## Topics

- Stack
- Design
- Queue

## Similar Problems

- [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/) - Easy
- [155. Min Stack](https://leetcode.com/problems/min-stack/) - Medium
- [622. Design Circular Queue](https://leetcode.com/problems/design-circular-queue/) - Medium
- [641. Design Circular Deque](https://leetcode.com/problems/design-circular-deque/) - Medium
- [1700. Number of Students Unable to Eat Lunch](https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/) - Easy

## Related Topics Problems

**Design Problems:**
- [146. LRU Cache](https://leetcode.com/problems/lru-cache/) - Medium
- [208. Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) - Medium
- [211. Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) - Medium
- [380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/) - Medium
- [705. Design HashSet](https://leetcode.com/problems/design-hashset/) - Easy
- [706. Design HashMap](https://leetcode.com/problems/design-hashmap/) - Easy

**Stack-Based Data Structures:**
- [173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/) - Medium
- [284. Peeking Iterator](https://leetcode.com/problems/peeking-iterator/) - Medium
- [341. Flatten Nested List Iterator](https://leetcode.com/problems/flatten-nested-list-iterator/) - Medium

**Queue Problems:**
- [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls/) - Easy
- [346. Moving Average from Data Stream](https://leetcode.com/problems/moving-average-from-data-stream/) - Easy
- [649. Dota2 Senate](https://leetcode.com/problems/dota2-senate/) - Medium

---

|**Previous:** [Day 66](../Day_66/) | **Next:** [Day 68](../Day_68/)|
|---|---|

|**Date:** March 08, 2026|
|---|