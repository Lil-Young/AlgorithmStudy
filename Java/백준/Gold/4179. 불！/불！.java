import java.util.*;
import java.io.*;

/*
 * 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출 할 수 있는지 여부,
 * 그리고 얼마나 빨리 탈출할 수 있는지 결정해야 한다.
 * 
 * 지훈이와 불은 매 분마다 한칸씩 수평 또는 수직으로 이동한다.
 * 불은 각 지점에서 네 방향으로 확산된다.
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 * 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
 * 
 * # : 벽 /  . : 지나갈 수 있는 공간 / J : 지훈이 위치 / F : 불이 난 공간
 * 
 * 탈출 할 수 없으면 IMPOSSIBLE, 탈출할 수 있으면 가장 빠른 탈출시간 출력
 */


public class Main {
	static int N, M, result;
	static boolean impossible;
	static char[][] arr;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static Queue<Point> jh_queue;
	static Queue<Point> fire_queue;
	static boolean[][] jh_v;
	static boolean[][] fire_v;
	
	
	static class Point {
		int r, c, cnt;
		Point(int r, int c, int cnt) {
			this.r=r;
			this.c=c;
			this.cnt=cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		jh_v = new boolean[N][M];
		fire_v = new boolean[N][M];
		jh_queue = new ArrayDeque<>();
		fire_queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j);
				// queue에 불 위치 삽입
				if(arr[i][j]=='F') {
					fire_queue.offer(new Point(i, j, 0));
					fire_v[i][j] = true;
				}
				if(arr[i][j]=='J') {
					jh_queue.offer(new Point(i, j, 0));
					jh_v[i][j] = true;
				}
			}
		}
		result = bfs();
		if(result==-1) {
			System.out.println("IMPOSSIBLE");
		}else {
			System.out.println(result+1);
		}
	}
	private static int bfs() {
		
		while(!jh_queue.isEmpty()) {
			// 불 이동
			int fireQueueSize = fire_queue.size();
			for (int f = 0; f < fireQueueSize; f++) {
				Point fire = fire_queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = fire.r + dr[d];
					int nc = fire.c + dc[d];
					if(nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]=='#' || fire_v[nr][nc]) continue;
					fire_queue.offer(new Point(nr, nc, fire.cnt+1));
					fire_v[nr][nc] = true;
				}
			}			
			
			
			
			
			// 지훈 이동
			int jhQueueSize = jh_queue.size();
			for (int j = 0; j < jhQueueSize; j++) {
				Point jh = jh_queue.poll();
				if(jh.r==0 || jh.r==N-1 || jh.c==0 || jh.c==M-1) {
					return jh.cnt;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = jh.r + dr[d];
					int nc = jh.c + dc[d];
					if(nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]!='.' || jh_v[nr][nc] || fire_v[nr][nc]) continue;
					jh_queue.offer(new Point(nr, nc, jh.cnt+1));
					jh_v[nr][nc] = true;
				}	
			}
		}
		return -1;
	}
}
