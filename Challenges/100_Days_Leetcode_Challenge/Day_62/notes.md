# Baseball Game - Study Notes

## Approach: Stack-Based Simulation

### Key Insight
This problem is a classic **stack simulation** where we need to:
1. Maintain a record of scores (stack is perfect for this)
2. Support operations that depend on previous scores (stack's LIFO property)
3. Sometimes remove the most recent entry (stack's pop operation)

The stack naturally handles all operations efficiently because we only need to access the most recent 1-2 scores.

### Algorithm Walkthrough

**Step-by-step process:**
1. Iterate through each operation
2. Check the operation type and perform accordingly:
   - **Number**: Push the integer value onto stack
   - **"C"**: Remove the last score (pop)
   - **"D"**: Double the last score and push it
   - **"+"**: Sum the last two scores and push the result
3. Sum all remaining values in the stack

### Visual Example

Let's trace through Example 1: ["5","2","C","D","+"]

```
Operation: "5"
Action: Push 5
Stack: [5]
Sum so far: 5

Operation: "2"
Action: Push 2
Stack: [5, 2]
Sum so far: 7

Operation: "C"
Action: Remove last score (2)
Stack: [5]
Sum so far: 5

Operation: "D"
Action: Double last score (5 * 2 = 10), push 10
Stack: [5, 10]
Sum so far: 15

Operation: "+"
Action: Sum last two scores (5 + 10 = 15), push 15
Stack: [5, 10, 15]
Final sum: 30
```

### Detailed Example with Negative Numbers

Example 2: ["5","-2","4","C","D","9","+","+"]

```
"5"    → Stack: [5]
"-2"   → Stack: [5, -2]
"4"    → Stack: [5, -2, 4]
"C"    → Stack: [5, -2]           (remove 4)
"D"    → Stack: [5, -2, -4]       (-2 * 2 = -4)
"9"    → Stack: [5, -2, -4, 9]
"+"    → Stack: [5, -2, -4, 9, 5]  (-4 + 9 = 5)
"+"    → Stack: [5, -2, -4, 9, 5, 14]  (9 + 5 = 14)

Final sum: 5 + (-2) + (-4) + 9 + 5 + 14 = 27
```

## Code Analysis

### The "+" Operation
```java
else if(i.equals("+"))
{
    int a = st.pop();      // Get the most recent score
    int b = st.peek();     // Get the second most recent (don't remove)
    st.push(a);            // Put the first score back
    st.push(a+b);          // Push the sum
}
```

**Why this works:**
- Pop first to access second element
- Peek at second element
- Push first back to maintain it
- Push sum as new score

## Complexity Analysis

### Time Complexity: O(n)
- **n** = length of operations array
- Each operation is processed once: O(n)
- String comparison and stack operations: O(1) each
- Final sum calculation: O(n) worst case
- **Total: O(n)**

### Space Complexity: O(n)
- Stack can contain up to n elements in worst case
- Example: all operations are numbers
- **Total: O(n)**

## Edge Cases to Consider

1. **Single "C" operation after a number**
   - Input: ["1","C"]
   - Output: 0 (Stack becomes empty)

2. **All operations are numbers**
   - Input: ["1","2","3","4","5"]
   - Stack: [1,2,3,4,5], Sum: 15

3. **Negative numbers**
   - Input: ["-10","D","C"]
   - Stack after "D": [-10, -20]
   - Stack after "C": [-10], Sum: -10

4. **Multiple consecutive "+" operations**
   - Input: ["5","10","+","+"]
   - Final stack: [5, 10, 15, 25], Sum: 55

## Common Mistakes

1. **Forgetting to restore the first score in "+" operation**

2. **Not handling negative numbers**
   - Negative numbers are passed as strings like "-2"
   - Integer.parseInt() handles this correctly

3. **Using == for string comparison**
   - WRONG: if(i == "C")
   - CORRECT: if(i.equals("C"))

## Interview Tips

1. **Clarify the problem**: Confirm that all operations are guaranteed valid

2. **Explain your approach**: "Stack is natural because we only access recent scores"

3. **Discuss trade-offs**: Stack vs ArrayList vs Array

## Key Takeaways

- Stack is ideal for "last-in" dependencies
- Always use .equals() for string comparison in Java
- Simulation problems: Break down complex rules into simple cases
- The straightforward solution is often best for interviews