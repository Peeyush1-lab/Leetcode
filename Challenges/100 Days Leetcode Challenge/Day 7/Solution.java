class Solution {
    public int[] sortArray(int[] nums) {
        mergesort(nums,0,nums.length-1);
        return nums;
    }
    public void mergesort(int arr[], int si, int ei)
    {
        if(si>=ei)
        {
            return;
        }
        int mid = si+(ei-si)/2;
        mergesort(arr,si,mid);
        mergesort(arr,mid+1,ei);
        merge(arr,si,mid,ei);
    }
    public void merge(int arr[], int si, int mid, int ei)
    {
        int i = si;
        int j = mid+1;
        int k = 0;
        int temp[] = new int[ei-si+1];
        while(i <= mid && j <= ei)
        {
            if(arr[i] < arr[j])
            {
                temp[k++] = arr[i++];
            }
            else
            {
                temp[k++] = arr[j++];
            }
        }
        while(i <= mid)
        {
            temp[k++] = arr[i++];
        }
        while(j <= ei)
        {
            temp[k++] = arr[j++];
        }
        j = si;
        for(i = 0; i < k; i++)
        {
            arr[j++] = temp[i];
        }
    }
}