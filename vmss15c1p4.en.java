import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br;
    static StringTokenizer st; 
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int lim = readInt();
        int n = readInt();
        int m = readInt();
        int d = readInt();
        boolean[] destinations = new boolean[n+1];
        for(int i = 0; i < d; i++){
            destinations[readInt()] = true;
        }
        ArrayList<Connection>[] x = new ArrayList[n+1];
        for(int i = 0; i < m; i++){
            int a = readInt();
            int b = readInt();
            int w = readInt();
            if(x[a] == null)
                x[a] = new ArrayList<Connection>();
            if(x[b] == null)
                x[b] = new ArrayList<Connection>();
            x[a].add(new Connection(b,w));
        }
        PriorityQueue<Connection> q = new PriorityQueue<>();
        q.add(new Connection(0,0));
        boolean[] popVis = new boolean[n+1];
        boolean[] addVis = new boolean[n+1];
        int[] dist = new int[n+1];
        addVis[0] = true;
        int counter = 0;
        while(!q.isEmpty()){
            Connection curr = q.poll();
            popVis[curr.t] = true;
            if(dist[curr.t] > lim)
                break;
            if(destinations[curr.t]){
                destinations[curr.t] = false;
                counter++;
                //System.out.println(curr);
            }
            ArrayList<Connection> temp = x[curr.t];
            for(Connection i : temp){
                if(popVis[i.t])
                    continue;
                int temp2 = 0;
                if(!addVis[i.t]){
                    dist[i.t] = dist[curr.t]+i.w; 
                    addVis[i.t] = true;
                }
                else{
                    temp2 = dist[i.t];
                    dist[i.t] = Math.min(dist[curr.t]+i.w,dist[i.t]);
                }
                if(temp2 != dist[i.t] && dist[i.t] <= lim)
                  q.add(new Connection(i.t,dist[i.t]));
            }
        }
        System.out.println(counter);
    }
    public static class Connection implements Comparable<Connection>{
        int t;
        int w;
        Connection(){
            t = -1;
            w = -1;
        }
        Connection(int t, int w){
            this.t = t;
            this.w = w;
        }
        public int compareTo(Connection b){
            return w-b.w;
        }
        public boolean equals(Connection b){
          return t == b.t && w == b.w;
        }
        public String toString(){
            return t + " " + w;
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
