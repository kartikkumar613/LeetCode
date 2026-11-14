class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        for (int k = l; k <= r; k++) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums.get(i);
            }
            if (sum > 0) {
                minSum = Math.min(minSum, sum);
            }
            for (int i = k; i < n; i++) {
                sum += nums.get(i);
                sum -= nums.get(i - k);
                if (sum > 0) {
                    minSum = Math.min(minSum, sum);
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}