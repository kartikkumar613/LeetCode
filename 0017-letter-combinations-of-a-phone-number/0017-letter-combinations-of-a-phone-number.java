import java.util.*;

class Solution {

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        if (digits.length() == 0) {
            return result;
        }

        backtrack(digits, 0, "", result);

        return result;
    }

    public void backtrack(String digits, int index,
                          String current, List<String> result) {

        if (index == digits.length()) {
            result.add(current);
            return;
        }

        String letters = "";

        switch (digits.charAt(index)) {

            case '2':
                letters = "abc";
                break;

            case '3':
                letters = "def";
                break;

            case '4':
                letters = "ghi";
                break;

            case '5':
                letters = "jkl";
                break;

            case '6':
                letters = "mno";
                break;

            case '7':
                letters = "pqrs";
                break;

            case '8':
                letters = "tuv";
                break;

            case '9':
                letters = "wxyz";
                break;
        }

        for (int i = 0; i < letters.length(); i++) {

            backtrack(digits, index + 1,
                      current + letters.charAt(i), result);
        }
    }
}