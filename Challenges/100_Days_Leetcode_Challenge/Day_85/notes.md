# Maximum Number of Eaten Apples - Study Notes

## 1. Why This Approach (Greedy + Min Heap)

### The Core Problem
- Apples grow on day `i` and rot on day `i + days[i]`
- Can eat at most 1 apple per day
- Want to maximize total apples eaten

### The Greedy Strategy
**Always eat apples that will rot soonest!**

Why? If we have apples expiring on day 5 and day 10:
- Eating day-10 apple first → day-5 apples rot (waste!)
- Eating day-5 apple first → day-10 apples still available (optimal!)

### Why Min Heap (Priority Queue)?
We need to:
1. Track multiple apple batches with different expiration dates
2. Always access the batch expiring soonest
3. Efficiently add new batches as days progress

**Min Heap** gives us:
- O(1) peek at earliest expiration
- O(log n) insert new batch
- O(log n) remove expired/eaten batches

### Data Structure Choice
Store: `[expirationDay, numberOfApples]`
- Priority: Sort by expiration day (earliest first)
- This ensures we always eat apples closest to rotting

### Algorithm Flow
```
For each day:
1. Add today's apple batch (if any) to heap
2. Remove all rotten/empty batches
3. Eat 1 apple from batch with earliest expiration
4. Continue even after day n (can still eat remaining apples)
```

## 2. Why Alternate Approaches Won't Work (or Are Less Ideal)

### Approach 1: Always Eat Today's Apples ❌
```java
int eaten = 0;
for (int i = 0; i < n; i++) {
    if (apples[i] > 0) {
        eaten++;
    }
}
```
**Problem**: Ignores future days and doesn't eat optimally
**Example**: apples=[3,0,0], days=[3,0,0]
- Wrong approach: eats 1 apple, result = 1
- Correct: can eat 3 apples (days 0,1,2), result = 3

### Approach 2: Simulation with Array ❌ (Too Slow)
```java
List<int[]> allBatches = new ArrayList<>();
for each day:
    add new batch
    sort all batches by expiration
    remove expired
    eat from earliest
```
**Problem**: O(n²) time - sorting repeatedly
**Why fails**: n = 20,000 → too slow

### Approach 3: Greedy without Heap ❌ (Inefficient)
```java
for each day:
    find batch with earliest expiration from all active batches
    eat from it
```
**Problem**: O(n²) - linear search each day
**Why fails**: Can't efficiently find minimum expiration

### Approach 4: Eat All Apples Immediately ❌
```java
int eaten = 0;
for (int i = 0; i < n; i++) {
    eaten += Math.min(apples[i], days[i]);
}
```
**Problem**: Can only eat 1 apple per day!
**Why fails**: Violates problem constraint

### Why Greedy + Min Heap Wins
- ✅ **O(n log n) time** - optimal for this problem
- ✅ **Greedy is correct** - eating earliest expiration is provably optimal
- ✅ **Heap maintains invariant** - always access earliest expiration efficiently
- ✅ **Handles edge cases** - continues after day n, removes rotten apples

## 3. How to Handle Changes

### Change 1: Can Eat K Apples Per Day
**Modification**: Eat up to k apples daily

```java
public int eatenApples(int[] apples, int[] days, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    int day = 0, eaten = 0;

    while (day < apples.length || !pq.isEmpty()) {
        if (day < apples.length && apples[day] > 0) {
            pq.offer(new int[]{day + days[day], apples[day]});
        }

        while (!pq.isEmpty() && (pq.peek()[0] <= day || pq.peek()[1] == 0)) {
            pq.poll();
        }

        // Eat up to k apples
        int toEat = k;
        while (toEat > 0 && !pq.isEmpty()) {
            int[] batch = pq.poll();
            int eat = Math.min(toEat, batch[1]);
            eaten += eat;
            batch[1] -= eat;
            toEat -= eat;
            if (batch[1] > 0) {
                pq.offer(batch);
            }
        }

        day++;
    }

    return eaten;
}
```

### Change 2: Track Which Days You Ate
**Modification**: Return list of days when apples eaten

```java
public List<Integer> eatenApplesDays(int[] apples, int[] days) {
    List<Integer> eatenDays = new ArrayList<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    int day = 0;

    while (day < apples.length || !pq.isEmpty()) {
        if (day < apples.length && apples[day] > 0) {
            pq.offer(new int[]{day + days[day], apples[day]});
        }

        while (!pq.isEmpty() && (pq.peek()[0] <= day || pq.peek()[1] == 0)) {
            pq.poll();
        }

        if (!pq.isEmpty()) {
            eatenDays.add(day);  // Record day
            int[] batch = pq.poll();
            batch[1]--;
            if (batch[1] > 0) {
                pq.offer(batch);
            }
        }
        day++;
    }

    return eatenDays;
}
```

### Change 3: Maximize Profit (Each Apple Has Value)
**Modification**: Apples have different values, maximize total value

```java
public int maxProfit(int[] apples, int[] days, int[] values) {
    // Store: [expirationDay, count, valuePerApple]
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        // First by expiration, then by value (descending)
        if (a[0] != b[0]) return a[0] - b[0];
        return b[2] - a[2];
    });

    int day = 0, profit = 0;

    while (day < apples.length || !pq.isEmpty()) {
        if (day < apples.length && apples[day] > 0) {
            pq.offer(new int[]{day + days[day], apples[day], values[day]});
        }

        while (!pq.isEmpty() && (pq.peek()[0] <= day || pq.peek()[1] == 0)) {
            pq.poll();
        }

        if (!pq.isEmpty()) {
            int[] batch = pq.poll();
            profit += batch[2];  // Add value
            batch[1]--;
            if (batch[1] > 0) {
                pq.offer(batch);
            }
        }
        day++;
    }

    return profit;
}
```

### Change 4: Some Days You Can't Eat
**Modification**: Array of unavailable days

```java
public int eatenApplesWithRestrictions(int[] apples, int[] days, Set<Integer> unavailableDays) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    int day = 0, eaten = 0;

    while (day < apples.length || !pq.isEmpty()) {
        if (day < apples.length && apples[day] > 0) {
            pq.offer(new int[]{day + days[day], apples[day]});
        }

        while (!pq.isEmpty() && (pq.peek()[0] <= day || pq.peek()[1] == 0)) {
            pq.poll();
        }

        // Only eat if day is available
        if (!unavailableDays.contains(day) && !pq.isEmpty()) {
            int[] batch = pq.poll();
            batch[1]--;
            eaten++;
            if (batch[1] > 0) {
                pq.offer(batch);
            }
        }
        day++;
    }

    return eaten;
}
```

### Change 5: Minimize Wasted Apples
**Modification**: Return number of apples that rotted

```java
public int wastedApples(int[] apples, int[] days) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    int day = 0, wasted = 0;

    while (day < apples.length || !pq.isEmpty()) {
        if (day < apples.length && apples[day] > 0) {
            pq.offer(new int[]{day + days[day], apples[day]});
        }

        // Count and remove expired apples
        while (!pq.isEmpty() && pq.peek()[0] <= day) {
            wasted += pq.poll()[1];  // All these apples wasted
        }

        // Remove empty batches
        while (!pq.isEmpty() && pq.peek()[1] == 0) {
            pq.poll();
        }

        if (!pq.isEmpty()) {
            int[] batch = pq.poll();
            batch[1]--;
            if (batch[1] > 0) {
                pq.offer(batch);
            }
        }
        day++;
    }

    // Count remaining apples as wasted
    while (!pq.isEmpty()) {
        wasted += pq.poll()[1];
    }

    return wasted;
}
```

## Visual Walkthrough

**Input**: `apples = [1,2,3,5,2], days = [3,2,1,4,2]`

```
Day 0:
  Grow: 1 apple, expires day 0+3=3
  Heap: [(3, 1)]
  Eat 1 apple from batch expiring day 3
  Heap: [(3, 0)] → remove empty
  Heap: []
  Eaten: 1

Day 1:
  Grow: 2 apples, expire day 1+2=3
  Heap: [(3, 2)]
  Eat 1 apple from batch expiring day 3
  Heap: [(3, 1)]
  Eaten: 2

Day 2:
  Grow: 3 apples, expire day 2+1=3
  Heap: [(3, 1), (3, 3)]
  Eat 1 apple from batch expiring day 3
  Heap: [(3, 0), (3, 3)] → remove empty
  Heap: [(3, 3)]
  Eaten: 3
  Note: Other batch with expiry 3 also expires today!

Day 3:
  Grow: 5 apples, expire day 3+4=7
  Heap: [(3, 3), (7, 5)]
  Remove batch expiring day 3 (rotten)
  Heap: [(7, 5)]
  Eat 1 apple from batch expiring day 7
  Heap: [(7, 4)]
  Eaten: 4

Day 4:
  Grow: 2 apples, expire day 4+2=6
  Heap: [(6, 2), (7, 4)]
  Eat 1 apple from batch expiring day 6 (earliest)
  Heap: [(6, 1), (7, 4)]
  Eaten: 5

Day 5 (after growth period):
  No growth
  Heap: [(6, 1), (7, 4)]
  Eat 1 apple from batch expiring day 6
  Heap: [(6, 0), (7, 4)] → remove empty
  Heap: [(7, 4)]
  Eaten: 6

Day 6:
  Heap: [(7, 4)]
  Eat 1 apple from batch expiring day 7
  Heap: [(7, 3)]
  Eaten: 7

Day 7:
  Heap: [(7, 3)]
  Remove batch expiring day 7 (rotten on this day)
  Heap: []
  Cannot eat
  Eaten: 7

Total: 7 ✓
```

## Key Patterns

**Pattern 1: Greedy with Expiration**
- Always choose item with earliest deadline
- Common in scheduling/event problems

**Pattern 2: Min Heap for Minimum**
- Efficiently track and access smallest value
- Better than repeated sorting

**Pattern 3: Cleanup Loop**
- Remove invalid items before processing
- Prevents accessing stale data

**Pattern 4: Continue Past Input**
- Problem allows action after input ends
- Loop condition: `day < n || !pq.isEmpty()`

## Complexity Analysis

- **Time**: O(n log n)
  - Each apple batch added once: O(n) batches
  - Each operation on heap: O(log n)
  - Total: O(n log n)

- **Space**: O(n)
  - Heap stores at most n batches
  - Each batch is int[2]

## Common Mistakes

1. **Not continuing after day n**: Missing apples that can be eaten later
2. **Wrong expiration check**: Using `<` instead of `<=`
3. **Not removing empty batches**: Causing infinite loops or errors
4. **Modifying array in heap**: Forgetting heap won't auto-update priorities
5. **Integer overflow**: When calculating expiration day (use bounds checking)

## Interview Tips

1. **Explain greedy choice**: "Always eat apples expiring soonest"
2. **Justify correctness**: "If we don't eat expiring apples, they rot and are wasted"
3. **Discuss heap choice**: "Min heap gives O(log n) access to earliest expiration"
4. **Handle edge cases**: Empty days, continuing after input
5. **Walk through example**: Show how heap evolves