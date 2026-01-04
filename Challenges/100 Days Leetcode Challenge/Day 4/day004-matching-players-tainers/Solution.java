class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int count = 0;
        int i = 0;
        int j = 0;
        int currPlayer = players[j];
        while(i < trainers.length)
        {
            if(trainers[i] >= currPlayer)
            {
                count++;
                if(j == players.length-1)
                {
                    break;
                }
                currPlayer = players[++j];
            }
            i++;
        }
        return count;
    }
}