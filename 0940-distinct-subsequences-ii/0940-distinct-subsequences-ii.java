class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long[] last = new long[26];
        long total = 1;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long newSubseq = total;
            total = (total + newSubseq - last[idx] + MOD) % MOD;

            last[idx] = newSubseq;
        }

        return (int) ((total - 1 + MOD) % MOD);
    }
}