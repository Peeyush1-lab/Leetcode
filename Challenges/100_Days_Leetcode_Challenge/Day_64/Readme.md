# 1475. Final Prices With a Special Discount in a Shop

**Difficulty:** Easy
**Day:** 64
**Topics:** Array, Stack, Monotonic Stack

## Problem Description

You are given an integer array `prices` where `prices[i]` is the price of the `ith` item in a shop.

There is a special discount for items in the shop. If you buy the `ith` item, then you will receive a discount equivalent to `prices[j]` where `j` is the **minimum index** such that `j > i` and `prices[j] <= prices[i]`. Otherwise, you will not receive any discount at all.

Return an integer array `answer` where `answer[i]` is the final price you will pay for the `ith` item of the shop, **considering the special discount**.

## Examples

### Example 1:

**Input:**
```
prices = [8,4,6,2,3]
```

**Output:**
```
[4,2,4,2,3]
```

**Explanation:**
```
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
For item 3 with price[3]=2 you will receive a discount equivalent to prices[4]=3, therefore, the final price you will pay is 2 - 3 = -1? No! Since prices[4]=3 is not less than or equal to prices[3]=2, you will not receive any discount for item 3.
Actually for items 3 and 4 you will not receive any discount at all.
```

### Example 2:

**Input:**
```
prices = [1,2,3,4,5]
```

**Output:**
```
[1,2,3,4,5]
```

**Explanation:**
```
In this case, for all items, you will not receive any discount at all.
```

### Example 3:

**Input:**
```
prices = [10,1,1,6]
```

**Output:**
```
[9,0,1,6]
```

**Explanation:**
```
For item 0 with price[0]=10 you will receive a discount equivalent to prices[1]=1, therefore, the final price is 10 - 1 = 9.
For item 1 with price[1]=1 you will receive a discount equivalent to prices[2]=1, therefore, the final price is 1 - 1 = 0.
For item 2 with price[2]=1 you will not receive any discount because prices[3]=6 > prices[2]=1.
For item 3 with price[3]=6 you will not receive any discount at all.
```

## Constraints

- `1 <= prices.length <= 500`
- `1 <= prices[i] <= 1000`

## Topics

- Array
- Stack
- Monotonic Stack

## Similar Problems

- [496. Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) - Easy
- [503. Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) - Medium
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Medium
- [901. Online Stock Span](https://leetcode.com/problems/online-stock-span/) - Medium
- [1019. Next Greater Node In Linked List](https://leetcode.com/problems/next-greater-node-in-linked-list/) - Medium
- [2104. Sum of Subarray Ranges](https://leetcode.com/problems/sum-of-subarray-ranges/) - Medium

## Related Topics Problems

**Monotonic Stack:**
- [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) - Hard
- [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Hard
- [85. Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/) - Hard
- [456. 132 Pattern](https://leetcode.com/problems/132-pattern/) - Medium
- [907. Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) - Medium

**Array Processing:**
- [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) - Medium
- [1389. Create Target Array in the Given Order](https://leetcode.com/problems/create-target-array-in-the-given-order/) - Easy

---

|**Previous:** [Day 63](../Day_63/) | **Next:** [Day 65](../Day_65/)|
|---|---|

|**Date:** March 05, 2026|
|---|