# Solution Explanation & Problem-Solving Notes

## Solution Breakdown

| Metric | Value |
|--------|-------|
| Time Complexity | O(n + m) |
| Space Complexity | O(1) |
| Approach | Frequency Array |
| Time Taken | ~12 minutes |

---

## Core Algorithm

### Strategy: Frequency Counting

The problem asks: **Can we build ransomNote using letters from magazine?**

Key insight: Use a frequency array to track available letters.

```
Step 1: Count all letters in magazine
Step 2: Check if ransomNote can be built
Step 3: Decrease count as we use each letter
```

---

## Code Walkthrough

### Part 1: Count Magazine Letters
```java
int[] freq = new int[26];

for(char c : magazine.toCharArray()) {
    freq[c - 'a']++;
}
```

**What this does:**
- Create array for 26 lowercase letters
- Count frequency of each letter in magazine
- `c - 'a'` maps 'a'→0, 'b'→1, ..., 'z'→25

**Example:**
```
magazine = "aab"
freq[0] = 2  (letter 'a')
freq[1] = 1  (letter 'b')
freq[2-25] = 0
```

### Part 2: Check RansomNote Construction
```java
for(char c : ransomNote.toCharArray()) {
    if(freq[c - 'a'] == 0) {
        return false;
    }
    freq[c - 'a']--;
}
```

**What this does:**
- For each letter needed in ransomNote
- Check if available in magazine (freq > 0)
- If not available, return false immediately
- Otherwise, use it (decrease count)

**Example:**
```
ransomNote = "aa"
magazine = "aab"

Check 'a': freq[0]=2 > 0, use it → freq[0]=1
Check 'a': freq[0]=1 > 0, use it → freq[0]=0
Success!
```

### Part 3: Return Success
```java
return true;
```

If we made it through all letters, we can construct the note.

---

## Visual Example

### Example 1: Success Case
```
ransomNote = "aa"
magazine = "aab"

Step 1: Count magazine
freq: [2, 1, 0, 0, ..., 0]
       a  b

Step 2: Check ransomNote
- Need 'a': freq[a]=2, use it → freq[a]=1
- Need 'a': freq[a]=1, use it → freq[a]=0

Result: true (all letters available)
```

### Example 2: Failure Case
```
ransomNote = "aa"
magazine = "ab"

Step 1: Count magazine
freq: [1, 1, 0, 0, ..., 0]
       a  b

Step 2: Check ransomNote
- Need 'a': freq[a]=1, use it → freq[a]=0
- Need 'a': freq[a]=0 → Not available!

Result: false (insufficient 'a')
```

---

## Alternative Approaches

### Approach 1: Frequency Array (Current)
**Time:** O(n + m)
**Space:** O(1)

Pros:
- Fast - O(1) array access
- Space efficient - fixed size
- Clean implementation

Cons:
- Only works for lowercase letters
- Slight overkill for sparse data

### Approach 2: HashMap
```java
Map<Character, Integer> freq = new HashMap<>();
for(char c : magazine.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
// Similar checking logic
```

**Time:** O(n + m)
**Space:** O(k) where k = unique characters

Pros:
- Works for any characters
- More flexible

Cons:
- HashMap overhead
- Slower than array access

### Approach 3: Sort and Compare
```java
// Sort both strings and match
char[] m = magazine.toCharArray();
char[] r = ransomNote.toCharArray();
Arrays.sort(m);
Arrays.sort(r);
// Check if r is subsequence of m
```

**Time:** O(n log n + m log m)
**Space:** O(1) or O(n + m) depending on sort

Pros:
- No extra data structure

Cons:
- Slower due to sorting
- More complex logic

---

## Key Insights

### Why Frequency Array Works
1. **Fixed alphabet** - Only 26 letters, perfect for array
2. **O(1) access** - Array index is fastest lookup
3. **Space efficient** - Fixed size regardless of input
4. **Early exit** - Return false immediately when letter unavailable

### The ASCII Trick
```java
freq[c - 'a']
```

**Character to index mapping:**
- 'a' - 'a' = 0
- 'b' - 'a' = 1
- 'z' - 'a' = 25

This maps each character to array index.

### Early Return Optimization
```java
if(freq[c - 'a'] == 0) {
    return false;  // Stop immediately
}
```

No need to check remaining letters once we know it's impossible.

---

## What Went Right

- Clean, efficient solution
- Correct edge case handling
- Optimal time complexity
- Space-efficient approach
- Good variable naming

---

## Complexity Analysis

```
Time Complexity: O(n + m)
- First loop: O(n) where n = magazine.length
- Second loop: O(m) where m = ransomNote.length
- Total: O(n + m)

Space Complexity: O(1)
- freq array: O(26) = O(1) constant space
- No additional data structures
- Independent of input size
```

**Best Case:** O(m) - Early exit on first unavailable letter
**Worst Case:** O(n + m) - All letters available

---

## Key Takeaways

**Lessons Learned:**
1. Frequency array efficient for fixed alphabets
2. ASCII arithmetic for character mapping
3. Early return saves unnecessary work
4. O(1) space possible with array approach
5. Two-pass solution cleaner than one-pass

**Pattern Recognition:**
- Character frequency counting
- Array as hash map for limited range
- Early exit optimization
- Two-pass algorithm

---

## Related Problems

- Valid Anagram - Similar frequency counting
- Find All Anagrams - Sliding window + frequency
- Group Anagrams - Frequency as key
- Longest Palindrome - Character frequency

---

## Summary

**Problem Solved Successfully**

Time: ~20 minutes
Attempts: 1
Issues: 0

**Difficulty:** Easy | **Pattern:** Array, Hash Table, String

**Key Achievement:** Clean frequency array solution with optimal complexity

---

**Last Updated:** January 15, 2026