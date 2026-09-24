class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit=0;
        int minProfit=prices[0];
    for(int price:prices){
        minProfit=Math.min(minProfit,price);
        maxProfit=Math.max(maxProfit,price-minProfit);
    }
    return maxProfit;
    }
}