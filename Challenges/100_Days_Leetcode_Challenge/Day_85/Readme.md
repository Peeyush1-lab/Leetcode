# 1705. Maximum Number of Eaten Apples

**Difficulty:** Medium
**Day:** 85
**Topics:** Array, Greedy, Heap (Priority Queue)

## Problem Description

There is a special kind of apple tree that grows apples every day for `n` days. On the `ith` day, the tree grows `apples[i]` apples that will rot after `days[i]` days, meaning on day `i + days[i]` the apples will be rotten and cannot be eaten. On some days, the apple tree does not grow any apples, which are denoted by `apples[i] == 0` and `days[i] == 0`.

You decided to eat **at most** one apple a day (to keep the doctors away). Note that you can keep eating after the first `n` days.

Return the **maximum number of apples** you can eat.

## Examples

### Example 1:

**Input:**
```
apples = [1,2,3,5,2], days = [3,2,1,4,2]
```

**Output:**
```
7
```

**Explanation:**
```
You can eat 7 apples:
- On the first day, you eat an apple that grew on the first day.
- On the second day, you eat an apple that grew on the second day.
- On the third day, you eat an apple that grew on the second day. After this day, the apples that grew on the third day rot.
- On the fourth to the seventh days, you eat apples that grew on the fourth day.
```

### Example 2:

**Input:**
```
apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
```

**Output:**
```
5
```

**Explanation:**
```
You can eat 5 apples:
- On the first to the third day you eat apples that grew on the first day.
- Do nothing on the fourth and fifth days.
- On the sixth and seventh days you eat apples that grew on the sixth day.
```

## Constraints

- `n == apples.length == days.length`
- `1 <= n <= 2 * 10⁴`
- `0 <= apples[i], days[i] <= 2 * 10⁴`
- `days[i] = 0` if and only if `apples[i] = 0`.

## Topics

- Array
- Greedy
- Heap (Priority Queue)

## Similar Problems

- [630. Course Schedule III](https://leetcode.com/problems/course-schedule-iii/) - Hard
- [1353. Maximum Number of Events That Can Be Attended](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/) - Medium
- [1834. Single-Threaded CPU](https://leetcode.com/problems/single-threaded-cpu/) - Medium
- [2402. Meeting Rooms III](https://leetcode.com/problems/meeting-rooms-iii/) - Hard
- [2462. Total Cost to Hire K Workers](https://leetcode.com/problems/total-cost-to-hire-k-workers/) - Medium

## Related Topics Problems

**Greedy + Heap:**
- [253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Medium
- [355. Design Twitter](https://leetcode.com/problems/design-twitter/) - Medium
- [373. Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) - Medium
- [767. Reorganize String](https://leetcode.com/problems/reorganize-string/) - Medium

**Priority Queue Applications:**
- [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) - Medium
- [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Medium
- [703. Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/) - Easy
- [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) - Medium

---

|**Previous:** [Day 84](../Day_84/) | **Next:** [Day 86](../Day_86/)|
|---|---|

|**Date:** March 27, 2026|
|---|