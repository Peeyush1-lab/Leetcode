class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        int count = 0;
        int j = 0;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                if(j == 1)
                {
                    break;
                }
                count = 0;
            }
            else
            {
                j = 1;
                count++;
            }
            i--;
        }
        return count;
    }
}