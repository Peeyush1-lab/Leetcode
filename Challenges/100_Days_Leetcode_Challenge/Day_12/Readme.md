# Day 12: Add Binary

## Problem Statement

Given two binary strings `a` and `b`, return their sum as a binary string.

The strings contain only `0` and `1`, and the result should also be in binary format.

## Examples

### Example 1

**Input:**
`a = "11", b = "1"`
**Output:**
`"100"`

### Example 2

**Input:**
`a = "1010", b = "1011"`
**Output:**
`"10101"`

## Constraints

* `1 <= a.length, b.length <= 10^4`
* `a` and `b` consist only of `'0'` and `'1'`
* No leading zeros, except for `"0"` itself

## Key Points

* Binary addition works from **right to left**
* A **carry** is maintained during addition
* Result is built in reverse and then reversed at the end
* Efficient solution with **O(n)** time complexity

## Solution Status

**Status:** Solved

This solution correctly handles:

* Different string lengths
* Carry propagation
* Large binary numbers

## Difficulty

Easy

## Pattern

* String manipulation
* Math
* Simulation

## Learning

Even simple problems require careful handling of edge cases like carry and unequal lengths.
