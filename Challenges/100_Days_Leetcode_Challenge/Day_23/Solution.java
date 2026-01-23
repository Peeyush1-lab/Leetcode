import java.util.*;
class Solution {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int day = 0;
        int eaten = 0;
        int n = apples.length;

        while (day < n || !pq.isEmpty()) {
            if (day < n && apples[day] > 0) {
                pq.offer(new int[]{day + days[day], apples[day]});
            }

            while (!pq.isEmpty() && (pq.peek()[0] <= day || pq.peek()[1] == 0)) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                int[] batch = pq.poll();
                batch[1]--;
                eaten++;
                if (batch[1] > 0) {
                    pq.offer(batch);
                }
            }
            day++;
        }

        return eaten;
    }
}
