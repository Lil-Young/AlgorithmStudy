import java.util.*;
import java.io.*;

public class Main {
	static int L, R, C;
	static char[][][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L==0 && R==0 && C==0) break;
			arr = new char[L][R][C];
			// start 위치
			int[] startIdx = new int[3];
			for (int t = 0; t < L; t++) {
				for (int i = 0; i < R; i++) {
					String s = br.readLine();
					for (int j = 0; j < C; j++) {
						arr[t][i][j] = s.charAt(j);
						if(arr[t][i][j]=='S') {
							startIdx[0] = t;
							startIdx[1] = i;
							startIdx[2] = j;
						}
					}
				}
				br.readLine();
			}
	//		for (int i = 0; i < L; i++) {
	//			for (int j = 0; j < R; j++) {
	//				System.out.println(Arrays.toString(arr[i][j]));
	//			}
	//		}
			
			int result = bfs(startIdx[0], startIdx[1], startIdx[2]);
			if(result == -1) {
				System.out.println("Trapped!");
			}else {
				System.out.println("Escaped in " + result + " minute(s).");
			}
		}
	}
	static class Point {
		int t, r, c, cnt;
		Point(int t, int r, int c, int cnt){
			this.t=t;
			this.r=r;
			this.c=c;
			this.cnt=cnt;
		}
	}
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	private static int bfs(int t, int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(t, r, c, 0));
		boolean[][][] v = new boolean[L][R][C];
		v[t][r][c] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
//			System.out.println("currentIdx:: " + p.t + " " + p.r + " " + p.c + " " + p.cnt);
			if(arr[p.t][p.r][p.c]=='E') {
				return p.cnt;
			}
			
			
			// 상하좌우
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr<0 || nr>=R || nc<0 || nc>=C || arr[p.t][nr][nc]=='#' || v[p.t][nr][nc]) continue;
				
				queue.offer(new Point(p.t, nr, nc, p.cnt+1));
				v[p.t][nr][nc] = true;
			}
			
			// 층 이동
			if(p.t+1<L && (arr[p.t+1][p.r][p.c]=='.' || arr[p.t+1][p.r][p.c]=='E') && !v[p.t+1][p.r][p.c]) {
				queue.offer(new Point(p.t+1, p.r, p.c, p.cnt+1));
				v[p.t+1][p.r][p.c] = true;
			}
			if(p.t-1>=0 && (arr[p.t-1][p.r][p.c]=='.' || arr[p.t-1][p.r][p.c]=='E') && !v[p.t-1][p.r][p.c]) {
				queue.offer(new Point(p.t-1, p.r, p.c, p.cnt+1));
				v[p.t-1][p.r][p.c] = true;
			}
			
		}
		
		return -1;
	}
}