# Trapping Rain Water - Study Notes

## 1. Why This Approach (Two Pointers)

### The Core Problem
Water trapped at position `i` depends on:
- **Left max**: Tallest bar to the left of `i`
- **Right max**: Tallest bar to the right of `i`
- **Water at i** = `min(leftMax, rightMax) - height[i]`

The limiting factor is the **shorter wall** (like a container's capacity is limited by its shortest side).

### Why Two Pointers?
The **optimal two-pointer approach** exploits this insight:
- Move from **both ends** toward center
- At each step, we know **for certain** which side determines water level
- If `height[left] < height[right]`, left side is the **bottleneck**
  - Water at `left` only depends on `leftMax` (right side is definitely taller)
- If `height[right] < height[left]`, right side is the **bottleneck**
  - Water at `right` only depends on `rightMax` (left side is definitely taller)

### Key Insight
**We don't need to know the exact maximum on the other side!** We just need to know that the other side is **taller than current side**. This allows us to compute water in a single pass.

### Example Visualization
```
height = [0,1,0,2,1,0,1,3,2,1,2,1]

Initial: left=0, right=11
         leftMax=0, rightMax=0

Step 1: height[0]=0 < height[11]=1
  Process left side
  leftMax = 0, water = 0
  left → 1

Step 2: height[1]=1 = height[11]=1
  Process left side (arbitrary choice when equal)
  leftMax = 1, water = 0
  left → 2

Step 3: height[2]=0 < height[11]=1
  leftMax = 1, water = 1-0 = 1
  left → 3

... continues until pointers meet
```

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Brute Force ❌ (Too Slow)
```java
for (int i = 0; i < n; i++) {
    int leftMax = 0, rightMax = 0;

    // Find max on left
    for (int j = 0; j <= i; j++) {
        leftMax = Math.max(leftMax, height[j]);
    }

    // Find max on right
    for (int j = i; j < n; j++) {
        rightMax = Math.max(rightMax, height[j]);
    }

    water += Math.min(leftMax, rightMax) - height[i];
}
```
**Problem**: O(n²) time - recalculates max for each position
**Why it fails**: Too slow for `n = 20,000`

### Approach 2: Precompute Arrays ✓ (Works but Uses Extra Space)
```java
int[] leftMax = new int[n];
int[] rightMax = new int[n];

// Fill leftMax array
leftMax[0] = height[0];
for (int i = 1; i < n; i++) {
    leftMax[i] = Math.max(leftMax[i-1], height[i]);
}

// Fill rightMax array
rightMax[n-1] = height[n-1];
for (int i = n-2; i >= 0; i--) {
    rightMax[i] = Math.max(rightMax[i+1], height[i]);
}

// Calculate water
for (int i = 0; i < n; i++) {
    water += Math.min(leftMax[i], rightMax[i]) - height[i];
}
```
**Works**: O(n) time, but O(n) space
**Why less ideal**: Uses extra memory when we can do it in O(1) space

### Approach 3: Stack-Based ✓ (Works but More Complex)
```java
Stack<Integer> stack = new Stack<>();
int water = 0;

for (int i = 0; i < height.length; i++) {
    while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
        int top = stack.pop();
        if (stack.isEmpty()) break;

        int distance = i - stack.peek() - 1;
        int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
        water += distance * boundedHeight;
    }
    stack.push(i);
}
```
**Works**: O(n) time, O(n) space
**Why less ideal**: More complex logic, harder to understand, uses stack space

### Approach 4: Greedy from Highest Point ❌ (Incorrect)
```java
int maxIdx = 0;
for (int i = 1; i < n; i++) {
    if (height[i] > height[maxIdx]) maxIdx = i;
}

// Process left of max, then right of max
```
**Problem**: Doesn't correctly calculate water between peaks
**Why it fails**: Oversimplifies the problem structure

### Why Two Pointers Wins
- ✅ **O(n) time** - single pass
- ✅ **O(1) space** - only 4 variables
- ✅ **Intuitive** - move from both ends
- ✅ **Interview-friendly** - shows optimization thinking
- ✅ **No extra structures** - just pointers and max values

## 3. How to Handle Changes

### Change 1: Return Positions Where Water Trapped
**Modification**: Store positions instead of just sum

```java
List<Integer> waterPositions = new ArrayList<>();

while (left < right) {
    if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
            leftMax = height[left];
        } else {
            waterPositions.add(left);  // Water trapped here
            totalWater += leftMax - height[left];
        }
        left++;
    } else {
        if (height[right] >= rightMax) {
            rightMax = height[right];
        } else {
            waterPositions.add(right);  // Water trapped here
            totalWater += rightMax - height[right];
        }
        right--;
    }
}

return waterPositions;  // List of indices with water
```

### Change 2: Calculate Maximum Water at Any Single Position
**Modification**: Track maximum water at one position

```java
int maxWaterAtSinglePosition = 0;

while (left < right) {
    if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
            leftMax = height[left];
        } else {
            int waterHere = leftMax - height[left];
            maxWaterAtSinglePosition = Math.max(maxWaterAtSinglePosition, waterHere);
            totalWater += waterHere;
        }
        left++;
    } else {
        // Similar for right side
    }
}
```

### Change 3: Water Can Overflow (Maximum Capacity)
**Modification**: Add capacity constraint

```java
int capacity = 100;  // Maximum water that can be trapped

while (left < right && totalWater < capacity) {
    if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
            leftMax = height[left];
        } else {
            int waterToAdd = Math.min(leftMax - height[left], capacity - totalWater);
            totalWater += waterToAdd;
        }
        left++;
    } else {
        // Similar for right
    }
}
```

### Change 4: Different Width Bars
**Modification**: Each bar has different width

```java
int[] height = {...};
int[] width = {...};  // Width of each bar

while (left < right) {
    if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
            leftMax = height[left];
        } else {
            totalWater += (leftMax - height[left]) * width[left];  // Multiply by width
        }
        left++;
    } else {
        // Similar for right with width[right]
    }
}
```

### Change 5: 2D Version (Trapping Rain Water II)
**Completely different approach needed**: Priority Queue (Heap)

```java
// This becomes a different problem - need heap-based approach
// Can't use simple two pointers for 2D
PriorityQueue<Cell> pq = new PriorityQueue<>();
// BFS-like traversal from borders inward
```

## Visual Step-by-Step

**Input**: `height = [0,1,0,2,1,0,1,3,2,1,2,1]`

```
Visual representation:
        3
    2   █ 2
  1 █ 1 █ █ 1 2 1
0 1 0 2 1 0 1 3 2 1 2 1

Water trapped (marked as ~):
        3
    2   █ 2
  1 █~█~█ █~█~2~1
0 1 0 2 1 0 1 3 2 1 2 1

Total: 6 units
```

**Pointer movements**:
```
left=0, right=11: height[0]=0 < height[11]=1
  → Process left, leftMax=0, water=0, left→1

left=1, right=11: height[1]=1 = height[11]=1
  → Process left, leftMax=1, water=0, left→2

left=2, right=11: height[2]=0 < height[11]=1
  → leftMax=1 > height[2]=0, water+=1, left→3

left=3, right=11: height[3]=2 > height[11]=1
  → Process right, rightMax=1, water+=0, right→10

left=3, right=10: height[3]=2 = height[10]=2
  → Process left, leftMax=2, water+=0, left→4

left=4, right=10: height[4]=1 < height[10]=2
  → leftMax=2 > height[4]=1, water+=1, left→5

left=5, right=10: height[5]=0 < height[10]=2
  → leftMax=2 > height[5]=0, water+=2, left→6

left=6, right=10: height[6]=1 < height[10]=2
  → leftMax=2 > height[6]=1, water+=1, left→7

left=7, right=10: height[7]=3 > height[10]=2
  → Process right, rightMax=2, water+=0, right→9

left=7, right=9: height[7]=3 > height[9]=1
  → Process right, rightMax=2 > height[9]=1, water+=1, right→8

left=7, right=8: height[7]=3 > height[8]=2
  → Process right, rightMax=2, water+=0, right→7

left=7, right=7: STOP (left >= right)

Total water: 1 + 1 + 2 + 1 + 1 = 6 ✓
```

## Key Patterns

**Pattern 1: Two-Pointer Convergence**
- Start from both ends
- Move pointers based on comparison
- Process smaller side first

**Pattern 2: Greedy Choice**
- Always process the side with smaller height
- This side determines water level (bottleneck)

**Pattern 3: Running Maximum**
- Track `leftMax` and `rightMax` as we go
- Update max before calculating water

**Pattern 4: Conditional Water Addition**
- If current height ≥ max: update max (no water)
- If current height < max: add water (max - current)

## Complexity Analysis

- **Time**: O(n) - single pass through array
- **Space**: O(1) - only 4 integer variables

Compare to alternatives:
- Brute force: O(n²) time, O(1) space
- Precompute arrays: O(n) time, O(n) space
- Stack: O(n) time, O(n) space

## Common Mistakes

1. **Not handling edge cases**: Arrays with length < 3
2. **Wrong comparison**: Using `<=` instead of `<` for pointer movement
3. **Forgetting to update max**: Only calculating water, not updating max
4. **Off-by-one errors**: Loop condition `left < right` vs `left <= right`
5. **Integer overflow**: For very large inputs (use `long` if needed)

## Interview Tips

1. **Start with brute force**: Explain O(n²) approach first
2. **Optimize to DP**: Show O(n) space solution with arrays
3. **Optimize to O(1)**: Explain two-pointer optimization
4. **Draw it out**: Visual diagram helps understanding
5. **Explain bottleneck**: Why smaller side determines water level
6. **Test edge cases**: Empty array, single element, no water trapped