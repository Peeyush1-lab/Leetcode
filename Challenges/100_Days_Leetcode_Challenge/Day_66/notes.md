# Evaluate Reverse Polish Notation - Study Notes

## Approach: Stack-Based Evaluation

### Key Insight

**Reverse Polish Notation (RPN)** is a postfix notation where operators come after operands. This makes it perfect for stack-based evaluation:

1. When you see a **number**: Push it onto the stack
2. When you see an **operator**: Pop two operands, apply the operator, push the result

**Why RPN is useful:**
- No parentheses needed
- No operator precedence rules needed
- Single left-to-right pass
- Natural fit for stack-based evaluation

### RPN vs Infix Notation

| Infix (Normal) | RPN (Postfix) | Evaluation |
|----------------|---------------|------------|
| `3 + 4` | `3 4 +` | Push 3, Push 4, Add → 7 |
| `(3 + 4) * 5` | `3 4 + 5 *` | (3+4)=7, Push 5, Multiply → 35 |
| `3 + 4 * 5` | `3 4 5 * +` | 4*5=20, 3+20 → 23 |

Notice how RPN naturally respects order without parentheses!

## Algorithm Walkthrough

### Step-by-Step Process

1. Initialize an empty stack (or list)
2. For each token:
   - **If it's a number**: Push it onto the stack
   - **If it's an operator**:
     - Pop the top two numbers (order matters!)
     - Apply the operation
     - Push the result back
3. The final result is the only element left in the stack

### Visual Example: ["2","1","+","3","*"]

```
Token: "2"
  Action: Push 2
  Stack: [2]

Token: "1"
  Action: Push 1
  Stack: [2, 1]

Token: "+"
  Action: Pop 1, Pop 2, Calculate 2+1=3, Push 3
  Stack: [3]

Token: "3"
  Action: Push 3
  Stack: [3, 3]

Token: "*"
  Action: Pop 3, Pop 3, Calculate 3*3=9, Push 9
  Stack: [9]

Result: 9
```

This represents: `(2 + 1) * 3 = 9`

### Complex Example: ["4","13","5","/","+"]

```
Token: "4"
  Stack: [4]

Token: "13"
  Stack: [4, 13]

Token: "5"
  Stack: [4, 13, 5]

Token: "/"
  Action: Pop 5 (divisor), Pop 13 (dividend)
  Calculate: 13 / 5 = 2 (integer division)
  Push 2
  Stack: [4, 2]

Token: "+"
  Action: Pop 2, Pop 4
  Calculate: 4 + 2 = 6
  Push 6
  Stack: [6]

Result: 6
```

This represents: `4 + (13 / 5) = 4 + 2 = 6`

### Detailed Example: ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]

```
Step-by-step evaluation:

"10"      → Stack: [10]
"6"       → Stack: [10, 6]
"9"       → Stack: [10, 6, 9]
"3"       → Stack: [10, 6, 9, 3]
"+"       → 9+3=12        Stack: [10, 6, 12]
"-11"     → Stack: [10, 6, 12, -11]
"*"       → 12*-11=-132   Stack: [10, 6, -132]
"/"       → 6/-132=0      Stack: [10, 0]
"*"       → 10*0=0        Stack: [0]
"17"      → Stack: [0, 17]
"+"       → 0+17=17       Stack: [17]
"5"       → Stack: [17, 5]
"+"       → 17+5=22       Stack: [22]

Result: 22
```

## Code Analysis

### Using ArrayList vs Stack

```java
ArrayList<Integer> arr = new ArrayList<>();
```

**Why ArrayList works:**
- `removeLast()` is O(1) - equivalent to stack's `pop()`
- `add()` is O(1) - equivalent to stack's `push()`
- Modern Java (21+) has `removeLast()` method

**Traditional Stack approach:**
```java
Stack<Integer> stack = new Stack<>();
// Use stack.pop() and stack.push()
```

Both work equally well for this problem!

### Order Matters for Non-Commutative Operations

```java
int a = arr.removeLast();  // Second operand (top of stack)
int b = arr.removeLast();  // First operand (below top)
arr.add(b - a);            // Correct order: b - a
```

**Critical for subtraction and division:**
- For `["5", "3", "-"]`: We want 5 - 3 = 2, not 3 - 5 = -2
- `a` is popped first (3), `b` is popped second (5)
- Result: `b - a = 5 - 3 = 2` ✓

### Why Order Doesn't Matter for + and *

```java
// For addition and multiplication (commutative operations)
arr.add(b + a);  // Same as arr.add(a + b)
arr.add(b * a);  // Same as arr.add(a * b)
```

Addition and multiplication are commutative, so the order of operands doesn't affect the result.

### Integer Division Truncation

```java
arr.add(b / a);  // Java's default integer division
```

Java automatically truncates toward zero:
- `13 / 5 = 2` (not 2.6)
- `-13 / 5 = -2` (not -3)
- `13 / -5 = -2` (not -3)

This matches the problem requirement!

## Complexity Analysis

### Time Complexity: O(n)
- **n** = number of tokens
- Each token is processed exactly once
- Stack operations (push/pop): O(1) each
- **Total: O(n)**

### Space Complexity: O(n)
- Stack can hold up to n/2 numbers in worst case
- Example: `["1","2","3","4","5","+","+","+","+"]`
- Stack grows to [1,2,3,4,5] then shrinks
- **Total: O(n)**

## Edge Cases

### Case 1: Single number
```
Input: ["42"]
No operators, just return the number
Output: 42
```

### Case 2: Simple two-number operation
```
Input: ["3", "4", "+"]
Stack: [3] → [3,4] → [7]
Output: 7
```

### Case 3: Negative numbers
```
Input: ["-3", "4", "+"]
Stack: [-3] → [-3,4] → [1]
Output: 1

Note: "-3" is a single token (negative number), not minus operator
```

### Case 4: Division with truncation
```
Input: ["7", "3", "/"]
7 / 3 = 2 (not 2.333...)
Output: 2

Input: ["-7", "3", "/"]
-7 / 3 = -2 (truncates toward zero, not floor division)
Output: -2
```

### Case 5: All operations
```
Input: ["2", "3", "+", "4", "*", "5", "-"]
Evaluates: ((2 + 3) * 4) - 5
Stack progression:
[2] → [2,3] → [5] → [5,4] → [20] → [20,5] → [15]
Output: 15
```

## Common Mistakes

1. **Wrong operand order for subtraction/division**
   ```java
   // WRONG - reversed order
   int a = arr.removeLast();
   int b = arr.removeLast();
   arr.add(a - b);  // Should be b - a

   // CORRECT
   arr.add(b - a);
   ```

2. **Using regular Stack instead of ArrayList**
   ```java
   // Old style (still works but verbose)
   Stack<Integer> stack = new Stack<>();
   int a = stack.pop();
   int b = stack.pop();

   // Modern style (cleaner)
   ArrayList<Integer> arr = new ArrayList<>();
   int a = arr.removeLast();
   int b = arr.removeLast();
   ```

3. **Not handling negative numbers**
   - Remember: "-3" is a number token, not an operator
   - `Integer.parseInt("-3")` correctly parses to -3

4. **Assuming the answer is always at index 0**
   - It is for valid RPN, but it's good practice to check
   - Could use `arr.get(arr.size() - 1)` or `arr.getLast()`

5. **Floor division vs truncation toward zero**
   ```java
   // Java's / operator truncates toward zero (correct for this problem)
   -7 / 3 = -2   (not -3)

   // Python's // operator does floor division
   -7 // 3 = -3  (would be wrong for this problem)
   ```

## Alternative Approaches

### Approach 1: Using Traditional Stack
```java
public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();

    for (String token : tokens) {
        if (token.equals("+")) {
            stack.push(stack.pop() + stack.pop());
        } else if (token.equals("-")) {
            int a = stack.pop();
            int b = stack.pop();
            stack.push(b - a);
        } else if (token.equals("*")) {
            stack.push(stack.pop() * stack.pop());
        } else if (token.equals("/")) {
            int a = stack.pop();
            int b = stack.pop();
            stack.push(b / a);
        } else {
            stack.push(Integer.parseInt(token));
        }
    }

    return stack.pop();
}
```

### Approach 2: Using Switch Statement (More Elegant)
```java
public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();

    for (String token : tokens) {
        switch (token) {
            case "+" -> stack.push(stack.pop() + stack.pop());
            case "-" -> {
                int a = stack.pop();
                stack.push(stack.pop() - a);
            }
            case "*" -> stack.push(stack.pop() * stack.pop());
            case "/" -> {
                int a = stack.pop();
                stack.push(stack.pop() / a);
            }
            default -> stack.push(Integer.parseInt(token));
        }
    }

    return stack.pop();
}
```

### Approach 3: Recursive (Not Recommended)
Convert RPN to expression tree and evaluate.
**Cons**: Much more complex, unnecessary for this problem

## Why RPN is Better Than Infix

1. **No Parentheses Needed**
   - Infix: `((3 + 4) * 5) + 6` needs parentheses
   - RPN: `3 4 + 5 * 6 +` naturally respects order

2. **No Operator Precedence Ambiguity**
   - Infix: `3 + 4 * 5` (need to know * comes before +)
   - RPN: `3 4 5 * +` or `4 5 * 3 +` is unambiguous

3. **Easy to Evaluate**
   - Infix: Need complex parsing (operator precedence, parentheses)
   - RPN: Simple stack-based evaluation

4. **Historical Use**
   - Used in HP calculators
   - Used in PostScript and Forth languages
   - Easier for computers to process

## Interview Tips

1. **Explain RPN first**: "Reverse Polish Notation puts operators after operands"

2. **Walk through simple example**: Use `["2","1","+","3","*"]` to show stack

3. **Emphasize order**: "Order matters for subtraction and division"

4. **Discuss time complexity**: "O(n) because we process each token once"

5. **Handle edge cases**: Mention negative numbers, single tokens

6. **Compare approaches**: Stack vs ArrayList, if-else vs switch

## Key Takeaways

- **RPN is perfect for stack-based evaluation** - operators come after operands
- **Order matters** for non-commutative operations (-, /)
- **Single pass** through tokens is sufficient
- **No parentheses or precedence** needed - RPN naturally encodes order
- Understanding RPN helps with **expression parsing** problems
- This pattern applies to many **stack-based evaluation** problems

## Practice Progression

After mastering this problem:
1. **Easier**: Baseball Game (682) - Similar stack operations
2. **Similar**: Basic Calculator II (227) - Infix to evaluation
3. **Harder**: Basic Calculator III (772) - Parentheses + precedence
4. **Different**: Decode String (394) - Stack with nested structures