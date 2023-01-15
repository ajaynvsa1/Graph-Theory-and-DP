import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
      try{
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = readInt();
        int m = readInt();
        int r = readInt();
        int v = readInt();
        ArrayList<Connection>[] x = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++)
            x[i] = new ArrayList<Connection>();
        for(int i = 0; i < m; i++){
            int a = readInt();
            int b = readInt();
            int w = readInt();
            x[a].add(new Connection(b,w));
            x[b].add(new Connection(a,w));
        }
        HashSet<Integer> dest = new HashSet<>();
        ArrayList<Integer> dest2 = new ArrayList<>();
        for(int i = 0; i < v; i++){
            int a = readInt();
            dest.add(a);
            dest2.add(a);
        }
        PriorityQueue<Connection> q = new PriorityQueue<>();
        boolean[] popVis = new boolean[n+1];
        boolean[] addVis = new boolean[n+1];
        int[] dis = new int[n+1];
        q.add(new Connection(r,0));
        addVis[r] = true;
        int temp2;
        while(!q.isEmpty()){
            Connection curr = q.poll();
            popVis[curr.to] = true;
            dest.remove(curr.to);
                
            if(dest.isEmpty())
                break;
            ArrayList<Connection> temp = x[curr.to];
            for(Connection i : temp){
                if(popVis[i.to])
                    continue;
                if(addVis[i.to]){
                    temp2 = dis[i.to];
                    dis[i.to] = Math.min(dis[i.to],dis[curr.to]+i.w);
                }
                else{
                    temp2 = 0;
                    dis[i.to] = dis[curr.to]+i.w;
                    addVis[i.to] = true;
                }
                if(dis[i.to] != temp2)
                    q.add(new Connection(i.to,dis[i.to]));
            }
        }
        for(int i : dest2)
            System.out.println(dest.contains(i) ? -1 : dis[i]);
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
    public static class Connection implements Comparable<Connection>{
        int to = -1;
        int w = -1;
        Connection(int to, int we){
            this.to = to;
            w = we;
        }
        public int compareTo(Connection b){
            return w-b.w;
        }
    }
    static String next() throws IOException {
        while(st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
}