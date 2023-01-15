import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br;
    static StringTokenizer st; 
    static ArrayList<Integer>[] x;
    static HashSet<Integer>[] parent;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int n = readInt();
        x = new ArrayList[n];
        parent = new HashSet[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            x[i] = new ArrayList<Integer>();
            parent[i] = new HashSet<Integer>();
        }
        for(int i = 0; i < n; i++){
            int prereqs = readInt();
            if(prereqs == 0){
                q.add(i+1);
                visited[i] = true;
            }
            for(int j = 0; j < prereqs; j++){
                int prereq = readInt();
                x[prereq-1].add(i+1);
                parent[i].add(prereq);
            }
        }
        while(!q.isEmpty()){
            int curr = q.poll();
            System.out.print(curr + " ");
            int size = x[curr-1].size();
            ArrayList<Integer> temp = x[curr-1];
            for(int i = 0; i < size; i++){
                parent[temp.get(i)-1].remove(curr);
                if(!visited[temp.get(i)-1] && parent[temp.get(i)-1].isEmpty()){
                    q.add(temp.get(i));
                    visited[temp.get(i)-1] = true;
                }
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
