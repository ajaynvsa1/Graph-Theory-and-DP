import java.io.*;
import java.util.*;

public class Main {
    static char[][] grid;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0; i < t; i++){
            int r = in.nextInt();
            int c = in.nextInt();
            grid = new char[r][c];
            for(int j = 0; j < r; j++){
                grid[j] = in.next().toCharArray();
            }
            System.out.println(bfs());
        }
      
        
    }
    static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        q.add(0);
        int[][] visited = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
          for(int j = 0; j < grid[0].length; j++){
            visited[i][j] = -1;
          }
        }
        visited[0][0] = 1;
        if(grid.length-1 == 0 && grid[0].length-1 == 0){
            if(grid[0][0] != '*')
                return visited[0][0];
            return -1;
        }
        while(!q.isEmpty()){
          //System.out.println(q);
            int currRow = q.poll();
            int currCol = q.poll();
          //System.out.println(grid[currRow][currCol]);
            switch(grid[currRow][currCol]){
                case '+':{  
                            for(int i = 0; i < 3; i++){
                              for(int j = 0; j < 3; j++){
                                int childRow = currRow + i-1;
                                int childCol = currCol + j-1;
                                if(childRow > -1 && childCol > -1 && childRow < grid.length && grid[0].length > childCol && visited[childRow][childCol] == -1 && grid[childRow][childCol] != '*' && Math.abs(childRow-currRow) != Math.abs(childCol-currCol)){
                                  if(childRow == grid.length-1 && childCol == grid[0].length-1){
                                    return visited[currRow][currCol]+1;   
                                  }
                                  q.add(childRow);
                                  q.add(childCol);
                                  visited[childRow][childCol] = visited[currRow][currCol]+1;
                                }
                              }
                            }
                            break;
                          }
                case '-':{
                    int childRow = currRow;
                    for(int j = 0; j < 3; j++){
                        int childCol = currCol + j-1;
                        if(childCol > -1 && grid[0].length > childCol && visited[childRow][childCol] == -1 && grid[childRow][childCol] != '*'){
                            if(childRow == grid.length-1 && childCol == grid[0].length-1){
                                return visited[currRow][currCol]+1;   
                            }
                            q.add(childRow);
                            q.add(childCol);
                            visited[childRow][childCol] = visited[currRow][currCol]+1;
                        }
                    }
                break;}
                case '|':{
                    int childCol = currCol;
                    for(int j = 0; j < 3; j++){
                        int childRow = currRow + j-1;
                        if(childRow > -1 && grid.length > childRow && visited[childRow][childCol] == -1 && grid[childRow][childCol] != '*'){
                            if(childRow == grid.length-1 && childCol == grid[0].length-1){
                                return visited[currRow][currCol]+1;   
                            }
                            q.add(childRow);
                            q.add(childCol);
                            visited[childRow][childCol] = visited[currRow][currCol]+1;
                        }
                    }
                break;}
                case '*':{}
            }
        }
        return -1;
    }
}
