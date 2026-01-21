import java.util.*;
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int ans[] = new int[len];
        Stack<Integer> arr = new Stack<>();
        for(int i = 0; i < len; i++)
        {
            while(!arr.isEmpty() && temperatures[i]>temperatures[arr.peek()])
            {
                int idx = arr.pop();
                ans[idx] = i-idx;
            }
            arr.push(i);
        }
        return ans;
    }
}