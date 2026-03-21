/**
 * Matrix Diagonal Sum - LeetCode Problem 1572
 *
 * Given a square matrix, return the sum of both diagonals.
 * - Primary diagonal: top-left to bottom-right (row == col)
 * - Secondary diagonal: top-right to bottom-left (row + col == n-1)
 * - Don't count the center element twice in odd-sized matrices
 *
 * Example:
 * [[1,2,3],      Primary: 1,5,9 (sum=15)
 *  [4,5,6],      Secondary: 3,5,7 (sum=15)
 *  [7,8,9]]      Center overlap: 5
 *                Total: 15+15-5 = 25
 *
 * Approach: Single Pass
 * 1. Add both diagonal elements in each iteration
 * 2. If matrix size is odd, subtract center (counted twice)
 *
 * Time Complexity: O(n) where n is the matrix dimension
 * Space Complexity: O(1) - only using sum variable
 */
class Solution {
    /**
     * Calculate the sum of matrix diagonals.
     */
    public int diagonalSum(int[][] mat) {
        int sum = 0;
        int n = mat.length;

        // Iterate through each row
        for (int i = 0; i < n; i++) {
            // Add primary diagonal element (row i, column i)
            sum += mat[i][i];

            // Add secondary diagonal element (row i, column n-i-1)
            sum += mat[n - i - 1][i];
        }

        // If matrix size is odd, center element was counted twice
        // Subtract it once to get correct sum
        if (n % 2 != 0) {
            return sum - mat[n / 2][n / 2];
        }

        return sum;
    }
}

/**
 * Detailed Example Walkthrough:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * n = 3 (odd size)
 *
 * Initial: sum = 0
 *
 * i=0:
 *   Primary: mat[0][0] = 1
 *   Secondary: mat[3-0-1][0] = mat[2][0] = 7
 *   sum = 0 + 1 + 7 = 8
 *
 * i=1:
 *   Primary: mat[1][1] = 5
 *   Secondary: mat[3-1-1][1] = mat[1][1] = 5 (same element!)
 *   sum = 8 + 5 + 5 = 18
 *
 * i=2:
 *   Primary: mat[2][2] = 9
 *   Secondary: mat[3-2-1][2] = mat[0][2] = 3
 *   sum = 18 + 9 + 3 = 30
 *
 * After loop: sum = 30
 *
 * n is odd (3 % 2 != 0), so subtract center:
 *   Center: mat[3/2][3/2] = mat[1][1] = 5
 *   Final: 30 - 5 = 25
 *
 * Result: 25
 */