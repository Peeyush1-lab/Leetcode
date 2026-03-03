import java.util.Stack;

/**
 * Baseball Game - LeetCode Problem 682
 *
 * You are keeping scores for a baseball game with special rules.
 * Given a list of operations, calculate the sum of all scores.
 *
 * Operations:
 * - Integer x: Record a new score of x
 * - "C": Invalidate the previous score
 * - "D": Record double of the previous score
 * - "+": Record sum of the previous two scores
 *
 * Approach: Stack-based simulation
 * - Use stack to maintain scores
 * - Process each operation according to its type
 * - Sum all remaining scores at the end
 *
 * Time Complexity: O(n) where n is the number of operations
 * Space Complexity: O(n) for the stack
 */
class Solution {
    /**
     * Calculate the sum of all scores after applying all operations.
     */
    public int calPoints(String[] operations) {
        Stack<Integer> st = new Stack<>();

        // Process each operation
        for (String i : operations) {
            if (i.equals("C")) {
                // Remove the last score
                st.pop();
            } else if (i.equals("D")) {
                // Double the last score and record it
                st.push(st.peek() * 2);
            } else if (i.equals("+")) {
                // Sum the last two scores and record it
                int a = st.pop(); // Most recent score
                int b = st.peek(); // Second most recent score
                st.push(a); // Restore the most recent score
                st.push(a + b); // Add the sum
            } else {
                // It's a number, parse and push it
                st.push(Integer.parseInt(i));
            }
        }

        // Calculate the sum of all scores
        int sum = 0;
        for (int score : st) {
            sum += score;
        }

        return sum;
    }
}

/**
 * Example usage:
 *
 * Solution solution = new Solution();
 *
 * String[] ops1 = {"5","2","C","D","+"};
 * System.out.println(solution.calPoints(ops1)); // Output: 30
 *
 * String[] ops2 = {"5","-2","4","C","D","9","+","+"};
 * System.out.println(solution.calPoints(ops2)); // Output: 27
 *
 * String[] ops3 = {"1","C"};
 * System.out.println(solution.calPoints(ops3)); // Output: 0
 */