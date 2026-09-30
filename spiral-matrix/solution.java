class  Solution{
    public List<Integer> spiralOrder(int matrix[][]) {
        int row = 0;
        int col = 0;
        int rlen = matrix.length-1;
        int clen = matrix[0].length-1;
        List<Integer> arr = new ArrayList<>();
        while (row <= rlen && col <= clen) {
            for (int i = col; i <= clen; i++) {
                arr.add(matrix[row][i]);
            }
            for (int i = row+1; i <= rlen; i++) {
                arr.add(matrix[i][clen]);
            }if (row < rlen) {
                for (int i = clen - 1; i >= col; i--) {
                    arr.add(matrix[rlen][i]);
                }
            }
            if (col < clen) {
                for (int i = rlen - 1; i > row; i--) {
                    arr.add(matrix[i][col]);
                }
            }
            row++;
            col++;
            rlen--;
            clen--;
        }
        return arr;
    }
}