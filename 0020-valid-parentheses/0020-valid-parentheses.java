class Solution {
    public boolean isValid(String str) {
        Stack<Character> stk = new Stack<>();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                stk.push(c);
            }else{
                if(stk.isEmpty()){
                    return false;
                }
                if((stk.peek() == '(' && c == ')')
                  || (stk.peek() == '{' && c == '}')
                  || (stk.peek() == '[' && c == ']')){
                    stk.pop();
                  }else{
                    return false;
                  }
            }
        }
        if(stk.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}