import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 빙산은 양의정수, 바다는 0
 * 각 빙산은 4방향에 0이 저장된 개수만큼 줄어든다.
 * 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
 * 한 덩어리 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 "최초의 시간(년)"을 구해라
 * 만일, 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 0을 출력한다.
 */

public class Main {
	static int N, M, result, s_r, s_c;
	static int[][] arr;
	static int[][] reduce;
	static boolean[][] v;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r=r;
			this.c=c;
		}
	}
	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(v[i]));
		}
		System.out.println();
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		reduce = new int[N][M];
		List<Point> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] > 0) list.add(new Point(i, j));
			}
		}
		
		result = 0;
		while(true) {
			// 4방 0 개수 세기
			for (int l = 0; l < list.size(); l++) {
				Point p = list.get(l);
				int count = zeroCount(p.r, p.c);
				reduce[p.r][p.c] = count; 
			}
			
			// 각 빙산 녹는다.
			for (int l = 0; l < list.size(); l++) {
				Point p = list.get(l);
				int num = arr[p.r][p.c] - reduce[p.r][p.c];
				if(num <= 0) {
					num = 0;
					list.remove(l--);
				}
				arr[p.r][p.c] = num;
				
				reduce[p.r][p.c] = 0;
			}
			
			// 빙산 덩어리 개수를 센다.
			int iceCount = 0;
			v = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(arr[i][j] > 0 && !v[i][j]) {
						dfs(i, j);
						iceCount++;
					}
				}
			}
//			print();
//			System.out.println(iceCount);
			result++;
			if(iceCount >=2) {
				break;
			}
			if(list.size() == 0) break;
		}
		
		int iceCount2 = 0;
		v = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] > 0 && !v[i][j]) {
					dfs(i, j);
					iceCount2++;
				}
			}
		}
		if(list.size() == 0) {
			System.out.println(0);
		}else {
			System.out.println(result);
		}
	}
	private static void dfs(int r, int c) {
		// TODO Auto-generated method stub
		v[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc] > 0 && !v[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
	private static int zeroCount(int r, int c) {
		// TODO Auto-generated method stub
		int count = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<M && arr[nr][nc] == 0) {
				count++;
			}
		}
		return count;
	}
}
