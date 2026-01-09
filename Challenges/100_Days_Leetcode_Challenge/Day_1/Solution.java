class Solution {
    public static int[] decrypt(int[] code, int k) {
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
    public static void main(String[] args) {
        // Example 1:
        int code[] = {5,7,1,4};
        int k = 3;

        // Example 2:
        // int code[] = {1,2,3,4};
        // int k = 0;

        // Example 3:
        // int code[] = {2,4,9,3}
        // int k = -2;

        int arr[] = decrypt(code, k);
        System.out.println("Result: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}