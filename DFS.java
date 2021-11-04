import java.util.*;
class Main {
  public static void main(String[] args) {
   Scanner in = new Scanner(System.in);
   int n = in.nextInt();
   int[][] adjMat = new int[n][n];
   int m = in.nextInt();
   int  i = 0;
   while(i < m){
     int u = in.nextInt();
     int v = in.nextInt();
     int w = in.nextInt();
     adjMat[u-1][v-1] = w;
     i++;
   }
   int root = in.nextInt();
   /*for(i = 0; i < n; i++){
     for(int j = 0; j < n; j++){
       System.out.print(adjMat[i][j]+" ");
     }
     System.out.println();
   }*/
   DFS(root,adjMat);
  }
  public static void DFS(int r, int[][] x){
    boolean[] vis = new boolean[x.length];
    ArrayList<Integer> a = new ArrayList<>();
    a.add(r);
    int mark = r;
    while(!a.isEmpty()){
      //System.out.println("Stack: " + a);
      while(getNextUVchild(mark,x,vis)!=-1){
        int temp = getNextUVchild(mark,x,vis);
         System.out.println(temp);
        a.add(temp);
        //System.out.println("Stack: " + a);
        mark = a.get(a.size()-1);
      }
      int index = a.remove(a.size()-1)-1;
      //System.out.println("Stack: " + a);
      vis[index] = true;
     // System.out.println(index+1);
      if(a.size() != 0)
        mark = a.get(a.size()-1);
    }
  }
  //gets next unvisited child
  public static int getNextUVchild(int val, int[][] adj, boolean[] vis){
    int i = 1;
    while(i<adj.length+1){
      if(adj[val-1][i-1]!=0 && !vis[i-1]){
        return i;
      }
     i++;
    }
   return -1;
  }
}