# Solution Explanation & Problem-Solving Notes

## Solution Breakdown

| Metric | Value |
|--------|-------|
| Time Complexity | O(log n) |
| Space Complexity | O(log n) - recursion stack |
| Approach | Modified Binary Search |
| Difficulty | Medium |
| Time Taken | ~25 minutes |

---

## Core Intuition

**The Key Insight:** In a rotated sorted array, after finding the middle, **at least one half is always sorted**.

```
Example: [4,5,6,7,0,1,2]
         Left half [4,5,6,7] - sorted
         Right half [0,1,2] - sorted
```

Once we identify the sorted half, we can check if target lies in that range using standard binary search logic.

---

## Algorithm Breakdown

### Step 1: Base Cases

```java
int mid = si + (ei-si)/2;
if(arr[mid] == target) {
    return mid;
}
if(si >= ei) {
    return -1;
}
```

**First check:** Did we find the target at mid?
**Second check:** Have we exhausted the search space?

### Step 2: Identify Sorted Half

```java
if(arr[si] <= arr[mid])
```

**This condition determines which half is sorted:**

- If `arr[si] <= arr[mid]`: **Left half is sorted**
  - Elements from si to mid are in ascending order
  - Example: `[4,5,6,7]` in `[4,5,6,7,0,1,2]`

- Otherwise: **Right half is sorted**
  - Elements from mid to ei are in ascending order
  - Example: `[0,1,2]` in `[7,0,1,2,4,5,6]`

### Step 3: Check if Target in Sorted Half

**Case 1: Left half is sorted**
```java
if(arr[si] <= target && arr[mid] >= target) {
    return RotatedSortedArray(arr, target, si, mid-1);
}
```
If target is within sorted left range, search left.

**Case 2: Right half is sorted**
```java
if(arr[mid] <= target && arr[ei] >= target) {
    return RotatedSortedArray(arr, target, mid+1, ei);
}
```
If target is within sorted right range, search right.

### Step 4: Search Other Half

If target not in sorted half, it must be in the unsorted (rotated) half.

---

## Visual Walkthrough

### Example: `nums = [4,5,6,7,0,1,2]`, `target = 0`

```
Initial: si=0, ei=6, target=0
Array: [4, 5, 6, 7, 0, 1, 2]
        si      mid      ei

Step 1: mid = 3, arr[3] = 7
- arr[3] != 0, continue
- si < ei, continue

Step 2: Check which half sorted
- arr[0]=4 <= arr[3]=7 → Left sorted

Step 3: Is target in left sorted range?
- Is 4 <= 0 <= 7? No
- Search right half: si=4, ei=6

───────────────────────────────

Recursion 2: si=4, ei=6
Array: [4, 5, 6, 7, 0, 1, 2]
                    si mid ei

Step 1: mid = 5, arr[5] = 1
- arr[5] != 0, continue

Step 2: Check which half sorted
- arr[4]=0 > arr[5]=1? No
- arr[4]=0 <= arr[5]=1 → Left sorted

Step 3: Is target in left sorted range?
- Is 0 <= 0 <= 1? Yes!
- Search left: si=4, ei=4

───────────────────────────────

Recursion 3: si=4, ei=4
Array: [4, 5, 6, 7, 0, 1, 2]
                    si/ei/mid

Step 1: mid = 4, arr[4] = 0
- arr[4] == 0 → Found!
- Return 4
```

---

## Code Flow Diagram

```
search(nums, target)
    ↓
RotatedSortedArray(arr, target, 0, n-1)
    ↓
Found mid, check if target
    ↓
Determine sorted half
    ↓
    ├─ Left sorted?
    │    ↓
    │    ├─ Target in left range?
    │    │    → Search left (si, mid-1)
    │    └─ Not in left range?
    │         → Search right (mid+1, ei)
    │
    └─ Right sorted?
         ↓
         ├─ Target in right range?
         │    → Search right (mid+1, ei)
         └─ Not in right range?
              → Search left (si, mid-1)
```

---

## Key Decisions

### Why `arr[si] <= arr[mid]`?

This identifies the sorted half:
- If left side increasing: left is sorted
- Otherwise: right is sorted

### Why `si >= ei` as base case?

When `si > ei`: search space exhausted
When `si == ei`: only one element left, check it

### Why check target range?

Standard binary search:
- Only search where target could exist
- If target not in sorted range, must be in rotated range

---

## Alternative Approach: Iterative

```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while(left <= right) {
        int mid = left + (right - left) / 2;

        if(nums[mid] == target) return mid;

        if(nums[left] <= nums[mid]) {
            if(nums[left] <= target && target < nums[mid])
                right = mid - 1;
            else
                left = mid + 1;
        } else {
            if(nums[mid] < target && target <= nums[right])
                left = mid + 1;
            else
                right = mid - 1;
        }
    }
    return -1;
}
```

**Comparison:**
- Iterative: O(1) space
- Recursive: O(log n) space
- Both: O(log n) time

---

## Edge Cases Handled

| Case | Example | Handled? |
|------|---------|----------|
| Single element | `[1]`, target=1 | Yes - base case |
| No rotation | `[1,2,3,4]`, target=3 | Yes - works like normal BS |
| Full rotation | Same as original | Yes |
| Target not found | `[4,5,6,7,0,1,2]`, target=3 | Yes - returns -1 |
| Target at boundaries | First or last element | Yes |

---

## Complexity Analysis

```
Time Complexity: O(log n)
- Each recursion eliminates half the array
- Maximum log n recursive calls
- Standard binary search complexity

Space Complexity: O(log n)
- Recursion stack depth = log n
- Each call uses constant space
- Can be optimized to O(1) with iteration

Best Case: O(1) - Target at first mid
Worst Case: O(log n) - Search entire depth
```

---

## What Went Right

- Correct identification of sorted half
- Proper range checking for target
- Clean recursive structure
- Handles all edge cases
- O(log n) time achieved

---

## Key Takeaways

**Core Concepts:**
1. Modified binary search for rotated arrays
2. One half always sorted after rotation
3. Use sorted half to determine search direction
4. Recursive solution elegant and clear

**Pattern Recognition:**
- Binary search on modified sorted arrays
- Two-part logic: identify sorted, check range
- Recursion vs iteration trade-offs

---

## Related Problems

- Find Minimum in Rotated Sorted Array
- Search in Rotated Sorted Array II (with duplicates)
- Find Peak Element
- Search a 2D Matrix II

---

## Summary

**Problem Solved Successfully**

Time: ~25 minutes
Attempts: 1
Difficulty: Medium

**Key Achievement:** Clean recursive binary search solution maintaining O(log n) complexity

---

**Last Updated:** January 16, 2026