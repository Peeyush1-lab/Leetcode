# Predict the Winner - Study Notes

## 1. Why This Approach (Minimax with Recursion)

### The Core Problem
Two players play optimally, taking turns choosing from array ends. Determine if Player 1 wins.

**Key Insight**: This is a **minimax problem**. Each player:
- Maximizes their own score
- Minimizes opponent's score
- Plays optimally (always makes best move)

### The Relative Score Concept
Instead of tracking both players' scores separately, we track the **difference**:
- `score = player1Score - player2Score`
- Player 1 wins if `score >= 0`
- Each player tries to maximize their relative advantage

### Why Recursion Works
At each state `[start, end]`:
- **Current player** chooses left or right
- Gets points for chosen number
- **Opponent** plays optimally on remaining array
- Return: `myPoints - opponentAdvantage`

### The Minimax Formula
```
helper(start, end) = max score difference current player can achieve

If player picks start:
  score = nums[start] - helper(start+1, end)

If player picks end:
  score = nums[end] - helper(start, end-1)

Return max of both options
```

**Why negative?**
- Current player gets `+nums[start]`
- Opponent becomes current player in next call
- Opponent's advantage is subtracted from our score

### Base Case
When `start == end`:
- Only one number left
- Current player takes it
- No opponent move
- Return `nums[start]`

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Greedy (Always Pick Larger) ❌
```java
// Always pick the larger of two ends
public boolean greedy(int[] nums) {
    int p1 = 0, p2 = 0;
    int left = 0, right = nums.length - 1;
    boolean p1Turn = true;

    while (left <= right) {
        if (nums[left] > nums[right]) {
            if (p1Turn) p1 += nums[left];
            else p2 += nums[left];
            left++;
        } else {
            if (p1Turn) p1 += nums[right];
            else p2 += nums[right];
            right--;
        }
        p1Turn = !p1Turn;
    }

    return p1 >= p2;
}
```
**Problem**: Doesn't consider future implications
**Counter-example**: `[1, 5, 2]`
- Greedy: P1 picks 2, P2 picks 5, P1 picks 1 → P1=3, P2=5 ❌
- Optimal: P1 picks 1, P2 picks 2, P1 picks 5 → P1=6, P2=2 ✓

### Approach 2: Track Both Scores Separately ✓ (Works but Redundant)
```java
public boolean twoScores(int[] nums) {
    return helper(nums, 0, nums.length - 1, true)[0] >= helper(...)[1];
    // Returns [p1Score, p2Score]
}
```
**Works**: Correctly simulates game
**Why less ideal**: More complex, same time complexity

### Approach 3: Brute Force All Combinations ❌ (Too Slow)
```java
// Try all possible game sequences
public boolean bruteForce(int[] nums) {
    return tryAllMoves(nums, 0, nums.length - 1, 0, 0, true);
}
```
**Problem**: Explores redundant paths, doesn't memoize
**Why fails**: Exponential without memoization

### Approach 4: Dynamic Programming Bottom-Up ✓ (Optimal)
```java
public boolean dpSolution(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n];

    // Base case: single element
    for (int i = 0; i < n; i++) {
        dp[i][i] = nums[i];
    }

    // Fill table
    for (int len = 2; len <= n; len++) {
        for (int i = 0; i <= n - len; i++) {
            int j = i + len - 1;
            dp[i][j] = Math.max(
                nums[i] - dp[i+1][j],
                nums[j] - dp[i][j-1]
            );
        }
    }

    return dp[0][n-1] >= 0;
}
```
**Works**: O(n²) time and space, no recursion overhead
**Why better**: Iterative, easier to optimize

### Why Minimax Recursion is Clear
- ✅ **Intuitive**: Mirrors game flow
- ✅ **Clean code**: Simple recursion
- ✅ **Easy to memoize**: Add HashMap
- ✅ **Correct**: Handles optimal play

## 3. How to Handle Changes

### Change 1: Add Memoization
**Modification**: Cache results to avoid recomputation

```java
class Solution {
    private Integer[][] memo;

    public boolean predictTheWinner(int[] nums) {
        memo = new Integer[nums.length][nums.length];
        return helper(nums, 0, nums.length - 1) >= 0;
    }

    public int helper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        if (memo[start][end] != null) {
            return memo[start][end];
        }

        int pickStart = nums[start] - helper(nums, start + 1, end);
        int pickEnd = nums[end] - helper(nums, start, end - 1);

        memo[start][end] = Math.max(pickStart, pickEnd);
        return memo[start][end];
    }
}
```

### Change 2: Track Actual Choices Made
**Modification**: Return the optimal path

```java
public List<Integer> getOptimalMoves(int[] nums) {
    List<Integer> moves = new ArrayList<>();
    helper(nums, 0, nums.length - 1, moves, true);
    return moves;
}

private int helper(int[] nums, int start, int end, List<Integer> moves, boolean p1) {
    if (start == end) {
        moves.add(nums[start]);
        return nums[start];
    }

    int pickStart = nums[start] - helper(nums, start + 1, end, new ArrayList<>(), !p1);
    int pickEnd = nums[end] - helper(nums, start, end - 1, new ArrayList<>(), !p1);

    if (pickStart > pickEnd) {
        moves.add(nums[start]);
        helper(nums, start + 1, end, moves, !p1);
        return pickStart;
    } else {
        moves.add(nums[end]);
        helper(nums, start, end - 1, moves, !p1);
        return pickEnd;
    }
}
```

### Change 3: Three Players
**Modification**: Rotate through 3 players

```java
public boolean threePlayerGame(int[] nums) {
    // Player 1 wins if their score is highest
    return helper(nums, 0, nums.length - 1, 0) >= 0;
}

private int helper(int[] nums, int start, int end, int player) {
    if (start == end) {
        return player == 0 ? nums[start] : -nums[start];
    }

    if (player == 0) {
        int pickStart = nums[start] + helper(nums, start + 1, end, 1);
        int pickEnd = nums[end] + helper(nums, start, end - 1, 1);
        return Math.max(pickStart, pickEnd);
    } else {
        // Opponent tries to minimize P1's score
        int pickStart = -nums[start] + helper(nums, start + 1, end, (player + 1) % 3);
        int pickEnd = -nums[end] + helper(nums, start, end - 1, (player + 1) % 3);
        return Math.min(pickStart, pickEnd);
    }
}
```

### Change 4: Can Pick From Middle
**Modification**: Pick any element, not just ends

```java
public boolean canPickAnywhere(int[] nums) {
    return helper(nums, new boolean[nums.length]) >= 0;
}

private int helper(int[] nums, boolean[] used) {
    boolean allUsed = true;
    for (boolean u : used) {
        if (!u) allUsed = false;
    }
    if (allUsed) return 0;

    int maxDiff = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {
        if (!used[i]) {
            used[i] = true;
            int diff = nums[i] - helper(nums, used);
            maxDiff = Math.max(maxDiff, diff);
            used[i] = false;
        }
    }

    return maxDiff;
}
```

### Change 5: Return Minimum Turns to Win
**Modification**: Find minimum moves for P1 to guarantee win

```java
public int minTurnsToWin(int[] nums) {
    return helper(nums, 0, nums.length - 1, 0);
}

private int helper(int[] nums, int start, int end, int turns) {
    if (start > end) {
        return turns;
    }

    // Try both moves and see which wins faster
    int pickStart = helper(nums, start + 1, end, turns + 1);
    int pickEnd = helper(nums, start, end - 1, turns + 1);

    return Math.min(pickStart, pickEnd);
}
```

## Visual Walkthrough

**Input**: `nums = [1,5,2]`

```
Initial call: helper(0, 2)

Level 1 (P1's turn):
  Option 1: Pick nums[0]=1
    P1 gets: 1
    Opponent plays: helper(1, 2)

      Level 2 (P2's turn, on [5,2]):
        Option A: Pick nums[1]=5
          P2 gets: 5
          P1 plays: helper(2, 2) = 2
          P2's advantage: 5 - 2 = 3

        Option B: Pick nums[2]=2
          P2 gets: 2
          P1 plays: helper(1, 1) = 5
          P2's advantage: 2 - 5 = -3

        P2 chooses max: 3

    P1's score from picking 1: 1 - 3 = -2

  Option 2: Pick nums[2]=2
    P1 gets: 2
    Opponent plays: helper(0, 1)

      Level 2 (P2's turn, on [1,5]):
        Option A: Pick nums[0]=1
          P2's advantage: 1 - 5 = -4

        Option B: Pick nums[1]=5
          P2's advantage: 5 - 1 = 4

        P2 chooses max: 4

    P1's score from picking 2: 2 - 4 = -2

P1's best: max(-2, -2) = -2

Since -2 < 0, Player 1 cannot win
Return false ✓
```

## Key Patterns

**Pattern 1: Minimax Game Theory**
- Each player maximizes their own advantage
- Recursive structure with alternating players

**Pattern 2: Relative Score**
- Track difference instead of absolute scores
- Simplifies win condition check

**Pattern 3: Optimal Substructure**
- Optimal play on `[start, end]` depends on optimal play on subproblems
- Perfect for DP/recursion

**Pattern 4: Memoization Opportunity**
- Many overlapping subproblems
- Cache `dp[start][end]` for efficiency

## Complexity Analysis

**Without Memoization:**
- **Time**: O(2^n) - each call branches twice
- **Space**: O(n) - recursion stack depth

**With Memoization:**
- **Time**: O(n²) - at most n² unique subproblems
- **Space**: O(n²) - memo table + O(n) recursion stack

## Common Mistakes

1. **Forgetting the negative sign**: `nums[i] - helper(...)` not `nums[i] + helper(...)`
2. **Wrong base case**: Returning 0 instead of `nums[start]`
3. **Not considering both options**: Only checking one end
4. **Greedy approach**: Assuming larger number is always better
5. **Off-by-one**: Wrong indices in recursive calls

## Interview Tips

1. **Start with game simulation**: Explain how players alternate
2. **Introduce relative score**: Easier than tracking both scores
3. **Explain minimax**: Current player maximizes, sees opponent as minimizing
4. **Draw recursion tree**: Visual helps understanding
5. **Mention memoization**: Shows optimization thinking
6. **Discuss DP alternative**: Bottom-up approach