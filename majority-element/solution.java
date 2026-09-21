class Solution {
    public int majorityElement(int[] nums) {
        int num = 0;
        int count = 0;
        for(int i : nums)
        {
            if(count == 0)
            {
                num = i;   
            }

            if(num == i)
            {
                count++;
            }
            else
            {
                count--;
            }
        }
        return num;
    }
}