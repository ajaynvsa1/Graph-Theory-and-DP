import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st; 
    static int n;
    static ArrayList<Edge>[] x;
    static HashSet<Integer> numbas;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        n = readInt();
        x = new ArrayList[n+1];
        numbas = new HashSet<>();
        for(int i = 0; i < n+1; i++){
            x[i] = new ArrayList<Edge>();
            numbas.add(i);
        }
        int sum = 0;
        for(int i = 0; i < n; i++){
            int a = readInt();
            int b = readInt();
            int c = readInt();
            sum += c;
            x[a].add(new Edge(a,b,c));
            x[b].add(new Edge(b,a,c));
        }
        //System.out.println("GOT");
        System.out.println(sum*2-bfs());
        
    }
    static int bfs(){
        ArrayDeque<Edge> q = new ArrayDeque<>();
        q.add(new Edge(-1, 0,0));
        int maxLeafCost = 0;
        boolean[] visited = new boolean[n+1];
        int[] degrees = new int[n+1];
        visited[0] = true;
        
        degrees[0] = 0;
        while(!q.isEmpty()){
          Edge curr = q.poll();
          int size = x[curr.too].size();
          for(int i = 0; i < size; i++){
            Edge temp = x[curr.too].get(i);
            if(visited[temp.too]){
              continue;
            }
            visited[temp.too] = true;
            q.add(temp);
            degrees[temp.too] = temp.cost+degrees[temp.from];
            maxLeafCost = Math.max(degrees[temp.too],maxLeafCost);
          }
        }
        
        
        return maxLeafCost;
    }
    static class Edge{
        int from;
        int too;
        int cost;
        public Edge(int f, int t, int c){
            from = f; too = t; cost = c;
        }
        public Edge(){
            from = 0; too = 0; cost = 0;
        }
        public String toString(){
          return from + " " + too + " " + cost;
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
