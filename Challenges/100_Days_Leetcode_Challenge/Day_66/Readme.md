# 150. Evaluate Reverse Polish Notation

**Difficulty:** Medium
**Day:** 66
**Topics:** Array, Math, Stack

## Problem Description

You are given an array of strings `tokens` that represents an arithmetic expression in **Reverse Polish Notation** (RPN).

Evaluate the expression and return an integer that represents the value of the expression.

**Note that:**
- The valid operators are `'+'`, `'-'`, `'*'`, and `'/'`.
- Each operand may be an integer or another expression.
- The division between two integers always **truncates toward zero**.
- There will not be any division by zero.
- The input represents a valid arithmetic expression in reverse Polish notation.
- The answer and all the intermediate calculations can be represented in a **32-bit** integer.

## What is Reverse Polish Notation?

Reverse Polish Notation (RPN), also known as postfix notation, is a mathematical notation where operators follow their operands.

**Examples:**
- Infix: `3 + 4` → RPN: `3 4 +`
- Infix: `(3 + 4) * 5` → RPN: `3 4 + 5 *`
- Infix: `3 + (4 * 5)` → RPN: `3 4 5 * +`

## Examples

### Example 1:

**Input:**
```
tokens = ["2","1","+","3","*"]
```

**Output:**
```
9
```

**Explanation:**
```
((2 + 1) * 3) = 9
Step by step:
  2 1 +  → 3
  3 3 *  → 9
```

### Example 2:

**Input:**
```
tokens = ["4","13","5","/","+"]
```

**Output:**
```
6
```

**Explanation:**
```
(4 + (13 / 5)) = 6
Step by step:
  13 5 /  → 2 (truncated division)
  4 2 +   → 6
```

### Example 3:

**Input:**
```
tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
```

**Output:**
```
22
```

**Explanation:**
```
((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```

## Constraints

- `1 <= tokens.length <= 10⁴`
- `tokens[i]` is either an operator: `"+"`, `"-"`, `"*"`, or `"/"`, or an integer in the range `[-200, 200]`.

## Topics

- Array
- Math
- Stack
- Expression Evaluation

## Similar Problems

- [224. Basic Calculator](https://leetcode.com/problems/basic-calculator/) - Hard
- [227. Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) - Medium
- [772. Basic Calculator III](https://leetcode.com/problems/basic-calculator-iii/) - Hard
- [394. Decode String](https://leetcode.com/problems/decode-string/) - Medium
- [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/) - Medium
- [726. Number of Atoms](https://leetcode.com/problems/number-of-atoms/) - Hard

## Related Topics Problems

**Stack-Based Evaluation:**
- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Easy
- [71. Simplify Path](https://leetcode.com/problems/simplify-path/) - Medium
- [385. Mini Parser](https://leetcode.com/problems/mini-parser/) - Medium
- [682. Baseball Game](https://leetcode.com/problems/baseball-game/) - Easy

**Expression Parsing:**
- [241. Different Ways to Add Parentheses](https://leetcode.com/problems/different-ways-to-add-parentheses/) - Medium
- [282. Expression Add Operators](https://leetcode.com/problems/expression-add-operators/) - Hard
- [439. Ternary Expression Parser](https://leetcode.com/problems/ternary-expression-parser/) - Medium

**Mathematical Operations:**
- [29. Divide Two Integers](https://leetcode.com/problems/divide-two-integers/) - Medium
- [50. Pow(x, n)](https://leetcode.com/problems/powx-n/) - Medium
- [166. Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal/) - Medium

---

|**Previous:** [Day 65](../Day_65/) | **Next:** [Day 67](../Day_67/)|
|---|---|

|**Date:** March 07, 2026|
|---|