class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        int[] best = new int[n + 1];
        Arrays.fill(best, INF);

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int prefix = 0;
        int ans = INF;

        for (int i = 1; i <= n; i++) {
            prefix += arr[i - 1];

            best[i] = best[i - 1];

            if (map.containsKey(prefix - target)) {
                int start = map.get(prefix - target);
                int len = i - start;

                if (best[start] != INF) {
                    ans = Math.min(ans, len + best[start]);
                }

                best[i] = Math.min(best[i], len);
            }

            map.put(prefix, i);
        }

        return ans == INF ? -1 : ans;
    }
}