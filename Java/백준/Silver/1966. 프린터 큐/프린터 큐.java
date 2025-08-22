import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Queue<int[]> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.offer(new int[]{priority, i});
            }

            int count = 0;
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                boolean isHighest = true;

                for (int[] doc : queue) {
                    if (doc[0] > current[0]) {
                        isHighest = false;
                        break;
                    }
                }

                if (!isHighest) {
                    queue.offer(current);
                } else {
                    count++;
                    if (current[1] == m) {
                        System.out.println(count);
                        break;
                    }
                }
            }
        }
    }
}
