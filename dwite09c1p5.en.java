import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static boolean[][] x;
    static int n;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int first = 0;
        for(int i = 0; i < 5; i++){
            n = readInt();
            x = new boolean[100][100];
            for(int j = 0; j < n; j++){
                if(j==0){
                  first = readInt();
                  x[first-1][readInt()-1] = true;
                }
                else{ 
                  x[readInt()-1][readInt()-1] = true;
                }
            }
            System.out.println(dfs(first));
        }
    }
    static int dfs(int first){
        Stack<Integer> s = new Stack<Integer>();
        first = 1;
        s.push(first);
        int[] degree = new int[100];
        boolean[] visited = new boolean[100];
        degree[first-1] = 0;
        visited[first-1] = true;
        while(!s.isEmpty()){
            int curr = s.pop();
            visited[curr-1] = true;
            for(int i = 0; i < 100; i++){
                if(!x[curr-1][i]){
                    continue;
                }
                if(!visited[i]){
                    s.push(i+1);
                    
                    degree[i] = degree[curr-1]+1;
                    
                    continue;
                }
                
                return Math.abs(degree[i] - degree[curr-1])+1;
            }
        }
        return -1;
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
