class Solution {
    public int[] arrayRankTransform(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int r = 1;
        for(int i = 0; i< arr.length; i++){
            if(!map.containsKey(sorted[i])){
                map.put(sorted[i], r++);
            }
            
        }
        int[] result = new int[arr.length];
        for(int i =0 ; i < arr.length; i++){
            result[i] = map.get(arr[i]);
        }
        return result;
    }
}