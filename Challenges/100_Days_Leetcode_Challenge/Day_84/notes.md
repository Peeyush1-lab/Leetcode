# Continuous Subarrays - Study Notes

## 1. Why This Approach (Sliding Window + Monotonic Deques)

### The Core Problem
A subarray is **continuous** if the difference between **any two elements** ≤ 2.

Equivalently: `max(subarray) - min(subarray) ≤ 2`

**Why?** The maximum difference in a subarray is between its max and min elements.

### Why Sliding Window?
- We need to find **all valid subarrays**
- Sliding window efficiently explores all subarrays in O(n)
- Expand right, shrink left when invalid

### Why Monotonic Deques?
We need to track **max and min** in current window efficiently:

**Problem with naive approach:**
```java
// O(n) per window to find max/min
int max = Integer.MIN_VALUE;
int min = Integer.MAX_VALUE;
for (int i = left; i <= right; i++) {
    max = Math.max(max, nums[i]);
    min = Math.min(min, nums[i]);
}
```
This makes total complexity O(n²) ❌

**Solution: Monotonic Deques**
- **maxDeque**: Maintains indices in decreasing order of values
  - Front = maximum element in window
- **minDeque**: Maintains indices in increasing order of values
  - Front = minimum element in window
- Both support O(1) peek and O(1) amortized add/remove

### How Monotonic Deques Work

**maxDeque (decreasing order):**
```
nums = [5, 4, 6, 2]

After adding 5: maxDeque = [0] (index 0, value 5)
After adding 4: maxDeque = [0, 1] (values: 5, 4 - decreasing)
After adding 6: maxDeque = [2] (remove 5,4 as they're smaller, add 6)
After adding 2: maxDeque = [2, 3] (values: 6, 2 - decreasing)

Front always has max!
```

**minDeque (increasing order):**
```
nums = [5, 4, 6, 2]

After adding 5: minDeque = [0]
After adding 4: minDeque = [1] (remove 5, keep 4)
After adding 6: minDeque = [1, 2] (values: 4, 6 - increasing)
After adding 2: minDeque = [3] (remove 4,6, add 2)

Front always has min!
```

### Counting Valid Subarrays
For each position `right`, count subarrays ending at `right`:
- If window `[left, right]` is valid
- All subarrays ending at `right` starting from any index ∈ `[left, right]` are valid
- Count: `right - left + 1`

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Brute Force ❌ (Too Slow)
```java
long count = 0;
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            max = Math.max(max, nums[k]);
            min = Math.min(min, nums[k]);
        }
        if (max - min <= 2) count++;
    }
}
```
**Problem**: O(n³) time - check every subarray with O(n) max/min
**Why fails**: Times out for n = 10⁵

### Approach 2: Optimized Brute Force ❌ (Still Too Slow)
```java
long count = 0;
for (int i = 0; i < n; i++) {
    int max = nums[i], min = nums[i];
    for (int j = i; j < n; j++) {
        max = Math.max(max, nums[j]);
        min = Math.min(min, nums[j]);
        if (max - min <= 2) count++;
        else break;  // No point extending further
    }
}
```
**Problem**: O(n²) time - still too slow
**Why fails**: n = 10⁵ gives 10¹⁰ operations

### Approach 3: TreeMap/PriorityQueue ✓ (Works but Slower)
```java
TreeMap<Integer, Integer> map = new TreeMap<>();
int left = 0;
long count = 0;

for (int right = 0; right < n; right++) {
    map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

    while (map.lastKey() - map.firstKey() > 2) {
        map.put(nums[left], map.get(nums[left]) - 1);
        if (map.get(nums[left]) == 0) map.remove(nums[left]);
        left++;
    }

    count += right - left + 1;
}
```
**Works**: O(n log n) time
**Why less ideal**: TreeMap operations are O(log n), deques are O(1) amortized

### Approach 4: Segment Tree ❌ (Overkill)
```java
// Build segment tree for range min/max queries
```
**Problem**: Complex implementation, O(n log n) time
**Why overkill**: Monotonic deques solve it more elegantly

### Why Sliding Window + Monotonic Deques Wins
- ✅ **O(n) time** - each element added/removed once
- ✅ **O(n) space** - deques store indices
- ✅ **Optimal** - linear time is best possible
- ✅ **Elegant** - clean pattern once understood

## 3. How to Handle Changes

### Change 1: Different Difference Threshold
**Modification**: Max difference ≤ k instead of 2

```java
public long continuousSubarrays(int[] nums, int k) {
    // ... same setup ...

    while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
        // Same shrinking logic
    }

    // ... rest same ...
}
```

### Change 2: Find Longest Continuous Subarray
**Modification**: Return max length instead of count

```java
public int longestContinuousSubarray(int[] nums) {
    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();

    int left = 0;
    int maxLength = 0;

    for (int right = 0; right < nums.length; right++) {
        // Maintain deques (same as before)

        // Shrink window (same as before)

        // Track maximum length
        maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
}
```

### Change 3: Count Subarrays with Sum Constraint
**Modification**: Different validity condition

```java
public long subarraysWithSumLimit(int[] nums, int limit) {
    int left = 0;
    long count = 0;
    long windowSum = 0;

    for (int right = 0; right < nums.length; right++) {
        windowSum += nums[right];

        while (windowSum > limit) {
            windowSum -= nums[left];
            left++;
        }

        count += right - left + 1;
    }

    return count;
}
```

### Change 4: Return All Valid Subarrays
**Modification**: Store subarrays instead of counting

```java
public List<List<Integer>> getAllContinuousSubarrays(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();

    int left = 0;

    for (int right = 0; right < nums.length; right++) {
        // Maintain deques

        // Shrink window

        // Add all subarrays ending at right
        for (int start = left; start <= right; start++) {
            List<Integer> subarray = new ArrayList<>();
            for (int i = start; i <= right; i++) {
                subarray.add(nums[i]);
            }
            result.add(subarray);
        }
    }

    return result;
}
```

### Change 5: Only Count Subarrays of Certain Length
**Modification**: Length constraint

```java
public long continuousSubarraysOfLengthK(int[] nums, int k) {
    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();

    long count = 0;

    for (int right = 0; right < nums.length; right++) {
        // Maintain deques

        // Remove elements outside window of size k
        if (right >= k) {
            int left = right - k;
            if (!maxDeque.isEmpty() && maxDeque.peekFirst() <= left) {
                maxDeque.pollFirst();
            }
            if (!minDeque.isEmpty() && minDeque.peekFirst() <= left) {
                minDeque.pollFirst();
            }
        }

        // Check if window of size k is valid
        if (right >= k - 1) {
            if (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] <= 2) {
                count++;
            }
        }
    }

    return count;
}
```

## Visual Walkthrough

**Input**: `nums = [5,4,2,4]`

```
Initial: left=0, count=0

right=0, nums[0]=5:
  maxDeque = [0], minDeque = [0]
  max=5, min=5, diff=0 ≤ 2 ✓
  count += (0-0+1) = 1
  Subarrays: [5]

right=1, nums[1]=4:
  maxDeque = [0,1] (values: 5,4)
  minDeque = [1] (removed 5, added 4)
  max=5, min=4, diff=1 ≤ 2 ✓
  count += (1-0+1) = 3
  Subarrays: [5,4], [4]

right=2, nums[2]=2:
  maxDeque = [0,1,2] (values: 5,4,2)
  minDeque = [2] (removed 4, added 2)
  max=5, min=2, diff=3 > 2 ❌

  Shrink: left=0 → 1
    Remove index 0 from maxDeque: [1,2]
    max=4, min=2, diff=2 ≤ 2 ✓

  count += (2-1+1) = 5
  Subarrays: [4,2], [2]

right=3, nums[3]=4:
  maxDeque = [3] (removed 1,2, added 3)
  minDeque = [2,3] (values: 2,4)
  max=4, min=2, diff=2 ≤ 2 ✓
  count += (3-1+1) = 8
  Subarrays: [4,2,4], [2,4], [4]

Total: 8 ✓
```

## Key Patterns

**Pattern 1: Two Monotonic Deques**
- One for max (decreasing), one for min (increasing)
- Both store indices, not values

**Pattern 2: Deque Maintenance**
- Remove from back while violating monotonic property
- Add new element to back
- Remove from front when outside window

**Pattern 3: Window Validation**
- Check: `max - min ≤ threshold`
- Shrink from left until valid

**Pattern 4: Counting Formula**
- For each `right`, count = `right - left + 1`
- This counts all subarrays ending at `right`

## Complexity Analysis

- **Time**: O(n)
  - Each element added once, removed once from both deques
  - Amortized O(1) per element

- **Space**: O(n)
  - Deques can hold up to n indices worst case

## Common Mistakes

1. **Storing values instead of indices** in deques
2. **Wrong monotonic property**: Forgetting which deque is increasing/decreasing
3. **Not removing from front**: When left pointer moves past deque front
4. **Forgetting to check both deques**: When shrinking window
5. **Integer overflow**: Using `int` instead of `long` for count

## Interview Tips

1. **Start with constraint**: "max - min ≤ 2" is the key
2. **Explain sliding window**: Why it works for this problem
3. **Draw deque evolution**: Show how they maintain max/min
4. **Discuss alternatives**: TreeMap works but slower
5. **Mention amortized O(1)**: Each element processed once