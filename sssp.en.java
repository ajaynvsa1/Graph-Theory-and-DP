import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = readInt();
        int m = readInt();
        ArrayList<Connection>[] x = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++){
            x[i] = new ArrayList<Connection>();
        }
        for(int i = 0; i < m; i++){
            int a = readInt();
            int b = readInt();
            int cap = readInt();
            x[a].add(new Connection(b,cap));
            x[b].add(new Connection(a,cap));
        }
        int[] bestCaps = new int[n+1];
        //Djikstra
        PriorityQueue<Connection> q = new PriorityQueue<>();
        q.add(new Connection(1,0));
        boolean[] vis = new boolean[n+1];
        vis[1] = true;
        bestCaps[1] = 0;
        while(!q.isEmpty()){
            Connection curr = q.poll();
            ArrayList<Connection> temp = x[curr.to];
            for(Connection i : temp){
                if(!vis[i.to]){
                    q.add(i);
                    bestCaps[i.to] = bestCaps[curr.to]+i.cap;
                    vis[i.to] = true;
                    continue;
                }
                if(bestCaps[curr.to]+i.cap < bestCaps[i.to]){
                    bestCaps[i.to] = bestCaps[curr.to]+i.cap;
                    q.add(i);
                }
            }
            
        }
      for(int i=1; i<=n; i++)
			 System.out.println(vis[i]? bestCaps[i] : -1);
    }
    public static class Connection implements Comparable<Connection>{
        int to;
        int cap;
        Connection(){
            to = -1;
            cap = -1;
        }
        Connection(int t, int c){
            to = t;
            cap = c;
        }
        public int compareTo(Connection b){
            return cap - b.cap;
        }
    }

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
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