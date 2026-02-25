# 20. Valid Parentheses

**Difficulty:** Easy

## Problem Description

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

## Examples

### Example 1:
```
Input: s = "()"
Output: true
```

### Example 2:
```
Input: s = "()[]{}"
Output: true
```

### Example 3:
```
Input: s = "(]"
Output: false
```

### Example 4:
```
Input: s = "([])"
Output: true
```

## Constraints

- `1 <= s.length <= 10^4`
- `s` consists of parentheses only `'()[]{}'`.

## Topics
- String
- Stack

## Solution Approach

This solution uses a **Stack** with a clever ASCII trick:

### Algorithm

1. **Use a stack** to track opening brackets
2. **For opening brackets** `(`, `{`, `[`: Push onto stack
3. **For closing brackets** `)`, `}`, `]`:
   - Check if stack is empty → invalid
   - Check if top of stack matches (using ASCII values)
   - If matches: pop the stack
   - If doesn't match: invalid
4. **After processing all characters**: Stack should be empty

### The ASCII Trick

The solution uses a clever observation about ASCII values:
- `'('` = 40, `')'` = 41 → difference of 1
- `'['` = 91, `']'` = 93 → difference of 2
- `'{'` = 123, `'}'` = 125 → difference of 2

So checking `brackets.peek() + 1 == s.charAt(i) || brackets.peek() + 2 == s.charAt(i)` verifies if the closing bracket matches the opening bracket!

**Time Complexity:** O(n) - Single pass through the string
**Space Complexity:** O(n) - Stack can hold up to n/2 opening brackets

## Related Problems

- [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) (Medium) - Generate all valid combinations
- [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/) (Hard) - Find longest valid substring
- [301. Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/) (Hard) - Remove minimum to make valid
- [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) (Medium) - Count additions needed
- [1541. Minimum Insertions to Balance a Parentheses String](https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/) (Medium) - Balance with rules

|**Previous:** [Day 55](../Day_55/) | **Next:** [Day 57](../Day_57/)|
|---|---|

|**Date:** February 25, 2026|
|---|