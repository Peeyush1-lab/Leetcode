# Notes - Valid Parentheses

## Problem Overview

Check if a string of brackets is valid - every opening bracket must have a matching closing bracket in the correct order.

**Valid:** `"()", "()[]{}", "({[]})"`
**Invalid:** `"(]", "([)]", "(("`

## Why Use a Stack?

This is a classic **Last-In-First-Out (LIFO)** problem:
- Most recently opened bracket should be closed first
- Perfect use case for a stack!

**Example:** `"({[]})"`
```
Read '(': push → stack: ['(']
Read '{': push → stack: ['(', '{']
Read '[': push → stack: ['(', '{', '[']
Read ']': pop '[' → stack: ['(', '{']  ✓ matches
Read '}': pop '{' → stack: ['(']       ✓ matches
Read ')': pop '(' → stack: []          ✓ matches
Stack empty → Valid!
```

## The Clever ASCII Trick

### ASCII Values

```
Character | ASCII | Closing Char | ASCII | Difference
----------|-------|--------------|-------|------------
   '('    |  40   |     ')'      |  41   |    +1
   '['    |  91   |     ']'      |  93   |    +2
   '{'    | 123   |     '}'      | 125   |    +2
```

### The Magic Check

```java
brackets.peek() + 1 == s.charAt(i) || brackets.peek() + 2 == s.charAt(i)
```

This checks if the closing bracket matches the opening bracket on top of the stack!

**Examples:**
- `'('` (40) + 1 = 41 = `')'` ✓
- `'['` (91) + 2 = 93 = `']'` ✓
- `'{'` (123) + 2 = 125 = `'}'` ✓

### Why This Works

The ASCII values of brackets were designed with specific patterns. Your solution exploits this pattern to avoid explicit character comparisons!

## Step-by-Step Walkthrough

### Example 1: s = "({[]})"

```
Initialize:
  stack = []
  brac_start = "({["

i=0, char='(':
  '(' is in brac_start → push
  stack = ['(']

i=1, char='{':
  '{' is in brac_start → push
  stack = ['(', '{']

i=2, char='[':
  '[' is in brac_start → push
  stack = ['(', '{', '[']

i=3, char=']':
  ']' NOT in brac_start → closing bracket
  stack not empty ✓
  peek='[' (91), char=']' (93)
  91 + 2 = 93 ✓ Match!
  pop
  stack = ['(', '{']

i=4, char='}':
  '}' NOT in brac_start → closing bracket
  stack not empty ✓
  peek='{' (123), char='}' (125)
  123 + 2 = 125 ✓ Match!
  pop
  stack = ['(']

i=5, char=')':
  ')' NOT in brac_start → closing bracket
  stack not empty ✓
  peek='(' (40), char=')' (41)
  40 + 1 = 41 ✓ Match!
  pop
  stack = []

Loop complete
stack.empty() = true
Return true ✅
```

### Example 2: s = "([)]" (Invalid)

```
Initialize:
  stack = []

i=0, char='(': push → stack = ['(']
i=1, char='[': push → stack = ['(', '[']

i=2, char=')':
  ')' NOT in brac_start
  peek='[' (91), char=')' (41)
  91 + 1 = 92 ✗ (not 41)
  91 + 2 = 93 ✗ (not 41)
  No match! Return false ✅
```

### Example 3: s = "(((" (Invalid)

```
i=0, char='(': push → stack = ['(']
i=1, char='(': push → stack = ['(', '(']
i=2, char='(': push → stack = ['(', '(', '(']

Loop complete
stack.empty() = false (still has 3 elements)
Return false ✅
```

### Example 4: s = ")" (Invalid)

```
Length = 1
Return false ✅ (early check)
```

## Code Breakdown

```java
public boolean isValid(String s) {
    Stack<Character> brackets = new Stack<>();
    String brac_start = "({[";  // Opening brackets

    // Edge case: single character can't be valid
    if (s.length() == 1) {
        return false;
    } else {
        // Process each character
        for (int i = 0; i < s.length(); i++) {
            // Check if it's an opening bracket
            if (brac_start.indexOf(s.charAt(i)) != -1) {
                brackets.push(s.charAt(i));  // Push opening bracket
            } else {
                // It's a closing bracket
                // Check if stack is empty OR brackets don't match
                if (!brackets.empty() &&
                    (brackets.peek() + 1 == s.charAt(i) ||
                     brackets.peek() + 2 == s.charAt(i))) {
                    brackets.pop();  // Match found, remove opening
                } else {
                    return false;  // No match or empty stack
                }
            }
        }
    }

    // Valid only if all brackets were matched (stack empty)
    if (brackets.empty()) {
        return true;
    }
    return false;
}
```

## Why Each Part is Necessary

### 1. Single Character Check
```java
if (s.length() == 1) return false;
```
A single bracket can never be valid (needs opening AND closing).

### 2. Opening Bracket Detection
```java
if (brac_start.indexOf(s.charAt(i)) != -1)
```
Checks if character is `'('`, `'{'`, or `'['`.
- Returns index (0, 1, or 2) if found
- Returns -1 if not found

### 3. ASCII Matching
```java
brackets.peek() + 1 == s.charAt(i) || brackets.peek() + 2 == s.charAt(i)
```
Clever! Checks if closing bracket matches opening bracket using ASCII arithmetic.

### 4. Empty Stack Check
```java
if (!brackets.empty() && ...)
```
**Critical!** If stack is empty when we see a closing bracket, there's no matching opening bracket → invalid.

### 5. Final Stack Check
```java
if (brackets.empty()) return true;
```
After processing all characters, stack should be empty. If not, there are unmatched opening brackets.

## Edge Cases

### 1. Empty String
```java
Input: s = ""
Output: true (technically valid, but constraints say length >= 1)
```
Not tested based on constraints.

### 2. Single Character
```java
Input: s = "("
Output: false
```
✅ Handled by early check

### 3. Only Opening Brackets
```java
Input: s = "((("
Output: false
```
✅ Stack not empty at end

### 4. Only Closing Brackets
```java
Input: s = ")))"
Output: false
```
✅ Stack empty when trying to pop

### 5. Wrong Order
```java
Input: s = "([)]"
Output: false
```
✅ ASCII check fails

### 6. Nested Valid
```java
Input: s = "{[()]}"
Output: true
```
✅ Proper LIFO matching

## Common Mistakes

### ❌ Mistake 1: Not checking if stack is empty before pop

```java
if (brackets.peek() + 1 == s.charAt(i)) {  // WRONG!
    brackets.pop();  // Could throw EmptyStackException
}
```

Always check `!brackets.empty()` first!

### ❌ Mistake 2: Using wrong ASCII arithmetic

```java
if (brackets.peek() == s.charAt(i) - 1)  // WRONG order
```

Should be `peek() + 1` or `peek() + 2`, not `char - 1`.

### ❌ Mistake 3: Not checking stack emptiness at end

```java
return true;  // WRONG! Might have unmatched opening brackets
```

Must check `brackets.empty()` at the end.

### ❌ Mistake 4: Forgetting the single-length check

```java
// Start loop without checking s.length() == 1
```

While this would still work (return false at end), the early check is more efficient.

### ❌ Mistake 5: Using == for Character comparison

```java
if (brackets.peek() == '(')  // Could work but less elegant
```

The ASCII trick is more elegant than multiple if-else statements.

## Complexity Analysis

**Time Complexity:** O(n)
- Single pass through string: O(n)
- Each push/pop/peek operation: O(1)
- indexOf operation: O(1) (only 3 characters)
- Total: O(n)

**Space Complexity:** O(n)
- Stack can hold up to n/2 opening brackets
- Example: "(((" has 3 opening brackets in stack
- Worst case: O(n) for all opening brackets

## Alternative Approach: Explicit Mapping

```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    Map<Character, Character> map = new HashMap<>();
    map.put(')', '(');
    map.put('}', '{');
    map.put(']', '[');

    for (char c : s.toCharArray()) {
        if (map.containsKey(c)) {  // Closing bracket
            if (stack.isEmpty() || stack.pop() != map.get(c)) {
                return false;
            }
        } else {  // Opening bracket
            stack.push(c);
        }
    }

    return stack.isEmpty();
}
```

**Pros:** More readable, explicit matching
**Cons:** Uses extra HashMap space

**Your ASCII approach:** More clever, no HashMap needed!

## The Stack Pattern

This problem demonstrates the **matching pairs with stack** pattern:

```
Whenever you see: "most recent X should match with Y"
Think: STACK (LIFO)
```

**Common applications:**
- Balanced brackets (this problem)
- HTML/XML tag matching
- Expression evaluation
- Function call stack
- Undo/Redo operations

## Interview Tips

1. **Explain the LIFO nature:**
   - "Most recently opened bracket should close first"
   - "Perfect for stack!"

2. **Mention the ASCII trick:**
   - "Notice the ASCII values have a pattern"
   - "Opening + 1 or +2 gives closing"

3. **Walk through an example:**
   - Show stack operations for `"({[]})"`
   - Demonstrate matching at each step

4. **Discuss edge cases:**
   - Single character → false
   - Empty stack when closing → false
   - Non-empty stack at end → false

5. **Mention alternatives:**
   - "Could use HashMap for explicit matching"
   - "ASCII trick is more elegant"

6. **State complexity:** "O(n) time, O(n) space"

## Related Problems with Hyperlinks

- [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) (Medium) - Generate all valid bracket combinations
- [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) (Hard) - Find longest valid substring
- [301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/) (Hard) - Remove minimum brackets to make valid
- [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) (Medium) - Count additions needed
- [1541. Minimum Insertions to Balance a Parentheses String](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/) (Medium) - Balance with special rules
- [1249. Minimum Remove to Make Valid Parentheses](https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/) (Medium) - Remove to make valid
- [856. Score of Parentheses](https://leetcode.com/problems/score-of-parentheses/) (Medium) - Calculate score of valid parentheses

## Key Takeaways

1. **Stack is perfect for bracket matching** - LIFO nature matches the requirement
2. **ASCII arithmetic can simplify code** - Exploit patterns in character encoding
3. **Check stack empty before pop/peek** - Avoid exceptions
4. **Three failure modes:**
   - Closing bracket with empty stack
   - Mismatched bracket types
   - Unmatched opening brackets at end
5. **O(n) time, O(n) space** - Optimal for this problem
6. **Pattern recognition** - "Most recent X matches Y" → think stack

Clever use of ASCII values for bracket matching!