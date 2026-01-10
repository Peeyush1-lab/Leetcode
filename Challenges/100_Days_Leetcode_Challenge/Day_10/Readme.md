# 🔤 Day 10: Remove Duplicate Letters

## 📋 Problem Statement

Given a string `s`, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the **smallest in lexicographical order** among all possible results.

## 💡 Examples

### Example 1: ✅
**Input:** `s = "bcabc"`
**Output:** `"abc"`
**Explanation:** Remove one 'b' and one 'c' to get "abc" in smallest order

### Example 2: ✅
**Input:** `s = "cbacdcbc"`
**Output:** `"acdb"`
**Explanation:** Remove duplicates while maintaining smallest lexicographical order

## ⚠️ Constraints

- `1 <= s.length <= 10^4`
- `s` consists of lowercase English letters

## 🔑 Key Points

1. 🔤 Each letter appears **exactly once**
2. 📊 Result must be **lexicographically smallest**
3. 🎯 **Greedy + Stack** approach required
4. 💡 Can remove larger characters if they appear later
5. 📈 Track frequency to know if character appears again

## 🛠️ Approach: Stack + Greedy

### Core Strategy
- Use **frequency array** to know remaining occurrences
- Use **Stack** to build result (allows removal)
- Use **visited array** to track characters in result
- **Greedy decision**: Pop larger characters if they appear later

### Algorithm
1. Count frequency of each character
2. For each character:
   - Decrease its frequency
   - Skip if already in result
   - Pop larger characters from stack if they appear later
   - Push current character

### ⏱️ Complexity
- **Time:** O(n) - Single pass
- **Space:** O(1) - Fixed size arrays (26 letters)