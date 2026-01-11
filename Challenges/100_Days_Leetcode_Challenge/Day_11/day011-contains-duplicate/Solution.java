import java.util.*;
class Solution{
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> arr = new HashSet<>();
        for (int i : nums) {
            if(!arr.add(i)){
                return true;
            }
        }
        return false;
    }
}