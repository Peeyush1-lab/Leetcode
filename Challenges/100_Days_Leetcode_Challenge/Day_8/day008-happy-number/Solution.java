class Solution {
    public static boolean isHappy(int n) {
        int res;
        while (n != 1 && n != 4) {
            res = 0;
            while (n != 0) {
                res += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = res;
        }
        return n == 1;
    }
}