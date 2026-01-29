# Day 29 - Detailed Notes


## Problem: Length of Last Word

**LeetCode:** [#58](https://leetcode.com/problems/length-of-last-word/) | **Difficulty:** Easy


### 📝 Problem Statement

Given a string `s` consisting of words and spaces, return the length of the **last** word in the string.

A **word** is a maximal substring consisting of non-space characters only.

**Constraints:**
- `1 <= s.length <= 10^4`
- `s` consists of only English letters and spaces `' '`
- There is at least one word in `s`


### 📊 Examples

#### Example 1
```
Input: s = "Hello World"
Output: 5

Explanation: Last word is "World" with length 5
```

#### Example 2
```
Input: s = "   fly me   to   the moon  "
Output: 4

Explanation: Last word is "moon" with length 4
Note: Trailing spaces are ignored
```

#### Example 3
```
Input: s = "luffy is still joyboy"
Output: 6

Explanation: Last word is "joyboy" with length 6
```


### 💡 Approach

**Strategy:** Reverse Traversal with State Tracking

**Core Idea:**
- Start from the end of the string
- Skip trailing spaces
- Once a non-space character is found, start counting
- Stop at the first space after the word starts

#### Why Reverse Traversal?

**Forward traversal issues:**
```
"Hello World   "
- Must process entire string
- Need to track last word separately
- More complex logic
```

**Reverse traversal benefits:**
```
"Hello World   "
          ↑ start here
- Skip trailing spaces immediately
- Count the last word directly
- Stop early when word ends
```


### 🔄 Algorithm Steps

1. **Initialize Variables:**
   - `i = s.length() - 1` (start from end)
   - `count = 0` (word length counter)
   - `j = 0` (flag: 0 = word not started, 1 = word started)

2. **Traverse from Right to Left:**

   **For each character:**

   **Case A: Space character**
   - If `j == 1` (word already started):
     - Break (we've reached the start of last word)
   - If `j == 0` (word not started):
     - Reset `count = 0` (skip trailing spaces)

   **Case B: Non-space character**
   - Set `j = 1` (mark word as started)
   - Increment `count`

   **Continue:** Move to previous character (`i--`)

3. **Return count**


### 🔍 Key Implementation Details

#### Starting Position
```java
int i = s.length() - 1;
```
- Start from last character
- Enables early termination

#### State Flag (`j`)
```java
int j = 0;  // 0 = word not found yet, 1 = word found
```
**Purpose:**
- Distinguishes between trailing spaces and word boundary
- Prevents counting trailing spaces as breaks

**Example:**
```
"World   "
      ^^^
      Trailing spaces (j=0) → skip

"Hello World"
     ^
     Space after word found (j=1) → break
```

#### Space Handling Logic
```java
if (s.charAt(i) == ' ') {
    if (j == 1) {
        break;  // Word ended, we're done
    }
    count = 0;  // Still in trailing spaces
}
```

**Two scenarios:**
1. **Before word (`j == 0`):** Reset count (ignore trailing spaces)
2. **After word (`j == 1`):** Break immediately (word boundary found)

#### Character Counting
```java
else {
    j = 1;      // Mark that word has started
    count++;    // Count this character
}
```

**Visual flow:**
```
"moon  "
    ^  j=0, count=0
   ^   j=1, count=1 (n)
  ^    j=1, count=2 (o)
 ^     j=1, count=3 (o)
^      j=1, count=4 (m)
       Stop: count=4 ✓
```


### ⚙️ Complexity Analysis

- **Time Complexity:** O(n)
  - Worst case: traverse entire string
  - Best case: O(length_last_word + trailing_spaces)
  - Average: depends on last word position

- **Space Complexity:** O(1)
  - Only three integer variables: `i`, `count`, `j`
  - No additional data structures

**Best vs Worst Case:**

| Case | Example | Operations |
|------|---------|-----------|
| Best | `"Hello World"` | ~5 iterations |
| Worst | `"a b c d e"` | Full string scan |
| Trailing spaces | `"Word   "` | word_length + spaces |


### 🎯 Key Insights

1. **Reverse Traversal is Natural:**
   - Problem asks for "last" word
   - Starting from end is most intuitive
   - Enables early termination

2. **State Flag Elegance:**
   - Single boolean-like flag (`j`)
   - Distinguishes two types of spaces
   - Cleaner than multiple conditions

3. **Single Pass Solution:**
   - Don't need to find all words
   - Don't need to store anything
   - Minimal memory footprint

4. **No String Methods Needed:**
   - No `trim()` needed
   - No `split()` needed
   - Direct character access only

5. **Break Optimization:**
   - Stop as soon as word boundary found
   - Don't process entire string unnecessarily
   - Especially helpful for strings with many words


### 🐛 Edge Cases Handled

| Case | Example | Behavior |
|------|---------|----------|
| Trailing spaces | `"word   "` | j flag skips them |
| Single word | `"hello"` | Works normally |
| Multiple spaces | `"a   b"` | Finds 'b' correctly |
| Leading spaces | `"  hello"` | Finds entire word |
| Single character | `"a"` | Returns 1 |
| Word at start | `"first second"` | Finds "second" |


### 🔄 Problems Encountered & Solutions

#### Problem 1: Understanding the State Flag
**Issue:** Initially unclear why `j` variable is needed.

**Confusion:**
- Why not just count backwards until first space?
- What's the difference between different spaces?

**Realization:**
```
"World   "
      ^^^ These spaces should NOT stop counting
     ^    This space should NOT exist (no previous word)

"Hello World   "
     ^    This space SHOULD stop counting
      ^^^ These spaces should be skipped
```

**Solution:** `j` distinguishes between:
- Trailing spaces (before word starts) → reset count
- Word boundary (after word starts) → break


#### Problem 2: Reset vs Break Logic
**Issue:** Debated when to reset `count` vs when to break.

**Wrong approach:**
```java
if (s.charAt(i) == ' ') {
    break;  // ❌ Stops at trailing spaces!
}
```

**Correct approach:**
```java
if (s.charAt(i) == ' ') {
    if (j == 1) {
        break;  // ✓ Only break after word found
    }
    count = 0;  // ✓ Reset during trailing spaces
}
```


#### Problem 3: When to Set `j = 1`
**Issue:** Considered setting `j` only on first non-space.

**Wrong logic:**
```java
if (j == 0 && s.charAt(i) != ' ') {
    j = 1;  // Only set once
}
count++;  // ❌ Always count?
```

**Correct logic:**
```java
else {
    j = 1;      // Set on every non-space
    count++;    // Count each character
}
```

**Why correct:** Setting `j` every time ensures it stays 1 throughout the word.


#### Problem 4: Initial Index
**Issue:** Considered starting from `s.length()` instead of `s.length() - 1`.

**Wrong:**
```java
int i = s.length();  // ❌ Out of bounds!
```

**Correct:**
```java
int i = s.length() - 1;  // ✓ Last valid index
```

**Remember:** String indices are 0-based, so last index is `length - 1`.


### 🎓 Alternative Approaches

#### Approach 2: Using trim() and split()
```java
public int lengthOfLastWord(String s) {
    s = s.trim();                    // Remove trailing spaces
    String[] words = s.split(" ");   // Split by spaces
    return words[words.length - 1].length();
}
```

**Pros:**
- Very concise (3 lines)
- Easy to understand

**Cons:**
- Creates array of all words (O(n) space)
- `split()` processes entire string
- Less efficient for long strings

#### Approach 3: Reverse without state flag
```java
public int lengthOfLastWord(String s) {
    int i = s.length() - 1;

    // Skip trailing spaces
    while (i >= 0 && s.charAt(i) == ' ') {
        i--;
    }

    // Count the word
    int count = 0;
    while (i >= 0 && s.charAt(i) != ' ') {
        count++;
        i--;
    }

    return count;
}
```

**Pros:**
- No state flag needed
- Two clear phases: skip then count
- Very readable

**Cons:**
- Two separate loops
- Slightly more code

#### Approach 4: Forward traversal (less efficient)
```java
public int lengthOfLastWord(String s) {
    int lastWordLength = 0;
    int currentWordLength = 0;

    for (char c : s.toCharArray()) {
        if (c == ' ') {
            if (currentWordLength > 0) {
                lastWordLength = currentWordLength;
                currentWordLength = 0;
            }
        } else {
            currentWordLength++;
        }
    }

    return currentWordLength > 0 ? currentWordLength : lastWordLength;
}
```

**Pros:**
- Forward iteration (more common pattern)

**Cons:**
- Must process entire string
- More variables to track
- More complex logic


### 📊 Approach Comparison

| Approach | Time | Space | Code Length | Readability |
|----------|------|-------|-------------|-------------|
| Your solution | O(n) | O(1) | Medium | Good |
| trim() + split() | O(n) | O(n) | Short | Excellent |
| Two loops (reverse) | O(n) | O(1) | Medium | Excellent |
| Forward traversal | O(n) | O(1) | Long | Moderate |

**Verdict:** Your single-loop reverse approach with state flag is a good balance of efficiency and conciseness.


### 🎓 Pattern Recognition

**This problem teaches:**
- **Reverse traversal** for "last element" queries
- **State tracking** with minimal flags
- **Early termination** optimization
- **Space-efficient** string processing

**Similar pattern problems:**
- **Valid Palindrome (#125):** Two-pointer string traversal
- **Reverse Words in a String (#151):** Word-level string manipulation
- **Find First and Last Position (#34):** Binary search from ends
- **Trapping Rain Water (#42):** Two-pointer from ends

**When to use reverse traversal:**
- Finding last occurrence of something
- Processing from end is more natural
- Early termination is possible
- Don't need to process entire data structure


### 💡 Real-World Applications

**Where this technique is used:**
1. **Text Editors:** Finding last word for autocomplete
2. **Log Processing:** Reading most recent entries first
3. **File Systems:** Getting filename from full path
4. **Command Line:** Parsing last argument
5. **Data Validation:** Checking trailing whitespace


## 📝 Daily Reflection

### ✅ What Went Well
- Clean reverse traversal implementation
- Elegant state flag usage
- Single-pass solution
- Handled trailing spaces without explicit trimming
- Early break optimization

### 💡 Key Realizations Today

**Back to Fundamentals After Week 1:**
- Week 1 ended with complex recursion
- Week 2 starts with string basics
- Reinforces that fundamentals matter

**Simple Problems, Important Patterns:**
- Reverse traversal is underutilized
- State flags simplify complex conditions
- Sometimes simple is better than clever

**Space Efficiency:**
- O(1) space is achievable for most string problems
- Don't always need auxiliary data structures
- Direct character access is powerful


### 🧩 Pattern Recognition Progress

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 22 | Build Array | Medium | Array Simulation |
| 23 | Max Eaten Apples | Medium | Greedy + Min-Heap |
| 24 | Kth Largest | Medium | Top K with Heap |
| 25 | Stock Strategy | Medium | Prefix Sum |
| 26 | Transpose Matrix | Easy | Matrix Manipulation |
| 27 | Decode String | Medium | Recursion + Parsing |
| 28 | Find Kth Bit | Medium | Mathematical Recursion |
| 29 | Length of Last Word | Easy | String Traversal |


**Skills Reinforced:**
- ✅ String manipulation
- ✅ State management
- ✅ Edge case handling
- ✅ Space optimization


### 🎯 Tomorrow's Focus
- Continue with string/array problems
- Consider two-pointer techniques
- Explore sliding window pattern
- Maybe return to medium difficulty

### 💭 Questions to Explore
1. When is reverse traversal better than forward?
2. How to generalize state flag pattern?
3. What other problems benefit from early termination?
4. How does this relate to two-pointer technique?


### 🌟 Today's Insight
Don't underestimate "easy" problems! They often teach fundamental patterns that apply to harder problems. Reverse traversal from today is used in many medium/hard problems like Trapping Rain Water and Container With Most Water.

**Keeping fundamentals sharp while tackling complex problems = Sustainable growth** 📈


**Last Updated:** January 29, 2026