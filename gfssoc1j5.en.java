import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br;
    static StringTokenizer st; 
    static int[][] deg;
    static ArrayList<Integer>[] x;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = readInt();
        int m = readInt();
        int t = readInt();
        x = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++){
            x[i] = new ArrayList<Integer>();
        }
        deg = new int[n+1][n+1];
        boolean[] s = new boolean[n+1];
        for(int i = 0; i < m; i++){
            x[readInt()].add(readInt());
        }
        int q = readInt();
        for(int i = 0; i < q; i++){
            int a = readInt();
            int b = readInt();
            if(!s[a]){
                bfs(a,n);
                s[a] = true;
            }
            System.out.println((deg[a][b] == 0) ? "Not enough hallways!" : deg[a][b]*t);
            
        }
    }
    public static void bfs(int root,int n){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[n+1];
        q.add(root);
        vis[root] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            ArrayList<Integer> temp = x[curr];
            for(int i : temp){
                if(vis[i])
                    continue;
                q.add(i);
                vis[i] = true;
                deg[root][i] = deg[root][curr]+1; 
            }
        }
        
    }
    static String next () throws IOException {
        while (st == null || ! st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static long readLong () throws IOException {
        return Long.parseLong(next());
    }
    static int readInt () throws IOException {
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