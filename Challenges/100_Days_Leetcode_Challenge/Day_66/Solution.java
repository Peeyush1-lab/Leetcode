import java.util.*;

/**
 * Evaluate Reverse Polish Notation - LeetCode Problem 150
 *
 * Evaluate an arithmetic expression given in Reverse Polish Notation (RPN).
 * In RPN, operators come after their operands.
 *
 * Examples:
 * - Infix: (2 + 1) * 3  →  RPN: 2 1 + 3 *  →  Result: 9
 * - Infix: 4 + (13 / 5) →  RPN: 4 13 5 / + →  Result: 6
 *
 * Valid operators: +, -, *, /
 * Division truncates toward zero (standard integer division)
 *
 * Approach: Stack-Based Evaluation
 * 1. When we see a number: push it onto the stack
 * 2. When we see an operator: pop two operands, apply operator, push result
 * 3. Final answer is the only element remaining in the stack
 *
 * Time Complexity: O(n) where n is the number of tokens
 * Space Complexity: O(n) for the stack
 */
class Solution {
    /**
     * Evaluate an expression in Reverse Polish Notation.
     */
    public int evalRPN(String[] tokens) {
        // Use ArrayList as a stack (Java 21+ has removeLast method)
        ArrayList<Integer> arr = new ArrayList<>();

        for(String token : tokens)
        {
            if(token.equals("+"))
            {
                // Pop two operands and add them
                int a = arr.removeLast();  // Second operand (top)
                int b = arr.removeLast();  // First operand
                arr.add(b + a);            // Order doesn't matter for addition
            }
            else if(token.equals("-"))
            {
                // Pop two operands and subtract
                int a = arr.removeLast();  // Second operand (top)
                int b = arr.removeLast();  // First operand
                arr.add(b - a);            // Order MATTERS: b - a, not a - b
            }
            else if(token.equals("*"))
            {
                // Pop two operands and multiply
                int a = arr.removeLast();  // Second operand (top)
                int b = arr.removeLast();  // First operand
                arr.add(b * a);            // Order doesn't matter for multiplication
            }
            else if(token.equals("/"))
            {
                // Pop two operands and divide
                int a = arr.removeLast();  // Divisor (top)
                int b = arr.removeLast();  // Dividend
                arr.add(b / a);            // Order MATTERS: b / a, not a / b
            }
            else
            {
                // It's a number, parse and push it
                arr.add(Integer.parseInt(token));
            }
        }

        // For valid RPN, exactly one element remains
        return arr.get(0);
    }
}

/**
 * Important Notes:
 *
 * 1. Operand Order:
 *    For "5 3 -": We want 5 - 3 = 2
 *    - First pop gives us 3 (a)
 *    - Second pop gives us 5 (b)
 *    - Result must be b - a = 5 - 3 = 2
 *
 * 2. Integer Division:
 *    Java's / operator truncates toward zero
 *    - 13 / 5 = 2 (not 2.6)
 *    - -13 / 5 = -2 (not -3)
 *
 * 3. Negative Numbers:
 *    "-3" is a token representing the number -3
 *    Not the minus operator!
 *
 * 4. Valid RPN:
 *    The problem guarantees valid RPN expression
 *    So we don't need to validate:
 *    - Sufficient operands for operators
 *    - Exactly one result remaining
 */

/**
 * Edge Cases:
 *
 * 1. Single number: ["42"] → 42
 *
 * 2. Negative numbers: ["-3","4","+"] → 1
 *
 * 3. Complex expression: ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 *    Evaluates to: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5 = 22
 *
 * 4. Division truncation: ["7","3","/"] → 2
 *                         ["-7","3","/"] → -2
 *
 * 5. All operations: ["15","7","1","1","+","-","/","3","*","2","1","1","+","+","-"]
 *    Result: 5
 */