import java.util.Stack;

/**
 * Final Prices With a Special Discount in a Shop - LeetCode Problem 1475
 *
 * Given an array of prices, for each item find the next item to the right
 * with a price less than or equal to it, and apply that as a discount.
 *
 * Example: prices = [8,4,6,2,3]
 * - Item 0 (price 8): Next ≤ price is 4 → final price = 8-4 = 4
 * - Item 1 (price 4): Next ≤ price is 2 → final price = 4-2 = 2
 * - Item 2 (price 6): Next ≤ price is 2 → final price = 6-2 = 4
 * - Item 3 (price 2): No next ≤ price → final price = 2
 * - Item 4 (price 3): No next ≤ price → final price = 3
 * Result: [4,2,4,2,3]
 *
 * Approach: Monotonic Stack (Next Smaller or Equal Element)
 * - Use stack to store indices of items waiting for discounts
 * - When we find a smaller or equal price, apply discount to previous items
 * - Stack maintains items in decreasing order by price
 *
 * Time Complexity: O(n) - each element pushed and popped at most once
 * Space Complexity: O(n) - for the answer array and stack
 */
class Solution {
    /**
     * Calculate final prices after applying special discounts.
     */
    public int[] finalPrices(int[] prices) {
        int len = prices.length;

        // Clone the array - start with original prices
        int ans[] = prices.clone();

        // Stack stores indices of items waiting to find their discount
        Stack<Integer> stack = new Stack<>();

        // Process each item from left to right
        for(int i = 0; i < len; i++)
        {
            // While stack has items AND current price can provide discount
            // Pop items and apply discount
            while(!stack.isEmpty() && prices[i] <= prices[stack.peek()])
            {
                // Get the index that receives the discount
                int idx = stack.pop();

                // Apply discount: original_price - discount_amount
                ans[idx] = ans[idx] - ans[i];
            }

            // Current item is waiting to find its discount
            stack.push(i);
        }

        // Items still in stack have no discount (keep original price)
        return ans;
    }
}

/**
 * Example Walkthrough:
 *
 * Input: [8, 4, 6, 2, 3]
 *
 * i=0: price=8
 *   Stack empty → push 0
 *   Stack: [0], ans: [8,4,6,2,3]
 *
 * i=1: price=4
 *   4 <= prices[0]=8 → pop 0, ans[0] = 8-4 = 4
 *   Stack empty → push 1
 *   Stack: [1], ans: [4,4,6,2,3]
 *
 * i=2: price=6
 *   6 > prices[1]=4 → no pop, push 2
 *   Stack: [1,2], ans: [4,4,6,2,3]
 *
 * i=3: price=2
 *   2 <= prices[2]=6 → pop 2, ans[2] = 6-2 = 4
 *   2 <= prices[1]=4 → pop 1, ans[1] = 4-2 = 2
 *   Stack empty → push 3
 *   Stack: [3], ans: [4,2,4,2,3]
 *
 * i=4: price=3
 *   3 > prices[3]=2 → no pop, push 4
 *   Stack: [3,4], ans: [4,2,4,2,3]
 *
 * Final: [4,2,4,2,3]
 */

/**
 * Alternative Solution: Using Array Instead of Stack
 * Slightly more memory efficient
 */
class SolutionWithArray {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = prices.clone();
        int[] stack = new int[n];
        int top = -1;  // Stack pointer

        for(int i = 0; i < n; i++) {
            while(top >= 0 && prices[i] <= prices[stack[top]]) {
                int idx = stack[top--];
                ans[idx] -= prices[i];
            }
            stack[++top] = i;
        }

        return ans;
    }
}

/**
 * Edge Cases:
 *
 * 1. Increasing prices: [1,2,3,4,5]
 *    No discounts applied → [1,2,3,4,5]
 *
 * 2. Decreasing prices: [5,4,3,2,1]
 *    All items get discounted → [1,1,1,1,1]
 *
 * 3. Equal prices: [3,3,3,3]
 *    Each discounts previous → [0,0,0,3]
 *
 * 4. Single element: [5]
 *    No next element → [5]
 *
 * 5. Mixed with no discounts: [10,1,1,6]
 *    Result: [9,0,1,6]
 */