class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder cb = new StringBuilder("");
        int i = 0;
        while(i != s.length())
        {
            char c = s.charAt(i);
            if((c >= 'A' && c <= 'Z'))
            {
                cb.append((char)(c+32));
            }
            else if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
            {
                cb.append(c);
            }
            i++;
        }
        String org = cb.toString();
        String rev = cb.reverse().toString();
        System.out.println(cb);
        System.out.println(cb.reverse());
        if(org.equals(rev))
        {
            return true;
        }
        return false;

    }
}