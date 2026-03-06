import java.util.*;

/**
 * Exclusive Time of Functions - LeetCode Problem 636
 *
 * On a single-threaded CPU, calculate the exclusive execution time for each function.
 * Functions can be nested (one calls another) and can be recursive (calls itself).
 *
 * Each log entry is formatted as: "function_id:start|end:timestamp"
 * - "start" events occur at the beginning of the timestamp
 * - "end" events occur at the end of the timestamp (inclusive)
 *
 * Example: ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Timeline:  0   1   2   3   4   5   6
 *           [0] [0] [1] [1] [1] [1] [0]
 *
 * Function 0: times [0,1] + time [6] = 2 + 1 = 3 units
 * Function 1: times [2,3,4,5] = 4 units
 * Output: [3, 4]
 *
 * Approach: Stack-based Simulation
 * - Stack maintains the call stack (currently executing functions)
 * - prevTime tracks when the current function started/resumed
 * - On "start": pause current function, start new one
 * - On "end": complete current function, resume previous
 *
 * Time Complexity: O(m) where m is the number of logs
 * Space Complexity: O(n) where n is the number of functions
 */
class Solution {
    /**
     * Calculate the exclusive execution time for each function.
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        // Result array to store execution time for each function
        int[] res = new int[n];

        // Stack to simulate the call stack (function IDs)
        Stack<Integer> stack = new Stack<>();

        // Track the last timestamp we processed
        int prevTime = 0;

        for (String log : logs) {
            // Parse the log entry: "id:type:timestamp"
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (type.equals("start")) {
                // A new function is starting

                // If there's a function currently running, it gets paused
                if (!stack.isEmpty()) {
                    // Add the time it has been running (from prevTime to now)
                    res[stack.peek()] += time - prevTime;
                }

                // Push the new function onto the stack
                stack.push(id);

                // Update prevTime to when this function started
                prevTime = time;

            } else {
                // A function is ending

                // Add the time from when it last started/resumed until now
                // +1 because "end" is inclusive (ends at the END of timestamp)
                res[stack.pop()] += time - prevTime + 1;

                // Update prevTime to the next unit (after this timestamp ends)
                prevTime = time + 1;
            }
        }

        return res;
    }
}

/**
 * Detailed Example Walkthrough:
 *
 * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 *
 * Initial state:
 *   res = [0, 0]
 *   stack = []
 *   prevTime = 0
 *
 * Process "0:start:0":
 *   - id=0, type="start", time=0
 *   - stack is empty, no function to pause
 *   - push 0
 *   - prevTime = 0
 *   State: res=[0,0], stack=[0], prevTime=0
 *
 * Process "1:start:2":
 *   - id=1, type="start", time=2
 *   - Function 0 has been running from prevTime=0 to time=2
 *   - res[0] += 2 - 0 = 2
 *   - push 1
 *   - prevTime = 2
 *   State: res=[2,0], stack=[0,1], prevTime=2
 *
 * Process "1:end:5":
 *   - id=1, type="end", time=5
 *   - Function 1 has been running from prevTime=2 to time=5 (inclusive)
 *   - res[1] += 5 - 2 + 1 = 4
 *   - pop 1
 *   - prevTime = 6 (next unit after 5 ends)
 *   State: res=[2,4], stack=[0], prevTime=6
 *
 * Process "0:end:6":
 *   - id=0, type="end", time=6
 *   - Function 0 has been running from prevTime=6 to time=6 (inclusive)
 *   - res[0] += 6 - 6 + 1 = 1
 *   - pop 0
 *   - prevTime = 7
 *   State: res=[3,4], stack=[], prevTime=7
 *
 * Final Answer: [3, 4]
 */

/**
 * Recursive Example:
 *
 * Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
 *
 * This shows function 0 calling itself recursively.
 *
 * Timeline:  0   1   2   3   4   5   6   7
 * Outer:    [0] [0] ... ... ... ...     [0]
 * Inner 1:          [0] [0] [0] [0]
 * Inner 2:                          [0]
 *
 * Breakdown:
 * - Times [0,1]: outer call (2 units)
 * - Times [2,3,4,5]: first recursive call (4 units)
 * - Time [6]: second recursive call (1 unit)
 * - Time [7]: outer call resumes (1 unit)
 *
 * Total: 2 + 4 + 1 + 1 = 8 units
 *
 * The stack can have [0, 0, 0] - same function ID multiple times!
 */

/**
 * Alternative Implementation: With start time tracking
 * Less elegant but shows different approach
 */
class SolutionAlternative {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>(); // [functionId, startTime]

        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (type.equals("start")) {
                stack.push(new int[]{id, time});
            } else {
                int[] top = stack.pop();
                int duration = time - top[1] + 1;
                res[top[0]] += duration;

                // Subtract nested function time from parent
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= duration;
                }
            }
        }

        return res;
    }
}

/**
 * Edge Cases:
 *
 * 1. Single function, no nesting:
 *    ["0:start:0", "0:end:5"] → [6]
 *
 * 2. Immediate end after start:
 *    ["0:start:0", "0:end:0"] → [1]
 *
 * 3. Deep nesting:
 *    ["0:start:0", "1:start:1", "2:start:2", "2:end:3", "1:end:4", "0:end:5"]
 *    Each function gets time when at top of stack
 *
 * 4. Recursive with multiple levels:
 *    ["0:start:0", "0:start:1", "0:start:2", "0:end:3", "0:end:4", "0:end:5"]
 *    Stack: [0] → [0,0] → [0,0,0] → [0,0] → [0] → []
 *
 * 5. Non-overlapping calls to same function:
 *    ["0:start:0", "0:end:2", "0:start:5", "0:end:7"]
 *    Same function runs in two separate time periods
 */