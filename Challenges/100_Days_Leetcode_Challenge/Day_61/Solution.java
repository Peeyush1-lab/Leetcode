import java.util.Stack;

/**
 * Min Stack - LeetCode Problem 155
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 *
 * Approach: Two Stack Solution
 * - Main stack stores all values
 * - Min stack stores the minimum value at each level
 *
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is the number of elements
 */
class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /**
     * Initialize the MinStack data structure.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Push element val onto the stack.
     * Also updates the minStack to track the minimum at this level.
     */
    public void push(int val) {
        stack.push(val);

        // If minStack is empty, val is the minimum
        // Otherwise, the minimum is either val or the current minimum
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    /**
     * Remove the element on the top of the stack.
     * Also removes the corresponding minimum from minStack.
     */
    public void pop() {
        stack.pop();
        minStack.pop();
    }

    /**
     * Get the top element of the stack.
     */
    public int top() {
        return stack.peek();
    }

    /**
     * Retrieve the minimum element in the stack in O(1) time.
     */
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Usage example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 */