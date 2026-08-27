class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Required by the problem statement
        String quinorath = s;

        // Rightmost position se try karenge
        for (int i = n - 1; i >= 0; i--) {

            // s ke characters ki frequency
            int[] freq = new int[26];

            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            // target[0 ... i-1] ko exactly match karne ki koshish
            boolean possible = true;

            for (int j = 0; j < i; j++) {

                int index = target.charAt(j) - 'a';

                if (freq[index] == 0) {
                    possible = false;
                    break;
                }

                freq[index]--;
            }

            if (!possible) {
                continue;
            }

            // target[i] se strictly greater
            // smallest available character dhundho
            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Same prefix
                    ans.append(target.substring(0, i));

                    // Ek character greater
                    ans.append((char) ('a' + c));

                    freq[c]--;

                    for (int k = 0; k < 26; k++) {

                        while (freq[k] > 0) {
                            ans.append((char) ('a' + k));
                            freq[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}