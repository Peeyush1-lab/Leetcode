# Subsets - Study Notes

## 1. Why This Approach (Backtracking/Recursion)

### The Problem Nature
We need to generate **all possible subsets** from an array. For `[1,2,3]`, we need:
- `[]` (empty set)
- `[1]`, `[2]`, `[3]` (single elements)
- `[1,2]`, `[1,3]`, `[2,3]` (pairs)
- `[1,2,3]` (all elements)

### Why Backtracking?
For each element, we have **two choices**:
1. **Include it** in the current subset
2. **Exclude it** from the current subset

This creates a **decision tree** structure, perfect for recursion/backtracking:
```
                    []
           /                \
       [1]                   []
      /    \                /    \
   [1,2]   [1]           [2]      []
   /  \    /  \         /  \      /  \
[1,2,3][1,2][1,3][1] [2,3][2]  [3]  []
```

**Backtracking** naturally explores all paths in this decision tree.

### Why Not Iterative?
- Iterative approaches exist but are **less intuitive**
- Would need to manually manage state (like keeping track of which elements to add)
- Backtracking elegantly handles the "try and undo" pattern

### Core Insight
Every subset is formed by making a **binary choice** for each element:
- Position 0: include nums[0]? (yes/no)
- Position 1: include nums[1]? (yes/no)
- Position 2: include nums[2]? (yes/no)

For n elements → 2^n possible subsets

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Nested Loops ❌
```java
// Doesn't work - can't nest n loops for unknown n
for (int i...) {
    for (int j...) {
        for (int k...) {  // How many loops? Don't know n!
```
**Problem**: Array size is dynamic, can't hardcode loop depth

### Approach 2: Single Pass ❌
```java
for (int i = 0; i < nums.length; i++) {
    result.add(nums[i]);  // Only gets individual elements
}
```
**Problem**: Can't generate combinations like `[1,2]` or `[1,3]`

### Approach 3: Iterative Building ✓ (Alternative That Works)
```java
List<List<Integer>> result = new ArrayList<>();
result.add(new ArrayList<>());  // Start with empty set

for (int num : nums) {
    int size = result.size();
    for (int i = 0; i < size; i++) {
        List<Integer> subset = new ArrayList<>(result.get(i));
        subset.add(num);
        result.add(subset);
    }
}
```
**Works but**: Less intuitive, harder to understand the logic

### Approach 4: Bit Manipulation ✓ (Alternative)
```java
for (int i = 0; i < (1 << n); i++) {  // 2^n iterations
    List<Integer> subset = new ArrayList<>();
    for (int j = 0; j < n; j++) {
        if ((i & (1 << j)) > 0) {
            subset.add(nums[j]);
        }
    }
    result.add(subset);
}
```
**Works but**: Less readable, harder to explain in interviews

### Why Backtracking Wins
- ✅ **Natural fit** for the problem structure
- ✅ **Easy to explain** (include/exclude decision)
- ✅ **Easy to extend** (works for similar problems)
- ✅ **Interview-friendly** (shows understanding of recursion)

## 3. How to Handle Changes

### Change 1: Array Contains Duplicates
**Problem**: `nums = [1,2,2]` should give `[[],[1],[2],[1,2],[2,2],[1,2,2]]`

**Solution**: Sort array, skip duplicates at same level
```java
Arrays.sort(nums);  // Sort first

void helper(int i, ...) {
    if (i == nums.length) {
        res.add(new ArrayList<>(arr));
        return;
    }

    // Include current
    arr.add(nums[i]);
    helper(i + 1, nums, res, arr);
    arr.remove(arr.size() - 1);

    // Skip duplicates when excluding
    while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
        i++;
    }

    // Exclude current (and all duplicates)
    helper(i + 1, nums, res, arr);
}
```

### Change 2: Only Subsets of Size K
**Problem**: Find only subsets of specific length

**Solution**: Add size constraint
```java
void helper(int i, int k, ...) {
    if (arr.size() == k) {  // Only add when size is k
        res.add(new ArrayList<>(arr));
        return;
    }
    if (i == nums.length) return;  // Stop if no more elements

    // Include
    arr.add(nums[i]);
    helper(i + 1, k, nums, res, arr);
    arr.remove(arr.size() - 1);

    // Exclude
    helper(i + 1, k, nums, res, arr);
}
```

### Change 3: Subsets Must Sum to Target
**Problem**: Only include subsets that sum to a target value

**Solution**: Track sum, check at base case
```java
void helper(int i, int currentSum, int target, ...) {
    if (i == nums.length) {
        if (currentSum == target) {  // Only add if sum matches
            res.add(new ArrayList<>(arr));
        }
        return;
    }

    // Include
    arr.add(nums[i]);
    helper(i + 1, currentSum + nums[i], target, nums, res, arr);
    arr.remove(arr.size() - 1);

    // Exclude
    helper(i + 1, currentSum, target, nums, res, arr);
}
```

### Change 4: Maximum/Minimum Constraints
**Problem**: Only subsets with at least 2 elements

**Solution**: Add condition in base case
```java
void helper(int i, ...) {
    if (i == nums.length) {
        if (arr.size() >= 2) {  // Size constraint
            res.add(new ArrayList<>(arr));
        }
        return;
    }
    // ... rest same
}
```

### Change 5: Different Data Structure
**Problem**: Return array instead of list, or return as strings

**Solution**: Convert at the end
```java
// For array
int[][] result = new int[res.size()][];
for (int i = 0; i < res.size(); i++) {
    List<Integer> subset = res.get(i);
    result[i] = subset.stream().mapToInt(Integer::intValue).toArray();
}

// For strings
List<String> result = new ArrayList<>();
for (List<Integer> subset : res) {
    result.add(subset.toString());
}
```

## Visual Walkthrough

**Input**: `nums = [1,2,3]`

```
Start: i=0, arr=[]

Decision at i=0 (element 1):
  → Include 1: arr=[1]
      Decision at i=1 (element 2):
        → Include 2: arr=[1,2]
            Decision at i=2 (element 3):
              → Include 3: arr=[1,2,3] → Add [1,2,3] ✓
              → Exclude 3: arr=[1,2] → Add [1,2] ✓
        → Exclude 2: arr=[1]
            Decision at i=2 (element 3):
              → Include 3: arr=[1,3] → Add [1,3] ✓
              → Exclude 3: arr=[1] → Add [1] ✓

  → Exclude 1: arr=[]
      Decision at i=1 (element 2):
        → Include 2: arr=[2]
            Decision at i=2 (element 3):
              → Include 3: arr=[2,3] → Add [2,3] ✓
              → Exclude 3: arr=[2] → Add [2] ✓
        → Exclude 2: arr=[]
            Decision at i=2 (element 3):
              → Include 3: arr=[3] → Add [3] ✓
              → Exclude 3: arr=[] → Add [] ✓

Result: [[1,2,3],[1,2],[1,3],[1],[2,3],[2],[3],[]]
```

## Key Patterns

**Pattern 1: Include/Exclude Decision**
- Add element → recurse → remove element (backtrack)
- Skip element → recurse

**Pattern 2: Base Case**
- When index reaches array length, we've made decisions for all elements
- Current `arr` represents one valid subset

**Pattern 3: Backtracking**
- After exploring "include" path, **undo** by removing element
- This restores state before exploring "exclude" path

**Pattern 4: State Tracking**
- `i`: current position in array
- `arr`: current subset being built
- `res`: final result storing all subsets

## Complexity Analysis

- **Time**: O(n × 2^n)
  - 2^n subsets to generate
  - Each subset takes O(n) to copy into result

- **Space**: O(n)
  - Recursion depth: O(n)
  - Temporary array `arr`: O(n)
  - Result storage not counted in space complexity

## Common Mistakes

1. **Forgetting to backtrack**: Not removing element after "include" path
2. **Not creating new list**: Adding reference instead of `new ArrayList<>(arr)`
3. **Wrong base case**: Checking `i >= nums.length` instead of `i == nums.length`
4. **Modifying result**: Changing `arr` after adding to `res`

## Interview Tips

1. **Start with example**: Draw the decision tree for `[1,2,3]`
2. **Explain choices**: "For each element, we either include or exclude it"
3. **Walk through recursion**: Show how backtracking restores state
4. **Discuss complexity**: Mention 2^n total subsets
5. **Mention alternatives**: Bit manipulation, iterative building