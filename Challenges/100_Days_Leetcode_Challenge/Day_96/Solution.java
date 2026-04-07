class Solution {
    public int maximumCount(int[] nums) {
        int neg = binarySearch(nums, 0);
        int pos = nums.length - binarySearch(nums, 1);
        return Math.max(neg, pos);
    }

    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }
}