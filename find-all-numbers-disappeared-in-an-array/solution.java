class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int arr[] = new int[nums.length];
        for(int i : nums)
        {
            arr[i-1]++;
        }
        for(int i = 0; i < nums.length; i++)
        {
            if(arr[i]==0)
            {
                result.add(i+1);
            }
        }
        return result;
    }
}