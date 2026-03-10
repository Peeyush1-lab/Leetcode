# Time Needed to Buy Tickets - Study Notes

## Approach: Mathematical Calculation

### Key Insight

Instead of simulating the queue, we can **calculate directly** how much time each person contributes:

**For person at position k to finish:**
- People **before or at k** (i ≤ k): Can buy up to `tickets[k]` tickets
- People **after k** (i > k): Can buy up to `tickets[k] - 1` tickets (person k finishes before they get another turn)

## Algorithm

1. Get target = `tickets[k]` (tickets person k needs)
2. For each person i:
   - If i ≤ k: Add `min(tickets[i], target)` to time
   - If i > k: Add `min(tickets[i], target - 1)` to time
3. Return total time

## Visual Example

**Input**: tickets = [2,3,2], k = 2

```
Target = tickets[2] = 2

Position 0 (i ≤ k):
  min(tickets[0], target) = min(2, 2) = 2
  Person 0 buys 2 tickets

Position 1 (i ≤ k):
  min(tickets[1], target) = min(3, 2) = 2
  Person 1 buys only 2 tickets (target finishes first)

Position 2 (i = k):
  min(tickets[2], target) = min(2, 2) = 2
  Person at k buys all 2 tickets

Total: 2 + 2 + 2 = 6 seconds
```

**Input**: tickets = [5,1,1,1], k = 0

```
Target = tickets[0] = 5

Position 0 (i = k):
  min(5, 5) = 5
  Person 0 buys all 5 tickets

Position 1 (i > k):
  min(1, 4) = 1
  Person 1 buys 1 ticket (can only buy target-1)

Position 2 (i > k):
  min(1, 4) = 1

Position 3 (i > k):
  min(1, 4) = 1

Total: 5 + 1 + 1 + 1 = 8 seconds
```

## Why i > k gets (target - 1)?

People after position k get **one less chance** because:
- Person k finishes and leaves the line
- They don't get another turn after person k is done

## Complexity

- **Time**: O(n) - single pass through array
- **Space**: O(1) - only a few variables

## Edge Cases

1. k at position 0: tickets = [5,1,1], k = 0 → 8
2. k at last position: tickets = [1,1,5], k = 2 → 9
3. Person k needs only 1: tickets = [3,2,1], k = 2 → 3
4. Everyone needs same: tickets = [2,2,2], k = 1 → 6