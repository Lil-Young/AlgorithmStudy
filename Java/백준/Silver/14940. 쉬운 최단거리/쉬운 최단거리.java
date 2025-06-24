import java.util.*;
import java.io.*;

public class Main {
	/*
	 * 0은 갈 수 없는 땅
	 * 1은 갈 수 있는 땅
	 * 2는 목표지점
	 * 
	 * 각 지점에서 목표지점까지의 거리를 출력한다.
	 * 원래 갈 수 없는 땅인 위치는 0을 출력하고,
	 * 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1를 출력한다.
	 */
	
	static int N, M;
	static int[][] arr;
	static int[][] results;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		// 목표지점 인덱스
		int endR = 0, endC = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==2) {
					endR=i;
					endC=j;
				}
			}
		}
		
		// 결과 배열에 MAX_VALUES를 채워 넣는다.
		results = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(results[i], Integer.MAX_VALUE);
		}
		bfs(endR, endC);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] == 0) System.out.print(0 + " ");
				else if(arr[i][j] != 0 && results[i][j] == Integer.MAX_VALUE) System.out.print(-1 + " ");
				else System.out.print(results[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static class Point {
		int r, c, cnt;
		Point(int r, int c, int cnt) { 
			this.r=r;
			this.c=c;
			this.cnt=cnt;
		}
	}
	private static void bfs(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c, 0));
		boolean[][] v = new boolean[N][M];
		v[r][c] = true;
		results[r][c] = 0;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>= M || arr[nr][nc]==0 || v[nr][nc]) continue;
				queue.offer(new Point(nr, nc, p.cnt+1));
				v[nr][nc] = true;
				results[nr][nc] = p.cnt+1;
			}
		}
	}
}
