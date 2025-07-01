import java.io.*;
import java.util.*;

/*
 * 파이어볼과 토네이도를 조합해 파이어스톰 시전 가능
 * 2^N X 2^N 격자
 * 위치(r,c)는 격자의 r행 c열 의미
 * A[r][c]는 (r, c)에 있는 얼음 양 의미
 * A[r][c]가 0이면, 얼음이 없는거임
 * 
 * 파이어스톰을 시전하려면 시전할때 마다 단계 L을 결정해야함
 * 1. 먼저 격자를 2^L X 2^L 크기의 부분 격자로 나눈다.
 * 2. 모든 부분 격자를 시계 방향으로 90도 회전시킨다.
 * 3. 이후 얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은
 * 		얼음의 양이 1 줄어든다.
 * 
 * 파이어스톰 총 Q번 시전하려고 한다.
 * 모든 파이어스톰을 시전한 후, 다음 2가지를 구해라
 * 1. 남아있는 얼음 A[r][c]의 합
 * 2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
 * 
 * 얼음이 있는 칸이 얼음이 있는 칸과 인접해있으면 두 칸을 연결되어있다고 한다.
 * 덩어리는 연결된 칸의 집합이다.
 * 
 * 첫째 줄에 N과 Q가 주어진다.
 * 둘째 줄부터 2^N개의 줄에는 격자의 각 칸에 있는 얼음의 양이 주어진다.
 * 마지막 줄에는 시전한 단계 L1, L2, ... LQ가 순서대로 주어진다.
 */

public class Main {
	static int N, Q, n, l, count;
	static int[][] arr;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		n = (int) Math.pow(2, N);
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int q = 0; q < Q; q++) {
			int L = Integer.parseInt(st.nextToken());
			l = (int) Math.pow(2, L);
			// 배열 회전
			for (int i = 0; i < n; i+=l) {
				for (int j = 0; j < n; j+=l) {
					rotate(i, j);
				}
			}
			
			// 상하좌우 확인
			v = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][j] > 0) {
						check(i, j, v);
					}
				}
			}
			// 확인된 얼음칸 1 삭제
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(v[i][j]) {
						arr[i][j]--;
					}
				}
			}
		}
		// 남아있는 얼음의 합
		System.out.println(ice_sum());
		// 가장 큰 덩어리가 차지하는 칸의 개수
		count = Integer.MIN_VALUE;
		v = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]>0 && !v[i][j]) {
					big_count(i, j);	
				}
			}
		}
		if(count==Integer.MIN_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(count);	
		}
	}
	private static void rotate(int r, int c) {
//		System.out.println(r + " " + c);
	    int[][] temp = new int[l][l];
	    
	    for (int i = 0; i < l; i++) {
	        for (int j = 0; j < l; j++) {
	            temp[j][l-1-i] = arr[r+i][c+j];
	        }
	    }

	    for (int i = 0; i < l; i++) {
	        for (int j = 0; j < l; j++) {
	            arr[r+i][c+j] = temp[i][j];
	        }
	    }
	}
	
	private static void check(int r, int c, boolean[][] v) {
		int sum = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0 && nr<n && nc>=0 && nc<n && arr[nr][nc]>0) sum++;
		}
		if(sum<3) v[r][c] = true;
	}
	private static int ice_sum() {
		int sum = 0;
		for(int[] a : arr) {
			for(int b : a) {
				if(b > 0) sum+=b;
			}
		}
		return sum;
	}
	static class Point {
		int r, c;
		Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	private static void big_count(int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		v[r][c] = true;
		int cnt = 0;
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			cnt++;
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				if(nr>=0 && nr<n && nc>=0 && nc<n && arr[nr][nc]>0 && !v[nr][nc]) {
					queue.offer(new Point(nr, nc));
					v[nr][nc] = true;
				}
			}
		}
		count = Math.max(count, cnt);
	}
}
