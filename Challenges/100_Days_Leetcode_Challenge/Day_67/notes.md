# Number of Students Unable to Eat Lunch - Study Notes

## Approach: Counting Optimization

### Key Insight

Instead of simulating the queue, we can use a **counting approach**:
- Count how many students want type 0 and type 1
- Process sandwiches from top of stack
- When we encounter a sandwich type with no students wanting it, stop
- Remaining students cannot eat

**Why this works**: Order in the queue doesn't matter! Students can rotate indefinitely until someone takes the top sandwich or no one wants it.

## Algorithm

1. Count students preferring type 0 and type 1
2. For each sandwich (from top to bottom):
   - If no students want this type → return remaining students
   - Otherwise, decrement count for this type
3. If all sandwiches taken → return 0

## Visual Example

**Input**: students = [1,1,0,0], sandwiches = [0,1,0,1]

```
Count: count0=2, count1=2

Sandwich 0:
  count0 > 0 → take it
  count0 = 1, count1 = 2

Sandwich 1:
  count1 > 0 → take it
  count0 = 1, count1 = 1

Sandwich 0:
  count0 > 0 → take it
  count0 = 0, count1 = 1

Sandwich 1:
  count1 > 0 → take it
  count0 = 0, count1 = 0

All students ate → return 0
```

## Complexity

- **Time**: O(n) - count students + process sandwiches
- **Space**: O(1) - only two counters

## Edge Cases

1. All same preference: students=[0,0], sandwiches=[1,1] → 2
2. All can eat: students=[0,1], sandwiches=[0,1] → 0
3. Single student: students=[0], sandwiches=[0] → 0