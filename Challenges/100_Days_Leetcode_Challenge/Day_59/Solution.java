class Solution {
    public String reversePrefix(String word, char ch) {
        int idx = word.indexOf(ch);
        if (idx == -1) return word;

        char[] arr = word.toCharArray();
        for (int l = 0, r = idx; l < r; l++, r--) {
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        return new String(arr);
    }
}