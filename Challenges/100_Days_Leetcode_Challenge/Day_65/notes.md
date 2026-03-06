# Exclusive Time of Functions - Study Notes

## Approach: Stack with Time Tracking

### Key Insight

This problem simulates a **call stack** in a single-threaded CPU. The crucial insights are:

1. **Functions can be nested** (one function calls another)
2. **Functions can be recursive** (a function calls itself)
3. When a function is paused (another starts), we need to account for its execution time
4. When a function ends, we add the time from when it last resumed/started until now

**The stack tracks**: Which function is currently executing
**prevTime tracks**: The last timestamp we processed

### Important Timestamp Details

- `"start"` event happens at the **beginning** of the timestamp
- `"end"` event happens at the **end** of the timestamp
- This means when a function ends at time `t`, it includes time `t` (hence `time + 1` for next prevTime)

### Algorithm Walkthrough

**For each log entry:**

1. **Parse the log**: Extract function ID, type (start/end), and timestamp

2. **If "start"**:
   - The currently running function (if any) gets paused
   - Add time from `prevTime` to current `time` to the paused function
   - Push new function ID onto stack
   - Update `prevTime` to current `time`

3. **If "end"**:
   - Add time from `prevTime` to current `time + 1` to the ending function
   - Pop the function from stack
   - Update `prevTime` to `time + 1` (next unit after this one ends)

## Visual Example 1: ["0:start:0","1:start:2","1:end:5","0:end:6"]

```
Timeline:  0   1   2   3   4   5   6
          [0] [0] [1] [1] [1] [1] [0]

Detailed Execution:

Log: "0:start:0"
  - Type: start, id=0, time=0
  - Stack is empty, no function to pause
  - Push 0 onto stack
  - prevTime = 0
  Stack: [0]
  res: [0, 0]

Log: "1:start:2"
  - Type: start, id=1, time=2
  - Function 0 (at stack.peek()) has been running from prevTime=0 to time=2
  - res[0] += 2 - 0 = 2
  - Push 1 onto stack
  - prevTime = 2
  Stack: [0, 1]
  res: [2, 0]

Log: "1:end:5"
  - Type: end, id=1, time=5
  - Function 1 has been running from prevTime=2 to time=5 (inclusive)
  - res[1] += 5 - 2 + 1 = 4
  - Pop 1 from stack
  - prevTime = 5 + 1 = 6
  Stack: [0]
  res: [2, 4]

Log: "0:end:6"
  - Type: end, id=0, time=6
  - Function 0 has been running from prevTime=6 to time=6 (inclusive)
  - res[0] += 6 - 6 + 1 = 1
  - Pop 0 from stack
  - prevTime = 6 + 1 = 7
  Stack: []
  res: [3, 4]

Final Answer: [3, 4]
```

**Time breakdown:**
- Function 0: Times [0,1] (2 units) + Time [6] (1 unit) = 3 units
- Function 1: Times [2,3,4,5] (4 units) = 4 units

## Visual Example 2: ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]

```
This shows recursive calls!

Timeline:  0   1   2   3   4   5   6   7
          [0] [0] [0] [0] [0] [0] [0] [0]
                  [0] [0] [0] [0]
                              [0]

Log: "0:start:0"
  - Push 0
  - prevTime = 0
  Stack: [0]
  res: [0]

Log: "0:start:2"
  - Function 0 (outer) paused: res[0] += 2 - 0 = 2
  - Push 0 (recursive call)
  - prevTime = 2
  Stack: [0, 0]
  res: [2]

Log: "0:end:5"
  - Function 0 (inner): res[0] += 5 - 2 + 1 = 4
  - Pop 0
  - prevTime = 6
  Stack: [0]
  res: [6]  (2 + 4)

Log: "0:start:6"
  - Function 0 paused: res[0] += 6 - 6 = 0
  - Push 0 (2nd recursive call)
  - prevTime = 6
  Stack: [0, 0]
  res: [6]

Log: "0:end:6"
  - Function 0 (2nd inner): res[0] += 6 - 6 + 1 = 1
  - Pop 0
  - prevTime = 7
  Stack: [0]
  res: [7]  (2 + 4 + 1)

Log: "0:end:7"
  - Function 0 (outer): res[0] += 7 - 7 + 1 = 1
  - Pop 0
  - prevTime = 8
  Stack: []
  res: [8]  (2 + 4 + 1 + 1)

Final Answer: [8]
```

## Code Analysis

### Parsing the Log

```java
String[] parts = log.split(":");
int id = Integer.parseInt(parts[0]);
String type = parts[1];
int time = Integer.parseInt(parts[2]);
```

Each log is formatted as `"id:type:timestamp"`, so split by `:` gives us the three parts.

### Start Event Logic

```java
if (type.equals("start")) {
    if (!stack.isEmpty()) {
        res[stack.peek()] += time - prevTime;
    }
    stack.push(id);
    prevTime = time;
}
```

**Why this works:**
- If stack has a function, it's been running continuously since `prevTime`
- We pause it by adding elapsed time to its total
- New function starts, so we push it and update `prevTime`

### End Event Logic

```java
else {
    res[stack.pop()] += time - prevTime + 1;
    prevTime = time + 1;
}
```

**Why `+ 1` twice?**
1. `time - prevTime + 1`: The ending function includes the end timestamp
   - If function runs from time 2 to 5, that's times [2,3,4,5] = 4 units
   - Formula: 5 - 2 + 1 = 4 ✓
2. `prevTime = time + 1`: Next function (if any) starts after this timestamp ends
   - If current ends at time 5, next starts counting from time 6

### Why Track prevTime?

Without `prevTime`, we wouldn't know when the current function started executing. Consider:
```
"0:start:0" → Function 0 starts at 0
"1:start:5" → Function 1 starts at 5
```
When function 1 starts, function 0 has been running from 0 to 5 (5 units).
We need `prevTime = 0` to calculate this as `5 - 0 = 5`.

## Complexity Analysis

### Time Complexity: O(m)
- **m** = number of logs
- Each log is processed exactly once
- String split: O(1) for fixed-size parts
- Stack operations: O(1) each
- **Total: O(m)**

### Space Complexity: O(n)
- Result array: O(n)
- Stack: O(n) worst case (all functions nested)
- **Total: O(n)** where n is number of functions

## Edge Cases

### Case 1: Single function, no nesting
```
logs = ["0:start:0", "0:end:5"]
Timeline: [0][0][0][0][0][0]
res[0] = 5 - 0 + 1 = 6
Output: [6]
```

### Case 2: Deep nesting
```
logs = ["0:start:0", "1:start:1", "2:start:2", "2:end:3", "1:end:4", "0:end:5"]
Stack progression: [0] → [0,1] → [0,1,2] → [0,1] → [0] → []
Each function gets time when it's at top of stack
```

### Case 3: Multiple non-overlapping calls to same function
```
logs = ["0:start:0", "0:end:2", "0:start:5", "0:end:7"]
Function 0 runs twice: [0,1,2] and [5,6,7]
res[0] = 3 + 3 = 6
```

### Case 4: Immediate recursive call
```
logs = ["0:start:0", "0:start:0", ...]
Stack: [0, 0]
Both are same function, tracked separately
```

## Common Mistakes

1. **Forgetting the `+ 1` in end event calculation**
   ```java
   // WRONG - doesn't include the end timestamp
   res[stack.pop()] += time - prevTime;

   // CORRECT
   res[stack.pop()] += time - prevTime + 1;
   ```

2. **Not updating prevTime correctly for end events**
   ```java
   // WRONG - next function would count the end timestamp again
   prevTime = time;

   // CORRECT
   prevTime = time + 1;
   ```

3. **Not checking if stack is empty before peeking**
   ```java
   // WRONG - crashes on first start event
   res[stack.peek()] += time - prevTime;

   // CORRECT
   if (!stack.isEmpty()) {
       res[stack.peek()] += time - prevTime;
   }
   ```

4. **Trying to track absolute start times instead of intervals**
   - The key is tracking time intervals, not absolute positions

## Alternative Approaches

### Approach 1: Without prevTime (More Complex)
Store start times in the stack along with IDs.
```java
Stack<int[]> stack = new Stack<>(); // [id, startTime]
```
**Cons**: More complex, harder to handle pausing/resuming

### Approach 2: Separate structures for start times
Keep a map of function IDs to their start times.
**Cons**: More space, harder to handle recursive calls

### Approach 3: Process intervals explicitly
Build intervals and merge overlapping ones.
**Cons**: Much more complex, doesn't handle the problem structure well

**The given approach is optimal!**

## Interview Tips

1. **Clarify timestamp semantics**:
   - "Does 'start:5' mean it starts at the beginning of 5?"
   - "Does 'end:5' mean it includes time 5?"

2. **Draw a timeline**: Visual representation helps understand overlapping

3. **Explain the stack usage**:
   - "Stack represents the call stack - nested function calls"
   - "Top of stack is currently executing function"

4. **Walk through an example**:
   - Show how prevTime tracks intervals
   - Show how nested calls work

5. **Handle edge cases**:
   - Recursive calls (same function ID multiple times in stack)
   - Single function with no nesting
   - Deep nesting

## Key Takeaways

- **Stack simulates program call stack** - perfect for nested/recursive scenarios
- **Track intervals, not absolute times** - prevTime is the key variable
- **Inclusive end timestamps** require careful handling (`+ 1`)
- **Parse carefully** - string manipulation can be error-prone
- Understanding **when functions pause/resume** is crucial
- This pattern applies to many **time interval** and **nested structure** problems

## Practice Progression

After mastering this problem:
1. **Easier**: Valid Parentheses (20) - Simpler stack matching
2. **Similar**: Basic Calculator (224) - Stack with nested operations
3. **Harder**: Number of Atoms (726) - Complex nested parsing with stack
4. **Related**: Meeting Rooms II (253) - Time interval overlapping