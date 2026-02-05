# Day 36 - LeetCode Challenge

| **Date:** February 05, 2026 |
| --------------------------- |

## 📋 Problem Solved

### ✅ Merge Two Sorted Lists

* **LeetCode:** #21
* **Difficulty:** Easy
* **Status:** Solved
* **Topics:** Linked List, Recursion, Merge Algorithm
* **Key Concept:** Recursive merge of two sorted linked lists by choosing the smaller head

## 📊 Daily Stats

* **Problems Attempted:** 1
* **Problems Solved:** 1
* **Time Spent:** ~30 minutes
* **Concepts Practiced:** Recursion, pointer redirection, merge algorithm

## 🎯 Learning Focus

* **Primary:** Recursive merge strategy for sorted linked lists
* **Secondary:** Breaking a problem into smaller subproblems
* **Advanced:** Understanding recursion stack behavior

## 🧠 Approach Overview (Recursive)

Instead of creating a new list or using a tail pointer, this approach:

* Compares the head nodes of both lists
* Selects the smaller node as the current head
* Recursively merges the remaining part of that list with the other list

Each recursive call solves a smaller version of the same problem.

## 🧩 Algorithm Explanation

1. **Base Cases**

   * If `l1` is `null`, return `l2`
   * If `l2` is `null`, return `l1`

2. **Recursive Step**

   * Compare `l1.val` and `l2.val`
   * The smaller value becomes part of the merged list
   * Its `next` pointer is set to the result of a recursive call

3. **Return Head**

   * Each recursive call returns the head of a partially merged list
   * These heads connect together to form the final list

## 💻 Solution Code

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```

## 🔍 Example Walkthrough

**Input:**

* `l1 = [1,2,4]`
* `l2 = [1,3,4]`

**Execution Flow:**

* Compare 1 and 1 → take from `l2`
* Compare 1 and 3 → take from `l1`
* Compare 2 and 3 → take from `l1`
* Compare 4 and 3 → take from `l2`
* Compare 4 and 4 → take from `l2`
* Append remaining node

**Output:**
`[1,1,2,3,4,4]`

## ⚙️ Complexity Analysis

* **Time Complexity:** O(n + m)

  * Each node is visited once

* **Space Complexity:** O(n + m)

  * Due to recursion stack

## ⚖️ Comparison with Iterative Approach

| Approach                | Time   | Space  | Notes                |
| ----------------------- | ------ | ------ | -------------------- |
| Recursive               | O(n+m) | O(n+m) | Elegant, clean logic |
| Iterative (new list)    | O(n+m) | O(n+m) | Preserves inputs     |
| Iterative (reuse nodes) | O(n+m) | O(1)   | Most optimal         |

## 💡 Key Takeaways

* Recursion naturally fits problems that can be broken into smaller identical subproblems
* Base cases are critical to avoid infinite recursion
* Recursive solutions are expressive but use extra stack space
* Understanding recursion flow is essential for interviews

## 🎓 Pattern Recognition

This recursive merge pattern appears in:

* Merge Sort on Linked Lists
* Divide and Conquer algorithms
* Tree-based recursion problems

## 📁 Folder Structure

```
Day36/
├── README.md
├── notes.md
└── Solution.java
```

## 📈 Progress Journey

| Day | Problem                | Difficulty | Core Pattern            |
| --- | ---------------------- | ---------- | ----------------------- |
| 33  | Delete Middle Node     | Medium     | Fast/Slow               |
| 34  | Middle of Linked List  | Easy       | Fast/Slow               |
| 35  | Remove Duplicates      | Easy       | Conditional Advancement |
| 36  | Merge Two Sorted Lists | Easy       | Recursive Merge         |

## 🌟 Why This Problem Matters

* Fundamental merge logic used in sorting algorithms
* Frequently asked in interviews
* Builds confidence with recursion and pointer handling

| **Previous:** Day 35 | **Next:** Day 37 |
|---|---|