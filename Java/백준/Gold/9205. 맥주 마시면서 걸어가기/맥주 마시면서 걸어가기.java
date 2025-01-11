import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, r, c;
	static Point[] arr;
	static boolean[] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	
	static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private static boolean arrive(Point start, Point end) {
		int dis = Math.abs(start.r - end.r) + Math.abs(start.c - end.c);
		// 맥주 1개당 50m, 최대 맥주 20개 소유 가능 -> 50 x 20 = 1000
		if(dis > 1000) return false;
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			arr = new Point[N+2];
			v = new boolean[N+2];
			// 좌표들
			for (int n = 0; n < N+2; n++) {
				st = new StringTokenizer(br.readLine());
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				arr[n] = new Point(r, c);
			}
			
//			for (int i = 0; i < N+2; i++) {
//				System.out.println(arr[i].r + " " + arr[i].c);
//			}
			
			boolean isArrive = bfs();
			if(isArrive) {
				sb.append("happy");
			}else {
				sb.append("sad");
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	private static boolean bfs() {
		// TODO Auto-generated method stub
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(arr[0]);
		v[0] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(arrive(p, arr[N+1])) {
				return true;
			}
			
			for (int d = 1; d < N+1; d++) {
				if(arrive(p, arr[d]) && !v[d]) {
					queue.offer(arr[d]);
					v[d] = true;
				}
			}
		}
		return false;
	}
}
