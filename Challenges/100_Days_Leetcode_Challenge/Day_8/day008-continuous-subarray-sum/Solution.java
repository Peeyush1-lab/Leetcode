import java.util.*;
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefix = 0;

        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (k != 0) prefix %= k;

            if (map.containsKey(prefix)) {
                if (i - map.get(prefix) >= 2) return true;
            } else {
                map.put(prefix, i);
            }
        }

        return false;
    }
}