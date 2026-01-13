# 📝 Solution Explanation & Problem-Solving Notes

## 🎯 Solution Breakdown

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n × m) |
| **Space Complexity** | O(m) |
| **Approach** | Substring sliding window |
| **Time Taken** | ~15 minutes |

---

## 💡 Core Algorithm

### Your Approach: Substring Comparison

```java
for (int i = 0; i <= n - m; i++) {
    if (haystack.substring(i, i + m).equals(needle)) {
        return i;
    }
}
```

**Strategy:**
- Try each possible starting position
- Extract substring of needle length
- Compare with needle
- Return first match

---

## 📋 Step-by-Step Logic

### 1. Calculate Lengths
```java
int n = haystack.length();  // Total string length
int m = needle.length();    // Pattern length
```

### 2. Loop Through Valid Positions
```java
for (int i = 0; i <= n - m; i++)
```
**Why `n - m`?**
- If `i > n - m`, not enough characters left
- Example: haystack = "abc" (n=3), needle = "bc" (m=2)
  - Can check positions: 0, 1 (3-2=1)
  - Position 2 would be out of bounds

### 3. Extract and Compare
```java
haystack.substring(i, i + m).equals(needle)
```
- `substring(i, i+m)` creates new string from index i to i+m
- `.equals()` compares content

### 4. Return on Match
```java
return i;  // First occurrence index
```

### 5. Not Found
```java
return -1;  // After checking all positions
```

---

## Example

```
haystack = "sadbutsad"
needle = "sad"

i=0: "sad" == "sad" ✓ → Return 0 ✅

(Don't need to check i=1,2,3... because found!)
```

```
haystack = "leetcode"
needle = "leeto"

i=0: "leetc" != "leeto" ✗
i=1: "eetco" != "leeto" ✗
i=2: "etcod" != "leeto" ✗
i=3: "tcode" != "leeto" ✗
(i=4 would be 4 > 8-5, stop)

Return -1 ❌
```

---

## 🔄 Alternative Approaches

### Approach 1: Built-in indexOf()
```java
return haystack.indexOf(needle);
```
**Pros:** ✅ One line, clean
**Cons:** ❌ Doesn't show algorithm understanding

### Approach 2: Substring (Your Choice) ✅
**Pros:**
- ✅ Shows understanding
- ✅ Clean and readable
- ✅ Good for interviews

**Cons:**
- ⚠️ Creates new string objects
- ⚠️ O(n × m) time

### Approach 3: Character-by-Character
```java
for (int i = 0; i <= n - m; i++) {
    int j;
    for (j = 0; j < m; j++) {
        if (haystack.charAt(i + j) != needle.charAt(j))
            break;
    }
    if (j == m) return i;
}
```
**Pros:** ✅ No substring creation
**Cons:** ❌ More complex

### Approach 4: KMP Algorithm (Advanced)
- **Time:** O(n + m) - Optimal!
- **Space:** O(m) - Pattern preprocessing
- Used in production string matching

---

## ✅ What Went Right

- ✅ **Clean solution** - Easy to understand
- ✅ **Correct logic** - Handles all cases
- ✅ **Good choice** - Substring is interview-appropriate
- ✅ **Edge case handling** - Loop boundary correct

---

## 💡 Key Takeaways

| # | Lesson |
|---|--------|
| 1️⃣ | 🎯 **Loop boundary** - `i <= n - m` prevents overflow |
| 2️⃣ | 📊 **Substring method** - Clean for interviews |
| 3️⃣ | ⚡ **Early return** - Stop at first match |
| 4️⃣ | 🔍 **Multiple approaches** - Know trade-offs |

---

## ⏱️ Complexity Analysis

```
┌─────────────────────────────────────────────────────────┐
│ Time Complexity:   O(n × m)                             │
│   - Outer loop: n - m + 1 iterations                    │
│   - substring(): O(m) to create                         │
│   - equals(): O(m) to compare                           │
│   - Total: O((n-m) × m) ≈ O(n × m)                      │
│                                                         │
│ Space Complexity:  O(m)                                 │
│   - substring() creates new string of length m          │
│   - Temporary string for comparison                     │
│                                                         │
│ Best Case: O(m) - Match at first position               │
│ Worst Case: O(n × m) - No match or match at end         │
└─────────────────────────────────────────────────────────┘
```

---

## 🎓 Comparison Table

| Approach | Time | Space | Interview Score | Production Use |
|----------|------|-------|----------------|----------------|
| indexOf() | O(n×m) | O(1) | ⭐⭐ | ✅ |
| Substring | O(n×m) | O(m) | ⭐⭐⭐⭐ | ✅ |
| Char-by-char | O(n×m) | O(1) | ⭐⭐⭐ | ✅ |
| KMP | O(n+m) | O(m) | ⭐⭐⭐⭐⭐ | ✅✅ |

**Your choice (Substring):** Great for interviews! Shows understanding without being too complex.

---

## 🔗 Related Problems

- **Implement strStr()** - Same problem
- **Repeated Substring Pattern** - Pattern matching
- **KMP Algorithm** - Optimal string matching
- **Rabin-Karp** - Hash-based matching

---

<div align="center">

### ✅ Problem Solved Successfully!

**Time:** ~25 minutes
**Issues:** 0 🎉
**Approach:** Clean substring solution with good interview balance!

**Difficulty:** 🟢 Easy | **Pattern:** String, Sliding Window

</div>

---

**Last Updated:** January 13, 2026