# Day 19 (Problem 1): Single Number

## Problem Statement

Given a **non-empty** array of integers `nums`, every element appears **twice** except for one. Find that single one.

You must implement a solution with a **linear runtime complexity** and use **only constant extra space**.

## Examples

### Example 1
**Input:** `nums = [2,2,1]`
**Output:** `1`

### Example 2
**Input:** `nums = [4,1,2,1,2]`
**Output:** `4`

### Example 3
**Input:** `nums = [1]`
**Output:** `1`

## Constraints

- `1 <= nums.length <= 3 * 10^4`
- `-3 * 10^4 <= nums[i] <= 3 * 10^4`
- Each element in the array appears twice except for one element which appears only once

## Key Points

- Every element appears exactly twice except one
- Must use O(1) space
- Must use O(n) time
- XOR operation is the key

## Approach

**Strategy:** Bit Manipulation with XOR

**XOR Properties:**
- `a ^ a = 0` (any number XOR itself = 0)
- `a ^ 0 = a` (any number XOR 0 = itself)
- XOR is commutative and associative

**Algorithm:**
- XOR all numbers together
- Duplicate pairs cancel out to 0
- Only single number remains

**Complexity:**
- Time: O(n)
- Space: O(1)