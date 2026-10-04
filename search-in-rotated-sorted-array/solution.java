class Solution {
    public int RotatedSortedArray(int[] arr,int target,int si,int ei) {
        int Mid = si + (ei-si)/2;
        if(arr[Mid] == target)
        {
            return Mid;
        }
        if(si >= ei)
        {
            return -1;
        }
        if(arr[si] <= arr[Mid])
        {
            if(arr[si] <= target && arr[Mid] >= target)
            {
                return RotatedSortedArray(arr, target, si, Mid-1);
            }
            else
            {
                return RotatedSortedArray(arr, target, Mid+1, ei);
            }
        }
        else{
            if(arr[Mid] <= target && arr[ei] >= target)
            {
                return RotatedSortedArray(arr, target, Mid+1, ei);
            }
            else
            {
                return RotatedSortedArray(arr, target, si, Mid-1);
            }
        }
    }
    public int search(int[] nums, int target) {
        return RotatedSortedArray(nums, target, 0, nums.length-1);
    }
}