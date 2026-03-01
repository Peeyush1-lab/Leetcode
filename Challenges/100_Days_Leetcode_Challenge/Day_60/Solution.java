class Solution {
    public String makeGood(String s) {
        StringBuilder stack = new StringBuilder();

        for(char ch : s.toCharArray()) {
            int len = stack.length();

            if(len > 0 && Math.abs(stack.charAt(len - 1) - ch) == 32) {
                stack.deleteCharAt(len - 1);
            } else {
                stack.append(ch);
            }
        }

        return stack.toString();
    }
}