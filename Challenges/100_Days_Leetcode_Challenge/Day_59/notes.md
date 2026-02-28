# Notes - Reverse Prefix of Word

## Problem Overview

Reverse the prefix of a word from index 0 up to and including the first occurrence of a given character.

**Key points:**
- Only reverse up to **first occurrence** of `ch`
- If `ch` not found, return original word
- Reverse is **inclusive** (includes the character itself)

## The Two-Pointer Technique

This is a classic **two-pointer reversal** pattern:
```
Left pointer at start, right pointer at end
Swap and move toward center until they meet
```

**Visual:**
```
word = "abcdefd", ch = 'd'
First 'd' at index 3

Before: a b c d e f d
        ↑     ↑
        L     R

Swap 1: d b c a e f d
          ↑ ↑
          L R

Swap 2: d c b a e f d
            (L >= R, stop)

Result: "dcbaefd" ✓
```

## Step-by-Step Walkthrough

### Example 1: word = "abcdefd", ch = 'd'

```
Step 1: Find first 'd'
  idx = word.indexOf('d') = 3

Step 2: Check if found
  idx != -1, so continue

Step 3: Convert to char array
  arr = ['a', 'b', 'c', 'd', 'e', 'f', 'd']

Step 4: Two-pointer reversal
  Initial: l=0, r=3

  Iteration 1: l=0, r=3 (l < r? YES)
    temp = arr[0] = 'a'
    arr[0] = arr[3] = 'd'
    arr[3] = temp = 'a'
    arr = ['d', 'b', 'c', 'a', 'e', 'f', 'd']
    l++, r-- → l=1, r=2

  Iteration 2: l=1, r=2 (l < r? YES)
    temp = arr[1] = 'b'
    arr[1] = arr[2] = 'c'
    arr[2] = temp = 'b'
    arr = ['d', 'c', 'b', 'a', 'e', 'f', 'd']
    l++, r-- → l=2, r=1

  Iteration 3: l=2, r=1 (l < r? NO)
    Exit loop

Step 5: Convert to string
  Return "dcbaefd" ✅
```

### Example 2: word = "xyxzxe", ch = 'z'

```
Step 1: idx = 3

Step 2: idx != -1, continue

Step 3: arr = ['x', 'y', 'x', 'z', 'x', 'e']

Step 4: Reverse [0...3]
  l=0, r=3: swap 'x' and 'z' → ['z', 'y', 'x', 'x', 'x', 'e']
  l=1, r=2: swap 'y' and 'x' → ['z', 'x', 'y', 'x', 'x', 'e']
  l=2, r=1: l >= r, stop

Step 5: Return "zxyxxe" ✅
```

### Example 3: word = "abcd", ch = 'z'

```
Step 1: idx = word.indexOf('z') = -1

Step 2: idx == -1, return word unchanged

Return: "abcd" ✅
```

## Code Breakdown

```java
public String reversePrefix(String word, char ch) {
    // Step 1: Find first occurrence of ch
    int idx = word.indexOf(ch);

    // Step 2: If not found, return original word
    if (idx == -1) return word;

    // Step 3: Convert to char array (mutable)
    char[] arr = word.toCharArray();

    // Step 4: Two-pointer reversal
    for (int l = 0, r = idx; l < r; l++, r--) {
        // Standard swap using temp variable
        char temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    // Step 5: Convert back to string
    return new String(arr);
}
```

## Why Each Step is Necessary

### 1. `indexOf(ch)`

Built-in method to find first occurrence:
- Returns index if found
- Returns -1 if not found
- O(n) time complexity

**Alternative:**
```java
int idx = -1;
for (int i = 0; i < word.length(); i++) {
    if (word.charAt(i) == ch) {
        idx = i;
        break;
    }
}
```
Your solution is cleaner using `indexOf()`!

### 2. Early Return on -1

```java
if (idx == -1) return word;
```

Avoids unnecessary processing when character doesn't exist.

### 3. Convert to Char Array

```java
char[] arr = word.toCharArray();
```

**Why?** Strings are **immutable** in Java:
- Can't modify string directly
- Each modification creates new string (expensive)
- Char array allows in-place modification

### 4. Two-Pointer Loop

```java
for (int l = 0, r = idx; l < r; l++, r--)
```

**Elegant loop structure:**
- Initialize both pointers in one line
- Increment/decrement both in one line
- Continue while `l < r` (meet in middle)

### 5. Standard Swap

```java
char temp = arr[l];
arr[l] = arr[r];
arr[r] = temp;
```

Classic three-step swap using temporary variable.

### 6. Convert Back to String

```java
return new String(arr);
```

Create new String from char array.

## Why This Solution is Clean

### ✅ Efficient
- Single pass to find character: O(n)
- Single pass to reverse: O(idx)
- Total: O(n)

### ✅ Space Optimal
- O(n) space for char array (necessary for mutable operations)
- No extra data structures

### ✅ Readable
- Clear variable names (`l`, `r`, `idx`)
- Standard patterns (indexOf, two-pointer swap)
- Proper early return

### ✅ Handles Edge Cases
- Character not found → return original
- Character at start → just returns it
- Character at end → reverses entire string

## Edge Cases

### 1. Character Not Found
```java
Input: word = "abcd", ch = 'z'
Output: "abcd"
```
✅ Early return handles this

### 2. Character at Index 0
```java
Input: word = "abcd", ch = 'a'
Output: "abcd"
```
✅ Loop condition `l < r` (0 < 0) immediately false, no swap

### 3. Character at End
```java
Input: word = "abcd", ch = 'd'
Output: "dcba"
```
✅ Reverses entire string (0 to 3)

### 4. Single Character Word
```java
Input: word = "a", ch = 'a'
Output: "a"
```
✅ l=0, r=0, no swap needed

### 5. Two Same Characters
```java
Input: word = "abcabc", ch = 'c'
Output: "cbabc"
```
✅ Only reverses to **first** 'c' at index 2

### 6. All Same Characters
```java
Input: word = "aaaa", ch = 'a'
Output: "aaaa"
```
✅ Reverses but result looks same

## Common Mistakes

### ❌ Mistake 1: Not checking if character exists

```java
int idx = word.indexOf(ch);
char[] arr = word.toCharArray();
// Missing: if (idx == -1) return word;
for (int l = 0, r = idx; l < r; l++, r--)  // idx=-1 causes issues!
```

Without check, `r` would be -1, causing wrong behavior.

### ❌ Mistake 2: Using substring and concatenation

```java
String prefix = word.substring(0, idx + 1);
String suffix = word.substring(idx + 1);
return reverse(prefix) + suffix;  // Creates multiple strings!
```

Less efficient due to multiple string creations.

### ❌ Mistake 3: Wrong loop condition

```java
for (int l = 0, r = idx; l <= r; l++, r--)  // WRONG!
```

Using `<=` causes middle character to "swap with itself" unnecessarily (harmless but inefficient).

### ❌ Mistake 4: Modifying string directly

```java
// Can't do this in Java!
word.setCharAt(l, word.charAt(r));  // Strings are immutable!
```

Must use char array.

### ❌ Mistake 5: Forgetting to include ch in reversal

```java
for (int l = 0, r = idx - 1; l < r; l++, r--)  // WRONG! Excludes ch
```

Problem says **inclusive** - must include the character itself.

## Complexity Analysis

**Time Complexity:** O(n)
- `indexOf(ch)`: O(n) - worst case scans entire string
- `toCharArray()`: O(n) - creates array of size n
- Reversal loop: O(idx) ≤ O(n) - at most n/2 swaps
- `new String(arr)`: O(n) - creates string from array
- Total: O(n)

**Space Complexity:** O(n)
- `char[] arr`: O(n) - same size as input string
- All other variables: O(1)
- Total: O(n)

**Note:** Output string also takes O(n) space, but we typically don't count output in space complexity.

## Alternative Approaches

### Approach 1: Using StringBuilder

```java
public String reversePrefix(String word, char ch) {
    int idx = word.indexOf(ch);
    if (idx == -1) return word;

    StringBuilder sb = new StringBuilder(word.substring(0, idx + 1));
    return sb.reverse().toString() + word.substring(idx + 1);
}
```

**Pros:** More concise
**Cons:** Creates multiple string objects (less efficient)

### Approach 2: Manual Loop Without Two Pointers

```java
public String reversePrefix(String word, char ch) {
    int idx = word.indexOf(ch);
    if (idx == -1) return word;

    StringBuilder sb = new StringBuilder();
    for (int i = idx; i >= 0; i--) {
        sb.append(word.charAt(i));
    }
    sb.append(word.substring(idx + 1));
    return sb.toString();
}
```

**Pros:** No char array needed
**Cons:** StringBuilder operations, still O(n) space

**Your char array + two-pointer approach is optimal!**

## The Two-Pointer Pattern

This problem demonstrates the classic **two-pointer reversal** pattern:

**When to use:**
- Reversing arrays/strings
- Checking palindromes
- Finding pairs in sorted arrays
- Removing duplicates in-place

**Template:**
```java
for (int l = start, r = end; l < r; l++, r--) {
    swap(arr[l], arr[r]);
}
```

## Interview Tips

1. **Clarify the problem:**
   - "Reverse includes the character itself, correct?"
   - "If character appears multiple times, use first occurrence?"

2. **Mention the approach:**
   - "Use indexOf to find character"
   - "Two-pointer technique to reverse in-place"

3. **Explain why char array:**
   - "Strings are immutable in Java"
   - "Char array allows efficient in-place swapping"

4. **Walk through example:**
   - Show "abcdefd", 'd' → "dcbaefd"
   - Demonstrate pointer movements

5. **Discuss edge cases:**
   - Character not found
   - Character at start/end

6. **State complexity:** "O(n) time, O(n) space"

## Related Problems with Hyperlinks

- [344. Reverse String](https://leetcode.com/problems/reverse-string/) (Easy) - Foundation: reverse entire array
- [541. Reverse String II](https://leetcode.com/problems/reverse-string-ii/) (Easy) - Reverse in intervals
- [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) (Medium) - Word-level reversal
- [186. Reverse Words in a String II](https://leetcode.com/problems/reverse-words-in-a-string-ii/) (Medium) - In-place word reversal
- [557. Reverse Words in a String III](https://leetcode.com/problems/reverse-words-in-a-string-iii/) (Easy) - Reverse each word separately
- [345. Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string/) (Easy) - Selective two-pointer reversal

## Key Takeaways

1. **Two-pointer reversal** - Classic pattern for in-place reversal
2. **indexOf() for first occurrence** - Built-in method is clean and efficient
3. **Char array for mutability** - Strings are immutable, arrays aren't
4. **Early return optimization** - Check if character exists first
5. **Inclusive range** - Remember to include the target character
6. **O(n) time and space** - Optimal for this problem

Clean and efficient solution using the two-pointer pattern!