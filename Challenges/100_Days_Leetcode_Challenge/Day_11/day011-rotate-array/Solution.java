class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length - 1;
        k = k%(len+1);
        reverse(nums, 0, len);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len);
    }

    private void reverse(int arr[], int left, int right)
    {
        while(left < right)
        {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}