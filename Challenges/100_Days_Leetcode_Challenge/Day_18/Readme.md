# Day 18: Minimum Number of Arrows to Burst Balloons

## Problem Statement

There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array `points` where `points[i] = [xstart, xend]` denotes a balloon whose **horizontal diameter** stretches between `xstart` and `xend`. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up **directly vertically** (in the positive y-direction) from different points along the x-axis. A balloon with `xstart` and `xend` is **burst** by an arrow shot at `x` if `xstart <= x <= xend`. There is **no limit** to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array `points`, return the **minimum** number of arrows that must be shot to burst all balloons.

## Examples

### Example 1
**Input:** `points = [[10,16],[2,8],[1,6],[7,12]]`
**Output:** `2`
**Explanation:** The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting balloons [2,8] and [1,6]
- Shoot an arrow at x = 11, bursting balloons [10,16] and [7,12]

### Example 2
**Input:** `points = [[1,2],[3,4],[5,6],[7,8]]`
**Output:** `4`
**Explanation:** One arrow needs to be shot for each balloon for a total of 4 arrows.

### Example 3
**Input:** `points = [[1,2],[2,3],[3,4],[4,5]]`
**Output:** `2`
**Explanation:** The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting balloons [1,2] and [2,3]
- Shoot an arrow at x = 4, bursting balloons [3,4] and [4,5]

## Constraints

- `1 <= points.length <= 10^5`
- `points[i].length == 2`
- `-2^31 <= xstart < xend <= 2^31 - 1`

## Key Points

- Greedy approach works optimally
- Sort by end points
- Track overlapping intervals
- One arrow per non-overlapping group

## Approach

**Strategy:** Greedy with Sorting

1. Sort balloons by end position
2. Shoot arrow at first balloon's end
3. This arrow bursts all overlapping balloons
4. When no overlap, need new arrow

**Complexity:**
- Time: O(n log n) - Sorting
- Space: O(1) or O(log n) - Sorting space