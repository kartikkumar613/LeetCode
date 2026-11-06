import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int k = queries.length;
        int[] ans = new int[k];
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int[][] q = new int[k][2];
        for (int i = 0; i < k; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        Arrays.sort(q, (a, b) -> a[0] - b[0]);
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        for (int i = 0; i < k; i++) {
            int start = q[i][0] - x;
            int end = q[i][0];
            while (right < logs.length && logs[right][1] <= end) {
                map.put(logs[right][0],
                    map.getOrDefault(logs[right][0], 0) + 1);
                right++;
            }
            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];
                map.put(server, map.get(server) - 1);
                if (map.get(server) == 0) {
                    map.remove(server);
                }
                left++;
            }
            ans[q[i][1]] = n - map.size();
        }
        return ans;
    }
}