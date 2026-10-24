class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        int sr = 0;
        int sc = 0;
        int er = matrix.length - 1;
        int ec = matrix[0].length - 1;

        ArrayList<Integer> list = new ArrayList<>();

        while (sr <= er && sc <= ec) {

            // top
            for (int j = sc; j <= ec; j++) {
                list.add(matrix[sr][j]);
            }

            // right
            for (int i = sr + 1; i <= er; i++) {
                list.add(matrix[i][ec]);
            }

            // bottom
            for (int j = ec - 1; j >= sc; j--) {
                if (sr == er) {
                    break;
                }
                list.add(matrix[er][j]);
            }

            // left
            for (int i = er - 1; i >= sr + 1; i--) {
                if (sc == ec) {
                    break;
                }
                list.add(matrix[i][sc]);
            }

            sr++;
            sc++;
            er--;
            ec--;
        }

        return list;
    }
}