import java.util.*;

class Solution {
    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        // Assign a random hash to each distinct value
        Map<Integer, Long> hashMap = new HashMap<>();
        Random rnd = new Random(987654321L);

        long[] prefixXor = new long[n + 1];
        for (int i = 0; i < n; i++) {
            long h = hashMap.computeIfAbsent(nums[i], x -> rnd.nextLong());
            prefixXor[i + 1] = prefixXor[i] ^ h;
        }

        // Fenwick tree (BIT) for distinct-count queries
        int[] bit = new int[n + 2];

        // Sort query indices by r
        Integer[] order = new Integer[q];
        for (int i = 0; i < q; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> queries[a][1] - queries[b][1]);

        Map<Integer, Integer> lastPos = new HashMap<>();
        boolean[] ans = new boolean[q];

        int cur = -1; // last index (0-indexed) included in BIT
        for (int idx : order) {
            int l = queries[idx][0];
            int r = queries[idx][1];

            while (cur < r) {
                cur++;
                int v = nums[cur];
                Integer prev = lastPos.get(v);
                if (prev != null) {
                    bitUpdate(bit, prev + 1, -1, n);
                }
                bitUpdate(bit, cur + 1, 1, n);
                lastPos.put(v, cur);
            }

            int distinct = bitQuery(bit, r + 1) - bitQuery(bit, l);
            boolean evenFreq = prefixXor[r + 1] == prefixXor[l];

            ans[idx] = evenFreq && (distinct == k);
        }

        return ans;
    }

    private void bitUpdate(int[] bit, int i, int delta, int n) {
        for (; i <= n; i += i & (-i)) bit[i] += delta;
    }

    private int bitQuery(int[] bit, int i) {
        int sum = 0;
        for (; i > 0; i -= i & (-i)) sum += bit[i];
        return sum;
    }
}