import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter out;
    static ArrayList<Character>[] x;
    static int n;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        x = new ArrayList[26];
        n = readInt();
        int queries = readInt();
        for(int i = 0; i < 26; i++)
            x[i] = new ArrayList<Character>();
        for(int i = 0; i < n; i++){
            char a = next().charAt(0);
            char b = next().charAt(0);
            x[a-65].add(b);
            x[b-65].add(a);
        }
        for(int i = 0; i < queries; i++){
            bfs(next().charAt(0),next().charAt(0));
            out.println();
            out.flush();
        }
        br.close();
        out.close();
    }
    static void bfs(char root, char dest){
        char[] parents = new char[26];
        //int[] degrees = new int[26];
        boolean[] visited = new boolean[26];
        ArrayDeque<Character> q = new ArrayDeque<>();
        q.add(root);
        visited[root-65] = true;
        while(!q.isEmpty()){
            char curr = q.poll();
            int size = x[curr-65].size();
            for(int i = 0; i < size; i++){
                char child = x[curr-65].get(i);
                if(visited[child-65])
                    continue;
                parents[child-65] = curr;
                visited[child-65] = true;
                if(child == dest)
                    break;
                q.add(child);
                //System.out.println(q);
            }
        }
        if(visited[dest-65]){
            //System.out.println("HERE");
            Stack<Character> s = new Stack<>();
            char curr = dest;
            while(parents[curr-65]!='\u0000'){
                s.push(curr);
                curr = parents[curr-65];
            }
            s.push(root);
            while(!s.isEmpty()){
                out.print(s.pop());
                out.flush();
            }
        }
    }
    static String next() throws IOException {
        while(st == null || !st.hasMoreTokens()){
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    static long readLong() throws IOException {
        return Long.parseLong(next());
    }

    static int readInt() throws IOException {
        return Integer.parseInt(next());
    }

    static double readDouble() throws IOException {
        return Double.parseDouble(next());
    }

    static char readCharacter() throws IOException {
        return next().charAt(0);
    }

    static String readLine() throws IOException {
        return br.readLine().trim();
    }
}
