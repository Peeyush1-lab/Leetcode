# Day 27 - Detailed Notes


## Problem: Decode String

**LeetCode:** [#394](https://leetcode.com/problems/decode-string/) | **Difficulty:** Medium


### 📝 Problem Statement

Given an encoded string, return its decoded string.

The encoding rule is: `k[encoded_string]`, where the `encoded_string` inside the brackets is being repeated exactly `k` times.

**Note:**
- `k` is guaranteed to be a positive integer
- You may assume the input string is always valid (well-formed)
- No extra spaces, and brackets are well-matched


### 📊 Examples

#### Example 1
```
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Breakdown:
- "3[a]" → "aaa"
- "2[bc]" → "bcbc"
- Result: "aaabcbc"
```

#### Example 2
```
Input: s = "3[a2[c]]"
Output: "accaccacc"

Breakdown (inside-out):
- Inner: "2[c]" → "cc"
- Substitute: "3[acc]"
- Outer: "3[acc]" → "accaccacc"
```

#### Example 3
```
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Breakdown:
- "2[abc]" → "abcabc"
- "3[cd]" → "cdcdcd"
- "ef" → "ef"
- Result: "abcabccdcdcdef"
```

#### Example 4
```
Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
```


### 💡 Approach

**Strategy:** Recursive Descent Parsing with Class-Level Index

**Core Idea:**
- Use recursion to handle nested brackets naturally
- Maintain a global index `i` to track position across recursive calls
- Each `[` triggers a new recursive call (going deeper)
- Each `]` returns from current recursion (going back up)
- Build strings incrementally with StringBuilder

#### Why Recursion Works Perfectly Here

**Natural Mapping:**
```
Nested Brackets         Recursion Call Stack
---------------         --------------------
3[a2[c]]               decode() outer
  ↓                      ↓
  a2[c]                decode() inner (returns "acc")
    ↓                    ↓
    c                  decode() deepest (returns "cc")
```

**Stack vs Recursion Comparison:**
```
Stack Approach:         Recursion Approach:
- Manually push/pop     - Automatic call stack
- Track state in stack  - State in parameters
- More code             - More elegant
```

### 🔄 Algorithm Steps

1. **Initialize:**
   - Class-level index `i = 0` (shared across recursive calls)
   - Entry point: `decodeString()` calls `decode()`

2. **For each character at position `i`:**

   **Case 1: Digit (0-9)**
   - Accumulate multi-digit number: `num = num * 10 + digit`
   - Increment `i`

   **Case 2: Opening Bracket `[`**
   - Increment `i` to skip `[`
   - Recursively call `decode()` to get inner string
   - Append inner string `num` times to result
   - Reset `num = 0`

   **Case 3: Closing Bracket `]`**
   - Increment `i` to skip `]`
   - **Return current result** (unwind recursion)

   **Case 4: Letter (a-z, A-Z)**
   - Append character to result
   - Increment `i`

3. **Return built string**


### 🔍 Key Implementation Details

#### Class-Level Index
```java
int i = 0;  // Shared across all recursive calls
```
**Why this works:**
- Single traversal of the string
- Each recursive call continues from where previous left off
- No need to pass/return index separately

#### Multi-Digit Number Handling
```java
int num = 0;
if (Character.isDigit(c)) {
    num = num * 10 + (c - '0');  // Build number digit by digit
    i++;
}
```
**Example:** For "123[abc]"
- First '1': num = 0×10 + 1 = 1
- Then '2': num = 1×10 + 2 = 12
- Then '3': num = 12×10 + 3 = 123 ✓

#### Recursive Call on `[`
```java
else if (c == '[') {
    i++;                          // Skip the '['
    String inner = decode(s);     // Recursive call
    for (int k = 0; k < num; k++)
        res.append(inner);        // Repeat inner string
    num = 0;                      // Reset for next number
}
```
**What happens:**
1. Skip opening bracket
2. Recursive call builds the substring until matching `]`
3. Repeat that substring `num` times
4. Reset counter

#### Early Return on `]`
```java
else if (c == ']') {
    i++;                    // Skip the ']'
    return res.toString();  // Return to caller
}
```
**Why this is elegant:**
- Naturally handles nested depth
- Each recursion level returns its own substring
- Caller decides how many times to repeat it

#### Character Handling
```java
else {
    res.append(c);  // Regular character
    i++;
}
```

### ⚙️ Complexity Analysis

- **Time Complexity:** O(n × k)
  - n = length of input string
  - k = maximum repeat count
  - Worst case: deeply nested with large k values
  - Example: "10[10[10[a]]]" processes 'a' 1000 times

- **Space Complexity:** O(n)
  - Recursion stack depth: O(d) where d = max nesting depth
  - StringBuilder storage: O(n) for result
  - Total: O(n + d) ≈ O(n)

**Detailed Example:**
```
Input: "3[a2[c]]"

Recursion calls:
1. decode() outer:
   - Sees '3', then '[', calls decode()

2. decode() inner:
   - Sees 'a', appends 'a'
   - Sees '2', then '[', calls decode()

3. decode() deepest:
   - Sees 'c', appends 'c'
   - Sees ']', returns "c"

2. decode() inner (continued):
   - Gets "c", repeats 2 times → "cc"
   - Appends to get "acc"
   - Sees ']', returns "acc"

1. decode() outer (continued):
   - Gets "acc", repeats 3 times → "accaccacc"
   - Returns "accaccacc"
```

### 🎯 Key Insights

1. **Class-Level Index is Crucial:**
   - Enables all recursive calls to share position
   - Avoids complex parameter passing
   - Single pass through string

2. **Recursion Matches Problem Structure:**
   - Brackets create hierarchical structure
   - Recursion naturally handles hierarchy
   - Call stack = bracket nesting

3. **Early Return Pattern:**
   - Returning on `]` elegantly unwinds recursion
   - Each level knows when its scope ends
   - No need for complex end-of-scope tracking

4. **StringBuilder Efficiency:**
   - String concatenation in loop is O(n²)
   - StringBuilder is O(n) for repeated appends
   - Critical for performance with large repeat counts

5. **Number Accumulation:**
   - Can't assume single-digit numbers
   - Must build number character by character
   - `num * 10 + digit` pattern is standard

---

### 🐛 Edge Cases Handled

| Case | Example | Behavior |
|------|---------|----------|
| No brackets | `"abc"` | Returns as-is |
| Multi-digit number | `"100[a]"` | Correctly builds 100 |
| Nested brackets | `"2[a2[b]]"` | Recursion handles depth |
| Multiple segments | `"2[abc]3[cd]"` | Processes sequentially |
| Letters outside brackets | `"a2[b]c"` | Appends normally |
| Deep nesting | `"2[2[2[a]]]"` | Stack handles depth |


### 🔄 Problems Encountered & Solutions

#### Problem 1: Understanding Index Management
**Issue:** Initially confused about how `i` is shared across recursive calls.

**Confusion:**
- Should `i` be local to each function?
- How does each recursive call know where to start?

**Solution:**
- Class-level `i` acts as global position pointer
- Each recursive call continues from current position
- When recursion returns, outer call continues from updated `i`

**Example walkthrough:**
```
String: "3[a]"
       ^
       i=0

Outer decode():
- Sees '3' at i=0, num=3, i=1
- Sees '[' at i=1, i=2, calls inner decode()

Inner decode():
- Sees 'a' at i=2, appends, i=3
- Sees ']' at i=3, i=4, returns "a"

Outer decode():
- Continues with i=4 (updated by inner call!)
- Repeats "a" 3 times → "aaa"
```


#### Problem 2: Multi-Digit Number Parsing
**Issue:** Initially tried to handle only single-digit numbers.

**Wrong approach:**
```java
int num = c - '0';  // ❌ Only works for single digit
```

**Correct approach:**
```java
int num = 0;
while (Character.isDigit(c)) {
    num = num * 10 + (c - '0');  // ✓ Accumulates digits
    i++;
    c = s.charAt(i);
}
```

**Why needed:** Problems like "100[a]" require proper multi-digit handling.


#### Problem 3: When to Reset `num`
**Issue:** Forgot to reset `num` after using it, causing incorrect repetitions.

**Bug:**
```java
for (int k = 0; k < num; k++)
    res.append(inner);
// Forgot: num = 0;  ❌
```

**Result:** Next bracket reuses old `num` value

**Fix:**
```java
for (int k = 0; k < num; k++)
    res.append(inner);
num = 0;  // ✓ Reset for next use
```


#### Problem 4: StringBuilder vs String Concatenation
**Issue:** Initially used `String result = ""` with `+=` operator.

**Performance problem:**
```java
String result = "";
for (int k = 0; k < num; k++) {
    result += inner;  // ❌ Creates new string each time: O(n²)
}
```

**Optimized:**
```java
StringBuilder res = new StringBuilder();
for (int k = 0; k < num; k++) {
    res.append(inner);  // ✓ Modifies in-place: O(n)
}
```

**Impact:** For "100[abc]", StringBuilder is ~100x faster.


#### Problem 5: Handling `]` Return Logic
**Issue:** Initially tried to track bracket matching manually.

**Complex approach:**
```java
// Track opening brackets, match with closing... ❌ Complicated
```

**Elegant solution:**
```java
else if (c == ']') {
    i++;
    return res.toString();  // ✓ Let recursion handle it
}
```

**Why better:** Recursion naturally tracks scope depth.


### 🎓 Alternative Approaches

#### Approach 2: Stack-Based Solution
```java
// Pseudo-code
Stack<Integer> numStack = new Stack<>();
Stack<StringBuilder> strStack = new Stack<>();
StringBuilder current = new StringBuilder();
int num = 0;

for (char c : s.toCharArray()) {
    if (isDigit(c)) {
        num = num * 10 + (c - '0');
    } else if (c == '[') {
        numStack.push(num);
        strStack.push(current);
        current = new StringBuilder();
        num = 0;
    } else if (c == ']') {
        StringBuilder temp = strStack.pop();
        int repeat = numStack.pop();
        for (int i = 0; i < repeat; i++) {
            temp.append(current);
        }
        current = temp;
    } else {
        current.append(c);
    }
}
```

**Comparison:**
- **Stack:** Explicit state management, more code
- **Recursion:** Implicit stack, cleaner code
- **Complexity:** Both O(n × k) time, O(n) space

#### Which is Better?
**Recursion (your approach):**
- ✅ Cleaner, more readable
- ✅ Naturally matches problem structure
- ⚠️ Stack overflow risk for very deep nesting (rare)

**Explicit Stack:**
- ✅ No recursion depth limit
- ⚠️ More code, more complex
- ⚠️ Manual state management

**Verdict:** For interview settings, recursion is preferred for elegance.


### 🎓 Pattern Recognition

**This problem teaches:**
- **Recursive descent parsing** - standard compiler technique
- **Global state management** in recursion
- **Nested structure traversal** with early returns
- **Bracket matching** through call stack

**Similar recursive parsing problems:**
- **Basic Calculator (#224):** Parse arithmetic expressions
- **Decode Ways (#91):** Recursive decoding combinations
- **Different Ways to Add Parentheses (#241):** Parse with operators
- **Ternary Expression Parser (#439):** Nested ternary parsing

**When to use this pattern:**
- Nested structures (brackets, parentheses)
- Hierarchical data parsing
- Expression evaluation
- Tree-like string representations


### 💡 Real-World Applications

**Where this technique is used:**
1. **Compilers:** Parsing nested expressions and blocks
2. **JSON/XML Parsing:** Handling nested objects/tags
3. **Template Engines:** Processing nested template syntax
4. **Config Files:** Parsing hierarchical configurations
5. **Markup Languages:** HTML/Markdown parsers


## 📝 Daily Reflection

### ✅ What Went Well
- Elegant recursive solution with minimal code
- Correctly handled class-level index sharing
- Properly accumulated multi-digit numbers
- Used StringBuilder for efficiency
- Early return on `]` is clean and effective

### 💡 Key Realizations Today

**Recursion vs Iteration:**
- Some problems have natural recursive structure
- Brackets/nesting → think recursion
- Call stack mirrors problem structure

**State Management:**
- Class-level variables can simplify recursion
- Shared index avoids complex parameter passing
- Trust the recursion to manage scope

**Performance Details Matter:**
- StringBuilder vs String concatenation
- O(n) vs O(n²) for repeated string building
- Critical when k is large


### 🧩 Pattern Recognition Progress

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 22 | Build Array | Medium | Array Simulation |
| 23 | Max Eaten Apples | Medium | Greedy + Min-Heap |
| 24 | Kth Largest | Medium | Top K with Heap |
| 25 | Stock Strategy | Medium | Prefix Sum |
| 26 | Transpose Matrix | Easy | Matrix Manipulation |
| 27 | Decode String | Medium | Recursion + Parsing |

**Skills Mastered:**
- ✅ Data structures (Heap, Queue, Stack)
- ✅ Optimization techniques (Prefix Sum, Heap size K)
- ✅ Matrix operations (Transpose)
- ✅ Recursive parsing (NEW!)

**Emerging Expertise:**
- Choosing between recursion and iteration
- Managing state in recursive solutions
- Performance optimization (StringBuilder)


### 🎯 Tomorrow's Focus
- Explore more recursive problems
- Try stack-based alternative for comparison
- Practice expression evaluation problems
- Consider dynamic programming next


### 💭 Questions to Explore
1. When should I use explicit stack vs recursion?
2. How to handle very deep nesting without stack overflow?
3. Can we optimize space further?
4. How does this relate to formal parsing theory?


### 🔥 Progress Metrics
- **6-day streak** achieved! 🎉🎉🎉
- **1 easy problem** solved
- **6 medium problems** solved
- **Recursion pattern** added to toolkit
- **Diverse problem types** practiced


### 🌟 Today's Breakthrough
Recursion isn't just for tree traversal! It's a powerful pattern for any hierarchical or nested structure. The key insight: **Let the call stack mirror the problem structure**. When you see nested brackets, think recursive descent parsing.

---

**Last Updated:** January 27, 2026