import java.io.*;
import java.util.*;

public class Main {
    static HashSet<Integer> monke;
    static char[][] grid;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int r = in.nextInt();
        int c = in.nextInt();
        grid = new char[r][c];
        monke = new HashSet<>();
        for(int i = 0; i < r; i++){
            String x = in.next();
            for(int j = 0; j < c; j++){
                grid[i][j] = x.charAt(j);
                if(grid[i][j] == 'M'){
                    monke.add(i*c+j);
                    //System.out.println(i+" " +j);
                }
            }
        }
        int counter = 0;
        while(!monke.isEmpty()){
            bfs();
            counter++;
        }
        System.out.println(counter);
    }
    static void bfs(){
        Queue<Integer> q  = new LinkedList<>();
        int r = grid.length;
        int c = grid[0].length;
        int monkeInd = monke.iterator().next();
        int currRow = monkeInd/c;
        monke.remove(monkeInd);
        int currCol = monkeInd%c;
        q.add(currRow);
        q.add(currCol);
        //System.out.println(currRow + " " + currCol);
      
        grid[currRow][currCol] = '#';
        while(!q.isEmpty()){
            currRow = q.poll();
            currCol = q.poll();
            int childRow;
            int childCol;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    childRow = currRow + i-1;
                    childCol = currCol + j-1;
                    if(childRow >= 0 && childCol >= 0 && childRow < r && childCol < c && Math.abs(childRow-currRow) != Math.abs(childCol-currCol)){
                        switch(grid[childRow][childCol]){
                            case 'M': monke.remove(childRow*c+childCol);
                            case '.': q.add(childRow);
                                      q.add(childCol);
                                      grid[childRow][childCol]='#';
                        }
                    }
                }
            }
        }
        
    }
}

fork us on Github | like us on Facebook | help us translate | terms of service