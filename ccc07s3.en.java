import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        HashMap<Integer,Integer> connections = new HashMap<>();
        for(int i = 0; i < n; i++){
            connections.put(in.nextInt(),in.nextInt());
        }
        while(true){
            int x = in.nextInt();
            int y = in.nextInt();
            if(x != 0 && y != 0){
                int z = BFS(connections,x,y);
                if(z!=-1){
                    System.out.println("Yes " + z);
                }
                else{
                    System.out.println("No");
                }
            }
            else{
                break;
            }
        }
    }
    public static int BFS(HashMap<Integer,Integer> conn, int a, int b){
        int hops = 0;
        Integer index = a;
        do{
            if(conn.containsKey(index))
                index = conn.get(index);
            else{
                break;
            }
            if(index == b){
                return hops;
            }
            hops++;
        }while(index != a);
        return -1;

    }
}
