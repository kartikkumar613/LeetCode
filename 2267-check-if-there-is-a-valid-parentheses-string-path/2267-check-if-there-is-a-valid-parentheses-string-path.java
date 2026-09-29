class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Total number of characters must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Starting with ')' is impossible
        if (grid[0][0] == ')') {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][(m + n) / 2 + 1];

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0)
                    continue;

                for (int balance = 0; balance <= (m + n) / 2; balance++) {

                    if (grid[i][j] == '(') {

                        if (i > 0 && balance > 0) {
                            dp[i][j][balance] |= dp[i - 1][j][balance - 1];
                        }

                        if (j > 0 && balance > 0) {
                            dp[i][j][balance] |= dp[i][j - 1][balance - 1];
                        }

                    } else {

                        if (i > 0 && balance + 1 <= (m + n) / 2) {
                            dp[i][j][balance] |= dp[i - 1][j][balance + 1];
                        }

                        if (j > 0 && balance + 1 <= (m + n) / 2) {
                            dp[i][j][balance] |= dp[i][j - 1][balance + 1];
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}