import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsethelper(0, nums, res, new ArrayList<>());
        return res;
    }

    public void subsethelper(int i, int[] nums, List<List<Integer>> res, List<Integer> arr) {
        if (i == nums.length) {
            res.add(new ArrayList<>(arr));
            return;
        }

        // Include current element
        arr.add(nums[i]);
        subsethelper(i + 1, nums, res, arr);
        arr.remove(arr.size() - 1);

        // Exclude current element
        subsethelper(i + 1, nums, res, arr);
    }
}