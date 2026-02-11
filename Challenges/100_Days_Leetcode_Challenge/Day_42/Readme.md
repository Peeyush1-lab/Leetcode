# Day 42 - LeetCode Challenge

## 📋 Problem Solved

### ✅ Insert Greatest Common Divisors in Linked List
- **LeetCode:** [#2807](https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/)
- **Difficulty:** Medium
- **Status:** Solved
- **Topics:** Linked List, Math, Number Theory
- **Key Concept:** Inserting nodes between adjacent pairs using GCD calculation

## 📊 Daily Stats
- **Problems Attempted:** 1
- **Problems Solved:** 1
- **Time Spent:** 30 min
- **Concepts Practiced:** Node insertion, GCD algorithm, Adjacent pair processing

## 🎯 Learning Focus
- **Primary:** Inserting nodes between existing nodes
- **Secondary:** GCD calculation (Euclidean algorithm)
- **Advanced:** Combining math and data structures

## 📁 Folder Structure
```
Day42/
├── README.md           ← You are here
├── notes.md            ← Detailed explanations & problem-solving
└── Solution2807.java   ← Insert GCD in Linked List
```

## 🔗 Quick Links
- [Problem Discussion](https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/discuss/)
- [GCD Algorithm](https://leetcode.com/discuss/study-guide/gcd-euclidean)
- [Linked List Insertion](https://leetcode.com/discuss/study-guide/linked-list-patterns)

## 💡 Key Takeaways

**Pattern Combination:**
- Math algorithm (GCD) + Data structure (linked list)
- Node insertion between adjacent pairs
- Two-pointer traversal (prev and curr)
- Helper function for mathematical operation

**Algorithm Strategy:**
1. Traverse with prev and curr pointers
2. Calculate GCD of adjacent values
3. Create new node with GCD
4. Insert between prev and curr
5. Move pointers forward
6. Return original head

**Implementation Highlights:**
- Clean GCD helper function
- Efficient Euclidean algorithm
- Simple insertion pattern
- No edge cases for single node

## ⚙️ Complexity Analysis

**Time Complexity: O(n × log(min(a,b)))**
- n iterations through list
- Each GCD calculation: O(log(min(a,b)))
- Total: O(n × log(min(a,b)))

**Space Complexity: O(n)**
- n-1 new nodes inserted
- Required output space
- Constant extra variables

**GCD Algorithm:**
```
Euclidean Algorithm: O(log(min(a,b)))
Efficient for finding greatest common divisor
Classic number theory algorithm
```

## 🎓 Pattern Recognition

**Adjacent Pair Processing:**
- Process consecutive nodes
- Insert between each pair
- Maintain prev and curr pointers
- Common pattern for list modifications

**This problem teaches:**
- Combining algorithms from different domains
- Node insertion technique
- Mathematical helper functions
- Two-pointer traversal with insertion

**Similar problems:**
- **Insert into Sorted Circular List (#708)** - Insertion logic
- **Merge In Between Linked Lists (#1669)** - Range insertion
- **Design Linked List (#707)** - Insert operations
- **Split Linked List in Parts (#725)** - List manipulation

**Math + Data Structure problems:**
- Prime factorization in lists
- Sum of nodes between zeroes
- Math operations on list values

## 📈 Progress Journey

| Day | Problem | Difficulty | Core Pattern |
|-----|---------|-----------|--------------|
| 39 | Reverse Linked List II | Medium | Partial Reversal |
| 40 | Double a Number | Medium | Arithmetic Operations |
| 41 | Add Two Numbers | Medium | Two-List Addition |
| 42 | Insert GCD | Medium | **Math + Insertion** |

**Skill Evolution:**
- Days 39-41: Arithmetic and reversal mastery ✓
- Day 42: **Math algorithm integration** ✓
- Expanding beyond pure list operations

## 🌟 Why This Problem Matters

**Cross-Domain Skills:**
- Combines math and data structures
- Tests helper function design
- Requires algorithm knowledge
- Shows practical application

**Interview Importance:**
- Tests multiple skill areas
- Shows problem decomposition
- Demonstrates clean code organization
- Common in system design contexts

**Real-World Applications:**
- Data transformation pipelines
- Signal processing (between samples)
- Interpolation in time series
- Data augmentation in lists

## 🎯 GCD Algorithm Analysis

**Euclidean Algorithm:**
```
GCD(a, b) = GCD(b, a mod b)
Base case: GCD(a, 0) = a

Classic, efficient number theory algorithm
Used in cryptography, fractions, etc.
```

**Your Implementation:**
- Handles edge cases (divisibility checks)
- Clean while loop for main algorithm
- Efficient modulo operation
- Returns correct GCD

**Optimization:**
```
Initial checks: if a%b==0 or b%a==0
Avoids unnecessary iterations
Early termination when possible
```

## 🔄 Insertion Pattern

**Two-Pointer Technique:**
```
prev → curr → next
Insert new node between prev and curr
Maintain pointers for next iteration
```

**Insertion Steps:**
1. Create new node with GCD value
2. Point new node to curr
3. Point prev to new node
4. Move pointers: prev = curr, curr = curr.next

**Why This Works:**
- Maintains list integrity
- No nodes lost
- Clean pointer management
- One pass through list

## 🎯 Helper Function Benefits

**Separation of Concerns:**
```
gcd() - Handles math logic
insertGreatestCommonDivisors() - Handles list logic

Clean, testable, maintainable
```

**Advantages:**
- Easy to test GCD independently
- Reusable in other problems
- Clear single responsibility
- Improves code readability

## 🔗 Next Steps

**Natural Progression:**
1. **Merge In Between Lists (#1669)** - More insertion
2. **Split Linked List in Parts (#725)** - List division
3. **Design Circular Queue (#622)** - Circular structures
4. **LCM in linked lists** - Related math operation

**Advanced Challenges:**
- Insert based on other math functions
- Multiple insertion criteria
- Optimize for sorted lists
- Handle circular lists

**Skill Development:**
- Master insertion patterns
- Practice more math algorithms
- Learn other number theory concepts
- Combine with other list operations

|**Previous:** [Day 41](../Day_41/) | **Next:** [Day 43](../Day_43/)|
|---|---|

|**Date:** February 11, 2026|
|---|