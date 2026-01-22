import java.util.*;
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> list=new ArrayList<>();
        n=0;

        for(int i=1;i<=target[target.length-1] ;i++){
            if( i==target[n]) {
                n++;
                list.add("Push");
            }
            else{
                list.add("Push");
                list.add("Pop");
            }
        }
        return list;
    }
}