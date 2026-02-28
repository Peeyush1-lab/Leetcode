# 2000. Reverse Prefix of Word

**Difficulty:** Easy

## Problem Description

Given a **0-indexed** string `word` and a character `ch`, **reverse** the segment of `word` that starts at index `0` and ends at the index of the **first occurrence** of `ch` (**inclusive**). If the character `ch` does not exist in `word`, do nothing.

- For example, if `word = "abcdefd"` and `ch = "d"`, then you should reverse the segment that starts at `0` and ends at `3` (**inclusive**). The resulting string will be `"dcbaefd"`.

Return the resulting string.

## Examples

### Example 1:
```
Input: word = "abcdefd", ch = "d"
Output: "dcbaefd"
Explanation: The first occurrence of "d" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
```

### Example 2:
```
Input: word = "xyxzxe", ch = "z"
Output: "zxyxxe"
Explanation: The first and only occurrence of "z" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
```

### Example 3:
```
Input: word = "abcd", ch = "z"
Output: "abcd"
Explanation: "z" does not exist in word.
You should not do any reverse operation, the resulting string is "abcd".
```

## Constraints

- `1 <= word.length <= 250`
- `word` consists of lowercase English letters.
- `ch` is a lowercase English letter.

## Topics
- Two Pointers
- String

## Solution Approach

This solution uses a **Two-Pointer Reversal** technique:

### Algorithm

1. **Find the first occurrence** of `ch` using `indexOf()`
2. **If not found** (returns -1): Return original word unchanged
3. **Convert to char array** for in-place swapping
4. **Use two pointers:**
   - Left pointer starts at 0
   - Right pointer starts at index of `ch`
   - Swap characters and move pointers toward center
5. **Convert back to string** and return

### Why Convert to Char Array?

Strings are immutable in Java. Converting to char array allows efficient in-place swapping without creating intermediate strings.

**Time Complexity:** O(n) - indexOf is O(n), reversal is O(k) where k ≤ n
**Space Complexity:** O(n) - char array of length n

## Related Problems

- [344. Reverse String](https://leetcode.com/problems/reverse-string/) (Easy) - Basic two-pointer reversal
- [541. Reverse String II](https://leetcode.com/problems/reverse-string-ii/) (Easy) - Reverse with intervals
- [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) (Medium) - Word-level reversal
- [186. Reverse Words in a String II](https://leetcode.com/problems/reverse-words-in-a-string-ii/) (Medium) - In-place word reversal
- [557. Reverse Words in a String III](https://leetcode.com/problems/reverse-words-in-a-string-iii/) (Easy) - Reverse each word individually

|**Previous:** [Day 58](../Day_58/) | **Next:** [Day 60](../Day_60/)|
|---|---|

|**Date:** February 28, 2026|
|---|