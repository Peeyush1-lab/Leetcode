# Day 23 - Detailed Notes

---

## Problem 1: Maximum Number of Eaten Apples

**LeetCode:** [#1705](https://leetcode.com/problems/maximum-number-of-eaten-apples/) | **Difficulty:** Medium

---

### 📝 Problem Statement

You have `n` days where on the `i-th` day:
- You grow `apples[i]` apples
- These apples will rot after `days[i]` days from day `i`

**Goal:** Maximize the number of apples you can eat.

**Rules:**
- Can eat at most **one apple per day**
- Must eat apples **before they rot**
- Can **continue eating after day n** (after tree stops producing)

---

### 📊 Examples

#### Example 1
```
Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
Output: 7

Explanation:
Day 0: Grow 1 apple, expires day 3. Eat 1 apple.
Day 1: Grow 2 apples, expires day 3. Eat 1 apple. (1 left)
Day 2: Grow 3 apples, expires day 3. Eat 1 apple. (1+2=3 left)
Day 3: Grow 5 apples, expires day 7. Eat 1 apple from day 1 batch.
Day 4: Eat 1 apple from day 2 batch.
Day 5: Eat 1 apple from day 2 batch.
Day 6: Eat 1 apple from day 3 batch.
Total: 7 apples
```

#### Example 2
```
Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
Output: 5
```

---

### 💡 Approach

**Strategy:** Greedy Algorithm with Min-Heap (Priority Queue)

**Core Idea:** Always eat the apple that will rot soonest to maximize total apples eaten.

#### Why Priority Queue?
- Need to efficiently find apple batch expiring soonest
- Min-heap orders by expiration date automatically
- O(log n) insertion and removal

#### Data Structure Choice
- **Store:** `[expiration_day, apple_count]`
- **Priority:** Earliest expiration first
- **Type:** Min-heap ordered by `expiration_day`

---

### 🔄 Algorithm Steps

1. **Initialize:**
   - Min-heap storing `[expiration_day, count]`
   - Day counter starting at 0
   - Eaten apple counter

2. **For each day (while production continues OR heap has apples):**
   - Add today's apple batch to heap (if any)
   - Remove all expired/empty batches
   - If valid apples exist:
     - Eat one apple from batch expiring soonest
     - Decrement that batch's count
     - Re-add batch if apples remain
   - Increment day counter

3. **Return total eaten**

---

### 🔍 Key Implementation Details

#### Adding Apples to Heap
```
if (day < n && apples[day] > 0) {
    heap.offer([day + days[day], apples[day]]);
}
```
- Only add if within production period AND apples exist
- Expiration = current day + days they last

#### Cleaning Expired Batches
```
while (!heap.isEmpty() && (heap.peek()[0] <= day || heap.peek()[1] == 0)) {
    heap.poll();
}
```
- Remove if expired (expiration day ≤ current day)
- Remove if no apples left in batch

#### Eating Logic
```
if (!heap.isEmpty()) {
    batch = heap.poll();
    batch[1]--;
    eaten++;
    if (batch[1] > 0) {
        heap.offer(batch);
    }
}
```
- Take batch expiring soonest
- Eat one apple, increment counter
- Return batch if apples remain

---

### ⚙️ Complexity Analysis

- **Time Complexity:** O(n log n)
  - Each of n batches added/removed from heap once
  - Heap operations: O(log n)

- **Space Complexity:** O(n)
  - Heap can store up to n batches

---

### 🎯 Key Insights

1. **Greedy Choice is Optimal:**
   - Eating apples closest to expiring maximizes future options
   - Never beneficial to save expiring apples

2. **Continue Beyond Production:**
   - Loop condition: `day < n || !heap.isEmpty()`
   - Must process all harvested apples even after day n

3. **Batch Cleanup is Essential:**
   - Prevents processing invalid data
   - Avoids unnecessary iterations

4. **Decrement and Re-add Pattern:**
   - Cleaner than maintaining separate counters
   - Leverages heap's automatic reordering

---

### 🐛 Edge Cases Handled

| Case | Example | Handling |
|------|---------|----------|
| No apples on some days | `apples = [1,0,2]` | Skip days with 0 apples |
| Apples expire immediately | `days = [1,...]` | Expire on same day grown |
| Single day production | `apples = [5], days = [10]` | Eat over multiple days |
| All apples expire same day | Multiple batches | Heap handles ordering |

---

### 🔄 Problems Encountered & Solutions

#### Problem 1: When to Stop Loop?
**Issue:** Initially only checked `day < n`, missing apples that last beyond production period.

**Solution:** Changed to `day < n || !heap.isEmpty()` to process all remaining apples.

---

#### Problem 2: Handling Multiple Batches
**Issue:** Confused about how to track multiple apple batches with different expiration dates.

**Solution:** Min-heap automatically maintains order by expiration, solving tracking problem.

---

#### Problem 3: Zero Apple Days
**Issue:** Adding empty batches to heap caused errors.

**Solution:** Added check `apples[day] > 0` before adding to heap.

---

### 🎓 Pattern Recognition

**This problem teaches:**
- **Greedy + Heap pattern** for optimization with constraints
- **Expiration/deadline management** using priority queues
- **Time-based simulation** with efficient data structures

**Similar problems:**
- Meeting Rooms II (intervals with priority queue)
- Task Scheduler (cooling period constraints)
- Process Tasks Using Servers (server assignment)


**Emerging Skills:**
- Choosing optimal data structures for time constraints
- Recognizing greedy algorithm opportunities
- Combining data structures for complex problems

---

### 🎯 Tomorrow's Focus
- Master min-heap vs max-heap decision making
- Explore bucket sort optimization technique
- Practice more HashMap + Heap combinations

---

### 💭 Questions to Explore
1. When is bucket sort better than heap-based solutions?
2. How to decide between min-heap and max-heap?
3. What other problems combine HashMap with Priority Queue?

---

**Last Updated:** January 23, 2026