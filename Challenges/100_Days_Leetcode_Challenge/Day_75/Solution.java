class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        int j = 0;
        for (j = 0; j < k; j++) {
            sum += arr[j];
        }
        int count = 0;
        if (sum / k >= threshold) {
            count += 1;
        }
        int i = 0;
        for (; j < arr.length; j++) {
            sum -= arr[i];
            sum += arr[j];
            i++;
            if (sum / k >= threshold) {
                count += 1;
            }
        }
        return count;
    }
}