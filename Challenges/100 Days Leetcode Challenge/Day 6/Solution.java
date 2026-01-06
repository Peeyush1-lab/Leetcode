class Solution {
    public int maximumWealth(int[][] accounts) {
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < accounts.length; i++) {
            sum = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                sum += accounts[i][j];
            }
            if (temp < sum) {
                temp = sum;
            }
        }
        return temp;
    }
}