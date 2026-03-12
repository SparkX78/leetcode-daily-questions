import java.util.*;

class Solution {

    class DSU {

        int[] parent;
        int[] rank;

        DSU(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i=0;i<n;i++)
                parent[i] = i;
        }

        int find(int x){
            if(parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b){

            int pa = find(a);
            int pb = find(b);

            if(pa == pb) return false;

            if(rank[pa] < rank[pb])
                parent[pa] = pb;
            else if(rank[pb] < rank[pa])
                parent[pb] = pa;
            else{
                parent[pb] = pa;
                rank[pa]++;
            }

            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {

        int maxS = 0;
        for(int[] e : edges)
            maxS = Math.max(maxS, e[2]);

        int left = 1;
        int right = maxS * 2;
        int ans = -1;

        while(left <= right){

            int mid = left + (right-left)/2;

            if(canBuild(n, edges, k, mid)){
                ans = mid;
                left = mid + 1;
            }
            else
                right = mid - 1;
        }

        return ans;
    }

    boolean canBuild(int n, int[][] edges, int k, int target){

        DSU dsu = new DSU(n);

        int upgrades = 0;
        int count = 0;

        List<int[]> good = new ArrayList<>();
        List<int[]> upgrade = new ArrayList<>();

        for(int[] e : edges){

            int u=e[0], v=e[1], s=e[2], must=e[3];

            if(must==1){

                if(s < target) return false;

                if(!dsu.union(u,v)) return false;

                count++;
            }
            else{

                if(s >= target)
                    good.add(e);
                else if(s*2 >= target)
                    upgrade.add(e);
            }
        }

        for(int[] e : good){

            if(dsu.union(e[0],e[1])){
                count++;
                if(count==n-1) return true;
            }
        }

        for(int[] e : upgrade){

            if(upgrades == k) break;

            if(dsu.union(e[0],e[1])){
                upgrades++;
                count++;
                if(count==n-1) return true;
            }
        }

        return count == n-1;
    }
}