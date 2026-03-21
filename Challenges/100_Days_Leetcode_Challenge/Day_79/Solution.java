/**
 * Flipping an Image - LeetCode Problem 832
 *
 * Given an n×n binary matrix, flip it horizontally then invert it.
 * - Flip horizontally: Reverse each row
 * - Invert: 0→1, 1→0
 *
 * Example:
 * Input:  [[1,1,0],[1,0,1],[0,0,0]]
 * Flip:   [[0,1,1],[1,0,1],[0,0,0]]
 * Invert: [[1,0,0],[0,1,0],[1,1,1]]
 *
 * Key Optimization: Do both operations simultaneously!
 * Use two pointers to swap and invert in one pass.
 *
 * Approach: In-Place Two Pointers
 * For each row:
 * 1. Use left (j) and right (mj) pointers
 * 2. Swap elements and invert both
 * 3. Move pointers toward center
 *
 * Time Complexity: O(n²) where n is the dimension
 * Space Complexity: O(1) - in-place modification
 */
class Solution {
    /**
     * Flip and invert the binary image.
     *
     * @param image n×n binary matrix
     * @return the flipped and inverted image
     */
    public int[][] flipAndInvertImage(int[][] image) {
        int j;      // Left pointer
        int mj;     // Right pointer

        // Process each row
        for (int i = 0; i < image.length; i++) {
            j = 0;                          // Start from left
            mj = image[0].length - 1;       // Start from right

            // Process until pointers meet or cross
            while (j <= mj) {
                // Store inverted left value temporarily
                int temp = 1 - image[i][j];

                // Put inverted right value at left position (flip + invert)
                image[i][j] = 1 - image[i][mj];

                // Put inverted left value at right position (flip + invert)
                image[i][mj] = temp;

                // Move pointers toward center
                j++;
                mj--;
            }
        }

        return image;
    }
}

/**
 * Detailed Example Walkthrough:
 *
 * Input: image = [[1,1,0],[1,0,1],[0,0,0]]
 *
 * Row 0: [1, 1, 0]
 *
 *   Iteration 1: j=0, mj=2
 *     temp = 1 - image[0][0] = 1 - 1 = 0
 *     image[0][0] = 1 - image[0][2] = 1 - 0 = 1
 *     image[0][2] = temp = 0
 *     Row becomes: [1, 1, 0]
 *     j=1, mj=1
 *
 *   Iteration 2: j=1, mj=1 (same position - middle element)
 *     temp = 1 - image[0][1] = 1 - 1 = 0
 *     image[0][1] = 1 - image[0][1] = 0
 *     image[0][1] = temp = 0
 *     Row becomes: [1, 0, 0]
 *     j=2, mj=0 (loop ends)
 *
 * Row 1: [1, 0, 1]
 *
 *   Iteration 1: j=0, mj=2
 *     temp = 1 - 1 = 0
 *     image[1][0] = 1 - 1 = 0
 *     image[1][2] = 0
 *     Row becomes: [0, 0, 0]
 *     j=1, mj=1
 *
 *   Iteration 2: j=1, mj=1 (middle)
 *     temp = 1 - 0 = 1
 *     image[1][1] = 1 - 0 = 1
 *     Row becomes: [0, 1, 0]
 *
 * Row 2: [0, 0, 0]
 *
 *   Iteration 1: j=0, mj=2
 *     temp = 1 - 0 = 1
 *     image[2][0] = 1 - 0 = 1
 *     image[2][2] = 1
 *     Row becomes: [1, 0, 1]
 *
 *   Iteration 2: j=1, mj=1
 *     temp = 1 - 0 = 1
 *     image[2][1] = 1 - 0 = 1
 *     Row becomes: [1, 1, 1]
 *
 * Final Result: [[1,0,0],[0,1,0],[1,1,1]]
 */