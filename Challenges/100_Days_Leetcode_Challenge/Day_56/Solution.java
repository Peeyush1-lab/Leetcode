import java.util.*;
class Solution {
    public boolean isValid(String s) {
        Stack<Character> brackets = new Stack<>();
        String brac_start = "({[";
        if (s.length() == 1) {
            return false;
        } else {

            for (int i = 0; i < s.length(); i++) {
                if (brac_start.indexOf(s.charAt(i)) != -1) {
                    brackets.push(s.charAt(i));
                } else {
                    if (!brackets.empty() && (brackets.peek() + 1 == s.charAt(i) || brackets.peek() + 2 == s.charAt(i))) {
                        brackets.pop();
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        if (brackets.empty()) {
            return true;
        }
        return false;
    }
}