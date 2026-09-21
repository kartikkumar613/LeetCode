class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            int rem = num % k;

            long[] next = new long[k];

            // Subarray containing only num
            next[rem] = 1;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                int newRem = (int) ((long) r * rem % k);
                next[newRem] += dp[r];
            }

            // Add all subarrays ending at current position
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}