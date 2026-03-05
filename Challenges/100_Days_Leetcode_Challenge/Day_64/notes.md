# Final Prices With a Special Discount in a Shop - Study Notes

## Approach: Monotonic Stack

### Key Insight

This is a classic **"Next Smaller Element"** problem. For each item, we need to find the next item to the right with a price less than or equal to the current price.

A **monotonic stack** is perfect for this because:
- We process elements left to right
- We need to find relationships with future elements
- Stack maintains indices of elements waiting to find their "next smaller"

### What is a Monotonic Stack?

A monotonic stack maintains elements in a specific order (increasing or decreasing). When a new element violates the order, we pop elements until the order is restored.

**For this problem**: We maintain a **decreasing monotonic stack** (by prices)
- Stack stores indices of prices that haven't found their discount yet
- When we find a smaller or equal price, we can apply discounts to previous items

## Algorithm Walkthrough

### Step-by-Step Process

1. Clone the prices array to create the answer array
2. Use a stack to store indices of items waiting for discounts
3. For each current item:
   - While stack is not empty AND current price ≤ price at stack top:
     - Pop the index (this item found its discount!)
     - Apply discount: `ans[popped_index] -= current_price`
   - Push current index to stack (it's waiting for its discount)
4. Items still in stack have no discount (no smaller price to the right)

### Visual Example: [8, 4, 6, 2, 3]

```
Index:  0  1  2  3  4
Price:  8  4  6  2  3

i=0, price=8:
  Stack empty, push 0
  Stack: [0]
  ans: [8,4,6,2,3]

i=1, price=4:
  4 <= prices[0]=8, so index 0 gets discount of 4
    Pop 0: ans[0] = 8 - 4 = 4
  Stack empty, push 1
  Stack: [1]
  ans: [4,4,6,2,3]

i=2, price=6:
  6 > prices[1]=4, no discount for index 1 yet
  Push 2
  Stack: [1,2]
  ans: [4,4,6,2,3]

i=3, price=2:
  2 <= prices[2]=6, so index 2 gets discount of 2
    Pop 2: ans[2] = 6 - 2 = 4
  2 <= prices[1]=4, so index 1 gets discount of 2
    Pop 1: ans[1] = 4 - 2 = 2
  Stack empty, push 3
  Stack: [3]
  ans: [4,2,4,2,3]

i=4, price=3:
  3 > prices[3]=2, no discount for index 3
  Push 4
  Stack: [3,4]
  ans: [4,2,4,2,3]

Final: Items at indices 3 and 4 remain in stack (no discount)
Answer: [4,2,4,2,3]
```

### Visual Example 2: [10, 1, 1, 6]

```
Index:  0  1  2  3
Price: 10  1  1  6

i=0, price=10:
  Stack: [0]
  ans: [10,1,1,6]

i=1, price=1:
  1 <= prices[0]=10, discount for index 0
    Pop 0: ans[0] = 10 - 1 = 9
  Stack: [1]
  ans: [9,1,1,6]

i=2, price=1:
  1 <= prices[1]=1, discount for index 1
    Pop 1: ans[1] = 1 - 1 = 0
  Stack: [2]
  ans: [9,0,1,6]

i=3, price=6:
  6 > prices[2]=1, no discount for index 2
  Stack: [2,3]
  ans: [9,0,1,6]

Final: Indices 2 and 3 have no discount
Answer: [9,0,1,6]
```

## Code Analysis

### Why Clone the Array?

```java
int ans[] = prices.clone();
```

We clone because:
1. We need to return a new array (not modify the input)
2. We start with original prices and subtract discounts
3. Items without discounts keep their original price

### The Core Logic

```java
while(!stack.isEmpty() && prices[i] <= prices[stack.peek()])
{
    int idx = stack.pop();
    ans[idx] = ans[idx] - ans[i];
}
```

**Breaking it down:**
- `!stack.isEmpty()`: Check if there are items waiting for discounts
- `prices[i] <= prices[stack.peek()]`: Current price can be a discount for stack top
- `int idx = stack.pop()`: Get the index that gets the discount
- `ans[idx] = ans[idx] - ans[i]`: Apply the discount

**Important**: We use `ans[i]` not `prices[i]` for subtraction. Since we cloned the array, both are the same initially, but using `ans` is consistent.

### Why Push Every Index?

```java
stack.push(i);
```

Every item needs to check if there's a smaller price to its right, so we push all indices. Items that never find a discount will remain in the stack at the end (which is fine - they keep their original price).

## Complexity Analysis

### Time Complexity: O(n)
- Single pass through the array: O(n)
- Each index is pushed exactly once: O(n)
- Each index is popped at most once: O(n)
- **Total: O(n)**

Even though there's a while loop inside a for loop, each element is processed at most twice (once pushed, once popped).

### Space Complexity: O(n)
- Answer array: O(n)
- Stack can hold up to n elements worst case: O(n)
- **Total: O(n)**

## Edge Cases

### Case 1: No Discounts (Increasing Prices)
```
Input: [1,2,3,4,5]
Stack builds up: [0,1,2,3,4]
No element ever gets popped
Output: [1,2,3,4,5] (all original prices)
```

### Case 2: All Get Maximum Discount
```
Input: [5,4,3,2,1]
Each element immediately discounts all previous ones
Stack never grows beyond size 1
Output: [1,1,1,1,1]
```

### Case 3: Equal Prices
```
Input: [3,3,3,3]
Each 3 provides discount for previous 3
Stack: [0] → [1] → [2] → [3]
Output: [0,0,0,3]
```

### Case 4: Single Element
```
Input: [5]
No next element, no discount
Output: [5]
```

### Case 5: Two Elements
```
Input: [8,4]
4 <= 8, so discount applied
Output: [4,4]

Input: [4,8]
8 > 4, no discount
Output: [4,8]
```

## Common Mistakes

1. **Using nested loops instead of stack**
   ```java
   // Brute force O(n²) - works but inefficient
   for(int i = 0; i < n; i++) {
       for(int j = i+1; j < n; j++) {
           if(prices[j] <= prices[i]) {
               ans[i] -= prices[j];
               break;
           }
       }
   }
   ```

2. **Not cloning the array**
   - Modifying the input array directly
   - Using wrong initial values in ans

3. **Wrong condition in while loop**
   ```java
   // WRONG: uses < instead of <=
   while(!stack.isEmpty() && prices[i] < prices[stack.peek()])
   ```
   Equal prices should also provide discount!

4. **Forgetting to push current index**
   - Every index needs to check for its discount

## Monotonic Stack Pattern

This problem is part of the **"Next Element"** family:

| Problem | Pattern | Stack Order |
|---------|---------|-------------|
| Next Greater Element | Find larger element to right | Decreasing |
| Next Smaller Element | Find smaller element to right | Increasing |
| Previous Greater | Find larger to left | Decreasing |
| Previous Smaller | Find smaller to left | Increasing |

**This problem**: Next Smaller or Equal → Similar to Next Smaller

## Alternative Approaches

### Approach 1: Brute Force (Nested Loops)
```java
public int[] finalPrices(int[] prices) {
    int n = prices.length;
    int[] ans = prices.clone();

    for(int i = 0; i < n; i++) {
        for(int j = i + 1; j < n; j++) {
            if(prices[j] <= prices[i]) {
                ans[i] -= prices[j];
                break;  // First eligible discount
            }
        }
    }
    return ans;
}
```
**Time**: O(n²), **Space**: O(1)
**When to use**: Small arrays (n < 20), simpler to understand

### Approach 2: Backward Iteration
Process from right to left, maintaining minimum seen so far.
**Pros**: Different perspective
**Cons**: More complex logic, not better than stack approach

## Interview Tips

1. **Recognize the pattern**: "Find next element with property X" → Think monotonic stack

2. **Start with brute force**: Explain O(n²) solution first, then optimize

3. **Explain monotonic stack**:
   - "We maintain elements in order waiting to find their match"
   - "When we find a match, we pop and process"
   - "Each element pushed once, popped once → O(n)"

4. **Walk through an example**: Use [8,4,6,2,3] to show stack operations

5. **Discuss trade-offs**:
   - Brute force: Simple but O(n²)
   - Stack: Optimal but requires understanding of monotonic stack

## Key Takeaways

- **Monotonic stack** is the optimal pattern for "next element" problems
- **Amortized O(n)**: Even with nested loops, each element processed twice maximum
- **Store indices, not values**: Easier to update the answer array
- Clone arrays when you need to preserve original and build new result
- Understanding monotonic stack opens up many similar problems

## Practice Progression

Master this pattern with:
1. **Easier**: Next Greater Element I (496) - Same pattern, simpler setup
2. **Similar**: Daily Temperatures (739) - Find next warmer day
3. **Harder**: Largest Rectangle in Histogram (84) - Advanced monotonic stack