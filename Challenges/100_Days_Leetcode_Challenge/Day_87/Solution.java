class Solution {
    public boolean predictTheWinner(int[] nums) {
        return helper(nums, 0, nums.length - 1) >= 0;
    }

    public int helper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int pickStart = nums[start] - helper(nums, start + 1, end);
        int pickEnd = nums[end] - helper(nums, start, end - 1);

        return Math.max(pickStart, pickEnd);
    }
}