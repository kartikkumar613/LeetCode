import java.util.*;

class Solution {
    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();
    int leftSize = 0, rightSize = 0;
    long leftSum = 0, rightSum = 0;
    void addMap(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }
    void removeMap(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
    void add(int val) {
        if (left.isEmpty() || val <= left.lastKey()) {
            addMap(left, val);
            leftSize++;
            leftSum += val;
        } else {
            addMap(right, val);
            rightSize++;
            rightSum += val;
        }
        balance();
    }
    void remove(int val) {
        if (left.containsKey(val)) {
            removeMap(left, val);
            leftSize--;
            leftSum -= val;
        } else {
            removeMap(right, val);
            rightSize--;
            rightSum -= val;
        }
        balance();
    }
    void balance() {
        int target = (leftSize + rightSize + 1) / 2;
        while (leftSize > target) {
            int val = left.lastKey();
            removeMap(left, val);
            leftSize--;
            leftSum -= val;

            addMap(right, val);
            rightSize++;
            rightSum += val;
        }
        while (leftSize < target) {
            int val = right.firstKey();
            removeMap(right, val);
            rightSize--;
            rightSum -= val;

            addMap(left, val);
            leftSize++;
            leftSum += val;
        }
    }
    long getCost() {
        long median = left.lastKey();
        long costLeft = median * leftSize - leftSum;
        long costRight = rightSum - median * rightSize;
        return costLeft + costRight;
    }
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }
        cost[0] = getCost();
        for (int i = x; i < n; i++) {
            remove(nums[i - x]);
            add(nums[i]);
            cost[i - x + 1] = getCost();
        }
        long INF = Long.MAX_VALUE / 2;
        long[][] dp = new long[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[j][i] = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }
        return dp[k][n];
    }
}