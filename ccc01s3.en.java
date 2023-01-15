import java.io.*;
import java.util.*;

public class Main {
    static Node[] nodes;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack<Connection> a = new Stack<>();
        nodes = new Node[26];
        for(int i = 0; i < 26; i++){
            nodes[i] = new Node((char)(i+65));
        }
        while(true){
            String x = in.next();
            if(x.equals("**")){
                break;
            }
            char one = x.charAt(0);
            char two = x.charAt(1);
            a.push(new Connection(one,two));
            nodes[(int)(one)-65].connections.add(a.peek());
            a.push(new Connection(two,one));
            nodes[(int)(two)-65].connections.add(a.peek());
            
            
        }
        int counter = 0;
        while(!a.isEmpty()){
            Connection curr = a.pop();
            Connection curr2 = a.pop();
            curr.available = false;
            curr2.available = false;
            if(!dfs()){
                System.out.println(curr2.fromVal + "" + curr2.toVal);
                counter++;
            }
            curr.available = true;
            curr2.available = true;
        }
        System.out.println("There are " + counter + " disconnecting roads.");
    }
    static class Connection{
        char fromVal;
        char toVal;
        boolean available = true;
        public Connection(){
            fromVal = 0;
            toVal = 0;
            available = true;
        }
        public Connection(char from, char too){
            fromVal = from;
            toVal = too;
            available = true;
        }
    }
    static class Node{
        char id;
        ArrayList<Connection> connections;
        public Node(char i){
            id = i;
            connections = new ArrayList<Connection>();
        }
        
    }
    static boolean dfs(){
        Stack<Node> s = new Stack<>();
        s.push(nodes[0]);
        boolean[] visited = new boolean[26];
        visited[(int)nodes[0].id-65] = true;
        while(!s.isEmpty()){
            Node curr = s.pop();
            if(curr.id == 'B'){
                return true;
            }
            int size = curr.connections.size();
            for(int i = 0; i < size; i++){
                if(curr.connections.get(i).available){
                    Node nodee = nodes[curr.connections.get(i).toVal-65];
                    if(!visited[(int)nodee.id-65]){
                        if(nodee.id == 'B'){
                            return true;
                        }
                        s.push(nodee);
                        visited[(int)nodee.id-65] = true;
                    }
                }
            }
        }
        return false;
    }
}
