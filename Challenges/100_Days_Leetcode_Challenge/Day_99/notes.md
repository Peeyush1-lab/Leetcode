# Climbing Stairs - Study Notes

## 1. Why This Approach (Dynamic Programming - Fibonacci Pattern)

### The Core Problem
Count distinct ways to climb `n` stairs when you can take 1 or 2 steps at a time.

### The Key Insight
To reach step `n`, you must come from either:
- Step `n-1` (take 1 step)
- Step `n-2` (take 2 steps)

Therefore: **`ways(n) = ways(n-1) + ways(n-2)`**

This is the **Fibonacci sequence**!

### Why This Works

**Base cases:**
- `n = 1`: Only one way (1 step)
- `n = 2`: Two ways (1+1 or 2)

**Recurrence relation:**
```
dp[1] = 1
dp[2] = 2
dp[i] = dp[i-1] + dp[i-2]  for i > 2
```

**Example for n=5:**
```
Step 5 can be reached from:
- Step 4 (all ways to reach 4, then +1 step)
- Step 3 (all ways to reach 3, then +2 steps)

So: ways(5) = ways(4) + ways(3)
```

### The DP Array Approach
Build solution bottom-up:
1. Initialize base cases
2. For each step i from 3 to n:
   - Calculate ways using previous two values
3. Return `dp[n]`

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Recursion (No Memoization) ❌ (Too Slow)
```java
public int recursion(int n) {
    if (n <= 2) return n;
    return recursion(n - 1) + recursion(n - 2);
}
```
**Problem**: O(2^n) time - exponential!
**Why fails**: Recalculates same values many times
**Example**: For n=5, calculates ways(3) twice, ways(2) three times, etc.

### Approach 2: Recursion with Memoization ✓ (Works, O(n))
```java
public int memoization(int n) {
    Integer[] memo = new Integer[n + 1];
    return helper(n, memo);
}

private int helper(int n, Integer[] memo) {
    if (n <= 2) return n;
    if (memo[n] != null) return memo[n];

    memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
    return memo[n];
}
```
**Works**: O(n) time, O(n) space
**Why less ideal**: Recursion overhead, same space as DP array

### Approach 3: Space-Optimized DP ✓ (Best!)
```java
public int spaceOptimized(int n) {
    if (n <= 2) return n;

    int prev2 = 1;  // dp[i-2]
    int prev1 = 2;  // dp[i-1]

    for (int i = 3; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }

    return prev1;
}
```
**Best**: O(n) time, O(1) space!
**Why ideal**: Same time, minimal space

### Approach 4: Math Formula (Fibonacci) ✓ (O(log n) but Complex)
```java
public int fibonacci(int n) {
    double phi = (1 + Math.sqrt(5)) / 2;
    double psi = (1 - Math.sqrt(5)) / 2;
    return (int) Math.round((Math.pow(phi, n + 1) - Math.pow(psi, n + 1)) / Math.sqrt(5));
}
```
**Works**: O(log n) for exponentiation
**Why less ideal**: Floating point precision issues, harder to understand

### Why DP Array is Good Choice
- ✅ **O(n) time** - efficient
- ✅ **O(n) space** - acceptable
- ✅ **Simple to understand** - clear pattern
- ✅ **No precision issues** - integer arithmetic
- ✅ **Easy to extend** - can modify for variations

## 3. How to Handle Changes

### Change 1: Space Optimized to O(1)
**Modification**: Only keep last two values

```java
public int climbStairsOptimized(int n) {
    if (n <= 2) return n;

    int prev2 = 1, prev1 = 2;

    for (int i = 3; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }

    return prev1;
}
```

### Change 2: Can Take 1, 2, or 3 Steps
**Modification**: Three choices at each step

```java
public int climbStairsThreeSteps(int n) {
    if (n <= 2) return n;
    if (n == 3) return 4;  // 111, 12, 21, 3

    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;

    for (int i = 4; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    }

    return dp[n];
}
```

### Change 3: Return All Distinct Paths
**Modification**: Generate actual sequences

```java
public List<List<Integer>> getAllPaths(int n) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    backtrack(n, path, result);
    return result;
}

private void backtrack(int n, List<Integer> path, List<List<Integer>> result) {
    if (n == 0) {
        result.add(new ArrayList<>(path));
        return;
    }

    if (n >= 1) {
        path.add(1);
        backtrack(n - 1, path, result);
        path.remove(path.size() - 1);
    }

    if (n >= 2) {
        path.add(2);
        backtrack(n - 2, path, result);
        path.remove(path.size() - 1);
    }
}
```

### Change 4: Min Cost Climbing Stairs
**Modification**: Each step has a cost

```java
public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;
    int[] dp = new int[n + 1];

    dp[0] = 0;
    dp[1] = 0;  // Can start from either 0 or 1

    for (int i = 2; i <= n; i++) {
        dp[i] = Math.min(
            dp[i - 1] + cost[i - 1],
            dp[i - 2] + cost[i - 2]
        );
    }

    return dp[n];
}
```

### Change 5: With Obstacles
**Modification**: Some steps are blocked

```java
public int climbStairsWithObstacles(int n, Set<Integer> obstacles) {
    if (obstacles.contains(n)) return 0;

    int[] dp = new int[n + 1];
    dp[0] = 1;

    for (int i = 1; i <= n; i++) {
        if (obstacles.contains(i)) {
            dp[i] = 0;
        } else {
            dp[i] = (i >= 1 ? dp[i - 1] : 0) +
                    (i >= 2 ? dp[i - 2] : 0);
        }
    }

    return dp[n];
}
```

## Visual Walkthrough

**Input**: `n = 5`

```
Step-by-step DP array construction:

dp[1] = 1
  Ways: [1]

dp[2] = 2
  Ways: [1,1], [2]

dp[3] = dp[2] + dp[1] = 2 + 1 = 3
  Ways: [1,1,1], [1,2], [2,1]

dp[4] = dp[3] + dp[2] = 3 + 2 = 5
  Ways: [1,1,1,1], [1,1,2], [1,2,1], [2,1,1], [2,2]

dp[5] = dp[4] + dp[3] = 5 + 3 = 8
  Ways:
    From dp[4] + [1]: [1,1,1,1,1], [1,1,2,1], [1,2,1,1], [2,1,1,1], [2,2,1]
    From dp[3] + [2]: [1,1,2], [1,2,2], [2,1,2]
  Total: 8 ways

Answer: 8
```

**Pattern Recognition:**
```
n:    1  2  3  4  5  6  7   8   9  ...
ways: 1  2  3  5  8  13 21  34  55 ...
      ↑  ↑  ↑  ↑  ↑
      This is Fibonacci sequence!
      (with offset: fib(1)=1, fib(2)=2)
```

## Key Patterns

**Pattern 1: Optimal Substructure**
- Solution to n depends on solutions to n-1 and n-2
- Perfect for dynamic programming

**Pattern 2: Fibonacci Relation**
- Same recurrence as Fibonacci
- Can use any Fibonacci technique

**Pattern 3: Bottom-Up Building**
- Start from base cases
- Build up to final answer

**Pattern 4: Choice at Each Step**
- At step i, can come from i-1 or i-2
- Total ways = sum of ways from both

## Complexity Analysis

**DP Array Approach:**
- **Time**: O(n) - single pass through array
- **Space**: O(n) - dp array

**Space-Optimized:**
- **Time**: O(n) - same iteration
- **Space**: O(1) - only two variables

**Naive Recursion:**
- **Time**: O(2^n) - exponential branching
- **Space**: O(n) - recursion stack

## Common Mistakes

1. **Wrong base cases**: Using dp[0]=0, dp[1]=1 instead of dp[1]=1, dp[2]=2
2. **Off-by-one**: Array size too small or wrong indexing
3. **Not handling n=1**: Forgetting edge case check
4. **Integer overflow**: For large n (though 45 is safe)
5. **Starting loop at wrong index**: Starting at i=2 instead of i=3

## Interview Tips

1. **Recognize Fibonacci**: Mention the pattern
2. **Start with recursion**: Show thought process
3. **Optimize to DP**: Explain memoization → DP array
4. **Space optimization**: Mention O(1) space solution
5. **Draw small example**: Show n=3 or n=4
6. **Discuss variations**: Cost, obstacles, more steps

## Why This is a Classic DP Problem

**Teaching moment:**
- Simple to understand
- Clear recurrence relation
- Multiple solution approaches
- Easy to extend
- Foundation for harder DP problems

**Progression:**
1. See recursion (inefficient)
2. Add memoization (better)
3. Convert to DP (iterative)
4. Optimize space (best)

This problem is the "Hello World" of dynamic programming!