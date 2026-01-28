# Day 28 - Detailed Notes

## Problem: Find Kth Bit in Nth Binary String

**LeetCode:** [#1545](https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/) | **Difficulty:** Medium


### 📝 Problem Statement

Given two positive integers `n` and `k`, the binary string `Sn` is formed as follows:

- `S1 = "0"`
- `Si = Si-1 + "1" + reverse(invert(Si-1))` for `i > 1`

Where:
- `+` denotes concatenation
- `reverse(x)` returns the reversed string
- `invert(x)` inverts all bits (0 → 1, 1 → 0)

**Task:** Return the k-th bit in `Sn` (1-indexed).

**Constraints:**
- `1 <= n <= 20`
- `1 <= k <= 2^n - 1`


### 📊 Examples

#### Example 1
```
Input: n = 3, k = 1
Output: "0"

Explanation:
S1 = "0"
S2 = "0" + "1" + "1" = "011"
S3 = "011" + "1" + "100" = "0111001"
     ^
The 1st bit is "0"
```

#### Example 2
```
Input: n = 4, k = 11
Output: "1"

Explanation:
S1 = "0"
S2 = "011"
S3 = "0111001"
S4 = "011100110110001"
              ^
The 11th bit is "1"
```


### 🔍 Understanding the Pattern

#### Building Strings Step by Step

```
S1 = "0"
Length = 1 = 2^1 - 1

S2 = S1 + "1" + reverse(invert(S1))
   = "0" + "1" + reverse(invert("0"))
   = "0" + "1" + reverse("1")
   = "0" + "1" + "1"
   = "011"
Length = 3 = 2^2 - 1

S3 = S2 + "1" + reverse(invert(S2))
   = "011" + "1" + reverse(invert("011"))
   = "011" + "1" + reverse("100")
   = "011" + "1" + "001"
   = "0111001"
Length = 7 = 2^3 - 1

S4 = S3 + "1" + reverse(invert(S3))
   = "0111001" + "1" + reverse(invert("0111001"))
   = "0111001" + "1" + reverse("1000110")
   = "0111001" + "1" + "0110001"
   = "011100110110001"
Length = 15 = 2^4 - 1
```

#### Pattern Recognition

**Length formula:** `len(Sn) = 2^n - 1`

**Structure of Sn:**
```
Sn = [    Sn-1    ] + "1" + [ reverse(invert(Sn-1)) ]
     └─ length: 2^(n-1) - 1 ─┘        └─ length: 2^(n-1) - 1 ─┘

Total: (2^(n-1) - 1) + 1 + (2^(n-1) - 1) = 2^n - 1
```

**Key insight:** Position `2^(n-1)` is always '1' (the middle element)

---

### 💡 Approach

**Strategy:** Recursive Pattern Recognition Without String Construction

**Core Idea:**
- Don't build the entire string (would be exponentially large!)
- Use the recursive structure to determine the bit at position k
- Three cases based on where k falls:
  1. **Left half:** Same as bit k in Sn-1
  2. **Middle:** Always '1'
  3. **Right half:** Inverted mirror of corresponding position in left half

#### Why This Works

**Visual Structure:**
```
Sn = [ Left half (Sn-1) ] + "1" + [ Right half (reversed inverted Sn-1) ]
     └─── positions 1 to mid-1 ───┘  └──── positions mid+1 to 2^n-1 ────┘
                    middle = 2^(n-1)
```

**Mapping right half to left half:**
- Position `k` in right half corresponds to position `len - k` in left half
- But inverted (0 ↔ 1)


### 🔄 Algorithm Steps

1. **Base Case:**
   - If `n = 1`, return '0' (S1 = "0")

2. **Calculate Length:**
   - `len = 2^n` (using bit shift: `1 << n`)

3. **Determine Position:**

   **Case A: k < len/2 (Left half)**
   - Return `findKthBit(n-1, k)`
   - Same position in the previous string

   **Case B: k == len/2 (Middle)**
   - Return '1'
   - Middle element is always '1'

   **Case C: k > len/2 (Right half)**
   - Find corresponding position in left half: `len - k`
   - Recursively get that bit: `findKthBit(n-1, len - k)`
   - **Invert the result** (0 → 1, 1 → 0)


### 🔍 Key Implementation Details

#### Base Case
```java
if (n == 1)
    return '0';
```
- S1 = "0", only one bit

#### Length Calculation
```java
int len = 1 << n;  // Same as 2^n
```
- **Bit shift optimization:** `1 << n` computes `2^n` efficiently
- Example: `1 << 3` = `0001` << 3 = `1000` = 8

**Why use 2^n instead of 2^n - 1?**
- Actual length is `2^n - 1`
- But using `2^n` simplifies middle calculation: `len/2 = 2^(n-1)`
- We only compare positions, so the extra 1 doesn't matter

#### Left Half (Recursive)
```java
if (k < len / 2) {
    return findKthBit(n - 1, k);
}
```
- Position k is in the left half (which is Sn-1)
- Same bit at same position in previous string

#### Middle Element
```java
else if (k == len / 2) {
    return '1';
}
```
- Middle position is always '1' by definition
- `len/2 = 2^(n-1)` is the middle of `2^n - 1` length string

#### Right Half (Mirror and Invert)
```java
else {
    char correspondingBit = findKthBit(n - 1, len - k);
    return (correspondingBit == '0') ? '1' : '0';
}
```
- Find mirror position in left half: `len - k`
- Get bit at that position recursively
- **Invert the result**

### 🎯 Detailed Example Walkthrough

#### Example: n = 4, k = 11

```
S4 = "011100110110001" (length 15)
      ^         ^
      1        11

findKthBit(4, 11):
├─ len = 16, mid = 8
├─ k=11 > 8 (right half)
├─ Mirror position: 16 - 11 = 5
└─ Call findKthBit(3, 5) and invert

  findKthBit(3, 5):
  ├─ len = 8, mid = 4
  ├─ k=5 > 4 (right half)
  ├─ Mirror position: 8 - 5 = 3
  └─ Call findKthBit(2, 3) and invert

    findKthBit(2, 3):
    ├─ len = 4, mid = 2
    ├─ k=3 > 2 (right half)
    ├─ Mirror position: 4 - 3 = 1
    └─ Call findKthBit(1, 1) and invert

      findKthBit(1, 1):
      └─ Base case: return '0'

    ← Returns '0', invert to '1'

  ← Returns '1', invert to '0'

← Returns '0', invert to '1'

Final answer: '1' ✓
```


### ⚙️ Complexity Analysis

- **Time Complexity:** O(n)
  - Maximum recursion depth is n
  - Each recursive call does O(1) work
  - Path from root to leaf is at most n calls

- **Space Complexity:** O(n)
  - Recursion call stack depth
  - No additional data structures used

**Comparison with brute force:**

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| Build String | O(2^n) | O(2^n) | Actually construct Sn |
| Your Solution | O(n) | O(n) | Pattern recognition only |

**Example n=20:**
- Brute force: ~1 million operations, 1MB memory
- Your solution: 20 operations, ~200 bytes stack


### 🎯 Key Insights

1. **Avoid Materialization:**
   - Never need to build the actual string
   - Pattern recognition is sufficient
   - Exponential space → Linear space

2. **Bit Shift for Powers of 2:**
   - `1 << n` computes `2^n` in O(1)
   - More efficient than `Math.pow(2, n)`
   - Common optimization in competitive programming

3. **Middle Element Property:**
   - Always '1' at position `2^(n-1)`
   - Serves as natural dividing point
   - Simplifies the three-case logic

4. **Mirror and Invert:**
   - Right half is transformed left half
   - Position mapping: `k` → `len - k`
   - Then invert the bit value

5. **Divide and Conquer:**
   - Problem size halves each recursion
   - Similar to binary search structure
   - O(log n) effective work per level


### 🐛 Edge Cases Handled

| Case | Example | Behavior |
|------|---------|----------|
| Base case n=1 | `n=1, k=1` | Returns '0' |
| First position | `n=3, k=1` | Returns '0' (always) |
| Middle position | `n=3, k=4` | Returns '1' (always) |
| Last position | `n=3, k=7` | Mirror of k=1, inverted |
| Large n | `n=20, k=1000` | Still O(20) time |
| Maximum k | `n=20, k=2^20-1` | Handles efficiently |


### 🔄 Problems Encountered & Solutions

#### Problem 1: Understanding the Recursive Structure
**Issue:** Initially confused about how Sn relates to Sn-1.

**Confusion:**
- How does reversing and inverting create the pattern?
- Where does the middle '1' come from?

**Solution:**
- Drew out S1, S2, S3 manually
- Recognized three-part structure: left + "1" + right
- Right half is deterministic transformation of left

---

#### Problem 2: Position Mapping in Right Half
**Issue:** Struggled with finding the corresponding position in left half.

**Initial wrong attempts:**
```java
// Wrong: len - k - 1  ❌
// Wrong: k - len/2    ❌
// Wrong: len/2 - (k - len/2)  ❌
```

**Correct formula:**
```java
len - k  ✓
```

**Why it works:**
```
For S3 = "0111001" (len=8 in calculation)
Position: 1 2 3 4 5 6 7
String:   0 1 1 1 0 0 1

k=5 in right half corresponds to:
len - k = 8 - 5 = 3 in left half

Position 3 is '1', inverted to '0' → matches position 5! ✓
```

---

#### Problem 3: When to Invert
**Issue:** Initially inverted for left half cases too.

**Wrong logic:**
```java
if (k < len / 2) {
    char bit = findKthBit(n - 1, k);
    return invert(bit);  // ❌ Don't invert left half!
}
```

**Correct logic:**
- **Left half:** Same as Sn-1, no inversion
- **Middle:** Always '1', no inversion
- **Right half:** Only here we invert

---

#### Problem 4: Off-by-One in Length Calculation
**Issue:** Used `2^n - 1` initially, causing middle calculation issues.

**Problematic:**
```java
int len = (1 << n) - 1;  // Actual length
int mid = (len + 1) / 2; // Complicated middle calculation
```

**Better (your approach):**
```java
int len = 1 << n;        // One more than actual
int mid = len / 2;       // Clean middle calculation
```

**Insight:** Using `2^n` simplifies comparisons without affecting correctness.


#### Problem 5: Ternary Operator for Inversion
**Issue:** Initially wrote verbose if-else for bit inversion.

**Verbose:**
```java
if (correspondingBit == '0') {
    return '1';
} else {
    return '0';
}
```

**Concise (your code):**
```java
return (correspondingBit == '0') ? '1' : '0';
```

**Even more concise alternative:**
```java
return (char)('0' + '1' - correspondingBit);
// '0' + '1' = 97 (char value sum)
// If bit is '0' (48): 97 - 48 = 49 ('1')
// If bit is '1' (49): 97 - 49 = 48 ('0')
```


### 🎓 Alternative Approaches

#### Approach 2: Iterative with Pattern Tracking
```java
// Track transformations iteratively
public char findKthBit(int n, int k) {
    boolean inverted = false;

    while (n > 1) {
        int len = 1 << n;
        int mid = len / 2;

        if (k == mid) {
            return inverted ? '0' : '1';
        } else if (k > mid) {
            k = len - k;
            inverted = !inverted;
        }
        n--;
    }

    return inverted ? '1' : '0';
}
```
- Avoids recursion stack
- Tracks inversion state iteratively
- Same O(n) time and space

#### Approach 3: Build String (Brute Force)
```java
// Don't use this - exponential!
public char findKthBit(int n, int k) {
    String s = "0";
    for (int i = 2; i <= n; i++) {
        String inverted = invert(s);
        String reversed = new StringBuilder(inverted).reverse().toString();
        s = s + "1" + reversed;
    }
    return s.charAt(k - 1);
}
```
- O(2^n) time and space
- Only works for small n (n ≤ 15)
- Simple but inefficient

#### Which is Best?
**Recursive (your approach):**
- ✅ Clean, elegant code
- ✅ Natural problem decomposition
- ✅ Easy to understand the logic

**Iterative:**
- ✅ No stack overflow risk
- ✅ Slightly more efficient (no function call overhead)
- ⚠️ Less intuitive

**Verdict:** For interviews and clarity, recursive is preferred.

### 🎓 Pattern Recognition

**This problem teaches:**
- **Recursive pattern analysis** without constructing data
- **Space optimization** from exponential to linear
- **Bit manipulation** for powers of 2
- **Mirror symmetry** in recursive structures

**Similar pattern problems:**
- **K-th Symbol in Grammar (#779):** Almost identical pattern
- **Gray Code (#89):** Recursive bit pattern generation
- **Pow(x, n) (#50):** Divide and conquer recursion
- **Count Complete Tree Nodes (#222):** Binary tree level patterns

**When to use this pattern:**
- Recursive string/array construction rules
- Pattern repeats with transformations
- Exponential size, but query needs O(1) or O(log n)
- Mathematical sequences with recursive definitions


### 💡 Mathematical Connection

**Relation to Gray Code:**
The string construction is related to reflected binary Gray code:
```
n=1: 0
n=2: 0, 1, 1     (reflect and prefix with 1)
n=3: 0,1,1, 1, 1,0,0  (reflect and invert)
```

**Relation to Binary Counting:**
- The middle '1' acts as a separator
- Left half: original binary patterns
- Right half: complement of reflected patterns


### 🎓 Real-World Applications

**Where this technique is used:**
1. **Fractals:** Self-similar patterns (Sierpinski triangle)
2. **Data Compression:** Recursive encoding schemes
3. **Error Correction:** Reed-Solomon codes
4. **Computer Graphics:** Recursive subdivision surfaces
5. **Cryptography:** Bit permutation and inversion


## 📝 Daily Reflection

### ✅ What Went Well
- Avoided exponential space by never building string
- Clean recursive structure with three clear cases
- Efficient bit shift for power of 2 calculation
- Correct mirror position mapping
- Proper inversion only in right half case

### 💡 Key Realizations Today

**Pattern Over Materialization:**
- Don't always need to build the data structure
- Pattern recognition can answer queries directly
- Exponential → Linear transformation possible

**Recursion Efficiency:**
- Day 27: Recursion for parsing nested structures
- Day 28: Recursion for mathematical patterns
- Both avoid building intermediate data

**Bit Operations:**
- `1 << n` is cleaner than `Math.pow(2, n)`
- Bit shift is O(1) and returns int (no casting needed)


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

**Recursion Mastery Progress:**
- Day 27: ✅ State-based recursion (index sharing)
- Day 28: ✅ Pattern-based recursion (no state needed)
- **Next:** Backtracking, Tree recursion, DP with memoization

### 💭 Questions to Explore
1. Can this be solved with bit manipulation only (no recursion)?
2. How does this relate to fractal generation?
3. What's the mathematical formula for any position without recursion?
4. How to generalize to different transformation rules?


**Patterns Mastered This Week:**
1. Array manipulation and simulation
2. Heap-based optimization (Top K)
3. Prefix sum techniques
4. Matrix operations
5. Recursive parsing (state management)
6. Mathematical recursion (pattern recognition)



**Last Updated:** January 28, 2026