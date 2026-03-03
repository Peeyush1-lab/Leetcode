/**
 * Count Collisions on a Road - LeetCode Problem 2211
 *
 * Given a string representing directions of cars on a road ('L', 'R', or 'S'),
 * calculate the total number of collisions that will occur.
 *
 * Key Insight:
 * - Cars moving left ('L') at the beginning escape left → no collisions
 * - Cars moving right ('R') at the end escape right → no collisions
 * - Everything between first non-'L' and last non-'R' will eventually collide
 * - Count all moving cars (non-'S') in this collision zone
 *
 * Approach: Two Pointers + Observation
 * - Find the collision zone boundaries
 * - Count all moving cars within that zone
 * - Each moving car will collide exactly once
 *
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(1) - only using a few variables
 */
class Solution {
    /**
     * Count the total number of collisions on the road.
     */
    public int countCollisions(String directions) {
        int n = directions.length();
        int i = 0, j = n - 1;

        // Skip all leading 'L' cars - they escape to the left without colliding
        while (i < n && directions.charAt(i) == 'L') {
            i++;
        }

        // Skip all trailing 'R' cars - they escape to the right without colliding
        while (j >= 0 && directions.charAt(j) == 'R') {
            j--;
        }

        // Count all moving cars (non-'S') in the collision zone [i, j]
        // Every moving car in this zone will eventually collide
        int collisions = 0;
        for (int k = i; k <= j; k++) {
            if (directions.charAt(k) != 'S') {
                collisions++;
            }
        }

        return collisions;
    }
}

/**
 * Example Walkthrough:
 *
 * Input: "RLRSLL"
 *
 * Step 1: Find first non-'L' (left boundary)
 *   i = 0 (R is not L)
 *
 * Step 2: Find last non-'R' (right boundary)
 *   j = 5 (L is not R)
 *
 * Step 3: Count non-'S' in range [0, 5]
 *   R L R S L L
 *   ^ ^ ^   ^ ^  (5 moving cars)
 *
 * Output: 5
 *
 * ---
 *
 * Input: "LLRR"
 *
 * Step 1: i = 2 (first 'R')
 * Step 2: j = 1 (last 'L')
 * Since i > j, the collision zone is empty
 *
 * Output: 0
 */


/**
 * Edge Cases:
 *
 * 1. All L's: "LLLL" → 0 (all escape left)
 * 2. All R's: "RRRR" → 0 (all escape right)
 * 3. All S's: "SSSS" → 0 (already stationary)
 * 4. Single collision: "RL" → 2
 * 5. Mixed: "LLRSRR" → 1
 * 6. Empty collision zone: "LR" with leading L and trailing R → 0
 */