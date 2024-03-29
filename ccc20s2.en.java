import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] x = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                x[i][j] = in.nextInt();
            }
        }
        bfs(x);

    }
    public static void bfs(int[][] x){
        int find = x.length * x[0].length;
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[x.length][x[0].length];
        boolean[] visitedVal = new boolean[(int)1e6+1];
        q.add(x[0][0]);
        visited[0][0] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            if(visitedVal[curr]){
                continue;
            }
            visitedVal[curr] = true;
            if(curr == find){
                System.out.println("yes");
                return;
            }
            int lim = (int)Math.sqrt(curr);
            for(int i = 1; i <= lim; i++){
                if(i > x.length){
                    break;
                }
                if(curr % i == 0){
                    if(curr / i <= x[0].length && !visitedVal[x[i - 1][curr / i - 1]]){
                        q.add(x[i-1][curr/i-1]);
                        visited[i-1][curr/i-1] = true;

                    }
                    if(i < x[0].length && curr/i-1 < x.length && !visitedVal[x[curr/i-1][i-1]]){
                        q.add(x[curr/i-1][i-1]);
                        visited[curr/i-1][i-1] = true;
                    }
                }
            }

        }
        System.out.println("no");
    }
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}

 