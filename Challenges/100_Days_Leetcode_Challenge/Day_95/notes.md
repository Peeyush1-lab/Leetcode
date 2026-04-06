# Robot Return to Origin - Study Notes

## 1. Why This Approach (Coordinate Tracking)

### The Core Problem
Robot starts at (0, 0). After all moves, check if it's back at (0, 0).

### The Key Insight
**Displacement must be zero in both dimensions:**
- Horizontal (x-axis): R moves right (+1), L moves left (-1)
- Vertical (y-axis): U moves up (+1), D moves down (-1)
- Return to origin ⟺ x = 0 AND y = 0

### Why This Works
Each move changes position:
- `R`: x++
- `L`: x--
- `U`: y++
- `D`: y--

If equal moves in opposite directions → net displacement = 0

### Algorithm Flow
```
1. Initialize x=0, y=0 (origin)
2. For each move:
   - Update x or y based on direction
3. Check if x==0 && y==0
```

This is a **simulation** - directly model the physical process.

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Count Each Direction ✓ (Works, Equivalent)
```java
public boolean countDirections(String moves) {
    int up = 0, down = 0, left = 0, right = 0;

    for (char c : moves.toCharArray()) {
        if (c == 'U') up++;
        else if (c == 'D') down++;
        else if (c == 'L') left++;
        else if (c == 'R') right++;
    }

    return up == down && left == right;
}
```
**Works**: Functionally identical
**Why equivalent**: up-down = net y, left-right = net x

### Approach 2: Use HashMap ✓ (Works but Overkill)
```java
public boolean hashMapApproach(String moves) {
    Map<Character, Integer> count = new HashMap<>();
    count.put('U', 0);
    count.put('D', 0);
    count.put('L', 0);
    count.put('R', 0);

    for (char c : moves.toCharArray()) {
        count.put(c, count.get(c) + 1);
    }

    return count.get('U').equals(count.get('D')) &&
           count.get('L').equals(count.get('R'));
}
```
**Works**: But unnecessary data structure
**Why overkill**: Simple counters suffice

### Approach 3: Track All Positions ❌ (Unnecessary)
```java
Set<String> visited = new HashSet<>();
int x = 0, y = 0;

for (char c : moves.toCharArray()) {
    // Update position...
    visited.add(x + "," + y);
}

// Only need final position, not path!
```
**Problem**: Tracks entire path when only end matters
**Why wasteful**: O(n) space, unnecessary

### Approach 4: Complex Math ❌ (Overcomplicating)
```java
// Try to use vector math, dot products, etc.
```
**Problem**: Simple problem doesn't need complex math
**Why overkill**: Coordinate tracking is clearer

### Why Coordinate Tracking Wins
- ✅ **O(n) time** - single pass
- ✅ **O(1) space** - only two variables
- ✅ **Clear and simple** - models physical reality
- ✅ **Easy to extend** - can track path, distance, etc.

## 3. How to Handle Changes

### Change 1: Return Final Position
**Modification**: Return coordinates instead of boolean

```java
public int[] finalPosition(String moves) {
    int x = 0, y = 0;

    for (char c : moves.toCharArray()) {
        if (c == 'R') x++;
        else if (c == 'L') x--;
        else if (c == 'U') y++;
        else if (c == 'D') y--;
    }

    return new int[]{x, y};
}
```

### Change 2: Return Distance from Origin
**Modification**: Manhattan or Euclidean distance

```java
public int manhattanDistance(String moves) {
    int x = 0, y = 0;

    for (char c : moves.toCharArray()) {
        if (c == 'R') x++;
        else if (c == 'L') x--;
        else if (c == 'U') y++;
        else if (c == 'D') y--;
    }

    return Math.abs(x) + Math.abs(y);
}

public double euclideanDistance(String moves) {
    int x = 0, y = 0;

    for (char c : moves.toCharArray()) {
        if (c == 'R') x++;
        else if (c == 'L') x--;
        else if (c == 'U') y++;
        else if (c == 'D') y--;
    }

    return Math.sqrt(x * x + y * y);
}
```

### Change 3: Track Maximum Distance
**Modification**: Find farthest point from origin during journey

```java
public int maxDistanceFromOrigin(String moves) {
    int x = 0, y = 0;
    int maxDist = 0;

    for (char c : moves.toCharArray()) {
        if (c == 'R') x++;
        else if (c == 'L') x--;
        else if (c == 'U') y++;
        else if (c == 'D') y--;

        int dist = Math.abs(x) + Math.abs(y);
        maxDist = Math.max(maxDist, dist);
    }

    return maxDist;
}
```

### Change 4: Check If Bounded in Circle
**Modification**: Robot repeats pattern endlessly, stays bounded?

```java
public boolean isCircleBounded(String moves) {
    int x = 0, y = 0;
    int dirX = 0, dirY = 1;  // Facing north initially

    for (char c : moves.toCharArray()) {
        if (c == 'G') {  // Go forward
            x += dirX;
            y += dirY;
        } else if (c == 'L') {  // Turn left
            int temp = dirX;
            dirX = -dirY;
            dirY = temp;
        } else if (c == 'R') {  // Turn right
            int temp = dirX;
            dirX = dirY;
            dirY = -temp;
        }
    }

    // Bounded if back at origin OR facing different direction
    return (x == 0 && y == 0) || (dirX != 0 || dirY != 1);
}
```

### Change 5: Count Moves to Return
**Modification**: Minimum additional moves to return to origin

```java
public int minMovesToReturn(String moves) {
    int x = 0, y = 0;

    for (char c : moves.toCharArray()) {
        if (c == 'R') x++;
        else if (c == 'L') x--;
        else if (c == 'U') y++;
        else if (c == 'D') y--;
    }

    // Need |x| horizontal moves + |y| vertical moves
    return Math.abs(x) + Math.abs(y);
}
```

## Visual Walkthrough

**Input**: `moves = "UD"`

```
Start: (0, 0)

Move 1: 'U' (Up)
  y++ → y = 1
  Position: (0, 1)
      ↑
  (0,1)
      |
  (0,0)

Move 2: 'D' (Down)
  y-- → y = 0
  Position: (0, 0)
  (0,1)
      |
      ↓
  (0,0) ← Back at origin!

Final: x=0, y=0
Return: true ✓
```

**Input**: `moves = "LL"`

```
Start: (0, 0)

Move 1: 'L' (Left)
  x-- → x = -1
  Position: (-1, 0)

  (-1,0) ← (0,0)

Move 2: 'L' (Left)
  x-- → x = -2
  Position: (-2, 0)

  (-2,0) ← (-1,0) ← (0,0)

Final: x=-2, y=0
Return: false ✗ (not at origin)
```

**Input**: `moves = "UDLR"`

```
Start: (0, 0)

U: (0, 0) → (0, 1)
D: (0, 1) → (0, 0)
L: (0, 0) → (-1, 0)
R: (-1, 0) → (0, 0)

Path:
    (0,1)
      |
(-1,0)—(0,0)

Final: (0, 0)
Return: true ✓
```

## Key Patterns

**Pattern 1: Coordinate System**
- 2D plane: x-axis (horizontal), y-axis (vertical)
- Origin at (0, 0)

**Pattern 2: Displacement Tracking**
- Running sum of movements
- Opposite moves cancel out

**Pattern 3: Simulation**
- Model real-world process step by step
- Simple state tracking

**Pattern 4: Net Effect**
- Only final position matters
- Path doesn't matter (for this problem)

## Complexity Analysis

- **Time**: O(n)
  - Single pass through string
  - Each character processed once

- **Space**: O(1)
  - Only two variables (x, y)
  - No extra data structures

## Common Mistakes

1. **Wrong coordinate updates**: Mixing up x/y or +/-
2. **Only checking one dimension**: Forgetting `x==0 && y==0`
3. **Using == instead of equals**: If using Integer objects
4. **Not handling all cases**: Missing a direction in if-else
5. **Overcomplicating**: Using complex data structures unnecessarily

## Interview Tips

1. **Clarify coordinates**: Confirm x is horizontal, y is vertical
2. **Explain simulation**: Walk through a small example
3. **Mention simplicity**: Why simple counters work
4. **Discuss alternatives**: Counting directions (equivalent approach)
5. **Consider extensions**: Distance, path tracking, bounded circle
6. **Edge cases**: Single move, all same direction, zigzag pattern

## Alternative Solution: Counting

```java
public boolean judgeCircleAlternative(String moves) {
    int horizontal = 0;  // R = +1, L = -1
    int vertical = 0;    // U = +1, D = -1

    for (char c : moves.toCharArray()) {
        switch (c) {
            case 'R': horizontal++; break;
            case 'L': horizontal--; break;
            case 'U': vertical++; break;
            case 'D': vertical--; break;
        }
    }

    return horizontal == 0 && vertical == 0;
}
```

Both approaches are equivalent and optimal!