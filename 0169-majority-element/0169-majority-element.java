class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int count = 1;
        int i = 1;
        
        while(i<nums.length){
            if(nums[i] == nums[i-1]){
                count++;
                i++;
            }
            else{
                count =1;
                i++;
            }
            if(count > nums.length/2){
                return nums[i-1];
            
            }  
        
        }
        return nums[0];
    }
}