# 📝 Solution Explanation & Problem-Solving Notes

## 🎯 Solution Breakdown

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n) |
| **Space Complexity** | O(1) - Fixed 26 size arrays |
| **Approach** | Stack + Greedy |
| **Difficulty** | 🟡 Medium |
| **Status** | ✅ Solved Completely |

---

## 💡 Core Intuition

**The Key Insight:** Use a **Stack** to build the result so we can remove characters when we find a better (smaller) option that allows us to add the removed character later!

### Three Essential Components
1. **Frequency array** - Know if character appears again
2. **Visited array** - Track what's already in result
3. **Stack** - Build result with ability to remove/undo

---

## 📋 Algorithm Walkthrough

### Step 1: Count Frequencies
```java
int[] freq = new int[26];
for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```
**Why?** We need to know if we'll see a character again later, so we can decide whether to keep or remove it.

### Step 2: Process Each Character
```java
for (char c : s.toCharArray()) {
    freq[c - 'a']--;  // Decrease remaining count

    if (visited[c - 'a'])  // Already in result
        continue;

    // Greedy removal logic...
}
```
- Decrease frequency (one less occurrence remaining)
- Skip if already added to result

### Step 3: Greedy Removal Decision
```java
while (!st.isEmpty() &&
       st.peek() > c &&
       freq[st.peek() - 'a'] > 0) {
    visited[st.pop() - 'a'] = false;
}
```

**The Magic Condition:** Remove top of stack if:
1. Stack not empty
2. Top character > current (we found smaller!)
3. Top character appears again later (can add it back!)

### Step 4: Add Current Character
```java
st.push(c);
visited[c - 'a'] = true;
```

### Step 5: Build Result
```java
StringBuilder sb = new StringBuilder();
for (char c : st)
    sb.append(c);
return sb.toString();
```

---

## 🎨 Visual Example: "cbacdcbc"

```
Initial: freq = {a:1, b:2, c:3, d:1}

───────────────────────────────────
Process 'c':
  freq[c] = 2 (remaining)
  Stack: [c]
  visited[c] = true

───────────────────────────────────
Process 'b':
  freq[b] = 1
  b < c AND freq[c] > 0 → Can remove 'c'!
  Pop 'c', visited[c] = false
  Stack: [b]
  visited[b] = true

───────────────────────────────────
Process 'a':
  freq[a] = 0
  a < b AND freq[b] > 0 → Remove 'b'!
  Pop 'b', visited[b] = false
  Stack: [a]
  visited[a] = true

───────────────────────────────────
Process 'c':
  freq[c] = 1
  c > a, don't remove 'a'
  Stack: [a, c]
  visited[c] = true

───────────────────────────────────
Process 'd':
  freq[d] = 0
  d > c, don't remove
  Stack: [a, c, d]
  visited[d] = true

───────────────────────────────────
Process 'c':
  visited[c] = true → Skip

───────────────────────────────────
Process 'b':
  freq[b] = 0
  b < d but freq[d] = 0 → Can't remove 'd'
  b < c but freq[c] = 0 → Can't remove 'c'
  Stack: [a, c, d, b]
  visited[b] = true

───────────────────────────────────
Process 'c':
  visited[c] = true → Skip

Result: "acdb" ✅
```

---

## 🎓 Key Learnings

### Why Stack?
- ✅ Allows **removal** of previously added characters
- ✅ LIFO structure perfect for greedy decisions
- ✅ Can "undo" when finding better option

### Why Frequency Array?
- ✅ Know if character appears again
- ✅ Safe to remove if `freq > 0` (will see it later)
- ✅ Enables greedy decision making

### Why Visited Array?
- ✅ Prevent duplicates
- ✅ Skip characters already in result
- ✅ O(1) lookup

---

## 💡 Key Takeaways

| # | Lesson |
|---|--------|
| 1️⃣ | 📚 **Stack enables undo** - Can remove previous decisions |
| 2️⃣ | 🎯 **Greedy + Future info** - Frequency tells if char appears later |
| 3️⃣ | ⚡ **Three arrays pattern** - freq, visited, stack |
| 4️⃣ | 🔄 **Lexicographical** requires greedy removal of larger chars |
| 5️⃣ | 💪 **O(n) possible** - Each char pushed/popped at most once |

---

## ⏱️ Complexity Analysis

```
┌─────────────────────────────────────────────────────────┐
│ Time Complexity:   O(n)                                 │
│   - First loop: O(n) for frequency count                │
│   - Second loop: O(n) iterations                        │
│   - Stack ops: Each char pushed/popped at most once     │
│   - Total: O(n)                                         │
│                                                         │
│ Space Complexity:  O(1)                                 │
│   - freq array: O(26) = O(1)                            │
│   - visited array: O(26) = O(1)                         │
│   - Stack: O(26) in worst case = O(1)                   │
│   - Only lowercase letters, fixed size!                 │
└─────────────────────────────────────────────────────────┘
```

---

## 🔗 Related Problems

| Problem | Pattern | Difficulty |
|---------|---------|-----------|
| Remove K Digits | Stack + Greedy | 🟡 Medium |
| Create Maximum Number | Similar stack logic | 🔴 Hard |
| Smallest Subsequence | Same pattern | 🟡 Medium |
| Next Greater Element | Monotonic stack | 🟢 Easy |

---

## 🌟 Pattern Recognition

**Monotonic Stack + Greedy** pattern appears when:
- ✅ Need to maintain order (increasing/decreasing)
- ✅ Can remove elements when better option comes
- ✅ Have future information (frequency, last index)
- ✅ Want lexicographically smallest/largest result

**Template:**
```java
Stack<Type> stack;
for (element in collection) {
    // Decrease frequency/count

    if (already_processed) continue;

    while (!stack.isEmpty() &&
           stack.peek() > element &&
           canRemoveLater(stack.peek())) {
        stack.pop();
    }

    stack.push(element);
}
```

---

<div align="center">

### ✅ Problem Solved Successfully!

**Time Taken:** ~30 minutes
**Attempts:** 1 ✨
**Key Skill:** Stack + Greedy with frequency tracking

**Difficulty:** 🟡 Medium | **Pattern:** Stack, Greedy, String

### 🏆 Complete Solution! 🏆

</div>

---

**Last Updated:** January 10, 2026