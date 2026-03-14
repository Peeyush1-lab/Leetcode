class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int baseSatisfied = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                baseSatisfied += customers[i];
            }
        }
        int windowGain = 0;
        int maxGain = 0;

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 1) {
                windowGain += customers[i];
            }

            if (i >= minutes && grumpy[i - minutes] == 1) {
                windowGain -= customers[i - minutes];
            }

            maxGain = Math.max(maxGain, windowGain);
        }

        return baseSatisfied + maxGain;
    }
}