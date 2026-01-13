# 🔍 Day 13: Find the Index of the First Occurrence in a String

## 📋 Problem Statement

Given two strings `needle` and `haystack`, return the index of the **first occurrence** of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

## 💡 Examples

### Example 1: ✅
**Input:** `haystack = "sadbutsad"`, `needle = "sad"`
**Output:** `0`
**Explanation:** "sad" occurs at index 0 and 6. The first occurrence is at index 0.

### Example 2: ❌
**Input:** `haystack = "leetcode"`, `needle = "leeto"`
**Output:** `-1`
**Explanation:** "leeto" did not occur in "leetcode".

## ⚠️ Constraints

- `1 <= haystack.length, needle.length <= 10^4`
- `haystack` and `needle` consist of only lowercase English characters

## 🔑 Key Points

1. 🎯 Find **first occurrence** only
2. 📊 Return **index** or -1
3. 💡 Multiple valid approaches
4. ⚡ Substring method: clean and efficient

## 🛠️ Approaches

### Approach 1: Built-in Function ⚡
```java
return haystack.indexOf(needle);
```
- **Pros:** One line, clean
- **Cons:** Doesn't show algorithm understanding

### Approach 2: Substring (My Choice) ✅
- Check each possible starting position
- Use `substring()` to extract and compare
- **Time:** O(n × m) worst case
- **Space:** O(m) for substring

### Approach 3: KMP Algorithm (Advanced)
- Optimal O(n + m) time
- More complex implementation
- Used in production systems

## ⏱️ Complexity
- **Time:** O(n × m) - Substring comparison
- **Space:** O(m) - Substring creation