class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minProfit = prices[0];
        for(int i : prices)
        {
            minProfit = Math.min(minProfit, i);
            maxProfit = Math.max(maxProfit,i-minProfit);
        }
        return maxProfit;
    }
}