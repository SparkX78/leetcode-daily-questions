class Solution {

    public int ladderLength(String beginWord,
                            String endWord,
                            List<String> wordList) {

        HashSet<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);

        HashSet<String> visited = new HashSet<>();

        visited.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int s = 0; s < size; s++) {

                String word = queue.poll();

                if (word.equals(endWord)) {
                    return level;
                }

                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {

                    char original = chars[i];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        chars[i] = ch;

                        String newWord = new String(chars);

                        if (set.contains(newWord)
                                && !visited.contains(newWord)) {

                            visited.add(newWord);

                            queue.offer(newWord);
                        }
                    }

                    chars[i] = original;
                }
            }

            level++;
        }

        return 0;
    }
}