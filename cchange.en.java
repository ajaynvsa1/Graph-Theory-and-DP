import java.io.*;
import java.util.*;

public class Main {
    static int goal;
    static int n;
    static int options[];
    static int[] deg;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        goal = in.nextInt();
        n = in.nextInt();
        options = new int[n];
        for(int i = 0 ; i < n; i++){
            options[i] = in.nextInt();
        }
        System.out.println(bfs());
    }
    public static int bfs(){
        int root = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[10001];
        deg = new int[10001];
        deg[0] = 0;
        vis[0] = true;
        q.add(root);
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int i = 0; i < n; i++){
                int child = curr + options[i];
                if(child == goal)
                    return child0(child) ? deg[curr] + 1 : Math.min(deg[curr]+1,deg[child]);
                if(child > 10000)
                    continue;
                if(vis[child])
                    continue;
                q.add(child);
                vis[child] = true;
                deg[child] = child0(child) ? deg[curr] + 1 : Math.min(deg[curr]+1,deg[child]);
            }   
        }
        return -1;
    }
    static boolean child0(int child){
      return deg[child] == 0;
    }
}
