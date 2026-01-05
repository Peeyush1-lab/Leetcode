# 🔄 Day 5 (Problem 2): Valid Palindrome

## 📋 Problem Statement

A phrase is a **palindrome** if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

## 💡 Examples

### Example 1: ✅
**Input:** `s = "A man, a plan, a canal: Panama"`
**Output:** `true`
**Explanation:**
- After processing: `"amanaplanacanalpanama"`
- Reads same forwards and backwards ✓

### Example 2: ❌
**Input:** `s = "race a car"`
**Output:** `false`
**Explanation:**
- After processing: `"raceacar"`
- Reversed: `"racaecar"`
- Not the same ✗

### Example 3: ✅
**Input:** `s = " "`
**Output:** `true`
**Explanation:**
- After removing non-alphanumeric: `""`
- Empty string is palindrome ✓

### Example 4: ✅
**Input:** `s = "0P"`
**Output:** `false`
**Explanation:**
- After processing: `"0p"`
- Reversed: `"p0"`
- Not the same ✗

## ⚠️ Constraints

- `1 <= s.length <= 2 * 10^5`
- `s` consists only of printable ASCII characters

## 🔑 Key Points

1. 🔤 Convert **uppercase to lowercase**
2. 🚫 Remove **all non-alphanumeric** characters (keep letters and digits)
3. 🔄 Check if string reads **same forwards and backwards**
4. ✨ Empty string after filtering is a palindrome
5. 🔢 **Numbers are alphanumeric** - keep them!
6. ⚡ Can optimize with two-pointer approach (O(1) space)

## 🛠️ Approach: Filter + Reverse

### 💡 Core Intuition
1. Build a **cleaned string** with only lowercase alphanumeric characters
2. Compare original cleaned string with its **reverse**

### 📋 Algorithm Steps
1. Create `StringBuilder` to collect valid characters
2. Iterate through original string:
   - If uppercase letter: convert to lowercase and add
   - If lowercase letter or digit: add as-is
   - Otherwise: skip
3. Convert StringBuilder to string
4. Reverse the StringBuilder
5. Compare both strings

### ⏱️ Complexity
- **Time:** O(n) - Single pass + reverse
- **Space:** O(n) - StringBuilder storage

## 🎨 Visual Example

```
Input: "A man, a plan, a canal: Panama"

Step 1: Process each character
'A' → 'a' ✓
' ' → skip
'm' → 'm' ✓
'a' → 'a' ✓
'n' → 'n' ✓
',' → skip
...

Result: "amanaplanacanalpanama"

Step 2: Reverse
"amanaplanacanalpanama"
         ⟷
"amanaplanacanalpanama"

Same! → true ✅
```