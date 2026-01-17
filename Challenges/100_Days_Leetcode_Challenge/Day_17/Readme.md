# Day 17: Majority Element II

## Problem Statement

Given an integer array of size `n`, find all elements that appear more than `⌊n/3⌋` times.

## Examples

### Example 1
**Input:** `nums = [3,2,3]`
**Output:** `[3]`

### Example 2
**Input:** `nums = [1]`
**Output:** `[1]`

### Example 3
**Input:** `nums = [1,2]`
**Output:** `[1,2]`

## Constraints

- `1 <= nums.length <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`

## Follow-up

Could you solve the problem in linear time and in O(1) space?

## Key Points

- Find elements appearing > n/3 times
- At most 2 elements can satisfy this condition
- HashMap approach: O(n) time, O(n) space
- Optimal: Boyer-Moore Voting (O(n) time, O(1) space)

## Approach

**Strategy:** HashMap Frequency Counting

1. Count frequency of each element
2. Check which elements exceed n/3 threshold
3. Return list of majority elements

**Complexity:**
- Time: O(n)
- Space: O(n) for HashMap