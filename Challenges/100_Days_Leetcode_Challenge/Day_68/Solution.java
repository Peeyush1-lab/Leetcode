/**
 * Time Needed to Buy Tickets - LeetCode Problem 2073
 *
 * People are in a line to buy tickets. Each person takes 1 second to buy 1 ticket.
 * After buying, they go to the back of the line to buy more (if needed).
 * Calculate the time for person at position k to finish buying all their tickets.
 *
 * Key Insight: We don't need to simulate! We can calculate directly:
 * - People at or before k can buy up to tickets[k] tickets
 * - People after k can buy up to tickets[k]-1 tickets (k finishes before their next turn)
 *
 * Approach: Mathematical Calculation
 * 1. Get target = tickets[k] (how many tickets person k needs)
 * 2. For each person:
 *    - If i <= k: they contribute min(tickets[i], target) seconds
 *    - If i > k: they contribute min(tickets[i], target-1) seconds
 * 3. Sum all contributions
 *
 * Time Complexity: O(n) where n is number of people
 * Space Complexity: O(1) - only a few variables
 */
class Solution {
    /**
     * Calculate time for person at position k to finish buying tickets.
     *
     * @param tickets array where tickets[i] is number of tickets person i wants
     * @param k the position (0-indexed) of the person we're tracking
     * @return total time in seconds
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        // Edge case: if person k only needs 1 ticket
        // They buy on their first turn at position k+1
        if (tickets[k] == 1) {
            return k + 1;
        }

        int time = 0;
        int target = tickets[k];  // How many tickets person k needs

        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                // People at or before position k
                // They can buy tickets until person k finishes
                // Max they contribute is 'target' tickets
                time += Math.min(tickets[i], target);
            } else {
                // People after position k
                // They get one less turn (person k leaves before their next turn)
                // Max they contribute is 'target - 1' tickets
                time += Math.min(tickets[i], target - 1);
            }
        }

        return time;
    }
}

/**
 * Example Walkthrough 1:
 *
 * Input: tickets = [2,3,2], k = 2
 *
 * target = tickets[2] = 2
 *
 * i = 0 (i <= k):
 *   time += min(2, 2) = 2
 *   time = 2
 *
 * i = 1 (i <= k):
 *   time += min(3, 2) = 2
 *   time = 4
 *
 * i = 2 (i = k):
 *   time += min(2, 2) = 2
 *   time = 6
 *
 * Return: 6
 *
 * Explanation:
 * - Round 1: Everyone buys 1 ticket (3 seconds)
 * - Round 2: Everyone buys 1 ticket (3 seconds)
 * - Person 2 is done after 6 seconds
 */

/**
 * Why the Formula Works:
 *
 * Imagine the queue wrapping around multiple times:
 *
 * tickets = [3, 5, 2], k = 1
 * target = 5
 *
 * Round 1: [3, 5, 2] → Everyone buys 1
 * Round 2: [2, 4, 1] → Everyone buys 1
 * Round 3: [1, 3, 0] → Person 2 leaves (bought 2)
 * Round 4: [0, 2] → Person 0 leaves (bought 3)
 * Round 5: [1] → Person 1 buys
 *
 * Person 0 (i < k): Bought min(3, 5) = 3 tickets
 * Person 1 (i = k): Bought min(5, 5) = 5 tickets
 * Person 2 (i > k): Bought min(2, 4) = 2 tickets
 *
 * Total time = 3 + 5 + 2 = 10 seconds
 */

/**
 * Why People After k Get (target - 1):
 *
 * Consider tickets = [2, 3], k = 0
 * target = 2
 *
 * Timeline:
 * Time 1: Person 0 buys (1st ticket) → tickets = [1, 3]
 * Time 2: Person 1 buys (1st ticket) → tickets = [1, 2]
 * Time 3: Person 0 buys (2nd ticket) → DONE, leaves line
 * Time 4: Person 1 buys (2nd ticket) → Would continue but k already left
 *
 * Person 1 only got 1 turn before person 0 finished!
 * So they contribute min(3, 2-1) = min(3, 1) = 1
 *
 * Total: 2 + 1 = 3 seconds (not 2 + 2 = 4)
 */