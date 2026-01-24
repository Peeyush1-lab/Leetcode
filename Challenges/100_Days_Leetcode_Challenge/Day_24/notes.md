# Day 24 - Detailed Notes

## Problem 1: Kth Largest Element in an Array

**LeetCode:** [#215](https://leetcode.com/problems/kth-largest-element-in-an-array/) | **Difficulty:** Medium

---

### 📝 Problem Statement

Given an integer array `nums` and an integer `k`, return the `k-th` largest element in the array.

**Note:** It is the `k-th` largest element in sorted order, not the `k-th` distinct element.

**Follow-up:** Can you solve it without sorting?

---

### 📊 Examples

#### Example 1
```
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Explanation:
Sorted descending: [6,5,4,3,2,1]
2nd largest: 5
```

#### Example 2
```
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Explanation:
Sorted descending: [6,5,5,4,3,3,2,2,1]
4th largest: 4
```

---

### 💡 Approach

**Strategy:** Min-Heap of Size K

**Core Idea:** Maintain a min-heap containing exactly K largest elements. The root (smallest in heap) is the K-th largest overall.

#### Why Min-Heap of Size K?

**Intuition:**
- If we keep K largest elements, the smallest among them is K-th largest
- Min-heap keeps smallest element at top for easy access
- When heap exceeds K elements, remove the smallest (it's not in top K)

**Visual Example:**
```
nums = [3,2,1,5,6,4], k = 2

After processing all elements:
Min-Heap (size 2): [5, 6]
         5
        /
       6

Top element = 5 = 2nd largest ✓
```

#### Why Not Max-Heap?

**Max-Heap approach would require:**
- Store ALL elements
- Pop K-1 times to get K-th largest
- More operations, more memory

**Min-Heap approach:**
- Store only K elements
- Direct access to answer
- O(n log k) vs O(n log n)

---

### 🔄 Algorithm Steps

1. **Initialize min-heap** (PriorityQueue in Java)

2. **For each element in array:**
   - Add element to heap
   - If heap size > K:
     - Remove smallest element (heap root)

3. **Return heap root** (K-th largest element)

---

### 🔍 Key Implementation Details

#### Heap Initialization
```
PriorityQueue<Integer> pq = new PriorityQueue<>();
```
- Default: Min-heap (natural ordering)
- Smallest element always at top

#### Adding and Maintaining Size
```
pq.offer(num);
if (pq.size() > k) {
    pq.poll();
}
```
- Add current element first
- If exceeds K, remove smallest
- Ensures heap always contains K largest seen so far

#### Why This Works
At any point during iteration:
- Heap contains K largest elements processed so far
- Smallest of these K is at the top
- After processing all elements, top = K-th largest

---

### ⚙️ Complexity Analysis

- **Time Complexity:** O(n log k)
  - n elements to process
  - Each heap operation: O(log k)
  - Better than sorting: O(n log n)

- **Space Complexity:** O(k)
  - Heap stores only K elements
  - Much better than O(n) if k << n


---

## 📝 Daily Reflection

### ✅ What Went Well
- Quickly identified min-heap of size K pattern
- Implementation was clean and efficient first try
- Understanding of when to use min vs max heap is solid
- Code handles all edge cases correctly

### 💡 Key Realizations Today

**Min-Heap Size K Pattern:**
- Day 23: Top K Frequent (suggested)
- Day 24: Kth Largest Element (solved)

**Pattern components:**
1. Identify what "K" represents (size, count, lists)
2. Use min-heap to maintain K elements/pointers
3. Process elements one at a time
4. Extract from heap for result

---

### 🧩 Pattern Recognition Progress

| Day | Problem | Pattern | Data Structure |
|-----|---------|---------|----------------|
| 22 | Build Array With Stack Operations | Simulation | Array + Index |
| 23 | Maximum Eaten Apples | Greedy + Expiration | Min-Heap (time) |
| 24 | Kth Largest Element | Top K Selection | Min-Heap (size K) |
| 24 | Merge K Sorted Lists | K-way Merge | Min-Heap (K pointers) |

**Emerging Mastery:**
- ✅ Min-heap for optimization problems
- ✅ Heap size management strategies
- ✅ When heap beats sorting

---


### 💭 Questions to Explore
1. How does QuickSelect compare to min-heap for Kth largest?
2. When is divide & conquer better than heap for merging?
3. What other problems use K-way merge pattern?
4. How to optimize heap operations for very large K?

---

### 🔥 Progress Metrics
- **3-day streak** maintained
- **4 medium problems** solved
- **Min-heap pattern** mastered

---

**Last Updated:** January 24, 2026