class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        boolean inBracket = false;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                inBracket = true;
                word.setLength(0); // clear word buffer
            } else if (ch == ')') {
                inBracket = false;
                String key = word.toString();
                // Replace key + brackets with value or "?"
                result.append(map.getOrDefault(key, "?"));
            } else {
                if (inBracket) {
                    word.append(ch); // collect key inside brackets
                } else {
                    result.append(ch); // normal character outside brackets
                }
            }
        }

        return result.toString();
    }
}