import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = readInt();
        int m = readInt();
        int d = readInt();
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
        q.add(new Connection(1, Integer.MAX_VALUE));
        boolean[] vis = new boolean[n+1];
        vis[1] = true;
        bestCaps[1] = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Connection curr = q.poll();
            ArrayList<Connection> temp = x[curr.to];
            for(Connection i : temp){
                if(!vis[i.to]){
                    q.add(i);
                    bestCaps[i.to] = Math.min(curr.cap,i.cap);
                    vis[i.to] = true;
                    continue;
                }
                if(Math.min(curr.cap,i.cap) > bestCaps[i.to]){
                    bestCaps[i.to] = Math.min(curr.cap,i.cap);
                    q.add(new Connection(i.to,bestCaps[i.to]));
                }
            }
            
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < d; i++){
            min = Math.min(min,bestCaps[readInt()]);
        }
        System.out.println(min);
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
            return b.cap-cap;
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