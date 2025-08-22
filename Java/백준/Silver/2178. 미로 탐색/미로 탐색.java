import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] arr;
	
	static class Point{
		int r, c, cnt;
		Point(int r, int c, int cnt) {
			this.r=r;
			this.c=c;
			this.cnt=cnt;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}		
		int result = bfs();
		System.out.println(result);
	}
	
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	private static int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, 1));
		boolean[][] v = new boolean[N][M];
		v[0][0] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			if(p.r==N-1 && p.c==M-1) return p.cnt;
			
			for(int d=0; d<4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc] || arr[nr][nc]==0) continue;
				
				queue.offer(new Point(nr, nc, p.cnt+1));
				v[nr][nc] = true;
			}
		}
		
		return -1;
	}
}
