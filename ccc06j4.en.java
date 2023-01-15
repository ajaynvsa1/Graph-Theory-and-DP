import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    Node[] nodes = new Node[7];
	    int i = 0;
	    ArrayList<Integer> q = new ArrayList<>();
	    ArrayList<Integer> taskList = new ArrayList<>();
	    while(i < 7){
	        nodes[i] = new Node(i+1);
	        i++;
        }
	    i = 0;
	    nodes[0].connections.add(7);
	    nodes[6].numP++;
	    nodes[0].connections.add(4);
	    nodes[3].numP++;
        nodes[1].connections.add(1);
        nodes[0].numP++;
        nodes[2].connections.add(4);
        nodes[3].numP++;
        nodes[2].connections.add(5);
        nodes[4].numP++;
        //int count = 0;
        int from;
        int to;
        while(true){
            from = in.nextInt();
            to = in.nextInt();
            //System.out.print(from + " " + to + ", ");
            if(from == 0 && to == 0){
                break;
            }
	        nodes[from-1].connections.add(to);
            nodes[to-1].numP++;
        }
        int root;
	    while(i < 7){
	        if(nodes[i].numP==0){
	            root = nodes[i].index;
	            q.add(root);
            }
	        i++;
        }
	    boolean shdPrint = true;
	    while(!q.isEmpty()){
         
	        if(!shdPrint){
	            break;
            }
            Collections.sort(q);
           
            int qet = q.get(0);
            taskList.add(qet);
            q.remove(0);
            nodes[qet-1].visited = true;
            
            int j = 0;
            int child;
            while(j < nodes[qet-1].connections.size()){
                child = nodes[qet-1].connections.get(j);
                nodes[child-1].parentDone++;
                if(!nodes[child-1].visited && !q.contains(child) && nodes[child-1].parentDone == nodes[child-1].numP) {
                    q.add(child);
                }
                else if(nodes[child-1].visited ){
                    shdPrint = false;
                    break;
                }
                j++;
            }
        }
	    i = 0;
	    if(shdPrint && taskList.size() == 7){
	        while(i < taskList.size()){
                System.out.print(taskList.get(i) + " ");
                i++;
            }
        }
	    else{
            System.out.print("Cannot complete these tasks. Going to bed.");
        }
    }
}
class Node{
    public int index;
    public ArrayList<Integer> connections;
    public int numP;
    public int parentDone = 0;
    public boolean visited = false;
    public Node(int x){
        index = x;
        connections = new ArrayList<>();
    }
}
 