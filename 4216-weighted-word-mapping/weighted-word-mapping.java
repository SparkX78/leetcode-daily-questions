class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder str = new StringBuilder();
        for(String word : words){
            int sum = 0;
            for(char ch : word.toCharArray()){
                sum += weights[ch-'a'];
            }
            sum = sum % 26;
            str.append((char)('z' - sum));

        }
        return str.toString();
    }
}