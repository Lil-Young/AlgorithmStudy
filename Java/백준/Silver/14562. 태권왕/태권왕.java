import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int s, t, cnt;
        Point(int s, int t, int cnt) {
            this.s = s;
            this.t = t;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            sb.append(bfs(S, T)).append('\n');
        }

        System.out.print(sb);
    }

    private static int bfs(int s, int t) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(s, t, 0));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.s == cur.t) return cur.cnt;

            if (cur.s > cur.t) continue;

            queue.offer(new Point(cur.s * 2, cur.t + 3, cur.cnt + 1));
            queue.offer(new Point(cur.s + 1, cur.t, cur.cnt + 1));
        }

        return -1;
    }
}

