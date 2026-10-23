import java.util.*;

class Solution {

public static int[] removeDuplicates(int[] arr) {
    int[] temp = new int[arr.length];
    int k = 0;

    for (int i = 0; i < arr.length; i++) {
        boolean duplicate = false;

        for (int j = 0; j < k; j++) {
            if (arr[i] == temp[j]) {
                duplicate = true;
                break;
            }
        }

        if (!duplicate) {
            temp[k++] = arr[i];
        }
    }

    return Arrays.copyOf(temp, k);
}

public int[] intersection(int[] nums1, int[] nums2) {
    int[] arr = new int[nums1.length];
    int k = 0;

    for (int i = 0; i < nums1.length; i++) {
        for (int j = 0; j < nums2.length; j++) {
            if (nums1[i] == nums2[j]) {
                arr[k++] = nums1[i];
                break;
            }
        }
    }

    return removeDuplicates(Arrays.copyOf(arr, k));
}

}
