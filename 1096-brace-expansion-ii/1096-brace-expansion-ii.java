import java.util.*;

class Solution {
    private String s;
    private int idx;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Handles union: A,B
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (idx < s.length() && s.charAt(idx) == ',') {
            idx++;
            result.addAll(parseTerm());
        }

        return result;
    }

    // Handles concatenation: AB
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (idx < s.length()
                && s.charAt(idx) != ','
                && s.charAt(idx) != '}') {

            Set<String> part;

            if (s.charAt(idx) == '{') {
                idx++;
                part = parseExpression();
                idx++; // skip '}'
            } else {
                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(idx)));
                idx++;
            }

            result = combine(result, part);
        }

        return result;
    }

    // Cartesian product for concatenation
    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}