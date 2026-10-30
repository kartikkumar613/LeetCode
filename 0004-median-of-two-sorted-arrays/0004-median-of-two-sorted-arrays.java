import java.util.*;

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;

        int[] arr = new int[n + m];

        for (int i = 0; i < n; i++) {
            arr[i] = nums1[i];
        }

        for (int i = 0; i < m; i++) {
            arr[n + i] = nums2[i];
        }

        Arrays.sort(arr);

        int len = arr.length;

        if (len % 2 == 1) {
            return arr[len / 2];
        }

        return (arr[len / 2] + arr[len / 2 - 1]) / 2.0;
    }
}