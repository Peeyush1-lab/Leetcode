
# 📝 Add Binary — Solution Notes

## 🔢 Problem Overview

Given two binary strings `a` and `b`, return their **sum as a binary string**.

### Example
```
Input:  a = "1010", b = "1011"
Output: "10101"
```

---

## 🎯 Approach Summary

| Metric | Value |
|------|------|
| **Approach** | Simulate Binary Addition |
| **Time Complexity** | O(n) |
| **Space Complexity** | O(n) |
| **Data Structure Used** | StringBuilder |
| **Pattern** | Two Pointers + Carry |

---

## 💡 Core Intuition

Binary addition works exactly like decimal addition:
- Add digits from **right to left**
- Maintain a **carry**
- Append result bits
- Reverse at the end

---

## 📋 Algorithm Steps

1. Start pointers from the end of both strings
2. Maintain a carry
3. Loop while digits or carry exist
4. Add bits and carry
5. Append `sum % 2`
6. Update carry
7. Reverse the result

---

## 🧩 Code Implementation

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1) {

            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }
}
```

---

## 🎨 Dry Run Example

Input:
```
a = "1010"
b = "1011"
```

Output:
```
10101
```

---

## ⏱️ Complexity Analysis

```
Time Complexity: O(n)
Space Complexity: O(n)
```

---

## ✅ Key Takeaways

- Binary addition simulation
- Carry management is key
- Efficient string handling
- Reverse at the end

---

## ✔️ Status

Problem Solved Successfully
Difficulty: Easy
Pattern: Binary Simulation