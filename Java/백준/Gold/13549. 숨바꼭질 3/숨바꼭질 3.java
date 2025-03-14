import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	
	static class Point {
		int value, depth;
		public Point(int value, int depth) {
			this.value = value;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int result = bfs();
		System.out.println(result);
	}

	private static int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(N, 0));
		boolean[] visited = new boolean[100001];
		visited[N] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			int x = p.value;
			int depth = p.depth;

			if (x == K) return depth;

			// 0초 이동
			if (x * 2 <= 100000 && !visited[x * 2]) {
				visited[x * 2] = true;
				queue.offer(new Point(x * 2, depth));
			}

			// 1초 이동
			if (x - 1 >= 0 && !visited[x - 1]) {
				visited[x - 1] = true;
				queue.offer(new Point(x - 1, depth + 1));
			}
			if (x + 1 <= 100000 && !visited[x + 1]) {
				visited[x + 1] = true;
				queue.offer(new Point(x + 1, depth + 1));
			}
		}
		return -1;
	}
}
