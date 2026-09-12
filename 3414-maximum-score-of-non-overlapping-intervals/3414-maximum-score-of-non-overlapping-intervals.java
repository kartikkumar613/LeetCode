import java.util.*;

class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [start, end, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by ending time
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        // prev[i] = index of the last interval that doesn't overlap i
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(arr, i, arr[i][0]);
        }

        // dp[k][i] = maximum score using at most k intervals
        // among first i intervals
        long[][] dp = new long[5][n + 1];

        // selected[k][i] = indices selected for dp[k][i]
        List<Integer>[][] selected = new ArrayList[5][n + 1];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                selected[k][i] = new ArrayList<>();
            }
        }

        for (int k = 1; k <= 4; k++) {

            for (int i = 1; i <= n; i++) {

                // Option 1: Don't take current interval
                dp[k][i] = dp[k][i - 1];
                selected[k][i] = new ArrayList<>(selected[k][i - 1]);

                // Option 2: Take current interval
                int idx = i - 1;
                int p = prev[idx];

                long takeScore = arr[idx][2] + dp[k - 1][p + 1];

                List<Integer> takeList =
                        new ArrayList<>(selected[k - 1][p + 1]);

                takeList.add(arr[idx][3]);

                if (takeScore > dp[k][i]) {
                    dp[k][i] = takeScore;
                    selected[k][i] = takeList;
                } else if (takeScore == dp[k][i]
                        && lexicographicallySmaller(takeList, selected[k][i])) {

                    selected[k][i] = takeList;
                }
            }
        }

        List<Integer> ans = selected[4][n];

        Collections.sort(ans);

        int[] result = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    // Find the last interval j < i such that:
    // arr[j][1] < start
    private int findPrevious(int[][] arr, int i, int start) {

        int lo = 0;
        int hi = i - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid][1] < start) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        List<Integer> x = new ArrayList<>(a);
        List<Integer> y = new ArrayList<>(b);

        Collections.sort(x);
        Collections.sort(y);

        int len = Math.min(x.size(), y.size());

        for (int i = 0; i < len; i++) {
            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i);
            }
        }

        return x.size() < y.size();
    }
}