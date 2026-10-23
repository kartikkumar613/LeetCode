// class Solution {
//     public int[] searchRange(int[] nums, int target) {
//         int[] result = new int[2];
//         result[0]=result[1]=-1;
//         for(int i=0;i<nums.length;i++){
//             if(nums[i]==target){
//                 result[0]=i;
//                 while(i<nums.length && nums[i+1]==target){
//                     i++;
//                     result[1]=i;
//                     break;
//                 }
//             } 
//         }
//         return result;

//     }
// }
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};

        int start = 0;
        int end = nums.length - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(nums[mid] == target) {
                result[0] = mid;
                end = mid - 1; 
            }
            else if(nums[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        start = 0;
        end = nums.length - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if(nums[mid] == target) {
                result[1] = mid;
                start = mid + 1; 
            }
            else if(nums[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return result;
    }
}