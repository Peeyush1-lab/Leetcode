class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) {
            return result;
        }
        for (int i = 0; i < n; i++) {
            if (k > 0) {
                for (int j = i + 1; j <= i + k; j++) {
                    result[i] += code[j % n];
                }
            } else {
                for (int j = i - Math.abs(k); j < i; j++) {
                    result[i] += code[(j + n) % n];
                }
            }
        }
        return result;
    }
}