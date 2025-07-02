import java.io.*;
import java.util.*;

/*
 * 각 칸은 방을 의미하고 각 칸에는 0~9까지 숫자가 적혀있다.
 * 상하좌우 4가지 방향 움직일 수 있고, 0이 적힌 방은 들어갈 수 없다.
 * 
 * 비밀번호 힌트는 다음과 같다.
 * 임의의 방에서 다른 방으로 이동할 때는 항상 두 방 사이의 최단 경로로 이동
 * 1번을 만족하는 경로 중 가장 긴 경로의 시작 방과 끝 방에 적힌 숫자 합
 * 만약 위 2가지 조건을 만족하는 경로가 여러개면, 
 * 시작 방과 끝 방에 적힌 숫자의 합이 가장 큰 값이 비밀번호가 된다.
 * 시작 방과 끝 방은 동일한 위치일 수도 있다.
 * 
 * 이때 비밀번호가 무엇인지 구해라
 * 만약 비밀번호를 만들 수 없다면 0을 출력한다.
 * 
 */
public class Main {
	static int N, M, depth, result;
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
		
		depth = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] > 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(result);
		
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
		v = new boolean[N][M];
		v[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			// p.cnt가 max일 때, 처음위치와 끝 방의 위치의 합 저장
			if(p.cnt > depth) {
				result = arr[r][c] + arr[p.r][p.c];
				depth = p.cnt;
			}
			// p.cnt == depth일 때, 이전 결과와 현재 결과 비교 후 큰 값 저장
			if(p.cnt == depth) {
				result = Math.max(result, arr[r][c] + arr[p.r][p.c]);
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc]>0 && !v[nr][nc]) {
					queue.offer(new Point(nr, nc, p.cnt+1));
					v[nr][nc] = true;
				}
			}
		}
		
	}
	
}
