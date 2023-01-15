import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int goal = in.nextInt();
        int n = in.nextInt();
        int[] c = new int[n];
        for(int i = 0; i < n; i++){
            c[i] = in.nextInt();
        }
        int ye = bfs(goal,n,c);
        System.out.println(ye==-1?"Roberta acknowledges defeat." : "Roberta wins in " + ye + " strokes.");
    }
    public static int bfs(int goal, int n, int[] c){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        boolean[] vis = new boolean[5281];
        int[] deg = new int[5281];
        vis[0] = true;
        deg[0] = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int i = 0; i < n; i++){
                int child = curr+c[i];
                if(child > 5280)
                    continue;
                if(vis[child])
                    continue;
                if(child == goal)
                    return deg[curr]+1;
                q.add(child);
                vis[child] = true;
                deg[child] = deg[curr]+1;
                
            }
        }
        return -1;
    }
}