# 🔗 Day 9: Valid Parentheses

## 📋 Problem Statement

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

## 💡 Examples

### Example 1: ✅
**Input:** `s = "()"`
**Output:** `true`

### Example 2: ✅
**Input:** `s = "()[]{}"`
**Output:** `true`

### Example 3: ❌
**Input:** `s = "(]"`
**Output:** `false`

### Example 4: ✅
**Input:** `s = "{[]}"`
**Output:** `true`

### Example 5: ❌
**Input:** `s = "([)]"`
**Output:** `false`

## ⚠️ Constraints

- `1 <= s.length <= 10^4`
- `s` consists of parentheses only `'()[]{}'`

## 🔑 Key Points

1. 📚 **Stack** is the perfect data structure!
2. 🔓 Push opening brackets onto stack
3. 🔒 Pop and match closing brackets
4. ✅ Valid if stack empty at end
5. 🎯 ASCII trick: closing bracket = opening + 1 or 2

## 🛠️ Approach: Stack with ASCII Matching

### 💡 Core Intuition
- Opening brackets → **Push** to stack
- Closing brackets → **Pop** and check if they match

### 🎯 ASCII Magic
```
'(' = 40, ')' = 41  → difference = 1
'[' = 91, ']' = 93  → difference = 2
'{' = 123, '}' = 125 → difference = 2
```

### ⏱️ Complexity
- **Time:** O(n)
- **Space:** O(n)