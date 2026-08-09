class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : tasks){
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int freq : map.values()){
            pq.offer(freq);
        }
        int time = 0;
        while(!pq.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            for(int i = 1; i <= n+1; i++){
                if(!pq.isEmpty()){
                    int freq = pq.poll();
                    freq--;
                    temp.add(freq);
                }

            }

            for(int freq : temp){
                if(freq > 0){
                    pq.offer(freq);
                }
            }
            if(pq.isEmpty()){
                time += temp.size();
            }
            else{
                time += n+1;
            }

        }
        return time;
        

    }
}