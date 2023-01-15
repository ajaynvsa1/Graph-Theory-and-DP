import java.io.*;
import java.util.*;

public class Main {
  static int n;
  static int m;
  static int min;
  static ArrayList<Integer>[] x;
  static int[] degree;
  static BufferedReader br;
  static StringTokenizer st;
  static ArrayList<Integer> antsList;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        n = nextInt();
        m = nextInt();
        degree = new int[n];
        x = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            degree[i] = (int) Math.pow(2, 31) - 1;
            x[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < m; i++){
            int addA = nextInt();
            int addB = nextInt();
            if (x[addA - 1] == null) {
                x[addA - 1] = new ArrayList<>();
            }
            x[addA-1].add(addB);
            if (x[addB - 1] == null) {
                x[addB - 1] = new ArrayList<>();
            }
            x[addB-1].add(addA);
        }
        int k = nextInt();
        antsList = new ArrayList<Integer>();
        for(int i = 0; i < k; i++){
            antsList.add(nextInt());
        }
        int beeEffEss = bfs();
        System.out.println(beeEffEss == -1?"sacrifice bobhob314" : beeEffEss);
    }
    
    static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> ants = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(1);
        degree[0] = 0;
        visited[0] = true;
        ants.addAll(antsList);
        for(int i : antsList){
          visited[i-1] = true;
        }
        int counter = 0;
        while(!q.isEmpty()){
          int curr = q.poll();
          counter++;
          if(counter % 4 == 0){
            ArrayList<Integer> temp = new ArrayList<>();
            while(!ants.isEmpty()){
              int currAnt = ants.poll();
              for(int i : x[currAnt-1]){
                temp.add(i);
                visited[i-1] = true;
              }
              for(int i = 0 ; i < n; i++){
                if(x[i].contains(currAnt)){
                  visited[i] = true;
                }
              }
            }
            ants.addAll(temp);
          }
          for(int i: x[curr-1]){
            //System.out.println(q);
            if(i == n && !visited[n-1]){
              return degree[curr-1]+1;
            }
            if(!visited[i-1]){
              q.add(i);
              degree[i-1] = degree[curr-1]+1;
              visited[i-1] = true;
            }       
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
    static int nextInt () throws IOException {
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
