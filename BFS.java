import java.util.*;
class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    String[] connect = new String[m];
    for(int i = 0; i < m; i++){
      connect[i] = Integer.toString(in.nextInt());
    }
    int root = in.nextInt();
    int d = in.nextInt();
    System.out.print("BFS: ");
    String foundPath = path(BFS(root,n,connect),d);
    System.out.println();
    System.out.print("Path: " + foundPath);
    in.close();
  }
  public static int[] BFS(int r, int n, String[] connections){
    ArrayList<Integer> q = new ArrayList<>();
    int[] vis = new int[n];
    int[] parents = new int[n];
    Integer add = r;
    q.add(r);
    int mark = r;
    while(!q.isEmpty()){
      while(getUVChild(mark, connections, vis) != -1){
        add = getUVChild(mark,connections,vis);
        q.add(add);
        vis[add-1]++;
        parents[add-1] = mark;
      }
      System.out.print(q.remove(0)+" ");
      if(!q.isEmpty())
        mark = q.get(0);
    }
    return parents;
  }
  public static int getUVChild(int value, String[] connections, int[] vis){
    int i = 0;
    while(i < connections.length){
      if(Integer.parseInt(connections[i].substring(0,1)) == value && vis[Integer.parseInt(connections[i].substring(1,2))-1] <= 0){
        return Integer.parseInt(connections[i].substring(1,2));
      }
      i++;
    }
    return -1;
  }
  public static String path(int[] parents, int end){
    int i = end-1;
    StringBuilder sb = new StringBuilder();
    sb.append(end);
    while(parents[i]!=0){
      sb.append(parents[i]);
      i = parents[i]-1;
    }
    return sb.reverse().toString();
  }
}