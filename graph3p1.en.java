import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int min;
    static ArrayList<Integer>[] x;
    static int[] degree;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = nextInt();
        m = nextInt();
        degree = new int[n];
        x = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            degree[i] = (int) Math.pow(2, 31) - 1;
        }
        for(int i = 0; i < m; i++){
            int addA = nextInt();
            int addB = nextInt();
            if (x[addA - 1] == null) {
                x[addA - 1] = new ArrayList<>();
            }
            x[addA-1].add(addB);
            if (x[addB - 1] == null) {
                x[addB - 1] = new ArrayList<>();
            }
            x[addB-1].add(addA);
        }
        int k = nextInt();
        min = n;
        for(int i = 0; i < k; i++){
            if(i==k-1){
                min = bfs(nextInt(),true);
            }
            else{
                bfs(nextInt(),false);
            }
            
        }
        System.out.println(min);
    }
    static int bfs(int root,boolean last){
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        boolean[] visited = new boolean[n];
        visited[root-1] = true;
        degree[root-1] = 0;
        int curr = 0;
        int max = 0;
        while(!q.isEmpty()){
            curr= q.poll();
            for(int i : x[curr-1]){
                if(!visited[i-1]){
                   
                    int currDeg = degree[i-1];
                    degree[i-1] = Math.min(degree[i-1],degree[curr-1]+1);
                    if(currDeg != degree[i-1]) {
                       q.add(i);
                    } else {
                        if(last){
                            q.add(i);    
                        }
                    }
                    if(degree[i-1] > max){
                        max = degree[i-1];
                    }
                    visited[i-1] = true;
                }

            }
        }
        //System.out.println("\n" + curr + " " + degree[curr-1]);

        return max;
    }
    static String next () throws IOException {
        while (st == null || ! st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong () throws IOException {
        return Long.parseLong(next());
    }
    static int nextInt () throws IOException {
        return Integer.parseInt(next());
    }
    static double readDouble () throws IOException {
        return Double.parseDouble(next());
    }
    static char readCharacter () throws IOException {
        return next().charAt(0);
    }
    static String readLine () throws IOException {
        return br.readLine().trim();
    }
}
