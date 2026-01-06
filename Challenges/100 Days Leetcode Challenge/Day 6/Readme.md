# 💰 Day 6: Richest Customer Wealth

## 📋 Problem Statement

You are given an `m x n` integer grid `accounts` where `accounts[i][j]` is the amount of money the `i-th` customer has in the `j-th` bank. Return the **wealth** that the richest customer has.

A customer's **wealth** is the amount of money they have in all their bank accounts. The richest customer is the customer that has the **maximum wealth**.

## 💡 Examples

### Example 1: 💵
**Input:** `accounts = [[1,2,3],[3,2,1]]`
**Output:** `6`
**Explanation:**
```
Customer 0: wealth = 1 + 2 + 3 = 6
Customer 1: wealth = 3 + 2 + 1 = 6
Maximum wealth = 6
```

### Example 2: 💵
**Input:** `accounts = [[1,5],[7,3],[3,5]]`
**Output:** `10`
**Explanation:**
```
Customer 0: wealth = 1 + 5 = 6
Customer 1: wealth = 7 + 3 = 10
Customer 2: wealth = 3 + 5 = 8
Maximum wealth = 10
```

### Example 3: 💵
**Input:** `accounts = [[2,8,7],[7,1,3],[1,9,5]]`
**Output:** `17`
**Explanation:**
```
Customer 0: wealth = 2 + 8 + 7 = 17
Customer 1: wealth = 7 + 1 + 3 = 11
Customer 2: wealth = 1 + 9 + 5 = 15
Maximum wealth = 17
```

## ⚠️ Constraints

- `m == accounts.length`
- `n == accounts[i].length`
- `1 <= m, n <= 50`
- `1 <= accounts[i][j] <= 100`

## 🔑 Key Points

1. 💰 Each row represents one customer
2. 🏦 Each column represents a bank account
3. ➕ Customer's wealth = sum of all their accounts (row sum)
4. 🎯 Need to find the **maximum** row sum
5. 📊 Simple nested loop problem
6. ✨ All values are positive (1 to 100)

## 🛠️ Approach: Nested Loop with Max Tracking

### 💡 Core Intuition
Calculate the sum for each customer (each row) and keep track of the maximum sum encountered.

### 📋 Algorithm Steps
1. Initialize `temp` to track maximum wealth (start at 0)
2. For each customer (outer loop):
   - Initialize `sum` to 0
   - Calculate total wealth by summing all accounts (inner loop)
   - Update `temp` if current customer's wealth is greater
3. Return the maximum wealth found

### ⏱️ Complexity
- **Time:** O(m × n) - Visit each element once
- **Space:** O(1) - Only a few variables

## Example

```
accounts = [[1, 5],
            [7, 3],
            [3, 5]]

Customer 0: 1 + 5 = 6  → temp = 6
Customer 1: 7 + 3 = 10 → temp = 10 (new max!)
Customer 2: 3 + 5 = 8  → temp = 10 (stays same)

Result: 10 💰
```