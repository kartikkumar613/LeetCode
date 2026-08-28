class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
        }

        if (odd > 1) return "";

        if (mid != 0) {
            cnt[mid - 'a']--;
        }

        int half = n / 2;

        for (int i = 0; i < half; i++) {
            cnt[target.charAt(i) - 'a'] -= 2;
        }

        boolean possible = true;

        for (int x : cnt) {
            if (x < 0) {
                possible = false;
                break;
            }
        }

        if (possible) {

            String left = target.substring(0, half);

            StringBuilder right = new StringBuilder(left).reverse();

            String candidate;

            if (n % 2 == 0) {
                candidate = left + right;
            } else {
                candidate = left + mid + right;
            }

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        for (int i = half - 1; i >= 0; i--) {

            int idx = target.charAt(i) - 'a';

     
            cnt[idx] += 2;

            boolean valid = true;

            for (int x : cnt) {
                if (x < 0) {
                    valid = false;
                    break;
                }
            }

            if (!valid) continue;

            for (int j = idx + 1; j < 26; j++) {

                if (cnt[j] < 2) continue;

                cnt[j] -= 2;

                StringBuilder left = new StringBuilder();

                left.append(target, 0, i);

                left.append((char) ('a' + j));

                for (int k = 0; k < 26; k++) {
                    while (cnt[k] >= 2) {
                        left.append((char) ('a' + k));
                        cnt[k] -= 2;
                    }
                }

                String leftPart = left.toString();
                String rightPart =
                        new StringBuilder(leftPart).reverse().toString();

                if (n % 2 == 0) {
                    return leftPart + rightPart;
                } else {
                    return leftPart + mid + rightPart;
                }
            }
        }

        return "";
    }
}