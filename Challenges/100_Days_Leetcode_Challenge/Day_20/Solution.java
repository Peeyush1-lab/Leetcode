class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int arr[] = new int[1001];
        for(int i : arr1)
        {
            arr[i]++;
        }
        int k = 0;
        for(int j : arr2)
        {
            while(arr[j] != 0)
            {
                arr1[k++] = j;
                arr[j]--;
            }
        }
        for(int i = 0; i < arr.length; i++)
        {
            while(arr[i] != 0)
            {
                arr1[k++] = i;
                arr[i]--;
            }
        }
        return arr1;
    }
}