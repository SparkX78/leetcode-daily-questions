class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        HashSet<String> bankSet = new HashSet<>();
        for(int i = 0; i < bank.length; i++){
            bankSet.add(bank[i]);
        }
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int level = 0;
        queue.offer(startGene);
        visited.add(startGene);
        while(!queue.isEmpty()){
            int n = queue.size();
            while(n-- > 0){
                String curr = queue.poll();
                if(curr.equals(endGene)){
                    return level;
                }
                
                for(char ch : "ACGT".toCharArray()){
                    for(int j = 0; j < curr.length(); j++){
                        StringBuilder neighbour = new StringBuilder(curr);
                        neighbour.setCharAt(j, ch);
                        String c = neighbour.toString();
                        if(bankSet.contains(c) && !visited.contains(c)){
                            visited.add(c);
                            queue.offer(c);
                        }
                    }
                }
                
                
            }
            level++;
            
        }
        return -1;
    }
}