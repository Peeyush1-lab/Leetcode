# Day 15: Ransom Note

## Problem Statement

Given two strings `ransomNote` and `magazine`, return `true` if `ransomNote` can be constructed by using the letters from `magazine` and `false` otherwise.

Each letter in `magazine` can only be used once in `ransomNote`.

## Examples

### Example 1
**Input:** `ransomNote = "a"`, `magazine = "b"`
**Output:** `false`

### Example 2
**Input:** `ransomNote = "aa"`, `magazine = "ab"`
**Output:** `false`

### Example 3
**Input:** `ransomNote = "aa"`, `magazine = "aab"`
**Output:** `true`

## Constraints

- `1 <= ransomNote.length, magazine.length <= 10^5`
- `ransomNote` and `magazine` consist of lowercase English letters

## Key Points

- Each letter can only be used once
- Need to check if magazine has enough of each letter
- Frequency counting approach
- Array faster than HashMap for fixed alphabet size

## Approach

**Strategy:** Frequency Array
1. Count all letters in magazine
2. For each letter in ransomNote, check availability
3. Decrease count as we use letters
4. Return false if any letter unavailable

**Complexity:**
- Time: O(n + m) where n = magazine length, m = ransomNote length
- Space: O(1) - Fixed size array of 26