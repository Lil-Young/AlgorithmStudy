import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int target;

    public static int cmdD(int n) {
        return (n * 2) % 10000;
    }

    public static int cmdS(int n) {
        return (n == 0) ? 9999 : n - 1;
    }

    public static int cmdL(int n) {
        return (n % 1000) * 10 + n / 1000;
    }

    public static int cmdR(int n) {
        return (n % 10) * 1000 + n / 10;
    }

    static class Point {
        int num;
        String res;

        public Point(int num, String res) {
            this.num = num;
            this.res = res;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            sb.append(bfs(start)).append("\n");
        }

        System.out.print(sb);
    }

    private static String bfs(int start) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[10000];
        queue.offer(new Point(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.num == target) {
                return p.res;
            }

            int nextD = cmdD(p.num);
            if (!visited[nextD]) {
                visited[nextD] = true;
                queue.offer(new Point(nextD, p.res + "D"));
            }

            int nextS = cmdS(p.num);
            if (!visited[nextS]) {
                visited[nextS] = true;
                queue.offer(new Point(nextS, p.res + "S"));
            }

            int nextL = cmdL(p.num);
            if (!visited[nextL]) {
                visited[nextL] = true;
                queue.offer(new Point(nextL, p.res + "L"));
            }

            int nextR = cmdR(p.num);
            if (!visited[nextR]) {
                visited[nextR] = true;
                queue.offer(new Point(nextR, p.res + "R"));
            }
        }

        return ""; // 도달할 수 없는 경우는 없음
    }
}
