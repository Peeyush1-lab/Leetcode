import java.util.*;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;

        for (int num : arr1) {
            if (isValid(num, arr2, d)) {
                count++;
            }
        }

        return count;
    }

    private boolean isValid(int num, int[] arr2, int d) {
        int left = 0;
        int right = arr2.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (Math.abs(arr2[mid] - num) <= d) {
                return false;
            } else if (arr2[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return true;
    }
}