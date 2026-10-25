class Solution {
public int maxProduct(int[] nums) {
int mp = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {
        int cp = 1;

        for (int j = i; j < nums.length; j++) {
            cp = cp * nums[j];
            mp = Math.max(cp, mp);
        }
    }

    return mp;
}
}
