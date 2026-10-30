class Solution {
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length-1;
        int MaxWater = 0;
        int res;
        int min;
        while(start < end)
        {
            res = (end-start)*Math.min(height[start],height[end]);
            MaxWater = Math.max(MaxWater,res);
            if(height[start]<height[end])
            {
                start++;
            }
            else
            {
                end--;
            }
        }
        return MaxWater;
    }
}