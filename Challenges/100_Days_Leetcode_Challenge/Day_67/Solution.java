import java.util.Stack;

/**
 * Implement Queue using Stacks - LeetCode Problem 232
 *
 * Implement a FIFO queue using only two stacks.
 * Queue operations: push, pop, peek, empty
 * Stack operations allowed: push, pop, peek, isEmpty, size
 *
 * This solution uses a "push-heavy" approach:
 * - Push: O(n) - reverse all elements to maintain queue order
 * - Pop/Peek/Empty: O(1) - direct stack operations
 *
 * The 'in' stack maintains elements in queue order (front at top).
 * The 'out' stack is used temporarily during push operations.
 */
class MyQueue {
    public Stack<Integer> in;   // Main stack maintaining queue order
    public Stack<Integer> out;  // Temporary stack for reversing

    /**
     * Initialize the queue.
     */
    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     * O(n) time complexity.
     *
     * Strategy: Maintain queue order in 'in' stack
     * - Transfer all elements to 'out' (reverses order)
     * - Push new element to 'in' (now at bottom)
     * - Transfer back (reverses back to queue order)
     */
    public void push(int x) {
        // Optimization: if empty, just push directly
        if (in.empty()) {
            in.push(x);
            return;
        }

        // Transfer all elements from 'in' to 'out'
        while (!in.isEmpty()) {
            out.push(in.pop());
        }

        // Push new element to 'in' (now at bottom)
        in.push(x);

        // Transfer all elements back from 'out' to 'in'
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
    }

    /**
     * Remove and return element from front of queue.
     * O(1) time complexity.
     */
    public int pop() {
        return in.pop();
    }

    /**
     * Get the front element without removing it.
     * O(1) time complexity.
     */
    public int peek() {
        return in.peek();
    }

    /**
     * Check if queue is empty.
     * O(1) time complexity.
     */
    public boolean empty() {
        return in.isEmpty();
    }
}

/**
 * Example Usage:
 *
 * MyQueue queue = new MyQueue();
 * queue.push(1);    // in = [1]
 * queue.push(2);    // in = [2, 1] (1 is at top, front of queue)
 * queue.push(3);    // in = [3, 2, 1]
 * queue.peek();     // returns 1
 * queue.pop();      // returns 1, in = [3, 2]
 * queue.empty();    // returns false
 */

/**
 * Time Complexity Analysis:
 *
 * - push(x): O(n) where n is current queue size
 *   Need to transfer all elements twice
 *
 * - pop(): O(1)
 *   Direct stack pop
 *
 * - peek(): O(1)
 *   Direct stack peek
 *
 * - empty(): O(1)
 *   Direct stack isEmpty check
 *
 * Space Complexity: O(n)
 * - Two stacks can collectively hold n elements
 * - At any time, elements distributed between in and out
 */