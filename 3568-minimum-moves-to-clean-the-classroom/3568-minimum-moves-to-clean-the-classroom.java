import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        // Each litter gets a unique bit
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int sr = 0, sc = 0;
        int litterCount = 0;

        // Find S and assign IDs to L
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } 
                else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int allMask = (1 << litterCount) - 1;

        /*
         * visited[row][col][energy][mask]
         *
         * mask:
         * 1 -> litter still remaining
         * 0 -> litter collected
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        Queue<int[]> q = new LinkedList<>();

        // State: row, col, remainingEnergy, remainingLitterMask
        q.offer(new int[]{sr, sc, energy, allMask});

        visited[sr][sc][energy][allMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];

                // All litter collected
                if (mask == 0) {
                    return moves;
                }

                // No energy -> cannot make another move
                if (e == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Boundary / obstacle
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n ||
                        classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char ch = classroom[nr].charAt(nc);

                    // Every move costs 1 energy
                    int newEnergy = e - 1;

                    // R always resets energy to maximum
                    if (ch == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                  
                    if (ch == 'L') {
                        int id = litterId[nr][nc];
                        newMask &= ~(1 << id);
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        q.offer(new int[]{
                            nr, nc, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}