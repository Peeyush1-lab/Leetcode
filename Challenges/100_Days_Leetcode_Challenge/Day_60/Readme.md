# 1544. Make The String Great

**Difficulty:** Easy

## Problem Description

Given a string `s` of lower and upper case English letters.

A good string is a string which doesn't have **two adjacent characters** `s[i]` and `s[i + 1]` where:

- `0 <= i <= s.length - 2`
- `s[i]` is a lower-case letter and `s[i + 1]` is the same letter but in upper-case or **vice-versa**.

To make the string good, you can choose **two adjacent** characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.

**Notice** that an empty string is also good.

## Examples

### Example 1:
```
Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
```

### Example 2:
```
Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" → "aAcC" → "cC" → ""
"abBAcC" → "abBA" → "aA" → ""
```

### Example 3:
```
Input: s = "s"
Output: "s"
```

## Constraints

- `1 <= s.length <= 100`
- `s` contains only lower and upper case English letters.

## Topics
- String
- Stack

## Solution Approach

This solution uses a **StringBuilder as Stack** with an **ASCII difference trick**:

### Key Insight

The ASCII values of uppercase and lowercase letters differ by exactly 32:
- `'a'` = 97, `'A'` = 65 → difference = 32
- `'b'` = 98, `'B'` = 66 → difference = 32
- `'z'` = 122, `'Z'` = 90 → difference = 32

So two characters form a "bad pair" if they're the same letter with different cases, which means `|char1 - char2| = 32`.

### Algorithm

1. **Use StringBuilder as stack** (more efficient than Stack class)
2. **For each character:**
   - If stack is non-empty AND difference with top is 32: Remove top (bad pair found)
   - Otherwise: Add character to stack
3. **Return the resulting string**

### Why StringBuilder Instead of Stack?

- More efficient for string building
- Direct conversion to string with `toString()`
- Same LIFO behavior for our use case

**Time Complexity:** O(n) - Single pass through string
**Space Complexity:** O(n) - StringBuilder can hold up to n characters

## Related Problems

- [1047. Remove All Adjacent Duplicates In String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) (Easy) - Similar stack pattern
- [1209. Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/) (Medium) - With count condition
- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) (Easy) - Classic stack matching
- [844. Backspace String Compare](https://leetcode.com/problems/backspace-string-compare/) (Easy) - Stack-like behavior
- [71. Simplify Path](https://leetcode.com/problems/simplify-path/) (Medium) - Stack for path simplification

|**Previous:** [Day 59](../Day_59/) | **Next:** [Day 61](../Day_61/)|
|---|---|

|**Date:** March 01, 2026|
|---|