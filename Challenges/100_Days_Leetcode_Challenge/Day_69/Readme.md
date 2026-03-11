# 402. Remove K Digits

**Difficulty:** Medium
**Day:** 69
**Topics:** String, Stack, Greedy, Monotonic Stack

## Problem Description

Given string `num` representing a non-negative integer `num`, and an integer `k`, return the **smallest possible integer** after removing `k` digits from `num`.

## Examples

### Example 1:

**Input:**
```
num = "1432219", k = 3
```

**Output:**
```
"1219"
```

**Explanation:**
```
Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
```

### Example 2:

**Input:**
```
num = "10200", k = 1
```

**Output:**
```
"200"
```

**Explanation:**
```
Remove the leading 1 and the number is 200. Note that the output must not contain leading zeros.
```

### Example 3:

**Input:**
```
num = "10", k = 2
```

**Output:**
```
"0"
```

**Explanation:**
```
Remove all the digits from the number and it is left with nothing which is 0.
```

## Constraints

- `1 <= k <= num.length <= 10⁵`
- `num` consists of only digits.
- `num` does not have any leading zeros except for the zero itself.

## Topics

- String
- Stack
- Greedy
- Monotonic Stack

## Similar Problems

- [321. Create Maximum Number](https://leetcode.com/problems/create-maximum-number/) - Hard
- [316. Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/) - Medium
- [1081. Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/) - Medium
- [1673. Find the Most Competitive Subsequence](https://leetcode.com/problems/find-the-most-competitive-subsequence/) - Medium
- [2259. Remove Digit From Number to Maximize Result](https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/) - Easy

## Related Topics Problems

**Monotonic Stack:**
- [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) - Easy
- [503. Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) - Medium
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Medium
- [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Hard
- [907. Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) - Medium

**Greedy String Problems:**
- [179. Largest Number](https://leetcode.com/problems/largest-number/) - Medium
- [Remove All Adjacent Duplicates in String](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) - Easy
- [1578. Minimum Time to Make Rope Colorful](https://leetcode.com/problems/minimum-time-to-make-rope-colorful/) - Medium

**String Manipulation:**
- [43. Multiply Strings](https://leetcode.com/problems/multiply-strings/) - Medium
- [415. Add Strings](https://leetcode.com/problems/add-strings/) - Easy
- [67. Add Binary](https://leetcode.com/problems/add-binary/) - Easy

---

|**Previous:** [Day 68](../Day_68/) | **Next:** [Day 70](../Day_70/)|
|---|---|

|**Date:** March 10, 2026|
|---|