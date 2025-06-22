import java.util.*;
import java.io.*;

public class Main {
	static int N, M, result, max;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		v = new boolean[N][M];
		result = 0;
		max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]==1 && !v[i][j]) {
					bfs(i, j);
					result++;
				}
			}
		}
		System.out.println(result);
		System.out.println(max);
	}
	static class Point {
		int r, c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	private static void bfs(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		v[r][c] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			max = Math.max(max, ++cnt);
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc]==1 && !v[nr][nc]) {
					queue.offer(new Point(nr, nc));
					v[nr][nc] = true;
				}
			}
		}
	}
}
