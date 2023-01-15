import java.io.*;
import java.util.*;

public class Main {
  static int maxSize;
  static int numP;
  static String[] names;
  static int[] crossTimes;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    maxSize = in.nextInt();
    numP = in.nextInt();
    names = new String[numP];
    crossTimes = new int[numP];
    for (int i = 0; i < numP; i++) {
      names[i] = in.next();
      crossTimes[i] = in.nextInt();
    }
    Node ans = bfs();
    System.out.println("Total Time: " + ans.timeTotal);
    for (Group i : ans.x) {
      for (String j : i.strings) {
        System.out.print(j + " ");
      }
      System.out.println();
    }
  }

  public static Node bfs() {
    PriorityQueue<Node> q = new PriorityQueue<>(new Comprat());
    Node root = new Node();
    root.timeTotal = 0;
    root.lastIndex = 0;
    root.numPRep = 0;
    q.add(root);
    while (!q.isEmpty()) {
      Node curr = q.poll();
      if (curr.numPRep == numP)
        return curr;
      for (int i = curr.lastIndex; i < curr.lastIndex + maxSize; i++) {
        if (i == numP) {
          break;
        }
        Node child = new Node(curr);
        Group temp = new Group();
        child.x.add(temp);
        for (int j = curr.lastIndex + maxSize - i; j > 0; j--) {
          if(child.lastIndex == numP)
            break;
          temp.strings.add(names[child.lastIndex]);
          temp.totalTime = Math.max(crossTimes[child.lastIndex],temp.totalTime);
          child.numPRep++;
          child.lastIndex++;
        }
        child.timeTotal += temp.totalTime;
        q.add(child);

      }
    }
    return null;
  }

  public static class Comprat implements Comparator<Node> {
    public int compare(Node o1, Node o2) {
      if (o1.timeTotal == o2.timeTotal)
        return o2.numPRep - o1.numPRep;
      return o1.timeTotal - o2.timeTotal;
    }
  }

  public static class Node {
    ArrayList<Group> x = new ArrayList<>();
    int timeTotal;
    int lastIndex;
    int numPRep;

    Node() {
      timeTotal = 0;
      lastIndex = -1;
      numPRep = 0;
    }

    Node(Node c) {
      for (Group i : c.x) {
        x.add(new Group(i));
      }
      timeTotal = c.timeTotal;
      lastIndex = c.lastIndex;
      numPRep = c.numPRep;
    }
  }
  public static class Group {
    public ArrayList<String> strings = new ArrayList<>();
    int totalTime;
    Group(){
      totalTime = 0;
    }
    Group(Group c) {
      for (String i : c.strings) {
        strings.add(i);
      }
      totalTime = c.totalTime;
    }
  }
}