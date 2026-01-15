# Notes: Contains Duplicate II

## Problem Analysis

This problem extends the basic "contains duplicate" problem by adding a **distance constraint**. We need to find duplicates that are "close enough" to each other.

### What We're Looking For
- Two indices `i` and `j` where:
  1. `nums[i] == nums[j]` (same value)
  2. `i != j` (different positions)
  3. `abs(i - j) <= k` (within distance k)

## Algorithm Breakdown

### Data Structure Choice: HashMap
```java
HashMap<Integer, Integer> check = new HashMap<>();
```
- **Key**: The number from the array
- **Value**: The most recent index where this number appeared
- Why HashMap? O(1) lookup to check if we've seen a number before

### Step-by-Step Process

**For each element at index i:**

1. **Check if we've seen this number before**
   ```java
   if(check.containsKey(nums[i]))
   ```

2. **If yes, calculate the distance**
   ```java
   if((i - check.get(nums[i]) <= k))
   ```
   - Current index: `i`
   - Previous index: `check.get(nums[i])`
   - Distance: `i - check.get(nums[i])`

3. **If distance is within k, we found our answer**
   ```java
   return true;
   ```

4. **Otherwise, update the index for this number**
   ```java
   check.put(nums[i], i);
   ```
   - This overwrites the old index with the current one
   - We only need the most recent occurrence

5. **If loop completes without finding duplicates**
   ```java
   return false;
   ```

## Example Walkthrough

### Example: nums = [1,2,3,1,2,3], k = 2

```
i=0: nums[0]=1
  HashMap: {1: 0}

i=1: nums[1]=2
  HashMap: {1: 0, 2: 1}

i=2: nums[2]=3
  HashMap: {1: 0, 2: 1, 3: 2}

i=3: nums[3]=1
  Found 1 in HashMap at index 0
  Distance: 3 - 0 = 3
  Is 3 <= 2? NO
  Update: HashMap: {1: 3, 2: 1, 3: 2}

i=4: nums[4]=2
  Found 2 in HashMap at index 1
  Distance: 4 - 1 = 3
  Is 3 <= 2? NO
  Update: HashMap: {1: 3, 2: 4, 3: 2}

i=5: nums[5]=3
  Found 3 in HashMap at index 2
  Distance: 5 - 2 = 3
  Is 3 <= 2? NO
  Update: HashMap: {1: 3, 2: 4, 3: 5}

Result: false (no duplicates within distance 2)
```

### Example: nums = [1,0,1,1], k = 1

```
i=0: nums[0]=1
  HashMap: {1: 0}

i=1: nums[1]=0
  HashMap: {1: 0, 0: 1}

i=2: nums[2]=1
  Found 1 in HashMap at index 0
  Distance: 2 - 0 = 2
  Is 2 <= 1? NO
  Update: HashMap: {1: 2, 0: 1}

i=3: nums[3]=1
  Found 1 in HashMap at index 2
  Distance: 3 - 2 = 1
  Is 1 <= 1? YES
  Return: true
```

## Why We Update the Index

```java
check.put(nums[i], i);
```

This line executes whether we found a duplicate or not. Here's why:

1. **If no duplicate found yet**: Store the index for future comparisons
2. **If duplicate found but distance > k**: Update to the more recent index
   - The newer index gives us a better chance of finding a closer duplicate
   - Example: [1, 5, 1, 1] with k=1
     - At i=2, distance is too far from i=0
     - But updating allows us to check i=2 against i=3

## Key Observations

### Why Only Store One Index?
We don't need to track all previous occurrences, just the most recent one because:
- If current index i has a duplicate at some earlier index j
- And the distance i - j > k
- Then any index before j will have an even larger distance
- So we only care about the closest previous occurrence

### Time Complexity Analysis
- Single pass through array: O(n)
- HashMap operations (get, put, containsKey): O(1) average
- Total: O(n)

### Space Complexity Analysis
- In worst case, all elements are unique: O(n)
- But if k is small, we could optimize further by removing old entries
- Current implementation: O(min(n, k)) practically

## Common Mistakes to Avoid

1. **Not updating the index after checking**
   - Must update even if distance is too large
   - Helps with future comparisons

2. **Using absolute value unnecessarily**
   - Since we're always comparing i with a previous index
   - `i - check.get(nums[i])` is always positive
   - No need for `Math.abs()`

3. **Forgetting the edge case k = 0**
   - If k = 0, we need duplicates at the same index (impossible)
   - The algorithm handles this correctly (always returns false)

## Optimization Idea (Not Necessary Here)

For very large arrays with small k, you could use a sliding window approach with a HashSet:
- Maintain a window of size k
- Remove elements that fall outside the window
- Space complexity strictly O(k)

However, for this problem, the current HashMap solution is optimal and cleaner.

## Related Problems
- 217. Contains Duplicate (no distance constraint)
- 220. Contains Duplicate III (value difference constraint added)