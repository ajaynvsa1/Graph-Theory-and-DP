import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br;
    static StringTokenizer st; 
    static int n;
    static int r;
    static ArrayList<Integer>[] x;
    static int minDeg;
    static int[] deg;
    static boolean[] rabbits;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        n = readInt();
        r = readInt();
        minDeg = Integer.MAX_VALUE;
        deg = new int[n+1];
        x = new ArrayList[n+1];
        for(int i = 0; i < n+1; i++)
            x[i] = new ArrayList<Integer>();
        for(int i = 0; i < n-1; i++){
            int a = readInt();
            int b = readInt();
            x[a].add(b);
            x[b].add(a);
        }
        rabbits = new boolean[n+1];
        for(int i = 0; i < r; i++){
            rabbits[readInt()] = true;
        }
        int root = readInt();
        int goal = readInt();
        Stack<Integer> path = bfs(root,goal);
        ArrayList<Integer> p = new ArrayList<>();
        int curr = path.pop();
        int prev = curr;
        p.add(curr);
        while(!path.isEmpty()){
          curr = path.pop();
          x[curr].remove((Object)prev);
          x[prev].remove((Object)curr);
          prev = curr;
          p.add(curr);
          
        }
        for(int i : p)
            if(bfsRabbit(i))
                break;
        
        System.out.println(minDeg);
        
    }  
    public static Stack<Integer> bfs(int root,int goal){
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        boolean[] vis = new boolean[n+1];
        q.add(root);
        vis[root] = true;
        int[] prev = new int[n+1];
        prev[root] = -1;
        boolean cont = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            ArrayList<Integer> temp = x[curr];
            for(int i : temp){
                if(vis[i])
                    continue;
                if(i == goal){
                    cont = false;
                    prev[i] = curr;
                    break;
                }
                q.add(i);
                prev[i] = curr;
                vis[i] = true;
            }
            if(!cont)
                break;
        }
        Stack<Integer> s = new Stack<Integer>();
        int i = goal;
        while(i != -1){
            s.add(i);
            i = prev[i];
        }
        return s;
    }
    public static boolean bfsRabbit(int root){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(root);
        boolean[] vis = new boolean[n+1];
        int[] locDeg = new int[n+1];
        vis[root] = true;
        if(rabbits[root]){
            minDeg = 0;
            return true;
        }
        int counter = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            ArrayList<Integer> temp = x[curr];
            for(int i : temp){
                if(vis[i])
                    continue;
                vis[i] = true;
                int currDeg = deg[i];
                locDeg[i] = locDeg[curr]+1;
                if(deg[i] == 0)
                    deg[i] = locDeg[i];
                else
                    deg[i] = Math.min(deg[i],locDeg[i]);
                if(rabbits[i]){
                    minDeg = Math.min(deg[i],minDeg);
                    counter++;
                    if(counter == r)
                      return false;
                }
                if(currDeg != deg[i])
                    q.add(i);
            }
        }
        return false;
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