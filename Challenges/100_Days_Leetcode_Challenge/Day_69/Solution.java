/**
 * Remove K Digits - LeetCode Problem 402
 *
 * Given a string representing a non-negative integer and an integer k,
 * remove k digits to form the smallest possible integer.
 *
 * Key Insight: Use greedy approach with monotonic increasing stack
 * - To minimize the number, we want smaller digits to appear earlier
 * - When we see a smaller digit, remove larger previous digits
 * - Maintain a monotonic increasing sequence
 *
 * Example: "1432219", k=3
 * - Remove 4, 3, 2 to get "1219"
 *
 * Approach: Greedy + Monotonic Stack
 * 1. Build stack by removing larger digits when seeing smaller ones
 * 2. If k removals remain, remove from end
 * 3. Remove leading zeros
 * 4. Handle empty result
 *
 * Time Complexity: O(n) where n is length of num
 * Space Complexity: O(n) for the StringBuilder stack
 */
class Solution {
    /**
     * Remove k digits to get the smallest possible number.
     */
    public String removeKdigits(String num, int k) {
        // Use StringBuilder as a stack
        StringBuilder stack = new StringBuilder();

        // Process each digit
        for (char digit : num.toCharArray()) {
            // Remove larger digits from stack while we can
            // Condition: stack not empty, k > 0, and top > current digit
            while (stack.length() > 0 && k > 0 &&
                   stack.charAt(stack.length() - 1) > digit) {
                stack.deleteCharAt(stack.length() - 1);  // Pop
                k--;  // One removal done
            }
            // Add current digit to stack
            stack.append(digit);
        }

        // If we still have removals left, remove from the end
        // This happens when digits are in ascending order
        // Example: "123456", k=3 → remove "456" to get "123"
        stack.setLength(stack.length() - k);

        // Remove leading zeros
        int i = 0;
        while (i < stack.length() && stack.charAt(i) == '0') {
            i++;
        }

        // Get result without leading zeros
        String result = stack.substring(i);

        // If result is empty, return "0"
        return result.isEmpty() ? "0" : result;
    }
}

/**
 * Detailed Example Walkthrough:
 *
 * Input: num = "1432219", k = 3
 *
 * Initial: stack = "", k = 3
 *
 * Process '1':
 *   Stack empty, just append
 *   stack = "1"
 *
 * Process '4':
 *   '4' > '1', no removal, append
 *   stack = "14"
 *
 * Process '3':
 *   '3' < '4', remove '4' (k becomes 2)
 *   stack = "1"
 *   Append '3'
 *   stack = "13"
 *
 * Process '2':
 *   '2' < '3', remove '3' (k becomes 1)
 *   stack = "1"
 *   Append '2'
 *   stack = "12"
 *
 * Process '2':
 *   '2' = '2', no removal, append
 *   stack = "122"
 *
 * Process '1':
 *   '1' < '2', remove '2' (k becomes 0)
 *   stack = "12"
 *   k = 0, stop removing
 *   Append '1'
 *   stack = "121"
 *
 * Process '9':
 *   k = 0, can't remove anymore
 *   Append '9'
 *   stack = "1219"
 *
 * After loop: k = 0, no need to trim
 * No leading zeros
 * Result: "1219"
 */