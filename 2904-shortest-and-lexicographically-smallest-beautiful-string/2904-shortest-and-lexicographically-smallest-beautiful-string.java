class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        for(int i=0;i<s.length();i++){
            for(int j=0;j<s.length();j++){
                int one = 0;
                for(int a=i;a<=j;a++){
                    if(s.charAt(a)=='1'){
                        one++;
                    }
                }
                if(one == k){
                    String curr = s.substring(i,j+1);

                    if(ans.equals("")){
                        ans = curr;
                    }
                    else if(curr.length() < ans.length()){
                        ans = curr;
                    }
                    else if(curr.length()==ans.length() && curr.compareTo(ans)<0){
                        ans = curr;
                    }
                }
            }
        }
        return ans;
         
    }
}