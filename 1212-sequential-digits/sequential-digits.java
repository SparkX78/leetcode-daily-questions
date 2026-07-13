class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String result = "123456789";
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        List<Integer> ans = new ArrayList<>();
        for(int i = minLen; i <= maxLen; i++){
            for(int start = 0; start+i <= 9; start++ ){
                String substr = result.substring(start, start+i);
                int num = Integer.parseInt(substr);
                if(num >= low && num <= high){
                    ans.add(num);
                }
            }

        }
        return ans;
    }
}