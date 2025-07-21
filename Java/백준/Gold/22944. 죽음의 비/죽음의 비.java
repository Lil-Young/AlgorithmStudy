import java.io.*;
import java.util.*;

public class Main {
	static int N, H, D, result, s_r, s_c;
	static char[][] arr;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j);
				if(arr[i][j] == 'S') {
					s_r = i;
					s_c = j;
				}
			}
		}
		
		result = bfs(s_r, s_c);
		System.out.println(result);
	}
	
	static class Point {
		int r, c, cnt, stemina, um_cnt;
		Point(int r, int c, int cnt, int stemina, int um_cnt) {
			this.r=r;
			this.c=c;
			this.cnt=cnt;
			this.stemina=stemina;
			this.um_cnt=um_cnt;
		}
	}
	private static int bfs(int r , int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c, 0, H, 0));
		int[][] v = new int[N][N];
		v[r][c] = H;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			if(arr[p.r][p.c]=='E') return p.cnt;
			for (int d = 0; d < 4; d++) {
				int nr = p.r+dr[d];
				int nc = p.c+dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				
				int stemina = p.stemina;
				int um_cnt = p.um_cnt;
				
				if(arr[nr][nc] == 'U') {
					um_cnt = D;
				}else {
					if(um_cnt>0) {
						um_cnt--;
					}else {
						stemina--;
					}
				}
				if(v[nr][nc] >= um_cnt+stemina) continue;
				
				queue.offer(new Point(nr, nc, p.cnt+1, stemina, um_cnt));
				v[nr][nc]=um_cnt+stemina;
			}
		}
		return -1;
	}
}