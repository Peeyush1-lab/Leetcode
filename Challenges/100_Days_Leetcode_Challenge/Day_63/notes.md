# Count Collisions on a Road - Study Notes

## Approach: Two Pointers + Observation

### Key Insight

The brilliant insight for this problem is: **Only cars that can potentially collide matter!**

Cars moving left (`L`) at the beginning will never collide - they just move off the road to the left.
Cars moving right (`R`) at the end will never collide - they just move off the road to the right.

**The collision zone** is everything between the first non-L car and the last non-R car. Within this zone, every moving car (R or L) will eventually collide and become stationary!

### Why This Works

Think about it logically:
1. **Leading 'L' cars**: They move left with nothing to stop them → No collisions
2. **Trailing 'R' cars**: They move right with nothing to stop them → No collisions
3. **Middle section**: Any 'R' or 'L' in the middle will eventually hit something:
   - 'R' will hit another 'R', 'S', or 'L' ahead
   - 'L' will hit an 'S' or another car that became 'S' behind it
   - All become stationary ('S') after collision

**Therefore**: Count all non-'S' cars in the collision zone!

## Algorithm Walkthrough

### Step-by-step Process

1. Find the first car that's not 'L' (start of collision zone)
2. Find the last car that's not 'R' (end of collision zone)
3. Count all 'R' and 'L' cars between these boundaries
4. These are exactly the cars that will collide

### Visual Example 1: "RLRSLL"

```
Original: R L R S L L
Index:    0 1 2 3 4 5

Step 1: Find first non-'L'
    i = 0 (R is not L, so i stops at 0)

Step 2: Find last non-'R' (from right)
    j starts at 5
    j = 5: 'L' (not R) → j stops at 5

Step 3: Count non-'S' between i and j
    Range [0, 5]: R L R S L L
    Non-'S' cars: R L R L L = 5 cars

Answer: 5 collisions
```

**What actually happens:**
```
R L R S L L
↓ Collide
S S R S L L  (R and L collide → both become S, +2)
    ↓ Collide
S S S S L L  (R hits S, becomes S, +1)
        ↓ Collide
S S S S S L  (L hits S, becomes S, +1)
          ↓ Collide
S S S S S S  (L hits S, becomes S, +1)

Total: 2 + 1 + 1 + 1 = 5
```

### Visual Example 2: "LLRR"

```
Original: L L R R
Index:    0 1 2 3

Step 1: Find first non-'L'
    i = 0: 'L' → continue
    i = 1: 'L' → continue
    i = 2: 'R' → stop
    i = 2

Step 2: Find last non-'R' (from right)
    j = 3: 'R' → continue
    j = 2: 'R' → continue
    j = 1: 'L' → stop
    j = 1

Step 3: Count collisions
    Range [2, 1]: Invalid! (i > j)

Answer: 0 collisions
```

The two L's escape left, the two R's escape right - no collision zone!

### Visual Example 3: "SSRSSRLLRSLLRSRR"

```
Original: S S R S S R L L R S L L R S R R
Index:    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15

Step 1: First non-'L'
    i = 0 ('S') → i = 0

Step 2: Last non-'R' (from right)
    j = 15: 'R' → continue
    j = 14: 'R' → continue
    j = 13: 'S' → stop
    j = 13

Step 3: Count non-'S' in range [0, 13]
    S S R S S R L L R S L L R S
    Non-'S': R R L L R L L R = 8 cars

Answer: 8 collisions
```

## Code Analysis

### The Two Pointers Logic

```java
// Skip all leading 'L' cars (they escape left)
while (i < n && directions.charAt(i) == 'L')
    i++;

// Skip all trailing 'R' cars (they escape right)
while (j >= 0 && directions.charAt(j) == 'R')
    j--;
```

**Important**: Missing closing brace in first while loop! Should be:
```java
while (i < n && directions.charAt(i) == 'L') {
    i++;
}
```

### Counting Collisions

```java
int collisions = 0;
for (int k = i; k <= j; k++) {
    if (directions.charAt(k) != 'S') collisions++;
}
```

We only count 'R' and 'L' because 'S' cars are already stationary - they don't "collide" in the sense of adding to the count (they just stop other cars).

## Complexity Analysis

### Time Complexity: O(n)
- First while loop: O(n) worst case
- Second while loop: O(n) worst case
- For loop: O(n) worst case
- **Total: O(n)** where n is length of string

### Space Complexity: O(1)
- Only using a few integer variables
- No extra data structures needed

## Edge Cases

### Case 1: All 'L's
```
Input: "LLLL"
i = 4 (end of string)
j = -1 (went past beginning)
Range [4, -1] is invalid
Output: 0
```

### Case 2: All 'R's
```
Input: "RRRR"
i = 0
j = -1 (all are R, so j goes to -1)
Range [0, -1] is invalid
Output: 0
```

### Case 3: All 'S's
```
Input: "SSSS"
i = 0
j = 3
Count non-'S' in [0, 3] = 0
Output: 0
```

### Case 4: Single collision
```
Input: "RL"
i = 0 (R)
j = 1 (L)
Count non-'S' in [0, 1] = 2 (both R and L)
Output: 2
```

### Case 5: Mixed with escaping cars
```
Input: "LLRSRR"
i = 2 (first non-L is 'R')
j = 3 (last non-R is 'S')
Count non-'S' in [2, 3] = 1 (just the 'R')
Output: 1
```

## Common Mistakes

1. **Trying to simulate the actual collisions**
   - This would be very complex with multiple passes
   - The observation-based approach is much simpler

2. **Not handling the boundary conditions**
   - What if i > j? (No collision zone exists)
   - What if all cars are the same direction?

3. **Counting 'S' cars in the collision count**
   - 'S' cars don't add to collision count, they're already stopped

4. **Missing the key insight**
   - Trying to track which specific cars collide with which
   - Just count: every moving car in the zone will collide!

## Alternative Approaches

### Approach 1: Stack-Based Simulation
```java
Stack<Character> stack = new Stack<>();
int collisions = 0;

for (char c : directions.toCharArray()) {
    if (c == 'L') {
        if (!stack.isEmpty() && stack.peek() == 'R') {
            stack.pop();
            collisions += 2;
            stack.push('S');
        } else if (!stack.isEmpty() && stack.peek() == 'S') {
            collisions += 1;
        }
    } else {
        stack.push(c);
    }
}
```
**Pros**: More intuitive simulation of collisions
**Cons**: More complex, O(n) space, harder to get right

### Approach 2: Multiple Pass Simulation
Keep simulating until no more changes occur.
**Pros**: Very intuitive
**Cons**: O(n²) time complexity or worse

## Interview Tips

1. **Start with examples**: Draw out what happens with "RLRSLL"

2. **Look for patterns**:
   - "Why don't leading L's collide?"
   - "Why don't trailing R's collide?"

3. **State the key insight clearly**:
   - "Everything between first non-L and last non-R will collide"
   - "We just need to count moving cars in that range"

4. **Explain why counting works**:
   - Each moving car contributes 1 or 2 to the collision count
   - Our method counts each moving car once
   - This equals the total collisions

5. **Handle edge cases**:
   - What if i > j? Return 0
   - What if string is empty? Return 0

## Key Takeaways

- **Look for patterns** instead of simulating complex processes
- **Boundary analysis** can simplify many problems
- **Two pointers** are great for identifying ranges
- Sometimes the **elegant solution** comes from observation, not simulation
- **Think about what doesn't matter** (leading L's, trailing R's)

## Practice Progression

After mastering this problem:
1. **Similar concept**: Asteroid Collision (735) - Stack-based collision
2. **Two pointers**: Container With Most Water (11)
3. **Observation**: Minimum Deletions to Make Character Frequencies Unique (1647)