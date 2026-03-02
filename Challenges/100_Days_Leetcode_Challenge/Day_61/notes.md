# Min Stack - Study Notes

## Approach: Two Stack Solution

### Key Insight
The challenge is to retrieve the minimum element in O(1) time while maintaining standard stack operations. The solution uses **two parallel stacks**:
1. **Main Stack**: stores all values
2. **Min Stack**: stores the minimum value at each level

### How It Works

When we push a value:
- Always push to the main stack
- Push to minStack: the minimum between the new value and current minimum

When we pop:
- Pop from both stacks simultaneously
- This maintains synchronization

### Visual Example
```
Operation: push(-2)
stack:    [-2]
minStack: [-2]    // -2 is the only element, so it's the min

Operation: push(0)
stack:    [-2, 0]
minStack: [-2, -2]    // min(0, -2) = -2

Operation: push(-3)
stack:    [-2, 0, -3]
minStack: [-2, -2, -3]    // min(-3, -2) = -3

getMin() returns -3 (top of minStack)

Operation: pop()
stack:    [-2, 0]
minStack: [-2, -2]

getMin() returns -2 (top of minStack)
```

### Why This Works

Each position in minStack stores the minimum of all elements **at or below** that position in the main stack. When we pop an element, we also pop the minimum for that level, automatically revealing the minimum for the remaining elements.

## Complexity Analysis

### Time Complexity: O(1) for all operations
- **push()**: Two stack pushes + one comparison = O(1)
- **pop()**: Two stack pops = O(1)
- **top()**: One stack peek = O(1)
- **getMin()**: One stack peek = O(1)

### Space Complexity: O(n)
- Main stack stores n elements
- Min stack stores n elements
- Total: 2n = O(n)

## Alternative Approaches

### 1. Single Stack with Pairs
Store pairs (value, currentMin) in one stack:
```java
class MinStack {
    private Stack<int[]> stack;

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new int[]{val, val});
        } else {
            int currentMin = stack.peek()[1];
            stack.push(new int[]{val, Math.min(val, currentMin)});
        }
    }
}
```
**Pros**: Conceptually simpler, one data structure
**Cons**: Same space complexity, more memory per element

### 2. Optimized Min Stack (Space Efficient)
Only push to minStack when we find a new minimum:
```java
public void push(int val) {
    stack.push(val);
    if (minStack.isEmpty() || val <= minStack.peek()) {
        minStack.push(val);
    }
}

public void pop() {
    if (stack.peek().equals(minStack.peek())) {
        minStack.pop();
    }
    stack.pop();
}
```
**Pros**: Better space complexity in practice (fewer elements in minStack)
**Cons**: Slightly more complex logic, need to handle equality carefully

### 3. Single Stack with Difference Encoding
Store differences from minimum, only push actual value when it's a new min:
**Pros**: Can achieve O(1) space in best case
**Cons**: Complex implementation, harder to understand, potential overflow issues

## Common Pitfalls

1. **Forgetting to update minStack on push**
   - Must always keep stacks synchronized

2. **Using `<` instead of `<=` when pushing to minStack**
   - When duplicates of minimum exist, we need multiple entries

3. **Not popping from both stacks**
   - If you only pop from main stack, minStack becomes out of sync

4. **Integer overflow**
   - When using difference encoding approach, subtraction can overflow

## Interview Tips

1. **Start with the constraint**: "O(1) for all operations including getMin"
   - This rules out searching through the stack each time

2. **Explain the trade-off**:
   - Trading space (extra stack) for time (O(1) getMin)

3. **Discuss alternatives**:
   - Show you understand there are multiple valid approaches

4. **Handle edge cases**:
   - Empty stack (but problem states methods won't be called on empty stack)
   - Duplicate minimums
   - Integer overflow (if using difference approach)

## Key Takeaways

- **Auxiliary data structures** can maintain extra information efficiently
- **Parallel tracking** is a common pattern for maintaining aggregates
- Sometimes the best solution is the **simplest one** (two stacks vs complex encoding)
- When designing data structures, consider the **frequency of operations**
  - If getMin is called rarely, O(n) might be acceptable
  - But O(1) requirement forces us to maintain minimum continuously