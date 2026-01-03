# 📝 Solution Explanation & Problem-Solving Notes

## 🎯 Solution Breakdown

### 📊 Algorithm Overview

| Metric | Value |
|--------|-------|
| **Time Complexity** | O(n log log n) |
| **Space Complexity** | O(n) |
| **Algorithm** | Sieve of Eratosthenes |
| **Pattern** | Number Theory, Array Manipulation |

### 🔄 Step-by-Step Logic

| Step | Action | Description |
|------|--------|-------------|
| 1 | Initialize Array | Create boolean array of size n+1 |
| 2 | Mark Primes | Set all indices ≥ 2 as true (assume prime) |
| 3 | Outer Loop | For i from 2 to √n |
| 4 | Check Prime | If i is still marked prime |
| 5 | Mark Multiples | Mark all multiples of i starting from i² |
| 6 | Count Primes | Count all remaining true values |
| 7 | Return | Return the count |

### 💻 Code Walkthrough

| Code Section | Purpose | Key Point |
|--------------|---------|-----------|
| `boolean isPrime[] = new boolean[n+1];` | Storage | Extra space to avoid index issues |
| `for(int i = 2; i < n; i++) isPrime[i] = true;` | Initialize | Assume all ≥ 2 are prime |
| `for(int i = 2; i*i < n; i++)` | Optimization | Only check up to √n |
| `if(isPrime[i])` | Check prime | Only process if still marked prime |
| `for(int j = i*i; j < n; j+=i)` | Mark multiples | Start from i², increment by i |
| `isPrime[j] = false;` | Eliminate | Mark as composite |
| `for(int i = 0; i < n; i++)` | Count loop | Iterate through all |
| `if(isPrime[i]) count++;` | Accumulate | Count remaining primes |

---

## ⚠️ Problems Encountered & Solutions

### ❌ Problem 1: Understanding Sieve of Eratosthenes

| Aspect | Details |
|--------|---------|
| **Issue** | Never heard of this algorithm before |
| **First Attempt** | Tried checking each number individually for primality |
| **Naive Approach** | For each n, check if divisible by 2 to √n |
| **Problem** | O(n × √n) = Too slow for n = 5×10^6 |
| **Research** | Discovered Sieve of Eratosthenes |
| **Understanding** | Instead of checking each, eliminate composites |
| **Key Insight** | Mark multiples of primes as composite |
| **Result** | ✅ Much more efficient algorithm |

**Naive approach (TLE - Time Limit Exceeded):**
```java
// O(n × √n) - Too slow!
int count = 0;
for(int i = 2; i < n; i++) {
    if(isPrime(i)) count++;
}

boolean isPrime(int num) {
    for(int i = 2; i*i <= num; i++) {
        if(num % i == 0) return false;
    }
    return true;
}
```

---

### ❌ Problem 2: Why Start from i² (i*i)?

| Aspect | Details |
|--------|---------|
| **Question** | Why `j = i*i` instead of `j = i*2`? |
| **Confusion** | Isn't i×2 the first multiple? |
| **Example** | For i=5: multiples are 10,15,20,25,30... |
| **Key Insight** | Smaller multiples already marked! |
| **Analysis** | 5×2=10 already marked by i=2 |
| | 5×3=15 already marked by i=3 |
| | 5×4=20 already marked by i=2 |
| | 5×5=25 is first new composite! |
| **Optimization** | Start from i² to avoid redundant work |
| **Result** | ✅ Significant performance improvement |

**Visual for i=5:**
```
Already marked by previous primes:
  5×2 = 10  (marked by 2)
  5×3 = 15  (marked by 3)
  5×4 = 20  (marked by 2)

First to mark:
  5×5 = 25  ← Start here!
```

---

### ❌ Problem 3: Loop Condition - Why i*i < n?

| Aspect | Details |
|--------|---------|
| **Initial Code** | Used `i < n` in outer loop |
| **Problem** | Unnecessary iterations for large i |
| **Example** | For n=100, checking i=50 wastes time |
| **Mathematical Fact** | Any composite n has factor ≤ √n |
| **Proof** | If n = a×b and both > √n, then a×b > n ❌ |
| **Optimization** | Only need to check up to √n |
| **Implementation** | `i*i < n` instead of `i < sqrt(n)` |
| **Benefit** | Avoid expensive sqrt() calculation |
| **Result** | ✅ Faster without losing correctness |

**Why i×i < n works:**
```
n = 100
√n = 10

Checking i=11:
  11×11 = 121 > 100 ✗ No need to check!

All composites < 100 already found by i ≤ 10
```

---

### ❌ Problem 4: Array Size and Index Confusion

| Aspect | Details |
|--------|---------|
| **Issue** | Should array be size n or n+1? |
| **First Attempt** | `boolean isPrime[] = new boolean[n];` |
| **Problem** | Index n-1 represents n-1, not n |
| **Confusion** | Want isPrime[5] to represent number 5 |
| **Solution** | Use size n+1 for cleaner indexing |
| **Benefit** | `isPrime[i]` directly represents number i |
| **Alternative** | Could use size n with offset logic |
| **Decision** | ✓ Size n+1 for clarity |

**Comparison:**
```java
// Size n - confusing
boolean[] isPrime = new boolean[n];
// isPrime[5] represents number 5, but max index is n-1

// Size n+1 - clear
boolean[] isPrime = new boolean[n+1];
// isPrime[5] represents number 5, works perfectly
```

---

### ❌ Problem 5: Understanding Time Complexity

| Aspect | Details |
|--------|---------|
| **Question** | Why O(n log log n) and not O(n²)? |
| **Analysis** | Nested loops don't mean O(n²)! |
| **Inner Loop Runs** | n/2 + n/3 + n/5 + ... + n/p |
| **Harmonic Series** | Sum of 1/p for primes p ≈ log log n |
| **Total Operations** | n × (1/2 + 1/3 + 1/5 + ...) |
| **Mathematical Result** | ≈ n log log n |
| **Intuition** | Each number marked at most once |
| **Comparison** | Much better than O(n√n) naive |
| **Result** | ✅ One of most efficient prime algorithms |

---

## 🔀 Alternative Approaches Considered

| Approach | Time | Space | Pros | Cons | Decision |
|----------|------|-------|------|------|----------|
| **Sieve of Eratosthenes** | O(n log log n) | O(n) | Very efficient | Needs memory | ✅ **Chosen** |
| **Trial Division** | O(n√n) | O(1) | No extra space | Too slow | ❌ TLE for large n |
| **Sieve of Atkin** | O(n/log log n) | O(n) | Slightly faster | More complex | ❌ Overkill |
| **Segmented Sieve** | O(n log log n) | O(√n) | Less memory | More complex | ❌ Not needed |
| **Miller-Rabin** | O(k log³ n) | O(1) | Fast for single | Not for range | ❌ Wrong tool |

---

## 💡 Key Takeaways

| # | Lesson | Application |
|---|--------|-------------|
| 1️⃣ | **🔢 Sieve of Eratosthenes** | Efficient prime generation for ranges |
| 2️⃣ | **⚡ Start from i²** | Previous multiples already eliminated |
| 3️⃣ | **📐 Check up to √n** | Any composite has factor ≤ √n |
| 4️⃣ | **🎯 Array indexing clarity** | Size n+1 for direct index mapping |
| 5️⃣ | **⏱️ Complexity analysis** | Nested loops ≠ always O(n²) |
| 6️⃣ | **📚 Classical algorithms** | Some problems need known algorithms |

---

## ⏱️ Complexity Analysis Summary

```
┌─────────────────────────────────────────────────────────┐
│ Time Complexity:   O(n log log n)                       │
│   - Sieve of Eratosthenes standard complexity          │
│   - Harmonic series of primes                          │
│                                                          │
│ Space Complexity:  O(n)                                 │
│   - Boolean array of size n+1                          │
│                                                          │
│ For n = 5×10^6:                                         │
│   - Time: ≈ 5×10^6 × log log(5×10^6) ≈ 10^7 ops       │
│   - Space: ≈ 5MB                                        │
│                                                          │
│ Comparison to Naive O(n√n):                             │
│   - Naive: ≈ 5×10^6 × 2236 ≈ 10^10 ops (1000x slower!) │
└─────────────────────────────────────────────────────────┘
```

---

## 🎓 Learning Moments

### Before This Problem
- ❓ Only knew trial division for checking primes
- ❓ Didn't know about Sieve of Eratosthenes
- ❓ Would've written O(n√n) solution
- ❓ Didn't understand √n optimization

### After This Problem
- ✅ Learned classical Sieve of Eratosthenes algorithm
- ✅ Understood why starting from i² is optimal
- ✅ Grasped the √n boundary for composite factors
- ✅ Appreciated ancient algorithms still relevant today
- ✅ Recognized when research/learning new algorithm is needed
- ✅ Understood that nested loops can be sub-quadratic

---

## 🎨 Detailed Visual Trace (n = 20)

```
Step 1: Initialize (all ≥ 2 are true)
Index:  0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19
Prime: [F  F  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T]

Step 2: i=2, mark multiples of 2 (4,6,8,10,12,14,16,18)
Prime: [F  F  T  T  F  T  F  T  F  T  F  T  F  T  F  T  F  T  F  T]

Step 3: i=3, mark multiples of 3 starting from 9 (9,12,15,18)
Prime: [F  F  T  T  F  T  F  T  F  F  F  T  F  T  F  F  F  T  F  T]

Step 4: i=4, skip (already false)

Step 5: i*i = 25 > 20, stop outer loop

Step 6: Count true values
Primes: 2, 3, 5, 7, 11, 13, 17, 19
Count: 8 ✅
```

---

## 🧮 Mathematical Insights

### Why Sieve Works

| Concept | Explanation |
|---------|-------------|
| **Prime Property** | Prime has no divisors except 1 and itself |
| **Composite Property** | Every composite is product of primes |
| **Elimination Strategy** | Mark all multiples of each prime |
| **Remaining Numbers** | Only primes survive elimination |

### Optimization Proofs

**Proof: Only check up to √n**
```
Assume n = a × b where both a,b > √n
Then: a × b > √n × √n = n
Contradiction! Therefore, at least one factor ≤ √n
```

**Proof: Start from i²**
```
For prime p and multiplier k < p:
p × k has already been marked when we processed k
Example: 5×3 marked when processing k=3
Therefore, first unmarked multiple is p×p
```

---

## 🔗 Related Problems

| Problem | Similarity | Difficulty |
|---------|-----------|-----------|
| Prime Number of Set Bits | Uses prime checking | 🟢 Easy |
| Ugly Number II | Similar sieving concept | 🟡 Medium |
| Perfect Squares | Number theory | 🟡 Medium |
| Count Primes in Ranges | Multiple queries | 🔴 Hard |
| Prime Factorization | Related concept | 🟡 Medium |

---

## 📚 Historical Context

**Sieve of Eratosthenes** was discovered by Greek mathematician Eratosthenes (276-194 BC) over 2,200 years ago!

Despite its ancient origins:
- ✅ Still one of the most efficient algorithms for finding all primes up to n
- ✅ Used in modern cryptography and number theory
- ✅ Taught in computer science courses worldwide
- ✅ Foundation for more advanced prime algorithms

---

<div align="center">

### ✅ Problem Solved Successfully!

**Time Taken:** ~45 minutes (including research)
**Attempts:** 2 (first was naive O(n√n))
**Key Learning:** Classical algorithms exist for a reason - Sieve of Eratosthenes

**Difficulty:** 🟡 Medium | **Pattern:** Number Theory, Sieve

</div>

---

**Last Updated:** January 03, 2026