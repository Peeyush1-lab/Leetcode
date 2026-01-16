class Solution {
    public int RotatedSortedArray(int[] arr,int target,int si,int ei) {
        int mid = si + (ei-si)/2;
        if(arr[mid] == target)
        {
            return mid;
        }
        if(si >= ei)
        {
            return -1;
        }
        if(arr[si] <= arr[mid])
        {
            if(arr[si] <= target && arr[mid] >= target)
            {
                return RotatedSortedArray(arr, target, si, mid-1);
            }
            else
            {
                return RotatedSortedArray(arr, target, mid+1, ei);
            }
        }
        else{
            if(arr[mid] <= target && arr[ei] >= target)
            {
                return RotatedSortedArray(arr, target, mid+1, ei);
            }
            else
            {
                return RotatedSortedArray(arr, target, si, mid-1);
            }
        }
    }
    public int search(int[] nums, int target) {
        return RotatedSortedArray(nums, target, 0, nums.length-1);
    }
}