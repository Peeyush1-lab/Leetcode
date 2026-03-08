/**
 * Number of Students Unable to Eat Lunch - LeetCode Problem 1700
 *
 * Students in a queue want to take sandwiches from a stack.
 * Each student prefers either circular (0) or square (1) sandwiches.
 * Students who don't want the top sandwich go to the back of the queue.
 * Return the number of students unable to eat.
 *
 * Key Insight: We don't need to simulate the queue!
 * Just count how many students want each type.
 * Process sandwiches from top - if no one wants current type, we're done.
 *
 * Approach: Counting
 * 1. Count students preferring type 0 and type 1
 * 2. Process each sandwich from top of stack
 * 3. If count for sandwich type is 0, return remaining students
 * 4. Otherwise decrement the count
 *
 * Time Complexity: O(n) where n is number of students
 * Space Complexity: O(1) - only two counter variables
 */
class Solution {
    /**
     * Count students unable to eat lunch.
     *
     * @param students array where students[i] is preference of ith student
     * @param sandwiches array where sandwiches[i] is type of ith sandwich (top of stack)
     * @return number of students unable to eat
     */
    public int countStudents(int[] students, int[] sandwiches) {
        // Count how many students want each type
        int count0 = 0, count1 = 0;

        for (int s : students) {
            if (s == 0) count0++;
            else count1++;
        }

        // Process sandwiches from top of stack
        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                // Need a student who wants type 0
                if (count0 == 0) return count1;  // No one wants it, remaining students stuck
                count0--;
            } else {
                // Need a student who wants type 1
                if (count1 == 0) return count0;  // No one wants it, remaining students stuck
                count1--;
            }
        }

        // All students got their sandwiches
        return 0;
    }
}

/**
 * Example Walkthrough:
 *
 * Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
 *
 * Step 1: Count preferences
 *   count0 = 2 (students wanting circular)
 *   count1 = 2 (students wanting square)
 *
 * Step 2: Process sandwiches
 *   sandwich[0] = 0 → count0 > 0, take it → count0=1, count1=2
 *   sandwich[1] = 1 → count1 > 0, take it → count0=1, count1=1
 *   sandwich[2] = 0 → count0 > 0, take it → count0=0, count1=1
 *   sandwich[3] = 1 → count1 > 0, take it → count0=0, count1=0
 *
 * Result: 0 (all students ate)
 */