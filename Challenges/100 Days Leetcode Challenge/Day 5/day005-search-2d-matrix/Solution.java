class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length-1;
        int j = 0;
        int m = 0;
        int n = matrix[0].length-1;
        while(i >= m && j <= n)
        {
            if(matrix[i][j] == target)
            {
                return true;
            }
            else if(matrix[i][j] < target)
            {
                j++;
            }
            else
            {
                i--;
            }
        }
        return false;
    }
}